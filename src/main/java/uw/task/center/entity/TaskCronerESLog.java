package uw.task.center.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;
import uw.log.es.vo.LogBaseVo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * taskCronerLog实体类。
 *
 * @author axeon
 * @version $Revision: 1.00 $ $Date: 2017-08-14 10:36:29
 */
@Schema(title = "定时任务ES实体类", description = "定时任务ES实体类")
@TableMeta(tableName = "\\\"uw.task.croner.log\\\"")
public class TaskCronerESLog extends LogBaseVo implements Serializable {

    /**
     * id
     */
    @ColumnMeta(columnName = "id", dataType = "long", dataSize = 19, nullable = false, primaryKey = true, autoIncrement = true)
    @Schema(title = "id")
    private long id;

    /**
     * 定时任务id
     */
    @ColumnMeta(columnName = "task_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "定时任务id", description = "定时任务id")
    private long taskId;

    /**
     * 关联ID
     */
    @ColumnMeta(columnName = "ref_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "关联ID")
    private long refId;

    /**
     * 执行的类名
     */
    @ColumnMeta(columnName = "task_class", dataType = "String", dataSize = 200, nullable = true)
    @Schema(title = "执行的类名")
    private String taskClass;

    /**
     * 任务参数
     */
    @ColumnMeta(columnName = "task_param", dataType = "String", dataSize = 200, nullable = true)
    @Schema(title = "任务参数")
    private String taskParam;

    /**
     * 配置信息
     */
    @ColumnMeta(columnName = "task_cron", dataType = "String", dataSize = 200, nullable = true)
    @Schema(title = "配置信息")
    private String taskCron;

    /**
     * 运行类型
     */
    @ColumnMeta(columnName = "run_type", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "运行类型", description = "运行类型")
    private int runType;

    /**
     * 执行主机位置
     */
    @ColumnMeta(columnName = "run_target", dataType = "String", dataSize = 20, nullable = true)
    @Schema(title = "执行主机位置")
    private String runTarget;

    /**
     * 计划执行时间
     */
    @ColumnMeta(columnName = "schedule_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "计划执行时间", description = "计划执行时间")
    private java.util.Date scheduleDate;

    /**
     * 开始运行时间
     */
    @ColumnMeta(columnName = "run_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "开始运行时间", description = "开始运行时间")
    private java.util.Date runDate;

    /**
     * 运行结束日期
     */
    @ColumnMeta(columnName = "finish_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "运行结束日期", description = "运行结束日期")
    private java.util.Date finishDate;

    /**
     * 下次执行时间
     */
    @ColumnMeta(columnName = "next_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "下次执行时间", description = "下次执行时间")
    private java.util.Date nextDate;

    /**
     * 执行信息，用于存储任务完成信息。
     */
    @ColumnMeta(columnName = "result_data", dataType = "String", dataSize = 1000, nullable = true)
    @Schema(title = "执行信息，用于存储任务完成信息。")
    private String resultData;

    /**
     * 执行状态
     */
    @ColumnMeta(columnName = "state", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "执行状态", description = "执行状态")
    private int state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public int getRunType() {
        return runType;
    }

    public void setRunType(int runType) {
        this.runType = runType;
    }

    public String getRunTarget() {
        return runTarget;
    }

    public void setRunTarget(String runTarget) {
        this.runTarget = runTarget;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Date getRunDate() {
        return runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
