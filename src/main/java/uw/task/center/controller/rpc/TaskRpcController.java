package uw.task.center.controller.rpc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.annotation.ResponseAdviceIgnore;
import uw.auth.service.constant.UserType;
import uw.common.app.constant.CommonState;
import uw.common.util.SystemClock;
import uw.dao.BatchUpdateManager;
import uw.dao.DaoFactory;
import uw.dao.DaoManager;
import uw.dao.TransactionManager;
import uw.dao.util.ShardingTableUtils;
import uw.task.center.entity.*;
import uw.task.center.service.AlertProcessService;
import uw.task.center.vo.HostReportResponse;
import uw.task.center.vo.TaskHostInfoExt;

import java.util.*;

/**
 * uw-task使用的接口API。
 *
 * @author axeon
 */
@RestController
@RequestMapping("/rpc/task")
@Tag(name = "任务RPC接口", description = "任务RPC接口")
@MscPermDeclare(user = UserType.RPC)
@ResponseAdviceIgnore
public class TaskRpcController {

    private static final Logger log = LoggerFactory.getLogger( TaskRpcController.class );
    /**
     * 更新定时任务统计信息。
     */
    private static final String UPDATE_CRONER_STATS = "update task_croner_info set stats_date=?,stats_run_num=stats_run_num+?,stats_fail_num=stats_fail_num+?,stats_run_time" +
            "=stats_run_time+? where id=?";
    /**
     * 更新队列任务统计信息。
     */
    private static final String UPDATE_RUNNER_STATS = "update task_runner_info set stats_date=?,stats_run_num=stats_run_num+?,stats_fail_num=stats_fail_num+?,stats_run_time" +
            "=stats_run_time+? where id=?";
    /**
     * 数据库操作对象。
     */
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 告警处理服务。
     */
    private final AlertProcessService alertProcessService;

    public TaskRpcController(AlertProcessService alertProcessService) {
        this.alertProcessService = alertProcessService;
    }

