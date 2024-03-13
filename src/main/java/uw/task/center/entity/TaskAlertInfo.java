package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

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
	@Schema(title = "id", description = "id")
	private long id;

	/**
	 * 任务类型。1croner2runner
	 */
	@ColumnMeta(columnName="task_type", dataType="String", dataSize=20, nullable=true)
	@Schema(title = "任务类型。1croner2runner", description = "任务类型。1croner2runner")
	private String taskType;

	/**
	 * 任务配置ID
	 */
	@ColumnMeta(columnName="task_id", dataType="long", dataSize=19, nullable=true)
	@Schema(title = "任务配置ID", description = "任务配置ID")
	private long taskId;

	/**
	 * 报警标题
	 */
	@ColumnMeta(columnName="alert_title", dataType="String", dataSize=200, nullable=true)
	@Schema(title = "报警标题", description = "报警标题")
	private String alertTitle;

	/**
	 * 报警信息
	 */
	@ColumnMeta(columnName="alert_body", dataType="String", dataSize=2000, nullable=true)
	@Schema(title = "报警信息", description = "报警信息")
	private String alertBody;

	/**
	 * 创建时间
	 */
	@ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
	@Schema(title = "创建时间", description = "创建时间")
	private java.util.Date createDate;

	/**
	 * 状态
	 */
	@ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "状态", description = "状态")
	private int state;

	/**
	 * 轻量级状态下更新列表list.
	 */
	public transient Set<String> UPDATED_COLUMN = null;

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
	 * 得到_INFO.
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
     * 清理_INFO和UPDATED_COLUMN信息.
     */
    public void CLEAR_UPDATED_INFO() {
        UPDATED_COLUMN = null;
        UPDATED_INFO = null;
	}

	/**
	 * 初始化set相关的信息.
	 */
	private void _INIT_UPDATE_INFO() {
		this.UPDATED_COLUMN = new HashSet<String>();
		this.UPDATED_INFO = new StringBuilder("表task_alert_info主键\"" + 
		this.id+ "\"更新为:\r\n");
	}


	/**
	 * 获得id。
	 */
	public long getId(){
		return this.id;
	}

	/**
	 * 获得任务类型。1croner2runner。
	 */
	public String getTaskType(){
		return this.taskType;
	}

	/**
	 * 获得任务配置ID。
	 */
	public long getTaskId(){
		return this.taskId;
	}

	/**
	 * 获得报警标题。
	 */
	public String getAlertTitle(){
		return this.alertTitle;
	}

	/**
	 * 获得报警信息。
	 */
	public String getAlertBody(){
		return this.alertBody;
	}

	/**
	 * 获得创建时间。
	 */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 获得状态。
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
			this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\""
                + id + "\"\r\n");
			this.id = id;
		}
	}

	/**
	 * 设置任务类型。1croner2runner。
	 */
	public void setTaskType(String taskType){
		if ((!String.valueOf(this.taskType).equals(String.valueOf(taskType)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("task_type");
			this.UPDATED_INFO.append("task_type:\"" + this.taskType+ "\"=>\""
                + taskType + "\"\r\n");
			this.taskType = taskType;
		}
	}

	/**
	 * 设置任务配置ID。
	 */
	public void setTaskId(long taskId){
		if ((!String.valueOf(this.taskId).equals(String.valueOf(taskId)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("task_id");
			this.UPDATED_INFO.append("task_id:\"" + this.taskId+ "\"=>\""
                + taskId + "\"\r\n");
			this.taskId = taskId;
		}
	}

	/**
	 * 设置报警标题。
	 */
	public void setAlertTitle(String alertTitle){
		if ((!String.valueOf(this.alertTitle).equals(String.valueOf(alertTitle)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("alert_title");
			this.UPDATED_INFO.append("alert_title:\"" + this.alertTitle+ "\"=>\""
                + alertTitle + "\"\r\n");
			this.alertTitle = alertTitle;
		}
	}

	/**
	 * 设置报警信息。
	 */
	public void setAlertBody(String alertBody){
		if ((!String.valueOf(this.alertBody).equals(String.valueOf(alertBody)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("alert_body");
			this.UPDATED_INFO.append("alert_body:\"" + this.alertBody+ "\"=>\""
                + alertBody + "\"\r\n");
			this.alertBody = alertBody;
		}
	}

	/**
	 * 设置创建时间。
	 */
	public void setCreateDate(java.util.Date createDate){
		if ((!String.valueOf(this.createDate).equals(String.valueOf(createDate)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("create_date");
			this.UPDATED_INFO.append("create_date:\"" + this.createDate+ "\"=>\""
                + createDate + "\"\r\n");
			this.createDate = createDate;
		}
	}

	/**
	 * 设置状态。
	 */
	public void setState(int state){
		if ((!String.valueOf(this.state).equals(String.valueOf(state)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("state");
			this.UPDATED_INFO.append("state:\"" + this.state+ "\"=>\""
                + state + "\"\r\n");
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
		sb.append("task_type:\"" + this.taskType + "\"\r\n");
		sb.append("task_id:\"" + this.taskId + "\"\r\n");
		sb.append("alert_title:\"" + this.alertTitle + "\"\r\n");
		sb.append("alert_body:\"" + this.alertBody + "\"\r\n");
		sb.append("create_date:\"" + this.createDate + "\"\r\n");
		sb.append("state:\"" + this.state + "\"\r\n");
		return sb.toString();
	}

}