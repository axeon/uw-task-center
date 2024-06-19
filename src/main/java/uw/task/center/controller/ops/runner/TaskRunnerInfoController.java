package uw.task.center.controller.ops.runner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.app.common.dto.SysDataHistoryQueryParam;
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
import uw.task.center.dto.TaskRunnerInfoQueryParam;
import uw.task.center.entity.TaskRunnerInfo;

import java.util.Date;
import java.util.Objects;

/**
 * 队列任务配置表：增删改查
 */
@RestController
@RequestMapping("/ops/runner/info")
@Tag(name = "队列任务管理")
@MscPermDeclare(type = UserType.OPS)
public class TaskRunnerInfoController {
    private DaoFactory dao = DaoFactory.getInstance();


    /**
     * 列表队列任务配置。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @GetMapping("/list")
    @Operation(summary = "列表队列任务配置", description = "列表队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<TaskRunnerInfo> list(TaskRunnerInfoQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef( TaskRunnerInfo.class );
        return dao.list( TaskRunnerInfo.class, queryParam );
    }

    /**
     * 加载队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @GetMapping("/load")
    @Operation(summary = "加载队列任务配置", description = "加载队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public TaskRunnerInfo load(@Parameter(description = "主键ID", required = true, example = "1") @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logRef( TaskRunnerInfo.class, id );
        return dao.load( TaskRunnerInfo.class, id );
    }

    /**
     * 列表队列任务配置历史。
     *
     * @param
     * @return
     */
    @GetMapping("/history")
    @Operation(summary = "队列任务配置修改历史", description = "队列任务配置修改历史")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<SysDataHistory> history(SysDataHistoryQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef( TaskRunnerInfo.class, queryParam.getEntityId() );
        queryParam.setEntityClass( TaskRunnerInfo.class );
        return SysDataHistoryHelper.listHistory( queryParam );
    }

    /**
     * 新增队列任务配置。
     *
     * @param taskRunnerInfo
     * @return
     * @throws TransactionException
     */
    @PostMapping("/save")
    @Operation(summary = "新增队列任务配置", description = "新增队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskRunnerInfo> save(@RequestBody TaskRunnerInfo taskRunnerInfo) throws TransactionException {
        long id = dao.getSequenceId( TaskRunnerInfo.class );
        AuthServiceHelper.logRef( TaskRunnerInfo.class, id );
        taskRunnerInfo.setId( id );
        taskRunnerInfo.setCreateDate( new Date() );
        taskRunnerInfo.setModifyDate( null );
        taskRunnerInfo.setState( 1 );
        dao.save( taskRunnerInfo );
        //保存历史记录
        SysDataHistoryHelper.saveHistory( taskRunnerInfo.getId(), taskRunnerInfo, "队列任务配置", "新增队列任务配置" );
        return ResponseData.success( taskRunnerInfo );
    }

    /**
     * 修改队列任务配置。
     *
     * @param taskRunnerInfo
     * @return
     * @throws TransactionException
     */
    @PutMapping("/update")
    @Operation(summary = "修改队列任务配置", description = "修改队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskRunnerInfo> update(@RequestBody TaskRunnerInfo taskRunnerInfo, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class, taskRunnerInfo.getId(), "修改队列任务配置！操作备注：" + remark );
        TaskRunnerInfo taskRunnerInfoDb = dao.load( TaskRunnerInfo.class, taskRunnerInfo.getId() );
        if (taskRunnerInfoDb == null) {
            return ResponseData.warnMsg( "未找到指定ID的队列任务配置！" );
        }
        taskRunnerInfoDb.setTaskName( taskRunnerInfo.getTaskName() );
        taskRunnerInfoDb.setTaskDesc( taskRunnerInfo.getTaskDesc() );
        taskRunnerInfoDb.setTaskClass( taskRunnerInfo.getTaskClass() );
        taskRunnerInfoDb.setTaskOwner( taskRunnerInfo.getTaskOwner() );
        taskRunnerInfoDb.setTaskTag( taskRunnerInfo.getTaskTag() );
        taskRunnerInfoDb.setQueueType( taskRunnerInfo.getQueueType() );
        taskRunnerInfoDb.setDelayType( taskRunnerInfo.getDelayType() );
        taskRunnerInfoDb.setLogLevel( taskRunnerInfo.getLogLevel() );
        taskRunnerInfoDb.setLogLimitSize( taskRunnerInfo.getLogLimitSize() );
        taskRunnerInfoDb.setRunType( taskRunnerInfo.getRunType() );
        taskRunnerInfoDb.setRunTarget( taskRunnerInfo.getRunTarget() );
        taskRunnerInfoDb.setConsumerNum( taskRunnerInfo.getConsumerNum() );
        taskRunnerInfoDb.setPrefetchNum( taskRunnerInfo.getPrefetchNum() );
        taskRunnerInfoDb.setRateLimitType( taskRunnerInfo.getRateLimitType() );
        taskRunnerInfoDb.setRateLimitValue( taskRunnerInfo.getRateLimitValue() );
        taskRunnerInfoDb.setRateLimitTime( taskRunnerInfo.getRateLimitTime() );
        taskRunnerInfoDb.setRateLimitWait( taskRunnerInfo.getRateLimitWait() );
        taskRunnerInfoDb.setRetryTimesByOverrated( taskRunnerInfo.getRetryTimesByOverrated() );
        taskRunnerInfoDb.setRetryTimesByPartner( taskRunnerInfo.getRetryTimesByPartner() );
        taskRunnerInfoDb.setStatsDate( taskRunnerInfo.getStatsDate() );
        taskRunnerInfoDb.setStatsRunNum( taskRunnerInfo.getStatsRunNum() );
        taskRunnerInfoDb.setStatsFailNum( taskRunnerInfo.getStatsFailNum() );
        taskRunnerInfoDb.setStatsRunTime( taskRunnerInfo.getStatsRunTime() );
        taskRunnerInfoDb.setAlertFailRate( taskRunnerInfo.getAlertFailRate() );
        taskRunnerInfoDb.setAlertFailPartnerRate( taskRunnerInfo.getAlertFailPartnerRate() );
        taskRunnerInfoDb.setAlertFailProgramRate( taskRunnerInfo.getAlertFailProgramRate() );
        taskRunnerInfoDb.setAlertFailConfigRate( taskRunnerInfo.getAlertFailConfigRate() );
        taskRunnerInfoDb.setAlertFailDataRate( taskRunnerInfo.getAlertFailDataRate() );
        taskRunnerInfoDb.setAlertQueueOversize( taskRunnerInfo.getAlertQueueOversize() );
        taskRunnerInfoDb.setAlertQueueTimeout( taskRunnerInfo.getAlertQueueTimeout() );
        taskRunnerInfoDb.setAlertWaitTimeout( taskRunnerInfo.getAlertWaitTimeout() );
        taskRunnerInfoDb.setAlertRunTimeout( taskRunnerInfo.getAlertRunTimeout() );
        taskRunnerInfoDb.setTaskLinkOur( taskRunnerInfo.getTaskLinkOur() );
        taskRunnerInfoDb.setTaskLinkMch( taskRunnerInfo.getTaskLinkMch() );
        taskRunnerInfoDb.setModifyDate( new Date() );
        dao.update( taskRunnerInfoDb );
        SysDataHistoryHelper.saveHistory( taskRunnerInfoDb.getId(), taskRunnerInfoDb, "队列任务配置", "修改队列任务配置！操作备注：" + remark );
        return ResponseData.success( taskRunnerInfoDb );
    }