    /**
     * 更新当前主机状态，并返回主机配置。
     */
    @PostMapping("/host/report")
    @Operation(summary = "更新主机当前状态", description = "更新主机当前状态")
    @MscPermDeclare(user = UserType.RPC)
    public HostReportResponse report(@RequestBody TaskHostInfoExt taskHostInfoExt) {
        //构造返回数据。
        HostReportResponse reportResponse = new HostReportResponse();
        String ip = AuthServiceHelper.getRemoteIp();
        taskHostInfoExt.setHostIp( ip );
        reportResponse.setHostIp( ip );
        reportResponse.setState( CommonState.ENABLED.getValue() ); //默认给正常状态。
        TaskHostInfoExt taskHostInfoDb = null;
        if (taskHostInfoExt.getId() > 0) {
            reportResponse.setId( taskHostInfoExt.getId() );
            taskHostInfoDb = dao.load( TaskHostInfoExt.class, taskHostInfoExt.getId() ).getData();
            //处理屏蔽状态
            if (taskHostInfoDb != null && taskHostInfoDb.getState() == 0) {
                reportResponse.setState( 0 );
                return reportResponse;
            }
        }
        //任务执行汇总统计信息
        int runnerRunNum = 0;
        int runnerFailNum = 0;
        long runnerRunTime = 0;
        int cronerRunNum = 0;
        int cronerFailNum = 0;
        long cronerRunTime = 0;
        //使用批量模式，必须使用独立的dao。
        DaoFactory batchDao = DaoFactory.getInstance();
        TransactionManager tm = batchDao.beginTransaction();
        BatchUpdateManager bum = batchDao.beginBatchUpdate();
        bum.setBatchSize( 1000 );

        //开始循环插入统计数据
        Date createDate = SystemClock.nowDate();
        String cronerTable = ShardingTableUtils.getTableNameByDate( "task_croner_stats", createDate );
        String runnerTable = ShardingTableUtils.getTableNameByDate( "task_runner_stats", createDate );
        try {
            //处理croner告警信息。
            alertProcessService.processCronerStats( taskHostInfoExt.getTaskCronerStatsList() );
            //处理runner告警信息。
            alertProcessService.processRunnerStats( taskHostInfoExt.getTaskRunnerStatsList() );
            //更新croner统计信息。
            for (TaskCronerStats stats : taskHostInfoExt.getTaskCronerStatsList()) {
                int numFail = stats.getNumFailConfig() + stats.getNumFailData() + stats.getNumFailPartner() + stats.getNumFailProgram();
                cronerRunNum += stats.getNumAll();
                cronerRunTime += stats.getTimeRun();
                cronerFailNum += numFail;
                //更新任务统计信息。
                batchDao.executeCommand( UPDATE_CRONER_STATS, new Object[]{createDate, stats.getNumAll(), numFail, stats.getTimeRun(), stats.getTaskId()} );
                //保存stats。
                stats.setId( dao.getSequenceId( TaskCronerStats.class ) );
                stats.setCreateDate( createDate );
                batchDao.save( stats, cronerTable );
            }
            //更新runner统计信息。
            for (TaskRunnerStats stats : taskHostInfoExt.getTaskRunnerStatsList()) {
                int numFail = stats.getNumFailConfig() + stats.getNumFailData() + stats.getNumFailPartner() + stats.getNumFailProgram();
                runnerRunNum += stats.getNumAll();
                runnerRunTime += stats.getTimeRun();
                runnerFailNum += numFail;
                //更新任务统计信息。
                batchDao.executeCommand( UPDATE_RUNNER_STATS, new Object[]{createDate, stats.getNumAll(), numFail, stats.getTimeRun(), stats.getTaskId()} );
                //保存stats。
                stats.setId( dao.getSequenceId( TaskRunnerStats.class ) );
                stats.setCreateDate( createDate );
                batchDao.save( stats, runnerTable );
            }
        } catch (Throwable e) {
            log.error( "Batch save host metrics error! {}", e.getMessage(), e );
        } finally {
            try {
                bum.submit();
            } catch (Throwable e) {
                log.error( "BatchUpdateManager error! {}", e.getMessage(), e );
            }
            try {
                tm.commit();
            } catch (Throwable e) {
                log.error( "TransactionManager error! {}", e.getMessage(), e );
            }
        }

        try {
            boolean isNewInfo = true;
            //先检查主机是否存在，决定是更新还是插入。
            if (taskHostInfoDb != null) {
                //更新操作。
                String updateHostSql = "UPDATE task_host_info SET croner_num=?, croner_run_num=croner_run_num+?, croner_fail_num=croner_fail_num+?, " + "croner_run_time" +
                        "=croner_run_time+?, runner_num=?, runner_run_num=runner_run_num+?, runner_fail_num=runner_fail_num+?, runner_run_time=runner_run_time+?, " +
                        "jvm_mem_max=?, jvm_mem_total=?, jvm_mem_free=?, thread_active=?, thread_peak=?, thread_daemon=?, thread_started=?, last_update=? WHERE id=? and " +
                        "host_ip=? and app_host=? and app_port=? and app_name=? and app_version=? and task_project=? and run_target=?";
                int effectNum = dao.executeCommand( updateHostSql, new Object[]{taskHostInfoExt.getCronerNum(), cronerRunNum, cronerFailNum, cronerRunTime,
                        taskHostInfoExt.getRunnerNum(), runnerRunNum, runnerFailNum, runnerRunTime, taskHostInfoExt.getJvmMemMax(), taskHostInfoExt.getJvmMemTotal(),
                        taskHostInfoExt.getJvmMemFree(), taskHostInfoExt.getThreadActive(), taskHostInfoExt.getThreadPeak(), taskHostInfoExt.getThreadDaemon(),
                        taskHostInfoExt.getThreadStarted(), createDate, taskHostInfoExt.getId(), taskHostInfoExt.getHostIp(), taskHostInfoExt.getAppHost(),
                        taskHostInfoExt.getAppPort(), taskHostInfoExt.getAppName(), taskHostInfoExt.getAppVersion(), taskHostInfoExt.getTaskProject(),
                        taskHostInfoExt.getRunTarget()} ).getData();
                isNewInfo = effectNum == 0;
            }
            if (isNewInfo) {
                //对应新增操作。
                taskHostInfoExt.setId( dao.getSequenceId( TaskHostInfo.class ) );
                taskHostInfoExt.setRunnerRunNum( runnerRunNum );
                taskHostInfoExt.setRunnerFailNum( runnerFailNum );
                taskHostInfoExt.setRunnerRunTime( runnerRunTime );
                taskHostInfoExt.setCronerRunNum( cronerRunNum );
                taskHostInfoExt.setCronerFailNum( cronerFailNum );
                taskHostInfoExt.setCronerRunTime( cronerRunTime );
                taskHostInfoExt.setCreateDate( createDate );
                taskHostInfoExt.setLastUpdate( createDate );
                taskHostInfoExt.setState( CommonState.ENABLED.getValue() );
                dao.save( taskHostInfoExt );
                reportResponse.setId( taskHostInfoExt.getId() );
            }
        } catch (Throwable e) {
            log.error( "taskHostInfoExt update exception: {}", e.getMessage(), e );
        }
        return reportResponse;
    }

