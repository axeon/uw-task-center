package uw.task.center.croner;

import org.springframework.stereotype.Component;
import uw.common.response.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
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

    /**
     * 数据库操作对象。
     */
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 将超过 5 分钟未上报的主机记录标记为删除（state=-1）。
     *
     * @param taskCronerLog 任务执行日志
     * @return 清理结果描述（含影响行数）
     */
    @Override
    public String runTask(TaskCronerLog taskCronerLog) throws Exception {
        ResponseData responseData = dao.execute("update task_host_info set state=-1 where last_update<? and state=1", new Object[]{new Date(SystemClock.now() - 300_000L)});
        return "清理过期主机记录:" + responseData.getData();
    }

    /**
     * 初始化配置信息（每 5 分钟运行一次，全局单例）。
     *
     * @return 定时任务配置
     */
    @Override
    public TaskCronerConfig initConfig() {
        TaskCronerConfig config = new TaskCronerConfig();
        //任务名称
        config.setTaskName("任务中心-清理过期的任务主机");
        //任务描述
        config.setTaskDesc("任务中心-清理过期的任务主机");
        // cron表达式，每3分钟
        config.setTaskCron("0 */5 * * * ?");
        //class地址
        config.setTaskClass(this.getClass().getName());
        //运行模式
        config.setRunType(TaskCronerConfig.RUN_TYPE_SINGLETON);
        //总失败率百分比数值
        config.setAlertFailRate(10);
        //程序失败率百分比数值
        config.setAlertFailProgramRate(10);
        //接口失败率百分比数值
        config.setAlertFailPartnerRate(10);
        //数据失败率百分比报警阀值
        config.setAlertFailDataRate(10);
        //限速等待超时ms数
        config.setAlertWaitTimeout(60_000);
        //运行超时ms数
        config.setAlertRunTimeout(120_000);
        // 记录所有日志
        config.setLogLevel(TaskCronerConfig.TASK_LOG_TYPE_RECORD_ALL);
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
}
