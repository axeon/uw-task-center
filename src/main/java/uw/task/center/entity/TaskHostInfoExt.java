package uw.task.center.entity;

import java.util.List;

/**
 * task主机信息扩展类。
 */
public class TaskHostInfoExt extends TaskHostInfo {

    /**
     * croner运行统计信息。
     */
    private List<TaskCronerStats> taskCronerStatsList;

    /**
     * runner运行统计信息。
     */
    private List<TaskRunnerStats> taskRunnerStatsList;

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
}
