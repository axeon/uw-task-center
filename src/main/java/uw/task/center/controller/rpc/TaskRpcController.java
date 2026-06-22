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
import uw.auth.service.constant.UserType;
import uw.common.app.constant.CommonState;
import uw.common.response.ResponseData;
import uw.common.util.JsonUtils;
import uw.common.util.SystemClock;
import uw.dao.BatchUpdateManager;
import uw.dao.DaoFactory;
import uw.common.data.PageList;
import uw.dao.DaoManager;
import uw.dao.TransactionManager;
import uw.dao.util.ShardingTableUtils;
import uw.task.center.entity.*;
import uw.task.center.service.AlertProcessService;
import uw.task.center.vo.HostReportResponse;
import uw.task.center.vo.TaskHostInfoExt;

import java.util.*;

/**
 * uw-task 客户端调用的服务端 RPC 接口。
 *
 * <p>供任务执行主机（uw-task 客户端）调用：主机状态上报、任务配置初始化与增量拉取、
 * 定时任务下次执行时间心跳更新、联系人信息上传。所有接口要求 RPC 用户身份（{@link UserType#RPC}）。</p>
 *
 * @author axeon
 */
@RestController
@RequestMapping("/rpc/task")
@Tag(name = "任务RPC接口", description = "任务RPC接口")
@MscPermDeclare(user = UserType.RPC)
public class TaskRpcController {

    /**
     * 日志器。
     */
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

    /**
     * @param alertProcessService 告警处理服务
     */
    public TaskRpcController(AlertProcessService alertProcessService) {
        this.alertProcessService = alertProcessService;
    }

    /**
     * 主机状态上报：更新主机 JVM/线程指标与任务执行统计，触发告警判定，并返回主机配置（id/状态）。
     * <p>主机首次上报时自动建记录并分配 id，后续按 id+身份校验更新；统计与明细以批量事务写入分表。</p>
     *
     * @param taskHostInfoExt 主机状态数据（含任务统计列表）
     * @return 主机报告响应（id、hostIp、状态）
     */
    @PostMapping("/host/report")
    @Operation(summary = "更新主机当前状态", description = "更新主机当前状态")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<HostReportResponse> report(@RequestBody TaskHostInfoExt taskHostInfoExt) {
        //构造返回数据。
        HostReportResponse reportResponse = new HostReportResponse();
        String ip = AuthServiceHelper.getRemoteIp();
        taskHostInfoExt.setHostIp( ip );
        reportResponse.setHostIp( ip );
        reportResponse.setState( CommonState.ENABLED.getValue() ); //默认给正常状态。
        TaskHostInfoExt taskHostInfoDb = null;
        if (taskHostInfoExt.getId() > 0) {
            reportResponse.setId( taskHostInfoExt.getId() );
            ResponseData<TaskHostInfoExt> loadResult = dao.load( TaskHostInfoExt.class, taskHostInfoExt.getId() );
            if (loadResult.isNotSuccess()) {
                return loadResult.raw();
            }
            taskHostInfoDb = loadResult.getData();
            //处理屏蔽状态
            if (taskHostInfoDb != null && taskHostInfoDb.getState() == 0) {
                reportResponse.setState( 0 );
                return ResponseData.success(reportResponse);
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
                batchDao.execute( UPDATE_CRONER_STATS, new Object[]{createDate, stats.getNumAll(), numFail, stats.getTimeRun(), stats.getTaskId()} );
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
                batchDao.execute( UPDATE_RUNNER_STATS, new Object[]{createDate, stats.getNumAll(), numFail, stats.getTimeRun(), stats.getTaskId()} );
                //保存stats。
                stats.setId( dao.getSequenceId( TaskRunnerStats.class ) );
                stats.setCreateDate( createDate );
                batchDao.save( stats, runnerTable );
            }
            bum.submit();
            tm.commit();
        } catch (Throwable e) {
            log.error( "Batch save host metrics error! {}", e.getMessage(), e );
            try {
                tm.rollback();
            } catch (Throwable re) {
                log.error( "TransactionManager rollback error! {}", re.getMessage(), re );
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
                ResponseData<Integer> updateResult = dao.execute( updateHostSql, new Object[]{taskHostInfoExt.getCronerNum(), cronerRunNum, cronerFailNum, cronerRunTime,
                        taskHostInfoExt.getRunnerNum(), runnerRunNum, runnerFailNum, runnerRunTime, taskHostInfoExt.getJvmMemMax(), taskHostInfoExt.getJvmMemTotal(),
                        taskHostInfoExt.getJvmMemFree(), taskHostInfoExt.getThreadActive(), taskHostInfoExt.getThreadPeak(), taskHostInfoExt.getThreadDaemon(),
                        taskHostInfoExt.getThreadStarted(), createDate, taskHostInfoExt.getId(), taskHostInfoExt.getHostIp(), taskHostInfoExt.getAppHost(),
                        taskHostInfoExt.getAppPort(), taskHostInfoExt.getAppName(), taskHostInfoExt.getAppVersion(), taskHostInfoExt.getTaskProject(),
                        taskHostInfoExt.getRunTarget()} );
                if (updateResult.isNotSuccess()) {
                    return updateResult.raw();
                }
                isNewInfo = updateResult.getData() == 0;
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
                ResponseData<TaskHostInfo> saveResult = dao.save( taskHostInfoExt );
                if (saveResult.isNotSuccess()) {
                    return saveResult.raw();
                }
                reportResponse.setId( taskHostInfoExt.getId() );
            }
        } catch (Throwable e) {
            log.error( "taskHostInfoExt update exception: {}", e.getMessage(), e );
        }
        return ResponseData.success(reportResponse);
    }

