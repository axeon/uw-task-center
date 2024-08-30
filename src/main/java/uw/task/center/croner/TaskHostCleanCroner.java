package uw.task.center.croner;

import org.springframework.stereotype.Component;
import uw.dao.DaoFactory;
import uw.task.TaskCroner;
import uw.task.center.util.ContactUtils;
import uw.task.entity.TaskContact;
import uw.task.entity.TaskCronerConfig;
import uw.task.entity.TaskCronerLog;

import java.util.Date;

/**
 * 清理过期的任务主机。
 */
@Component
public class TaskHostCleanCroner extends TaskCroner {

    private final DaoFactory dao = DaoFactory.getInstance();

    /**
     * 运行任务。
     *
     * @param taskCronerLog
     */
    @Override
    public String runTask(TaskCronerLog taskCronerLog) throws Exception {
        int effectNum = dao.executeCommand( "update task_host_info set state=-1 where last_update<? and state=1", new Object[]{new Date( System.currentTimeMillis() - 300_000L )} );
        return "清理过期主机记录:" + effectNum;
    }

    /**
     * 初始化配置信息。
     */
    @Override
    public TaskCronerConfig initConfig() {
        TaskCronerConfig config = new TaskCronerConfig();
        //任务名称
        config.setTaskName( "任务中心-清理过期的任务主机" );
        //任务描述
        config.setTaskDesc( "任务中心-清理过期的任务主机" );
        // cron表达式，每3分钟
        config.setTaskCron( "0 */5 * * * ?" );
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
