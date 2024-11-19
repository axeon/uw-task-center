package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
* 队列任务配置列表查询参数。
*/
@Schema(title = "队列任务配置列表查询参数", description = "队列任务配置列表查询参数")
public class TaskRunnerInfoQueryParam extends PageQueryParam{


    /**
    * id。
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

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
    * 任务所有人。
    */
    @QueryMeta(expr = "task_owner like ?")
    @Schema(title="任务所有人", description = "任务所有人")
    private String taskOwner;

    /**
    * 运行标签。
    */
    @QueryMeta(expr = "task_tag like ?")
    @Schema(title="运行标签", description = "运行标签")
    private String taskTag;

    /**
    * 队列类型。
    */
    @QueryMeta(expr = "queue_type=?")
    @Schema(title="队列类型", description = "队列类型")
    private Integer queueType;

    /**
    * 延迟类型。
    */
    @QueryMeta(expr = "delay_type=?")
    @Schema(title="延迟类型", description = "延迟类型")
    private Integer delayType;

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
    * 消费者的数量。
    */
    @QueryMeta(expr = "consumer_num=?")
    @Schema(title="消费者的数量", description = "消费者的数量")
    private Integer consumerNum;

    /**
    * 消费者的数量范围。
    */
    @QueryMeta(expr = "consumer_num between ? and ?")
    @Schema(title="消费者的数量范围", description = "消费者的数量范围")
    private Integer[] consumerNumRange;

    /**
    * 队列预取数量。
    */
    @QueryMeta(expr = "prefetch_num=?")
    @Schema(title="队列预取数量", description = "队列预取数量")
    private Integer prefetchNum;

    /**
    * 队列预取数量范围。
    */
    @QueryMeta(expr = "prefetch_num between ? and ?")
    @Schema(title="队列预取数量范围", description = "队列预取数量范围")
    private Integer[] prefetchNumRange;

    /**
    * 限速类型。
    */
    @QueryMeta(expr = "rate_limit_type=?")
    @Schema(title="限速类型", description = "限速类型")
    private Integer rateLimitType;

    /**
    * 流量限定数值，默认为60次。
    */
    @QueryMeta(expr = "rate_limit_value=?")
    @Schema(title="流量限定数值，默认为60次", description = "流量限定数值，默认为60次")
    private Integer rateLimitValue;

    /**
    * 流量限定数值，默认为60次范围。
    */
    @QueryMeta(expr = "rate_limit_value between ? and ?")
    @Schema(title="流量限定数值，默认为60次范围", description = "流量限定数值，默认为60次范围")
    private Integer[] rateLimitValueRange;

    /**
    * 流量限定时间(S)，默认为60秒。
    */
    @QueryMeta(expr = "rate_limit_time=?")
    @Schema(title="流量限定时间(S)，默认为60秒", description = "流量限定时间(S)，默认为60秒")
    private Integer rateLimitTime;

    /**
    * 流量限定时间(S)，默认为60秒范围。
    */
    @QueryMeta(expr = "rate_limit_time between ? and ?")
    @Schema(title="流量限定时间(S)，默认为60秒范围", description = "流量限定时间(S)，默认为60秒范围")
    private Integer[] rateLimitTimeRange;

    /**
    * 当发生流量限制时，等待的秒数。
    */
    @QueryMeta(expr = "rate_limit_wait=?")
    @Schema(title="当发生流量限制时，等待的秒数", description = "当发生流量限制时，等待的秒数")
    private Integer rateLimitWait;

    /**
    * 当发生流量限制时，等待的秒数范围。
    */
    @QueryMeta(expr = "rate_limit_wait between ? and ?")
    @Schema(title="当发生流量限制时，等待的秒数范围", description = "当发生流量限制时，等待的秒数范围")
    private Integer[] rateLimitWaitRange;

    /**
    * 超过流量限制重试次数。
    */
    @QueryMeta(expr = "retry_times_by_overrated=?")
    @Schema(title="超过流量限制重试次数", description = "超过流量限制重试次数")
    private Integer retryTimesByOverrated;

