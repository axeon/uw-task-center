package uw.task.center.controller.ops.log;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskCritLogQueryParam;
import uw.task.center.entity.TaskCritLog;


/**
 * 关键日志管理。
 */
@RestController
@MscPermDeclare(type = UserType.OPS)
@Tag(name = "关键日志管理", description = "关键日志管理")
@RequestMapping("/ops/log/crit")
public class CritLogController {

    DaoFactory dao = DaoFactory.getInstance();

    /**
     * 列表OPS关键日志。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    @Operation(summary = "列表关键日志", description = "列表关键日志")
    @GetMapping("/list")
    public DataList<TaskCritLog> list(TaskCritLogQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logInfo(TaskCritLog.class, 0, "列表关键日志");
        return dao.list(TaskCritLog.class, queryParam);
    }

    /**
     * 加载关键日志。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    @Operation(summary = "加载关键日志", description = "加载关键日志")
    @GetMapping("/load")
    public TaskCritLog load(@Parameter(description = "主键ID", required = true, example = "1") @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logInfo(TaskCritLog.class, id, "加载关键日志");
        return dao.load(TaskCritLog.class, id);
    }

}