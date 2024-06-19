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
 * TaskCronerInfo实体类
 * 定时任务配置
 *
 * @author axeon
 */
@TableMeta(tableName="task_croner_info",tableType="table")
@Schema(title = "定时任务配置", description = "定时任务配置")
public class TaskCronerInfo implements DataEntity,Serializable{


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
     * 任务参数
     */
    @ColumnMeta(columnName="task_param", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "任务参数", description = "任务参数")
    private String taskParam;

    /**
     * 任务所有人
     */
    @ColumnMeta(columnName="task_owner", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "任务所有人", description = "任务所有人")
    private String taskOwner;

    /**
     * cron表达式
     */
    @ColumnMeta(columnName="task_cron", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "cron表达式", description = "cron表达式")
    private String taskCron;

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
     * 下次执行时间
     */
    @ColumnMeta(columnName="next_run_date", dataType="java.util.Date", dataSize=19, nullable=true)
    @Schema(title = "下次执行时间", description = "下次执行时间")
    private java.util.Date nextRunDate;

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
     * 数据失败率
     */
    @ColumnMeta(columnName="alert_fail_data_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "数据失败率", description = "数据失败率")
    private int alertFailDataRate;

    /**
     * 程序失败率
     */
    @ColumnMeta(columnName="alert_fail_program_rate", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "程序失败率", description = "程序失败率")
    private int alertFailProgramRate;

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
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间")
    private java.util.Date createDate;

    /**
     * 最后修改时间
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "最后修改时间", description = "最后修改时间")
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
        this.UPDATED_INFO = new StringBuilder("表task_croner_info主键\"" + 
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
     * 获得任务参数。
     */
    public String getTaskParam(){
        return this.taskParam;
    }

    /**
     * 获得任务所有人。
     */
    public String getTaskOwner(){
        return this.taskOwner;
    }

    /**
     * 获得cron表达式。
     */
    public String getTaskCron(){
        return this.taskCron;
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
     * 获得下次执行时间。
     */
    public java.util.Date getNextRunDate(){
        return this.nextRunDate;
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
     * 获得数据失败率。
     */
    public int getAlertFailDataRate(){
        return this.alertFailDataRate;
    }

    /**
     * 获得程序失败率。
     */
    public int getAlertFailProgramRate(){
        return this.alertFailProgramRate;
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
     * 获得创建时间。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获得最后修改时间。
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
     * 设置任务参数。
     */
    public void setTaskParam(String taskParam){
        if ((!String.valueOf(this.taskParam).equals(String.valueOf(taskParam)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_param");
            this.UPDATED_INFO.append("task_param:\"" + this.taskParam+ "\"=>\"" + taskParam + "\"\r\n");
            this.taskParam = taskParam;
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
     * 设置cron表达式。
     */
    public void setTaskCron(String taskCron){
        if ((!String.valueOf(this.taskCron).equals(String.valueOf(taskCron)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_cron");
            this.UPDATED_INFO.append("task_cron:\"" + this.taskCron+ "\"=>\"" + taskCron + "\"\r\n");
            this.taskCron = taskCron;
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
     * 设置下次执行时间。
     */
    public void setNextRunDate(java.util.Date nextRunDate){
        if ((!String.valueOf(this.nextRunDate).equals(String.valueOf(nextRunDate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("next_run_date");
            this.UPDATED_INFO.append("next_run_date:\"" + this.nextRunDate+ "\"=>\"" + nextRunDate + "\"\r\n");
            this.nextRunDate = nextRunDate;
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
     * 设置创建时间。
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
     * 设置最后修改时间。
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
        sb.append("task_param:\"" + this.taskParam + "\"\r\n");
        sb.append("task_owner:\"" + this.taskOwner + "\"\r\n");
        sb.append("task_cron:\"" + this.taskCron + "\"\r\n");
        sb.append("run_type:\"" + this.runType + "\"\r\n");
        sb.append("run_target:\"" + this.runTarget + "\"\r\n");
        sb.append("log_level:\"" + this.logLevel + "\"\r\n");
        sb.append("log_limit_size:\"" + this.logLimitSize + "\"\r\n");
        sb.append("next_run_date:\"" + this.nextRunDate + "\"\r\n");
        sb.append("stats_date:\"" + this.statsDate + "\"\r\n");
        sb.append("stats_run_num:\"" + this.statsRunNum + "\"\r\n");
        sb.append("stats_fail_num:\"" + this.statsFailNum + "\"\r\n");
        sb.append("stats_run_time:\"" + this.statsRunTime + "\"\r\n");
        sb.append("alert_fail_rate:\"" + this.alertFailRate + "\"\r\n");
        sb.append("alert_fail_partner_rate:\"" + this.alertFailPartnerRate + "\"\r\n");
        sb.append("alert_fail_data_rate:\"" + this.alertFailDataRate + "\"\r\n");
        sb.append("alert_fail_program_rate:\"" + this.alertFailProgramRate + "\"\r\n");
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