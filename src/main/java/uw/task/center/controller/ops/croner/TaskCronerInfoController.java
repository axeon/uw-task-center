package uw.task.center.controller.ops.croner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.constant.CommonState;
import uw.common.app.dto.IdQueryParam;
import uw.common.app.dto.IdStateQueryParam;
import uw.common.app.dto.SysCritLogQueryParam;
import uw.common.app.dto.SysDataHistoryQueryParam;
import uw.common.app.entity.SysCritLog;
import uw.common.app.entity.SysDataHistory;
import uw.common.app.helper.SysDataHistoryHelper;
import uw.common.response.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.common.data.PageList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskCronerInfoQueryParam;
import uw.task.center.entity.TaskCronerInfo;

/**
 * 定时任务配置管理接口。
 *
 * <p>维护定时任务（task_croner_info）的服务端配置：cron 表达式、运行目标、各类告警阈值、联系人等，
 * 支持配置历史与操作日志回溯。任务执行主机通过 RPC 增量拉取这些配置。</p>
 *
 * @author axeon
 */

@RestController
@RequestMapping("/ops/croner/info")
@Tag(name = "定时任务管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskCronerInfoController {
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 列表定时任务配置。
     *
     * @param queryParam
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "列表定时任务配置", description = "列表定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<PageList<TaskCronerInfo>> list(TaskCronerInfoQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskCronerInfo.class);
        return dao.list(TaskCronerInfo.class, queryParam);
    }

    /**
     * 轻量级列表定时任务配置，一般用于select控件。
     *
     * @return
     */
    @GetMapping("/listLite")
    @Operation(summary = "轻量级列表定时任务配置", description = "轻量级列表定时任务配置，一般用于select控件。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public ResponseData<PageList<TaskCronerInfo>> listLite(TaskCronerInfoQueryParam queryParam) {
        queryParam.SELECT_SQL("SELECT id,task_name,task_class,task_param,task_owner,task_cron,run_type,run_target,log_level,log_limit_size,next_run_date,stats_date,stats_run_num,stats_fail_num,stats_run_time,alert_fail_rate,alert_fail_partner_rate,alert_fail_data_rate,alert_fail_program_rate,alert_fail_config_rate,alert_wait_timeout,alert_run_timeout,task_link_our,task_link_mch,create_date,modify_date,state from task_croner_info ");
        return dao.list(TaskCronerInfo.class, queryParam);
    }

    /**
     * 加载定时任务配置。
     *
     * @param id
     */
    @GetMapping("/load")
    @Operation(summary = "加载定时任务配置", description = "加载定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<TaskCronerInfo> load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
        AuthServiceHelper.logRef(TaskCronerInfo.class, id);
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
    public ResponseData<PageList<SysDataHistory>> listDataHistory(SysDataHistoryQueryParam queryParam) {
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
    public ResponseData<PageList<SysCritLog>> listCritLog(SysCritLogQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskCronerInfo.class, queryParam.getBizId());
        queryParam.setBizTypeClass(TaskCronerInfo.class);
        return dao.list(SysCritLog.class, queryParam);
    }

    /**
     * 新增定时任务配置。
     *
     * @param taskCronerInfo
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "新增定时任务配置", description = "新增定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskCronerInfo> save(@RequestBody TaskCronerInfo taskCronerInfo) {
        // 多实例区分维度：taskClass + taskParam + runTarget 三元组唯一，重复则拒绝。
        ResponseData<TaskCronerInfo> checkResult = checkDuplicate(taskCronerInfo, 0);
        if (checkResult.isNotSuccess()) {
            return checkResult;
        }
        long id = dao.getSequenceId(TaskCronerInfo.class);
        AuthServiceHelper.logRef(TaskCronerInfo.class, id);
        taskCronerInfo.setId(id);
        taskCronerInfo.setCreateDate(SystemClock.nowDate());
        taskCronerInfo.setModifyDate(null);
        taskCronerInfo.setState(CommonState.ENABLED.getValue());
        //保存历史记录
        return dao.save(taskCronerInfo).onSuccess(savedEntity -> {
            SysDataHistoryHelper.saveHistory(taskCronerInfo);
        });
    }

    /**
     * 修改定时任务配置。
     *
     * @param taskCronerInfo
     * @return
     */
    @PutMapping("/update")
    @Operation(summary = "修改定时任务配置", description = "修改定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskCronerInfo> update(@RequestBody TaskCronerInfo taskCronerInfo, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskCronerInfo.class, taskCronerInfo.getId(), remark);
        // 修改时需校验新的 taskClass + taskParam + runTarget 三元组是否与他人冲突（排除自身）。
        ResponseData<TaskCronerInfo> checkResult = checkDuplicate(taskCronerInfo, taskCronerInfo.getId());
        if (checkResult.isNotSuccess()) {
            return checkResult;
        }
        return dao.load(TaskCronerInfo.class, taskCronerInfo.getId()).onSuccess(taskCronerInfoDb -> {
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
            taskCronerInfoDb.setAlertFailConfigRate(taskCronerInfo.getAlertFailConfigRate());
            taskCronerInfoDb.setAlertWaitTimeout(taskCronerInfo.getAlertWaitTimeout());
            taskCronerInfoDb.setAlertRunTimeout(taskCronerInfo.getAlertRunTimeout());
            taskCronerInfoDb.setTaskLinkOur(taskCronerInfo.getTaskLinkOur());
            taskCronerInfoDb.setTaskLinkMch(taskCronerInfo.getTaskLinkMch());
            taskCronerInfoDb.setModifyDate(SystemClock.nowDate());
            return dao.update(taskCronerInfoDb).onSuccess(updatedEntity -> {
                SysDataHistoryHelper.saveHistory(taskCronerInfoDb, remark);
            });
        });
    }

    /**
     * 启用定时任务配置。
     *
     * @param id
     */
    @PutMapping("/enable")
    @Operation(summary = "启用定时任务配置", description = "启用定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskCronerInfo.class, id, remark);
        return dao.update(new TaskCronerInfo().modifyDate(SystemClock.nowDate()).state(CommonState.ENABLED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 禁用定时任务配置。
     *
     * @param id
     */
    @PutMapping("/disable")
    @Operation(summary = "禁用定时任务配置", description = "禁用定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskCronerInfo.class, id, remark);
        return dao.update(new TaskCronerInfo().modifyDate(SystemClock.nowDate()).state(CommonState.DISABLED.getValue()), new IdStateQueryParam(id, CommonState.ENABLED.getValue()));
    }

    /**
     * 删除定时任务配置。
     *
     * @param id
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除定时任务配置", description = "删除定时任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskCronerInfo.class, id, remark);
        return dao.update(new TaskCronerInfo().modifyDate(SystemClock.nowDate()).state(CommonState.DELETED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 清空统计数据
     *
     * @param id
     * @return
     * @throws TransactionException
     */
    @PutMapping("/resetStats")
    @Operation(summary = "清空统计数据", description = "清空统计数据")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData resetStats(@Parameter(description = "主键") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskCronerInfo.class, id, remark);
        return dao.update(new TaskCronerInfo().statsDate(null).statsRunNum(0).statsFailNum(0).statsRunTime(0), new IdQueryParam(id));
    }

    /**
     * 校验定时任务配置是否重复。
     * <p>多实例区分维度：taskClass + taskParam + runTarget 三元组唯一。
     * 与 RPC 的 {@code /croner/init} 去重条件保持一致。</p>
     *
     * @param info       待校验配置（取 taskClass/taskParam/runTarget）
     * @param excludeId  需要排除的自身 id（新增时传 0）
     * @return 成功表示无重复；warn 表示已存在重复配置
     */
    private ResponseData<TaskCronerInfo> checkDuplicate(TaskCronerInfo info, long excludeId) {
        String taskClass = info.getTaskClass();
        String taskParam = info.getTaskParam() == null ? "" : info.getTaskParam();
        String runTarget = info.getRunTarget() == null ? "" : info.getRunTarget();
        if (taskClass == null || taskClass.isBlank()) {
            return ResponseData.warn(info, "taskClass不能为空");
        }
        ResponseData<TaskCronerInfo> queryResult = dao.queryForObject(TaskCronerInfo.class,
                "select * from task_croner_info where task_class=? and task_param=? and run_target=? and state>=0 and id<>?",
                new Object[]{taskClass, taskParam, runTarget, excludeId});
        // queryForObject 查无数据返回 warn，这是"无重复"的正常情况，仅 error 才中断。
        if (queryResult.isError()) {
            return queryResult;
        }
        if (queryResult.getData() != null) {
            return ResponseData.warn(info, "定时任务配置已存在: taskClass=[" + taskClass + "], taskParam=[" + taskParam + "], runTarget=[" + runTarget + "]");
        }
        return ResponseData.success(info);
    }

}
