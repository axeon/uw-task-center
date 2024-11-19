package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
 * 定时任务配置列表查询参数。
 */
@Schema(title = "定时任务配置列表查询参数", description = "定时任务配置列表查询参数")
public class TaskCronerLogQueryParam extends PageQueryParam {

    /**
     * id
     */
    @QueryMeta(expr = "id=?")
    @Schema(title = "ID", description = "ID")
    private Long id;

    /**
     * id
     */
    @QueryMeta(expr = "refId=?")
    @Schema(title = "refId", description = "refId")
    private Long refId;

    /**
     * id
     */
    @QueryMeta(expr = "taskId=?")
    @Schema(title = "定时任务id", description = "定时任务id")
    private Long taskId;

    /**
     * 执行类信息
     */
    @QueryMeta(expr = "taskClass like ?")
    @Schema(title = "执行类信息", description = "执行类信息")
    private String taskClass;

    /**
     * 任务参数
     */
    @QueryMeta(expr = "taskParam=?")
    @Schema(title = "任务参数", description = "任务参数")
    private String taskParam;

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
     * 应用信息
     */
    @QueryMeta(expr = "hostIp=?")
    @Schema(title = "应用信息", description = "应用信息")
    private String appInfo;

    /**
     * 应用主机
     */
    @QueryMeta(expr = "appHost=?")
    @Schema(title = "应用主机", description = "应用主机")
    private String appHost;

    /**
     * 运行时间范围
     */
    @QueryMeta(expr = "runDate between ? and ?")
    @Schema(title = "运行时间范围", description = "运行时间范围")
    private Long[] runDateRange;

    /**
     * 状态。
     */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态", description = "状态")
    private Integer state;

    /**
     * 数组状态。
     */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="数组状态", description = "状态数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
     * 大于等于状态。
     */
    @QueryMeta(expr = "state>=?")
    @Schema(title="大于等于状态", description = "大于等于状态")
    private Integer stateGte;

    /**
     * 小于等于状态。
     */
    @QueryMeta(expr = "state<=?")
    @Schema(title="小于等于状态", description = "小于等于状态")
    private Integer stateLte;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getAppHost() {
        return appHost;
    }

    public void setAppHost(String appHost) {
        this.appHost = appHost;
    }

    public Long[] getRunDateRange() {
        return runDateRange;
    }

    public void setRunDateRange(Long[] runDateRange) {
        this.runDateRange = runDateRange;
    }
    /**
     * 获取状态。
     */
    public Integer getState(){
        return this.state;
    }

    /**
     * 设置状态。
     */
    public void setState(Integer state){
        this.state = state;
    }

    /**
     * 获取数组状态。
     */
    public Integer[] getStates(){
        return this.states;
    }

    /**
     * 设置数组状态。
     */
    public void setStates(Integer[] states){
        this.states = states;
    }

    /**
     * 获取大于等于状态。
     */
    public Integer getStateGte(){
        return this.stateGte;
    }

    /**
     * 设置大于等于状态。
     */
    public void setStateGte(Integer stateGte){
        this.stateGte = stateGte;
    }

    /**
     * 获取小于等于状态。
     */
    public Integer getStateLte(){
        return this.stateLte;
    }

    /**
     * 获取小于等于状态。
     */
    public void setStateLte(Integer stateLte){
        this.stateLte = stateLte;
    }

}