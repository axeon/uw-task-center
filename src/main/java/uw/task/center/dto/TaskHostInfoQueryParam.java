package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
* task主机信息列表查询参数。
*/
@Schema(title = "task主机信息列表查询参数", description = "task主机信息列表查询参数")
public class TaskHostInfoQueryParam extends PageQueryParam{


    /**
    * id
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * 主机注册IP
    */
    @QueryMeta(expr = "host_ip like ?")
    @Schema(title="主机注册IP", description = "主机注册IP")
    private String hostIp;

    /**
    * 应用名称
    */
    @QueryMeta(expr = "app_name like ?")
    @Schema(title="应用名称", description = "应用名称")
    private String appName;

    /**
    * 应用版本
    */
    @QueryMeta(expr = "app_version like ?")
    @Schema(title="应用版本", description = "应用版本")
    private String appVersion;

    /**
    * app主机
    */
    @QueryMeta(expr = "app_host like ?")
    @Schema(title="app主机", description = "app主机")
    private String appHost;

    /**
    * app端口
    */
    @QueryMeta(expr = "app_port=?")
    @Schema(title="app端口", description = "app端口")
    private Integer appPort;

    /**
    * app端口范围
    */
    @QueryMeta(expr = "app_port between ? and ?")
    @Schema(title="app端口范围", description = "app端口范围")
    private Integer[] appPortRange;

    /**
    * 任务项目
    */
    @QueryMeta(expr = "task_project like ?")
    @Schema(title="任务项目", description = "任务项目")
    private String taskProject;

    /**
    * 运行目标
    */
    @QueryMeta(expr = "run_target like ?")
    @Schema(title="运行目标", description = "运行目标")
    private String runTarget;

    /**
    * 定时任务数量
    */
    @QueryMeta(expr = "croner_num=?")
    @Schema(title="定时任务数量", description = "定时任务数量")
    private Integer cronerNum;

    /**
    * 定时任务数量范围
    */
    @QueryMeta(expr = "croner_num between ? and ?")
    @Schema(title="定时任务数量范围", description = "定时任务数量范围")
    private Integer[] cronerNumRange;

    /**
    * 统计运行次数
    */
    @QueryMeta(expr = "croner_run_num=?")
    @Schema(title="统计运行次数", description = "统计运行次数")
    private Integer cronerRunNum;

    /**
    * 统计运行次数范围
    */
    @QueryMeta(expr = "croner_run_num between ? and ?")
    @Schema(title="统计运行次数范围", description = "统计运行次数范围")
    private Integer[] cronerRunNumRange;

    /**
    * 统计运行失败次数
    */
    @QueryMeta(expr = "croner_fail_num=?")
    @Schema(title="统计运行失败次数", description = "统计运行失败次数")
    private Integer cronerFailNum;

    /**
    * 统计运行失败次数范围
    */
    @QueryMeta(expr = "croner_fail_num between ? and ?")
    @Schema(title="统计运行失败次数范围", description = "统计运行失败次数范围")
    private Integer[] cronerFailNumRange;

    /**
    * 统计总时间毫秒数
    */
    @QueryMeta(expr = "croner_run_time=?")
    @Schema(title="统计总时间毫秒数", description = "统计总时间毫秒数")
    private Long cronerRunTime;

    /**
    * 统计总时间毫秒数范围
    */
    @QueryMeta(expr = "croner_run_time between ? and ?")
    @Schema(title="统计总时间毫秒数范围", description = "统计总时间毫秒数范围")
    private Long[] cronerRunTimeRange;

    /**
    * 队列任务数量
    */
    @QueryMeta(expr = "runner_num=?")
    @Schema(title="队列任务数量", description = "队列任务数量")
    private Integer runnerNum;

    /**
    * 队列任务数量范围
    */
    @QueryMeta(expr = "runner_num between ? and ?")
    @Schema(title="队列任务数量范围", description = "队列任务数量范围")
    private Integer[] runnerNumRange;

    /**
    * 统计运行次数
    */
    @QueryMeta(expr = "runner_run_num=?")
    @Schema(title="统计运行次数", description = "统计运行次数")
    private Integer runnerRunNum;

    /**
    * 统计运行次数范围
    */
    @QueryMeta(expr = "runner_run_num between ? and ?")
    @Schema(title="统计运行次数范围", description = "统计运行次数范围")
    private Integer[] runnerRunNumRange;

    /**
    * 统计运行失败次数
    */
    @QueryMeta(expr = "runner_fail_num=?")
    @Schema(title="统计运行失败次数", description = "统计运行失败次数")
    private Integer runnerFailNum;

