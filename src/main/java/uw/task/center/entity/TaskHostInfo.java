package uw.task.center.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @Schema(title = "id", description = "id", maxLength=19, nullable=false )
    private long id;

    /**
     * 主机注册IP
     */
    @ColumnMeta(columnName="host_ip", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "主机注册IP", description = "主机注册IP", maxLength=100, nullable=true )
    private String hostIp;

    /**
     * 应用名称
     */
    @ColumnMeta(columnName="app_name", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "应用名称", description = "应用名称", maxLength=100, nullable=true )
    private String appName;

    /**
     * 应用版本
     */
    @ColumnMeta(columnName="app_version", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "应用版本", description = "应用版本", maxLength=100, nullable=true )
    private String appVersion;

    /**
     * app主机
     */
    @ColumnMeta(columnName="app_host", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "app主机", description = "app主机", maxLength=100, nullable=true )
    private String appHost;

    /**
     * app端口
     */
    @ColumnMeta(columnName="app_port", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "app端口", description = "app端口", maxLength=10, nullable=true )
    private int appPort;

    /**
     * 任务项目
     */
    @ColumnMeta(columnName="task_project", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "任务项目", description = "任务项目", maxLength=100, nullable=true )
    private String taskProject;

    /**
     * 运行目标
     */
    @ColumnMeta(columnName="run_target", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "运行目标", description = "运行目标", maxLength=100, nullable=true )
    private String runTarget;

    /**
     * 定时任务数量
     */
    @ColumnMeta(columnName="croner_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "定时任务数量", description = "定时任务数量", maxLength=10, nullable=true )
    private int cronerNum;

    /**
     * 统计运行次数
     */
    @ColumnMeta(columnName="croner_run_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行次数", description = "统计运行次数", maxLength=10, nullable=true )
    private int cronerRunNum;

    /**
     * 统计运行失败次数
     */
    @ColumnMeta(columnName="croner_fail_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数", maxLength=10, nullable=true )
    private int cronerFailNum;

    /**
     * 统计总时间毫秒数
     */
    @ColumnMeta(columnName="croner_run_time", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数", maxLength=19, nullable=true )
    private long cronerRunTime;

    /**
     * 队列任务数量
     */
    @ColumnMeta(columnName="runner_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "队列任务数量", description = "队列任务数量", maxLength=10, nullable=true )
    private int runnerNum;

    /**
     * 统计运行次数
     */
    @ColumnMeta(columnName="runner_run_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行次数", description = "统计运行次数", maxLength=10, nullable=true )
    private int runnerRunNum;

    /**
     * 统计运行失败次数
     */
    @ColumnMeta(columnName="runner_fail_num", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数", maxLength=10, nullable=true )
    private int runnerFailNum;

    /**
     * 统计总时间毫秒数
     */
    @ColumnMeta(columnName="runner_run_time", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数", maxLength=19, nullable=true )
    private long runnerRunTime;

    /**
     * jvm内存总数
     */
    @ColumnMeta(columnName="jvm_mem_max", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "jvm内存总数", description = "jvm内存总数", maxLength=19, nullable=true )
    private long jvmMemMax;

    /**
     * jvm内存总数
     */
    @ColumnMeta(columnName="jvm_mem_total", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "jvm内存总数", description = "jvm内存总数", maxLength=19, nullable=true )
    private long jvmMemTotal;

    /**
     * jvm空闲内存
     */
    @ColumnMeta(columnName="jvm_mem_free", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "jvm空闲内存", description = "jvm空闲内存", maxLength=19, nullable=true )
    private long jvmMemFree;

    /**
     * 活跃线程
     */
    @ColumnMeta(columnName="thread_active", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "活跃线程", description = "活跃线程", maxLength=10, nullable=true )
    private int threadActive;

    /**
     * 峰值线程
     */
    @ColumnMeta(columnName="thread_peak", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "峰值线程", description = "峰值线程", maxLength=10, nullable=true )
    private int threadPeak;

    /**
     * 守护线程
     */
    @ColumnMeta(columnName="thread_daemon", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "守护线程", description = "守护线程", maxLength=10, nullable=true )
    private int threadDaemon;

    /**
     * 累计启动线程
     */
    @ColumnMeta(columnName="thread_started", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "累计启动线程", description = "累计启动线程", maxLength=19, nullable=true )
    private long threadStarted;

    /**
     * 建立日期
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "建立日期", description = "建立日期", maxLength=23, nullable=true )
    private java.util.Date createDate;

    /**
     * 修改时间
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "修改时间", description = "修改时间", maxLength=23, nullable=true )
    private java.util.Date modifyDate;

    /**
     * 最后更新
     */
    @ColumnMeta(columnName="last_update", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "最后更新", description = "最后更新", maxLength=23, nullable=true )
    private java.util.Date lastUpdate;

    /**
     * 状态
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态", description = "状态", maxLength=10, nullable=true )
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
     * 获得实体的表名。
     */
    @Override
    public String ENTITY_TABLE(){
         return "task_host_info";
       }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
          return "task主机信息";
       }

    /**
     * 获得主键
     */
    @Override
    public Serializable ENTITY_ID(){
          return getId();
       }


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
        this.UPDATED_INFO = new StringBuilder("表task_host_info主键\"" + 
        this.id+ "\"更新为:\r\n");
    }


    /**
     * 获取id。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获取主机注册IP。
     */
    public String getHostIp(){
        return this.hostIp;
    }

    /**
     * 获取应用名称。
     */
    public String getAppName(){
        return this.appName;
    }

    /**
     * 获取应用版本。
     */
    public String getAppVersion(){
        return this.appVersion;
    }

    /**
     * 获取app主机。
     */
    public String getAppHost(){
        return this.appHost;
    }

    /**
     * 获取app端口。
     */
    public int getAppPort(){
        return this.appPort;
    }

    /**
     * 获取任务项目。
     */
    public String getTaskProject(){
        return this.taskProject;
    }

    /**
     * 获取运行目标。
     */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
     * 获取定时任务数量。
     */
    public int getCronerNum(){
        return this.cronerNum;
    }

    /**
     * 获取统计运行次数。
     */
    public int getCronerRunNum(){
        return this.cronerRunNum;
    }

    /**
     * 获取统计运行失败次数。
     */
    public int getCronerFailNum(){
        return this.cronerFailNum;
    }

    /**
     * 获取统计总时间毫秒数。
     */
    public long getCronerRunTime(){
        return this.cronerRunTime;
    }

    /**
     * 获取队列任务数量。
     */
    public int getRunnerNum(){
        return this.runnerNum;
    }

    /**
     * 获取统计运行次数。
     */
    public int getRunnerRunNum(){
        return this.runnerRunNum;
    }

    /**
     * 获取统计运行失败次数。
     */
    public int getRunnerFailNum(){
        return this.runnerFailNum;
    }

    /**
     * 获取统计总时间毫秒数。
     */
    public long getRunnerRunTime(){
        return this.runnerRunTime;
    }

    /**
     * 获取jvm内存总数。
     */
    public long getJvmMemMax(){
        return this.jvmMemMax;
    }

    /**
     * 获取jvm内存总数。
     */
    public long getJvmMemTotal(){
        return this.jvmMemTotal;
    }

    /**
     * 获取jvm空闲内存。
     */
    public long getJvmMemFree(){
        return this.jvmMemFree;
    }

    /**
     * 获取活跃线程。
     */
    public int getThreadActive(){
        return this.threadActive;
    }

    /**
     * 获取峰值线程。
     */
    public int getThreadPeak(){
        return this.threadPeak;
    }

    /**
     * 获取守护线程。
     */
    public int getThreadDaemon(){
        return this.threadDaemon;
    }

    /**
     * 获取累计启动线程。
     */
    public long getThreadStarted(){
        return this.threadStarted;
    }

    /**
     * 获取建立日期。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获取修改时间。
     */
    public java.util.Date getModifyDate(){
        return this.modifyDate;
    }

    /**
     * 获取最后更新。
     */
    public java.util.Date getLastUpdate(){
        return this.lastUpdate;
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
        if (!Objects.equals(this.id, id)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("id");
            this.UPDATED_INFO.append("id:\"").append(this.id).append("\"=>\"").append(id).append("\"\n");
            this.id = id;
        }
    }

    /**
     *  设置id链式调用。
     */
    public TaskHostInfo id(long id){
        setId(id);
        return this;
        }

    /**
     * 设置主机注册IP。
     */
    public void setHostIp(String hostIp){
        if (!Objects.equals(this.hostIp, hostIp)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("host_ip");
            this.UPDATED_INFO.append("host_ip:\"").append(this.hostIp).append("\"=>\"").append(hostIp).append("\"\n");
            this.hostIp = hostIp;
        }
    }

    /**
     *  设置主机注册IP链式调用。
     */
    public TaskHostInfo hostIp(String hostIp){
        setHostIp(hostIp);
        return this;
        }

    /**
     * 设置应用名称。
     */
    public void setAppName(String appName){
        if (!Objects.equals(this.appName, appName)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_name");
            this.UPDATED_INFO.append("app_name:\"").append(this.appName).append("\"=>\"").append(appName).append("\"\n");
            this.appName = appName;
        }
    }

    /**
     *  设置应用名称链式调用。
     */
    public TaskHostInfo appName(String appName){
        setAppName(appName);
        return this;
        }

    /**
     * 设置应用版本。
     */
    public void setAppVersion(String appVersion){
        if (!Objects.equals(this.appVersion, appVersion)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_version");
            this.UPDATED_INFO.append("app_version:\"").append(this.appVersion).append("\"=>\"").append(appVersion).append("\"\n");
            this.appVersion = appVersion;
        }
    }

    /**
     *  设置应用版本链式调用。
     */
    public TaskHostInfo appVersion(String appVersion){
        setAppVersion(appVersion);
        return this;
        }

    /**
     * 设置app主机。
     */
    public void setAppHost(String appHost){
        if (!Objects.equals(this.appHost, appHost)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_host");
            this.UPDATED_INFO.append("app_host:\"").append(this.appHost).append("\"=>\"").append(appHost).append("\"\n");
            this.appHost = appHost;
        }
    }

    /**
     *  设置app主机链式调用。
     */
    public TaskHostInfo appHost(String appHost){
        setAppHost(appHost);
        return this;
        }

    /**
     * 设置app端口。
     */
    public void setAppPort(int appPort){
        if (!Objects.equals(this.appPort, appPort)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("app_port");
            this.UPDATED_INFO.append("app_port:\"").append(this.appPort).append("\"=>\"").append(appPort).append("\"\n");
            this.appPort = appPort;
        }
    }

    /**
     *  设置app端口链式调用。
     */
    public TaskHostInfo appPort(int appPort){
        setAppPort(appPort);
        return this;
        }

    /**
     * 设置任务项目。
     */
    public void setTaskProject(String taskProject){
        if (!Objects.equals(this.taskProject, taskProject)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("task_project");
            this.UPDATED_INFO.append("task_project:\"").append(this.taskProject).append("\"=>\"").append(taskProject).append("\"\n");
            this.taskProject = taskProject;
        }
    }

    /**
     *  设置任务项目链式调用。
     */
    public TaskHostInfo taskProject(String taskProject){
        setTaskProject(taskProject);
        return this;
        }

    /**
     * 设置运行目标。
     */
    public void setRunTarget(String runTarget){
        if (!Objects.equals(this.runTarget, runTarget)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("run_target");
            this.UPDATED_INFO.append("run_target:\"").append(this.runTarget).append("\"=>\"").append(runTarget).append("\"\n");
            this.runTarget = runTarget;
        }
    }

    /**
     *  设置运行目标链式调用。
     */
    public TaskHostInfo runTarget(String runTarget){
        setRunTarget(runTarget);
        return this;
        }

    /**
     * 设置定时任务数量。
     */
    public void setCronerNum(int cronerNum){
        if (!Objects.equals(this.cronerNum, cronerNum)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_num");
            this.UPDATED_INFO.append("croner_num:\"").append(this.cronerNum).append("\"=>\"").append(cronerNum).append("\"\n");
            this.cronerNum = cronerNum;
        }
    }

    /**
     *  设置定时任务数量链式调用。
     */
    public TaskHostInfo cronerNum(int cronerNum){
        setCronerNum(cronerNum);
        return this;
        }

    /**
     * 设置统计运行次数。
     */
    public void setCronerRunNum(int cronerRunNum){
        if (!Objects.equals(this.cronerRunNum, cronerRunNum)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_run_num");
            this.UPDATED_INFO.append("croner_run_num:\"").append(this.cronerRunNum).append("\"=>\"").append(cronerRunNum).append("\"\n");
            this.cronerRunNum = cronerRunNum;
        }
    }

    /**
     *  设置统计运行次数链式调用。
     */
    public TaskHostInfo cronerRunNum(int cronerRunNum){
        setCronerRunNum(cronerRunNum);
        return this;
        }

    /**
     * 设置统计运行失败次数。
     */
    public void setCronerFailNum(int cronerFailNum){
        if (!Objects.equals(this.cronerFailNum, cronerFailNum)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_fail_num");
            this.UPDATED_INFO.append("croner_fail_num:\"").append(this.cronerFailNum).append("\"=>\"").append(cronerFailNum).append("\"\n");
            this.cronerFailNum = cronerFailNum;
        }
    }

    /**
     *  设置统计运行失败次数链式调用。
     */
    public TaskHostInfo cronerFailNum(int cronerFailNum){
        setCronerFailNum(cronerFailNum);
        return this;
        }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setCronerRunTime(long cronerRunTime){
        if (!Objects.equals(this.cronerRunTime, cronerRunTime)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("croner_run_time");
            this.UPDATED_INFO.append("croner_run_time:\"").append(this.cronerRunTime).append("\"=>\"").append(cronerRunTime).append("\"\n");
            this.cronerRunTime = cronerRunTime;
        }
    }

    /**
     *  设置统计总时间毫秒数链式调用。
     */
    public TaskHostInfo cronerRunTime(long cronerRunTime){
        setCronerRunTime(cronerRunTime);
        return this;
        }

    /**
     * 设置队列任务数量。
     */
    public void setRunnerNum(int runnerNum){
        if (!Objects.equals(this.runnerNum, runnerNum)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_num");
            this.UPDATED_INFO.append("runner_num:\"").append(this.runnerNum).append("\"=>\"").append(runnerNum).append("\"\n");
            this.runnerNum = runnerNum;
        }
    }

    /**
     *  设置队列任务数量链式调用。
     */
    public TaskHostInfo runnerNum(int runnerNum){
        setRunnerNum(runnerNum);
        return this;
        }

    /**
     * 设置统计运行次数。
     */
    public void setRunnerRunNum(int runnerRunNum){
        if (!Objects.equals(this.runnerRunNum, runnerRunNum)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_run_num");
            this.UPDATED_INFO.append("runner_run_num:\"").append(this.runnerRunNum).append("\"=>\"").append(runnerRunNum).append("\"\n");
            this.runnerRunNum = runnerRunNum;
        }
    }

    /**
     *  设置统计运行次数链式调用。
     */
    public TaskHostInfo runnerRunNum(int runnerRunNum){
        setRunnerRunNum(runnerRunNum);
        return this;
        }

    /**
     * 设置统计运行失败次数。
     */
    public void setRunnerFailNum(int runnerFailNum){
        if (!Objects.equals(this.runnerFailNum, runnerFailNum)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_fail_num");
            this.UPDATED_INFO.append("runner_fail_num:\"").append(this.runnerFailNum).append("\"=>\"").append(runnerFailNum).append("\"\n");
            this.runnerFailNum = runnerFailNum;
        }
    }

    /**
     *  设置统计运行失败次数链式调用。
     */
    public TaskHostInfo runnerFailNum(int runnerFailNum){
        setRunnerFailNum(runnerFailNum);
        return this;
        }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setRunnerRunTime(long runnerRunTime){
        if (!Objects.equals(this.runnerRunTime, runnerRunTime)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("runner_run_time");
            this.UPDATED_INFO.append("runner_run_time:\"").append(this.runnerRunTime).append("\"=>\"").append(runnerRunTime).append("\"\n");
            this.runnerRunTime = runnerRunTime;
        }
    }

    /**
     *  设置统计总时间毫秒数链式调用。
     */
    public TaskHostInfo runnerRunTime(long runnerRunTime){
        setRunnerRunTime(runnerRunTime);
        return this;
        }

    /**
     * 设置jvm内存总数。
     */
    public void setJvmMemMax(long jvmMemMax){
        if (!Objects.equals(this.jvmMemMax, jvmMemMax)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("jvm_mem_max");
            this.UPDATED_INFO.append("jvm_mem_max:\"").append(this.jvmMemMax).append("\"=>\"").append(jvmMemMax).append("\"\n");
            this.jvmMemMax = jvmMemMax;
        }
    }

    /**
     *  设置jvm内存总数链式调用。
     */
    public TaskHostInfo jvmMemMax(long jvmMemMax){
        setJvmMemMax(jvmMemMax);
        return this;
        }

    /**
     * 设置jvm内存总数。
     */
    public void setJvmMemTotal(long jvmMemTotal){
        if (!Objects.equals(this.jvmMemTotal, jvmMemTotal)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("jvm_mem_total");
            this.UPDATED_INFO.append("jvm_mem_total:\"").append(this.jvmMemTotal).append("\"=>\"").append(jvmMemTotal).append("\"\n");
            this.jvmMemTotal = jvmMemTotal;
        }
    }

    /**
     *  设置jvm内存总数链式调用。
     */
    public TaskHostInfo jvmMemTotal(long jvmMemTotal){
        setJvmMemTotal(jvmMemTotal);
        return this;
        }

    /**
     * 设置jvm空闲内存。
     */
    public void setJvmMemFree(long jvmMemFree){
        if (!Objects.equals(this.jvmMemFree, jvmMemFree)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("jvm_mem_free");
            this.UPDATED_INFO.append("jvm_mem_free:\"").append(this.jvmMemFree).append("\"=>\"").append(jvmMemFree).append("\"\n");
            this.jvmMemFree = jvmMemFree;
        }
    }

    /**
     *  设置jvm空闲内存链式调用。
     */
    public TaskHostInfo jvmMemFree(long jvmMemFree){
        setJvmMemFree(jvmMemFree);
        return this;
        }

    /**
     * 设置活跃线程。
     */
    public void setThreadActive(int threadActive){
        if (!Objects.equals(this.threadActive, threadActive)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_active");
            this.UPDATED_INFO.append("thread_active:\"").append(this.threadActive).append("\"=>\"").append(threadActive).append("\"\n");
            this.threadActive = threadActive;
        }
    }

    /**
     *  设置活跃线程链式调用。
     */
    public TaskHostInfo threadActive(int threadActive){
        setThreadActive(threadActive);
        return this;
        }

    /**
     * 设置峰值线程。
     */
    public void setThreadPeak(int threadPeak){
        if (!Objects.equals(this.threadPeak, threadPeak)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_peak");
            this.UPDATED_INFO.append("thread_peak:\"").append(this.threadPeak).append("\"=>\"").append(threadPeak).append("\"\n");
            this.threadPeak = threadPeak;
        }
    }

    /**
     *  设置峰值线程链式调用。
     */
    public TaskHostInfo threadPeak(int threadPeak){
        setThreadPeak(threadPeak);
        return this;
        }

    /**
     * 设置守护线程。
     */
    public void setThreadDaemon(int threadDaemon){
        if (!Objects.equals(this.threadDaemon, threadDaemon)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_daemon");
            this.UPDATED_INFO.append("thread_daemon:\"").append(this.threadDaemon).append("\"=>\"").append(threadDaemon).append("\"\n");
            this.threadDaemon = threadDaemon;
        }
    }

    /**
     *  设置守护线程链式调用。
     */
    public TaskHostInfo threadDaemon(int threadDaemon){
        setThreadDaemon(threadDaemon);
        return this;
        }

    /**
     * 设置累计启动线程。
     */
    public void setThreadStarted(long threadStarted){
        if (!Objects.equals(this.threadStarted, threadStarted)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("thread_started");
            this.UPDATED_INFO.append("thread_started:\"").append(this.threadStarted).append("\"=>\"").append(threadStarted).append("\"\n");
            this.threadStarted = threadStarted;
        }
    }

    /**
     *  设置累计启动线程链式调用。
     */
    public TaskHostInfo threadStarted(long threadStarted){
        setThreadStarted(threadStarted);
        return this;
        }

    /**
     * 设置建立日期。
     */
    public void setCreateDate(java.util.Date createDate){
        if (!Objects.equals(this.createDate, createDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("create_date");
            this.UPDATED_INFO.append("create_date:\"").append(this.createDate).append("\"=>\"").append(createDate).append("\"\n");
            this.createDate = createDate;
        }
    }

    /**
     *  设置建立日期链式调用。
     */
    public TaskHostInfo createDate(java.util.Date createDate){
        setCreateDate(createDate);
        return this;
        }

    /**
     * 设置修改时间。
     */
    public void setModifyDate(java.util.Date modifyDate){
        if (!Objects.equals(this.modifyDate, modifyDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("modify_date");
            this.UPDATED_INFO.append("modify_date:\"").append(this.modifyDate).append("\"=>\"").append(modifyDate).append("\"\n");
            this.modifyDate = modifyDate;
        }
    }

    /**
     *  设置修改时间链式调用。
     */
    public TaskHostInfo modifyDate(java.util.Date modifyDate){
        setModifyDate(modifyDate);
        return this;
        }

    /**
     * 设置最后更新。
     */
    public void setLastUpdate(java.util.Date lastUpdate){
        if (!Objects.equals(this.lastUpdate, lastUpdate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("last_update");
            this.UPDATED_INFO.append("last_update:\"").append(this.lastUpdate).append("\"=>\"").append(lastUpdate).append("\"\n");
            this.lastUpdate = lastUpdate;
        }
    }

    /**
     *  设置最后更新链式调用。
     */
    public TaskHostInfo lastUpdate(java.util.Date lastUpdate){
        setLastUpdate(lastUpdate);
        return this;
        }

    /**
     * 设置状态。
     */
    public void setState(int state){
        if (!Objects.equals(this.state, state)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("state");
            this.UPDATED_INFO.append("state:\"").append(this.state).append("\"=>\"").append(state).append("\"\n");
            this.state = state;
        }
    }

    /**
     *  设置状态链式调用。
     */
    public TaskHostInfo state(int state){
        setState(state);
        return this;
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