    /**
     * 拉取定时任务配置列表（供客户端增量同步配置）。
     * <p>按 runTarget、taskProject 前缀、lastUpdateTime 过滤，仅返回 state>=0 的配置。</p>
     *
     * @param runTarget     运行目标（可空）
     * @param taskProject   任务项目包名前缀（可空）
     * @param lastUpdateTime 上次更新时间戳，>0 时仅返回此后变更的配置
     * @return 定时任务配置列表
     */
    @GetMapping("/croner/list")
    @Operation(summary = "获取定时任务列表", description = "获取定时任务列表")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<List<TaskCronerInfo>> getCronerConfigList(@Parameter(description = "运行目标", example = "default") String runTarget,
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
        ResponseData<PageList<TaskCronerInfo>> result = dao.list( TaskCronerInfo.class, sql.toString(), params.toArray(), 0, 0, false );
        if (result.isNotSuccess()) {
            return result.raw();
        }
        return ResponseData.success(result.getData().list());
    }

    /**
     * 拉取队列任务配置列表（供客户端增量同步配置）。
     * <p>按 runTarget、taskProject 前缀、lastUpdateTime 过滤，仅返回 state>=0 的配置。</p>
     *
     * @param runTarget     运行目标（可空）
     * @param taskProject   任务项目包名前缀（可空）
     * @param lastUpdateTime 上次更新时间戳，>0 时仅返回此后变更的配置
     * @return 队列任务配置列表
     */
    @GetMapping("/runner/list")
    @Operation(summary = "获取队列任务列表", description = "获取队列任务列表")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<List<TaskRunnerInfo>> getRunnerConfigList(@Parameter(description = "运行目标", example = "default") String runTarget,
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
        ResponseData<PageList<TaskRunnerInfo>> result = dao.list( TaskRunnerInfo.class, sql.toString(), params.toArray(), 0, 0, false );
        if (result.isNotSuccess()) {
            return result.raw();
        }
        return ResponseData.success(result.getData().list());
    }