    /**
    * 超过流量限制重试次数范围。
    */
    @QueryMeta(expr = "retry_times_by_overrated between ? and ?")
    @Schema(title="超过流量限制重试次数范围", description = "超过流量限制重试次数范围")
    private Integer[] retryTimesByOverratedRange;

    /**
    * 对方接口错误重试次数。
    */
    @QueryMeta(expr = "retry_times_by_partner=?")
    @Schema(title="对方接口错误重试次数", description = "对方接口错误重试次数")
    private Integer retryTimesByPartner;

    /**
    * 对方接口错误重试次数范围。
    */
    @QueryMeta(expr = "retry_times_by_partner between ? and ?")
    @Schema(title="对方接口错误重试次数范围", description = "对方接口错误重试次数范围")
    private Integer[] retryTimesByPartnerRange;
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
    * 配置失败率。
    */
    @QueryMeta(expr = "alert_fail_config_rate=?")
    @Schema(title="配置失败率", description = "配置失败率")
    private Integer alertFailConfigRate;

    /**
    * 配置失败率范围。
    */
    @QueryMeta(expr = "alert_fail_config_rate between ? and ?")
    @Schema(title="配置失败率范围", description = "配置失败率范围")
    private Integer[] alertFailConfigRateRange;

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
    * 队列长度限制。
    */
    @QueryMeta(expr = "alert_queue_oversize=?")
    @Schema(title="队列长度限制", description = "队列长度限制")
    private Integer alertQueueOversize;

    /**
    * 队列长度限制范围。
    */
    @QueryMeta(expr = "alert_queue_oversize between ? and ?")
    @Schema(title="队列长度限制范围", description = "队列长度限制范围")
    private Integer[] alertQueueOversizeRange;

    /**
    * 队列等待超时。
    */
    @QueryMeta(expr = "alert_queue_timeout=?")
    @Schema(title="队列等待超时", description = "队列等待超时")
    private Integer alertQueueTimeout;

    /**
    * 队列等待超时范围。
    */
    @QueryMeta(expr = "alert_queue_timeout between ? and ?")
    @Schema(title="队列等待超时范围", description = "队列等待超时范围")
    private Integer[] alertQueueTimeoutRange;

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
    * 创建日期范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建日期范围", description = "创建日期范围")
    private Date[] createDateRange;

