package uw.task.center.controller.ops.host;

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
import uw.common.app.dto.IdStateQueryParam;
import uw.common.dto.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskHostInfoQueryParam;
import uw.task.center.entity.TaskHostInfo;

/**
 * 主机状态管理
 */
@RestController
@RequestMapping("/ops/host/info")
@Tag(name = "主机状态报表")
@MscPermDeclare(user = UserType.OPS)
public class TaskHostInfoController {
    private final DaoManager dao = DaoManager.getInstance();


    /**
     * 列表task主机信息。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @GetMapping("/list")
    @Operation(summary = "列表task主机信息", description = "列表task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<DataList<TaskHostInfo>> list(TaskHostInfoQueryParam queryParam) {
        AuthServiceHelper.logRef(TaskHostInfo.class);
        return dao.list(TaskHostInfo.class, queryParam);
    }


    /**
     * 加载task主机信息。
     *
     * @param id
     * @throws TransactionException
     */
    @GetMapping("/load")
    @Operation(summary = "加载task主机信息", description = "加载task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<TaskHostInfo> load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
        AuthServiceHelper.logRef(TaskHostInfo.class, id);
        return dao.load(TaskHostInfo.class, id);
    }

    /**
     * 启用task主机信息。
     *
     * @param id
     */
    @PutMapping("/enable")
    @Operation(summary = "启用task主机信息", description = "启用task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskHostInfo.class, id, remark);
        return dao.update(new TaskHostInfo().modifyDate(SystemClock.nowDate()).state(CommonState.ENABLED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 禁用task主机信息。
     *
     * @param id
     */
    @PutMapping("/disable")
    @Operation(summary = "禁用task主机信息", description = "禁用task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskHostInfo.class, id, remark);
        return dao.update(new TaskHostInfo().modifyDate(SystemClock.nowDate()).state(CommonState.DISABLED.getValue()), new IdStateQueryParam(id, CommonState.ENABLED.getValue()));
    }

    /**
     * 删除task主机信息。
     *
     * @param id
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除task主机信息", description = "删除task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskHostInfo.class, id, remark);
        return dao.update(new TaskHostInfo().modifyDate(SystemClock.nowDate()).state(CommonState.DELETED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

}