    /**
     * 更新定时任务下次执行时间（由客户端在每次执行后心跳上报）。
     *
     * @param id       任务配置 id
     * @param nextDate 下一次执行时间戳
     * @return 影响行数
     */
    @PutMapping("/croner/tick")
    @Operation(summary = "更新定时任务下次执行时间", description = "更新定时任务下次执行时间")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<Integer> updateCronerLog(@Parameter(description = "主键") @RequestParam(required = false) Long id, @Parameter(description = "下一个日期", example = "0") @RequestParam(required =
            false) Long nextDate) {
        // 用包装类型接收，避免 required=false + 原始类型 long 在缺参时抛 400；
        // 仅当任一关键参数缺失时才报错，保留对 id=0 等历史调用的兼容（update 命中 0 行即无操作）。
        if (id == null || nextDate == null) {
            return ResponseData.errorCode("400", "id and nextDate are required.");
        }
        ResponseData<Integer> result = dao.execute( "update task_croner_info set next_run_date=? where id=? ", new Object[]{new Date( nextDate ), id} );
        if (result.isNotSuccess()) {
            return result;
        }
        return ResponseData.success(result.getData());
    }

    /**
     * 初始化队列任务配置（首次注册时上传默认配置）。
     * <p>按 task_class + task_tag + run_target 三元组幂等去重：已存在则直接返回已有配置，否则新建。</p>
     *
     * @param config 队列任务配置
     * @return 服务端配置（含已分配 id）
     */
    @PostMapping("/runner/init")
    @Operation(summary = "初始化队列任务配置", description = "初始化队列任务配置")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<TaskRunnerInfo> initRunnerConfig(@RequestBody TaskRunnerInfo config) {
        if (config != null) {
            String taskClass = config.getTaskClass();
            if (config.getRunTarget() == null) {
                config.setRunTarget( "" );
            }
            if (config.getTaskTag() == null) {
                config.setTaskTag( "" );
            }
            if (StringUtils.isNotBlank( taskClass )) {
                // 去重维度：task_class + task_tag + run_target。
                // task_tag 是多实例区分维度（同一 TaskRunner 子类通过不同 taskTag 运行多份实例），
                // 必须纳入去重，否则同 taskClass 下不同 taskTag 的实例会互相覆盖、只保留首条。
                ResponseData<TaskRunnerInfo> queryResult = dao.queryForObject( TaskRunnerInfo.class, "select * from task_runner_info where task_class=? and task_tag=? and run_target=? and state>=0",
                        new Object[]{taskClass, config.getTaskTag(), config.getRunTarget()} );
                // queryForObject 查无数据返回 warn（uw.dao.data.not.found.warn），这正是"需要新建"的正常分支，
                // 不能用 isNotSuccess() 判定（它对 warn 也为 true，会把首次注册误判成失败直接 return，导致任务永远注册不进去、id 恒为 0）。
                // 仅真正的 error 才中断。
                if (queryResult.isError()) {
                    return queryResult;
                }
                TaskRunnerInfo testOpt = queryResult.getData();
                if (testOpt == null) {
                    config.setId( dao.getSequenceId( TaskRunnerInfo.class ) );
                    config.setTaskOwner( "" );
                    config.setTaskLinkMch( "" );
                    config.setTaskLinkOur( "" );
                    config.setCreateDate( SystemClock.nowDate() );
                    config.setState( CommonState.ENABLED.getValue() );
                    ResponseData<TaskRunnerInfo> saveResult = dao.save( config );
                    if (saveResult.isNotSuccess()) {
                        return saveResult;
                    }
                }
            }
        }
        return ResponseData.success(config);
    }