    /**
    * 最后修改日期范围。
    */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title="最后修改日期范围", description = "最后修改日期范围")
    private Date[] modifyDateRange;

    /**
    * 状态1正常，0暂停，-1标记删除。
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除")
    private Integer state;

    /**
    * 数组状态1正常，0暂停，-1标记删除。
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="数组状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除数组，可同时匹配多个状态。")
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
    * 获取运行标签。
    */
    public String getTaskTag(){
        return this.taskTag;
    }

    /**
    * 设置运行标签。
    */
    public void setTaskTag(String taskTag){
        this.taskTag = taskTag;
    }
    /**
    * 获取队列类型。
    */
    public Integer getQueueType(){
        return this.queueType;
    }

    /**
    * 设置队列类型。
    */
    public void setQueueType(Integer queueType){
        this.queueType = queueType;
    }
    /**
    * 获取延迟类型。
    */
    public Integer getDelayType(){
        return this.delayType;
    }

    /**
    * 设置延迟类型。
    */
    public void setDelayType(Integer delayType){
        this.delayType = delayType;
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
    * 获取消费者的数量。
    */
    public Integer getConsumerNum(){
        return this.consumerNum;
    }

    /**
    * 设置消费者的数量。
    */
    public void setConsumerNum(Integer consumerNum){
        this.consumerNum = consumerNum;
    }

    /**
    * 获取消费者的数量范围。
    */
    public Integer[] getConsumerNumRange(){
        return this.consumerNumRange;
    }

    /**
    * 设置消费者的数量范围。
    */
    public void setConsumerNumRange(Integer[] consumerNumRange){
        this.consumerNumRange = consumerNumRange;
    }

    /**
    * 获取队列预取数量。
    */
    public Integer getPrefetchNum(){
        return this.prefetchNum;
    }

    /**
    * 设置队列预取数量。
    */
    public void setPrefetchNum(Integer prefetchNum){
        this.prefetchNum = prefetchNum;
    }

    /**
    * 获取队列预取数量范围。
    */
    public Integer[] getPrefetchNumRange(){
        return this.prefetchNumRange;
    }

    /**
    * 设置队列预取数量范围。
    */
    public void setPrefetchNumRange(Integer[] prefetchNumRange){
        this.prefetchNumRange = prefetchNumRange;
    }
    /**
    * 获取限速类型。
    */
    public Integer getRateLimitType(){
        return this.rateLimitType;
    }

    /**
    * 设置限速类型。
    */
    public void setRateLimitType(Integer rateLimitType){
        this.rateLimitType = rateLimitType;
    }

    /**
    * 获取流量限定数值，默认为60次。
    */
    public Integer getRateLimitValue(){
        return this.rateLimitValue;
    }

    /**
    * 设置流量限定数值，默认为60次。
    */
    public void setRateLimitValue(Integer rateLimitValue){
        this.rateLimitValue = rateLimitValue;
    }

    /**
    * 获取流量限定数值，默认为60次范围。
    */
    public Integer[] getRateLimitValueRange(){
        return this.rateLimitValueRange;
    }

    /**
    * 设置流量限定数值，默认为60次范围。
    */
    public void setRateLimitValueRange(Integer[] rateLimitValueRange){
        this.rateLimitValueRange = rateLimitValueRange;
    }

    /**
    * 获取流量限定时间(S)，默认为60秒。
    */
    public Integer getRateLimitTime(){
        return this.rateLimitTime;
    }

    /**
    * 设置流量限定时间(S)，默认为60秒。
    */
    public void setRateLimitTime(Integer rateLimitTime){
        this.rateLimitTime = rateLimitTime;
    }

    /**
    * 获取流量限定时间(S)，默认为60秒范围。
    */
    public Integer[] getRateLimitTimeRange(){
        return this.rateLimitTimeRange;
    }

    /**
    * 设置流量限定时间(S)，默认为60秒范围。
    */
    public void setRateLimitTimeRange(Integer[] rateLimitTimeRange){
        this.rateLimitTimeRange = rateLimitTimeRange;
    }

    /**
    * 获取当发生流量限制时，等待的秒数。
    */
    public Integer getRateLimitWait(){
        return this.rateLimitWait;
    }

    /**
    * 设置当发生流量限制时，等待的秒数。
    */
    public void setRateLimitWait(Integer rateLimitWait){
        this.rateLimitWait = rateLimitWait;
    }

    /**
    * 获取当发生流量限制时，等待的秒数范围。
    */
    public Integer[] getRateLimitWaitRange(){
        return this.rateLimitWaitRange;
    }

    /**
    * 设置当发生流量限制时，等待的秒数范围。
    */
    public void setRateLimitWaitRange(Integer[] rateLimitWaitRange){
        this.rateLimitWaitRange = rateLimitWaitRange;
    }

    /**
    * 获取超过流量限制重试次数。
    */
    public Integer getRetryTimesByOverrated(){
        return this.retryTimesByOverrated;
    }

    /**
    * 设置超过流量限制重试次数。
    */
    public void setRetryTimesByOverrated(Integer retryTimesByOverrated){
        this.retryTimesByOverrated = retryTimesByOverrated;
    }

    /**
    * 获取超过流量限制重试次数范围。
    */
    public Integer[] getRetryTimesByOverratedRange(){
        return this.retryTimesByOverratedRange;
    }

    /**
    * 设置超过流量限制重试次数范围。
    */
    public void setRetryTimesByOverratedRange(Integer[] retryTimesByOverratedRange){
        this.retryTimesByOverratedRange = retryTimesByOverratedRange;
    }

    /**
    * 获取对方接口错误重试次数。
    */
    public Integer getRetryTimesByPartner(){
        return this.retryTimesByPartner;
    }

    /**
    * 设置对方接口错误重试次数。
    */
    public void setRetryTimesByPartner(Integer retryTimesByPartner){
        this.retryTimesByPartner = retryTimesByPartner;
    }

    /**
    * 获取对方接口错误重试次数范围。
    */
    public Integer[] getRetryTimesByPartnerRange(){
        return this.retryTimesByPartnerRange;
    }

    /**
    * 设置对方接口错误重试次数范围。
    */
    public void setRetryTimesByPartnerRange(Integer[] retryTimesByPartnerRange){
        this.retryTimesByPartnerRange = retryTimesByPartnerRange;
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
    * 获取配置失败率。
    */
    public Integer getAlertFailConfigRate(){
        return this.alertFailConfigRate;
    }

    /**
    * 设置配置失败率。
    */
    public void setAlertFailConfigRate(Integer alertFailConfigRate){
        this.alertFailConfigRate = alertFailConfigRate;
    }

    /**
    * 获取配置失败率范围。
    */
    public Integer[] getAlertFailConfigRateRange(){
        return this.alertFailConfigRateRange;
    }

    /**
    * 设置配置失败率范围。
    */
    public void setAlertFailConfigRateRange(Integer[] alertFailConfigRateRange){
        this.alertFailConfigRateRange = alertFailConfigRateRange;
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
    * 获取队列长度限制。
    */
    public Integer getAlertQueueOversize(){
        return this.alertQueueOversize;
    }

    /**
    * 设置队列长度限制。
    */
    public void setAlertQueueOversize(Integer alertQueueOversize){
        this.alertQueueOversize = alertQueueOversize;
    }

    /**
    * 获取队列长度限制范围。
    */
    public Integer[] getAlertQueueOversizeRange(){
        return this.alertQueueOversizeRange;
    }

    /**
    * 设置队列长度限制范围。
    */
    public void setAlertQueueOversizeRange(Integer[] alertQueueOversizeRange){
        this.alertQueueOversizeRange = alertQueueOversizeRange;
    }

    /**
    * 获取队列等待超时。
    */
    public Integer getAlertQueueTimeout(){
        return this.alertQueueTimeout;
    }

    /**
    * 设置队列等待超时。
    */
    public void setAlertQueueTimeout(Integer alertQueueTimeout){
        this.alertQueueTimeout = alertQueueTimeout;
    }

    /**
    * 获取队列等待超时范围。
    */
    public Integer[] getAlertQueueTimeoutRange(){
        return this.alertQueueTimeoutRange;
    }

    /**
    * 设置队列等待超时范围。
    */
    public void setAlertQueueTimeoutRange(Integer[] alertQueueTimeoutRange){
        this.alertQueueTimeoutRange = alertQueueTimeoutRange;
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
    * 获取创建日期范围。
    */
    public Date[] getCreateDateRange(){
        return this.createDateRange;
    }

    /**
    * 设置创建日期范围。
    */
    public void setCreateDateRange(Date[] createDateRange){
        this.createDateRange = createDateRange;
    }
    /**
    * 获取最后修改日期范围。
    */
    public Date[] getModifyDateRange(){
        return this.modifyDateRange;
    }

    /**
    * 设置最后修改日期范围。
    */
    public void setModifyDateRange(Date[] modifyDateRange){
        this.modifyDateRange = modifyDateRange;
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
    * 获取数组状态1正常，0暂停，-1标记删除。
    */
    public Integer[] getStates(){
        return this.states;
    }

    /**
    * 设置数组状态1正常，0暂停，-1标记删除。
    */
    public void setStates(Integer[] states){
        this.states = states;
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
    

}