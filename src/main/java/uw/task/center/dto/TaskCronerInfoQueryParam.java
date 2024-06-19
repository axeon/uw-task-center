package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
* 定时任务配置列表查询参数。
*/
@Schema(title = "定时任务配置列表查询参数", description = "定时任务配置列表查询参数")
public class TaskCronerInfoQueryParam extends PageQueryParam{


    /**
    * id
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * 任务名称
    */
    @QueryMeta(expr = "task_name like ?")
    @Schema(title="任务名称", description = "任务名称")
    private String taskName;

    /**
    * 执行类信息
    */
    @QueryMeta(expr = "task_class like ?")
    @Schema(title="执行类信息", description = "执行类信息")
    private String taskClass;

    /**
    * 任务参数
    */
    @QueryMeta(expr = "task_param like ?")
    @Schema(title="任务参数", description = "任务参数")
    private String taskParam;

    /**
    * 任务所有人
    */
    @QueryMeta(expr = "task_owner like ?")
    @Schema(title="任务所有人", description = "任务所有人")
    private String taskOwner;

    /**
    * cron表达式
    */
    @QueryMeta(expr = "task_cron like ?")
    @Schema(title="cron表达式", description = "cron表达式")
    private String taskCron;

    /**
    * 运行类型
    */
    @QueryMeta(expr = "run_type=?")
    @Schema(title="运行类型", description = "运行类型")
    private Integer runType;

    /**
    * 运行目标
    */
    @QueryMeta(expr = "run_target like ?")
    @Schema(title="运行目标", description = "运行目标")
    private String runTarget;

    /**
    * 日志类型
    */
    @QueryMeta(expr = "log_level=?")
    @Schema(title="日志类型", description = "日志类型")
    private Integer logLevel;

    /**
    * 日志类型范围
    */
    @QueryMeta(expr = "log_level between ? and ?")
    @Schema(title="日志类型范围", description = "日志类型范围")
    private Integer[] logLevelRange;

    /**
    * 日志长度限制
    */
    @QueryMeta(expr = "log_limit_size=?")
    @Schema(title="日志长度限制", description = "日志长度限制")
    private Integer logLimitSize;

    /**
    * 日志长度限制范围
    */
    @QueryMeta(expr = "log_limit_size between ? and ?")
    @Schema(title="日志长度限制范围", description = "日志长度限制范围")
    private Integer[] logLimitSizeRange;
    /**
    * 下次执行时间范围
    */
    @QueryMeta(expr = "next_run_date between ? and ?")
    @Schema(title="下次执行时间范围", description = "下次执行时间范围")
    private Date[] nextRunDateRange;

    /**
    * 最后统计时间范围
    */
    @QueryMeta(expr = "stats_date between ? and ?")
    @Schema(title="最后统计时间范围", description = "最后统计时间范围")
    private Date[] statsDateRange;


    /**
    * 统计运行次数
    */
    @QueryMeta(expr = "stats_run_num=?")
    @Schema(title="统计运行次数", description = "统计运行次数")
    private Integer statsRunNum;

    /**
    * 统计运行次数范围
    */
    @QueryMeta(expr = "stats_run_num between ? and ?")
    @Schema(title="统计运行次数范围", description = "统计运行次数范围")
    private Integer[] statsRunNumRange;

    /**
    * 统计运行失败次数
    */
    @QueryMeta(expr = "stats_fail_num=?")
    @Schema(title="统计运行失败次数", description = "统计运行失败次数")
    private Integer statsFailNum;

    /**
    * 统计运行失败次数范围
    */
    @QueryMeta(expr = "stats_fail_num between ? and ?")
    @Schema(title="统计运行失败次数范围", description = "统计运行失败次数范围")
    private Integer[] statsFailNumRange;

    /**
    * 统计总时间毫秒数
    */
    @QueryMeta(expr = "stats_run_time=?")
    @Schema(title="统计总时间毫秒数", description = "统计总时间毫秒数")
    private Long statsRunTime;

    /**
    * 统计总时间毫秒数范围
    */
    @QueryMeta(expr = "stats_run_time between ? and ?")
    @Schema(title="统计总时间毫秒数范围", description = "统计总时间毫秒数范围")
    private Long[] statsRunTimeRange;

    /**
    * 失败率
    */
    @QueryMeta(expr = "alert_fail_rate=?")
    @Schema(title="失败率", description = "失败率")
    private Integer alertFailRate;

    /**
    * 失败率范围
    */
    @QueryMeta(expr = "alert_fail_rate between ? and ?")
    @Schema(title="失败率范围", description = "失败率范围")
    private Integer[] alertFailRateRange;

    /**
    * 接口失败率
    */
    @QueryMeta(expr = "alert_fail_partner_rate=?")
    @Schema(title="接口失败率", description = "接口失败率")
    private Integer alertFailPartnerRate;

    /**
    * 接口失败率范围
    */
    @QueryMeta(expr = "alert_fail_partner_rate between ? and ?")
    @Schema(title="接口失败率范围", description = "接口失败率范围")
    private Integer[] alertFailPartnerRateRange;

    /**
    * 数据失败率
    */
    @QueryMeta(expr = "alert_fail_data_rate=?")
    @Schema(title="数据失败率", description = "数据失败率")
    private Integer alertFailDataRate;