    /**
    * 统计运行失败次数范围
    */
    @QueryMeta(expr = "runner_fail_num between ? and ?")
    @Schema(title="统计运行失败次数范围", description = "统计运行失败次数范围")
    private Integer[] runnerFailNumRange;

    /**
    * 统计总时间毫秒数
    */
    @QueryMeta(expr = "runner_run_time=?")
    @Schema(title="统计总时间毫秒数", description = "统计总时间毫秒数")
    private Long runnerRunTime;

    /**
    * 统计总时间毫秒数范围
    */
    @QueryMeta(expr = "runner_run_time between ? and ?")
    @Schema(title="统计总时间毫秒数范围", description = "统计总时间毫秒数范围")
    private Long[] runnerRunTimeRange;

    /**
    * jvm内存总数
    */
    @QueryMeta(expr = "jvm_mem_max=?")
    @Schema(title="jvm内存总数", description = "jvm内存总数")
    private Long jvmMemMax;

    /**
    * jvm内存总数范围
    */
    @QueryMeta(expr = "jvm_mem_max between ? and ?")
    @Schema(title="jvm内存总数范围", description = "jvm内存总数范围")
    private Long[] jvmMemMaxRange;

    /**
    * jvm内存总数
    */
    @QueryMeta(expr = "jvm_mem_total=?")
    @Schema(title="jvm内存总数", description = "jvm内存总数")
    private Long jvmMemTotal;

    /**
    * jvm内存总数范围
    */
    @QueryMeta(expr = "jvm_mem_total between ? and ?")
    @Schema(title="jvm内存总数范围", description = "jvm内存总数范围")
    private Long[] jvmMemTotalRange;

    /**
    * jvm空闲内存
    */
    @QueryMeta(expr = "jvm_mem_free=?")
    @Schema(title="jvm空闲内存", description = "jvm空闲内存")
    private Long jvmMemFree;

    /**
    * jvm空闲内存范围
    */
    @QueryMeta(expr = "jvm_mem_free between ? and ?")
    @Schema(title="jvm空闲内存范围", description = "jvm空闲内存范围")
    private Long[] jvmMemFreeRange;

    /**
    * 活跃线程
    */
    @QueryMeta(expr = "thread_active=?")
    @Schema(title="活跃线程", description = "活跃线程")
    private Integer threadActive;

    /**
    * 活跃线程范围
    */
    @QueryMeta(expr = "thread_active between ? and ?")
    @Schema(title="活跃线程范围", description = "活跃线程范围")
    private Integer[] threadActiveRange;

    /**
    * 峰值线程
    */
    @QueryMeta(expr = "thread_peak=?")
    @Schema(title="峰值线程", description = "峰值线程")
    private Integer threadPeak;

    /**
    * 峰值线程范围
    */
    @QueryMeta(expr = "thread_peak between ? and ?")
    @Schema(title="峰值线程范围", description = "峰值线程范围")
    private Integer[] threadPeakRange;

    /**
    * 守护线程
    */
    @QueryMeta(expr = "thread_daemon=?")
    @Schema(title="守护线程", description = "守护线程")
    private Integer threadDaemon;

    /**
    * 守护线程范围
    */
    @QueryMeta(expr = "thread_daemon between ? and ?")
    @Schema(title="守护线程范围", description = "守护线程范围")
    private Integer[] threadDaemonRange;

    /**
    * 累计启动线程
    */
    @QueryMeta(expr = "thread_started=?")
    @Schema(title="累计启动线程", description = "累计启动线程")
    private Long threadStarted;

