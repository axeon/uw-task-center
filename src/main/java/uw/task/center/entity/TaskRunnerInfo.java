package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

/**
 * TaskRunnerInfo实体类
 * 队列任务配置
 *
 * @author axeon
 */
@TableMeta(tableName="task_runner_info",tableType="table")
@Schema(title = "队列任务配置", description = "队列任务配置")
public class TaskRunnerInfo implements DataEntity,Serializable{


    /**
     * id
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "id", description = "id")
    private long id;

    /**
     * 任务名称
     */
    @ColumnMeta(columnName="task_name", dataType="String", dataSize=200, nullable=true)
    @Schema(title = "任务名称", description = "任务名称")
    private String taskName;

    /**
     * 任务描述
     */
    @ColumnMeta(columnName="task_desc", dataType="String", dataSize=1000, nullable=true)
    @Schema(title = "任务描述", description = "任务描述")
    private String taskDesc;

    /**
     * 执行类信息
     */
    @ColumnMeta(columnName="task_class", dataType="String", dataSize=200, nullable=true)
    @Schema(title = "执行类信息", description = "执行类信息")
    private String taskClass;

    /**
     * 任务所有人
     */
    @ColumnMeta(columnName="task_owner", dataType="String", dataSize=200, nullable=true)
    @Schema(title = "任务所有人", description = "任务所有人")
    private String taskOwner;