    /**
    * 数据失败率范围
    */
    @QueryMeta(expr = "alert_fail_data_rate between ? and ?")
    @Schema(title="数据失败率范围", description = "数据失败率范围")
    private Integer[] alertFailDataRateRange;

    /**
    * 程序失败率
    */
    @QueryMeta(expr = "alert_fail_program_rate=?")
    @Schema(title="程序失败率", description = "程序失败率")
    private Integer alertFailProgramRate;

    /**
    * 程序失败率范围
    */
    @QueryMeta(expr = "alert_fail_program_rate between ? and ?")
    @Schema(title="程序失败率范围", description = "程序失败率范围")
    private Integer[] alertFailProgramRateRange;

    /**
    * 等待超时
    */
    @QueryMeta(expr = "alert_wait_timeout=?")
    @Schema(title="等待超时", description = "等待超时")
    private Integer alertWaitTimeout;

    /**
    * 等待超时范围
    */
    @QueryMeta(expr = "alert_wait_timeout between ? and ?")
    @Schema(title="等待超时范围", description = "等待超时范围")
    private Integer[] alertWaitTimeoutRange;

    /**
    * 运行超时
    */
    @QueryMeta(expr = "alert_run_timeout=?")
    @Schema(title="运行超时", description = "运行超时")
    private Integer alertRunTimeout;

    /**
    * 运行超时范围
    */
    @QueryMeta(expr = "alert_run_timeout between ? and ?")
    @Schema(title="运行超时范围", description = "运行超时范围")
    private Integer[] alertRunTimeoutRange;

    /**
    * 我方联系信息
    */
    @QueryMeta(expr = "task_link_our like ?")
    @Schema(title="我方联系信息", description = "我方联系信息")
    private String taskLinkOur;

    /**
    * 商户联系信息
    */
    @QueryMeta(expr = "task_link_mch like ?")
    @Schema(title="商户联系信息", description = "商户联系信息")
    private String taskLinkMch;
    /**
    * 创建时间范围
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

    /**
    * 最后修改时间范围
    */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title="最后修改时间范围", description = "最后修改时间范围")
    private Date[] modifyDateRange;

    /**
    * 状态1正常，0暂停，-1标记删除
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除")
    private Integer state;

    /**
    * 正常状态1正常，0暂停，-1标记删除
    */
    @QueryMeta(expr = "state>-1")
    @Schema(title="正常状态1正常，0暂停，-1标记删除", description = "正常状态1正常，0暂停，-1标记删除")
    private Boolean stateOn;

    /**
    * 状态1正常，0暂停，-1标记删除数组
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="状态1正常，0暂停，-1标记删除数组", description = "状态1正常，0暂停，-1标记删除数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
    * 状态1正常，0暂停，-1标记删除运算条件。
    * 可以使用运算符号。
    */
    @QueryMeta(expr = "state ?")
    @Schema(title="状态1正常，0暂停，-1标记删除运算条件", description = "状态1正常，0暂停，-1标记删除运算条件，可使用><=!比较运算符。")
    private String stateOp;


    /**
    * 获得id。
    */
    public Long getId(){
        return this.id;
    }

    /**
    * 设置id。
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获得任务名称。
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
    * 获得执行类信息。
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
    * 获得任务参数。
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
    * 获得任务所有人。
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
    * 获得cron表达式。
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
    * 获得运行类型。
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
    * 获得运行目标。
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
    * 获得日志类型。
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
    * 获得日志类型范围。
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
    * 获得日志长度限制。
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
    * 获得日志长度限制范围。
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
    * 获得下次执行时间范围。
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
    * 获得最后统计时间范围。
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
    * 获得统计运行次数。
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
    * 获得统计运行次数范围。
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
    * 获得统计运行失败次数。
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
    * 获得统计运行失败次数范围。
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
    * 获得统计总时间毫秒数。
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
    * 获得统计总时间毫秒数范围。
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
    * 获得失败率。
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
    * 获得失败率范围。
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
    * 获得接口失败率。
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
    * 获得接口失败率范围。
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
    * 获得数据失败率。
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
    * 获得数据失败率范围。
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
    * 获得程序失败率。
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
    * 获得程序失败率范围。
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
    * 获得等待超时。
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
    * 获得等待超时范围。
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
    * 获得运行超时。
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
    * 获得运行超时范围。
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
    * 获得我方联系信息。
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
    * 获得商户联系信息。
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
    * 获得创建时间范围。
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
    * 获得最后修改时间范围。
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
    * 获得状态1正常，0暂停，-1标记删除。
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
    * 获得正常状态1正常，0暂停，-1标记删除。
    */
    public Boolean getStateOn(){
        return this.stateOn;
    }

    /**
    * 设置正常状态1正常，0暂停，-1标记删除。
    */
    public void setStateOn(Boolean stateOn){
        this.stateOn = stateOn;
    }

    /**
    * 获得状态1正常，0暂停，-1标记删除数组。
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
    * 获得状态1正常，0暂停，-1标记删除运算条件。
    */
    public String getStateOp(){
        return this.stateOp;
    }

    /**
    * 设置状态1正常，0暂停，-1标记删除运算条件。
    */
    public void setStateOp(String stateOp){
        this.stateOp = stateOp;
    }

}