    /**
     * 初始化定时任务配置（首次注册时上传默认配置）。
     * <p>按 task_class + task_param + run_target 三元组幂等去重：已存在则直接返回已有配置，否则新建。</p>
     *
     * @param config 定时任务配置
     * @return 服务端配置（含已分配 id）
     */
    @PostMapping("/croner/init")
    @Operation(summary = "初始化定时任务配置", description = "初始化定时任务配置")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<TaskCronerInfo> initCronerConfig(@RequestBody TaskCronerInfo config) {
        if (config != null) {
            String taskClass = config.getTaskClass();
            if (config.getRunTarget() == null) {
                config.setRunTarget( "" );
            }
            if (config.getTaskParam() == null) {
                config.setTaskParam( "" );
            }
            if (StringUtils.isNotBlank( taskClass )) {
                // 去重维度：task_class + task_param + run_target。
                // task_param 是多实例区分维度（同一 TaskCroner 子类通过不同 taskParam 运行多份实例），
                // 必须纳入去重，否则同 taskClass 下不同 taskParam 的实例会互相覆盖、只保留首条。
                ResponseData<TaskCronerInfo> queryResult = dao.queryForObject( TaskCronerInfo.class, "select * from task_croner_info where task_class=? and task_param=? and run_target=? and state>=0",
                        new Object[]{taskClass, config.getTaskParam(), config.getRunTarget()} );
                // queryForObject 查无数据返回 warn（uw.dao.data.not.found.warn），这正是"需要新建"的正常分支，
                // 不能用 isNotSuccess() 判定（它对 warn 也为 true，会把首次注册误判成失败直接 return，导致任务永远注册不进去、id 恒为 0）。
                // 仅真正的 error 才中断。
                if (queryResult.isError()) {
                    return queryResult;
                }
                TaskCronerInfo testOpt = queryResult.getData();
                if (testOpt == null) {
                    config.setId( dao.getSequenceId( TaskCronerInfo.class ) );
                    config.setTaskOwner( "" );
                    config.setTaskLinkMch( "" );
                    config.setTaskLinkOur( "" );
                    config.setCreateDate( SystemClock.nowDate() );
                    config.setState( CommonState.ENABLED.getValue() );
                    ResponseData<TaskCronerInfo> saveResult = dao.save( config );
                    if (saveResult.isNotSuccess()) {
                        return saveResult;
                    }
                }
            }
        }
        return ResponseData.success(config);
    }

    /**
     * 上传任务报警联系人信息（首次注册时提交默认联系人）。
     *
     * @param contactData 联系人信息（键值对，至少含 contactName）
     * @return 空响应
     */
    @PostMapping("/contact/init")
    @Operation(summary = "初始化任务联系人信息", description = "初始化任务联系人信息")
    @MscPermDeclare(user = UserType.RPC)
    public ResponseData<Void> initTaskContact(@RequestBody Map<String, String> contactData) {
        if (contactData != null) {
            String contactName = contactData.get( "contactName" );
            if (StringUtils.isNotBlank( contactName )) {
                ResponseData<TaskAlertContact> queryResult = dao.queryForObject( TaskAlertContact.class, "select * from task_alert_contact where contact_name=? and state=1",
                        new Object[]{contactName} );
                // queryForObject 查无数据返回 warn，这是"需要新建联系人"的正常分支，仅 error 才中断。
                if (queryResult.isError()) {
                    return queryResult.raw();
                }
                TaskAlertContact taskAlertContact = queryResult.getData();
                if (taskAlertContact == null) {
                    taskAlertContact = new TaskAlertContact();
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
                    ResponseData<TaskAlertContact> saveResult = dao.save( taskAlertContact );
                    if (saveResult.isNotSuccess()) {
                        return saveResult.raw();
                    }
                }
                // 用 JSON 库安全构造 linkData：原写法直接把 contactName 拼进字符串，
                // 若 contactName 含 " 或 \ 会生成非法 JSON，导致后续告警链路 JsonUtils.parse 失败、
                // 该任务所有告警都无法匹配联系人。
                HashMap<String, String> linkMap = new HashMap<>();
                linkMap.put( String.valueOf( taskAlertContact.getId() ), taskAlertContact.getContactName() );
                String linkData = JsonUtils.toString( linkMap );
                String taskClass = contactData.get( "taskClass" );
                if (StringUtils.isNotBlank( taskClass )) {
                    dao.execute( "update task_runner_info set task_owner=? where task_class=? and task_owner='' and state=1", new Object[]{linkData, taskClass} );
                    dao.execute( "update task_croner_info set task_owner=? where task_class=? and task_owner='' and state=1", new Object[]{linkData, taskClass} );
                }
            }
        }
        return ResponseData.success();
    }
}
