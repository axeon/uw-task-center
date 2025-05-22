package uw.task.center.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.util.JsonUtils;
import uw.dao.DataEntity;
import uw.dao.DataUpdateInfo;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

import java.io.Serializable;


/**
 * TaskCronerInfo实体类
 * 定时任务配置
 *
 * @author axeon
 */
@TableMeta(tableName="task_croner_info",tableType="table")
@Schema(title = "定时任务配置", description = "定时任务配置")
public class TaskCronerInfo implements DataEntity,Serializable{


    /**
     * id
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "id", description = "id", maxLength=19, nullable=false )
    private long id;

    /**
     * 任务名称
     */
    @ColumnMeta(columnName="task_name", dataType="String", dataSize=200, nullable=true)
    @Schema(title = "任务名称", description = "任务名称", maxLength=200, nullable=true )
    private String taskName;

    /**
     * 任务描述
     */
    @ColumnMeta(columnName="task_desc", dataType="String", dataSize=1000, nullable=true)
    @Schema(title = "任务描述", description = "任务描述", maxLength=1000, nullable=true )
    private String taskDesc;

    /**
     * 执行类信息
     */
    @ColumnMeta(columnName="task_class", dataType="String", dataSize=200, nullable=true)
    @Schema(title = "执行类信息", description = "执行类信息", maxLength=200, nullable=true )
    private String taskClass;

    /**
     * 任务参数
     */
    @ColumnMeta(columnName="task_param", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "任务参数", description = "任务参数", maxLength=100, nullable=true )
    private String taskParam;

    /**
     * 任务所有人
     */
    @ColumnMeta(columnName="task_owner", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "任务所有人", description = "任务所有人", maxLength=500, nullable=true )
    private String taskOwner;

    /**
     * cron表达式
     */
    @ColumnMeta(columnName="task_cron", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "cron表达式", description = "cron表达式", maxLength=100, nullable=true )
    private String taskCron;

    /**
     * 运行类型
     */
    @ColumnMeta(columnName="run_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行类型", description = "运行类型", maxLength=10, nullable=true )
    private int runType;