    /**
     * 运行标签
     */
    @ColumnMeta(columnName="task_tag", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行标签", description = "运行标签")
    private String taskTag;

    /**
     * 队列类型
     */
    @ColumnMeta(columnName="queue_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列类型", description = "队列类型")
    private int queueType;

    /**
     * 延迟类型
     */
    @ColumnMeta(columnName="delay_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "延迟类型", description = "延迟类型")
    private int delayType;

    /**
     * 日志类型
     */
    @ColumnMeta(columnName="log_level", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "日志类型", description = "日志类型")
    private int logLevel;

    /**
     * 日志长度限制
     */
    @ColumnMeta(columnName="log_limit_size", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "日志长度限制", description = "日志长度限制")
    private int logLimitSize;

    /**
     * 运行类型
     */
    @ColumnMeta(columnName="run_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行类型", description = "运行类型")
    private int runType;

    /**
     * 运行目标
     */
    @ColumnMeta(columnName="run_target", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行目标", description = "运行目标")
    private String runTarget;

    /**
     * 消费者的数量
     */
    @ColumnMeta(columnName="consumer_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "消费者的数量", description = "消费者的数量")
    private int consumerNum;

    /**
     * 队列预取数量
     */
    @ColumnMeta(columnName="prefetch_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列预取数量", description = "队列预取数量")
    private int prefetchNum;

    /**
     * 限速类型
     */
    @ColumnMeta(columnName="rate_limit_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "限速类型", description = "限速类型")
    private int rateLimitType;

    /**
     * 流量限定数值，默认为60次
     */
    @ColumnMeta(columnName="rate_limit_value", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "流量限定数值，默认为60次", description = "流量限定数值，默认为60次")
    private int rateLimitValue;

    /**
     * 流量限定时间(S)，默认为60秒
     */
    @ColumnMeta(columnName="rate_limit_time", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "流量限定时间(S)，默认为60秒", description = "流量限定时间(S)，默认为60秒")
    private int rateLimitTime;

    /**
     * 当发生流量限制时，等待的秒数
     */
    @ColumnMeta(columnName="rate_limit_wait", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "当发生流量限制时，等待的秒数", description = "当发生流量限制时，等待的秒数")
    private int rateLimitWait;

    /**
     * 超过流量限制重试次数
     */
    @ColumnMeta(columnName="retry_times_by_overrated", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "超过流量限制重试次数", description = "超过流量限制重试次数")
    private int retryTimesByOverrated;

    /**
     * 对方接口错误重试次数
     */
    @ColumnMeta(columnName="retry_times_by_partner", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "对方接口错误重试次数", description = "对方接口错误重试次数")
    private int retryTimesByPartner;

    /**
     * 最后统计时间
     */
    @ColumnMeta(columnName="stats_date", dataType="java.util.Date", dataSize=19, nullable=true)
    @Schema(title = "最后统计时间", description = "最后统计时间")
    private java.util.Date statsDate;

    /**
     * 统计运行次数
     */
    @ColumnMeta(columnName="stats_run_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行次数", description = "统计运行次数")
    private int statsRunNum;

    /**
     * 统计运行失败次数
     */
    @ColumnMeta(columnName="stats_fail_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数")
    private int statsFailNum;

    /**
     * 统计总时间毫秒数
     */
    @ColumnMeta(columnName="stats_run_time", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数")
    private long statsRunTime;

    /**
     * 失败率
     */
    @ColumnMeta(columnName="alert_fail_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "失败率", description = "失败率")
    private int alertFailRate;

    /**
     * 接口失败率
     */
    @ColumnMeta(columnName="alert_fail_partner_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "接口失败率", description = "接口失败率")
    private int alertFailPartnerRate;

    /**
     * 程序失败率
     */
    @ColumnMeta(columnName="alert_fail_program_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序失败率", description = "程序失败率")
    private int alertFailProgramRate;

    /**
     * 配置失败率
     */
    @ColumnMeta(columnName="alert_fail_config_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "配置失败率", description = "配置失败率")
    private int alertFailConfigRate;

    /**
     * 数据失败率
     */
    @ColumnMeta(columnName="alert_fail_data_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "数据失败率", description = "数据失败率")
    private int alertFailDataRate;

    /**
     * 队列长度限制
     */
    @ColumnMeta(columnName="alert_queue_oversize", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列长度限制", description = "队列长度限制")
    private int alertQueueOversize;

    /**
     * 队列等待超时
     */
    @ColumnMeta(columnName="alert_queue_timeout", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列等待超时", description = "队列等待超时")
    private int alertQueueTimeout;

    /**
     * 等待超时
     */
    @ColumnMeta(columnName="alert_wait_timeout", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "等待超时", description = "等待超时")
    private int alertWaitTimeout;

    /**
     * 运行超时
     */
    @ColumnMeta(columnName="alert_run_timeout", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行超时", description = "运行超时")
    private int alertRunTimeout;

    /**
     * 我方联系信息
     */
    @ColumnMeta(columnName="task_link_our", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "我方联系信息", description = "我方联系信息")
    private String taskLinkOur;

    /**
     * 商户联系信息
     */
    @ColumnMeta(columnName="task_link_mch", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "商户联系信息", description = "商户联系信息")
    private String taskLinkMch;

    /**
     * 创建日期
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建日期", description = "创建日期")
    private java.util.Date createDate;

    /**
     * 最后修改日期
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "最后修改日期", description = "最后修改日期")
    private java.util.Date modifyDate;

    /**
     * 状态1正常，0暂停，-1标记删除
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态1正常，0暂停，-1标记删除", description = "状态1正常，0暂停，-1标记删除")
    private int state;

    /**
     * 轻量级状态下更新列表list.
     */
    private transient Set<String> UPDATED_COLUMN = null;

    /**
     * 更新的信息.
     */
    private transient StringBuilder UPDATED_INFO = null;

    /**
     * 获得更改的字段列表.
     */
    @Override
    public Set<String> GET_UPDATED_COLUMN() {
        return UPDATED_COLUMN;
    }

    /**
     * 获得文本更新信息.
     */
    @Override
    public String GET_UPDATED_INFO() {
        if (this.UPDATED_INFO == null) {
            return null;
        } else {
            return this.UPDATED_INFO.toString();
        }
    }

    /**
     * 清除更新信息.
     */
    @Override
    public void CLEAR_UPDATED_INFO() {
        UPDATED_COLUMN = null;
        UPDATED_INFO = null;
    }

    /**
     * 初始化set相关的信息.
     */
    private void _INIT_UPDATE_INFO() {
        this.UPDATED_COLUMN = new HashSet<String>();
        this.UPDATED_INFO = new StringBuilder("表task_runner_info主键\"" + 
        this.id+ "\"更新为:\r\n");
    }


    /**
     * 获得id。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获得任务名称。
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * 获得任务描述。
     */
    public String getTaskDesc(){
        return this.taskDesc;
    }

    /**
     * 获得执行类信息。
     */
    public String getTaskClass(){
        return this.taskClass;
    }

    /**
     * 获得任务所有人。
     */
    public String getTaskOwner(){
        return this.taskOwner;
    }

    /**
     * 获得运行标签。
     */
    public String getTaskTag(){
        return this.taskTag;
    }

    /**
     * 获得队列类型。
     */
    public int getQueueType(){
        return this.queueType;
    }

    /**
     * 获得延迟类型。
     */
    public int getDelayType(){
        return this.delayType;
    }

    /**
     * 获得日志类型。
     */
    public int getLogLevel(){
        return this.logLevel;
    }

    /**
     * 获得日志长度限制。
     */
    public int getLogLimitSize(){
        return this.logLimitSize;
    }

    /**
     * 获得运行类型。
     */
    public int getRunType(){
        return this.runType;
    }

    /**
     * 获得运行目标。
     */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
     * 获得消费者的数量。
     */
    public int getConsumerNum(){
        return this.consumerNum;
    }

    /**
     * 获得队列预取数量。
     */
    public int getPrefetchNum(){
        return this.prefetchNum;
    }

    /**
     * 获得限速类型。
     */
    public int getRateLimitType(){
        return this.rateLimitType;
    }

    /**
     * 获得流量限定数值，默认为60次。
     */
    public int getRateLimitValue(){
        return this.rateLimitValue;
    }

    /**
     * 获得流量限定时间(S)，默认为60秒。
     */
    public int getRateLimitTime(){
        return this.rateLimitTime;
    }

    /**
     * 获得当发生流量限制时，等待的秒数。
     */
    public int getRateLimitWait(){
        return this.rateLimitWait;
    }

    /**
     * 获得超过流量限制重试次数。
     */
    public int getRetryTimesByOverrated(){
        return this.retryTimesByOverrated;
    }

    /**
     * 获得对方接口错误重试次数。
     */
    public int getRetryTimesByPartner(){
        return this.retryTimesByPartner;
    }

    /**
     * 获得最后统计时间。
     */
    public java.util.Date getStatsDate(){
        return this.statsDate;
    }

    /**
     * 获得统计运行次数。
     */
    public int getStatsRunNum(){
        return this.statsRunNum;
    }

    /**
     * 获得统计运行失败次数。
     */
    public int getStatsFailNum(){
        return this.statsFailNum;
    }

    /**
     * 获得统计总时间毫秒数。
     */
    public long getStatsRunTime(){
        return this.statsRunTime;
    }

    /**
     * 获得失败率。
     */
    public int getAlertFailRate(){
        return this.alertFailRate;
    }

    /**
     * 获得接口失败率。
     */
    public int getAlertFailPartnerRate(){
        return this.alertFailPartnerRate;
    }

    /**
     * 获得程序失败率。
     */
    public int getAlertFailProgramRate(){
        return this.alertFailProgramRate;
    }

    /**
     * 获得配置失败率。
     */
    public int getAlertFailConfigRate(){
        return this.alertFailConfigRate;
    }

    /**
     * 获得数据失败率。
     */
    public int getAlertFailDataRate(){
        return this.alertFailDataRate;
    }

    /**
     * 获得队列长度限制。
     */
    public int getAlertQueueOversize(){
        return this.alertQueueOversize;
    }

    /**
     * 获得队列等待超时。
     */
    public int getAlertQueueTimeout(){
        return this.alertQueueTimeout;
    }

    /**
     * 获得等待超时。
     */
    public int getAlertWaitTimeout(){
        return this.alertWaitTimeout;
    }

    /**
     * 获得运行超时。
     */
    public int getAlertRunTimeout(){
        return this.alertRunTimeout;
    }

    /**
     * 获得我方联系信息。
     */
    public String getTaskLinkOur(){
        return this.taskLinkOur;
    }

    /**
     * 获得商户联系信息。
     */
    public String getTaskLinkMch(){
        return this.taskLinkMch;
    }

    /**
     * 获得创建日期。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获得最后修改日期。
     */
    public java.util.Date getModifyDate(){
        return this.modifyDate;
    }

    /**
     * 获得状态1正常，0暂停，-1标记删除。
     */
    public int getState(){
        return this.state;
    }


    /**
     * 设置id。
     */
    public void setId(long id){
        if ((!String.valueOf(this.id).equals(String.valueOf(id)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("id");
            this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\"" + id + "\"\r\n");
            this.id = id;
        }
    }

    /**
     * 设置任务名称。
     */
    public void setTaskName(String taskName){
        if ((!String.valueOf(this.taskName).equals(String.valueOf(taskName)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_name");
            this.UPDATED_INFO.append("task_name:\"" + this.taskName+ "\"=>\"" + taskName + "\"\r\n");
            this.taskName = taskName;
        }
    }

    /**
     * 设置任务描述。
     */
    public void setTaskDesc(String taskDesc){
        if ((!String.valueOf(this.taskDesc).equals(String.valueOf(taskDesc)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_desc");
            this.UPDATED_INFO.append("task_desc:\"" + this.taskDesc+ "\"=>\"" + taskDesc + "\"\r\n");
            this.taskDesc = taskDesc;
        }
    }

    /**
     * 设置执行类信息。
     */
    public void setTaskClass(String taskClass){
        if ((!String.valueOf(this.taskClass).equals(String.valueOf(taskClass)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_class");
            this.UPDATED_INFO.append("task_class:\"" + this.taskClass+ "\"=>\"" + taskClass + "\"\r\n");
            this.taskClass = taskClass;
        }
    }

    /**
     * 设置任务所有人。
     */
    public void setTaskOwner(String taskOwner){
        if ((!String.valueOf(this.taskOwner).equals(String.valueOf(taskOwner)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_owner");
            this.UPDATED_INFO.append("task_owner:\"" + this.taskOwner+ "\"=>\"" + taskOwner + "\"\r\n");
            this.taskOwner = taskOwner;
        }
    }

    /**
     * 设置运行标签。
     */
    public void setTaskTag(String taskTag){
        if ((!String.valueOf(this.taskTag).equals(String.valueOf(taskTag)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_tag");
            this.UPDATED_INFO.append("task_tag:\"" + this.taskTag+ "\"=>\"" + taskTag + "\"\r\n");
            this.taskTag = taskTag;
        }
    }

    /**
     * 设置队列类型。
     */
    public void setQueueType(int queueType){
        if ((!String.valueOf(this.queueType).equals(String.valueOf(queueType)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("queue_type");
            this.UPDATED_INFO.append("queue_type:\"" + this.queueType+ "\"=>\"" + queueType + "\"\r\n");
            this.queueType = queueType;
        }
    }

    /**
     * 设置延迟类型。
     */
    public void setDelayType(int delayType){
        if ((!String.valueOf(this.delayType).equals(String.valueOf(delayType)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("delay_type");
            this.UPDATED_INFO.append("delay_type:\"" + this.delayType+ "\"=>\"" + delayType + "\"\r\n");
            this.delayType = delayType;
        }
    }

    /**
     * 设置日志类型。
     */
    public void setLogLevel(int logLevel){
        if ((!String.valueOf(this.logLevel).equals(String.valueOf(logLevel)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("log_level");
            this.UPDATED_INFO.append("log_level:\"" + this.logLevel+ "\"=>\"" + logLevel + "\"\r\n");
            this.logLevel = logLevel;
        }
    }

    /**
     * 设置日志长度限制。
     */
    public void setLogLimitSize(int logLimitSize){
        if ((!String.valueOf(this.logLimitSize).equals(String.valueOf(logLimitSize)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("log_limit_size");
            this.UPDATED_INFO.append("log_limit_size:\"" + this.logLimitSize+ "\"=>\"" + logLimitSize + "\"\r\n");
            this.logLimitSize = logLimitSize;
        }
    }

    /**
     * 设置运行类型。
     */
    public void setRunType(int runType){
        if ((!String.valueOf(this.runType).equals(String.valueOf(runType)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("run_type");
            this.UPDATED_INFO.append("run_type:\"" + this.runType+ "\"=>\"" + runType + "\"\r\n");
            this.runType = runType;
        }
    }

    /**
     * 设置运行目标。
     */
    public void setRunTarget(String runTarget){
        if ((!String.valueOf(this.runTarget).equals(String.valueOf(runTarget)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("run_target");
            this.UPDATED_INFO.append("run_target:\"" + this.runTarget+ "\"=>\"" + runTarget + "\"\r\n");
            this.runTarget = runTarget;
        }
    }

    /**
     * 设置消费者的数量。
     */
    public void setConsumerNum(int consumerNum){
        if ((!String.valueOf(this.consumerNum).equals(String.valueOf(consumerNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("consumer_num");
            this.UPDATED_INFO.append("consumer_num:\"" + this.consumerNum+ "\"=>\"" + consumerNum + "\"\r\n");
            this.consumerNum = consumerNum;
        }
    }

    /**
     * 设置队列预取数量。
     */
    public void setPrefetchNum(int prefetchNum){
        if ((!String.valueOf(this.prefetchNum).equals(String.valueOf(prefetchNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("prefetch_num");
            this.UPDATED_INFO.append("prefetch_num:\"" + this.prefetchNum+ "\"=>\"" + prefetchNum + "\"\r\n");
            this.prefetchNum = prefetchNum;
        }
    }

    /**
     * 设置限速类型。
     */
    public void setRateLimitType(int rateLimitType){
        if ((!String.valueOf(this.rateLimitType).equals(String.valueOf(rateLimitType)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("rate_limit_type");
            this.UPDATED_INFO.append("rate_limit_type:\"" + this.rateLimitType+ "\"=>\"" + rateLimitType + "\"\r\n");
            this.rateLimitType = rateLimitType;
        }
    }

    /**
     * 设置流量限定数值，默认为60次。
     */
    public void setRateLimitValue(int rateLimitValue){
        if ((!String.valueOf(this.rateLimitValue).equals(String.valueOf(rateLimitValue)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("rate_limit_value");
            this.UPDATED_INFO.append("rate_limit_value:\"" + this.rateLimitValue+ "\"=>\"" + rateLimitValue + "\"\r\n");
            this.rateLimitValue = rateLimitValue;
        }
    }

    /**
     * 设置流量限定时间(S)，默认为60秒。
     */
    public void setRateLimitTime(int rateLimitTime){
        if ((!String.valueOf(this.rateLimitTime).equals(String.valueOf(rateLimitTime)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("rate_limit_time");
            this.UPDATED_INFO.append("rate_limit_time:\"" + this.rateLimitTime+ "\"=>\"" + rateLimitTime + "\"\r\n");
            this.rateLimitTime = rateLimitTime;
        }
    }

    /**
     * 设置当发生流量限制时，等待的秒数。
     */
    public void setRateLimitWait(int rateLimitWait){
        if ((!String.valueOf(this.rateLimitWait).equals(String.valueOf(rateLimitWait)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("rate_limit_wait");
            this.UPDATED_INFO.append("rate_limit_wait:\"" + this.rateLimitWait+ "\"=>\"" + rateLimitWait + "\"\r\n");
            this.rateLimitWait = rateLimitWait;
        }
    }

    /**
     * 设置超过流量限制重试次数。
     */
    public void setRetryTimesByOverrated(int retryTimesByOverrated){
        if ((!String.valueOf(this.retryTimesByOverrated).equals(String.valueOf(retryTimesByOverrated)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("retry_times_by_overrated");
            this.UPDATED_INFO.append("retry_times_by_overrated:\"" + this.retryTimesByOverrated+ "\"=>\"" + retryTimesByOverrated + "\"\r\n");
            this.retryTimesByOverrated = retryTimesByOverrated;
        }
    }

    /**
     * 设置对方接口错误重试次数。
     */
    public void setRetryTimesByPartner(int retryTimesByPartner){
        if ((!String.valueOf(this.retryTimesByPartner).equals(String.valueOf(retryTimesByPartner)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("retry_times_by_partner");
            this.UPDATED_INFO.append("retry_times_by_partner:\"" + this.retryTimesByPartner+ "\"=>\"" + retryTimesByPartner + "\"\r\n");
            this.retryTimesByPartner = retryTimesByPartner;
        }
    }

    /**
     * 设置最后统计时间。
     */
    public void setStatsDate(java.util.Date statsDate){
        if ((!String.valueOf(this.statsDate).equals(String.valueOf(statsDate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("stats_date");
            this.UPDATED_INFO.append("stats_date:\"" + this.statsDate+ "\"=>\"" + statsDate + "\"\r\n");
            this.statsDate = statsDate;
        }
    }

    /**
     * 设置统计运行次数。
     */
    public void setStatsRunNum(int statsRunNum){
        if ((!String.valueOf(this.statsRunNum).equals(String.valueOf(statsRunNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("stats_run_num");
            this.UPDATED_INFO.append("stats_run_num:\"" + this.statsRunNum+ "\"=>\"" + statsRunNum + "\"\r\n");
            this.statsRunNum = statsRunNum;
        }
    }

    /**
     * 设置统计运行失败次数。
     */
    public void setStatsFailNum(int statsFailNum){
        if ((!String.valueOf(this.statsFailNum).equals(String.valueOf(statsFailNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("stats_fail_num");
            this.UPDATED_INFO.append("stats_fail_num:\"" + this.statsFailNum+ "\"=>\"" + statsFailNum + "\"\r\n");
            this.statsFailNum = statsFailNum;
        }
    }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setStatsRunTime(long statsRunTime){
        if ((!String.valueOf(this.statsRunTime).equals(String.valueOf(statsRunTime)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("stats_run_time");
            this.UPDATED_INFO.append("stats_run_time:\"" + this.statsRunTime+ "\"=>\"" + statsRunTime + "\"\r\n");
            this.statsRunTime = statsRunTime;
        }
    }

    /**
     * 设置失败率。
     */
    public void setAlertFailRate(int alertFailRate){
        if ((!String.valueOf(this.alertFailRate).equals(String.valueOf(alertFailRate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_fail_rate");
            this.UPDATED_INFO.append("alert_fail_rate:\"" + this.alertFailRate+ "\"=>\"" + alertFailRate + "\"\r\n");
            this.alertFailRate = alertFailRate;
        }
    }

    /**
     * 设置接口失败率。
     */
    public void setAlertFailPartnerRate(int alertFailPartnerRate){
        if ((!String.valueOf(this.alertFailPartnerRate).equals(String.valueOf(alertFailPartnerRate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_fail_partner_rate");
            this.UPDATED_INFO.append("alert_fail_partner_rate:\"" + this.alertFailPartnerRate+ "\"=>\"" + alertFailPartnerRate + "\"\r\n");
            this.alertFailPartnerRate = alertFailPartnerRate;
        }
    }

    /**
     * 设置程序失败率。
     */
    public void setAlertFailProgramRate(int alertFailProgramRate){
        if ((!String.valueOf(this.alertFailProgramRate).equals(String.valueOf(alertFailProgramRate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_fail_program_rate");
            this.UPDATED_INFO.append("alert_fail_program_rate:\"" + this.alertFailProgramRate+ "\"=>\"" + alertFailProgramRate + "\"\r\n");
            this.alertFailProgramRate = alertFailProgramRate;
        }
    }

    /**
     * 设置配置失败率。
     */
    public void setAlertFailConfigRate(int alertFailConfigRate){
        if ((!String.valueOf(this.alertFailConfigRate).equals(String.valueOf(alertFailConfigRate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_fail_config_rate");
            this.UPDATED_INFO.append("alert_fail_config_rate:\"" + this.alertFailConfigRate+ "\"=>\"" + alertFailConfigRate + "\"\r\n");
            this.alertFailConfigRate = alertFailConfigRate;
        }
    }

    /**
     * 设置数据失败率。
     */
    public void setAlertFailDataRate(int alertFailDataRate){
        if ((!String.valueOf(this.alertFailDataRate).equals(String.valueOf(alertFailDataRate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_fail_data_rate");
            this.UPDATED_INFO.append("alert_fail_data_rate:\"" + this.alertFailDataRate+ "\"=>\"" + alertFailDataRate + "\"\r\n");
            this.alertFailDataRate = alertFailDataRate;
        }
    }

    /**
     * 设置队列长度限制。
     */
    public void setAlertQueueOversize(int alertQueueOversize){
        if ((!String.valueOf(this.alertQueueOversize).equals(String.valueOf(alertQueueOversize)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_queue_oversize");
            this.UPDATED_INFO.append("alert_queue_oversize:\"" + this.alertQueueOversize+ "\"=>\"" + alertQueueOversize + "\"\r\n");
            this.alertQueueOversize = alertQueueOversize;
        }
    }

    /**
     * 设置队列等待超时。
     */
    public void setAlertQueueTimeout(int alertQueueTimeout){
        if ((!String.valueOf(this.alertQueueTimeout).equals(String.valueOf(alertQueueTimeout)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_queue_timeout");
            this.UPDATED_INFO.append("alert_queue_timeout:\"" + this.alertQueueTimeout+ "\"=>\"" + alertQueueTimeout + "\"\r\n");
            this.alertQueueTimeout = alertQueueTimeout;
        }
    }

    /**
     * 设置等待超时。
     */
    public void setAlertWaitTimeout(int alertWaitTimeout){
        if ((!String.valueOf(this.alertWaitTimeout).equals(String.valueOf(alertWaitTimeout)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_wait_timeout");
            this.UPDATED_INFO.append("alert_wait_timeout:\"" + this.alertWaitTimeout+ "\"=>\"" + alertWaitTimeout + "\"\r\n");
            this.alertWaitTimeout = alertWaitTimeout;
        }
    }

    /**
     * 设置运行超时。
     */
    public void setAlertRunTimeout(int alertRunTimeout){
        if ((!String.valueOf(this.alertRunTimeout).equals(String.valueOf(alertRunTimeout)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("alert_run_timeout");
            this.UPDATED_INFO.append("alert_run_timeout:\"" + this.alertRunTimeout+ "\"=>\"" + alertRunTimeout + "\"\r\n");
            this.alertRunTimeout = alertRunTimeout;
        }
    }

    /**
     * 设置我方联系信息。
     */
    public void setTaskLinkOur(String taskLinkOur){
        if ((!String.valueOf(this.taskLinkOur).equals(String.valueOf(taskLinkOur)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_link_our");
            this.UPDATED_INFO.append("task_link_our:\"" + this.taskLinkOur+ "\"=>\"" + taskLinkOur + "\"\r\n");
            this.taskLinkOur = taskLinkOur;
        }
    }

    /**
     * 设置商户联系信息。
     */
    public void setTaskLinkMch(String taskLinkMch){
        if ((!String.valueOf(this.taskLinkMch).equals(String.valueOf(taskLinkMch)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_link_mch");
            this.UPDATED_INFO.append("task_link_mch:\"" + this.taskLinkMch+ "\"=>\"" + taskLinkMch + "\"\r\n");
            this.taskLinkMch = taskLinkMch;
        }
    }

    /**
     * 设置创建日期。
     */
    public void setCreateDate(java.util.Date createDate){
        if ((!String.valueOf(this.createDate).equals(String.valueOf(createDate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("create_date");
            this.UPDATED_INFO.append("create_date:\"" + this.createDate+ "\"=>\"" + createDate + "\"\r\n");
            this.createDate = createDate;
        }
    }

    /**
     * 设置最后修改日期。
     */
    public void setModifyDate(java.util.Date modifyDate){
        if ((!String.valueOf(this.modifyDate).equals(String.valueOf(modifyDate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("modify_date");
            this.UPDATED_INFO.append("modify_date:\"" + this.modifyDate+ "\"=>\"" + modifyDate + "\"\r\n");
            this.modifyDate = modifyDate;
        }
    }

    /**
     * 设置状态1正常，0暂停，-1标记删除。
     */
    public void setState(int state){
        if ((!String.valueOf(this.state).equals(String.valueOf(state)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("state");
            this.UPDATED_INFO.append("state:\"" + this.state+ "\"=>\"" + state + "\"\r\n");
            this.state = state;
        }
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
        sb.append("task_owner:\"" + this.taskOwner + "\"\r\n");
        sb.append("task_tag:\"" + this.taskTag + "\"\r\n");
        sb.append("queue_type:\"" + this.queueType + "\"\r\n");
        sb.append("delay_type:\"" + this.delayType + "\"\r\n");
        sb.append("log_level:\"" + this.logLevel + "\"\r\n");
        sb.append("log_limit_size:\"" + this.logLimitSize + "\"\r\n");
        sb.append("run_type:\"" + this.runType + "\"\r\n");
        sb.append("run_target:\"" + this.runTarget + "\"\r\n");
        sb.append("consumer_num:\"" + this.consumerNum + "\"\r\n");
        sb.append("prefetch_num:\"" + this.prefetchNum + "\"\r\n");
        sb.append("rate_limit_type:\"" + this.rateLimitType + "\"\r\n");
        sb.append("rate_limit_value:\"" + this.rateLimitValue + "\"\r\n");
        sb.append("rate_limit_time:\"" + this.rateLimitTime + "\"\r\n");
        sb.append("rate_limit_wait:\"" + this.rateLimitWait + "\"\r\n");
        sb.append("retry_times_by_overrated:\"" + this.retryTimesByOverrated + "\"\r\n");
        sb.append("retry_times_by_partner:\"" + this.retryTimesByPartner + "\"\r\n");
        sb.append("stats_date:\"" + this.statsDate + "\"\r\n");
        sb.append("stats_run_num:\"" + this.statsRunNum + "\"\r\n");
        sb.append("stats_fail_num:\"" + this.statsFailNum + "\"\r\n");
        sb.append("stats_run_time:\"" + this.statsRunTime + "\"\r\n");
        sb.append("alert_fail_rate:\"" + this.alertFailRate + "\"\r\n");
        sb.append("alert_fail_partner_rate:\"" + this.alertFailPartnerRate + "\"\r\n");
        sb.append("alert_fail_program_rate:\"" + this.alertFailProgramRate + "\"\r\n");
        sb.append("alert_fail_config_rate:\"" + this.alertFailConfigRate + "\"\r\n");
        sb.append("alert_fail_data_rate:\"" + this.alertFailDataRate + "\"\r\n");
        sb.append("alert_queue_oversize:\"" + this.alertQueueOversize + "\"\r\n");
        sb.append("alert_queue_timeout:\"" + this.alertQueueTimeout + "\"\r\n");
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