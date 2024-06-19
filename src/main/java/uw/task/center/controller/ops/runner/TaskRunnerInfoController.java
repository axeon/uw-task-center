package uw.task.center.controller.ops.runner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.app.common.helper.SysDataHistoryHelper;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
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
@Tag(name = "队列任务管理")
@RequestMapping("/ops/runner/info")
@MscPermDeclare(type = UserType.OPS)
public class TaskRunnerInfoController {
    private DaoFactory dao = DaoFactory.getInstance();


    /**
     * 列表队列任务
     */
    @Operation(summary = "列表队列任务", description = "列表队列任务")
    @GetMapping("/list")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<TaskRunnerInfo> list(TaskRunnerInfoQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class.getSimpleName(), 0, "列表队列任务" );
        return dao.list( TaskRunnerInfo.class, queryParam );
    }

    /**
     * 查看队列任务
     */
    @Operation(summary = "查看队列任务", description = "查看队列任务")
    @GetMapping(value = "/load")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.ALL)
    public TaskRunnerInfo load(@Parameter(description = "主键", example = "1") long id) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class.getSimpleName(), id, "查看队列任务" );
        TaskRunnerInfo info = dao.load( TaskRunnerInfo.class, id );
        return info;
    }

    /**
     * 新增队列任务
     */
    @Operation(summary = "新增队列任务", description = "队列任务配置表(task_runner_info)：新增记录")
    @PostMapping("/save")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskRunnerInfo> save(@RequestBody TaskRunnerInfo taskRunnerInfo) throws TransactionException {
        long id = dao.getSequenceId( TaskRunnerInfo.class );
        AuthServiceHelper.logInfo( TaskRunnerInfo.class.getSimpleName(), id, "新增队列任务配置" );
        taskRunnerInfo.setId( id );
        taskRunnerInfo.setCreateDate( new Date() );
        taskRunnerInfo.setModifyDate( null );
        taskRunnerInfo.setState( 1 );
        dao.save( taskRunnerInfo );
        //保存历史记录
        SysDataHistoryHelper.saveHistory( taskRunnerInfo.getId(), taskRunnerInfo ,"","");
        return ResponseData.success( taskRunnerInfo );
    }

    /**
     * 修改队列任务
     */
    @Operation(summary = "修改队列任务", description = "修改队列任务")
    @PutMapping(value = "/update")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskRunnerInfo> update(@RequestBody TaskRunnerInfo taskRunnerInfo, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo(TaskRunnerInfo.class,taskRunnerInfo.getId(),"修改队列任务配置!"+remark);
        TaskRunnerInfo taskRunnerInfoDb = dao.load( TaskRunnerInfo.class, taskRunnerInfo.getId() );
        if (taskRunnerInfoDb == null) {
            return ResponseData.errorMsg( "未找到指定ID的数值！" );
        }
        taskRunnerInfoDb.setTaskName( taskRunnerInfo.getTaskName() );
        taskRunnerInfoDb.setTaskDesc( taskRunnerInfo.getTaskDesc() );
        taskRunnerInfoDb.setTaskClass( taskRunnerInfo.getTaskClass() );
        taskRunnerInfoDb.setTaskTag( taskRunnerInfo.getTaskTag() );
        taskRunnerInfoDb.setTaskOwner( taskRunnerInfo.getTaskOwner() );
        taskRunnerInfoDb.setConsumerNum( taskRunnerInfo.getConsumerNum() );
        taskRunnerInfoDb.setPrefetchNum( taskRunnerInfo.getPrefetchNum() );
        taskRunnerInfoDb.setQueueType( taskRunnerInfo.getQueueType() );
        taskRunnerInfoDb.setDelayType( taskRunnerInfo.getDelayType() );
        taskRunnerInfoDb.setRateLimitType( taskRunnerInfo.getRateLimitType() );
        taskRunnerInfoDb.setRateLimitValue( taskRunnerInfo.getRateLimitValue() );
        taskRunnerInfoDb.setRateLimitTime( taskRunnerInfo.getRateLimitTime() );
        taskRunnerInfoDb.setRateLimitWait( taskRunnerInfo.getRateLimitWait() );
        taskRunnerInfoDb.setRetryTimesByOverrated( taskRunnerInfo.getRetryTimesByOverrated() );
        taskRunnerInfoDb.setRetryTimesByPartner( taskRunnerInfo.getRetryTimesByPartner() );
        taskRunnerInfoDb.setRunType( taskRunnerInfo.getRunType() );
        taskRunnerInfoDb.setRunTarget( taskRunnerInfo.getRunTarget() );
        taskRunnerInfoDb.setLogLevel( taskRunnerInfo.getLogLevel() );
        taskRunnerInfoDb.setLogLimitSize( taskRunnerInfo.getLogLimitSize() );
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
        //保存新记录。
        taskRunnerInfoDb.setModifyDate( new Date() );
        dao.update( taskRunnerInfoDb );
        SysDataHistoryHelper.saveHistory( taskRunnerInfoDb.getId(), taskRunnerInfoDb ,"","");
        return ResponseData.success( taskRunnerInfoDb );
    }



    /**
     * 启用队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "启用队列任务配置", description = "启用队列任务配置")
    @PutMapping("/enable")
    public ResponseData enable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskRunnerInfo taskRunnerInfo = dao.load(TaskRunnerInfo.class, id);
        if (taskRunnerInfo != null) {
            taskRunnerInfo.setModifyDate(new Date());
            taskRunnerInfo.setState(1);
            dao.update(taskRunnerInfo);
            AuthServiceHelper.logInfo(TaskRunnerInfo.class,id,"启用队列任务配置成功！"+remark);
            return ResponseData.successMsg("启用队列任务配置成功！"+remark);
        }else{
            AuthServiceHelper.logInfo(TaskRunnerInfo.class,id,"启用队列任务配置失败！"+remark);
            return ResponseData.errorMsg("启用队列任务配置失败！"+remark);
        }
    }

    /**
     * 禁用队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "禁用队列任务配置", description = "禁用队列任务配置")
    @PutMapping("/disable")
    public ResponseData disable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskRunnerInfo taskRunnerInfo = dao.load(TaskRunnerInfo.class, id);
        if (taskRunnerInfo != null) {
            taskRunnerInfo.setModifyDate(new Date());
            taskRunnerInfo.setState(0);
            dao.update(taskRunnerInfo);
            AuthServiceHelper.logInfo(TaskRunnerInfo.class,id,"禁用队列任务配置成功！"+remark);
            return ResponseData.successMsg("禁用队列任务配置成功！"+remark);
        }else{
            AuthServiceHelper.logInfo(TaskRunnerInfo.class,id,"禁用队列任务配置失败！"+remark);
            return ResponseData.errorMsg("禁用队列任务配置失败！"+remark);
        }
    }

    /**
     * 删除队列任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "删除队列任务配置", description = "删除队列任务配置")
    @DeleteMapping("/delete")
    public ResponseData delete(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskRunnerInfo taskRunnerInfo = dao.load(TaskRunnerInfo.class, id);
        if (taskRunnerInfo != null) {
            taskRunnerInfo.setModifyDate(new Date());
            taskRunnerInfo.setState(-1);
            dao.update(taskRunnerInfo);
            AuthServiceHelper.logInfo(TaskRunnerInfo.class,id,"删除队列任务配置成功！"+remark);
            return ResponseData.successMsg("删除队列任务配置成功！"+remark);
        }else{
            AuthServiceHelper.logInfo(TaskRunnerInfo.class,id,"删除队列任务配置失败！"+remark);
            return ResponseData.errorMsg("删除队列任务配置失败！"+remark);
        }
    }

    /**
     * 清空统计数据
     *
     * @param id
     * @return
     * @throws TransactionException
     */
    @Operation(summary = "清空统计数据", description = "清空统计数据")
    @PutMapping("/resetStats")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData resetStats(@Parameter(description = "主键", example = "1") long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskRunnerInfo.class, id, "清空统计数据!"+remark );
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