    /**
     * 运行目标
     */
    @ColumnMeta(columnName="run_target", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行目标", description = "运行目标", maxLength=100, nullable=true )
    private String runTarget;

    /**
     * 日志类型
     */
    @ColumnMeta(columnName="log_level", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "日志类型", description = "日志类型", maxLength=10, nullable=true )
    private int logLevel;

    /**
     * 日志长度限制
     */
    @ColumnMeta(columnName="log_limit_size", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "日志长度限制", description = "日志长度限制", maxLength=10, nullable=true )
    private int logLimitSize;

    /**
     * 下次执行时间
     */
    @ColumnMeta(columnName="next_run_date", dataType="java.util.Date", dataSize=19, nullable=true)
    @Schema(title = "下次执行时间", description = "下次执行时间", maxLength=19, nullable=true )
    private java.util.Date nextRunDate;

    /**
     * 最后统计时间
     */
    @ColumnMeta(columnName="stats_date", dataType="java.util.Date", dataSize=19, nullable=true)
    @Schema(title = "最后统计时间", description = "最后统计时间", maxLength=19, nullable=true )
    private java.util.Date statsDate;

    /**
     * 统计运行次数
     */
    @ColumnMeta(columnName="stats_run_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行次数", description = "统计运行次数", maxLength=10, nullable=true )
    private int statsRunNum;

    /**
     * 统计运行失败次数
     */
    @ColumnMeta(columnName="stats_fail_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数", maxLength=10, nullable=true )
    private int statsFailNum;

    /**
     * 统计总时间毫秒数
     */
    @ColumnMeta(columnName="stats_run_time", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数", maxLength=19, nullable=true )
    private long statsRunTime;

    /**
     * 失败率
     */
    @ColumnMeta(columnName="alert_fail_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "失败率", description = "失败率", maxLength=10, nullable=true )
    private int alertFailRate;

    /**
     * 接口失败率
     */
    @ColumnMeta(columnName="alert_fail_partner_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "接口失败率", description = "接口失败率", maxLength=10, nullable=true )
    private int alertFailPartnerRate;

    /**
     * 数据失败率
     */
    @ColumnMeta(columnName="alert_fail_data_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "数据失败率", description = "数据失败率", maxLength=10, nullable=true )
    private int alertFailDataRate;

    /**
     * 程序失败率
     */
    @ColumnMeta(columnName="alert_fail_program_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序失败率", description = "程序失败率", maxLength=10, nullable=true )
    private int alertFailProgramRate;

    /**
     * 等待超时
     */
    @ColumnMeta(columnName="alert_wait_timeout", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "等待超时", description = "等待超时", maxLength=10, nullable=true )
    private int alertWaitTimeout;

    /**
     * 运行超时
     */
    @ColumnMeta(columnName="alert_run_timeout", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行超时", description = "运行超时", maxLength=10, nullable=true )
    private int alertRunTimeout;

    /**
     * 我方联系信息
     */
    @ColumnMeta(columnName="task_link_our", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "我方联系信息", description = "我方联系信息", maxLength=500, nullable=true )
    private String taskLinkOur;

    /**
     * 商户联系信息
     */
    @ColumnMeta(columnName="task_link_mch", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "商户联系信息", description = "商户联系信息", maxLength=500, nullable=true )
    private String taskLinkMch;

    /**
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间", maxLength=23, nullable=true )
    private java.util.Date createDate;

    /**
     * 最后修改时间
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "最后修改时间", description = "最后修改时间", maxLength=23, nullable=true )
    private java.util.Date modifyDate;

    /**
     * 状态1正常，0暂停，-1标记删除
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除", maxLength=10, nullable=true )
    private int state;

    /**
     * 数据更新信息.
     */
    private transient DataUpdateInfo _UPDATED_INFO = null;

    /**
     * 是否加载完成.
     */
    private transient boolean _IS_LOADED;

    /**
     * 获得实体的表名。
     */
    @Override
    public String ENTITY_TABLE(){
        return "task_croner_info";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "定时任务配置";
    }

    /**
     * 获得主键
     */
    @Override
    public Serializable ENTITY_ID(){
        return getId();
    }

    /**
     * 获取更新信息.
     */
    @Override
    public DataUpdateInfo GET_UPDATED_INFO() {
        return this._UPDATED_INFO;
    }

    /**
     * 清除更新信息.
     */
    @Override
    public void CLEAR_UPDATED_INFO() {
        _UPDATED_INFO = null;
    }


    /**
     * 获取id。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获取任务名称。
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * 获取任务描述。
     */
    public String getTaskDesc(){
        return this.taskDesc;
    }

    /**
     * 获取执行类信息。
     */
    public String getTaskClass(){
        return this.taskClass;
    }

    /**
     * 获取任务参数。
     */
    public String getTaskParam(){
        return this.taskParam;
    }

    /**
     * 获取任务所有人。
     */
    public String getTaskOwner(){
        return this.taskOwner;
    }

    /**
     * 获取cron表达式。
     */
    public String getTaskCron(){
        return this.taskCron;
    }

    /**
     * 获取运行类型。
     */
    public int getRunType(){
        return this.runType;
    }

    /**
     * 获取运行目标。
     */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
     * 获取日志类型。
     */
    public int getLogLevel(){
        return this.logLevel;
    }

    /**
     * 获取日志长度限制。
     */
    public int getLogLimitSize(){
        return this.logLimitSize;
    }

    /**
     * 获取下次执行时间。
     */
    public java.util.Date getNextRunDate(){
        return this.nextRunDate;
    }

    /**
     * 获取最后统计时间。
     */
    public java.util.Date getStatsDate(){
        return this.statsDate;
    }

    /**
     * 获取统计运行次数。
     */
    public int getStatsRunNum(){
        return this.statsRunNum;
    }

    /**
     * 获取统计运行失败次数。
     */
    public int getStatsFailNum(){
        return this.statsFailNum;
    }

    /**
     * 获取统计总时间毫秒数。
     */
    public long getStatsRunTime(){
        return this.statsRunTime;
    }

    /**
     * 获取失败率。
     */
    public int getAlertFailRate(){
        return this.alertFailRate;
    }

    /**
     * 获取接口失败率。
     */
    public int getAlertFailPartnerRate(){
        return this.alertFailPartnerRate;
    }

    /**
     * 获取数据失败率。
     */
    public int getAlertFailDataRate(){
        return this.alertFailDataRate;
    }

    /**
     * 获取程序失败率。
     */
    public int getAlertFailProgramRate(){
        return this.alertFailProgramRate;
    }

    /**
     * 获取等待超时。
     */
    public int getAlertWaitTimeout(){
        return this.alertWaitTimeout;
    }

    /**
     * 获取运行超时。
     */
    public int getAlertRunTimeout(){
        return this.alertRunTimeout;
    }

    /**
     * 获取我方联系信息。
     */
    public String getTaskLinkOur(){
        return this.taskLinkOur;
    }

    /**
     * 获取商户联系信息。
     */
    public String getTaskLinkMch(){
        return this.taskLinkMch;
    }

    /**
     * 获取创建时间。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获取最后修改时间。
     */
    public java.util.Date getModifyDate(){
        return this.modifyDate;
    }

    /**
     * 获取状态1正常，0暂停，-1标记删除。
     */
    public int getState(){
        return this.state;
    }


    /**
     * 设置id。
     */
    public void setId(long id){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "id", this.id, id, !_IS_LOADED );
        this.id = id;
    }

    /**
     *  设置id链式调用。
     */
    public TaskCronerInfo id(long id){
        setId(id);
        return this;
    }

    /**
     * 设置任务名称。
     */
    public void setTaskName(String taskName){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskName", this.taskName, taskName, !_IS_LOADED );
        this.taskName = taskName;
    }

    /**
     *  设置任务名称链式调用。
     */
    public TaskCronerInfo taskName(String taskName){
        setTaskName(taskName);
        return this;
    }

    /**
     * 设置任务描述。
     */
    public void setTaskDesc(String taskDesc){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskDesc", this.taskDesc, taskDesc, !_IS_LOADED );
        this.taskDesc = taskDesc;
    }

    /**
     *  设置任务描述链式调用。
     */
    public TaskCronerInfo taskDesc(String taskDesc){
        setTaskDesc(taskDesc);
        return this;
    }

    /**
     * 设置执行类信息。
     */
    public void setTaskClass(String taskClass){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskClass", this.taskClass, taskClass, !_IS_LOADED );
        this.taskClass = taskClass;
    }

    /**
     *  设置执行类信息链式调用。
     */
    public TaskCronerInfo taskClass(String taskClass){
        setTaskClass(taskClass);
        return this;
    }

    /**
     * 设置任务参数。
     */
    public void setTaskParam(String taskParam){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskParam", this.taskParam, taskParam, !_IS_LOADED );
        this.taskParam = taskParam;
    }

    /**
     *  设置任务参数链式调用。
     */
    public TaskCronerInfo taskParam(String taskParam){
        setTaskParam(taskParam);
        return this;
    }

    /**
     * 设置任务所有人。
     */
    public void setTaskOwner(String taskOwner){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskOwner", this.taskOwner, taskOwner, !_IS_LOADED );
        this.taskOwner = taskOwner;
    }

    /**
     *  设置任务所有人链式调用。
     */
    public TaskCronerInfo taskOwner(String taskOwner){
        setTaskOwner(taskOwner);
        return this;
    }

    /**
     * 设置cron表达式。
     */
    public void setTaskCron(String taskCron){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskCron", this.taskCron, taskCron, !_IS_LOADED );
        this.taskCron = taskCron;
    }

    /**
     *  设置cron表达式链式调用。
     */
    public TaskCronerInfo taskCron(String taskCron){
        setTaskCron(taskCron);
        return this;
    }

    /**
     * 设置运行类型。
     */
    public void setRunType(int runType){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "runType", this.runType, runType, !_IS_LOADED );
        this.runType = runType;
    }

    /**
     *  设置运行类型链式调用。
     */
    public TaskCronerInfo runType(int runType){
        setRunType(runType);
        return this;
    }

    /**
     * 设置运行目标。
     */
    public void setRunTarget(String runTarget){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "runTarget", this.runTarget, runTarget, !_IS_LOADED );
        this.runTarget = runTarget;
    }

    /**
     *  设置运行目标链式调用。
     */
    public TaskCronerInfo runTarget(String runTarget){
        setRunTarget(runTarget);
        return this;
    }

    /**
     * 设置日志类型。
     */
    public void setLogLevel(int logLevel){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "logLevel", this.logLevel, logLevel, !_IS_LOADED );
        this.logLevel = logLevel;
    }

    /**
     *  设置日志类型链式调用。
     */
    public TaskCronerInfo logLevel(int logLevel){
        setLogLevel(logLevel);
        return this;
    }

    /**
     * 设置日志长度限制。
     */
    public void setLogLimitSize(int logLimitSize){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "logLimitSize", this.logLimitSize, logLimitSize, !_IS_LOADED );
        this.logLimitSize = logLimitSize;
    }

    /**
     *  设置日志长度限制链式调用。
     */
    public TaskCronerInfo logLimitSize(int logLimitSize){
        setLogLimitSize(logLimitSize);
        return this;
    }

    /**
     * 设置下次执行时间。
     */
    public void setNextRunDate(java.util.Date nextRunDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "nextRunDate", this.nextRunDate, nextRunDate, !_IS_LOADED );
        this.nextRunDate = nextRunDate;
    }