    /**
     * 获取队列任务列表
     */
    @GetMapping("/croner/list")
    @Operation(summary = "获取定时任务列表", description = "获取定时任务列表")
    @MscPermDeclare(user = UserType.RPC)
    public List<TaskCronerInfo> getCronerConfigList(@Parameter(description = "运行目标", example = "default") String runTarget,
                                                    @Parameter(description = "任务项目", example = "任务项目") String taskProject,
                                                    @Parameter(description = "上一次更新时间", example = "0") Long lastUpdateTime) {
        StringBuilder sql = new StringBuilder( 256 );
        ArrayList<Object> params = new ArrayList<>( 3 );
        sql.append( "select * from task_croner_info where state>=0 " );
        if (StringUtils.isNotBlank( runTarget )) {
            sql.append( "and run_target=? " );
            params.add( runTarget );
        }
        if (StringUtils.isNotBlank( taskProject )) {
            sql.append( "and task_class like ? " );
            params.add( taskProject + "%" );
        }
        if (lastUpdateTime > 0) {
            sql.append( "and modify_date>=? " );
            params.add( new Date( lastUpdateTime ) );
        }
        return dao.list( TaskCronerInfo.class, sql.toString(), params.toArray(), 0, 0, false ).getData().results();
    }

    /**
     * 获取队列任务列表
     */
    @GetMapping("/runner/list")
    @Operation(summary = "获取队列任务列表", description = "获取队列任务列表")
    @MscPermDeclare(user = UserType.RPC)
    public List<TaskRunnerInfo> getRunnerConfigList(@Parameter(description = "运行目标", example = "default") String runTarget,
                                                    @Parameter(description = "任务项目", example = "任务项目") String taskProject,
                                                    @Parameter(description = "上一次更新时间", example = "0") Long lastUpdateTime) {
        StringBuilder sql = new StringBuilder( 256 );
        ArrayList<Object> params = new ArrayList<>( 3 );
        sql.append( "select * from task_runner_info where state>=0 " );
        if (StringUtils.isNotBlank( runTarget )) {
            sql.append( "and run_target=? " );
            params.add( runTarget );
        }
        if (StringUtils.isNotBlank( taskProject )) {
            sql.append( "and task_class like ? " );
            params.add( taskProject + "%" );
        }
        if (lastUpdateTime > 0) {
            sql.append( "and modify_date>=? " );
            params.add( new Date( lastUpdateTime ) );
        }
        return dao.list( TaskRunnerInfo.class, sql.toString(), params.toArray(), 0, 0, false ).getData().results();
    }

    /**
     * 更新Croner定时任务下一次执行时间
     *
     * @param id       配置Id
     * @param nextDate 下一次执行时间
     * @return
     */
    @PutMapping("/croner/tick")
    @Operation(summary = "更新定时任务下次执行时间", description = "更新定时任务下次执行时间")
    @MscPermDeclare(user = UserType.RPC)
    public int updateCronerLog(@Parameter(description = "主键") @RequestParam(required = false) long id, @Parameter(description = "下一个日期", example = "0") @RequestParam(required =
            false) long nextDate) {
        return dao.executeCommand( "update task_croner_info set next_run_date=? where id=? ", new Object[]{new Date( nextDate ), id} ).getData();
    }

    /**
     * 初始化Runner配置
     */
    @PostMapping("/runner/init")
    @Operation(summary = "初始化队列任务配置", description = "初始化队列任务配置")
    @MscPermDeclare(user = UserType.RPC)
    public TaskRunnerInfo initRunnerConfig(@RequestBody TaskRunnerInfo config) {
        if (config != null) {
            String taskClass = config.getTaskClass();
            if (config.getRunTarget() == null) {
                config.setRunTarget( "" );
            }
            if (config.getTaskTag() == null) {
                config.setTaskTag( "" );
            }
            if (StringUtils.isNotBlank( taskClass )) {
                // 再次检查，并创建配置
                TaskRunnerInfo testOpt = dao.queryForSingleObject( TaskRunnerInfo.class, "select * from task_runner_info where task_class=? and run_target=? and state>=0",
                        new Object[]{taskClass, config.getRunTarget()} ).getData();
                if (Objects.isNull( testOpt )) {
                    config.setId( dao.getSequenceId( TaskRunnerInfo.class ) );
                    config.setTaskOwner( "" );
                    config.setTaskLinkMch( "" );
                    config.setTaskLinkOur( "" );
                    config.setCreateDate( SystemClock.nowDate() );
                    config.setState( CommonState.ENABLED.getValue() );
                    dao.save( config );
                }
            }
        }
        return config;
    }

