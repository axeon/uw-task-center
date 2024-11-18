package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

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
     * 程序失败计数
     */
    @ColumnMeta(columnName="num_fail_program", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序失败计数", description = "程序失败计数")
    private int numFailProgram;

    /**
     * 配置失败计数
     */
    @ColumnMeta(columnName="num_fail_config", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "配置失败计数", description = "配置失败计数")
    private int numFailConfig;

    /**
     * 数据失败计数
     */
    @ColumnMeta(columnName="num_fail_data", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "数据失败计数", description = "数据失败计数")
    private int numFailData;

    /**
     * 对方失败计数
     */
    @ColumnMeta(columnName="num_fail_partner", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "对方失败计数", description = "对方失败计数")
    private int numFailPartner;

    /**
     * 超时等待
     */
    @ColumnMeta(columnName="time_wait", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "超时等待", description = "超时等待")
    private int timeWait;

    /**
     * 运行时间
     */
    @ColumnMeta(columnName="time_run", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "运行时间", description = "运行时间")
    private int timeRun;

    /**
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间")
    private java.util.Date createDate;

    /**
     * 轻量级状态下更新列表list.
     */
    private transient Set<String> UPDATED_COLUMN = null;

    /**
     * 更新的信息.
     */
    private transient StringBuilder UPDATED_INFO = null;

    /**
     * 获取更改的字段列表.
     */
    @Override
    public Set<String> GET_UPDATED_COLUMN() {
        return UPDATED_COLUMN;
    }

    /**
     * 获取文本更新信息.
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
        this.UPDATED_INFO = new StringBuilder("表task_croner_stats主键\"" + 
        this.id+ "\"更新为:\r\n");
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
        if (!Objects.equals(this.id, id)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("id");
            this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\"" + id + "\"\r\n");
            this.id = id;
        }
    }

    /**
     * 设置任务配置id。
     */
    public void setTaskId(long taskId){
        if (!Objects.equals(this.taskId, taskId)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_id");
            this.UPDATED_INFO.append("task_id:\"" + this.taskId+ "\"=>\"" + taskId + "\"\r\n");
            this.taskId = taskId;
        }
    }

    /**
     * 设置全部执行计数。
     */
    public void setNumAll(int numAll){
        if (!Objects.equals(this.numAll, numAll)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("num_all");
            this.UPDATED_INFO.append("num_all:\"" + this.numAll+ "\"=>\"" + numAll + "\"\r\n");
            this.numAll = numAll;
        }
    }

    /**
     * 设置程序失败计数。
     */
    public void setNumFailProgram(int numFailProgram){
        if (!Objects.equals(this.numFailProgram, numFailProgram)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("num_fail_program");
            this.UPDATED_INFO.append("num_fail_program:\"" + this.numFailProgram+ "\"=>\"" + numFailProgram + "\"\r\n");
            this.numFailProgram = numFailProgram;
        }
    }

    /**
     * 设置配置失败计数。
     */
    public void setNumFailConfig(int numFailConfig){
        if (!Objects.equals(this.numFailConfig, numFailConfig)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("num_fail_config");
            this.UPDATED_INFO.append("num_fail_config:\"" + this.numFailConfig+ "\"=>\"" + numFailConfig + "\"\r\n");
            this.numFailConfig = numFailConfig;
        }
    }

    /**
     * 设置数据失败计数。
     */
    public void setNumFailData(int numFailData){
        if (!Objects.equals(this.numFailData, numFailData)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("num_fail_data");
            this.UPDATED_INFO.append("num_fail_data:\"" + this.numFailData+ "\"=>\"" + numFailData + "\"\r\n");
            this.numFailData = numFailData;
        }
    }

    /**
     * 设置对方失败计数。
     */
    public void setNumFailPartner(int numFailPartner){
        if (!Objects.equals(this.numFailPartner, numFailPartner)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("num_fail_partner");
            this.UPDATED_INFO.append("num_fail_partner:\"" + this.numFailPartner+ "\"=>\"" + numFailPartner + "\"\r\n");
            this.numFailPartner = numFailPartner;
        }
    }

    /**
     * 设置超时等待。
     */
    public void setTimeWait(int timeWait){
        if (!Objects.equals(this.timeWait, timeWait)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("time_wait");
            this.UPDATED_INFO.append("time_wait:\"" + this.timeWait+ "\"=>\"" + timeWait + "\"\r\n");
            this.timeWait = timeWait;
        }
    }

    /**
     * 设置运行时间。
     */
    public void setTimeRun(int timeRun){
        if (!Objects.equals(this.timeRun, timeRun)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("time_run");
            this.UPDATED_INFO.append("time_run:\"" + this.timeRun+ "\"=>\"" + timeRun + "\"\r\n");
            this.timeRun = timeRun;
        }
    }

    /**
     * 设置创建时间。
     */
    public void setCreateDate(java.util.Date createDate){
        if (!Objects.equals(this.createDate, createDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("create_date");
            this.UPDATED_INFO.append("create_date:\"" + this.createDate+ "\"=>\"" + createDate + "\"\r\n");
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
        sb.append("time_wait:\"" + this.timeWait + "\"\r\n");
        sb.append("time_run:\"" + this.timeRun + "\"\r\n");
        sb.append("create_date:\"" + this.createDate + "\"\r\n");
        return sb.toString();
    }

}