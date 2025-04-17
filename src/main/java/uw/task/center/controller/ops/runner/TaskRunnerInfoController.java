package uw.task.center.controller.ops.runner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.app.constant.CommonState;
import uw.common.app.dto.IdQueryParam;
import uw.common.app.dto.IdStateQueryParam;
import uw.common.app.dto.SysCritLogQueryParam;
import uw.common.app.dto.SysDataHistoryQueryParam;
import uw.common.app.entity.SysCritLog;
import uw.common.app.entity.SysDataHistory;
import uw.common.app.helper.SysDataHistoryHelper;
import uw.common.dto.ResponseData;
import uw.dao.DaoManager;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskRunnerInfoQueryParam;
import uw.task.center.entity.TaskRunnerInfo;

import java.util.Date;

/**
 * 队列任务配置表：增删改查
 */
@RestController
@RequestMapping("/ops/runner/info")
@Tag(name = "队列任务管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskRunnerInfoController {
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 列表队列任务配置。
     *
     * @param queryParam
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "列表队列任务配置", description = "列表队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<DataList<TaskRunnerInfo>> list(TaskRunnerInfoQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskRunnerInfo.class);
        return dao.list(TaskRunnerInfo.class, queryParam);
    }

    /**
     * 轻量级列表队列任务配置，一般用于select控件。
     *
     * @return
     */
    @GetMapping("/liteList")
    @Operation(summary = "轻量级列表队列任务配置", description = "轻量级列表队列任务配置，一般用于select控件。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public ResponseData<DataList<TaskRunnerInfo>> liteList(TaskRunnerInfoQueryParam queryParam) {
        queryParam.SELECT_SQL("SELECT id,task_name,task_class,task_owner,task_tag,queue_type,delay_type,log_level,log_limit_size,run_type,run_target,consumer_num,prefetch_num,rate_limit_type,rate_limit_value,rate_limit_time,rate_limit_wait,retry_times_by_overrated,retry_times_by_partner,stats_date,stats_run_num,stats_fail_num,stats_run_time,alert_fail_rate,alert_fail_partner_rate,alert_fail_program_rate,alert_fail_config_rate,alert_fail_data_rate,alert_queue_oversize,alert_queue_timeout,alert_wait_timeout,alert_run_timeout,task_link_our,task_link_mch,create_date,modify_date,state from task_runner_info ");
        return dao.list(TaskRunnerInfo.class, queryParam);
    }

    /**
     * 加载队列任务配置。
     *
     * @param id
     */
    @GetMapping("/load")
    @Operation(summary = "加载队列任务配置", description = "加载队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<TaskRunnerInfo> load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
        AuthServiceHelper.logRef(TaskRunnerInfo.class, id);
        return dao.load(TaskRunnerInfo.class, id);
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
    public ResponseData<DataList<SysDataHistory>> listDataHistory(SysDataHistoryQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskRunnerInfo.class, queryParam.getEntityId());
        queryParam.setEntityClass(TaskRunnerInfo.class);
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
    public ResponseData<DataList<SysCritLog>> listCritLog(SysCritLogQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskRunnerInfo.class, queryParam.getBizId());
        queryParam.setBizTypeClass(TaskRunnerInfo.class);
        return dao.list(SysCritLog.class, queryParam);
    }

    /**
     * 新增队列任务配置。
     *
     * @param taskRunnerInfo
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "新增队列任务配置", description = "新增队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskRunnerInfo> save(@RequestBody TaskRunnerInfo taskRunnerInfo) {
        long id = dao.getSequenceId(TaskRunnerInfo.class);
        AuthServiceHelper.logRef(TaskRunnerInfo.class, id);
        taskRunnerInfo.setId(id);
        taskRunnerInfo.setCreateDate(new Date());
        taskRunnerInfo.setModifyDate(null);
        taskRunnerInfo.setState(CommonState.ENABLED.getValue());
        //保存历史记录
        return dao.save(taskRunnerInfo).onSuccess(savedEntity -> {
            SysDataHistoryHelper.saveHistory(taskRunnerInfo);
        });
    }

    /**
     * 修改队列任务配置。
     *
     * @param taskRunnerInfo
     * @return
     */
    @PutMapping("/update")
    @Operation(summary = "修改队列任务配置", description = "修改队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskRunnerInfo> update(@RequestBody TaskRunnerInfo taskRunnerInfo, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskRunnerInfo.class, taskRunnerInfo.getId(), remark);
        return dao.load(TaskRunnerInfo.class, taskRunnerInfo.getId()).onSuccess(taskRunnerInfoDb -> {
            taskRunnerInfoDb.setTaskName(taskRunnerInfo.getTaskName());
            taskRunnerInfoDb.setTaskDesc(taskRunnerInfo.getTaskDesc());
            taskRunnerInfoDb.setTaskClass(taskRunnerInfo.getTaskClass());
            taskRunnerInfoDb.setTaskOwner(taskRunnerInfo.getTaskOwner());
            taskRunnerInfoDb.setTaskTag(taskRunnerInfo.getTaskTag());
            taskRunnerInfoDb.setQueueType(taskRunnerInfo.getQueueType());
            taskRunnerInfoDb.setDelayType(taskRunnerInfo.getDelayType());
            taskRunnerInfoDb.setLogLevel(taskRunnerInfo.getLogLevel());
            taskRunnerInfoDb.setLogLimitSize(taskRunnerInfo.getLogLimitSize());
            taskRunnerInfoDb.setRunType(taskRunnerInfo.getRunType());
            taskRunnerInfoDb.setRunTarget(taskRunnerInfo.getRunTarget());
            taskRunnerInfoDb.setConsumerNum(taskRunnerInfo.getConsumerNum());
            taskRunnerInfoDb.setPrefetchNum(taskRunnerInfo.getPrefetchNum());
            taskRunnerInfoDb.setRateLimitType(taskRunnerInfo.getRateLimitType());
            taskRunnerInfoDb.setRateLimitValue(taskRunnerInfo.getRateLimitValue());
            taskRunnerInfoDb.setRateLimitTime(taskRunnerInfo.getRateLimitTime());
            taskRunnerInfoDb.setRateLimitWait(taskRunnerInfo.getRateLimitWait());
            taskRunnerInfoDb.setRetryTimesByOverrated(taskRunnerInfo.getRetryTimesByOverrated());
            taskRunnerInfoDb.setRetryTimesByPartner(taskRunnerInfo.getRetryTimesByPartner());
            taskRunnerInfoDb.setStatsDate(taskRunnerInfo.getStatsDate());
            taskRunnerInfoDb.setStatsRunNum(taskRunnerInfo.getStatsRunNum());
            taskRunnerInfoDb.setStatsFailNum(taskRunnerInfo.getStatsFailNum());
            taskRunnerInfoDb.setStatsRunTime(taskRunnerInfo.getStatsRunTime());
            taskRunnerInfoDb.setAlertFailRate(taskRunnerInfo.getAlertFailRate());
            taskRunnerInfoDb.setAlertFailPartnerRate(taskRunnerInfo.getAlertFailPartnerRate());
            taskRunnerInfoDb.setAlertFailProgramRate(taskRunnerInfo.getAlertFailProgramRate());
            taskRunnerInfoDb.setAlertFailConfigRate(taskRunnerInfo.getAlertFailConfigRate());
            taskRunnerInfoDb.setAlertFailDataRate(taskRunnerInfo.getAlertFailDataRate());
            taskRunnerInfoDb.setAlertQueueOversize(taskRunnerInfo.getAlertQueueOversize());
            taskRunnerInfoDb.setAlertQueueTimeout(taskRunnerInfo.getAlertQueueTimeout());
            taskRunnerInfoDb.setAlertWaitTimeout(taskRunnerInfo.getAlertWaitTimeout());
            taskRunnerInfoDb.setAlertRunTimeout(taskRunnerInfo.getAlertRunTimeout());
            taskRunnerInfoDb.setTaskLinkOur(taskRunnerInfo.getTaskLinkOur());
            taskRunnerInfoDb.setTaskLinkMch(taskRunnerInfo.getTaskLinkMch());
            taskRunnerInfoDb.setModifyDate(new Date());
            return dao.update(taskRunnerInfoDb).onSuccess(updatedEntity -> {
                SysDataHistoryHelper.saveHistory(taskRunnerInfoDb, remark);
            });
        });
    }

    /**
     * 启用队列任务配置。
     *
     * @param id
     */
    @PutMapping("/enable")
    @Operation(summary = "启用队列任务配置", description = "启用队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskRunnerInfo.class, id, remark);
        return dao.update(new TaskRunnerInfo().modifyDate(new Date()).state(CommonState.ENABLED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 禁用队列任务配置。
     *
     * @param id
     */
    @PutMapping("/disable")
    @Operation(summary = "禁用队列任务配置", description = "禁用队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskRunnerInfo.class, id, remark);
        return dao.update(new TaskRunnerInfo().modifyDate(new Date()).state(CommonState.DISABLED.getValue()), new IdStateQueryParam(id, CommonState.ENABLED.getValue()));
    }

    /**
     * 删除队列任务配置。
     *
     * @param id
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除队列任务配置", description = "删除队列任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskRunnerInfo.class, id, remark);
        return dao.update(new TaskRunnerInfo().modifyDate(new Date()).state(CommonState.DELETED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
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
    public ResponseData resetStats(@Parameter(description = "主键") long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskRunnerInfo.class, id, remark);
        return dao.update(new TaskRunnerInfo().statsDate(null).statsRunNum(0).statsFailNum(0).statsRunTime(0), new IdQueryParam(id));


    }

}
