package uw.task.center.croner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uw.dao.DaoManager;
import uw.common.data.PageList;
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


    /**
     * 日志器。
     */
    private static final Logger logger = LoggerFactory.getLogger( AlertNotifyScanCroner.class );
    /**
     * 数据库操作对象。
     */
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 时间格式化工具.
     */
    private final FastDateFormat dateFormat = FastDateFormat.getInstance( "yyyy-MM-dd HH:mm:ss" );

    /**
     * 配置中心.
     */
    private final TaskCenterProperties taskCenterProperties;


    /**
     * @param taskCenterProperties 任务中心配置
     */
    @Autowired
    public AlertNotifyScanCroner(TaskCenterProperties taskCenterProperties) {
        this.taskCenterProperties = taskCenterProperties;
    }

    /**
     * 扫描待发送的告警通知，按联系人收敛后通过钉钉/notifyUrl 发送。
     *
     * @param taskCronerLog 任务执行日志
     * @return 本次扫描与发送的统计描述
     */
    @Override
    public String runTask(TaskCronerLog taskCronerLog){
        // 先以原子条件推进 state 0->1：仅本实例抢到的行才会被发送，
        // 避免多任务中心实例并发对同一批通知重复发送（钉钉告警刷屏）。
        // 先以原子条件推进 state 0->1：仅本实例抢到的行才会被发送，避免多实例并发重复发送。
        dao.execute( "update task_alert_notify set state=1 where state=0 and sent_times=0" );
        // 无论本次是否抢占到新通知，都查 state=1且sent_times=0：含本次抢占 + 上次实例崩溃遗留的孤儿，避免孤儿永久滞留。
        PageList<TaskAlertNotify> notifyList = dao.list( TaskAlertNotify.class, "select * from task_alert_notify where state=1 and sent_times=0" ).getData();
        if (notifyList == null || notifyList.isEmpty()){
            return "本次执行无数据!";
        }
        // 标记 sent_times=1：因 state 已被本实例原子抢占（0->1），其他实例不会重复发送。
        // 注：发送为同步 HTTP 调用，理论上仍存在"标记成功但发送异常"的丢通知窗口，
        // 当前钉钉/notifyUrl 发送失败仅记日志不抛出，保持与原行为一致，避免无限重试刷屏。
        dao.execute( "update task_alert_notify set sent_date=now(),sent_times=1 where state=1 and sent_times=0" );

        // 按联系人收敛 notifyUrl：key=notifyUrl value=infoIdList（email 通道暂未启用，相关死代码已移除）
        Map<String, String> notifyMap = new HashMap<>();
        Set<Long> globalInfoIdSet = new HashSet<>();
        for (TaskAlertNotify notify : notifyList) {
            globalInfoIdSet.add( notify.getInfoId() );
            if ("notifyUrl".equals( notify.getContactType() )) {
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
        if (!globalInfoIdSet.isEmpty()) {
            dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + StringUtils.join( globalInfoIdSet, ',' ) + ")" ).onSuccess( list -> {
                String title = "!!!收到" + list.size() + "条任务报警信息!";
                StringBuilder content = new StringBuilder();
                for (TaskAlertInfo info : list) {
                    content.append( "报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( "\n\n" );
                    content.append( "报警内容:" ).append( info.getAlertBody() ).append( "\n\n" );
                }
                sendDing( title, content.toString() );
            });
        }

        //发送通知信息。
        for (Map.Entry<String, String> kv : notifyMap.entrySet()) {
            String contactInfo = kv.getKey();
            String infoIds = kv.getValue();
            dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + infoIds + ")" ).onSuccess( list -> {
                String title = "!!!收到" + list.size() + "条任务报警信息!";
                StringBuilder content = new StringBuilder();
                for (TaskAlertInfo info : list) {
                    content.append( "##### 报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( " \n\n " );
                    content.append( "##### 报警内容:" ).append( info.getAlertBody() ).append( " \n\n " );
                }
                //默认只支持钉钉。
                notifyUrl( contactInfo, title, content.toString() );
            });
        }
        return "共扫描" + notifyList.size() + "条信息，合并后发送" + notifyMap.size() + "条通知!";
    }

    /**
     * 初始化配置信息（每 3 分钟运行一次，全局单例）。
     *
     * @return 定时任务配置
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
     * @return 联系人信息
     */
    @Override
    public TaskContact initContact() {
        return ContactUtils.getTaskContact();
    }

    /**
     * 通过全局钉钉配置发送告警。
     *
     * @param title   告警标题
     * @param content 告警正文（markdown）
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
     * 向指定 notifyUrl 发送告警（当前底层仅支持钉钉 webhook）。
     *
     * @param notifyUrl 接收方 notifyUrl（钉钉 webhook）
     * @param title     告警标题
     * @param content   告警正文（markdown）
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
