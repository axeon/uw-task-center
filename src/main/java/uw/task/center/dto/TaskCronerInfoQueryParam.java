package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 定时任务配置列表查询参数。
*/
@Schema(title = "定时任务配置列表查询参数", description = "定时任务配置列表查询参数")
public class TaskCronerInfoQueryParam extends PageQueryParam{

    /**
     * 允许的排序属性。
     * key:排序名 value:排序字段
     *
     * @return
     */
    @Override
    public Map<String, String> ALLOWED_SORT_PROPERTY() {
        return new HashMap<>() {{
            put( "id", "id" );
            put( "taskName", "task_name" );
            put( "taskClass", "task_class" );
            put( "taskParam", "task_param" );
            put( "taskOwner", "task_owner" );
            put( "taskCron", "task_cron" );
            put( "runType", "run_type" );
            put( "runTarget", "run_target" );
            put( "logLevel", "log_level" );
            put( "logLimitSize", "log_limit_size" );
            put( "nextRunDate", "next_run_date" );
            put( "statsDate", "stats_date" );
            put( "statsRunNum", "stats_run_num" );
            put( "statsFailNum", "stats_fail_num" );
            put( "statsRunTime", "stats_run_time" );
            put( "alertFailRate", "alert_fail_rate" );
            put( "alertFailPartnerRate", "alert_fail_partner_rate" );
            put( "alertFailDataRate", "alert_fail_data_rate" );
            put( "alertFailProgramRate", "alert_fail_program_rate" );
            put( "alertWaitTimeout", "alert_wait_timeout" );
            put( "alertRunTimeout", "alert_run_timeout" );
            put( "taskLinkOur", "task_link_our" );
            put( "taskLinkMch", "task_link_mch" );
            put( "createDate", "create_date" );
            put( "modifyDate", "modify_date" );
            put( "state", "state" );
        }};
    }

    /**
    * id。
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * ID数组。
    */
    @QueryMeta(expr = "id in (?)")
    @Schema(title="ID数组", description = "ID数组，可同时匹配多个。")
    private Long[] ids;

    /**
    * 任务名称。
    */
    @QueryMeta(expr = "task_name like ?")
    @Schema(title="任务名称", description = "任务名称")
    private String taskName;
	
    /**
    * 执行类信息。
    */
    @QueryMeta(expr = "task_class like ?")
    @Schema(title="执行类信息", description = "执行类信息")
    private String taskClass;
	
    /**
    * 任务参数。
    */
    @QueryMeta(expr = "task_param like ?")
    @Schema(title="任务参数", description = "任务参数")
    private String taskParam;
	
    /**
    * 任务所有人。
    */
    @QueryMeta(expr = "task_owner like ?")
    @Schema(title="任务所有人", description = "任务所有人")
    private String taskOwner;
	
    /**
    * cron表达式。
    */
    @QueryMeta(expr = "task_cron like ?")
    @Schema(title="cron表达式", description = "cron表达式")
    private String taskCron;
	
    /**
    * 运行类型。
    */
    @QueryMeta(expr = "run_type=?")
    @Schema(title="运行类型", description = "运行类型")
    private Integer runType;
	
    /**
    * 运行目标。
    */
    @QueryMeta(expr = "run_target like ?")
    @Schema(title="运行目标", description = "运行目标")
    private String runTarget;
	
    /**
    * 日志类型。
    */
    @QueryMeta(expr = "log_level=?")
    @Schema(title="日志类型", description = "日志类型")
    private Integer logLevel;

    /**
    * 日志类型范围。
    */
    @QueryMeta(expr = "log_level between ? and ?")
    @Schema(title="日志类型范围", description = "日志类型范围")
    private Integer[] logLevelRange;
	
    /**
    * 日志长度限制。
    */
    @QueryMeta(expr = "log_limit_size=?")
    @Schema(title="日志长度限制", description = "日志长度限制")
    private Integer logLimitSize;

