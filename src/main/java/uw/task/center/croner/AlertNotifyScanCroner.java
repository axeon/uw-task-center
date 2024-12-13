package uw.task.center.croner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.task.TaskCroner;
import uw.task.center.conf.TaskCenterProperties;
import uw.task.center.entity.TaskAlertInfo;
import uw.task.center.entity.TaskAlertNotify;
import uw.task.center.util.ContactUtils;
import uw.task.center.util.DingUtils;
import uw.task.entity.TaskContact;
import uw.task.entity.TaskCronerConfig;
import uw.task.entity.TaskCronerLog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 发送报警信息定时任务。
 * 3分钟一次扫描未发送的任务。
 **/
@Component
public class AlertNotifyScanCroner extends TaskCroner {


    private static final Logger logger = LoggerFactory.getLogger( AlertNotifyScanCroner.class );
    private final DaoFactory dao = DaoFactory.getInstance();

    /**
     * 时间格式化工具.
     */
    private final FastDateFormat dateFormat = FastDateFormat.getInstance( "yyyy-MM-dd HH:mm:ss" );

    /**
     * 配置中心.
     */
    private final TaskCenterProperties taskCenterProperties;

    /**
     * 邮件发送者.
     */
    private final JavaMailSender mailSender;

    /**
     * 邮件发送者.
     */
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public AlertNotifyScanCroner(TaskCenterProperties taskCenterProperties, JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.taskCenterProperties = taskCenterProperties;
    }

    @Override
    public String runTask(TaskCronerLog taskCronerLog) throws Exception {
        // 先更新到处理中状态
        int effect = dao.executeCommand( "update task_alert_notify set state=1 where state=0 and sent_times=0" );
        if (effect < 1) {
            return "本次执行无数据!";
        }
        DataList<TaskAlertNotify> notifyList = dao.list( TaskAlertNotify.class, "select * from task_alert_notify where state=1 and sent_times=0" );
        dao.executeCommand( "update task_alert_notify set sent_date=now(),sent_times=1 where state=1 and sent_times=0" );

        // 先按照用户收敛.key=email value=infoIdList
        Map<String, String> emailMap = new HashMap<>();
        Map<String, String> notifyMap = new HashMap<>();
        Set<Long> globalInfoIdSet = new HashSet<>();
        for (TaskAlertNotify notify : notifyList) {
            globalInfoIdSet.add( notify.getInfoId() );
            if ("email".equals( notify.getContactType() )) {
                String ids = emailMap.get( notify.getContactInfo() );
                if (ids != null) {
                    ids += "," + notify.getInfoId();
                } else {
                    ids = String.valueOf( notify.getInfoId() );
                }
                emailMap.put( notify.getContactInfo(), ids );
            } else if ("notifyUrl".equals( notify.getContactType() )) {
                String ids = notifyMap.get( notify.getContactInfo() );
                if (ids != null) {
                    ids += "," + notify.getInfoId();
                } else {
                    ids = String.valueOf( notify.getInfoId() );
                }
                notifyMap.put( notify.getContactInfo(), ids );
            }
        }

        // 发送全局告警通知
        if (globalInfoIdSet.size() > 0) {
            DataList<TaskAlertInfo> infoList = dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + StringUtils.join( globalInfoIdSet, ',' ) + ")" );
            String title = "!!!收到" + infoList.size() + "条任务报警信息!";
            StringBuilder content = new StringBuilder();
            for (TaskAlertInfo info : infoList) {
                content.append( "报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( "\n\n" );
                content.append( "报警内容:" ).append( info.getAlertBody() ).append( "\n\n" );
            }
            sendDing( title, content.toString() );
        }

        // 按照信息构造要发送的信息。
        for (Map.Entry<String, String> kv : emailMap.entrySet()) {
            String contactInfo = kv.getKey();
            String infoIds = kv.getValue();
            DataList<TaskAlertInfo> infoList = dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + infoIds + ")" );
            String title = "!!!收到" + infoList.size() + "条任务报警信息!";
            StringBuilder content = new StringBuilder();
            for (TaskAlertInfo info : infoList) {
                content.append( "报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( "\n\n" );
                content.append( "报警内容:" ).append( info.getAlertBody() ).append( "\n\n" );
            }
            sendEmail( contactInfo, title, content.toString() );
        }

        //发送通知信息。
        for (Map.Entry<String, String> kv : notifyMap.entrySet()) {
            String contactInfo = kv.getKey();
            String infoIds = kv.getValue();
            DataList<TaskAlertInfo> infoList = dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + infoIds + ")" );
            String title = "!!!收到" + infoList.size() + "条任务报警信息!";
            StringBuilder content = new StringBuilder();
            for (TaskAlertInfo info : infoList) {
                content.append( "##### 报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( " \n\n " );
                content.append( "##### 报警内容:" ).append( info.getAlertBody() ).append( " \n\n " );
            }
            //默认只支持钉钉。
            notifyUrl( contactInfo, title, content.toString() );
        }
        return "共扫描" + notifyList.size() + "条信息，合并后发送" + emailMap.size() + "条Email, " + notifyMap.size() + "条通知!";
    }

