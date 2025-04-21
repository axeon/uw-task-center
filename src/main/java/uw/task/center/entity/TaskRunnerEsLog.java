package uw.task.center.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;
import uw.log.es.vo.LogBaseVo;
import uw.task.center.util.JsonAsStringDeserializer;

import java.io.Serializable;
import java.util.Date;

/**
 * taskRunnerLog实体类。
 *
 * @author axeon
 * @version $Revision: 1.00 $ $Date: 2017-08-14 10:36:29
 */
@Schema(title = "队列任务ES实体类", description = "队列任务ES实体类")
@TableMeta(tableName = "\\\"uw.task.runner.log\\\"")
public class TaskRunnerEsLog extends LogBaseVo implements Serializable {

    /**
     * id
     */
    @ColumnMeta(columnName = "id", dataType = "long", dataSize = 19, nullable = false, primaryKey = true, autoIncrement = true)
    @Schema(title = "id", format = "long", defaultValue = "1")
    private long id;

    /**
     * 队列任务id
     */
    @ColumnMeta(columnName = "task_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "队列任务id", description = "队列任务id")
    private long taskId;

    /**
     * 关联ID
     */
    @ColumnMeta(columnName = "ref_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "关联ID", description = "关联ID")
    private long refId;

    /**
     * 关联子ID
     */
    @ColumnMeta(columnName = "ref_sub_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "关联子ID", description = "关联子ID")
    private long refSubId;

    /**
     * 关联tag
     */
    @ColumnMeta(columnName = "ref_tag", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "关联tag", description = "关联tag")
    private String refTag;

    /**
     * 执行的类名
     */
    @ColumnMeta(columnName = "task_class", dataType = "String", dataSize = 200, nullable = true)
    @Schema(title = "执行的类名", description = "执行的类名")
    private String taskClass;

    /**
     * 任务标签
     */
    @ColumnMeta(columnName = "task_tag", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "任务标签", description = "任务标签")
    private String taskTag;

    /**
     * 任务指定延时毫秒数
     */
    @ColumnMeta(columnName = "task_delay", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "任务延时毫秒数", description = "任务延时毫秒数")
    private long taskDelay;

    /**
     * 执行参数，建议用json表达。
     */
    @ColumnMeta(columnName = "task_param", dataType = "String", dataSize = 2000, nullable = true)
    @Schema(title = "执行参数，建议用json表达。", description = "执行参数，建议用json表达。")
    @JsonDeserialize(using = JsonAsStringDeserializer.class)
    private String taskParam;

    /**
     * 执行类型
     */
    @ColumnMeta(columnName = "run_type", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "执行类型", description = "执行类型")
    private int runType;

    /**
     * 运行目标
     */
    @ColumnMeta(columnName = "run_target", dataType = "String", dataSize = 20, nullable = true)
    @Schema(title = "运行目标", description = "运行目标")
    private String runTarget;

    /**
     * 队列类型
     */
    @ColumnMeta(columnName = "queue_type", dataType = "String", dataSize = 20, nullable = true)
    @Schema(title = "队列类型", description = "队列类型")
    private int queueType;

    /**
     * 重试类型
     */
    @ColumnMeta(columnName = "retry_type", dataType = "String", dataSize = 20, nullable = true)
    @Schema(title = "重试类型", description = "重试类型")
    private int retryType;

    /**
     * 流量限速TAG
     */
    @ColumnMeta(columnName = "rate_limit_tag", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "流量限速TAG", description = "流量限速TAG")
    private String rateLimitTag;

    /**
     * 进入队列时间
     */
    @ColumnMeta(columnName = "queue_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "进入队列时间", description = "进入队列时间")
    private java.util.Date queueDate;

    /**
     * 开始消费时间
     */
    @ColumnMeta(columnName = "consume_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "开始消费时间", description = "开始消费时间")
    private java.util.Date consumeDate;

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
     * 执行信息，用于存储任务完成信息。
     */
    @ColumnMeta(columnName = "result_data", dataType = "String", dataSize = 2000, nullable = true)
    @Schema(title = "执行信息，用于存储任务完成信息。", description = "执行信息，用于存储任务完成信息。")
    @JsonDeserialize(using = JsonAsStringDeserializer.class)
    private String resultData;

    /**
     * 执行信息，用于存储任务错误信息，并不入库。
     */
    @Schema(title = "用于存储出错信息。", description = "用于存储出错信息。")
    @JsonDeserialize(using = JsonAsStringDeserializer.class)
    private String errorInfo;

    /**
     * 已经执行的次数
     */
    @ColumnMeta(columnName = "ran_times", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "已经执行的次数", description = "已经执行的次数")
    private int ranTimes;

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

    public long getRefSubId() {
        return refSubId;
    }

    public void setRefSubId(long refSubId) {
        this.refSubId = refSubId;
    }

    public String getRefTag() {
        return refTag;
    }

    public void setRefTag(String refTag) {
        this.refTag = refTag;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public long getTaskDelay() {
        return taskDelay;
    }

    public void setTaskDelay(long taskDelay) {
        this.taskDelay = taskDelay;
    }

    public String getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
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

    public int getQueueType() {
        return queueType;
    }

    public void setQueueType(int queueType) {
        this.queueType = queueType;
    }

    public int getRetryType() {
        return retryType;
    }

    public void setRetryType(int retryType) {
        this.retryType = retryType;
    }

    public String getRateLimitTag() {
        return rateLimitTag;
    }

    public void setRateLimitTag(String rateLimitTag) {
        this.rateLimitTag = rateLimitTag;
    }

    public Date getQueueDate() {
        return queueDate;
    }

    public void setQueueDate(Date queueDate) {
        this.queueDate = queueDate;
    }

    public Date getConsumeDate() {
        return consumeDate;
    }

    public void setConsumeDate(Date consumeDate) {
        this.consumeDate = consumeDate;
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

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public int getRanTimes() {
        return ranTimes;
    }

    public void setRanTimes(int ranTimes) {
        this.ranTimes = ranTimes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
