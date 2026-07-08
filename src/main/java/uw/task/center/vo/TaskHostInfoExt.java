package uw.task.center.vo;

import uw.task.center.entity.TaskCronerStats;
import uw.task.center.entity.TaskDelayerStats;
import uw.task.center.entity.TaskHostInfo;
import uw.task.center.entity.TaskRunnerStats;

import java.util.List;

/**
 * task 主机信息扩展类。
 *
 * <p>在 {@link TaskHostInfo} 基础上附带主机本次上报的 croner / runner / delay 统计明细列表，
 * 作为 {@code /rpc/task/host/report} 接口的请求体。</p>
 *
 * @author axeon
 */
public class TaskHostInfoExt extends TaskHostInfo {

    /**
     * 本次上报的定时任务统计信息列表。
     */
    private List<TaskCronerStats> taskCronerStatsList;

    /**
     * 本次上报的队列任务统计信息列表。
     */
    private List<TaskRunnerStats> taskRunnerStatsList;

    /**
     * 本次上报的延迟任务统计信息列表。
     */
    private List<TaskDelayerStats> taskDelayerStatsList;

    public List<TaskCronerStats> getTaskCronerStatsList() {
        return taskCronerStatsList;
    }

    public void setTaskCronerStatsList(List<TaskCronerStats> taskCronerStatsList) {
        this.taskCronerStatsList = taskCronerStatsList;
    }

    public List<TaskRunnerStats> getTaskRunnerStatsList() {
        return taskRunnerStatsList;
    }

    public void setTaskRunnerStatsList(List<TaskRunnerStats> taskRunnerStatsList) {
        this.taskRunnerStatsList = taskRunnerStatsList;
    }

    public List<TaskDelayerStats> getTaskDelayStatsList() {
        return taskDelayerStatsList;
    }

    public void setTaskDelayStatsList(List<TaskDelayerStats> taskDelayStatsList) {
        this.taskDelayerStatsList = taskDelayStatsList;
    }
}
