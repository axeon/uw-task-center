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
 * TaskDelayerLog ES 实体类（延迟任务执行日志，索引 uw.task.delayer.log）。
 *
 * <p>客户端 TaskDelayerContainer 执行后通过 LogClient 直写 ES（载体复用 TaskData），
 * 服务端 TaskDelayerLogController 只读查询。字段对齐客户端 {@code uw.task.entity.TaskDelayerLog}
 * （委托 TaskData 的 getter）。</p>
 *
 * @author axeon
 */
@Schema(title = "延迟任务ES实体类", description = "延迟任务ES实体类")
@TableMeta(tableName = "\"uw.task.delayer.log\"")
public class TaskDelayerEsLog extends LogBaseVo implements Serializable {

    @ColumnMeta(columnName = "id", dataType = "long", dataSize = 19, nullable = false, primaryKey = true)
    @Schema(title = "id", format = "long", defaultValue = "1")
    private long id;

    @ColumnMeta(columnName = "task_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "延迟任务配置id", description = "延迟任务配置id")
    private long taskId;

    @ColumnMeta(columnName = "task_class", dataType = "String", dataSize = 200, nullable = true)
    @Schema(title = "执行的类名", description = "执行的类名")
    private String taskClass;

    @ColumnMeta(columnName = "task_tag", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "任务标签", description = "任务标签")
    private String taskTag;

    @ColumnMeta(columnName = "ref_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "关联ID", description = "关联ID")
    private long refId;

    @ColumnMeta(columnName = "ref_sub_id", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "关联子ID", description = "关联子ID")
    private long refSubId;

    @ColumnMeta(columnName = "ref_tag", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "关联tag", description = "关联tag")
    private String refTag;

    @ColumnMeta(columnName = "task_delay", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "延迟毫秒数", description = "延迟毫秒数")
    private long taskDelay;

    @ColumnMeta(columnName = "task_param", dataType = "String", dataSize = 2000, nullable = true)
    @Schema(title = "执行参数", description = "执行参数")
    @JsonDeserialize(using = JsonAsStringDeserializer.class)
    private String taskParam;

    @ColumnMeta(columnName = "run_target", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "运行目标", description = "运行目标")
    private String runTarget;

    @ColumnMeta(columnName = "rate_limit_tag", dataType = "String", dataSize = 100, nullable = true)
    @Schema(title = "流量限速TAG", description = "流量限速TAG")
    private String rateLimitTag;

    @ColumnMeta(columnName = "queue_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "提交入zset时间", description = "提交入zset时间")
    private Date queueDate;

    @ColumnMeta(columnName = "consume_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "被poll取出时间", description = "被poll取出时间")
    private Date consumeDate;

    @ColumnMeta(columnName = "run_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "开始运行时间", description = "开始运行时间")
    private Date runDate;

    @ColumnMeta(columnName = "finish_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "运行结束时间", description = "运行结束时间")
    private Date finishDate;

    @ColumnMeta(columnName = "trigger_date", dataType = "java.util.Date", dataSize = 19, nullable = true)
    @Schema(title = "触发执行时刻(同runDate)", description = "触发执行时刻")
    private Date triggerDate;

    @ColumnMeta(columnName = "delay_millis", dataType = "long", dataSize = 19, nullable = true)
    @Schema(title = "实际延迟时长(runDate-(queueDate+taskDelay))", description = "实际延迟时长")
    private long delayMillis;

    @ColumnMeta(columnName = "ran_times", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "已经执行的次数", description = "已经执行的次数")
    private int ranTimes;

    @ColumnMeta(columnName = "retry_type", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "重试类型", description = "重试类型")
    private int retryType;

    @ColumnMeta(columnName = "state", dataType = "int", dataSize = 10, nullable = true)
    @Schema(title = "执行状态", description = "执行状态")
    private int state;

    @ColumnMeta(columnName = "result_data", dataType = "String", dataSize = 2000, nullable = true)
    @Schema(title = "执行结果", description = "执行结果")
    @JsonDeserialize(using = JsonAsStringDeserializer.class)
    private String resultData;

    @Schema(title = "出错信息", description = "出错信息")
    @JsonDeserialize(using = JsonAsStringDeserializer.class)
    private String errorInfo;

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

    public String getRunTarget() {
        return runTarget;
    }

    public void setRunTarget(String runTarget) {
        this.runTarget = runTarget;
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

    public Date getTriggerDate() {
        return triggerDate;
    }

    public void setTriggerDate(Date triggerDate) {
        this.triggerDate = triggerDate;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public int getRanTimes() {
        return ranTimes;
    }

    public void setRanTimes(int ranTimes) {
        this.ranTimes = ranTimes;
    }

    public int getRetryType() {
        return retryType;
    }

    public void setRetryType(int retryType) {
        this.retryType = retryType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
}
