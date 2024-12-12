package uw.task.center.croner;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.task.TaskCroner;
import uw.task.TaskData;
import uw.task.TaskFactory;
import uw.task.center.conf.TaskCenterProperties;
import uw.task.center.entity.TaskAlertInfo;
import uw.task.center.entity.TaskAlertNotify;
import uw.task.center.runner.AlertNotifySendRunner;
import uw.task.center.util.ContactUtils;
import uw.task.center.vo.TaskAlertNotifyData;
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

    private static final Joiner JOINER = Joiner.on( "," ).skipNulls();
    private final DaoFactory dao = DaoFactory.getInstance();
    private final FastDateFormat dateFormat = FastDateFormat.getInstance( "yyyy-MM-dd HH:mm:ss" );
    private final TaskFactory taskFactory;
    private final TaskCenterProperties taskCenterProperties;


    @Autowired
    public AlertNotifyScanCroner(TaskFactory taskFactory, TaskCenterProperties taskCenterProperties) {
        this.taskFactory = taskFactory;
        this.taskCenterProperties = taskCenterProperties;
    }

    @Override
    public String runTask(TaskCronerLog taskCronerLog) throws Exception {
        // 先更新到处理中状态
        int effect = dao.executeCommand( "update task_alert_notify set state=1 where state=0 and sent_times=0" );
        if (effect < 1) {
            return "本次执行无数据!";
        }
        DataList<TaskAlertNotify> notifyList = dao.list( TaskAlertNotify.class,
                "select * from task_alert_notify where state=1 and sent_times=0" );
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
        if (StringUtils.isNotBlank( taskCenterProperties.getGlobalAlertNotifyUrl() ) && globalInfoIdSet.size() > 0) {
            DataList<TaskAlertInfo> infoList = dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + JOINER.join( globalInfoIdSet ) + ")" );
            String title = "!!!收到" + infoList.size() + "条任务报警信息!";
            StringBuilder content = new StringBuilder();
            for (TaskAlertInfo info : infoList) {
                content.append( "报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( "\n" );
                content.append( "报警标题:" ).append( info.getAlertTitle() ).append( "\n" );
                content.append( "报警内容:" ).append( info.getAlertBody() ).append( "\n\n" );
            }
            TaskAlertNotifyData notifyData = new TaskAlertNotifyData("notifyUrl", taskCenterProperties.getGlobalAlertNotifyUrl(), title, content.toString() );
            TaskData<TaskAlertNotifyData, String> taskData = new TaskData<>();
            taskData.setTaskClass( AlertNotifySendRunner.class.getName() );
            taskData.setTaskParam( notifyData );
            taskFactory.runQueue( taskData );
        }

        // 按照信息构造要发送的信息。
        for (Map.Entry<String, String> kv : emailMap.entrySet()) {
            String contactInfo = kv.getKey();
            String infoIds = kv.getValue();
            DataList<TaskAlertInfo> infoList = dao.list( TaskAlertInfo.class, "select * from task_alert_info where id in (" + infoIds + ")" );
            String title = "!!!收到" + infoList.size() + "条任务报警信息!";
            StringBuilder content = new StringBuilder();
            for (TaskAlertInfo info : infoList) {
                content.append( "报警时间:" ).append( dateFormat.format( info.getCreateDate() ) ).append( "\n" );
                content.append( "报警标题:" ).append( info.getAlertTitle() ).append( "\n" );
                content.append( "报警内容:" ).append( info.getAlertBody() ).append( "\n\n" );
            }
            TaskAlertNotifyData notifyData = new TaskAlertNotifyData( "email", contactInfo, title, content.toString() );
            TaskData<TaskAlertNotifyData, String> taskData = new TaskData<>();
            taskData.setTaskClass( AlertNotifySendRunner.class.getName() );
            taskData.setTaskParam( notifyData );
            taskFactory.runQueue( taskData );
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
                content.append( "##### 报警标题:" ).append( info.getAlertTitle() ).append( " \n\n " );
                content.append( "##### 报警内容:" ).append( info.getAlertBody() ).append( " \n\n " );
            }
            TaskAlertNotifyData notifyData = new TaskAlertNotifyData("notifyUrl", contactInfo, title, content.toString() );
            taskFactory.runQueue( TaskData.builder( AlertNotifySendRunner.class.getName() ).taskParam( notifyData ).build() );
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
}
