package uw.task.center.controller.ops.croner;

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
import uw.task.center.dto.TaskCronerInfoQueryParam;
import uw.task.center.entity.TaskCronerInfo;

import java.util.Date;
import java.util.Objects;

/**
 * 定时任务配置表：增删改查
 */

@RestController
@Tag(name = "定时任务管理")
@RequestMapping("/ops/croner/info")
@MscPermDeclare(type = UserType.OPS)
public class TaskCronerInfoController {
    private DaoFactory dao = DaoFactory.getInstance();


    /**
     * 定时任务列表。
     */
    @Operation(summary = "定时任务列表", description = "定时任务列表")
    @GetMapping("/list")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.REQUEST)
    public DataList<TaskCronerInfo> list(TaskCronerInfoQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.log(TaskCronerInfo.class.getSimpleName(), 0, "队列任务列表");
        return dao.list(TaskCronerInfo.class, queryParam);
    }

    /**
     * 查看定时任务
     */
    @Operation(summary = "查看定时任务", description = "查看定时任务")
    @GetMapping(value = "/load")
    @MscPermDeclare(type = UserType.OPS,auth = AuthType.USER, log = ActionLog.REQUEST)
    public TaskCronerInfo load(@Parameter(description = "主键", example = "1") long id) throws TransactionException {
        AuthServiceHelper.log(TaskCronerInfo.class.getSimpleName(), id, "查看定时任务");
        TaskCronerInfo config = dao.load(TaskCronerInfo.class, id);
        return config;
    }

    /**
     * 新增定时任务
     */
    @Operation(summary = "新增定时任务", description = "新增定时任务")
    @PostMapping(value = "/save")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.CRIT)
    public ResponseData<TaskCronerInfo> save(@RequestBody TaskCronerInfo taskCronerInfo) throws TransactionException {
        long id = dao.getSequenceId(TaskCronerInfo.class);
        AuthServiceHelper.log(TaskCronerInfo.class.getSimpleName(), id, "新增定时任务");
        taskCronerInfo.setId(id);
        taskCronerInfo.setCreateDate(new Date());
        taskCronerInfo.setModifyDate(new Date());
        taskCronerInfo.setState(1);
        //保存历史记录
        SysDataHistoryHelper.saveHistory(taskCronerInfo.getId(),taskCronerInfo,"","");
        return ResponseData.success(taskCronerInfo);
    }


    /**
     * 修改定时任务
     *
     * @param taskCronerInfo
     * @return
     * @throws TransactionException
     */
    @Operation(summary = "修改定时任务", description = "修改定时任务")
    @PutMapping(value = "/update")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.CRIT)
    public ResponseData<TaskCronerInfo> update(@RequestBody TaskCronerInfo taskCronerInfo, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.log(TaskCronerInfo.class,taskCronerInfo.getId(),"修改定时任务配置!"+remark);
        TaskCronerInfo taskCronerInfoDb = dao.load(TaskCronerInfo.class, taskCronerInfo.getId());
        if (taskCronerInfoDb == null) {
            return ResponseData.errorMsg("未找到指定ID的数值！");
        }
        taskCronerInfoDb.setTaskName(taskCronerInfo.getTaskName());
        taskCronerInfoDb.setTaskDesc(taskCronerInfo.getTaskDesc());
        taskCronerInfoDb.setTaskClass(taskCronerInfo.getTaskClass());
        taskCronerInfoDb.setTaskParam(taskCronerInfo.getTaskParam());
        taskCronerInfoDb.setTaskOwner(taskCronerInfo.getTaskOwner());
        taskCronerInfoDb.setTaskCron(taskCronerInfo.getTaskCron());
        taskCronerInfoDb.setRunType(taskCronerInfo.getRunType());
        taskCronerInfoDb.setRunTarget(taskCronerInfo.getRunTarget());
        taskCronerInfoDb.setLogLevel(taskCronerInfo.getLogLevel());
        taskCronerInfoDb.setLogLimitSize(taskCronerInfo.getLogLimitSize());
        taskCronerInfoDb.setNextRunDate(taskCronerInfo.getNextRunDate());
        taskCronerInfoDb.setAlertFailRate(taskCronerInfo.getAlertFailRate());
        taskCronerInfoDb.setAlertFailPartnerRate(taskCronerInfo.getAlertFailPartnerRate());
        taskCronerInfoDb.setAlertFailDataRate(taskCronerInfo.getAlertFailDataRate());
        taskCronerInfoDb.setAlertFailProgramRate(taskCronerInfo.getAlertFailProgramRate());
        taskCronerInfoDb.setAlertWaitTimeout(taskCronerInfo.getAlertWaitTimeout());
        taskCronerInfoDb.setAlertRunTimeout(taskCronerInfo.getAlertRunTimeout());
        taskCronerInfoDb.setTaskLinkOur(taskCronerInfo.getTaskLinkOur());
        taskCronerInfoDb.setTaskLinkMch(taskCronerInfo.getTaskLinkMch());
        //保存新记录。
        taskCronerInfoDb.setModifyDate(new Date());
        dao.update(taskCronerInfoDb);
        SysDataHistoryHelper.saveHistory(taskCronerInfoDb.getId(),taskCronerInfoDb,"","");
        return ResponseData.success(taskCronerInfoDb);
    }


    /**
     * 启用定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "启用定时任务配置", description = "启用定时任务配置")
    @PutMapping("/enable")
    public ResponseData enable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskCronerInfo taskCronerInfo = dao.load(TaskCronerInfo.class, id);
        if (taskCronerInfo != null) {
            taskCronerInfo.setModifyDate(new Date());
            taskCronerInfo.setState(1);
            dao.update(taskCronerInfo);
            AuthServiceHelper.log(TaskCronerInfo.class,id,"启用定时任务配置成功！"+remark);
            return ResponseData.successMsg("启用定时任务配置成功！"+remark);
        }else{
            AuthServiceHelper.log(TaskCronerInfo.class,id,"启用定时任务配置失败！"+remark);
            return ResponseData.errorMsg("启用定时任务配置失败！"+remark);
        }
    }

    /**
     * 禁用定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "禁用定时任务配置", description = "禁用定时任务配置")
    @PutMapping("/disable")
    public ResponseData disable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskCronerInfo taskCronerInfo = dao.load(TaskCronerInfo.class, id);
        if (taskCronerInfo != null) {
            taskCronerInfo.setModifyDate(new Date());
            taskCronerInfo.setState(0);
            dao.update(taskCronerInfo);
            AuthServiceHelper.log(TaskCronerInfo.class,id,"禁用定时任务配置成功！"+remark);
            return ResponseData.successMsg("禁用定时任务配置成功！"+remark);
        }else{
            AuthServiceHelper.log(TaskCronerInfo.class,id,"禁用定时任务配置失败！"+remark);
            return ResponseData.errorMsg("禁用定时任务配置失败！"+remark);
        }
    }

    /**
     * 删除定时任务配置。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "删除定时任务配置", description = "删除定时任务配置")
    @DeleteMapping("/delete")
    public ResponseData delete(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskCronerInfo taskCronerInfo = dao.load(TaskCronerInfo.class, id);
        if (taskCronerInfo != null) {
            taskCronerInfo.setModifyDate(new Date());
            taskCronerInfo.setState(-1);
            dao.update(taskCronerInfo);
            AuthServiceHelper.log(TaskCronerInfo.class,id,"删除定时任务配置成功！"+remark);
            return ResponseData.successMsg("删除定时任务配置成功！"+remark);
        }else{
            AuthServiceHelper.log(TaskCronerInfo.class,id,"删除定时任务配置失败！"+remark);
            return ResponseData.errorMsg("删除定时任务配置失败！"+remark);
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
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.CRIT)
    public ResponseData resetStats(@Parameter(description = "主键", example = "1") long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.log(TaskCronerInfo.class, id, "清空统计数据!"+remark);
        TaskCronerInfo info = dao.load(TaskCronerInfo.class, id);
        if (Objects.isNull(info)) {
            return ResponseData.errorMsg( "找不到对应的定时任务！" );
        }
        info.setStatsDate(null);
        info.setStatsRunNum(0);
        info.setStatsFailNum(0);
        info.setStatsRunTime( 0 );
        dao.update(info);
        return ResponseData.successMsg( "清空统计数据成功！" );

    }

}