    /**
     * 初始化配置信息。
     */
    @Override
    public TaskCronerConfig initConfig() {
        TaskCronerConfig config = new TaskCronerConfig();
        //任务名称
        config.setTaskName( "任务中心-报警通知扫描定时任务" );
        //任务描述
        config.setTaskDesc( "任务中心-报警通知扫描定时任务" );
        // cron表达式，每1分钟
        config.setTaskCron( "0 */3 * * * ?" );
        //class地址
        config.setTaskClass( this.getClass().getName() );
        //运行模式
        config.setRunType( TaskCronerConfig.RUN_TYPE_SINGLETON );
        //总失败率百分比数值
        config.setAlertFailRate( 10 );
        //程序失败率百分比数值
        config.setAlertFailProgramRate( 10 );
        //接口失败率百分比数值
        config.setAlertFailPartnerRate( 10 );
        //数据失败率百分比报警阀值
        config.setAlertFailDataRate( 10 );
        //限速等待超时ms数
        config.setAlertWaitTimeout( 60_000 );
        //运行超时ms数
        config.setAlertRunTimeout( 120_000 );
        // 记录所有日志
        config.setLogLevel( TaskCronerConfig.TASK_LOG_TYPE_RECORD_ALL );
        return config;
    }

    /**
     * 初始化联系人信息。
     *
     * @return
     */
    @Override
    public TaskContact initContact() {
        return ContactUtils.getTaskContact();
    }

    /**
     * 发送邮件
     *
     * @param toEmail 收件人集合
     * @param title   邮件标题
     * @param content 邮件内容
     */
    private void sendEmail(String toEmail, String title, String content) {
        if (StringUtils.isNotBlank( fromEmail ) && StringUtils.isNotBlank( toEmail )) {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom( fromEmail );// 发件人
            email.setTo( toEmail );// 收件人地址
            email.setSubject( title );// 邮件标题
            email.setText( content );// 邮件信息
            mailSender.send( email );
        }
    }

    /**
     * 发送alert。
     *
     * @param title
     * @param content
     */
    private void sendDing(String title, String content) {
        TaskCenterProperties.DingConfig alertDingConfig = taskCenterProperties.getAlertDing();
        if (alertDingConfig.isValid()) {
            if (logger.isDebugEnabled()) {
                logger.debug( "发送Ding报警信息:title={},content={}", title, content );
            }
            if (StringUtils.isNotBlank( taskCenterProperties.getCenterName() )) {
                title = "[" + taskCenterProperties.getCenterName() + "]" + title;
            }
            content = "### " + title + "\n" + content;
            DingUtils.send( alertDingConfig.getNotifyUrl(), alertDingConfig.getNotifyKey() + title, content );
        } else {
            logger.warn( "发送Ding信息失败，请检查配置！title={},content={}", title, content );
        }
    }


    /**
     * 发送alert。
     * 当前只支持钉钉。。。
     *
     * @param title
     * @param content
     */
    private void notifyUrl(String notifyUrl, String title, String content) {
        TaskCenterProperties.DingConfig alertDingConfig = taskCenterProperties.getAlertDing();
        if (StringUtils.isNotBlank( taskCenterProperties.getCenterName() )) {
            title = "[" + taskCenterProperties.getCenterName() + "]" + title;
        }
        content = "### " + title + "\n" + content;
        DingUtils.send( notifyUrl, alertDingConfig.getNotifyKey() + title, content );
    }
}
