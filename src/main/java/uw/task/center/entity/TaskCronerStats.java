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
 * TaskCronerStats实体类
 * 定时任务统计信息
 *
 * @author axeon
 */
@TableMeta(tableName="task_croner_stats",tableType="table")
@Schema(title = "定时任务统计信息", description = "定时任务统计信息")
public class TaskCronerStats implements DataEntity,Serializable{


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
     * 程序失败计数
     */
    @ColumnMeta(columnName="num_fail_program", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序失败计数", description = "程序失败计数", maxLength=10, nullable=true )
    private int numFailProgram;

    /**
     * 配置失败计数
     */
    @ColumnMeta(columnName="num_fail_config", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "配置失败计数", description = "配置失败计数", maxLength=10, nullable=true )
    private int numFailConfig;

    /**
     * 数据失败计数
     */
    @ColumnMeta(columnName="num_fail_data", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "数据失败计数", description = "数据失败计数", maxLength=10, nullable=true )
    private int numFailData;

    /**
     * 对方失败计数
     */
    @ColumnMeta(columnName="num_fail_partner", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "对方失败计数", description = "对方失败计数", maxLength=10, nullable=true )
    private int numFailPartner;

    /**
     * 超时等待
     */
    @ColumnMeta(columnName="time_wait", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "超时等待", description = "超时等待", maxLength=10, nullable=true )
    private int timeWait;

    /**
     * 运行时间
     */
    @ColumnMeta(columnName="time_run", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行时间", description = "运行时间", maxLength=10, nullable=true )
    private int timeRun;

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
        return "task_croner_stats";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "定时任务统计信息";
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
     * 获取程序失败计数。
     */
    public int getNumFailProgram(){
        return this.numFailProgram;
    }

    /**
     * 获取配置失败计数。
     */
    public int getNumFailConfig(){
        return this.numFailConfig;
    }

    /**
     * 获取数据失败计数。
     */
    public int getNumFailData(){
        return this.numFailData;
    }

    /**
     * 获取对方失败计数。
     */
    public int getNumFailPartner(){
        return this.numFailPartner;
    }

    /**
     * 获取超时等待。
     */
    public int getTimeWait(){
        return this.timeWait;
    }

    /**
     * 获取运行时间。
     */
    public int getTimeRun(){
        return this.timeRun;
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
    public TaskCronerStats id(long id){
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
    public TaskCronerStats taskId(long taskId){
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
    public TaskCronerStats numAll(int numAll){
        setNumAll(numAll);
        return this;
    }

    /**
     * 设置程序失败计数。
     */
    public void setNumFailProgram(int numFailProgram){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailProgram", this.numFailProgram, numFailProgram, !_IS_LOADED );
        this.numFailProgram = numFailProgram;
    }

    /**
     *  设置程序失败计数链式调用。
     */
    public TaskCronerStats numFailProgram(int numFailProgram){
        setNumFailProgram(numFailProgram);
        return this;
    }

    /**
     * 设置配置失败计数。
     */
    public void setNumFailConfig(int numFailConfig){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailConfig", this.numFailConfig, numFailConfig, !_IS_LOADED );
        this.numFailConfig = numFailConfig;
    }

    /**
     *  设置配置失败计数链式调用。
     */
    public TaskCronerStats numFailConfig(int numFailConfig){
        setNumFailConfig(numFailConfig);
        return this;
    }

    /**
     * 设置数据失败计数。
     */
    public void setNumFailData(int numFailData){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailData", this.numFailData, numFailData, !_IS_LOADED );
        this.numFailData = numFailData;
    }

    /**
     *  设置数据失败计数链式调用。
     */
    public TaskCronerStats numFailData(int numFailData){
        setNumFailData(numFailData);
        return this;
    }

    /**
     * 设置对方失败计数。
     */
    public void setNumFailPartner(int numFailPartner){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "numFailPartner", this.numFailPartner, numFailPartner, !_IS_LOADED );
        this.numFailPartner = numFailPartner;
    }

    /**
     *  设置对方失败计数链式调用。
     */
    public TaskCronerStats numFailPartner(int numFailPartner){
        setNumFailPartner(numFailPartner);
        return this;
    }

    /**
     * 设置超时等待。
     */
    public void setTimeWait(int timeWait){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "timeWait", this.timeWait, timeWait, !_IS_LOADED );
        this.timeWait = timeWait;
    }

    /**
     *  设置超时等待链式调用。
     */
    public TaskCronerStats timeWait(int timeWait){
        setTimeWait(timeWait);
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
    public TaskCronerStats timeRun(int timeRun){
        setTimeRun(timeRun);
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
    public TaskCronerStats createDate(java.util.Date createDate){
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