    /**
     *  设置下次执行时间链式调用。
     */
    public TaskCronerInfo nextRunDate(java.util.Date nextRunDate){
        setNextRunDate(nextRunDate);
        return this;
    }

    /**
     * 设置最后统计时间。
     */
    public void setStatsDate(java.util.Date statsDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "statsDate", this.statsDate, statsDate, !_IS_LOADED );
        this.statsDate = statsDate;
    }

    /**
     *  设置最后统计时间链式调用。
     */
    public TaskCronerInfo statsDate(java.util.Date statsDate){
        setStatsDate(statsDate);
        return this;
    }

    /**
     * 设置统计运行次数。
     */
    public void setStatsRunNum(int statsRunNum){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "statsRunNum", this.statsRunNum, statsRunNum, !_IS_LOADED );
        this.statsRunNum = statsRunNum;
    }

    /**
     *  设置统计运行次数链式调用。
     */
    public TaskCronerInfo statsRunNum(int statsRunNum){
        setStatsRunNum(statsRunNum);
        return this;
    }

    /**
     * 设置统计运行失败次数。
     */
    public void setStatsFailNum(int statsFailNum){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "statsFailNum", this.statsFailNum, statsFailNum, !_IS_LOADED );
        this.statsFailNum = statsFailNum;
    }

    /**
     *  设置统计运行失败次数链式调用。
     */
    public TaskCronerInfo statsFailNum(int statsFailNum){
        setStatsFailNum(statsFailNum);
        return this;
    }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setStatsRunTime(long statsRunTime){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "statsRunTime", this.statsRunTime, statsRunTime, !_IS_LOADED );
        this.statsRunTime = statsRunTime;
    }

    /**
     *  设置统计总时间毫秒数链式调用。
     */
    public TaskCronerInfo statsRunTime(long statsRunTime){
        setStatsRunTime(statsRunTime);
        return this;
    }

    /**
     * 设置失败率。
     */
    public void setAlertFailRate(int alertFailRate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertFailRate", this.alertFailRate, alertFailRate, !_IS_LOADED );
        this.alertFailRate = alertFailRate;
    }

    /**
     *  设置失败率链式调用。
     */
    public TaskCronerInfo alertFailRate(int alertFailRate){
        setAlertFailRate(alertFailRate);
        return this;
    }

    /**
     * 设置接口失败率。
     */
    public void setAlertFailPartnerRate(int alertFailPartnerRate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertFailPartnerRate", this.alertFailPartnerRate, alertFailPartnerRate, !_IS_LOADED );
        this.alertFailPartnerRate = alertFailPartnerRate;
    }

    /**
     *  设置接口失败率链式调用。
     */
    public TaskCronerInfo alertFailPartnerRate(int alertFailPartnerRate){
        setAlertFailPartnerRate(alertFailPartnerRate);
        return this;
    }

    /**
     * 设置数据失败率。
     */
    public void setAlertFailDataRate(int alertFailDataRate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertFailDataRate", this.alertFailDataRate, alertFailDataRate, !_IS_LOADED );
        this.alertFailDataRate = alertFailDataRate;
    }

    /**
     *  设置数据失败率链式调用。
     */
    public TaskCronerInfo alertFailDataRate(int alertFailDataRate){
        setAlertFailDataRate(alertFailDataRate);
        return this;
    }

    /**
     * 设置程序失败率。
     */
    public void setAlertFailProgramRate(int alertFailProgramRate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertFailProgramRate", this.alertFailProgramRate, alertFailProgramRate, !_IS_LOADED );
        this.alertFailProgramRate = alertFailProgramRate;
    }

    /**
     *  设置程序失败率链式调用。
     */
    public TaskCronerInfo alertFailProgramRate(int alertFailProgramRate){
        setAlertFailProgramRate(alertFailProgramRate);
        return this;
    }

    /**
     * 设置等待超时。
     */
    public void setAlertWaitTimeout(int alertWaitTimeout){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertWaitTimeout", this.alertWaitTimeout, alertWaitTimeout, !_IS_LOADED );
        this.alertWaitTimeout = alertWaitTimeout;
    }

    /**
     *  设置等待超时链式调用。
     */
    public TaskCronerInfo alertWaitTimeout(int alertWaitTimeout){
        setAlertWaitTimeout(alertWaitTimeout);
        return this;
    }

    /**
     * 设置运行超时。
     */
    public void setAlertRunTimeout(int alertRunTimeout){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertRunTimeout", this.alertRunTimeout, alertRunTimeout, !_IS_LOADED );
        this.alertRunTimeout = alertRunTimeout;
    }

    /**
     *  设置运行超时链式调用。
     */
    public TaskCronerInfo alertRunTimeout(int alertRunTimeout){
        setAlertRunTimeout(alertRunTimeout);
        return this;
    }

    /**
     * 设置我方联系信息。
     */
    public void setTaskLinkOur(String taskLinkOur){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskLinkOur", this.taskLinkOur, taskLinkOur, !_IS_LOADED );
        this.taskLinkOur = taskLinkOur;
    }

    /**
     *  设置我方联系信息链式调用。
     */
    public TaskCronerInfo taskLinkOur(String taskLinkOur){
        setTaskLinkOur(taskLinkOur);
        return this;
    }

    /**
     * 设置商户联系信息。
     */
    public void setTaskLinkMch(String taskLinkMch){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskLinkMch", this.taskLinkMch, taskLinkMch, !_IS_LOADED );
        this.taskLinkMch = taskLinkMch;
    }

    /**
     *  设置商户联系信息链式调用。
     */
    public TaskCronerInfo taskLinkMch(String taskLinkMch){
        setTaskLinkMch(taskLinkMch);
        return this;
    }

    /**
     * 设置创建时间。
     */
    public void setCreateDate(java.util.Date createDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "createDate", this.createDate, createDate, !_IS_LOADED );
        this.createDate = createDate;
    }

    /**
     *  设置创建时间链式调用。
     */
    public TaskCronerInfo createDate(java.util.Date createDate){
        setCreateDate(createDate);
        return this;
    }

    /**
     * 设置最后修改时间。
     */
    public void setModifyDate(java.util.Date modifyDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "modifyDate", this.modifyDate, modifyDate, !_IS_LOADED );
        this.modifyDate = modifyDate;
    }

    /**
     *  设置最后修改时间链式调用。
     */
    public TaskCronerInfo modifyDate(java.util.Date modifyDate){
        setModifyDate(modifyDate);
        return this;
    }

    /**
     * 设置状态1正常，0暂停，-1标记删除。
     */
    public void setState(int state){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "state", this.state, state, !_IS_LOADED );
        this.state = state;
    }

    /**
     *  设置状态1正常，0暂停，-1标记删除链式调用。
     */
    public TaskCronerInfo state(int state){
        setState(state);
        return this;
    }

    /**
     * 重载toString方法.
     */
    @Override
    public String toString() {
        return JsonUtils.toString(this);
    }

}