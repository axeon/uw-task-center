package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 队列任务统计信息列表查询参数。
 */
@Schema(title = "队列任务统计信息列表查询参数", description = "队列任务统计信息列表查询参数")
public class TaskRunnerStatsQueryParam extends PageQueryParam {

    /**
     * id。
     */
    @QueryMeta(expr = "id=?")
    @Schema(title = "id", description = "id")
    private Long id;
    /**
     * 任务配置id。
     */
    @QueryMeta(expr = "task_id=?")
    @Schema(title = "任务配置id", description = "任务配置id")
    private Long taskId;
    /**
     * 全部执行计数。
     */
    @QueryMeta(expr = "num_all=?")
    @Schema(title = "全部执行计数", description = "全部执行计数")
    private Integer numAll;
    /**
     * 全部执行计数范围。
     */
    @QueryMeta(expr = "num_all between ? and ?")
    @Schema(title = "全部执行计数范围", description = "全部执行计数范围")
    private Integer[] numAllRange;
    /**
     * 程序错误计数。
     */
    @QueryMeta(expr = "num_fail_program=?")
    @Schema(title = "程序错误计数", description = "程序错误计数")
    private Integer numFailProgram;
    /**
     * 程序错误计数范围。
     */
    @QueryMeta(expr = "num_fail_program between ? and ?")
    @Schema(title = "程序错误计数范围", description = "程序错误计数范围")
    private Integer[] numFailProgramRange;
    /**
     * 配置错误计数。
     */
    @QueryMeta(expr = "num_fail_config=?")
    @Schema(title = "配置错误计数", description = "配置错误计数")
    private Integer numFailConfig;
    /**
     * 配置错误计数范围。
     */
    @QueryMeta(expr = "num_fail_config between ? and ?")
    @Schema(title = "配置错误计数范围", description = "配置错误计数范围")
    private Integer[] numFailConfigRange;
    /**
     * 数据错误计数。
     */
    @QueryMeta(expr = "num_fail_data=?")
    @Schema(title = "数据错误计数", description = "数据错误计数")
    private Integer numFailData;
    /**
     * 数据错误计数范围。
     */
    @QueryMeta(expr = "num_fail_data between ? and ?")
    @Schema(title = "数据错误计数范围", description = "数据错误计数范围")
    private Integer[] numFailDataRange;
    /**
     * 对方错误计数。
     */
    @QueryMeta(expr = "num_fail_partner=?")
    @Schema(title = "对方错误计数", description = "对方错误计数")
    private Integer numFailPartner;
    /**
     * 对方错误计数范围。
     */
    @QueryMeta(expr = "num_fail_partner between ? and ?")
    @Schema(title = "对方错误计数范围", description = "对方错误计数范围")
    private Integer[] numFailPartnerRange;
    /**
     * 队列等待时间。
     */
    @QueryMeta(expr = "time_wait_queue=?")
    @Schema(title = "队列等待时间", description = "队列等待时间")
    private Integer timeWaitQueue;
    /**
     * 队列等待时间范围。
     */
    @QueryMeta(expr = "time_wait_queue between ? and ?")
    @Schema(title = "队列等待时间范围", description = "队列等待时间范围")
    private Integer[] timeWaitQueueRange;
    /**
     * 超时等待时间。
     */
    @QueryMeta(expr = "time_wait_delay=?")
    @Schema(title = "超时等待时间", description = "超时等待时间")
    private Integer timeWaitDelay;
    /**
     * 超时等待时间范围。
     */
    @QueryMeta(expr = "time_wait_delay between ? and ?")
    @Schema(title = "超时等待时间范围", description = "超时等待时间范围")
    private Integer[] timeWaitDelayRange;
    /**
     * 运行时间。
     */
    @QueryMeta(expr = "time_run=?")
    @Schema(title = "运行时间", description = "运行时间")
    private Integer timeRun;
    /**
     * 运行时间范围。
     */
    @QueryMeta(expr = "time_run between ? and ?")
    @Schema(title = "运行时间范围", description = "运行时间范围")
    private Integer[] timeRunRange;
    /**
     * 队列长度。
     */
    @QueryMeta(expr = "queue_size=?")
    @Schema(title = "队列长度", description = "队列长度")
    private Integer queueSize;
    /**
     * 队列长度范围。
     */
    @QueryMeta(expr = "queue_size between ? and ?")
    @Schema(title = "队列长度范围", description = "队列长度范围")
    private Integer[] queueSizeRange;
    /**
     * 消费者数量。
     */
    @QueryMeta(expr = "consumer_num=?")
    @Schema(title = "消费者数量", description = "消费者数量")
    private Integer consumerNum;
    /**
     * 消费者数量范围。
     */
    @QueryMeta(expr = "consumer_num between ? and ?")
    @Schema(title = "消费者数量范围", description = "消费者数量范围")
    private Integer[] consumerNumRange;
    /**
     * 创建时间范围。
     */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title = "创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

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
            put( "taskId", "task_id" );
            put( "numAll", "num_all" );
            put( "numFailProgram", "num_fail_program" );
            put( "numFailConfig", "num_fail_config" );
            put( "numFailData", "num_fail_data" );
            put( "numFailPartner", "num_fail_partner" );
            put( "timeWaitQueue", "time_wait_queue" );
            put( "timeWaitDelay", "time_wait_delay" );
            put( "timeRun", "time_run" );
            put( "queueSize", "queue_size" );
            put( "consumerNum", "consumer_num" );
            put( "createDate", "create_date" );
        }};
    }

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
    public TaskRunnerStatsQueryParam id(Long id) {
        setId( id );
        return this;
    }

    /**
     * 获取任务配置id。
     */
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * 设置任务配置id。
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * 设置任务配置id链式调用。
     */
    public TaskRunnerStatsQueryParam taskId(Long taskId) {
        setTaskId( taskId );
        return this;
    }

    /**
     * 获取全部执行计数。
     */
    public Integer getNumAll() {
        return this.numAll;
    }

    /**
     * 设置全部执行计数。
     */
    public void setNumAll(Integer numAll) {
        this.numAll = numAll;
    }

    /**
     * 设置全部执行计数链式调用。
     */
    public TaskRunnerStatsQueryParam numAll(Integer numAll) {
        setNumAll( numAll );
        return this;
    }

    /**
     * 获取全部执行计数范围。
     */
    public Integer[] getNumAllRange() {
        return this.numAllRange;
    }

    /**
     * 设置全部执行计数范围。
     */
    public void setNumAllRange(Integer[] numAllRange) {
        this.numAllRange = numAllRange;
    }

    /**
     * 设置全部执行计数范围链式调用。
     */
    public TaskRunnerStatsQueryParam numAllRange(Integer[] numAllRange) {
        setNumAllRange( numAllRange );
        return this;
    }

    /**
     * 获取程序错误计数。
     */
    public Integer getNumFailProgram() {
        return this.numFailProgram;
    }

    /**
     * 设置程序错误计数。
     */
    public void setNumFailProgram(Integer numFailProgram) {
        this.numFailProgram = numFailProgram;
    }

    /**
     * 设置程序错误计数链式调用。
     */
    public TaskRunnerStatsQueryParam numFailProgram(Integer numFailProgram) {
        setNumFailProgram( numFailProgram );
        return this;
    }

    /**
     * 获取程序错误计数范围。
     */
    public Integer[] getNumFailProgramRange() {
        return this.numFailProgramRange;
    }

    /**
     * 设置程序错误计数范围。
     */
    public void setNumFailProgramRange(Integer[] numFailProgramRange) {
        this.numFailProgramRange = numFailProgramRange;
    }

    /**
     * 设置程序错误计数范围链式调用。
     */
    public TaskRunnerStatsQueryParam numFailProgramRange(Integer[] numFailProgramRange) {
        setNumFailProgramRange( numFailProgramRange );
        return this;
    }

    /**
     * 获取配置错误计数。
     */
    public Integer getNumFailConfig() {
        return this.numFailConfig;
    }

    /**
     * 设置配置错误计数。
     */
    public void setNumFailConfig(Integer numFailConfig) {
        this.numFailConfig = numFailConfig;
    }

    /**
     * 设置配置错误计数链式调用。
     */
    public TaskRunnerStatsQueryParam numFailConfig(Integer numFailConfig) {
        setNumFailConfig( numFailConfig );
        return this;
    }

    /**
     * 获取配置错误计数范围。
     */
    public Integer[] getNumFailConfigRange() {
        return this.numFailConfigRange;
    }

    /**
     * 设置配置错误计数范围。
     */
    public void setNumFailConfigRange(Integer[] numFailConfigRange) {
        this.numFailConfigRange = numFailConfigRange;
    }

    /**
     * 设置配置错误计数范围链式调用。
     */
    public TaskRunnerStatsQueryParam numFailConfigRange(Integer[] numFailConfigRange) {
        setNumFailConfigRange( numFailConfigRange );
        return this;
    }

    /**
     * 获取数据错误计数。
     */
    public Integer getNumFailData() {
        return this.numFailData;
    }

    /**
     * 设置数据错误计数。
     */
    public void setNumFailData(Integer numFailData) {
        this.numFailData = numFailData;
    }

    /**
     * 设置数据错误计数链式调用。
     */
    public TaskRunnerStatsQueryParam numFailData(Integer numFailData) {
        setNumFailData( numFailData );
        return this;
    }

    /**
     * 获取数据错误计数范围。
     */
    public Integer[] getNumFailDataRange() {
        return this.numFailDataRange;
    }

    /**
     * 设置数据错误计数范围。
     */
    public void setNumFailDataRange(Integer[] numFailDataRange) {
        this.numFailDataRange = numFailDataRange;
    }

    /**
     * 设置数据错误计数范围链式调用。
     */
    public TaskRunnerStatsQueryParam numFailDataRange(Integer[] numFailDataRange) {
        setNumFailDataRange( numFailDataRange );
        return this;
    }

    /**
     * 获取对方错误计数。
     */
    public Integer getNumFailPartner() {
        return this.numFailPartner;
    }

    /**
     * 设置对方错误计数。
     */
    public void setNumFailPartner(Integer numFailPartner) {
        this.numFailPartner = numFailPartner;
    }

    /**
     * 设置对方错误计数链式调用。
     */
    public TaskRunnerStatsQueryParam numFailPartner(Integer numFailPartner) {
        setNumFailPartner( numFailPartner );
        return this;
    }

    /**
     * 获取对方错误计数范围。
     */
    public Integer[] getNumFailPartnerRange() {
        return this.numFailPartnerRange;
    }

    /**
     * 设置对方错误计数范围。
     */
    public void setNumFailPartnerRange(Integer[] numFailPartnerRange) {
        this.numFailPartnerRange = numFailPartnerRange;
    }

    /**
     * 设置对方错误计数范围链式调用。
     */
    public TaskRunnerStatsQueryParam numFailPartnerRange(Integer[] numFailPartnerRange) {
        setNumFailPartnerRange( numFailPartnerRange );
        return this;
    }

    /**
     * 获取队列等待时间。
     */
    public Integer getTimeWaitQueue() {
        return this.timeWaitQueue;
    }

    /**
     * 设置队列等待时间。
     */
    public void setTimeWaitQueue(Integer timeWaitQueue) {
        this.timeWaitQueue = timeWaitQueue;
    }

    /**
     * 设置队列等待时间链式调用。
     */
    public TaskRunnerStatsQueryParam timeWaitQueue(Integer timeWaitQueue) {
        setTimeWaitQueue( timeWaitQueue );
        return this;
    }

    /**
     * 获取队列等待时间范围。
     */
    public Integer[] getTimeWaitQueueRange() {
        return this.timeWaitQueueRange;
    }

    /**
     * 设置队列等待时间范围。
     */
    public void setTimeWaitQueueRange(Integer[] timeWaitQueueRange) {
        this.timeWaitQueueRange = timeWaitQueueRange;
    }

    /**
     * 设置队列等待时间范围链式调用。
     */
    public TaskRunnerStatsQueryParam timeWaitQueueRange(Integer[] timeWaitQueueRange) {
        setTimeWaitQueueRange( timeWaitQueueRange );
        return this;
    }

    /**
     * 获取超时等待时间。
     */
    public Integer getTimeWaitDelay() {
        return this.timeWaitDelay;
    }

    /**
     * 设置超时等待时间。
     */
    public void setTimeWaitDelay(Integer timeWaitDelay) {
        this.timeWaitDelay = timeWaitDelay;
    }

    /**
     * 设置超时等待时间链式调用。
     */
    public TaskRunnerStatsQueryParam timeWaitDelay(Integer timeWaitDelay) {
        setTimeWaitDelay( timeWaitDelay );
        return this;
    }

    /**
     * 获取超时等待时间范围。
     */
    public Integer[] getTimeWaitDelayRange() {
        return this.timeWaitDelayRange;
    }

    /**
     * 设置超时等待时间范围。
     */
    public void setTimeWaitDelayRange(Integer[] timeWaitDelayRange) {
        this.timeWaitDelayRange = timeWaitDelayRange;
    }

    /**
     * 设置超时等待时间范围链式调用。
     */
    public TaskRunnerStatsQueryParam timeWaitDelayRange(Integer[] timeWaitDelayRange) {
        setTimeWaitDelayRange( timeWaitDelayRange );
        return this;
    }

    /**
     * 获取运行时间。
     */
    public Integer getTimeRun() {
        return this.timeRun;
    }

    /**
     * 设置运行时间。
     */
    public void setTimeRun(Integer timeRun) {
        this.timeRun = timeRun;
    }

    /**
     * 设置运行时间链式调用。
     */
    public TaskRunnerStatsQueryParam timeRun(Integer timeRun) {
        setTimeRun( timeRun );
        return this;
    }

    /**
     * 获取运行时间范围。
     */
    public Integer[] getTimeRunRange() {
        return this.timeRunRange;
    }

    /**
     * 设置运行时间范围。
     */
    public void setTimeRunRange(Integer[] timeRunRange) {
        this.timeRunRange = timeRunRange;
    }

    /**
     * 设置运行时间范围链式调用。
     */
    public TaskRunnerStatsQueryParam timeRunRange(Integer[] timeRunRange) {
        setTimeRunRange( timeRunRange );
        return this;
    }

    /**
     * 获取队列长度。
     */
    public Integer getQueueSize() {
        return this.queueSize;
    }

    /**
     * 设置队列长度。
     */
    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    /**
     * 设置队列长度链式调用。
     */
    public TaskRunnerStatsQueryParam queueSize(Integer queueSize) {
        setQueueSize( queueSize );
        return this;
    }

    /**
     * 获取队列长度范围。
     */
    public Integer[] getQueueSizeRange() {
        return this.queueSizeRange;
    }

    /**
     * 设置队列长度范围。
     */
    public void setQueueSizeRange(Integer[] queueSizeRange) {
        this.queueSizeRange = queueSizeRange;
    }

    /**
     * 设置队列长度范围链式调用。
     */
    public TaskRunnerStatsQueryParam queueSizeRange(Integer[] queueSizeRange) {
        setQueueSizeRange( queueSizeRange );
        return this;
    }

    /**
     * 获取消费者数量。
     */
    public Integer getConsumerNum() {
        return this.consumerNum;
    }

    /**
     * 设置消费者数量。
     */
    public void setConsumerNum(Integer consumerNum) {
        this.consumerNum = consumerNum;
    }

    /**
     * 设置消费者数量链式调用。
     */
    public TaskRunnerStatsQueryParam consumerNum(Integer consumerNum) {
        setConsumerNum( consumerNum );
        return this;
    }

    /**
     * 获取消费者数量范围。
     */
    public Integer[] getConsumerNumRange() {
        return this.consumerNumRange;
    }

    /**
     * 设置消费者数量范围。
     */
    public void setConsumerNumRange(Integer[] consumerNumRange) {
        this.consumerNumRange = consumerNumRange;
    }

    /**
     * 设置消费者数量范围链式调用。
     */
    public TaskRunnerStatsQueryParam consumerNumRange(Integer[] consumerNumRange) {
        setConsumerNumRange( consumerNumRange );
        return this;
    }

    /**
     * 获取创建时间范围。
     */
    public Date[] getCreateDateRange() {
        return this.createDateRange;
    }

    /**
     * 设置创建时间范围。
     */
    public void setCreateDateRange(Date[] createDateRange) {
        this.createDateRange = createDateRange;
    }

    /**
     * 设置创建时间范围链式调用。
     */
    public TaskRunnerStatsQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange( createDateRange );
        return this;
    }


}