    /**
    * 日志长度限制范围。
    */
    @QueryMeta(expr = "log_limit_size between ? and ?")
    @Schema(title="日志长度限制范围", description = "日志长度限制范围")
    private Integer[] logLimitSizeRange;
	
    /**
    * 下次执行时间范围。
    */
    @QueryMeta(expr = "next_run_date between ? and ?")
    @Schema(title="下次执行时间范围", description = "下次执行时间范围")
    private Date[] nextRunDateRange;

    /**
    * 最后统计时间范围。
    */
    @QueryMeta(expr = "stats_date between ? and ?")
    @Schema(title="最后统计时间范围", description = "最后统计时间范围")
    private Date[] statsDateRange;

    /**
    * 统计运行次数。
    */
    @QueryMeta(expr = "stats_run_num=?")
    @Schema(title="统计运行次数", description = "统计运行次数")
    private Integer statsRunNum;

    /**
    * 统计运行次数范围。
    */
    @QueryMeta(expr = "stats_run_num between ? and ?")
    @Schema(title="统计运行次数范围", description = "统计运行次数范围")
    private Integer[] statsRunNumRange;
	
    /**
    * 统计运行失败次数。
    */
    @QueryMeta(expr = "stats_fail_num=?")
    @Schema(title="统计运行失败次数", description = "统计运行失败次数")
    private Integer statsFailNum;

    /**
    * 统计运行失败次数范围。
    */
    @QueryMeta(expr = "stats_fail_num between ? and ?")
    @Schema(title="统计运行失败次数范围", description = "统计运行失败次数范围")
    private Integer[] statsFailNumRange;
	
    /**
    * 统计总时间毫秒数。
    */
    @QueryMeta(expr = "stats_run_time=?")
    @Schema(title="统计总时间毫秒数", description = "统计总时间毫秒数")
    private Long statsRunTime;

    /**
    * 统计总时间毫秒数范围。
    */
    @QueryMeta(expr = "stats_run_time between ? and ?")
    @Schema(title="统计总时间毫秒数范围", description = "统计总时间毫秒数范围")
    private Long[] statsRunTimeRange;
	
    /**
    * 失败率。
    */
    @QueryMeta(expr = "alert_fail_rate=?")
    @Schema(title="失败率", description = "失败率")
    private Integer alertFailRate;

    /**
    * 失败率范围。
    */
    @QueryMeta(expr = "alert_fail_rate between ? and ?")
    @Schema(title="失败率范围", description = "失败率范围")
    private Integer[] alertFailRateRange;
	
    /**
    * 接口失败率。
    */
    @QueryMeta(expr = "alert_fail_partner_rate=?")
    @Schema(title="接口失败率", description = "接口失败率")
    private Integer alertFailPartnerRate;

    /**
    * 接口失败率范围。
    */
    @QueryMeta(expr = "alert_fail_partner_rate between ? and ?")
    @Schema(title="接口失败率范围", description = "接口失败率范围")
    private Integer[] alertFailPartnerRateRange;
	
    /**
    * 数据失败率。
    */
    @QueryMeta(expr = "alert_fail_data_rate=?")
    @Schema(title="数据失败率", description = "数据失败率")
    private Integer alertFailDataRate;

    /**
    * 数据失败率范围。
    */
    @QueryMeta(expr = "alert_fail_data_rate between ? and ?")
    @Schema(title="数据失败率范围", description = "数据失败率范围")
    private Integer[] alertFailDataRateRange;
	
    /**
    * 程序失败率。
    */
    @QueryMeta(expr = "alert_fail_program_rate=?")
    @Schema(title="程序失败率", description = "程序失败率")
    private Integer alertFailProgramRate;

    /**
    * 程序失败率范围。
    */
    @QueryMeta(expr = "alert_fail_program_rate between ? and ?")
    @Schema(title="程序失败率范围", description = "程序失败率范围")
    private Integer[] alertFailProgramRateRange;
	
