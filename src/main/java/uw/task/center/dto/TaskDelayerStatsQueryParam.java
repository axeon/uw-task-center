package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.dto.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.Map;

/**
* 延迟任务统计信息列表查询参数。
*/
@Schema(title = "延迟任务统计信息列表查询参数", description = "延迟任务统计信息列表查询参数")
public class TaskDelayerStatsQueryParam extends PageQueryParam{


    /**
     * 允许排序的属性。
     * key:排序名 value:排序字段
     *
     */
    private static final Map<String, String> ALLOWED_SORT_PROPERTY = Map.ofEntries(
        Map.entry( "id", "id" ),
        Map.entry( "taskId", "task_id" ),
        Map.entry( "createDate", "create_date" )
        );

    /**
     * 获取允许排序的属性。
     *
     */
    @Override
    public Map<String, String> ALLOWED_SORT_PROPERTY() {
        return ALLOWED_SORT_PROPERTY;
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
    * 任务配置id。
    */
    @QueryMeta(expr = "task_id=?")
    @Schema(title="任务配置id", description = "任务配置id")
    private Long taskId;
	
    /**
    * 全部执行计数。
    */
    @QueryMeta(expr = "num_all=?")
    @Schema(title="全部执行计数", description = "全部执行计数")
    private Integer numAll;

    /**
    * 全部执行计数范围。
    */
    @QueryMeta(expr = "num_all between ? and ?")
    @Schema(title="全部执行计数范围", description = "全部执行计数范围")
    private Integer[] numAllRange;
	
    /**
    * 程序错误计数。
    */
    @QueryMeta(expr = "num_fail_program=?")
    @Schema(title="程序错误计数", description = "程序错误计数")
    private Integer numFailProgram;

    /**
    * 程序错误计数范围。
    */
    @QueryMeta(expr = "num_fail_program between ? and ?")
    @Schema(title="程序错误计数范围", description = "程序错误计数范围")
    private Integer[] numFailProgramRange;
	
    /**
    * 配置错误计数(连续限速超限放弃)。
    */
    @QueryMeta(expr = "num_fail_config=?")
    @Schema(title="配置错误计数(连续限速超限放弃)", description = "配置错误计数(连续限速超限放弃)")
    private Integer numFailConfig;

    /**
    * 配置错误计数(连续限速超限放弃)范围。
    */
    @QueryMeta(expr = "num_fail_config between ? and ?")
    @Schema(title="配置错误计数(连续限速超限放弃)范围", description = "配置错误计数(连续限速超限放弃)范围")
    private Integer[] numFailConfigRange;
	
    /**
    * 数据错误计数。
    */
    @QueryMeta(expr = "num_fail_data=?")
    @Schema(title="数据错误计数", description = "数据错误计数")
    private Integer numFailData;

    /**
    * 数据错误计数范围。
    */
    @QueryMeta(expr = "num_fail_data between ? and ?")
    @Schema(title="数据错误计数范围", description = "数据错误计数范围")
    private Integer[] numFailDataRange;
	
    /**
    * 对方错误计数。
    */
    @QueryMeta(expr = "num_fail_partner=?")
    @Schema(title="对方错误计数", description = "对方错误计数")
    private Integer numFailPartner;

    /**
    * 对方错误计数范围。
    */
    @QueryMeta(expr = "num_fail_partner between ? and ?")
    @Schema(title="对方错误计数范围", description = "对方错误计数范围")
    private Integer[] numFailPartnerRange;
	
    /**
    * 实际延迟等待(runAt到consumeDate毫秒)。
    */
    @QueryMeta(expr = "time_wait=?")
    @Schema(title="实际延迟等待(runAt到consumeDate毫秒)", description = "实际延迟等待(runAt到consumeDate毫秒)")
    private Integer timeWaitDelay;

    /**
    * 实际延迟等待(runAt到consumeDate毫秒)范围。
    */
    @QueryMeta(expr = "time_wait between ? and ?")
    @Schema(title="实际延迟等待(runAt到consumeDate毫秒)范围", description = "实际延迟等待(runAt到consumeDate毫秒)范围")
    private Integer[] timeWaitDelayRange;
	
    /**
    * 运行时间(毫秒)。
    */
    @QueryMeta(expr = "time_run=?")
    @Schema(title="运行时间(毫秒)", description = "运行时间(毫秒)")
    private Integer timeRun;

    /**
    * 运行时间(毫秒)范围。
    */
    @QueryMeta(expr = "time_run between ? and ?")
    @Schema(title="运行时间(毫秒)范围", description = "运行时间(毫秒)范围")
    private Integer[] timeRunRange;
	
    /**
    * 队列积压消息数。
    */
    @QueryMeta(expr = "queue_size=?")
    @Schema(title="队列积压消息数", description = "队列积压消息数")
    private Integer queueSize;

    /**
    * 队列积压消息数范围。
    */
    @QueryMeta(expr = "queue_size between ? and ?")
    @Schema(title="队列积压消息数范围", description = "队列积压消息数范围")
    private Integer[] queueSizeRange;
	
    /**
    * 执行线程数。
    */
    @QueryMeta(expr = "consumer_num=?")
    @Schema(title="执行线程数", description = "执行线程数")
    private Integer consumerNum;

    /**
    * 执行线程数范围。
    */
    @QueryMeta(expr = "consumer_num between ? and ?")
    @Schema(title="执行线程数范围", description = "执行线程数范围")
    private Integer[] consumerNumRange;
	
    /**
    * 创建时间范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;


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
    public TaskDelayerStatsQueryParam id(Long id) {
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
    public TaskDelayerStatsQueryParam ids(Long[] ids) {
        setIds(ids);
        return this;
    }

    /**
    * 获取任务配置id。
    */
    public Long getTaskId(){
        return this.taskId;
    }

    /**
    * 设置任务配置id。
    */
    public void setTaskId(Long taskId){
        this.taskId = taskId;
    }
	
    /**
    * 设置任务配置id链式调用。
    */
	public TaskDelayerStatsQueryParam taskId(Long taskId){
        setTaskId(taskId);
        return this;
    }
	
    /**
    * 获取全部执行计数。
    */
    public Integer getNumAll(){
        return this.numAll;
    }

    /**
    * 设置全部执行计数。
    */
    public void setNumAll(Integer numAll){
        this.numAll = numAll;
    }
	
    /**
    * 设置全部执行计数链式调用。
    */
    public TaskDelayerStatsQueryParam numAll(Integer numAll){
        setNumAll(numAll);
        return this;
    }

    /**
    * 获取全部执行计数范围。
    */
    public Integer[] getNumAllRange(){
        return this.numAllRange;
    }

    /**
    * 设置全部执行计数范围。
    */
    public void setNumAllRange(Integer[] numAllRange){
        this.numAllRange = numAllRange;
    }
	
    /**
    * 设置全部执行计数范围链式调用。
    */
    public TaskDelayerStatsQueryParam numAllRange(Integer[] numAllRange){
        setNumAllRange(numAllRange);
        return this;
    }
	
    /**
    * 获取程序错误计数。
    */
    public Integer getNumFailProgram(){
        return this.numFailProgram;
    }

    /**
    * 设置程序错误计数。
    */
    public void setNumFailProgram(Integer numFailProgram){
        this.numFailProgram = numFailProgram;
    }
	
    /**
    * 设置程序错误计数链式调用。
    */
    public TaskDelayerStatsQueryParam numFailProgram(Integer numFailProgram){
        setNumFailProgram(numFailProgram);
        return this;
    }

    /**
    * 获取程序错误计数范围。
    */
    public Integer[] getNumFailProgramRange(){
        return this.numFailProgramRange;
    }

    /**
    * 设置程序错误计数范围。
    */
    public void setNumFailProgramRange(Integer[] numFailProgramRange){
        this.numFailProgramRange = numFailProgramRange;
    }
	
    /**
    * 设置程序错误计数范围链式调用。
    */
    public TaskDelayerStatsQueryParam numFailProgramRange(Integer[] numFailProgramRange){
        setNumFailProgramRange(numFailProgramRange);
        return this;
    }
	
    /**
    * 获取配置错误计数(连续限速超限放弃)。
    */
    public Integer getNumFailConfig(){
        return this.numFailConfig;
    }

    /**
    * 设置配置错误计数(连续限速超限放弃)。
    */
    public void setNumFailConfig(Integer numFailConfig){
        this.numFailConfig = numFailConfig;
    }
	
    /**
    * 设置配置错误计数(连续限速超限放弃)链式调用。
    */
    public TaskDelayerStatsQueryParam numFailConfig(Integer numFailConfig){
        setNumFailConfig(numFailConfig);
        return this;
    }

    /**
    * 获取配置错误计数(连续限速超限放弃)范围。
    */
    public Integer[] getNumFailConfigRange(){
        return this.numFailConfigRange;
    }

    /**
    * 设置配置错误计数(连续限速超限放弃)范围。
    */
    public void setNumFailConfigRange(Integer[] numFailConfigRange){
        this.numFailConfigRange = numFailConfigRange;
    }
	
    /**
    * 设置配置错误计数(连续限速超限放弃)范围链式调用。
    */
    public TaskDelayerStatsQueryParam numFailConfigRange(Integer[] numFailConfigRange){
        setNumFailConfigRange(numFailConfigRange);
        return this;
    }
	
    /**
    * 获取数据错误计数。
    */
    public Integer getNumFailData(){
        return this.numFailData;
    }

    /**
    * 设置数据错误计数。
    */
    public void setNumFailData(Integer numFailData){
        this.numFailData = numFailData;
    }
	
    /**
    * 设置数据错误计数链式调用。
    */
    public TaskDelayerStatsQueryParam numFailData(Integer numFailData){
        setNumFailData(numFailData);
        return this;
    }

    /**
    * 获取数据错误计数范围。
    */
    public Integer[] getNumFailDataRange(){
        return this.numFailDataRange;
    }

    /**
    * 设置数据错误计数范围。
    */
    public void setNumFailDataRange(Integer[] numFailDataRange){
        this.numFailDataRange = numFailDataRange;
    }
	
    /**
    * 设置数据错误计数范围链式调用。
    */
    public TaskDelayerStatsQueryParam numFailDataRange(Integer[] numFailDataRange){
        setNumFailDataRange(numFailDataRange);
        return this;
    }
	
    /**
    * 获取对方错误计数。
    */
    public Integer getNumFailPartner(){
        return this.numFailPartner;
    }

    /**
    * 设置对方错误计数。
    */
    public void setNumFailPartner(Integer numFailPartner){
        this.numFailPartner = numFailPartner;
    }
	
    /**
    * 设置对方错误计数链式调用。
    */
    public TaskDelayerStatsQueryParam numFailPartner(Integer numFailPartner){
        setNumFailPartner(numFailPartner);
        return this;
    }

    /**
    * 获取对方错误计数范围。
    */
    public Integer[] getNumFailPartnerRange(){
        return this.numFailPartnerRange;
    }

    /**
    * 设置对方错误计数范围。
    */
    public void setNumFailPartnerRange(Integer[] numFailPartnerRange){
        this.numFailPartnerRange = numFailPartnerRange;
    }
	
    /**
    * 设置对方错误计数范围链式调用。
    */
    public TaskDelayerStatsQueryParam numFailPartnerRange(Integer[] numFailPartnerRange){
        setNumFailPartnerRange(numFailPartnerRange);
        return this;
    }
	
    /**
    * 获取实际延迟等待(runAt到consumeDate毫秒)。
    */
    public Integer getTimeWaitDelay(){
        return this.timeWaitDelay;
    }

    /**
    * 设置实际延迟等待(runAt到consumeDate毫秒)。
    */
    public void setTimeWaitDelay(Integer timeWaitDelay){
        this.timeWaitDelay = timeWaitDelay;
    }
	
    /**
    * 设置实际延迟等待(runAt到consumeDate毫秒)链式调用。
    */
    public TaskDelayerStatsQueryParam timeWaitDelay(Integer timeWaitDelay){
        setTimeWaitDelay(timeWaitDelay);
        return this;
    }

    /**
    * 获取实际延迟等待(runAt到consumeDate毫秒)范围。
    */
    public Integer[] getTimeWaitDelayRange(){
        return this.timeWaitDelayRange;
    }

    /**
    * 设置实际延迟等待(runAt到consumeDate毫秒)范围。
    */
    public void setTimeWaitDelayRange(Integer[] timeWaitDelayRange){
        this.timeWaitDelayRange = timeWaitDelayRange;
    }
	
    /**
    * 设置实际延迟等待(runAt到consumeDate毫秒)范围链式调用。
    */
    public TaskDelayerStatsQueryParam timeWaitDelayRange(Integer[] timeWaitDelayRange){
        setTimeWaitDelayRange(timeWaitDelayRange);
        return this;
    }
	
    /**
    * 获取运行时间(毫秒)。
    */
    public Integer getTimeRun(){
        return this.timeRun;
    }

    /**
    * 设置运行时间(毫秒)。
    */
    public void setTimeRun(Integer timeRun){
        this.timeRun = timeRun;
    }
	
    /**
    * 设置运行时间(毫秒)链式调用。
    */
    public TaskDelayerStatsQueryParam timeRun(Integer timeRun){
        setTimeRun(timeRun);
        return this;
    }

    /**
    * 获取运行时间(毫秒)范围。
    */
    public Integer[] getTimeRunRange(){
        return this.timeRunRange;
    }

    /**
    * 设置运行时间(毫秒)范围。
    */
    public void setTimeRunRange(Integer[] timeRunRange){
        this.timeRunRange = timeRunRange;
    }
	
    /**
    * 设置运行时间(毫秒)范围链式调用。
    */
    public TaskDelayerStatsQueryParam timeRunRange(Integer[] timeRunRange){
        setTimeRunRange(timeRunRange);
        return this;
    }
	
    /**
    * 获取队列积压消息数。
    */
    public Integer getQueueSize(){
        return this.queueSize;
    }

    /**
    * 设置队列积压消息数。
    */
    public void setQueueSize(Integer queueSize){
        this.queueSize = queueSize;
    }
	
    /**
    * 设置队列积压消息数链式调用。
    */
    public TaskDelayerStatsQueryParam queueSize(Integer queueSize){
        setQueueSize(queueSize);
        return this;
    }

    /**
    * 获取队列积压消息数范围。
    */
    public Integer[] getQueueSizeRange(){
        return this.queueSizeRange;
    }

    /**
    * 设置队列积压消息数范围。
    */
    public void setQueueSizeRange(Integer[] queueSizeRange){
        this.queueSizeRange = queueSizeRange;
    }
	
    /**
    * 设置队列积压消息数范围链式调用。
    */
    public TaskDelayerStatsQueryParam queueSizeRange(Integer[] queueSizeRange){
        setQueueSizeRange(queueSizeRange);
        return this;
    }
	
    /**
    * 获取执行线程数。
    */
    public Integer getConsumerNum(){
        return this.consumerNum;
    }

    /**
    * 设置执行线程数。
    */
    public void setConsumerNum(Integer consumerNum){
        this.consumerNum = consumerNum;
    }
	
    /**
    * 设置执行线程数链式调用。
    */
    public TaskDelayerStatsQueryParam consumerNum(Integer consumerNum){
        setConsumerNum(consumerNum);
        return this;
    }

    /**
    * 获取执行线程数范围。
    */
    public Integer[] getConsumerNumRange(){
        return this.consumerNumRange;
    }

    /**
    * 设置执行线程数范围。
    */
    public void setConsumerNumRange(Integer[] consumerNumRange){
        this.consumerNumRange = consumerNumRange;
    }
	
    /**
    * 设置执行线程数范围链式调用。
    */
    public TaskDelayerStatsQueryParam consumerNumRange(Integer[] consumerNumRange){
        setConsumerNumRange(consumerNumRange);
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
    public TaskDelayerStatsQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange(createDateRange);
        return this;
    }
	

}