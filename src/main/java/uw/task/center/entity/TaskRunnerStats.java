package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

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
	@Schema(title = "id", description = "id")
	private long id;

	/**
	 * 任务配置id
	 */
	@ColumnMeta(columnName="task_id", dataType="long", dataSize=19, nullable=true)
	@Schema(title = "任务配置id", description = "任务配置id")
	private long taskId;

	/**
	 * 全部执行计数
	 */
	@ColumnMeta(columnName="num_all", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "全部执行计数", description = "全部执行计数")
	private int numAll;

	/**
	 * 程序错误计数
	 */
	@ColumnMeta(columnName="num_fail_program", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "程序错误计数", description = "程序错误计数")
	private int numFailProgram;

	/**
	 * 配置错误计数
	 */
	@ColumnMeta(columnName="num_fail_config", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "配置错误计数", description = "配置错误计数")
	private int numFailConfig;

	/**
	 * 数据错误计数
	 */
	@ColumnMeta(columnName="num_fail_data", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "数据错误计数", description = "数据错误计数")
	private int numFailData;

	/**
	 * 对方错误计数
	 */
	@ColumnMeta(columnName="num_fail_partner", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "对方错误计数", description = "对方错误计数")
	private int numFailPartner;

	/**
	 * 队列等待时间
	 */
	@ColumnMeta(columnName="time_wait_queue", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "队列等待时间", description = "队列等待时间")
	private int timeWaitQueue;

	/**
	 * 超时等待时间
	 */
	@ColumnMeta(columnName="time_wait_delay", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "超时等待时间", description = "超时等待时间")
	private int timeWaitDelay;

	/**
	 * 运行时间
	 */
	@ColumnMeta(columnName="time_run", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "运行时间", description = "运行时间")
	private int timeRun;

	/**
	 * 队列长度
	 */
	@ColumnMeta(columnName="queue_size", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "队列长度", description = "队列长度")
	private int queueSize;

	/**
	 * 消费者数量
	 */
	@ColumnMeta(columnName="consumer_num", dataType="int", dataSize=10, nullable=true)
	@Schema(title = "消费者数量", description = "消费者数量")
	private int consumerNum;

	/**
	 * 创建时间
	 */
	@ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
	@Schema(title = "创建时间", description = "创建时间")
	private java.util.Date createDate;

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
		this.UPDATED_INFO = new StringBuilder("表task_runner_stats主键\"" + 
		this.id+ "\"更新为:\r\n");
	}


	/**
	 * 获得id。
	 */
	public long getId(){
		return this.id;
	}

	/**
	 * 获得任务配置id。
	 */
	public long getTaskId(){
		return this.taskId;
	}

	/**
	 * 获得全部执行计数。
	 */
	public int getNumAll(){
		return this.numAll;
	}

	/**
	 * 获得程序错误计数。
	 */
	public int getNumFailProgram(){
		return this.numFailProgram;
	}

	/**
	 * 获得配置错误计数。
	 */
	public int getNumFailConfig(){
		return this.numFailConfig;
	}

	/**
	 * 获得数据错误计数。
	 */
	public int getNumFailData(){
		return this.numFailData;
	}

	/**
	 * 获得对方错误计数。
	 */
	public int getNumFailPartner(){
		return this.numFailPartner;
	}

	/**
	 * 获得队列等待时间。
	 */
	public int getTimeWaitQueue(){
		return this.timeWaitQueue;
	}

	/**
	 * 获得超时等待时间。
	 */
	public int getTimeWaitDelay(){
		return this.timeWaitDelay;
	}

	/**
	 * 获得运行时间。
	 */
	public int getTimeRun(){
		return this.timeRun;
	}

	/**
	 * 获得队列长度。
	 */
	public int getQueueSize(){
		return this.queueSize;
	}

	/**
	 * 获得消费者数量。
	 */
	public int getConsumerNum(){
		return this.consumerNum;
	}

	/**
	 * 获得创建时间。
	 */
	public java.util.Date getCreateDate(){
		return this.createDate;
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
	 * 设置任务配置id。
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
	 * 设置全部执行计数。
	 */
	public void setNumAll(int numAll){
		if ((!String.valueOf(this.numAll).equals(String.valueOf(numAll)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("num_all");
			this.UPDATED_INFO.append("num_all:\"" + this.numAll+ "\"=>\""
                + numAll + "\"\r\n");
			this.numAll = numAll;
		}
	}

	/**
	 * 设置程序错误计数。
	 */
	public void setNumFailProgram(int numFailProgram){
		if ((!String.valueOf(this.numFailProgram).equals(String.valueOf(numFailProgram)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("num_fail_program");
			this.UPDATED_INFO.append("num_fail_program:\"" + this.numFailProgram+ "\"=>\""
                + numFailProgram + "\"\r\n");
			this.numFailProgram = numFailProgram;
		}
	}

	/**
	 * 设置配置错误计数。
	 */
	public void setNumFailConfig(int numFailConfig){
		if ((!String.valueOf(this.numFailConfig).equals(String.valueOf(numFailConfig)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("num_fail_config");
			this.UPDATED_INFO.append("num_fail_config:\"" + this.numFailConfig+ "\"=>\""
                + numFailConfig + "\"\r\n");
			this.numFailConfig = numFailConfig;
		}
	}

	/**
	 * 设置数据错误计数。
	 */
	public void setNumFailData(int numFailData){
		if ((!String.valueOf(this.numFailData).equals(String.valueOf(numFailData)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("num_fail_data");
			this.UPDATED_INFO.append("num_fail_data:\"" + this.numFailData+ "\"=>\""
                + numFailData + "\"\r\n");
			this.numFailData = numFailData;
		}
	}

	/**
	 * 设置对方错误计数。
	 */
	public void setNumFailPartner(int numFailPartner){
		if ((!String.valueOf(this.numFailPartner).equals(String.valueOf(numFailPartner)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("num_fail_partner");
			this.UPDATED_INFO.append("num_fail_partner:\"" + this.numFailPartner+ "\"=>\""
                + numFailPartner + "\"\r\n");
			this.numFailPartner = numFailPartner;
		}
	}

	/**
	 * 设置队列等待时间。
	 */
	public void setTimeWaitQueue(int timeWaitQueue){
		if ((!String.valueOf(this.timeWaitQueue).equals(String.valueOf(timeWaitQueue)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("time_wait_queue");
			this.UPDATED_INFO.append("time_wait_queue:\"" + this.timeWaitQueue+ "\"=>\""
                + timeWaitQueue + "\"\r\n");
			this.timeWaitQueue = timeWaitQueue;
		}
	}

	/**
	 * 设置超时等待时间。
	 */
	public void setTimeWaitDelay(int timeWaitDelay){
		if ((!String.valueOf(this.timeWaitDelay).equals(String.valueOf(timeWaitDelay)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("time_wait_delay");
			this.UPDATED_INFO.append("time_wait_delay:\"" + this.timeWaitDelay+ "\"=>\""
                + timeWaitDelay + "\"\r\n");
			this.timeWaitDelay = timeWaitDelay;
		}
	}

	/**
	 * 设置运行时间。
	 */
	public void setTimeRun(int timeRun){
		if ((!String.valueOf(this.timeRun).equals(String.valueOf(timeRun)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("time_run");
			this.UPDATED_INFO.append("time_run:\"" + this.timeRun+ "\"=>\""
                + timeRun + "\"\r\n");
			this.timeRun = timeRun;
		}
	}

	/**
	 * 设置队列长度。
	 */
	public void setQueueSize(int queueSize){
		if ((!String.valueOf(this.queueSize).equals(String.valueOf(queueSize)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("queue_size");
			this.UPDATED_INFO.append("queue_size:\"" + this.queueSize+ "\"=>\""
                + queueSize + "\"\r\n");
			this.queueSize = queueSize;
		}
	}

	/**
	 * 设置消费者数量。
	 */
	public void setConsumerNum(int consumerNum){
		if ((!String.valueOf(this.consumerNum).equals(String.valueOf(consumerNum)))) {
			if (this.UPDATED_COLUMN == null) {
				_INIT_UPDATE_INFO();
			}
			this.UPDATED_COLUMN.add("consumer_num");
			this.UPDATED_INFO.append("consumer_num:\"" + this.consumerNum+ "\"=>\""
                + consumerNum + "\"\r\n");
			this.consumerNum = consumerNum;
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
	 * 重载toString方法.
	 */
    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id:\"" + this.id + "\"\r\n");
		sb.append("task_id:\"" + this.taskId + "\"\r\n");
		sb.append("num_all:\"" + this.numAll + "\"\r\n");
		sb.append("num_fail_program:\"" + this.numFailProgram + "\"\r\n");
		sb.append("num_fail_config:\"" + this.numFailConfig + "\"\r\n");
		sb.append("num_fail_data:\"" + this.numFailData + "\"\r\n");
		sb.append("num_fail_partner:\"" + this.numFailPartner + "\"\r\n");
		sb.append("time_wait_queue:\"" + this.timeWaitQueue + "\"\r\n");
		sb.append("time_wait_delay:\"" + this.timeWaitDelay + "\"\r\n");
		sb.append("time_run:\"" + this.timeRun + "\"\r\n");
		sb.append("queue_size:\"" + this.queueSize + "\"\r\n");
		sb.append("consumer_num:\"" + this.consumerNum + "\"\r\n");
		sb.append("create_date:\"" + this.createDate + "\"\r\n");
		return sb.toString();
	}

}