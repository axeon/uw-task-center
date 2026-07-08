package uw.task.center.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.util.JsonUtils;
import uw.dao.DataEntity;
import uw.dao.DataUpdateInfo;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

import java.io.Serializable;


/**
 * TaskDelayerInfo实体类
 * 延迟任务配置
 *
 * @author axeon
 */
@TableMeta(tableName="task_delayer_info",tableType="table")
@Schema(title = "延迟任务配置", description = "延迟任务配置")
public class TaskDelayerInfo implements DataEntity,Serializable{


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
     * 任务所有人
     */
    @ColumnMeta(columnName="task_owner", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "任务所有人", description = "任务所有人", maxLength=500, nullable=true )
    private String taskOwner;

    /**
     * 运行标签
     */
    @ColumnMeta(columnName="task_tag", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行标签", description = "运行标签", maxLength=100, nullable=true )
    private String taskTag;

    /**
     * 运行目标
     */
    @ColumnMeta(columnName="run_target", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行目标", description = "运行目标", maxLength=100, nullable=true )
    private String runTarget;

    /**
     * 执行线程数
     */
    @ColumnMeta(columnName="consumer_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "执行线程数", description = "执行线程数", maxLength=10, nullable=true )
    private int consumerNum;

    /**
     * poll间隔秒数
     */
    @ColumnMeta(columnName="poll_interval", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "poll间隔数", description = "poll间隔数", maxLength=19, nullable=true )
    private long pollInterval;

    /**
     * 单次poll最大条数
     */
    @ColumnMeta(columnName="prefetch_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "单次poll最大条数", description = "单次poll最大条数", maxLength=10, nullable=true )
    private int prefetchNum;

    /**
     * 限速类型
     */
    @ColumnMeta(columnName="rate_limit_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "限速类型", description = "限速类型", maxLength=10, nullable=true )
    private int rateLimitType;

    /**
     * 限速窗口配额上限
     */
    @ColumnMeta(columnName="rate_limit_value", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "限速窗口配额上限", description = "限速窗口配额上限", maxLength=10, nullable=true )
    private int rateLimitValue;

    /**
     * 限速窗口长度(秒)
     */
    @ColumnMeta(columnName="rate_limit_time", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "限速窗口长度(秒)", description = "限速窗口长度(秒)", maxLength=10, nullable=true )
    private int rateLimitTime;

    /**
     * 限速不足时等待秒数
     */
    @ColumnMeta(columnName="rate_limit_wait", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "限速不足时等待秒数", description = "限速不足时等待秒数", maxLength=10, nullable=true )
    private int rateLimitWait;

    /**
     * 连续限速超限放弃次数上限(防死循环)
     */
    @ColumnMeta(columnName="retry_times_by_overrated", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "连续限速超限放弃次数上限(防死循环)", description = "连续限速超限放弃次数上限(防死循环)", maxLength=10, nullable=true )
    private int retryTimesByOverrated;

    /**
     * 合作方异常重试次数
     */
    @ColumnMeta(columnName="retry_times_by_partner", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "合作方异常重试次数", description = "合作方异常重试次数", maxLength=10, nullable=true )
    private int retryTimesByPartner;

    /**
     * 程序异常重试次数
     */
    @ColumnMeta(columnName="retry_times_by_program", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序异常重试次数", description = "程序异常重试次数", maxLength=10, nullable=true )
    private int retryTimesByProgram;

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
     * 程序失败率
     */
    @ColumnMeta(columnName="alert_fail_program_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序失败率", description = "程序失败率", maxLength=10, nullable=true )
    private int alertFailProgramRate;

    /**
     * 运行超时(毫秒)
     */
    @ColumnMeta(columnName="alert_run_timeout", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行超时(毫秒)", description = "运行超时(毫秒)", maxLength=10, nullable=true )
    private int alertRunTimeout;

    /**
     * 延迟超时(实际执行晚于runAt的平均毫秒)
     */
    @ColumnMeta(columnName="alert_delay_overtime", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "延迟超时(实际执行晚于runAt的平均毫秒)", description = "延迟超时(实际执行晚于runAt的平均毫秒)", maxLength=10, nullable=true )
    private int alertDelayOvertime;

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
     * 创建日期
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建日期", description = "创建日期", maxLength=23, nullable=true )
    private java.util.Date createDate;

    /**
     * 最后修改日期
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "最后修改日期", description = "最后修改日期", maxLength=23, nullable=true )
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
        return "task_delayer_info";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "延迟任务配置";
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
     * 获取任务所有人。
     */
    public String getTaskOwner(){
        return this.taskOwner;
    }

    /**
     * 获取运行标签。
     */
    public String getTaskTag(){
        return this.taskTag;
    }

    /**
     * 获取运行目标。
     */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
     * 获取执行线程数。
     */
    public int getConsumerNum(){
        return this.consumerNum;
    }

    /**
     * 获取poll间隔秒数。
     */
    public long getPollInterval(){
        return this.pollInterval;
    }

    /**
     * 获取单次poll最大条数。
     */
    public int getPrefetchNum(){
        return this.prefetchNum;
    }

    /**
     * 获取限速类型。
     */
    public int getRateLimitType(){
        return this.rateLimitType;
    }

    /**
     * 获取限速窗口配额上限。
     */
    public int getRateLimitValue(){
        return this.rateLimitValue;
    }

    /**
     * 获取限速窗口长度(秒)。
     */
    public int getRateLimitTime(){
        return this.rateLimitTime;
    }

    /**
     * 获取限速不足时等待秒数。
     */
    public int getRateLimitWait(){
        return this.rateLimitWait;
    }

    /**
     * 获取连续限速超限放弃次数上限(防死循环)。
     */
    public int getRetryTimesByOverrated(){
        return this.retryTimesByOverrated;
    }

    /**
     * 获取合作方异常重试次数。
     */
    public int getRetryTimesByPartner(){
        return this.retryTimesByPartner;
    }

    /**
     * 获取程序异常重试次数。
     */
    public int getRetryTimesByProgram(){
        return this.retryTimesByProgram;
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
     * 获取程序失败率。
     */
    public int getAlertFailProgramRate(){
        return this.alertFailProgramRate;
    }

    /**
     * 获取运行超时(毫秒)。
     */
    public int getAlertRunTimeout(){
        return this.alertRunTimeout;
    }

    /**
     * 获取延迟超时(实际执行晚于runAt的平均毫秒)。
     */
    public int getAlertDelayOvertime(){
        return this.alertDelayOvertime;
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
     * 获取创建日期。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获取最后修改日期。
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
    public TaskDelayerInfo id(long id){
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
    public TaskDelayerInfo taskName(String taskName){
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
    public TaskDelayerInfo taskDesc(String taskDesc){
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
    public TaskDelayerInfo taskClass(String taskClass){
        setTaskClass(taskClass);
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
    public TaskDelayerInfo taskOwner(String taskOwner){
        setTaskOwner(taskOwner);
        return this;
    }

    /**
     * 设置运行标签。
     */
    public void setTaskTag(String taskTag){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskTag", this.taskTag, taskTag, !_IS_LOADED );
        this.taskTag = taskTag;
    }

    /**
     *  设置运行标签链式调用。
     */
    public TaskDelayerInfo taskTag(String taskTag){
        setTaskTag(taskTag);
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
    public TaskDelayerInfo runTarget(String runTarget){
        setRunTarget(runTarget);
        return this;
    }

    /**
     * 设置执行线程数。
     */
    public void setConsumerNum(int consumerNum){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "consumerNum", this.consumerNum, consumerNum, !_IS_LOADED );
        this.consumerNum = consumerNum;
    }

    /**
     *  设置执行线程数链式调用。
     */
    public TaskDelayerInfo consumerNum(int consumerNum){
        setConsumerNum(consumerNum);
        return this;
    }

    /**
     * 设置poll间隔秒数。
     */
    public void setPollInterval(long pollInterval){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "pollInterval", this.pollInterval, pollInterval, !_IS_LOADED );
        this.pollInterval = pollInterval;
    }

    /**
     *  设置poll间隔秒数链式调用。
     */
    public TaskDelayerInfo pollInterval(long pollInterval){
        setPollInterval(pollInterval);
        return this;
    }

    /**
     * 设置单次poll最大条数。
     */
    public void setPrefetchNum(int prefetchNum){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "prefetchNum", this.prefetchNum, prefetchNum, !_IS_LOADED );
        this.prefetchNum = prefetchNum;
    }

    /**
     *  设置单次poll最大条数链式调用。
     */
    public TaskDelayerInfo prefetchNum(int prefetchNum){
        setPrefetchNum(prefetchNum);
        return this;
    }

    /**
     * 设置限速类型。
     */
    public void setRateLimitType(int rateLimitType){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "rateLimitType", this.rateLimitType, rateLimitType, !_IS_LOADED );
        this.rateLimitType = rateLimitType;
    }

    /**
     *  设置限速类型链式调用。
     */
    public TaskDelayerInfo rateLimitType(int rateLimitType){
        setRateLimitType(rateLimitType);
        return this;
    }

    /**
     * 设置限速窗口配额上限。
     */
    public void setRateLimitValue(int rateLimitValue){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "rateLimitValue", this.rateLimitValue, rateLimitValue, !_IS_LOADED );
        this.rateLimitValue = rateLimitValue;
    }

    /**
     *  设置限速窗口配额上限链式调用。
     */
    public TaskDelayerInfo rateLimitValue(int rateLimitValue){
        setRateLimitValue(rateLimitValue);
        return this;
    }

    /**
     * 设置限速窗口长度(秒)。
     */
    public void setRateLimitTime(int rateLimitTime){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "rateLimitTime", this.rateLimitTime, rateLimitTime, !_IS_LOADED );
        this.rateLimitTime = rateLimitTime;
    }

    /**
     *  设置限速窗口长度(秒)链式调用。
     */
    public TaskDelayerInfo rateLimitTime(int rateLimitTime){
        setRateLimitTime(rateLimitTime);
        return this;
    }

    /**
     * 设置限速不足时等待秒数。
     */
    public void setRateLimitWait(int rateLimitWait){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "rateLimitWait", this.rateLimitWait, rateLimitWait, !_IS_LOADED );
        this.rateLimitWait = rateLimitWait;
    }

    /**
     *  设置限速不足时等待秒数链式调用。
     */
    public TaskDelayerInfo rateLimitWait(int rateLimitWait){
        setRateLimitWait(rateLimitWait);
        return this;
    }

    /**
     * 设置连续限速超限放弃次数上限(防死循环)。
     */
    public void setRetryTimesByOverrated(int retryTimesByOverrated){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "retryTimesByOverrated", this.retryTimesByOverrated, retryTimesByOverrated, !_IS_LOADED );
        this.retryTimesByOverrated = retryTimesByOverrated;
    }

    /**
     *  设置连续限速超限放弃次数上限(防死循环)链式调用。
     */
    public TaskDelayerInfo retryTimesByOverrated(int retryTimesByOverrated){
        setRetryTimesByOverrated(retryTimesByOverrated);
        return this;
    }

    /**
     * 设置合作方异常重试次数。
     */
    public void setRetryTimesByPartner(int retryTimesByPartner){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "retryTimesByPartner", this.retryTimesByPartner, retryTimesByPartner, !_IS_LOADED );
        this.retryTimesByPartner = retryTimesByPartner;
    }

    /**
     *  设置合作方异常重试次数链式调用。
     */
    public TaskDelayerInfo retryTimesByPartner(int retryTimesByPartner){
        setRetryTimesByPartner(retryTimesByPartner);
        return this;
    }

    /**
     * 设置程序异常重试次数。
     */
    public void setRetryTimesByProgram(int retryTimesByProgram){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "retryTimesByProgram", this.retryTimesByProgram, retryTimesByProgram, !_IS_LOADED );
        this.retryTimesByProgram = retryTimesByProgram;
    }

    /**
     *  设置程序异常重试次数链式调用。
     */
    public TaskDelayerInfo retryTimesByProgram(int retryTimesByProgram){
        setRetryTimesByProgram(retryTimesByProgram);
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
    public TaskDelayerInfo logLevel(int logLevel){
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
    public TaskDelayerInfo logLimitSize(int logLimitSize){
        setLogLimitSize(logLimitSize);
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
    public TaskDelayerInfo statsDate(java.util.Date statsDate){
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
    public TaskDelayerInfo statsRunNum(int statsRunNum){
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
    public TaskDelayerInfo statsFailNum(int statsFailNum){
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
    public TaskDelayerInfo statsRunTime(long statsRunTime){
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
    public TaskDelayerInfo alertFailRate(int alertFailRate){
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
    public TaskDelayerInfo alertFailPartnerRate(int alertFailPartnerRate){
        setAlertFailPartnerRate(alertFailPartnerRate);
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
    public TaskDelayerInfo alertFailProgramRate(int alertFailProgramRate){
        setAlertFailProgramRate(alertFailProgramRate);
        return this;
    }

    /**
     * 设置运行超时(毫秒)。
     */
    public void setAlertRunTimeout(int alertRunTimeout){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertRunTimeout", this.alertRunTimeout, alertRunTimeout, !_IS_LOADED );
        this.alertRunTimeout = alertRunTimeout;
    }

    /**
     *  设置运行超时(毫秒)链式调用。
     */
    public TaskDelayerInfo alertRunTimeout(int alertRunTimeout){
        setAlertRunTimeout(alertRunTimeout);
        return this;
    }

    /**
     * 设置延迟超时(实际执行晚于runAt的平均毫秒)。
     */
    public void setAlertDelayOvertime(int alertDelayOvertime){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertDelayOvertime", this.alertDelayOvertime, alertDelayOvertime, !_IS_LOADED );
        this.alertDelayOvertime = alertDelayOvertime;
    }

    /**
     *  设置延迟超时(实际执行晚于runAt的平均毫秒)链式调用。
     */
    public TaskDelayerInfo alertDelayOvertime(int alertDelayOvertime){
        setAlertDelayOvertime(alertDelayOvertime);
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
    public TaskDelayerInfo taskLinkOur(String taskLinkOur){
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
    public TaskDelayerInfo taskLinkMch(String taskLinkMch){
        setTaskLinkMch(taskLinkMch);
        return this;
    }

    /**
     * 设置创建日期。
     */
    public void setCreateDate(java.util.Date createDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "createDate", this.createDate, createDate, !_IS_LOADED );
        this.createDate = createDate;
    }

    /**
     *  设置创建日期链式调用。
     */
    public TaskDelayerInfo createDate(java.util.Date createDate){
        setCreateDate(createDate);
        return this;
    }

    /**
     * 设置最后修改日期。
     */
    public void setModifyDate(java.util.Date modifyDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "modifyDate", this.modifyDate, modifyDate, !_IS_LOADED );
        this.modifyDate = modifyDate;
    }

    /**
     *  设置最后修改日期链式调用。
     */
    public TaskDelayerInfo modifyDate(java.util.Date modifyDate){
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
    public TaskDelayerInfo state(int state){
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