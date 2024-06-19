package uw.task.center.controller.ops.alert;

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
import uw.task.center.dto.TaskAlertNotifyQueryParam;
import uw.task.center.entity.TaskAlertNotify;

/**
 * 报警信息通知表：增删改查
 */

@RestController
@Tag(name = "报警通知管理")
@RequestMapping("/ops/alert/notify")
@MscPermDeclare(type = UserType.OPS)
public class TaskAlertNotifyController {
    private DaoFactory dao = DaoFactory.getInstance();

    /**
     * 列表报警信息通知。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    @Operation(summary = "列表报警信息通知", description = "列表报警信息通知")
    @GetMapping("/list")
    public DataList<TaskAlertNotify> list(TaskAlertNotifyQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.log(TaskAlertNotify.class, 0, "列表报警信息通知");
        return dao.list(TaskAlertNotify.class, queryParam);
    }

    /**
     * 加载报警信息通知。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    @Operation(summary = "加载报警信息通知", description = "加载报警信息通知")
    @GetMapping("/load")
    public TaskAlertNotify load(@Parameter(description = "主键ID", required = true, example = "1")
                                @RequestParam long id) throws TransactionException {
        AuthServiceHelper.log(TaskAlertNotify.class, id, "加载报警信息通知");
        return dao.load(TaskAlertNotify.class, id);
    }

}
