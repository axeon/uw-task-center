package uw.task.center.controller.ops.croner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.app.common.dto.SysCritLogQueryParam;
import uw.app.common.dto.SysDataHistoryQueryParam;
import uw.app.common.entity.SysCritLog;
import uw.app.common.entity.SysDataHistory;
import uw.app.common.helper.SysDataHistoryHelper;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.constant.StateCommon;
import uw.common.dto.ResponseData;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskCronerInfoQueryParam;
import uw.task.center.entity.TaskCronerInfo;

import java.util.Date;
import java.util.Objects;

/**
 * 定时任务配置表：增删改查
 */

@RestController
@RequestMapping("/ops/croner/info")
@Tag(name = "定时任务管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskCronerInfoController {
    private final DaoFactory dao = DaoFactory.getInstance();

    /**
     * 列表定时任务配置。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @GetMapping("/list")
    @Operation(summary = "列表定时任务配置", description = "列表定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<TaskCronerInfo> list(TaskCronerInfoQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef(TaskCronerInfo.class);
        return dao.list(TaskCronerInfo.class, queryParam);
    }

    /**
     * 轻量级列表定时任务配置，一般用于select控件。
     *
     * @return
     */
    @GetMapping("/liteList")
    @Operation(summary = "轻量级列表定时任务配置", description = "轻量级列表定时任务配置，一般用于select控件。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public DataList<TaskCronerInfo> liteList(TaskCronerInfoQueryParam queryParam) throws TransactionException {
        queryParam.SELECT_SQL( "SELECT id,task_name,task_class,task_param,task_owner,task_cron,run_type,run_target,log_level,log_limit_size,next_run_date,stats_date,stats_run_num,stats_fail_num,stats_run_time,alert_fail_rate,alert_fail_partner_rate,alert_fail_data_rate,alert_fail_program_rate,alert_wait_timeout,alert_run_timeout,task_link_our,task_link_mch,create_date,modify_date,state from task_croner_info " );
        return dao.list(TaskCronerInfo.class, queryParam);
    }

    /**
     * 加载定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @GetMapping("/load")
    @Operation(summary = "加载定时任务配置", description = "加载定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public TaskCronerInfo load(@Parameter(description = "主键ID", required = true, example = "1") @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logRef(TaskCronerInfo.class,id);
        return dao.load(TaskCronerInfo.class, id);
    }

    /**
     * 查询数据历史。
     *
     * @param
     * @return
     */
    @GetMapping("/listDataHistory")
    @Operation(summary = "查询数据历史", description = "查询数据历史")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<SysDataHistory> listDataHistory(SysDataHistoryQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef(TaskCronerInfo.class, queryParam.getEntityId());
        queryParam.setEntityClass(TaskCronerInfo.class);
        return dao.list(SysDataHistory.class, queryParam);
    }

    /**
     * 查询操作日志。
     *
     * @param
     * @return
     */
    @GetMapping("/listCritLog")
    @Operation(summary = "查询操作日志", description = "查询操作日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<SysCritLog> listCritLog(SysCritLogQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef(TaskCronerInfo.class, queryParam.getRefId());
        queryParam.setRefTypeClass(TaskCronerInfo.class);
        return dao.list( SysCritLog.class, queryParam);
    }

    /**
     * 新增定时任务配置。
     *
     * @param taskCronerInfo
     * @return
     * @throws TransactionException
     */
    @PostMapping("/save")
    @Operation(summary = "新增定时任务配置", description = "新增定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskCronerInfo> save(@RequestBody TaskCronerInfo taskCronerInfo) throws TransactionException {
        long id = dao.getSequenceId( TaskCronerInfo.class );
        AuthServiceHelper.logRef( TaskCronerInfo.class, id );
        taskCronerInfo.setId( id );
        taskCronerInfo.setCreateDate( new Date() );
        taskCronerInfo.setModifyDate( null );
        taskCronerInfo.setState( 1 );
        dao.save( taskCronerInfo );
        //保存历史记录
        SysDataHistoryHelper.saveHistory( taskCronerInfo.getId(), taskCronerInfo, "定时任务配置", "新增定时任务配置" );
        return ResponseData.success( taskCronerInfo );
    }

    /**
     * 修改定时任务配置。
     *
     * @param taskCronerInfo
     * @return
     * @throws TransactionException
     */
    @PutMapping("/update")
    @Operation(summary = "修改定时任务配置", description = "修改定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskCronerInfo> update(@RequestBody TaskCronerInfo taskCronerInfo, @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskCronerInfo.class, taskCronerInfo.getId(), "修改定时任务配置！操作备注：" + remark );
        TaskCronerInfo taskCronerInfoDb = dao.load( TaskCronerInfo.class, taskCronerInfo.getId() );
        if (taskCronerInfoDb == null) {
            return ResponseData.warnMsg( "未找到指定ID的定时任务配置！" );
        }
        taskCronerInfoDb.setTaskName( taskCronerInfo.getTaskName() );
        taskCronerInfoDb.setTaskDesc( taskCronerInfo.getTaskDesc() );
        taskCronerInfoDb.setTaskClass( taskCronerInfo.getTaskClass() );
        taskCronerInfoDb.setTaskParam( taskCronerInfo.getTaskParam() );
        taskCronerInfoDb.setTaskOwner( taskCronerInfo.getTaskOwner() );
        taskCronerInfoDb.setTaskCron( taskCronerInfo.getTaskCron() );
        taskCronerInfoDb.setRunType( taskCronerInfo.getRunType() );
        taskCronerInfoDb.setRunTarget( taskCronerInfo.getRunTarget() );
        taskCronerInfoDb.setLogLevel( taskCronerInfo.getLogLevel() );
        taskCronerInfoDb.setLogLimitSize( taskCronerInfo.getLogLimitSize() );
        taskCronerInfoDb.setNextRunDate( taskCronerInfo.getNextRunDate() );
        taskCronerInfoDb.setStatsDate( taskCronerInfo.getStatsDate() );
        taskCronerInfoDb.setStatsRunNum( taskCronerInfo.getStatsRunNum() );
        taskCronerInfoDb.setStatsFailNum( taskCronerInfo.getStatsFailNum() );
        taskCronerInfoDb.setStatsRunTime( taskCronerInfo.getStatsRunTime() );
        taskCronerInfoDb.setAlertFailRate( taskCronerInfo.getAlertFailRate() );
        taskCronerInfoDb.setAlertFailPartnerRate( taskCronerInfo.getAlertFailPartnerRate() );
        taskCronerInfoDb.setAlertFailDataRate( taskCronerInfo.getAlertFailDataRate() );
        taskCronerInfoDb.setAlertFailProgramRate( taskCronerInfo.getAlertFailProgramRate() );
        taskCronerInfoDb.setAlertWaitTimeout( taskCronerInfo.getAlertWaitTimeout() );
        taskCronerInfoDb.setAlertRunTimeout( taskCronerInfo.getAlertRunTimeout() );
        taskCronerInfoDb.setTaskLinkOur( taskCronerInfo.getTaskLinkOur() );
        taskCronerInfoDb.setTaskLinkMch( taskCronerInfo.getTaskLinkMch() );
        taskCronerInfoDb.setModifyDate( new Date() );
        dao.update( taskCronerInfoDb );
        SysDataHistoryHelper.saveHistory( taskCronerInfoDb.getId(), taskCronerInfoDb, "定时任务配置", "修改定时任务配置！操作备注：" + remark );
        return ResponseData.success( taskCronerInfoDb );
    }

    /**
     * 启用定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @PatchMapping("/enable")
    @Operation(summary = "启用定时任务配置", description = "启用定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                               @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskCronerInfo.class, id, "启用定时任务配置！操作备注：" + remark );
        TaskCronerInfo taskCronerInfo = dao.load( TaskCronerInfo.class, id );
        if (taskCronerInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的定时任务配置！" );
        }
        if (taskCronerInfo.getState() != StateCommon.DISABLED.getValue()) {
            return ResponseData.warnMsg( "启用定时任务配置失败！当前状态不是禁用状态！" );
        }
        taskCronerInfo.setModifyDate( new Date() );
        taskCronerInfo.setState( StateCommon.ENABLED.getValue() );
        dao.update( taskCronerInfo );
        return ResponseData.success();
    }

    /**
     * 禁用定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @PatchMapping("/disable")
    @Operation(summary = "禁用定时任务配置", description = "禁用定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                                @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskCronerInfo.class, id, "禁用定时任务配置！操作备注：" + remark );
        TaskCronerInfo taskCronerInfo = dao.load( TaskCronerInfo.class, id );
        if (taskCronerInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的定时任务配置！" );
        }
        if (taskCronerInfo.getState() != StateCommon.ENABLED.getValue()) {
            return ResponseData.warnMsg( "禁用定时任务配置失败！当前状态不是启用状态！" );
        }
        taskCronerInfo.setModifyDate( new Date() );
        taskCronerInfo.setState( StateCommon.DISABLED.getValue() );
        dao.update( taskCronerInfo );
        return ResponseData.success();
    }

    /**
     * 删除定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除定时任务配置", description = "删除定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                               @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskCronerInfo.class, id, "删除定时任务配置！操作备注：" + remark );
        TaskCronerInfo taskCronerInfo = dao.load( TaskCronerInfo.class, id );
        if (taskCronerInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的定时任务配置！" );
        }
        if (taskCronerInfo.getState() != StateCommon.DISABLED.getValue()) {
            return ResponseData.warnMsg( "删除定时任务配置失败！当前状态不是禁用状态！" );
        }
        taskCronerInfo.setModifyDate( new Date() );
        taskCronerInfo.setState( StateCommon.DELETED.getValue() );
        dao.update( taskCronerInfo );
        return ResponseData.success();
    }

    /**
     * 清空统计数据
     *
     * @param id
     * @return
     * @throws TransactionException
     */
    @PatchMapping("/resetStats")
    @Operation(summary = "清空统计数据", description = "清空统计数据")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData resetStats(@Parameter(description = "主键", example = "1") long id, @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskCronerInfo.class, id, "清空统计数据！操作备注：" + remark );
        TaskCronerInfo info = dao.load( TaskCronerInfo.class, id );
        if (Objects.isNull( info )) {
            return ResponseData.errorMsg( "找不到对应的定时任务！" );
        }
        info.setStatsDate( null );
        info.setStatsRunNum( 0 );
        info.setStatsFailNum( 0 );
        info.setStatsRunTime( 0 );
        dao.update( info );
        return ResponseData.successMsg( "清空统计数据成功！" );

    }

}
