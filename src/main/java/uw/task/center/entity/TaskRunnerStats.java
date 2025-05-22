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
 * TaskRunnerStats实体类
 * 队列任务统计信息
 *
 * @author axeon
 */
@TableMeta(tableName="task_runner_stats",tableType="table")
@Schema(title = "队列任务统计信息", description = "队列任务统计信息")
public class TaskRunnerStats implements DataEntity,Serializable{


    /**
     * id
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "id", description = "id", maxLength=19, nullable=false )
    private long id;

    /**
     * 任务配置id
     */
    @ColumnMeta(columnName="task_id", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "任务配置id", description = "任务配置id", maxLength=19, nullable=true )
    private long taskId;

    /**
     * 全部执行计数
     */
    @ColumnMeta(columnName="num_all", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "全部执行计数", description = "全部执行计数", maxLength=10, nullable=true )
    private int numAll;

    /**
     * 程序错误计数
     */
    @ColumnMeta(columnName="num_fail_program", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序错误计数", description = "程序错误计数", maxLength=10, nullable=true )
    private int numFailProgram;

    /**
     * 配置错误计数
     */
    @ColumnMeta(columnName="num_fail_config", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "配置错误计数", description = "配置错误计数", maxLength=10, nullable=true )
    private int numFailConfig;

    /**
     * 数据错误计数
     */
    @ColumnMeta(columnName="num_fail_data", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "数据错误计数", description = "数据错误计数", maxLength=10, nullable=true )
    private int numFailData;

    /**
     * 对方错误计数
     */
    @ColumnMeta(columnName="num_fail_partner", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "对方错误计数", description = "对方错误计数", maxLength=10, nullable=true )
    private int numFailPartner;

    /**
     * 队列等待时间
     */
    @ColumnMeta(columnName="time_wait_queue", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列等待时间", description = "队列等待时间", maxLength=10, nullable=true )
    private int timeWaitQueue;

    /**
     * 超时等待时间
     */
    @ColumnMeta(columnName="time_wait_delay", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "超时等待时间", description = "超时等待时间", maxLength=10, nullable=true )
    private int timeWaitDelay;

    /**
     * 运行时间
     */
    @ColumnMeta(columnName="time_run", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行时间", description = "运行时间", maxLength=10, nullable=true )
    private int timeRun;

    /**
     * 队列长度
     */
    @ColumnMeta(columnName="queue_size", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列长度", description = "队列长度", maxLength=10, nullable=true )
    private int queueSize;

    /**
     * 消费者数量
     */
    @ColumnMeta(columnName="consumer_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "消费者数量", description = "消费者数量", maxLength=10, nullable=true )
    private int consumerNum;

    /**
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间", maxLength=23, nullable=true )
    private java.util.Date createDate;

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
        return "task_runner_stats";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "队列任务统计信息";
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
     * 获取任务配置id。
     */
    public long getTaskId(){
        return this.taskId;
    }

    /**
     * 获取全部执行计数。
     */
    public int getNumAll(){
        return this.numAll;
    }

    /**
     * 获取程序错误计数。
     */
    public int getNumFailProgram(){
        return this.numFailProgram;
    }

    /**
     * 获取配置错误计数。
     */
    public int getNumFailConfig(){
        return this.numFailConfig;
    }

    /**
     * 获取数据错误计数。
     */
    public int getNumFailData(){
        return this.numFailData;
    }

    /**
     * 获取对方错误计数。
     */
    public int getNumFailPartner(){
        return this.numFailPartner;
    }

    /**
     * 获取队列等待时间。
     */
    public int getTimeWaitQueue(){
        return this.timeWaitQueue;
    }

    /**
     * 获取超时等待时间。
     */
    public int getTimeWaitDelay(){
        return this.timeWaitDelay;
    }

    /**
     * 获取运行时间。
     */
    public int getTimeRun(){
        return this.timeRun;
    }

    /**
     * 获取队列长度。
     */
    public int getQueueSize(){
        return this.queueSize;
    }

    /**
     * 获取消费者数量。
     */
    public int getConsumerNum(){
        return this.consumerNum;
    }

    /**
     * 获取创建时间。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
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
    public TaskRunnerStats id(long id){
        setId(id);
        return this;
    }

    /**
     * 设置任务配置id。
     */
    public void setTaskId(long taskId){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskId", this.taskId, taskId, !_IS_LOADED );
        this.taskId = taskId;
    }

    /**
     *  设置任务配置id链式调用。
     */
    public TaskRunnerStats taskId(long taskId){
        setTaskId(taskId);
        return this;
    }

    /**
     * 设置全部执行计数。
     */
    public void setNumAll(int numAll){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numAll", this.numAll, numAll, !_IS_LOADED );
        this.numAll = numAll;
    }

    /**
     *  设置全部执行计数链式调用。
     */
    public TaskRunnerStats numAll(int numAll){
        setNumAll(numAll);
        return this;
    }

    /**
     * 设置程序错误计数。
     */
    public void setNumFailProgram(int numFailProgram){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailProgram", this.numFailProgram, numFailProgram, !_IS_LOADED );
        this.numFailProgram = numFailProgram;
    }

    /**
     *  设置程序错误计数链式调用。
     */
    public TaskRunnerStats numFailProgram(int numFailProgram){
        setNumFailProgram(numFailProgram);
        return this;
    }

    /**
     * 设置配置错误计数。
     */
    public void setNumFailConfig(int numFailConfig){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailConfig", this.numFailConfig, numFailConfig, !_IS_LOADED );
        this.numFailConfig = numFailConfig;
    }

    /**
     *  设置配置错误计数链式调用。
     */
    public TaskRunnerStats numFailConfig(int numFailConfig){
        setNumFailConfig(numFailConfig);
        return this;
    }

    /**
     * 设置数据错误计数。
     */
    public void setNumFailData(int numFailData){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailData", this.numFailData, numFailData, !_IS_LOADED );
        this.numFailData = numFailData;
    }

    /**
     *  设置数据错误计数链式调用。
     */
    public TaskRunnerStats numFailData(int numFailData){
        setNumFailData(numFailData);
        return this;
    }

    /**
     * 设置对方错误计数。
     */
    public void setNumFailPartner(int numFailPartner){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailPartner", this.numFailPartner, numFailPartner, !_IS_LOADED );
        this.numFailPartner = numFailPartner;
    }

    /**
     *  设置对方错误计数链式调用。
     */
    public TaskRunnerStats numFailPartner(int numFailPartner){
        setNumFailPartner(numFailPartner);
        return this;
    }

    /**
     * 设置队列等待时间。
     */
    public void setTimeWaitQueue(int timeWaitQueue){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "timeWaitQueue", this.timeWaitQueue, timeWaitQueue, !_IS_LOADED );
        this.timeWaitQueue = timeWaitQueue;
    }

    /**
     *  设置队列等待时间链式调用。
     */
    public TaskRunnerStats timeWaitQueue(int timeWaitQueue){
        setTimeWaitQueue(timeWaitQueue);
        return this;
    }

    /**
     * 设置超时等待时间。
     */
    public void setTimeWaitDelay(int timeWaitDelay){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "timeWaitDelay", this.timeWaitDelay, timeWaitDelay, !_IS_LOADED );
        this.timeWaitDelay = timeWaitDelay;
    }

    /**
     *  设置超时等待时间链式调用。
     */
    public TaskRunnerStats timeWaitDelay(int timeWaitDelay){
        setTimeWaitDelay(timeWaitDelay);
        return this;
    }

    /**
     * 设置运行时间。
     */
    public void setTimeRun(int timeRun){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "timeRun", this.timeRun, timeRun, !_IS_LOADED );
        this.timeRun = timeRun;
    }

    /**
     *  设置运行时间链式调用。
     */
    public TaskRunnerStats timeRun(int timeRun){
        setTimeRun(timeRun);
        return this;
    }

    /**
     * 设置队列长度。
     */
    public void setQueueSize(int queueSize){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "queueSize", this.queueSize, queueSize, !_IS_LOADED );
        this.queueSize = queueSize;
    }

    /**
     *  设置队列长度链式调用。
     */
    public TaskRunnerStats queueSize(int queueSize){
        setQueueSize(queueSize);
        return this;
    }

    /**
     * 设置消费者数量。
     */
    public void setConsumerNum(int consumerNum){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "consumerNum", this.consumerNum, consumerNum, !_IS_LOADED );
        this.consumerNum = consumerNum;
    }

    /**
     *  设置消费者数量链式调用。
     */
    public TaskRunnerStats consumerNum(int consumerNum){
        setConsumerNum(consumerNum);
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
    public TaskRunnerStats createDate(java.util.Date createDate){
        setCreateDate(createDate);
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