    /**
    * 等待超时。
    */
    @QueryMeta(expr = "alert_wait_timeout=?")
    @Schema(title="等待超时", description = "等待超时")
    private Integer alertWaitTimeout;

    /**
    * 等待超时范围。
    */
    @QueryMeta(expr = "alert_wait_timeout between ? and ?")
    @Schema(title="等待超时范围", description = "等待超时范围")
    private Integer[] alertWaitTimeoutRange;
	
    /**
    * 运行超时。
    */
    @QueryMeta(expr = "alert_run_timeout=?")
    @Schema(title="运行超时", description = "运行超时")
    private Integer alertRunTimeout;

    /**
    * 运行超时范围。
    */
    @QueryMeta(expr = "alert_run_timeout between ? and ?")
    @Schema(title="运行超时范围", description = "运行超时范围")
    private Integer[] alertRunTimeoutRange;
	
    /**
    * 我方联系信息。
    */
    @QueryMeta(expr = "task_link_our like ?")
    @Schema(title="我方联系信息", description = "我方联系信息")
    private String taskLinkOur;
	
    /**
    * 商户联系信息。
    */
    @QueryMeta(expr = "task_link_mch like ?")
    @Schema(title="商户联系信息", description = "商户联系信息")
    private String taskLinkMch;
	
