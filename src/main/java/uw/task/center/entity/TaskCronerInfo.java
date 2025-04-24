package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

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
     * 轻量级状态下更新列表list.
     */
    private transient Set<String> _UPDATED_COLUMN = null;

    /**
     * 更新的信息.
     */
    private transient StringBuilder _UPDATED_INFO = null;


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
     * 获取更改的字段列表.
     */
    @Override
    public Set<String> GET_UPDATED_COLUMN() {
        return _UPDATED_COLUMN;
    }

    /**
     * 获取文本更新信息.
     */
    @Override
    public String GET_UPDATED_INFO() {
        if (this._UPDATED_INFO == null) {
            return null;
        } else {
            return this._UPDATED_INFO.toString();
        }
    }

    /**
     * 清除更新信息.
     */
    @Override
    public void CLEAR_UPDATED_INFO() {
        _UPDATED_COLUMN = null;
        _UPDATED_INFO = null;
    }

    /**
     * 初始化set相关的信息.
     */
    private void _INIT_UPDATE_INFO() {
        this._UPDATED_COLUMN = new HashSet<String>();
        this._UPDATED_INFO = new StringBuilder("表task_croner_info主键\"" + 
        this.id+ "\"更新为:\r\n");
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
        if (!_IS_LOADED||!Objects.equals(this.id, id)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("id");
            this._UPDATED_INFO.append("id:\"").append(this.id).append("\"=>\"").append(id).append("\"\n");
            this.id = id;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskName, taskName)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_name");
            this._UPDATED_INFO.append("task_name:\"").append(this.taskName).append("\"=>\"").append(taskName).append("\"\n");
            this.taskName = taskName;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskDesc, taskDesc)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_desc");
            this._UPDATED_INFO.append("task_desc:\"").append(this.taskDesc).append("\"=>\"").append(taskDesc).append("\"\n");
            this.taskDesc = taskDesc;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskClass, taskClass)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_class");
            this._UPDATED_INFO.append("task_class:\"").append(this.taskClass).append("\"=>\"").append(taskClass).append("\"\n");
            this.taskClass = taskClass;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskParam, taskParam)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_param");
            this._UPDATED_INFO.append("task_param:\"").append(this.taskParam).append("\"=>\"").append(taskParam).append("\"\n");
            this.taskParam = taskParam;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskOwner, taskOwner)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_owner");
            this._UPDATED_INFO.append("task_owner:\"").append(this.taskOwner).append("\"=>\"").append(taskOwner).append("\"\n");
            this.taskOwner = taskOwner;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskCron, taskCron)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_cron");
            this._UPDATED_INFO.append("task_cron:\"").append(this.taskCron).append("\"=>\"").append(taskCron).append("\"\n");
            this.taskCron = taskCron;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.runType, runType)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("run_type");
            this._UPDATED_INFO.append("run_type:\"").append(this.runType).append("\"=>\"").append(runType).append("\"\n");
            this.runType = runType;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.runTarget, runTarget)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("run_target");
            this._UPDATED_INFO.append("run_target:\"").append(this.runTarget).append("\"=>\"").append(runTarget).append("\"\n");
            this.runTarget = runTarget;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.logLevel, logLevel)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("log_level");
            this._UPDATED_INFO.append("log_level:\"").append(this.logLevel).append("\"=>\"").append(logLevel).append("\"\n");
            this.logLevel = logLevel;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.logLimitSize, logLimitSize)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("log_limit_size");
            this._UPDATED_INFO.append("log_limit_size:\"").append(this.logLimitSize).append("\"=>\"").append(logLimitSize).append("\"\n");
            this.logLimitSize = logLimitSize;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.nextRunDate, nextRunDate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("next_run_date");
            this._UPDATED_INFO.append("next_run_date:\"").append(this.nextRunDate).append("\"=>\"").append(nextRunDate).append("\"\n");
            this.nextRunDate = nextRunDate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.statsDate, statsDate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("stats_date");
            this._UPDATED_INFO.append("stats_date:\"").append(this.statsDate).append("\"=>\"").append(statsDate).append("\"\n");
            this.statsDate = statsDate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.statsRunNum, statsRunNum)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("stats_run_num");
            this._UPDATED_INFO.append("stats_run_num:\"").append(this.statsRunNum).append("\"=>\"").append(statsRunNum).append("\"\n");
            this.statsRunNum = statsRunNum;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.statsFailNum, statsFailNum)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("stats_fail_num");
            this._UPDATED_INFO.append("stats_fail_num:\"").append(this.statsFailNum).append("\"=>\"").append(statsFailNum).append("\"\n");
            this.statsFailNum = statsFailNum;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.statsRunTime, statsRunTime)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("stats_run_time");
            this._UPDATED_INFO.append("stats_run_time:\"").append(this.statsRunTime).append("\"=>\"").append(statsRunTime).append("\"\n");
            this.statsRunTime = statsRunTime;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.alertFailRate, alertFailRate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("alert_fail_rate");
            this._UPDATED_INFO.append("alert_fail_rate:\"").append(this.alertFailRate).append("\"=>\"").append(alertFailRate).append("\"\n");
            this.alertFailRate = alertFailRate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.alertFailPartnerRate, alertFailPartnerRate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("alert_fail_partner_rate");
            this._UPDATED_INFO.append("alert_fail_partner_rate:\"").append(this.alertFailPartnerRate).append("\"=>\"").append(alertFailPartnerRate).append("\"\n");
            this.alertFailPartnerRate = alertFailPartnerRate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.alertFailDataRate, alertFailDataRate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("alert_fail_data_rate");
            this._UPDATED_INFO.append("alert_fail_data_rate:\"").append(this.alertFailDataRate).append("\"=>\"").append(alertFailDataRate).append("\"\n");
            this.alertFailDataRate = alertFailDataRate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.alertFailProgramRate, alertFailProgramRate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("alert_fail_program_rate");
            this._UPDATED_INFO.append("alert_fail_program_rate:\"").append(this.alertFailProgramRate).append("\"=>\"").append(alertFailProgramRate).append("\"\n");
            this.alertFailProgramRate = alertFailProgramRate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.alertWaitTimeout, alertWaitTimeout)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("alert_wait_timeout");
            this._UPDATED_INFO.append("alert_wait_timeout:\"").append(this.alertWaitTimeout).append("\"=>\"").append(alertWaitTimeout).append("\"\n");
            this.alertWaitTimeout = alertWaitTimeout;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.alertRunTimeout, alertRunTimeout)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("alert_run_timeout");
            this._UPDATED_INFO.append("alert_run_timeout:\"").append(this.alertRunTimeout).append("\"=>\"").append(alertRunTimeout).append("\"\n");
            this.alertRunTimeout = alertRunTimeout;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskLinkOur, taskLinkOur)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_link_our");
            this._UPDATED_INFO.append("task_link_our:\"").append(this.taskLinkOur).append("\"=>\"").append(taskLinkOur).append("\"\n");
            this.taskLinkOur = taskLinkOur;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.taskLinkMch, taskLinkMch)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("task_link_mch");
            this._UPDATED_INFO.append("task_link_mch:\"").append(this.taskLinkMch).append("\"=>\"").append(taskLinkMch).append("\"\n");
            this.taskLinkMch = taskLinkMch;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.createDate, createDate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("create_date");
            this._UPDATED_INFO.append("create_date:\"").append(this.createDate).append("\"=>\"").append(createDate).append("\"\n");
            this.createDate = createDate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.modifyDate, modifyDate)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("modify_date");
            this._UPDATED_INFO.append("modify_date:\"").append(this.modifyDate).append("\"=>\"").append(modifyDate).append("\"\n");
            this.modifyDate = modifyDate;
        }
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
        if (!_IS_LOADED||!Objects.equals(this.state, state)){
            if (this._UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this._UPDATED_COLUMN.add("state");
            this._UPDATED_INFO.append("state:\"").append(this.state).append("\"=>\"").append(state).append("\"\n");
            this.state = state;
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append("id:\"" + this.id + "\"\r\n");
        sb.append("task_name:\"" + this.taskName + "\"\r\n");
        sb.append("task_desc:\"" + this.taskDesc + "\"\r\n");
        sb.append("task_class:\"" + this.taskClass + "\"\r\n");
        sb.append("task_param:\"" + this.taskParam + "\"\r\n");
        sb.append("task_owner:\"" + this.taskOwner + "\"\r\n");
        sb.append("task_cron:\"" + this.taskCron + "\"\r\n");
        sb.append("run_type:\"" + this.runType + "\"\r\n");
        sb.append("run_target:\"" + this.runTarget + "\"\r\n");
        sb.append("log_level:\"" + this.logLevel + "\"\r\n");
        sb.append("log_limit_size:\"" + this.logLimitSize + "\"\r\n");
        sb.append("next_run_date:\"" + this.nextRunDate + "\"\r\n");
        sb.append("stats_date:\"" + this.statsDate + "\"\r\n");
        sb.append("stats_run_num:\"" + this.statsRunNum + "\"\r\n");
        sb.append("stats_fail_num:\"" + this.statsFailNum + "\"\r\n");
        sb.append("stats_run_time:\"" + this.statsRunTime + "\"\r\n");
        sb.append("alert_fail_rate:\"" + this.alertFailRate + "\"\r\n");
        sb.append("alert_fail_partner_rate:\"" + this.alertFailPartnerRate + "\"\r\n");
        sb.append("alert_fail_data_rate:\"" + this.alertFailDataRate + "\"\r\n");
        sb.append("alert_fail_program_rate:\"" + this.alertFailProgramRate + "\"\r\n");
        sb.append("alert_wait_timeout:\"" + this.alertWaitTimeout + "\"\r\n");
        sb.append("alert_run_timeout:\"" + this.alertRunTimeout + "\"\r\n");
        sb.append("task_link_our:\"" + this.taskLinkOur + "\"\r\n");
        sb.append("task_link_mch:\"" + this.taskLinkMch + "\"\r\n");
        sb.append("create_date:\"" + this.createDate + "\"\r\n");
        sb.append("modify_date:\"" + this.modifyDate + "\"\r\n");
        sb.append("state:\"" + this.state + "\"\r\n");
        return sb.toString();
    }

}