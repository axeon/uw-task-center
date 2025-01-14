package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * task主机信息列表查询参数。
 */
@Schema(title = "task主机信息列表查询参数", description = "task主机信息列表查询参数")
public class TaskHostInfoQueryParam extends PageQueryParam {

    /**
     * id。
     */
    @QueryMeta(expr = "id=?")
    @Schema(title = "id", description = "id")
    private Long id;
    /**
     * 主机注册IP。
     */
    @QueryMeta(expr = "host_ip like ?")
    @Schema(title = "主机注册IP", description = "主机注册IP")
    private String hostIp;
    /**
     * 应用名称。
     */
    @QueryMeta(expr = "app_name like ?")
    @Schema(title = "应用名称", description = "应用名称")
    private String appName;
    /**
     * 应用版本。
     */
    @QueryMeta(expr = "app_version like ?")
    @Schema(title = "应用版本", description = "应用版本")
    private String appVersion;
    /**
     * app主机。
     */
    @QueryMeta(expr = "app_host like ?")
    @Schema(title = "app主机", description = "app主机")
    private String appHost;
    /**
     * app端口。
     */
    @QueryMeta(expr = "app_port=?")
    @Schema(title = "app端口", description = "app端口")
    private Integer appPort;
    /**
     * app端口范围。
     */
    @QueryMeta(expr = "app_port between ? and ?")
    @Schema(title = "app端口范围", description = "app端口范围")
    private Integer[] appPortRange;
    /**
     * 任务项目。
     */
    @QueryMeta(expr = "task_project like ?")
    @Schema(title = "任务项目", description = "任务项目")
    private String taskProject;
    /**
     * 运行目标。
     */
    @QueryMeta(expr = "run_target like ?")
    @Schema(title = "运行目标", description = "运行目标")
    private String runTarget;
    /**
     * 定时任务数量。
     */
    @QueryMeta(expr = "croner_num=?")
    @Schema(title = "定时任务数量", description = "定时任务数量")
    private Integer cronerNum;
    /**
     * 定时任务数量范围。
     */
    @QueryMeta(expr = "croner_num between ? and ?")
    @Schema(title = "定时任务数量范围", description = "定时任务数量范围")
    private Integer[] cronerNumRange;
    /**
     * 统计运行次数。
     */
    @QueryMeta(expr = "croner_run_num=?")
    @Schema(title = "统计运行次数", description = "统计运行次数")
    private Integer cronerRunNum;
    /**
     * 统计运行次数范围。
     */
    @QueryMeta(expr = "croner_run_num between ? and ?")
    @Schema(title = "统计运行次数范围", description = "统计运行次数范围")
    private Integer[] cronerRunNumRange;
    /**
     * 统计运行失败次数。
     */
    @QueryMeta(expr = "croner_fail_num=?")
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数")
    private Integer cronerFailNum;
    /**
     * 统计运行失败次数范围。
     */
    @QueryMeta(expr = "croner_fail_num between ? and ?")
    @Schema(title = "统计运行失败次数范围", description = "统计运行失败次数范围")
    private Integer[] cronerFailNumRange;
    /**
     * 统计总时间毫秒数。
     */
    @QueryMeta(expr = "croner_run_time=?")
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数")
    private Long cronerRunTime;
    /**
     * 统计总时间毫秒数范围。
     */
    @QueryMeta(expr = "croner_run_time between ? and ?")
    @Schema(title = "统计总时间毫秒数范围", description = "统计总时间毫秒数范围")
    private Long[] cronerRunTimeRange;
    /**
     * 队列任务数量。
     */
    @QueryMeta(expr = "runner_num=?")
    @Schema(title = "队列任务数量", description = "队列任务数量")
    private Integer runnerNum;
    /**
     * 队列任务数量范围。
     */
    @QueryMeta(expr = "runner_num between ? and ?")
    @Schema(title = "队列任务数量范围", description = "队列任务数量范围")
    private Integer[] runnerNumRange;
    /**
     * 统计运行次数。
     */
    @QueryMeta(expr = "runner_run_num=?")
    @Schema(title = "统计运行次数", description = "统计运行次数")
    private Integer runnerRunNum;
    /**
     * 统计运行次数范围。
     */
    @QueryMeta(expr = "runner_run_num between ? and ?")
    @Schema(title = "统计运行次数范围", description = "统计运行次数范围")
    private Integer[] runnerRunNumRange;
    /**
     * 统计运行失败次数。
     */
    @QueryMeta(expr = "runner_fail_num=?")
    @Schema(title = "统计运行失败次数", description = "统计运行失败次数")
    private Integer runnerFailNum;
    /**
     * 统计运行失败次数范围。
     */
    @QueryMeta(expr = "runner_fail_num between ? and ?")
    @Schema(title = "统计运行失败次数范围", description = "统计运行失败次数范围")
    private Integer[] runnerFailNumRange;
    /**
     * 统计总时间毫秒数。
     */
    @QueryMeta(expr = "runner_run_time=?")
    @Schema(title = "统计总时间毫秒数", description = "统计总时间毫秒数")
    private Long runnerRunTime;
    /**
     * 统计总时间毫秒数范围。
     */
    @QueryMeta(expr = "runner_run_time between ? and ?")
    @Schema(title = "统计总时间毫秒数范围", description = "统计总时间毫秒数范围")
    private Long[] runnerRunTimeRange;
    /**
     * jvm内存总数。
     */
    @QueryMeta(expr = "jvm_mem_max=?")
    @Schema(title = "jvm内存总数", description = "jvm内存总数")
    private Long jvmMemMax;
    /**
     * jvm内存总数范围。
     */
    @QueryMeta(expr = "jvm_mem_max between ? and ?")
    @Schema(title = "jvm内存总数范围", description = "jvm内存总数范围")
    private Long[] jvmMemMaxRange;
    /**
     * jvm内存总数。
     */
    @QueryMeta(expr = "jvm_mem_total=?")
    @Schema(title = "jvm内存总数", description = "jvm内存总数")
    private Long jvmMemTotal;
    /**
     * jvm内存总数范围。
     */
    @QueryMeta(expr = "jvm_mem_total between ? and ?")
    @Schema(title = "jvm内存总数范围", description = "jvm内存总数范围")
    private Long[] jvmMemTotalRange;
    /**
     * jvm空闲内存。
     */
    @QueryMeta(expr = "jvm_mem_free=?")
    @Schema(title = "jvm空闲内存", description = "jvm空闲内存")
    private Long jvmMemFree;
    /**
     * jvm空闲内存范围。
     */
    @QueryMeta(expr = "jvm_mem_free between ? and ?")
    @Schema(title = "jvm空闲内存范围", description = "jvm空闲内存范围")
    private Long[] jvmMemFreeRange;
    /**
     * 活跃线程。
     */
    @QueryMeta(expr = "thread_active=?")
    @Schema(title = "活跃线程", description = "活跃线程")
    private Integer threadActive;
    /**
     * 活跃线程范围。
     */
    @QueryMeta(expr = "thread_active between ? and ?")
    @Schema(title = "活跃线程范围", description = "活跃线程范围")
    private Integer[] threadActiveRange;
    /**
     * 峰值线程。
     */
    @QueryMeta(expr = "thread_peak=?")
    @Schema(title = "峰值线程", description = "峰值线程")
    private Integer threadPeak;
    /**
     * 峰值线程范围。
     */
    @QueryMeta(expr = "thread_peak between ? and ?")
    @Schema(title = "峰值线程范围", description = "峰值线程范围")
    private Integer[] threadPeakRange;
    /**
     * 守护线程。
     */
    @QueryMeta(expr = "thread_daemon=?")
    @Schema(title = "守护线程", description = "守护线程")
    private Integer threadDaemon;
    /**
     * 守护线程范围。
     */
    @QueryMeta(expr = "thread_daemon between ? and ?")
    @Schema(title = "守护线程范围", description = "守护线程范围")
    private Integer[] threadDaemonRange;
    /**
     * 累计启动线程。
     */
    @QueryMeta(expr = "thread_started=?")
    @Schema(title = "累计启动线程", description = "累计启动线程")
    private Long threadStarted;
    /**
     * 累计启动线程范围。
     */
    @QueryMeta(expr = "thread_started between ? and ?")
    @Schema(title = "累计启动线程范围", description = "累计启动线程范围")
    private Long[] threadStartedRange;
    /**
     * 建立日期范围。
     */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title = "建立日期范围", description = "建立日期范围")
    private Date[] createDateRange;
    /**
     * 修改时间范围。
     */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title = "修改时间范围", description = "修改时间范围")
    private Date[] modifyDateRange;
    /**
     * 最后更新范围。
     */
    @QueryMeta(expr = "last_update between ? and ?")
    @Schema(title = "最后更新范围", description = "最后更新范围")
    private Date[] lastUpdateRange;
    /**
     * 状态。
     */
    @QueryMeta(expr = "state=?")
    @Schema(title = "状态", description = "状态")
    private Integer state;
    /**
     * 数组状态。
     */
    @QueryMeta(expr = "state in (?)")
    @Schema(title = "数组状态", description = "状态数组，可同时匹配多个状态。")
    private Integer[] states;
    /**
     * 大于等于状态。
     */
    @QueryMeta(expr = "state>=?")
    @Schema(title = "大于等于状态", description = "大于等于状态")
    private Integer stateGte;
    /**
     * 小于等于状态。
     */
    @QueryMeta(expr = "state<=?")
    @Schema(title = "小于等于状态", description = "小于等于状态")
    private Integer stateLte;

    /**
     * 允许的排序属性。
     * key:排序名 value:排序字段
     *
     * @return
     */
    @Override
    public Map<String, String> ALLOWED_SORT_PROPERTY() {
        return new HashMap<>() {{
            put( "id", "id" );
            put( "hostIp", "host_ip" );
            put( "appName", "app_name" );
            put( "appVersion", "app_version" );
            put( "appHost", "app_host" );
            put( "appPort", "app_port" );
            put( "taskProject", "task_project" );
            put( "runTarget", "run_target" );
            put( "cronerNum", "croner_num" );
            put( "cronerRunNum", "croner_run_num" );
            put( "cronerFailNum", "croner_fail_num" );
            put( "cronerRunTime", "croner_run_time" );
            put( "runnerNum", "runner_num" );
            put( "runnerRunNum", "runner_run_num" );
            put( "runnerFailNum", "runner_fail_num" );
            put( "runnerRunTime", "runner_run_time" );
            put( "jvmMemMax", "jvm_mem_max" );
            put( "jvmMemTotal", "jvm_mem_total" );
            put( "jvmMemFree", "jvm_mem_free" );
            put( "threadActive", "thread_active" );
            put( "threadPeak", "thread_peak" );
            put( "threadDaemon", "thread_daemon" );
            put( "threadStarted", "thread_started" );
            put( "createDate", "create_date" );
            put( "modifyDate", "modify_date" );
            put( "lastUpdate", "last_update" );
            put( "state", "state" );
        }};
    }

    /**
     * 获取id。
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置id。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 设置id链式调用。
     */
    public TaskHostInfoQueryParam id(Long id) {
        setId( id );
        return this;
    }

    /**
     * 获取主机注册IP。
     */
    public String getHostIp() {
        return this.hostIp;
    }

    /**
     * 设置主机注册IP。
     */
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    /**
     * 设置主机注册IP链式调用。
     */
    public TaskHostInfoQueryParam hostIp(String hostIp) {
        setHostIp( hostIp );
        return this;
    }

    /**
     * 获取应用名称。
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * 设置应用名称。
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 设置应用名称链式调用。
     */
    public TaskHostInfoQueryParam appName(String appName) {
        setAppName( appName );
        return this;
    }

    /**
     * 获取应用版本。
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * 设置应用版本。
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 设置应用版本链式调用。
     */
    public TaskHostInfoQueryParam appVersion(String appVersion) {
        setAppVersion( appVersion );
        return this;
    }

    /**
     * 获取app主机。
     */
    public String getAppHost() {
        return this.appHost;
    }

    /**
     * 设置app主机。
     */
    public void setAppHost(String appHost) {
        this.appHost = appHost;
    }

    /**
     * 设置app主机链式调用。
     */
    public TaskHostInfoQueryParam appHost(String appHost) {
        setAppHost( appHost );
        return this;
    }

    /**
     * 获取app端口。
     */
    public Integer getAppPort() {
        return this.appPort;
    }

    /**
     * 设置app端口。
     */
    public void setAppPort(Integer appPort) {
        this.appPort = appPort;
    }

    /**
     * 设置app端口链式调用。
     */
    public TaskHostInfoQueryParam appPort(Integer appPort) {
        setAppPort( appPort );
        return this;
    }

    /**
     * 获取app端口范围。
     */
    public Integer[] getAppPortRange() {
        return this.appPortRange;
    }

    /**
     * 设置app端口范围。
     */
    public void setAppPortRange(Integer[] appPortRange) {
        this.appPortRange = appPortRange;
    }

    /**
     * 设置app端口范围链式调用。
     */
    public TaskHostInfoQueryParam appPortRange(Integer[] appPortRange) {
        setAppPortRange( appPortRange );
        return this;
    }

    /**
     * 获取任务项目。
     */
    public String getTaskProject() {
        return this.taskProject;
    }

    /**
     * 设置任务项目。
     */
    public void setTaskProject(String taskProject) {
        this.taskProject = taskProject;
    }

    /**
     * 设置任务项目链式调用。
     */
    public TaskHostInfoQueryParam taskProject(String taskProject) {
        setTaskProject( taskProject );
        return this;
    }

    /**
     * 获取运行目标。
     */
    public String getRunTarget() {
        return this.runTarget;
    }

    /**
     * 设置运行目标。
     */
    public void setRunTarget(String runTarget) {
        this.runTarget = runTarget;
    }

    /**
     * 设置运行目标链式调用。
     */
    public TaskHostInfoQueryParam runTarget(String runTarget) {
        setRunTarget( runTarget );
        return this;
    }

    /**
     * 获取定时任务数量。
     */
    public Integer getCronerNum() {
        return this.cronerNum;
    }

    /**
     * 设置定时任务数量。
     */
    public void setCronerNum(Integer cronerNum) {
        this.cronerNum = cronerNum;
    }

    /**
     * 设置定时任务数量链式调用。
     */
    public TaskHostInfoQueryParam cronerNum(Integer cronerNum) {
        setCronerNum( cronerNum );
        return this;
    }

    /**
     * 获取定时任务数量范围。
     */
    public Integer[] getCronerNumRange() {
        return this.cronerNumRange;
    }

    /**
     * 设置定时任务数量范围。
     */
    public void setCronerNumRange(Integer[] cronerNumRange) {
        this.cronerNumRange = cronerNumRange;
    }

    /**
     * 设置定时任务数量范围链式调用。
     */
    public TaskHostInfoQueryParam cronerNumRange(Integer[] cronerNumRange) {
        setCronerNumRange( cronerNumRange );
        return this;
    }

    /**
     * 获取统计运行次数。
     */
    public Integer getCronerRunNum() {
        return this.cronerRunNum;
    }

    /**
     * 设置统计运行次数。
     */
    public void setCronerRunNum(Integer cronerRunNum) {
        this.cronerRunNum = cronerRunNum;
    }

    /**
     * 设置统计运行次数链式调用。
     */
    public TaskHostInfoQueryParam cronerRunNum(Integer cronerRunNum) {
        setCronerRunNum( cronerRunNum );
        return this;
    }

    /**
     * 获取统计运行次数范围。
     */
    public Integer[] getCronerRunNumRange() {
        return this.cronerRunNumRange;
    }

    /**
     * 设置统计运行次数范围。
     */
    public void setCronerRunNumRange(Integer[] cronerRunNumRange) {
        this.cronerRunNumRange = cronerRunNumRange;
    }

    /**
     * 设置统计运行次数范围链式调用。
     */
    public TaskHostInfoQueryParam cronerRunNumRange(Integer[] cronerRunNumRange) {
        setCronerRunNumRange( cronerRunNumRange );
        return this;
    }

    /**
     * 获取统计运行失败次数。
     */
    public Integer getCronerFailNum() {
        return this.cronerFailNum;
    }

    /**
     * 设置统计运行失败次数。
     */
    public void setCronerFailNum(Integer cronerFailNum) {
        this.cronerFailNum = cronerFailNum;
    }

    /**
     * 设置统计运行失败次数链式调用。
     */
    public TaskHostInfoQueryParam cronerFailNum(Integer cronerFailNum) {
        setCronerFailNum( cronerFailNum );
        return this;
    }

    /**
     * 获取统计运行失败次数范围。
     */
    public Integer[] getCronerFailNumRange() {
        return this.cronerFailNumRange;
    }

    /**
     * 设置统计运行失败次数范围。
     */
    public void setCronerFailNumRange(Integer[] cronerFailNumRange) {
        this.cronerFailNumRange = cronerFailNumRange;
    }

    /**
     * 设置统计运行失败次数范围链式调用。
     */
    public TaskHostInfoQueryParam cronerFailNumRange(Integer[] cronerFailNumRange) {
        setCronerFailNumRange( cronerFailNumRange );
        return this;
    }

    /**
     * 获取统计总时间毫秒数。
     */
    public Long getCronerRunTime() {
        return this.cronerRunTime;
    }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setCronerRunTime(Long cronerRunTime) {
        this.cronerRunTime = cronerRunTime;
    }

    /**
     * 设置统计总时间毫秒数链式调用。
     */
    public TaskHostInfoQueryParam cronerRunTime(Long cronerRunTime) {
        setCronerRunTime( cronerRunTime );
        return this;
    }

    /**
     * 获取统计总时间毫秒数范围。
     */
    public Long[] getCronerRunTimeRange() {
        return this.cronerRunTimeRange;
    }

    /**
     * 设置统计总时间毫秒数范围。
     */
    public void setCronerRunTimeRange(Long[] cronerRunTimeRange) {
        this.cronerRunTimeRange = cronerRunTimeRange;
    }

    /**
     * 设置统计总时间毫秒数范围链式调用。
     */
    public TaskHostInfoQueryParam cronerRunTimeRange(Long[] cronerRunTimeRange) {
        setCronerRunTimeRange( cronerRunTimeRange );
        return this;
    }

    /**
     * 获取队列任务数量。
     */
    public Integer getRunnerNum() {
        return this.runnerNum;
    }

    /**
     * 设置队列任务数量。
     */
    public void setRunnerNum(Integer runnerNum) {
        this.runnerNum = runnerNum;
    }

    /**
     * 设置队列任务数量链式调用。
     */
    public TaskHostInfoQueryParam runnerNum(Integer runnerNum) {
        setRunnerNum( runnerNum );
        return this;
    }

    /**
     * 获取队列任务数量范围。
     */
    public Integer[] getRunnerNumRange() {
        return this.runnerNumRange;
    }

    /**
     * 设置队列任务数量范围。
     */
    public void setRunnerNumRange(Integer[] runnerNumRange) {
        this.runnerNumRange = runnerNumRange;
    }

    /**
     * 设置队列任务数量范围链式调用。
     */
    public TaskHostInfoQueryParam runnerNumRange(Integer[] runnerNumRange) {
        setRunnerNumRange( runnerNumRange );
        return this;
    }

    /**
     * 获取统计运行次数。
     */
    public Integer getRunnerRunNum() {
        return this.runnerRunNum;
    }

    /**
     * 设置统计运行次数。
     */
    public void setRunnerRunNum(Integer runnerRunNum) {
        this.runnerRunNum = runnerRunNum;
    }

    /**
     * 设置统计运行次数链式调用。
     */
    public TaskHostInfoQueryParam runnerRunNum(Integer runnerRunNum) {
        setRunnerRunNum( runnerRunNum );
        return this;
    }

    /**
     * 获取统计运行次数范围。
     */
    public Integer[] getRunnerRunNumRange() {
        return this.runnerRunNumRange;
    }

    /**
     * 设置统计运行次数范围。
     */
    public void setRunnerRunNumRange(Integer[] runnerRunNumRange) {
        this.runnerRunNumRange = runnerRunNumRange;
    }

    /**
     * 设置统计运行次数范围链式调用。
     */
    public TaskHostInfoQueryParam runnerRunNumRange(Integer[] runnerRunNumRange) {
        setRunnerRunNumRange( runnerRunNumRange );
        return this;
    }

    /**
     * 获取统计运行失败次数。
     */
    public Integer getRunnerFailNum() {
        return this.runnerFailNum;
    }

    /**
     * 设置统计运行失败次数。
     */
    public void setRunnerFailNum(Integer runnerFailNum) {
        this.runnerFailNum = runnerFailNum;
    }

    /**
     * 设置统计运行失败次数链式调用。
     */
    public TaskHostInfoQueryParam runnerFailNum(Integer runnerFailNum) {
        setRunnerFailNum( runnerFailNum );
        return this;
    }

    /**
     * 获取统计运行失败次数范围。
     */
    public Integer[] getRunnerFailNumRange() {
        return this.runnerFailNumRange;
    }

    /**
     * 设置统计运行失败次数范围。
     */
    public void setRunnerFailNumRange(Integer[] runnerFailNumRange) {
        this.runnerFailNumRange = runnerFailNumRange;
    }

    /**
     * 设置统计运行失败次数范围链式调用。
     */
    public TaskHostInfoQueryParam runnerFailNumRange(Integer[] runnerFailNumRange) {
        setRunnerFailNumRange( runnerFailNumRange );
        return this;
    }

    /**
     * 获取统计总时间毫秒数。
     */
    public Long getRunnerRunTime() {
        return this.runnerRunTime;
    }

    /**
     * 设置统计总时间毫秒数。
     */
    public void setRunnerRunTime(Long runnerRunTime) {
        this.runnerRunTime = runnerRunTime;
    }

    /**
     * 设置统计总时间毫秒数链式调用。
     */
    public TaskHostInfoQueryParam runnerRunTime(Long runnerRunTime) {
        setRunnerRunTime( runnerRunTime );
        return this;
    }

    /**
     * 获取统计总时间毫秒数范围。
     */
    public Long[] getRunnerRunTimeRange() {
        return this.runnerRunTimeRange;
    }

    /**
     * 设置统计总时间毫秒数范围。
     */
    public void setRunnerRunTimeRange(Long[] runnerRunTimeRange) {
        this.runnerRunTimeRange = runnerRunTimeRange;
    }

    /**
     * 设置统计总时间毫秒数范围链式调用。
     */
    public TaskHostInfoQueryParam runnerRunTimeRange(Long[] runnerRunTimeRange) {
        setRunnerRunTimeRange( runnerRunTimeRange );
        return this;
    }

    /**
     * 获取jvm内存总数。
     */
    public Long getJvmMemMax() {
        return this.jvmMemMax;
    }

    /**
     * 设置jvm内存总数。
     */
    public void setJvmMemMax(Long jvmMemMax) {
        this.jvmMemMax = jvmMemMax;
    }

    /**
     * 设置jvm内存总数链式调用。
     */
    public TaskHostInfoQueryParam jvmMemMax(Long jvmMemMax) {
        setJvmMemMax( jvmMemMax );
        return this;
    }

    /**
     * 获取jvm内存总数范围。
     */
    public Long[] getJvmMemMaxRange() {
        return this.jvmMemMaxRange;
    }

    /**
     * 设置jvm内存总数范围。
     */
    public void setJvmMemMaxRange(Long[] jvmMemMaxRange) {
        this.jvmMemMaxRange = jvmMemMaxRange;
    }

    /**
     * 设置jvm内存总数范围链式调用。
     */
    public TaskHostInfoQueryParam jvmMemMaxRange(Long[] jvmMemMaxRange) {
        setJvmMemMaxRange( jvmMemMaxRange );
        return this;
    }

    /**
     * 获取jvm内存总数。
     */
    public Long getJvmMemTotal() {
        return this.jvmMemTotal;
    }

    /**
     * 设置jvm内存总数。
     */
    public void setJvmMemTotal(Long jvmMemTotal) {
        this.jvmMemTotal = jvmMemTotal;
    }

    /**
     * 设置jvm内存总数链式调用。
     */
    public TaskHostInfoQueryParam jvmMemTotal(Long jvmMemTotal) {
        setJvmMemTotal( jvmMemTotal );
        return this;
    }

    /**
     * 获取jvm内存总数范围。
     */
    public Long[] getJvmMemTotalRange() {
        return this.jvmMemTotalRange;
    }

    /**
     * 设置jvm内存总数范围。
     */
    public void setJvmMemTotalRange(Long[] jvmMemTotalRange) {
        this.jvmMemTotalRange = jvmMemTotalRange;
    }

    /**
     * 设置jvm内存总数范围链式调用。
     */
    public TaskHostInfoQueryParam jvmMemTotalRange(Long[] jvmMemTotalRange) {
        setJvmMemTotalRange( jvmMemTotalRange );
        return this;
    }

    /**
     * 获取jvm空闲内存。
     */
    public Long getJvmMemFree() {
        return this.jvmMemFree;
    }

    /**
     * 设置jvm空闲内存。
     */
    public void setJvmMemFree(Long jvmMemFree) {
        this.jvmMemFree = jvmMemFree;
    }

    /**
     * 设置jvm空闲内存链式调用。
     */
    public TaskHostInfoQueryParam jvmMemFree(Long jvmMemFree) {
        setJvmMemFree( jvmMemFree );
        return this;
    }

    /**
     * 获取jvm空闲内存范围。
     */
    public Long[] getJvmMemFreeRange() {
        return this.jvmMemFreeRange;
    }

    /**
     * 设置jvm空闲内存范围。
     */
    public void setJvmMemFreeRange(Long[] jvmMemFreeRange) {
        this.jvmMemFreeRange = jvmMemFreeRange;
    }

    /**
     * 设置jvm空闲内存范围链式调用。
     */
    public TaskHostInfoQueryParam jvmMemFreeRange(Long[] jvmMemFreeRange) {
        setJvmMemFreeRange( jvmMemFreeRange );
        return this;
    }

    /**
     * 获取活跃线程。
     */
    public Integer getThreadActive() {
        return this.threadActive;
    }

    /**
     * 设置活跃线程。
     */
    public void setThreadActive(Integer threadActive) {
        this.threadActive = threadActive;
    }

    /**
     * 设置活跃线程链式调用。
     */
    public TaskHostInfoQueryParam threadActive(Integer threadActive) {
        setThreadActive( threadActive );
        return this;
    }

    /**
     * 获取活跃线程范围。
     */
    public Integer[] getThreadActiveRange() {
        return this.threadActiveRange;
    }

    /**
     * 设置活跃线程范围。
     */
    public void setThreadActiveRange(Integer[] threadActiveRange) {
        this.threadActiveRange = threadActiveRange;
    }

    /**
     * 设置活跃线程范围链式调用。
     */
    public TaskHostInfoQueryParam threadActiveRange(Integer[] threadActiveRange) {
        setThreadActiveRange( threadActiveRange );
        return this;
    }

    /**
     * 获取峰值线程。
     */
    public Integer getThreadPeak() {
        return this.threadPeak;
    }

    /**
     * 设置峰值线程。
     */
    public void setThreadPeak(Integer threadPeak) {
        this.threadPeak = threadPeak;
    }

    /**
     * 设置峰值线程链式调用。
     */
    public TaskHostInfoQueryParam threadPeak(Integer threadPeak) {
        setThreadPeak( threadPeak );
        return this;
    }

    /**
     * 获取峰值线程范围。
     */
    public Integer[] getThreadPeakRange() {
        return this.threadPeakRange;
    }

    /**
     * 设置峰值线程范围。
     */
    public void setThreadPeakRange(Integer[] threadPeakRange) {
        this.threadPeakRange = threadPeakRange;
    }

    /**
     * 设置峰值线程范围链式调用。
     */
    public TaskHostInfoQueryParam threadPeakRange(Integer[] threadPeakRange) {
        setThreadPeakRange( threadPeakRange );
        return this;
    }

    /**
     * 获取守护线程。
     */
    public Integer getThreadDaemon() {
        return this.threadDaemon;
    }

    /**
     * 设置守护线程。
     */
    public void setThreadDaemon(Integer threadDaemon) {
        this.threadDaemon = threadDaemon;
    }

    /**
     * 设置守护线程链式调用。
     */
    public TaskHostInfoQueryParam threadDaemon(Integer threadDaemon) {
        setThreadDaemon( threadDaemon );
        return this;
    }

    /**
     * 获取守护线程范围。
     */
    public Integer[] getThreadDaemonRange() {
        return this.threadDaemonRange;
    }

    /**
     * 设置守护线程范围。
     */
    public void setThreadDaemonRange(Integer[] threadDaemonRange) {
        this.threadDaemonRange = threadDaemonRange;
    }

    /**
     * 设置守护线程范围链式调用。
     */
    public TaskHostInfoQueryParam threadDaemonRange(Integer[] threadDaemonRange) {
        setThreadDaemonRange( threadDaemonRange );
        return this;
    }

    /**
     * 获取累计启动线程。
     */
    public Long getThreadStarted() {
        return this.threadStarted;
    }

    /**
     * 设置累计启动线程。
     */
    public void setThreadStarted(Long threadStarted) {
        this.threadStarted = threadStarted;
    }

    /**
     * 设置累计启动线程链式调用。
     */
    public TaskHostInfoQueryParam threadStarted(Long threadStarted) {
        setThreadStarted( threadStarted );
        return this;
    }

    /**
     * 获取累计启动线程范围。
     */
    public Long[] getThreadStartedRange() {
        return this.threadStartedRange;
    }

    /**
     * 设置累计启动线程范围。
     */
    public void setThreadStartedRange(Long[] threadStartedRange) {
        this.threadStartedRange = threadStartedRange;
    }

    /**
     * 设置累计启动线程范围链式调用。
     */
    public TaskHostInfoQueryParam threadStartedRange(Long[] threadStartedRange) {
        setThreadStartedRange( threadStartedRange );
        return this;
    }

    /**
     * 获取建立日期范围。
     */
    public Date[] getCreateDateRange() {
        return this.createDateRange;
    }

    /**
     * 设置建立日期范围。
     */
    public void setCreateDateRange(Date[] createDateRange) {
        this.createDateRange = createDateRange;
    }

    /**
     * 设置建立日期范围链式调用。
     */
    public TaskHostInfoQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange( createDateRange );
        return this;
    }

    /**
     * 获取修改时间范围。
     */
    public Date[] getModifyDateRange() {
        return this.modifyDateRange;
    }

    /**
     * 设置修改时间范围。
     */
    public void setModifyDateRange(Date[] modifyDateRange) {
        this.modifyDateRange = modifyDateRange;
    }

    /**
     * 设置修改时间范围链式调用。
     */
    public TaskHostInfoQueryParam modifyDateRange(Date[] modifyDateRange) {
        setModifyDateRange( modifyDateRange );
        return this;
    }

    /**
     * 获取最后更新范围。
     */
    public Date[] getLastUpdateRange() {
        return this.lastUpdateRange;
    }

    /**
     * 设置最后更新范围。
     */
    public void setLastUpdateRange(Date[] lastUpdateRange) {
        this.lastUpdateRange = lastUpdateRange;
    }

    /**
     * 设置最后更新范围链式调用。
     */
    public TaskHostInfoQueryParam lastUpdateRange(Date[] lastUpdateRange) {
        setLastUpdateRange( lastUpdateRange );
        return this;
    }

    /**
     * 获取状态。
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置状态。
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 设置状态链式调用。
     */
    public TaskHostInfoQueryParam state(Integer state) {
        setState( state );
        return this;
    }

    /**
     * 获取数组状态。
     */
    public Integer[] getStates() {
        return this.states;
    }

    /**
     * 设置数组状态。
     */
    public void setStates(Integer[] states) {
        this.states = states;
    }

    /**
     * 设置数组状态链式调用。
     */
    public TaskHostInfoQueryParam states(Integer[] states) {
        setStates( states );
        return this;
    }

    /**
     * 获取大于等于状态。
     */
    public Integer getStateGte() {
        return this.stateGte;
    }

    /**
     * 设置大于等于状态。
     */
    public void setStateGte(Integer stateGte) {
        this.stateGte = stateGte;
    }

    /**
     * 设置大于等于状态链式调用。
     */
    public TaskHostInfoQueryParam stateGte(Integer stateGte) {
        setStateGte( stateGte );
        return this;
    }

    /**
     * 获取小于等于状态。
     */
    public Integer getStateLte() {
        return this.stateLte;
    }

    /**
     * 获取小于等于状态。
     */
    public void setStateLte(Integer stateLte) {
        this.stateLte = stateLte;
    }

    /**
     * 获取小于等于状态链式调用。
     */
    public TaskHostInfoQueryParam stateLte(Integer stateLte) {
        setStateLte( stateLte );
        return this;
    }


}