    /**
    * 创建时间范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

    /**
    * 最后修改时间范围。
    */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title="最后修改时间范围", description = "最后修改时间范围")
    private Date[] modifyDateRange;

    /**
    * 状态1正常，0暂停，-1标记删除。
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除")
    private Integer state;

    /**
    * 状态1正常，0暂停，-1标记删除数组。
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="状态1正常，0暂停，-1标记删除数组", description = "状态1正常，0暂停，-1标记删除数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
    * 大于等于状态1正常，0暂停，-1标记删除。
    */
    @QueryMeta(expr = "state>=?")
    @Schema(title="大于等于状态1正常，0暂停，-1标记删除", description = "大于等于状态1正常，0暂停，-1标记删除")
    private Integer stateGte;

    /**
    * 小于等于状态1正常，0暂停，-1标记删除。
    */
    @QueryMeta(expr = "state<=?")
    @Schema(title="小于等于状态1正常，0暂停，-1标记删除", description = "小于等于状态1正常，0暂停，-1标记删除")
    private Integer stateLte;


    /**
    * 获取id。
    */
    public Long getId() {
        return this.id;
    }

    /**
    * 设置id。
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * 设置id链式调用。
    */
    public TaskCronerInfoQueryParam id(Long id) {
        setId(id);
        return this;
    }

    /**
    * 获取ID数组。
    */
    public Long[] getIds() {
        return this.ids;
    }

    /**
    * 设置ID数组。
    */
    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    /**
    * 设置ID数组链式调用。
    */
    public TaskCronerInfoQueryParam ids(Long[] ids) {
        setIds(ids);
        return this;
    }

    /**
    * 获取任务名称。
    */
    public String getTaskName(){
        return this.taskName;
    }

    /**
    * 设置任务名称。
    */
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
	
    /**
    * 设置任务名称链式调用。
    */
    public TaskCronerInfoQueryParam taskName(String taskName) {
        setTaskName(taskName);
        return this;
    }
	
    /**
    * 获取执行类信息。
    */
    public String getTaskClass(){
        return this.taskClass;
    }

    /**
    * 设置执行类信息。
    */
    public void setTaskClass(String taskClass){
        this.taskClass = taskClass;
    }
	
    /**
    * 设置执行类信息链式调用。
    */
    public TaskCronerInfoQueryParam taskClass(String taskClass) {
        setTaskClass(taskClass);
        return this;
    }
	
    /**
    * 获取任务参数。
    */
    public String getTaskParam(){
        return this.taskParam;
    }

    /**
    * 设置任务参数。
    */
    public void setTaskParam(String taskParam){
        this.taskParam = taskParam;
    }
	
    /**
    * 设置任务参数链式调用。
    */
    public TaskCronerInfoQueryParam taskParam(String taskParam) {
        setTaskParam(taskParam);
        return this;
    }
	
    /**
    * 获取任务所有人。
    */
    public String getTaskOwner(){
        return this.taskOwner;
    }

    /**
    * 设置任务所有人。
    */
    public void setTaskOwner(String taskOwner){
        this.taskOwner = taskOwner;
    }
	
    /**
    * 设置任务所有人链式调用。
    */
    public TaskCronerInfoQueryParam taskOwner(String taskOwner) {
        setTaskOwner(taskOwner);
        return this;
    }
	
    /**
    * 获取cron表达式。
    */
    public String getTaskCron(){
        return this.taskCron;
    }

    /**
    * 设置cron表达式。
    */
    public void setTaskCron(String taskCron){
        this.taskCron = taskCron;
    }
	
    /**
    * 设置cron表达式链式调用。
    */
    public TaskCronerInfoQueryParam taskCron(String taskCron) {
        setTaskCron(taskCron);
        return this;
    }
	
    /**
    * 获取运行类型。
    */
    public Integer getRunType(){
        return this.runType;
    }

    /**
    * 设置运行类型。
    */
    public void setRunType(Integer runType){
        this.runType = runType;
    }
	
    /**
    * 设置运行类型链式调用。
    */
	public TaskCronerInfoQueryParam runType(Integer runType){
        setRunType(runType);
        return this;
    }
	
    /**
    * 获取运行目标。
    */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
    * 设置运行目标。
    */
    public void setRunTarget(String runTarget){
        this.runTarget = runTarget;
    }
	
    /**
    * 设置运行目标链式调用。
    */
    public TaskCronerInfoQueryParam runTarget(String runTarget) {
        setRunTarget(runTarget);
        return this;
    }
	
    /**
    * 获取日志类型。
    */
    public Integer getLogLevel(){
        return this.logLevel;
    }

    /**
    * 设置日志类型。
    */
    public void setLogLevel(Integer logLevel){
        this.logLevel = logLevel;
    }
	
    /**
    * 设置日志类型链式调用。
    */
    public TaskCronerInfoQueryParam logLevel(Integer logLevel){
        setLogLevel(logLevel);
        return this;
    }

    /**
    * 获取日志类型范围。
    */
    public Integer[] getLogLevelRange(){
        return this.logLevelRange;
    }

    /**
    * 设置日志类型范围。
    */
    public void setLogLevelRange(Integer[] logLevelRange){
        this.logLevelRange = logLevelRange;
    }
	
    /**
    * 设置日志类型范围链式调用。
    */
    public TaskCronerInfoQueryParam logLevelRange(Integer[] logLevelRange){
        setLogLevelRange(logLevelRange);
        return this;
    }
	
    /**
    * 获取日志长度限制。
    */
    public Integer getLogLimitSize(){
        return this.logLimitSize;
    }

    /**
    * 设置日志长度限制。
    */
    public void setLogLimitSize(Integer logLimitSize){
        this.logLimitSize = logLimitSize;
    }
	
    /**
    * 设置日志长度限制链式调用。
    */
    public TaskCronerInfoQueryParam logLimitSize(Integer logLimitSize){
        setLogLimitSize(logLimitSize);
        return this;
    }

    /**
    * 获取日志长度限制范围。
    */
    public Integer[] getLogLimitSizeRange(){
        return this.logLimitSizeRange;
    }

    /**
    * 设置日志长度限制范围。
    */
    public void setLogLimitSizeRange(Integer[] logLimitSizeRange){
        this.logLimitSizeRange = logLimitSizeRange;
    }
	
    /**
    * 设置日志长度限制范围链式调用。
    */
    public TaskCronerInfoQueryParam logLimitSizeRange(Integer[] logLimitSizeRange){
        setLogLimitSizeRange(logLimitSizeRange);
        return this;
    }
	
    /**
    * 获取下次执行时间范围。
    */
    public Date[] getNextRunDateRange(){
        return this.nextRunDateRange;
    }

    /**
    * 设置下次执行时间范围。
    */
    public void setNextRunDateRange(Date[] nextRunDateRange){
        this.nextRunDateRange = nextRunDateRange;
    }
	
    /**
    * 设置下次执行时间范围链式调用。
    */
    public TaskCronerInfoQueryParam nextRunDateRange(Date[] nextRunDateRange) {
        setNextRunDateRange(nextRunDateRange);
        return this;
    }
	
    /**
    * 获取最后统计时间范围。
    */
    public Date[] getStatsDateRange(){
        return this.statsDateRange;
    }

    /**
    * 设置最后统计时间范围。
    */
    public void setStatsDateRange(Date[] statsDateRange){
        this.statsDateRange = statsDateRange;
    }
	
    /**
    * 设置最后统计时间范围链式调用。
    */
    public TaskCronerInfoQueryParam statsDateRange(Date[] statsDateRange) {
        setStatsDateRange(statsDateRange);
        return this;
    }
	
    /**
    * 获取统计运行次数。
    */
    public Integer getStatsRunNum(){
        return this.statsRunNum;
    }

    /**
    * 设置统计运行次数。
    */
    public void setStatsRunNum(Integer statsRunNum){
        this.statsRunNum = statsRunNum;
    }
	
    /**
    * 设置统计运行次数链式调用。
    */
    public TaskCronerInfoQueryParam statsRunNum(Integer statsRunNum){
        setStatsRunNum(statsRunNum);
        return this;
    }

    /**
    * 获取统计运行次数范围。
    */
    public Integer[] getStatsRunNumRange(){
        return this.statsRunNumRange;
    }

    /**
    * 设置统计运行次数范围。
    */
    public void setStatsRunNumRange(Integer[] statsRunNumRange){
        this.statsRunNumRange = statsRunNumRange;
    }
	
    /**
    * 设置统计运行次数范围链式调用。
    */
    public TaskCronerInfoQueryParam statsRunNumRange(Integer[] statsRunNumRange){
        setStatsRunNumRange(statsRunNumRange);
        return this;
    }
	
    /**
    * 获取统计运行失败次数。
    */
    public Integer getStatsFailNum(){
        return this.statsFailNum;
    }

    /**
    * 设置统计运行失败次数。
    */
    public void setStatsFailNum(Integer statsFailNum){
        this.statsFailNum = statsFailNum;
    }
	
    /**
    * 设置统计运行失败次数链式调用。
    */
    public TaskCronerInfoQueryParam statsFailNum(Integer statsFailNum){
        setStatsFailNum(statsFailNum);
        return this;
    }

    /**
    * 获取统计运行失败次数范围。
    */
    public Integer[] getStatsFailNumRange(){
        return this.statsFailNumRange;
    }

    /**
    * 设置统计运行失败次数范围。
    */
    public void setStatsFailNumRange(Integer[] statsFailNumRange){
        this.statsFailNumRange = statsFailNumRange;
    }
	
    /**
    * 设置统计运行失败次数范围链式调用。
    */
    public TaskCronerInfoQueryParam statsFailNumRange(Integer[] statsFailNumRange){
        setStatsFailNumRange(statsFailNumRange);
        return this;
    }
	
    /**
    * 获取统计总时间毫秒数。
    */
    public Long getStatsRunTime(){
        return this.statsRunTime;
    }

    /**
    * 设置统计总时间毫秒数。
    */
    public void setStatsRunTime(Long statsRunTime){
        this.statsRunTime = statsRunTime;
    }
	
    /**
    * 设置统计总时间毫秒数链式调用。
    */
    public TaskCronerInfoQueryParam statsRunTime(Long statsRunTime){
        setStatsRunTime(statsRunTime);
        return this;
    }

    /**
    * 获取统计总时间毫秒数范围。
    */
    public Long[] getStatsRunTimeRange(){
        return this.statsRunTimeRange;
    }

    /**
    * 设置统计总时间毫秒数范围。
    */
    public void setStatsRunTimeRange(Long[] statsRunTimeRange){
        this.statsRunTimeRange = statsRunTimeRange;
    }
	
    /**
    * 设置统计总时间毫秒数范围链式调用。
    */
    public TaskCronerInfoQueryParam statsRunTimeRange(Long[] statsRunTimeRange){
        setStatsRunTimeRange(statsRunTimeRange);
        return this;
    }
	
    /**
    * 获取失败率。
    */
    public Integer getAlertFailRate(){
        return this.alertFailRate;
    }

    /**
    * 设置失败率。
    */
    public void setAlertFailRate(Integer alertFailRate){
        this.alertFailRate = alertFailRate;
    }
	
    /**
    * 设置失败率链式调用。
    */
    public TaskCronerInfoQueryParam alertFailRate(Integer alertFailRate){
        setAlertFailRate(alertFailRate);
        return this;
    }

    /**
    * 获取失败率范围。
    */
    public Integer[] getAlertFailRateRange(){
        return this.alertFailRateRange;
    }

    /**
    * 设置失败率范围。
    */
    public void setAlertFailRateRange(Integer[] alertFailRateRange){
        this.alertFailRateRange = alertFailRateRange;
    }
	
    /**
    * 设置失败率范围链式调用。
    */
    public TaskCronerInfoQueryParam alertFailRateRange(Integer[] alertFailRateRange){
        setAlertFailRateRange(alertFailRateRange);
        return this;
    }
	
    /**
    * 获取接口失败率。
    */
    public Integer getAlertFailPartnerRate(){
        return this.alertFailPartnerRate;
    }

    /**
    * 设置接口失败率。
    */
    public void setAlertFailPartnerRate(Integer alertFailPartnerRate){
        this.alertFailPartnerRate = alertFailPartnerRate;
    }
	
    /**
    * 设置接口失败率链式调用。
    */
    public TaskCronerInfoQueryParam alertFailPartnerRate(Integer alertFailPartnerRate){
        setAlertFailPartnerRate(alertFailPartnerRate);
        return this;
    }

    /**
    * 获取接口失败率范围。
    */
    public Integer[] getAlertFailPartnerRateRange(){
        return this.alertFailPartnerRateRange;
    }

    /**
    * 设置接口失败率范围。
    */
    public void setAlertFailPartnerRateRange(Integer[] alertFailPartnerRateRange){
        this.alertFailPartnerRateRange = alertFailPartnerRateRange;
    }
	
    /**
    * 设置接口失败率范围链式调用。
    */
    public TaskCronerInfoQueryParam alertFailPartnerRateRange(Integer[] alertFailPartnerRateRange){
        setAlertFailPartnerRateRange(alertFailPartnerRateRange);
        return this;
    }
	
    /**
    * 获取数据失败率。
    */
    public Integer getAlertFailDataRate(){
        return this.alertFailDataRate;
    }

    /**
    * 设置数据失败率。
    */
    public void setAlertFailDataRate(Integer alertFailDataRate){
        this.alertFailDataRate = alertFailDataRate;
    }
	
    /**
    * 设置数据失败率链式调用。
    */
    public TaskCronerInfoQueryParam alertFailDataRate(Integer alertFailDataRate){
        setAlertFailDataRate(alertFailDataRate);
        return this;
    }

    /**
    * 获取数据失败率范围。
    */
    public Integer[] getAlertFailDataRateRange(){
        return this.alertFailDataRateRange;
    }

    /**
    * 设置数据失败率范围。
    */
    public void setAlertFailDataRateRange(Integer[] alertFailDataRateRange){
        this.alertFailDataRateRange = alertFailDataRateRange;
    }
	
    /**
    * 设置数据失败率范围链式调用。
    */
    public TaskCronerInfoQueryParam alertFailDataRateRange(Integer[] alertFailDataRateRange){
        setAlertFailDataRateRange(alertFailDataRateRange);
        return this;
    }
	
    /**
    * 获取程序失败率。
    */
    public Integer getAlertFailProgramRate(){
        return this.alertFailProgramRate;
    }

    /**
    * 设置程序失败率。
    */
    public void setAlertFailProgramRate(Integer alertFailProgramRate){
        this.alertFailProgramRate = alertFailProgramRate;
    }
	
    /**
    * 设置程序失败率链式调用。
    */
    public TaskCronerInfoQueryParam alertFailProgramRate(Integer alertFailProgramRate){
        setAlertFailProgramRate(alertFailProgramRate);
        return this;
    }

    /**
    * 获取程序失败率范围。
    */
    public Integer[] getAlertFailProgramRateRange(){
        return this.alertFailProgramRateRange;
    }

    /**
    * 设置程序失败率范围。
    */
    public void setAlertFailProgramRateRange(Integer[] alertFailProgramRateRange){
        this.alertFailProgramRateRange = alertFailProgramRateRange;
    }
	
    /**
    * 设置程序失败率范围链式调用。
    */
    public TaskCronerInfoQueryParam alertFailProgramRateRange(Integer[] alertFailProgramRateRange){
        setAlertFailProgramRateRange(alertFailProgramRateRange);
        return this;
    }
	
    /**
    * 获取等待超时。
    */
    public Integer getAlertWaitTimeout(){
        return this.alertWaitTimeout;
    }

    /**
    * 设置等待超时。
    */
    public void setAlertWaitTimeout(Integer alertWaitTimeout){
        this.alertWaitTimeout = alertWaitTimeout;
    }
	
    /**
    * 设置等待超时链式调用。
    */
    public TaskCronerInfoQueryParam alertWaitTimeout(Integer alertWaitTimeout){
        setAlertWaitTimeout(alertWaitTimeout);
        return this;
    }

    /**
    * 获取等待超时范围。
    */
    public Integer[] getAlertWaitTimeoutRange(){
        return this.alertWaitTimeoutRange;
    }

    /**
    * 设置等待超时范围。
    */
    public void setAlertWaitTimeoutRange(Integer[] alertWaitTimeoutRange){
        this.alertWaitTimeoutRange = alertWaitTimeoutRange;
    }
	
    /**
    * 设置等待超时范围链式调用。
    */
    public TaskCronerInfoQueryParam alertWaitTimeoutRange(Integer[] alertWaitTimeoutRange){
        setAlertWaitTimeoutRange(alertWaitTimeoutRange);
        return this;
    }
	
    /**
    * 获取运行超时。
    */
    public Integer getAlertRunTimeout(){
        return this.alertRunTimeout;
    }

    /**
    * 设置运行超时。
    */
    public void setAlertRunTimeout(Integer alertRunTimeout){
        this.alertRunTimeout = alertRunTimeout;
    }
	
    /**
    * 设置运行超时链式调用。
    */
    public TaskCronerInfoQueryParam alertRunTimeout(Integer alertRunTimeout){
        setAlertRunTimeout(alertRunTimeout);
        return this;
    }

    /**
    * 获取运行超时范围。
    */
    public Integer[] getAlertRunTimeoutRange(){
        return this.alertRunTimeoutRange;
    }

    /**
    * 设置运行超时范围。
    */
    public void setAlertRunTimeoutRange(Integer[] alertRunTimeoutRange){
        this.alertRunTimeoutRange = alertRunTimeoutRange;
    }
	
    /**
    * 设置运行超时范围链式调用。
    */
    public TaskCronerInfoQueryParam alertRunTimeoutRange(Integer[] alertRunTimeoutRange){
        setAlertRunTimeoutRange(alertRunTimeoutRange);
        return this;
    }
	
    /**
    * 获取我方联系信息。
    */
    public String getTaskLinkOur(){
        return this.taskLinkOur;
    }

    /**
    * 设置我方联系信息。
    */
    public void setTaskLinkOur(String taskLinkOur){
        this.taskLinkOur = taskLinkOur;
    }
	
    /**
    * 设置我方联系信息链式调用。
    */
    public TaskCronerInfoQueryParam taskLinkOur(String taskLinkOur) {
        setTaskLinkOur(taskLinkOur);
        return this;
    }
	
    /**
    * 获取商户联系信息。
    */
    public String getTaskLinkMch(){
        return this.taskLinkMch;
    }

    /**
    * 设置商户联系信息。
    */
    public void setTaskLinkMch(String taskLinkMch){
        this.taskLinkMch = taskLinkMch;
    }
	
    /**
    * 设置商户联系信息链式调用。
    */
    public TaskCronerInfoQueryParam taskLinkMch(String taskLinkMch) {
        setTaskLinkMch(taskLinkMch);
        return this;
    }
	
    /**
    * 获取创建时间范围。
    */
    public Date[] getCreateDateRange(){
        return this.createDateRange;
    }

    /**
    * 设置创建时间范围。
    */
    public void setCreateDateRange(Date[] createDateRange){
        this.createDateRange = createDateRange;
    }
	
    /**
    * 设置创建时间范围链式调用。
    */
    public TaskCronerInfoQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange(createDateRange);
        return this;
    }
	
    /**
    * 获取最后修改时间范围。
    */
    public Date[] getModifyDateRange(){
        return this.modifyDateRange;
    }

    /**
    * 设置最后修改时间范围。
    */
    public void setModifyDateRange(Date[] modifyDateRange){
        this.modifyDateRange = modifyDateRange;
    }
	
    /**
    * 设置最后修改时间范围链式调用。
    */
    public TaskCronerInfoQueryParam modifyDateRange(Date[] modifyDateRange) {
        setModifyDateRange(modifyDateRange);
        return this;
    }
	
    /**
    * 获取状态1正常，0暂停，-1标记删除。
    */
    public Integer getState(){
        return this.state;
    }

    /**
    * 设置状态1正常，0暂停，-1标记删除。
    */
    public void setState(Integer state){
        this.state = state;
    }
	
    /**
    * 设置状态1正常，0暂停，-1标记删除链式调用。
    */
    public TaskCronerInfoQueryParam state(Integer state) {
        setState(state);
        return this;
    }

    /**
    * 获取状态1正常，0暂停，-1标记删除数组。
    */
    public Integer[] getStates(){
        return this.states;
    }

    /**
    * 设置状态1正常，0暂停，-1标记删除数组。
    */
    public void setStates(Integer[] states){
        this.states = states;
    }
	
    /**
    * 设置状态1正常，0暂停，-1标记删除数组链式调用。
    */
    public TaskCronerInfoQueryParam states(Integer[] states) {
        setStates(states);
        return this;
    }
    
    /**
    * 获取大于等于状态1正常，0暂停，-1标记删除。
    */
    public Integer getStateGte(){
        return this.stateGte;
    }

    /**
    * 设置大于等于状态1正常，0暂停，-1标记删除。
    */
    public void setStateGte(Integer stateGte){
        this.stateGte = stateGte;
    }
	
    /**
    * 设置大于等于状态1正常，0暂停，-1标记删除链式调用。
    */
    public TaskCronerInfoQueryParam stateGte(Integer stateGte) {
        setStateGte(stateGte);
        return this;
    }
    
    /**
    * 获取小于等于状态1正常，0暂停，-1标记删除。
    */
    public Integer getStateLte(){
        return this.stateLte;
    }

    /**
    * 获取小于等于状态1正常，0暂停，-1标记删除。
    */
    public void setStateLte(Integer stateLte){
        this.stateLte = stateLte;
    }
	
    /**
    * 获取小于等于状态1正常，0暂停，-1标记删除链式调用。
    */
    public TaskCronerInfoQueryParam stateLte(Integer stateLte) {
        setStateLte(stateLte);
        return this;
    }
    

}