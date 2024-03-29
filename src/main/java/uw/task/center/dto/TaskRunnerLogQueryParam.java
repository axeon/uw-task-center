package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
 * 定时任务配置列表查询参数。
 */
@Schema(title = "定时任务配置列表查询参数", description = "定时任务配置列表查询参数")
public class TaskRunnerLogQueryParam extends PageQueryParam {

    /**
     * id
     */
    @QueryMeta(expr = "id=?")
    @Schema(title = "ID", description = "ID")
    private Long id;

    /**
     * id
     */
    @QueryMeta(expr = "taskId=?")
    @Schema(title = "任务ID", description = "任务ID")
    private Long taskId;

    /**
     * 关联ID
     */
    @QueryMeta(expr = "refId=?")
    @Schema(title = "关联ID", description = "关联ID")
    private Long refId;

    /**
     * 关联子ID
     */
    @QueryMeta(expr = "refSubId=?")
    @Schema(title = "关联子ID", description = "关联子ID")
    private Long refSubId;

    /**
     * 关联tag
     */
    @QueryMeta(expr = "refTag=?")
    @Schema(title = "关联tag", description = "关联tag")
    private String refTag;

    /**
     * 任务标签
     */
    @QueryMeta(expr = "taskTag=?")
    @Schema(title = "任务标签", description = "任务标签")
    private String taskTag;

    /**
     * 执行类信息
     */
    @QueryMeta(expr = "taskClass like ?")
    @Schema(title = "执行类信息", description = "执行类信息")
    private String taskClass;

    /**
     * 运行类型
     */
    @QueryMeta(expr = "runType=?")
    @Schema(title = "运行类型", description = "运行类型")
    private Integer runType;

    /**
     * 运行目标
     */
    @QueryMeta(expr = "runTarget=?")
    @Schema(title = "运行目标", description = "运行目标")
    private String runTarget;

    /**
     * 队列类型
     */
    @QueryMeta(expr = "queueType=?")
    @Schema(title = "队列类型", description = "队列类型")
    private int queueType;

    /**
     * 重试类型
     */
    @QueryMeta(expr = "retryType=?")
    @Schema(title = "重试类型", description = "重试类型")
    private int retryType;

    /**
     * 流量限速TAG
     */
    @QueryMeta(expr = "rateLimitTag=?")
    @Schema(title = "流量限速TAG", description = "流量限速TAG")
    private String rateLimitTag;

    /**
     * 队列时间范围
     */
    @QueryMeta(expr = "queueDate between ? and ?")
    @Schema(title = "队列时间范围", description = "队列时间范围")
    private Long[] queueDateRange;

    /**
     * 运行时间范围
     */
    @QueryMeta(expr = "runDate between ? and ?")
    @Schema(title = "运行时间范围", description = "运行时间范围")
    private Long[] runDateRange;

    /**
     * 状态1正常，0暂停，-1标记删除
     */
    @QueryMeta(expr = "state=?")
    @Schema(title = "状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除")
    private Integer state;

    /**
     * 正常状态1正常，0暂停，-1标记删除
     */
    @QueryMeta(expr = "state>-1")
    @Schema(title = "正常状态1正常，0暂停，-1标记删除", description = "正常状态1正常，0暂停，-1标记删除")
    private Boolean stateOn;

    /**
     * 状态1正常，0暂停，-1标记删除数组
     */
    @QueryMeta(expr = "state in (?)")
    @Schema(title = "状态1正常，0暂停，-1标记删除数组", description = "状态1正常，0暂停，-1标记删除数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
     * 状态1正常，0暂停，-1标记删除运算条件。
     * 可以使用运算符号。
     */
    @QueryMeta(expr = "state ?")
    @Schema(title = "状态1正常，0暂停，-1标记删除运算条件", description = "状态1正常，0暂停，-1标记删除运算条件，可使用><=!比较运算符。")
    private String stateOp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getRefSubId() {
        return refSubId;
    }

    public void setRefSubId(Long refSubId) {
        this.refSubId = refSubId;
    }

    public String getRefTag() {
        return refTag;
    }

    public void setRefTag(String refTag) {
        this.refTag = refTag;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public Integer getRunType() {
        return runType;
    }

    public void setRunType(Integer runType) {
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

    public Long[] getQueueDateRange() {
        return queueDateRange;
    }

    public void setQueueDateRange(Long[] queueDateRange) {
        this.queueDateRange = queueDateRange;
    }

    public Long[] getRunDateRange() {
        return runDateRange;
    }

    public void setRunDateRange(Long[] runDateRange) {
        this.runDateRange = runDateRange;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getStateOn() {
        return stateOn;
    }

    public void setStateOn(Boolean stateOn) {
        this.stateOn = stateOn;
    }

    public Integer[] getStates() {
        return states;
    }

    public void setStates(Integer[] states) {
        this.states = states;
    }

    public String getStateOp() {
        return stateOp;
    }

    public void setStateOp(String stateOp) {
        this.stateOp = stateOp;
    }
}