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
 * TaskHostInfo实体类
 * task主机信息
 *
 * @author axeon
 */
@TableMeta(tableName="task_host_info",tableType="table")
@Schema(title = "task主机信息", description = "task主机信息")
public class TaskHostInfo implements DataEntity,Serializable{


    /**
     * id
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "id", description = "id")
    private long id;

    /**
     * 主机注册IP
     */
    @ColumnMeta(columnName="host_ip", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "主机注册IP", description = "主机注册IP")
    private String hostIp;

    /**
     * 应用名称
     */
    @ColumnMeta(columnName="app_name", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "应用名称", description = "应用名称")
    private String appName;

    /**
     * 应用版本
     */
    @ColumnMeta(columnName="app_version", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "应用版本", description = "应用版本")
    private String appVersion;

    /**
     * app主机
     */
    @ColumnMeta(columnName="app_host", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "app主机", description = "app主机")
    private String appHost;

    /**
     * app端口
     */
    @ColumnMeta(columnName="app_port", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "app端口", description = "app端口")
    private int appPort;

    /**
     * 任务项目
     */
    @ColumnMeta(columnName="task_project", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "任务项目", description = "任务项目")
    private String taskProject;

    /**
     * 运行目标
     */
    @ColumnMeta(columnName="run_target", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行目标", description = "运行目标")
    private String runTarget;

    /**
     * 定时任务数量
     */
    @ColumnMeta(columnName="croner_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "定时任务数量", description = "定时任务数量")
    private int cronerNum;

    /**
     * 统计运行次数
     */
    @ColumnMeta(columnName="croner_run_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行次数", description = "统计运行次数")
    private int cronerRunNum;

    /**
     * 统计运行失败次数
     */
    @ColumnMeta(columnName="croner_fail_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数")
    private int cronerFailNum;

    /**
     * 统计总时间毫秒数
     */
    @ColumnMeta(columnName="croner_run_time", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数")
    private long cronerRunTime;

    /**
     * 队列任务数量
     */
    @ColumnMeta(columnName="runner_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列任务数量", description = "队列任务数量")
    private int runnerNum;

    /**
     * 统计运行次数
     */
    @ColumnMeta(columnName="runner_run_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行次数", description = "统计运行次数")
    private int runnerRunNum;

    /**
     * 统计运行失败次数
     */
    @ColumnMeta(columnName="runner_fail_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数")
    private int runnerFailNum;

    /**
     * 统计总时间毫秒数
     */
    @ColumnMeta(columnName="runner_run_time", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数")
    private long runnerRunTime;

    /**
     * jvm内存总数
     */
    @ColumnMeta(columnName="jvm_mem_max", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "jvm内存总数", description = "jvm内存总数")
    private long jvmMemMax;

    /**
     * jvm内存总数
     */
    @ColumnMeta(columnName="jvm_mem_total", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "jvm内存总数", description = "jvm内存总数")
    private long jvmMemTotal;

    /**
     * jvm空闲内存
     */
    @ColumnMeta(columnName="jvm_mem_free", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "jvm空闲内存", description = "jvm空闲内存")
    private long jvmMemFree;

    /**
     * 活跃线程
     */
    @ColumnMeta(columnName="thread_active", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "活跃线程", description = "活跃线程")
    private int threadActive;

    /**
     * 峰值线程
     */
    @ColumnMeta(columnName="thread_peak", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "峰值线程", description = "峰值线程")
    private int threadPeak;

    /**
     * 守护线程
     */
    @ColumnMeta(columnName="thread_daemon", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "守护线程", description = "守护线程")
    private int threadDaemon;

    /**
     * 累计启动线程
     */
    @ColumnMeta(columnName="thread_started", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "累计启动线程", description = "累计启动线程")
    private long threadStarted;

    /**
     * 建立日期
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "建立日期", description = "建立日期")
    private java.util.Date createDate;

    /**
     * 修改时间
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "修改时间", description = "修改时间")
    private java.util.Date modifyDate;

    /**
     * 最后更新
     */
    @ColumnMeta(columnName="last_update", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "最后更新", description = "最后更新")
    private java.util.Date lastUpdate;

    /**
     * 状态
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态", description = "状态")
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
        this.UPDATED_INFO = new StringBuilder("表task_host_info主键\"" + 
        this.id+ "\"更新为:\r\n");
    }


    /**
     * 获得id。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获得主机注册IP。
     */
    public String getHostIp(){
        return this.hostIp;
    }

    /**
     * 获得应用名称。
     */
    public String getAppName(){
        return this.appName;
    }

    /**
     * 获得应用版本。
     */
    public String getAppVersion(){
        return this.appVersion;
    }

    /**
     * 获得app主机。
     */
    public String getAppHost(){
        return this.appHost;
    }

    /**
     * 获得app端口。
     */
    public int getAppPort(){
        return this.appPort;
    }

    /**
     * 获得任务项目。
     */
    public String getTaskProject(){
        return this.taskProject;
    }

    /**
     * 获得运行目标。
     */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
     * 获得定时任务数量。
     */
    public int getCronerNum(){
        return this.cronerNum;
    }

    /**
     * 获得统计运行次数。
     */
    public int getCronerRunNum(){
        return this.cronerRunNum;
    }

    /**
     * 获得统计运行失败次数。
     */
    public int getCronerFailNum(){
        return this.cronerFailNum;
    }

    /**
     * 获得统计总时间毫秒数。
     */
    public long getCronerRunTime(){
        return this.cronerRunTime;
    }

    /**
     * 获得队列任务数量。
     */
    public int getRunnerNum(){
        return this.runnerNum;
    }

    /**
     * 获得统计运行次数。
     */
    public int getRunnerRunNum(){
        return this.runnerRunNum;
    }

    /**
     * 获得统计运行失败次数。
     */
    public int getRunnerFailNum(){
        return this.runnerFailNum;
    }

    /**
     * 获得统计总时间毫秒数。
     */
    public long getRunnerRunTime(){
        return this.runnerRunTime;
    }

    /**
     * 获得jvm内存总数。
     */
    public long getJvmMemMax(){
        return this.jvmMemMax;
    }

    /**
     * 获得jvm内存总数。
     */
    public long getJvmMemTotal(){
        return this.jvmMemTotal;
    }

    /**
     * 获得jvm空闲内存。
     */
    public long getJvmMemFree(){
        return this.jvmMemFree;
    }

    /**
     * 获得活跃线程。
     */
    public int getThreadActive(){
        return this.threadActive;
    }

    /**
     * 获得峰值线程。
     */
    public int getThreadPeak(){
        return this.threadPeak;
    }

    /**
     * 获得守护线程。
     */
    public int getThreadDaemon(){
        return this.threadDaemon;
    }

    /**
     * 获得累计启动线程。
     */
    public long getThreadStarted(){
        return this.threadStarted;
    }

    /**
     * 获得建立日期。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获得修改时间。
     */
    public java.util.Date getModifyDate(){
        return this.modifyDate;
    }

    /**
     * 获得最后更新。
     */
    public java.util.Date getLastUpdate(){
        return this.lastUpdate;
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
            this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\"" + id + "\"\r\n");
            this.id = id;
        }
    }

    /**
     * 设置主机注册IP。
     */
    public void setHostIp(String hostIp){
        if ((!String.valueOf(this.hostIp).equals(String.valueOf(hostIp)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("host_ip");
            this.UPDATED_INFO.append("host_ip:\"" + this.hostIp+ "\"=>\"" + hostIp + "\"\r\n");
            this.hostIp = hostIp;
        }
    }

    /**
     * 设置应用名称。
     */
    public void setAppName(String appName){
        if ((!String.valueOf(this.appName).equals(String.valueOf(appName)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_name");
            this.UPDATED_INFO.append("app_name:\"" + this.appName+ "\"=>\"" + appName + "\"\r\n");
            this.appName = appName;
        }
    }

    /**
     * 设置应用版本。
     */
    public void setAppVersion(String appVersion){
        if ((!String.valueOf(this.appVersion).equals(String.valueOf(appVersion)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_version");
            this.UPDATED_INFO.append("app_version:\"" + this.appVersion+ "\"=>\"" + appVersion + "\"\r\n");
            this.appVersion = appVersion;
        }
    }

    /**
     * 设置app主机。
     */
    public void setAppHost(String appHost){
        if ((!String.valueOf(this.appHost).equals(String.valueOf(appHost)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_host");
            this.UPDATED_INFO.append("app_host:\"" + this.appHost+ "\"=>\"" + appHost + "\"\r\n");
            this.appHost = appHost;
        }
    }

    /**
     * 设置app端口。
     */
    public void setAppPort(int appPort){
        if ((!String.valueOf(this.appPort).equals(String.valueOf(appPort)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_port");
            this.UPDATED_INFO.append("app_port:\"" + this.appPort+ "\"=>\"" + appPort + "\"\r\n");
            this.appPort = appPort;
        }
    }

    /**
     * 设置任务项目。
     */
    public void setTaskProject(String taskProject){
        if ((!String.valueOf(this.taskProject).equals(String.valueOf(taskProject)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_project");
            this.UPDATED_INFO.append("task_project:\"" + this.taskProject+ "\"=>\"" + taskProject + "\"\r\n");
            this.taskProject = taskProject;
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
     * 设置定时任务数量。
     */
    public void setCronerNum(int cronerNum){
        if ((!String.valueOf(this.cronerNum).equals(String.valueOf(cronerNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_num");
            this.UPDATED_INFO.append("croner_num:\"" + this.cronerNum+ "\"=>\"" + cronerNum + "\"\r\n");
            this.cronerNum = cronerNum;
        }
    }

    /**
     * 设置统计运行次数。
     */
    public void setCronerRunNum(int cronerRunNum){
        if ((!String.valueOf(this.cronerRunNum).equals(String.valueOf(cronerRunNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_run_num");
            this.UPDATED_INFO.append("croner_run_num:\"" + this.cronerRunNum+ "\"=>\"" + cronerRunNum + "\"\r\n");
            this.cronerRunNum = cronerRunNum;
        }
    }

    /**
     * 设置统计运行失败次数。
     */
    public void setCronerFailNum(int cronerFailNum){
        if ((!String.valueOf(this.cronerFailNum).equals(String.valueOf(cronerFailNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_fail_num");
            this.UPDATED_INFO.append("croner_fail_num:\"" + this.cronerFailNum+ "\"=>\"" + cronerFailNum + "\"\r\n");
            this.cronerFailNum = cronerFailNum;
        }
    }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setCronerRunTime(long cronerRunTime){
        if ((!String.valueOf(this.cronerRunTime).equals(String.valueOf(cronerRunTime)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_run_time");
            this.UPDATED_INFO.append("croner_run_time:\"" + this.cronerRunTime+ "\"=>\"" + cronerRunTime + "\"\r\n");
            this.cronerRunTime = cronerRunTime;
        }
    }

    /**
     * 设置队列任务数量。
     */
    public void setRunnerNum(int runnerNum){
        if ((!String.valueOf(this.runnerNum).equals(String.valueOf(runnerNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_num");
            this.UPDATED_INFO.append("runner_num:\"" + this.runnerNum+ "\"=>\"" + runnerNum + "\"\r\n");
            this.runnerNum = runnerNum;
        }
    }

    /**
     * 设置统计运行次数。
     */
    public void setRunnerRunNum(int runnerRunNum){
        if ((!String.valueOf(this.runnerRunNum).equals(String.valueOf(runnerRunNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_run_num");
            this.UPDATED_INFO.append("runner_run_num:\"" + this.runnerRunNum+ "\"=>\"" + runnerRunNum + "\"\r\n");
            this.runnerRunNum = runnerRunNum;
        }
    }

    /**
     * 设置统计运行失败次数。
     */
    public void setRunnerFailNum(int runnerFailNum){
        if ((!String.valueOf(this.runnerFailNum).equals(String.valueOf(runnerFailNum)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_fail_num");
            this.UPDATED_INFO.append("runner_fail_num:\"" + this.runnerFailNum+ "\"=>\"" + runnerFailNum + "\"\r\n");
            this.runnerFailNum = runnerFailNum;
        }
    }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setRunnerRunTime(long runnerRunTime){
        if ((!String.valueOf(this.runnerRunTime).equals(String.valueOf(runnerRunTime)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_run_time");
            this.UPDATED_INFO.append("runner_run_time:\"" + this.runnerRunTime+ "\"=>\"" + runnerRunTime + "\"\r\n");
            this.runnerRunTime = runnerRunTime;
        }
    }

    /**
     * 设置jvm内存总数。
     */
    public void setJvmMemMax(long jvmMemMax){
        if ((!String.valueOf(this.jvmMemMax).equals(String.valueOf(jvmMemMax)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("jvm_mem_max");
            this.UPDATED_INFO.append("jvm_mem_max:\"" + this.jvmMemMax+ "\"=>\"" + jvmMemMax + "\"\r\n");
            this.jvmMemMax = jvmMemMax;
        }
    }

    /**
     * 设置jvm内存总数。
     */
    public void setJvmMemTotal(long jvmMemTotal){
        if ((!String.valueOf(this.jvmMemTotal).equals(String.valueOf(jvmMemTotal)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("jvm_mem_total");
            this.UPDATED_INFO.append("jvm_mem_total:\"" + this.jvmMemTotal+ "\"=>\"" + jvmMemTotal + "\"\r\n");
            this.jvmMemTotal = jvmMemTotal;
        }
    }

    /**
     * 设置jvm空闲内存。
     */
    public void setJvmMemFree(long jvmMemFree){
        if ((!String.valueOf(this.jvmMemFree).equals(String.valueOf(jvmMemFree)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("jvm_mem_free");
            this.UPDATED_INFO.append("jvm_mem_free:\"" + this.jvmMemFree+ "\"=>\"" + jvmMemFree + "\"\r\n");
            this.jvmMemFree = jvmMemFree;
        }
    }

    /**
     * 设置活跃线程。
     */
    public void setThreadActive(int threadActive){
        if ((!String.valueOf(this.threadActive).equals(String.valueOf(threadActive)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_active");
            this.UPDATED_INFO.append("thread_active:\"" + this.threadActive+ "\"=>\"" + threadActive + "\"\r\n");
            this.threadActive = threadActive;
        }
    }

    /**
     * 设置峰值线程。
     */
    public void setThreadPeak(int threadPeak){
        if ((!String.valueOf(this.threadPeak).equals(String.valueOf(threadPeak)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_peak");
            this.UPDATED_INFO.append("thread_peak:\"" + this.threadPeak+ "\"=>\"" + threadPeak + "\"\r\n");
            this.threadPeak = threadPeak;
        }
    }

    /**
     * 设置守护线程。
     */
    public void setThreadDaemon(int threadDaemon){
        if ((!String.valueOf(this.threadDaemon).equals(String.valueOf(threadDaemon)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_daemon");
            this.UPDATED_INFO.append("thread_daemon:\"" + this.threadDaemon+ "\"=>\"" + threadDaemon + "\"\r\n");
            this.threadDaemon = threadDaemon;
        }
    }

    /**
     * 设置累计启动线程。
     */
    public void setThreadStarted(long threadStarted){
        if ((!String.valueOf(this.threadStarted).equals(String.valueOf(threadStarted)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_started");
            this.UPDATED_INFO.append("thread_started:\"" + this.threadStarted+ "\"=>\"" + threadStarted + "\"\r\n");
            this.threadStarted = threadStarted;
        }
    }

    /**
     * 设置建立日期。
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
     * 设置修改时间。
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
     * 设置最后更新。
     */
    public void setLastUpdate(java.util.Date lastUpdate){
        if ((!String.valueOf(this.lastUpdate).equals(String.valueOf(lastUpdate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("last_update");
            this.UPDATED_INFO.append("last_update:\"" + this.lastUpdate+ "\"=>\"" + lastUpdate + "\"\r\n");
            this.lastUpdate = lastUpdate;
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
        sb.append("host_ip:\"" + this.hostIp + "\"\r\n");
        sb.append("app_name:\"" + this.appName + "\"\r\n");
        sb.append("app_version:\"" + this.appVersion + "\"\r\n");
        sb.append("app_host:\"" + this.appHost + "\"\r\n");
        sb.append("app_port:\"" + this.appPort + "\"\r\n");
        sb.append("task_project:\"" + this.taskProject + "\"\r\n");
        sb.append("run_target:\"" + this.runTarget + "\"\r\n");
        sb.append("croner_num:\"" + this.cronerNum + "\"\r\n");
        sb.append("croner_run_num:\"" + this.cronerRunNum + "\"\r\n");
        sb.append("croner_fail_num:\"" + this.cronerFailNum + "\"\r\n");
        sb.append("croner_run_time:\"" + this.cronerRunTime + "\"\r\n");
        sb.append("runner_num:\"" + this.runnerNum + "\"\r\n");
        sb.append("runner_run_num:\"" + this.runnerRunNum + "\"\r\n");
        sb.append("runner_fail_num:\"" + this.runnerFailNum + "\"\r\n");
        sb.append("runner_run_time:\"" + this.runnerRunTime + "\"\r\n");
        sb.append("jvm_mem_max:\"" + this.jvmMemMax + "\"\r\n");
        sb.append("jvm_mem_total:\"" + this.jvmMemTotal + "\"\r\n");
        sb.append("jvm_mem_free:\"" + this.jvmMemFree + "\"\r\n");
        sb.append("thread_active:\"" + this.threadActive + "\"\r\n");
        sb.append("thread_peak:\"" + this.threadPeak + "\"\r\n");
        sb.append("thread_daemon:\"" + this.threadDaemon + "\"\r\n");
        sb.append("thread_started:\"" + this.threadStarted + "\"\r\n");
        sb.append("create_date:\"" + this.createDate + "\"\r\n");
        sb.append("modify_date:\"" + this.modifyDate + "\"\r\n");
        sb.append("last_update:\"" + this.lastUpdate + "\"\r\n");
        sb.append("state:\"" + this.state + "\"\r\n");
        return sb.toString();
    }

}