    /**
     * 初始化Croner配置
     */
    @PostMapping("/croner/init")
    @Operation(summary = "初始化定时任务配置", description = "初始化定时任务配置")
    @MscPermDeclare(user = UserType.RPC)
    public TaskCronerInfo initCronerConfig(@RequestBody TaskCronerInfo config) {
        if (config != null) {
            String taskClass = config.getTaskClass();
            if (config.getRunTarget() == null) {
                config.setRunTarget( "" );
            }
            if (config.getTaskParam() == null) {
                config.setTaskParam( "" );
            }
            if (StringUtils.isNotBlank( taskClass )) {
                // 再次检查，并创建配置
                TaskCronerInfo testOpt = dao.queryForSingleObject( TaskCronerInfo.class, "select * from task_croner_info where task_class=? and run_target=? and state>=0",
                        new Object[]{taskClass, config.getRunTarget()} ).getData();
                if (Objects.isNull( testOpt )) {
                    config.setId( dao.getSequenceId( TaskCronerInfo.class ) );
                    config.setTaskOwner( "" );
                    config.setTaskLinkMch( "" );
                    config.setTaskLinkOur( "" );
                    config.setCreateDate( SystemClock.nowDate() );
                    config.setState( CommonState.ENABLED.getValue() );
                    dao.save( config );
                }
            }
        }
        return config;
    }

    /**
     * 提交报警联系人。
     */
    @PostMapping("/contact/init")
    @Operation(summary = "初始化任务联系人信息", description = "初始化任务联系人信息")
    @MscPermDeclare(user = UserType.RPC)
    public void initRunnerConfig(@RequestBody Map<String, String> contactData) {
        if (contactData != null) {
            String contactName = contactData.get( "contactName" );
            if (StringUtils.isNotBlank( contactName )) {
                // 再次检查，并创建配置
                TaskAlertContact taskAlertContact = dao.queryForSingleObject( TaskAlertContact.class, "select * from task_alert_contact where contact_name=? and state=1",
                        new Object[]{contactName} ).getData();
                if (Objects.isNull( taskAlertContact )) {
                    taskAlertContact = new TaskAlertContact();
                    // 再次检查，并创建报警配置
                    taskAlertContact.setId( dao.getSequenceId( TaskAlertContact.class ) );
                    taskAlertContact.setContactType( 0 );// 任务负责人
                    taskAlertContact.setContactName( contactName );
                    taskAlertContact.setMobile( contactData.get( "mobile" ) );
                    taskAlertContact.setEmail( contactData.get( "email" ) );
                    taskAlertContact.setWechat( contactData.get( "wechat" ) );
                    taskAlertContact.setIm( contactData.get( "im" ) );
                    taskAlertContact.setNotifyUrl( contactData.get( "notifyUrl" ) );
                    taskAlertContact.setRemark( contactData.get( "remark" ) );
                    taskAlertContact.setCreateDate( SystemClock.nowDate() );
                    taskAlertContact.setState( CommonState.ENABLED.getValue() );
                    dao.save( taskAlertContact );
                    // 更新任务负责人和任务报警负责人
                }
                String linkData = "{\"" + taskAlertContact.getId() + "\":" + "\"" + taskAlertContact.getContactName() + "\"}";
                String taskClass = contactData.get( "taskClass" );
                if (StringUtils.isNotBlank( taskClass )) {
                    if (dao.executeCommand( "update task_runner_info set task_owner=? where task_class=? and task_owner='' and state=1", new Object[]{linkData, taskClass} ).getData() < 1) {
                        dao.executeCommand( "update task_croner_info set task_owner=? where task_class=? and task_owner='' and state=1", new Object[]{linkData, taskClass} );
                    }
                }
            }
        }
    }
}