    /**
    * 累计启动线程范围
    */
    @QueryMeta(expr = "thread_started between ? and ?")
    @Schema(title="累计启动线程范围", description = "累计启动线程范围")
    private Long[] threadStartedRange;
    /**
    * 建立日期范围
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="建立日期范围", description = "建立日期范围")
    private Date[] createDateRange;

    /**
    * 修改时间范围
    */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title="修改时间范围", description = "修改时间范围")
    private Date[] modifyDateRange;

    /**
    * 最后更新范围
    */
    @QueryMeta(expr = "last_update between ? and ?")
    @Schema(title="最后更新范围", description = "最后更新范围")
    private Date[] lastUpdateRange;

    /**
    * 状态
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态", description = "状态")
    private Integer state;

    /**
    * 正常状态
    */
    @QueryMeta(expr = "state>-1")
    @Schema(title="正常状态", description = "正常状态")
    private Boolean stateOn;

    /**
    * 状态数组
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="状态数组", description = "状态数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
    * 状态运算条件。
    * 可以使用运算符号。
    */
    @QueryMeta(expr = "state ?")
    @Schema(title="状态运算条件", description = "状态运算条件，可使用><=!比较运算符。")
    private String stateOp;


    /**
    * 获得id。
    */
    public Long getId(){
        return this.id;
    }

    /**
    * 设置id。
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获得主机注册IP。
    */
    public String getHostIp(){
        return this.hostIp;
    }

    /**
    * 设置主机注册IP。
    */
    public void setHostIp(String hostIp){
        this.hostIp = hostIp;
    }

    /**
    * 获得应用名称。
    */
    public String getAppName(){
        return this.appName;
    }

    /**
    * 设置应用名称。
    */
    public void setAppName(String appName){
        this.appName = appName;
    }

    /**
    * 获得应用版本。
    */
    public String getAppVersion(){
        return this.appVersion;
    }

    /**
    * 设置应用版本。
    */
    public void setAppVersion(String appVersion){
        this.appVersion = appVersion;
    }

    /**
    * 获得app主机。
    */
    public String getAppHost(){
        return this.appHost;
    }

    /**
    * 设置app主机。
    */
    public void setAppHost(String appHost){
        this.appHost = appHost;
    }

    /**
    * 获得app端口。
    */
    public Integer getAppPort(){
        return this.appPort;
    }

    /**
    * 设置app端口。
    */
    public void setAppPort(Integer appPort){
        this.appPort = appPort;
    }

    /**
    * 获得app端口范围。
    */
    public Integer[] getAppPortRange(){
        return this.appPortRange;
    }

    /**
    * 设置app端口范围。
    */
    public void setAppPortRange(Integer[] appPortRange){
        this.appPortRange = appPortRange;
    }

    /**
    * 获得任务项目。
    */
    public String getTaskProject(){
        return this.taskProject;
    }

    /**
    * 设置任务项目。
    */
    public void setTaskProject(String taskProject){
        this.taskProject = taskProject;
    }

    /**
    * 获得运行目标。
    */
    public String getRunTarget(){
        return this.runTarget;
    }

    /**
    * 设置运行目标。
    */
    public void setRunTarget(String runTarget){
        this.runTarget = runTarget;
    }

    /**
    * 获得定时任务数量。
    */
    public Integer getCronerNum(){
        return this.cronerNum;
    }

    /**
    * 设置定时任务数量。
    */
    public void setCronerNum(Integer cronerNum){
        this.cronerNum = cronerNum;
    }

    /**
    * 获得定时任务数量范围。
    */
    public Integer[] getCronerNumRange(){
        return this.cronerNumRange;
    }

    /**
    * 设置定时任务数量范围。
    */
    public void setCronerNumRange(Integer[] cronerNumRange){
        this.cronerNumRange = cronerNumRange;
    }

    /**
    * 获得统计运行次数。
    */
    public Integer getCronerRunNum(){
        return this.cronerRunNum;
    }

    /**
    * 设置统计运行次数。
    */
    public void setCronerRunNum(Integer cronerRunNum){
        this.cronerRunNum = cronerRunNum;
    }

    /**
    * 获得统计运行次数范围。
    */
    public Integer[] getCronerRunNumRange(){
        return this.cronerRunNumRange;
    }

    /**
    * 设置统计运行次数范围。
    */
    public void setCronerRunNumRange(Integer[] cronerRunNumRange){
        this.cronerRunNumRange = cronerRunNumRange;
    }

    /**
    * 获得统计运行失败次数。
    */
    public Integer getCronerFailNum(){
        return this.cronerFailNum;
    }

    /**
    * 设置统计运行失败次数。
    */
    public void setCronerFailNum(Integer cronerFailNum){
        this.cronerFailNum = cronerFailNum;
    }

    /**
    * 获得统计运行失败次数范围。
    */
    public Integer[] getCronerFailNumRange(){
        return this.cronerFailNumRange;
    }

    /**
    * 设置统计运行失败次数范围。
    */
    public void setCronerFailNumRange(Integer[] cronerFailNumRange){
        this.cronerFailNumRange = cronerFailNumRange;
    }

    /**
    * 获得统计总时间毫秒数。
    */
    public Long getCronerRunTime(){
        return this.cronerRunTime;
    }

    /**
    * 设置统计总时间毫秒数。
    */
    public void setCronerRunTime(Long cronerRunTime){
        this.cronerRunTime = cronerRunTime;
    }

    /**
    * 获得统计总时间毫秒数范围。
    */
    public Long[] getCronerRunTimeRange(){
        return this.cronerRunTimeRange;
    }

    /**
    * 设置统计总时间毫秒数范围。
    */
    public void setCronerRunTimeRange(Long[] cronerRunTimeRange){
        this.cronerRunTimeRange = cronerRunTimeRange;
    }

    /**
    * 获得队列任务数量。
    */
    public Integer getRunnerNum(){
        return this.runnerNum;
    }

    /**
    * 设置队列任务数量。
    */
    public void setRunnerNum(Integer runnerNum){
        this.runnerNum = runnerNum;
    }

    /**
    * 获得队列任务数量范围。
    */
    public Integer[] getRunnerNumRange(){
        return this.runnerNumRange;
    }

    /**
    * 设置队列任务数量范围。
    */
    public void setRunnerNumRange(Integer[] runnerNumRange){
        this.runnerNumRange = runnerNumRange;
    }

    /**
    * 获得统计运行次数。
    */
    public Integer getRunnerRunNum(){
        return this.runnerRunNum;
    }

    /**
    * 设置统计运行次数。
    */
    public void setRunnerRunNum(Integer runnerRunNum){
        this.runnerRunNum = runnerRunNum;
    }

    /**
    * 获得统计运行次数范围。
    */
    public Integer[] getRunnerRunNumRange(){
        return this.runnerRunNumRange;
    }

    /**
    * 设置统计运行次数范围。
    */
    public void setRunnerRunNumRange(Integer[] runnerRunNumRange){
        this.runnerRunNumRange = runnerRunNumRange;
    }

    /**
    * 获得统计运行失败次数。
    */
    public Integer getRunnerFailNum(){
        return this.runnerFailNum;
    }

    /**
    * 设置统计运行失败次数。
    */
    public void setRunnerFailNum(Integer runnerFailNum){
        this.runnerFailNum = runnerFailNum;
    }

    /**
    * 获得统计运行失败次数范围。
    */
    public Integer[] getRunnerFailNumRange(){
        return this.runnerFailNumRange;
    }

    /**
    * 设置统计运行失败次数范围。
    */
    public void setRunnerFailNumRange(Integer[] runnerFailNumRange){
        this.runnerFailNumRange = runnerFailNumRange;
    }

    /**
    * 获得统计总时间毫秒数。
    */
    public Long getRunnerRunTime(){
        return this.runnerRunTime;
    }

    /**
    * 设置统计总时间毫秒数。
    */
    public void setRunnerRunTime(Long runnerRunTime){
        this.runnerRunTime = runnerRunTime;
    }

    /**
    * 获得统计总时间毫秒数范围。
    */
    public Long[] getRunnerRunTimeRange(){
        return this.runnerRunTimeRange;
    }

    /**
    * 设置统计总时间毫秒数范围。
    */
    public void setRunnerRunTimeRange(Long[] runnerRunTimeRange){
        this.runnerRunTimeRange = runnerRunTimeRange;
    }

    /**
    * 获得jvm内存总数。
    */
    public Long getJvmMemMax(){
        return this.jvmMemMax;
    }

    /**
    * 设置jvm内存总数。
    */
    public void setJvmMemMax(Long jvmMemMax){
        this.jvmMemMax = jvmMemMax;
    }

    /**
    * 获得jvm内存总数范围。
    */
    public Long[] getJvmMemMaxRange(){
        return this.jvmMemMaxRange;
    }

    /**
    * 设置jvm内存总数范围。
    */
    public void setJvmMemMaxRange(Long[] jvmMemMaxRange){
        this.jvmMemMaxRange = jvmMemMaxRange;
    }

    /**
    * 获得jvm内存总数。
    */
    public Long getJvmMemTotal(){
        return this.jvmMemTotal;
    }

    /**
    * 设置jvm内存总数。
    */
    public void setJvmMemTotal(Long jvmMemTotal){
        this.jvmMemTotal = jvmMemTotal;
    }

    /**
    * 获得jvm内存总数范围。
    */
    public Long[] getJvmMemTotalRange(){
        return this.jvmMemTotalRange;
    }

    /**
    * 设置jvm内存总数范围。
    */
    public void setJvmMemTotalRange(Long[] jvmMemTotalRange){
        this.jvmMemTotalRange = jvmMemTotalRange;
    }

    /**
    * 获得jvm空闲内存。
    */
    public Long getJvmMemFree(){
        return this.jvmMemFree;
    }

    /**
    * 设置jvm空闲内存。
    */
    public void setJvmMemFree(Long jvmMemFree){
        this.jvmMemFree = jvmMemFree;
    }

    /**
    * 获得jvm空闲内存范围。
    */
    public Long[] getJvmMemFreeRange(){
        return this.jvmMemFreeRange;
    }

    /**
    * 设置jvm空闲内存范围。
    */
    public void setJvmMemFreeRange(Long[] jvmMemFreeRange){
        this.jvmMemFreeRange = jvmMemFreeRange;
    }

    /**
    * 获得活跃线程。
    */
    public Integer getThreadActive(){
        return this.threadActive;
    }

    /**
    * 设置活跃线程。
    */
    public void setThreadActive(Integer threadActive){
        this.threadActive = threadActive;
    }

    /**
    * 获得活跃线程范围。
    */
    public Integer[] getThreadActiveRange(){
        return this.threadActiveRange;
    }

    /**
    * 设置活跃线程范围。
    */
    public void setThreadActiveRange(Integer[] threadActiveRange){
        this.threadActiveRange = threadActiveRange;
    }

    /**
    * 获得峰值线程。
    */
    public Integer getThreadPeak(){
        return this.threadPeak;
    }

    /**
    * 设置峰值线程。
    */
    public void setThreadPeak(Integer threadPeak){
        this.threadPeak = threadPeak;
    }

    /**
    * 获得峰值线程范围。
    */
    public Integer[] getThreadPeakRange(){
        return this.threadPeakRange;
    }

    /**
    * 设置峰值线程范围。
    */
    public void setThreadPeakRange(Integer[] threadPeakRange){
        this.threadPeakRange = threadPeakRange;
    }

    /**
    * 获得守护线程。
    */
    public Integer getThreadDaemon(){
        return this.threadDaemon;
    }

    /**
    * 设置守护线程。
    */
    public void setThreadDaemon(Integer threadDaemon){
        this.threadDaemon = threadDaemon;
    }

    /**
    * 获得守护线程范围。
    */
    public Integer[] getThreadDaemonRange(){
        return this.threadDaemonRange;
    }

    /**
    * 设置守护线程范围。
    */
    public void setThreadDaemonRange(Integer[] threadDaemonRange){
        this.threadDaemonRange = threadDaemonRange;
    }

    /**
    * 获得累计启动线程。
    */
    public Long getThreadStarted(){
        return this.threadStarted;
    }

    /**
    * 设置累计启动线程。
    */
    public void setThreadStarted(Long threadStarted){
        this.threadStarted = threadStarted;
    }

    /**
    * 获得累计启动线程范围。
    */
    public Long[] getThreadStartedRange(){
        return this.threadStartedRange;
    }

    /**
    * 设置累计启动线程范围。
    */
    public void setThreadStartedRange(Long[] threadStartedRange){
        this.threadStartedRange = threadStartedRange;
    }
    /**
    * 获得建立日期范围。
    */
    public Date[] getCreateDateRange(){
        return this.createDateRange;
    }

    /**
    * 设置建立日期范围。
    */
    public void setCreateDateRange(Date[] createDateRange){
        this.createDateRange = createDateRange;
    }
    /**
    * 获得修改时间范围。
    */
    public Date[] getModifyDateRange(){
        return this.modifyDateRange;
    }

    /**
    * 设置修改时间范围。
    */
    public void setModifyDateRange(Date[] modifyDateRange){
        this.modifyDateRange = modifyDateRange;
    }
    /**
    * 获得最后更新范围。
    */
    public Date[] getLastUpdateRange(){
        return this.lastUpdateRange;
    }

    /**
    * 设置最后更新范围。
    */
    public void setLastUpdateRange(Date[] lastUpdateRange){
        this.lastUpdateRange = lastUpdateRange;
    }
    /**
    * 获得状态。
    */
    public Integer getState(){
        return this.state;
    }

    /**
    * 设置状态。
    */
    public void setState(Integer state){
        this.state = state;
    }

    /**
    * 获得正常状态。
    */
    public Boolean getStateOn(){
        return this.stateOn;
    }

    /**
    * 设置正常状态。
    */
    public void setStateOn(Boolean stateOn){
        this.stateOn = stateOn;
    }

    /**
    * 获得状态数组。
    */
    public Integer[] getStates(){
        return this.states;
    }

    /**
    * 设置状态数组。
    */
    public void setStates(Integer[] states){
        this.states = states;
    }

    /**
    * 获得状态运算条件。
    */
    public String getStateOp(){
        return this.stateOp;
    }

    /**
    * 设置状态运算条件。
    */
    public void setStateOp(String stateOp){
        this.stateOp = stateOp;
    }

}