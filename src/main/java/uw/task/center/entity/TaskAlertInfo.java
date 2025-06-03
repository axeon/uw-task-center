package uw.task.center.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.util.JsonUtils;
import uw.dao.DataEntity;
import uw.dao.DataUpdateInfo;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

import java.io.Serializable;


/**
 * TaskAlertInfo实体类
 * 报警信息
 *
 * @author axeon
 */
@TableMeta(tableName="task_alert_info",tableType="table")
@Schema(title = "报警信息", description = "报警信息")
public class TaskAlertInfo implements DataEntity,Serializable{


    /**
     * id
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "id", description = "id", maxLength=19, nullable=false )
    private long id;

    /**
     * 任务类型。1croner2runner
     */
    @ColumnMeta(columnName="task_type", dataType="String", dataSize=20, nullable=true)
    @Schema(title = "任务类型。1croner2runner", description = "任务类型。1croner2runner", maxLength=20, nullable=true )
    private String taskType;

    /**
     * 任务配置ID
     */
    @ColumnMeta(columnName="task_id", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "任务配置ID", description = "任务配置ID", maxLength=19, nullable=true )
    private long taskId;

    /**
     * 报警标题
     */
    @ColumnMeta(columnName="alert_title", dataType="String", dataSize=200, nullable=true)
    @Schema(title = "报警标题", description = "报警标题", maxLength=200, nullable=true )
    private String alertTitle;

    /**
     * 报警信息
     */
    @ColumnMeta(columnName="alert_body", dataType="String", dataSize=2147483646, nullable=true)
    @Schema(title = "报警信息", description = "报警信息", maxLength=2147483646, nullable=true )
    private String alertBody;

    /**
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间", maxLength=23, nullable=true )
    private java.util.Date createDate;

    /**
     * 状态
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态", description = "状态", maxLength=10, nullable=true )
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
        return "task_alert_info";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "报警信息";
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
     * 获取任务类型。1croner2runner。
     */
    public String getTaskType(){
        return this.taskType;
    }

    /**
     * 获取任务配置ID。
     */
    public long getTaskId(){
        return this.taskId;
    }

    /**
     * 获取报警标题。
     */
    public String getAlertTitle(){
        return this.alertTitle;
    }

    /**
     * 获取报警信息。
     */
    public String getAlertBody(){
        return this.alertBody;
    }

    /**
     * 获取创建时间。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获取状态。
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
    public TaskAlertInfo id(long id){
        setId(id);
        return this;
    }

    /**
     * 设置任务类型。1croner2runner。
     */
    public void setTaskType(String taskType){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskType", this.taskType, taskType, !_IS_LOADED );
        this.taskType = taskType;
    }

    /**
     *  设置任务类型。1croner2runner链式调用。
     */
    public TaskAlertInfo taskType(String taskType){
        setTaskType(taskType);
        return this;
    }

    /**
     * 设置任务配置ID。
     */
    public void setTaskId(long taskId){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "taskId", this.taskId, taskId, !_IS_LOADED );
        this.taskId = taskId;
    }

    /**
     *  设置任务配置ID链式调用。
     */
    public TaskAlertInfo taskId(long taskId){
        setTaskId(taskId);
        return this;
    }

    /**
     * 设置报警标题。
     */
    public void setAlertTitle(String alertTitle){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertTitle", this.alertTitle, alertTitle, !_IS_LOADED );
        this.alertTitle = alertTitle;
    }

    /**
     *  设置报警标题链式调用。
     */
    public TaskAlertInfo alertTitle(String alertTitle){
        setAlertTitle(alertTitle);
        return this;
    }

    /**
     * 设置报警信息。
     */
    public void setAlertBody(String alertBody){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "alertBody", this.alertBody, alertBody, !_IS_LOADED );
        this.alertBody = alertBody;
    }

    /**
     *  设置报警信息链式调用。
     */
    public TaskAlertInfo alertBody(String alertBody){
        setAlertBody(alertBody);
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
    public TaskAlertInfo createDate(java.util.Date createDate){
        setCreateDate(createDate);
        return this;
    }

    /**
     * 设置状态。
     */
    public void setState(int state){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "state", this.state, state, !_IS_LOADED );
        this.state = state;
    }

    /**
     *  设置状态链式调用。
     */
    public TaskAlertInfo state(int state){
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