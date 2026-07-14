package uw.task.center.controller.ops.delayer;

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
import uw.common.data.PageList;
import uw.common.app.entity.SysCritLog;
import uw.common.app.entity.SysDataHistory;
import uw.common.app.helper.SysDataHistoryHelper;
import uw.common.response.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.task.center.dto.TaskDelayerInfoQueryParam;
import uw.task.center.entity.TaskDelayerInfo;

/**
 * 延迟任务配置管理接口（OPS）。
 *
 * <p>维护延迟任务（task_delayer_info）的服务端配置：执行并发、限速、重试、告警阈值、联系人等，
 * 支持配置历史回溯。任务执行主机通过 RPC 增量拉取这些配置。三元组 taskClass+taskTag+runTarget 唯一。</p>
 *
 * @author axeon
 */
@RestController
@RequestMapping("/ops/delayer/info")
@Tag(name = "延迟任务管理", description = "延迟任务管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskDelayerInfoController {

    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 列表延迟任务配置。
     */
    @GetMapping("/list")
    @Operation(summary = "列表延迟任务配置", description = "列表延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<PageList<TaskDelayerInfo>> list(TaskDelayerInfoQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskDelayerInfo.class);
        return dao.list(TaskDelayerInfo.class, queryParam);
    }

    /**
     * 轻量级列表（select 控件用）。
     */
    @GetMapping("/listLite")
    @Operation(summary = "轻量级列表延迟任务配置", description = "轻量级列表，用于select控件。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public ResponseData<PageList<TaskDelayerInfo>> listLite(TaskDelayerInfoQueryParam queryParam) {
        queryParam.SELECT_SQL("SELECT id,task_name,task_class,task_owner,task_tag,run_target,poll_interval,prefetch_num,consumer_num,"
                + "rate_limit_type,rate_limit_value,rate_limit_time,rate_limit_wait,retry_times_by_overrated,"
                + "retry_times_by_partner,retry_times_by_program,log_level,log_limit_size,"
                + "stats_date,stats_run_num,stats_fail_num,stats_run_time,"
                + "alert_fail_rate,alert_fail_partner_rate,alert_fail_program_rate,alert_fail_config_rate,alert_fail_data_rate,alert_run_timeout,alert_wait_timeout,"
                + "task_link_our,task_link_mch,create_date,modify_date,state from task_delayer_info ");
        return dao.list(TaskDelayerInfo.class, queryParam);
    }

    /**
     * 加载延迟任务配置。
     */
    @GetMapping("/load")
    @Operation(summary = "加载延迟任务配置", description = "加载延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<TaskDelayerInfo> load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
        AuthServiceHelper.logRef(TaskDelayerInfo.class, id);
        return dao.load(TaskDelayerInfo.class, id);
    }

    /**
     * 新增延迟任务配置。
     */
    @PostMapping("/save")
    @Operation(summary = "新增延迟任务配置", description = "新增延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskDelayerInfo> save(@RequestBody TaskDelayerInfo taskDelayInfo) {
        // 三元组 taskClass + taskTag + runTarget 唯一。
        ResponseData<TaskDelayerInfo> checkResult = checkDuplicate(taskDelayInfo, 0);
        if (checkResult.isNotSuccess()) {
            return checkResult;
        }
        long id = dao.getSequenceId(TaskDelayerInfo.class);
        AuthServiceHelper.logRef(TaskDelayerInfo.class, id);
        taskDelayInfo.setId(id);
        taskDelayInfo.setCreateDate(SystemClock.nowDate());
        taskDelayInfo.setModifyDate(null);
        taskDelayInfo.setState(CommonState.ENABLED.getValue());
        return dao.save(taskDelayInfo).onSuccess(savedEntity -> {
            SysDataHistoryHelper.saveHistory(taskDelayInfo);
        });
    }

    /**
     * 修改延迟任务配置（load-改-update 模式，避免覆盖统计/历史字段）。
     */
    @PutMapping("/update")
    @Operation(summary = "修改延迟任务配置", description = "修改延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskDelayerInfo> update(@RequestBody TaskDelayerInfo taskDelayInfo, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskDelayerInfo.class, taskDelayInfo.getId(), remark);
        // 修改时校验三元组与他人冲突（排除自身）。
        ResponseData<TaskDelayerInfo> checkResult = checkDuplicate(taskDelayInfo, taskDelayInfo.getId());
        if (checkResult.isNotSuccess()) {
            return checkResult;
        }
        return dao.load(TaskDelayerInfo.class, taskDelayInfo.getId()).onSuccess(db -> {
            db.setTaskName(taskDelayInfo.getTaskName());
            db.setTaskDesc(taskDelayInfo.getTaskDesc());
            db.setTaskClass(taskDelayInfo.getTaskClass());
            db.setTaskOwner(taskDelayInfo.getTaskOwner());
            db.setTaskTag(taskDelayInfo.getTaskTag());
            db.setRunTarget(taskDelayInfo.getRunTarget());
            db.setConsumerNum(taskDelayInfo.getConsumerNum());
            db.setRateLimitType(taskDelayInfo.getRateLimitType());
            db.setRateLimitValue(taskDelayInfo.getRateLimitValue());
            db.setRateLimitTime(taskDelayInfo.getRateLimitTime());
            db.setRateLimitWait(taskDelayInfo.getRateLimitWait());
            db.setRetryTimesByOverrated(taskDelayInfo.getRetryTimesByOverrated());
            db.setRetryTimesByPartner(taskDelayInfo.getRetryTimesByPartner());
            db.setRetryTimesByProgram(taskDelayInfo.getRetryTimesByProgram());
            db.setPollInterval(taskDelayInfo.getPollInterval());
            db.setPrefetchNum(taskDelayInfo.getPrefetchNum());
            db.setLogLevel(taskDelayInfo.getLogLevel());
            db.setLogLimitSize(taskDelayInfo.getLogLimitSize());
            db.setAlertFailRate(taskDelayInfo.getAlertFailRate());
            db.setAlertFailPartnerRate(taskDelayInfo.getAlertFailPartnerRate());
            db.setAlertFailProgramRate(taskDelayInfo.getAlertFailProgramRate());
            db.setAlertFailConfigRate(taskDelayInfo.getAlertFailConfigRate());
            db.setAlertFailDataRate(taskDelayInfo.getAlertFailDataRate());
            db.setAlertRunTimeout(taskDelayInfo.getAlertRunTimeout());
            db.setAlertWaitTimeout(taskDelayInfo.getAlertWaitTimeout());
            db.setTaskLinkOur(taskDelayInfo.getTaskLinkOur());
            db.setTaskLinkMch(taskDelayInfo.getTaskLinkMch());
            db.setModifyDate(SystemClock.nowDate());
            return dao.update(db).onSuccess(updatedEntity -> {
                SysDataHistoryHelper.saveHistory(db, remark);
            });
        });
    }

    /**
     * 启用延迟任务配置。
     */
    @PutMapping("/enable")
    @Operation(summary = "启用延迟任务配置", description = "启用延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskDelayerInfo.class, id, remark);
        TaskDelayerInfo update = new TaskDelayerInfo();
        update.setModifyDate(SystemClock.nowDate());
        update.setState(CommonState.ENABLED.getValue());
        return dao.update(update, new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 禁用延迟任务配置。
     */
    @PutMapping("/disable")
    @Operation(summary = "禁用延迟任务配置", description = "禁用延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskDelayerInfo.class, id, remark);
        TaskDelayerInfo update = new TaskDelayerInfo();
        update.setModifyDate(SystemClock.nowDate());
        update.setState(CommonState.DISABLED.getValue());
        return dao.update(update, new IdStateQueryParam(id, CommonState.ENABLED.getValue()));
    }

    /**
     * 删除延迟任务配置（软删除 state=-1，前置须 DISABLED）。
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除延迟任务配置", description = "删除延迟任务配置")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskDelayerInfo.class, id, remark);
        TaskDelayerInfo update = new TaskDelayerInfo();
        update.setModifyDate(SystemClock.nowDate());
        update.setState(CommonState.DELETED.getValue());
        return dao.update(update, new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 清空累计统计数据。
     */
    @PutMapping("/resetStats")
    @Operation(summary = "清空统计数据", description = "清空统计数据")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData resetStats(@Parameter(description = "主键") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskDelayerInfo.class, id, remark);
        TaskDelayerInfo update = new TaskDelayerInfo();
        update.setStatsDate(null);
        update.setStatsRunNum(0);
        update.setStatsFailNum(0);
        update.setStatsRunTime(0);
        return dao.update(update, new IdQueryParam(id));
    }

    /**
     * 校验延迟任务配置三元组是否重复（与 RPC /delayer/init 去重条件一致）。
     *
     * @param info       待校验配置
     * @param excludeId  排除的自身 id（新增传 0）
     * @return 成功无重复；warn 已存在
     */
    private ResponseData<TaskDelayerInfo> checkDuplicate(TaskDelayerInfo info, long excludeId) {
        String taskClass = info.getTaskClass();
        String taskTag = info.getTaskTag() == null ? "" : info.getTaskTag();
        String runTarget = info.getRunTarget() == null ? "" : info.getRunTarget();
        if (taskClass == null || taskClass.isBlank()) {
            return ResponseData.warn(info, "taskClass不能为空");
        }
        ResponseData<TaskDelayerInfo> queryResult = dao.queryForObject(TaskDelayerInfo.class,
                "select * from task_delayer_info where task_class=? and task_tag=? and run_target=? and state>=0 and id<>?",
                new Object[]{taskClass, taskTag, runTarget, excludeId});
        // queryForObject 查无数据返回 warn，这是"无重复"的正常情况，仅 error 才中断。
        if (queryResult.isError()) {
            return queryResult;
        }
        if (queryResult.getData() != null) {
            return ResponseData.warn(info, "延迟任务配置已存在: taskClass=[" + taskClass + "], taskTag=[" + taskTag + "], runTarget=[" + runTarget + "]");
        }
        return ResponseData.success(info);
    }

    /**
     * 查询数据历史。
     */
    @GetMapping("/listDataHistory")
    @Operation(summary = "查询数据历史", description = "查询数据历史")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<PageList<SysDataHistory>> listDataHistory(SysDataHistoryQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskDelayerInfo.class, queryParam.getEntityId());
        queryParam.setEntityClass(TaskDelayerInfo.class);
        return dao.list(SysDataHistory.class, queryParam);
    }

    /**
     * 查询操作日志。
     */
    @GetMapping("/listCritLog")
    @Operation(summary = "查询操作日志", description = "查询操作日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<PageList<SysCritLog>> listCritLog(SysCritLogQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskDelayerInfo.class, queryParam.getBizId());
        queryParam.setBizTypeClass(TaskDelayerInfo.class);
        return dao.list(SysCritLog.class, queryParam);
    }
}