    /**
     * 启用队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @PatchMapping("/enable")
    @Operation(summary = "启用队列任务配置", description = "启用队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                               @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class, id, "启用队列任务配置！操作备注：" + remark );
        TaskRunnerInfo taskRunnerInfo = dao.load( TaskRunnerInfo.class, id );
        if (taskRunnerInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的队列任务配置！" );
        }
        if (taskRunnerInfo.getState() != StateCommon.DISABLED.getValue()) {
            return ResponseData.warnMsg( "启用队列任务配置失败！当前状态不是禁用状态！" );
        }
        taskRunnerInfo.setModifyDate( new Date() );
        taskRunnerInfo.setState( StateCommon.ENABLED.getValue() );
        dao.update( taskRunnerInfo );
        return ResponseData.success();
    }

    /**
     * 禁用队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @PatchMapping("/disable")
    @Operation(summary = "禁用队列任务配置", description = "禁用队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                                @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class, id, "禁用队列任务配置！操作备注：" + remark );
        TaskRunnerInfo taskRunnerInfo = dao.load( TaskRunnerInfo.class, id );
        if (taskRunnerInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的队列任务配置！" );
        }
        if (taskRunnerInfo.getState() != StateCommon.ENABLED.getValue()) {
            return ResponseData.warnMsg( "禁用队列任务配置失败！当前状态不是启用状态！" );
        }
        taskRunnerInfo.setModifyDate( new Date() );
        taskRunnerInfo.setState( StateCommon.DISABLED.getValue() );
        dao.update( taskRunnerInfo );
        return ResponseData.success();
    }

    /**
     * 删除队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除队列任务配置", description = "删除队列任务配置")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                               @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class, id, "删除队列任务配置！操作备注：" + remark );
        TaskRunnerInfo taskRunnerInfo = dao.load( TaskRunnerInfo.class, id );
        if (taskRunnerInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的队列任务配置！" );
        }
        if (taskRunnerInfo.getState() != StateCommon.DISABLED.getValue()) {
            return ResponseData.warnMsg( "删除队列任务配置失败！当前状态不是禁用状态！" );
        }
        taskRunnerInfo.setModifyDate( new Date() );
        taskRunnerInfo.setState( StateCommon.DELETED.getValue() );
        dao.update( taskRunnerInfo );
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
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData resetStats(@Parameter(description = "主键", example = "1") long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class, id, "清空统计数据！操作备注：" + remark );
        TaskRunnerInfo info = dao.load( TaskRunnerInfo.class, id );
        if (Objects.isNull( info )) {
            return ResponseData.errorMsg( "找不到对应的队列任务！" );
        }
        info.setStatsDate( null );
        info.setStatsRunNum( 0 );
        info.setStatsFailNum( 0 );
        info.setStatsRunTime( 0 );
        dao.update( info );
        return ResponseData.successMsg( "清空统计数据成功！" );

    }

}
