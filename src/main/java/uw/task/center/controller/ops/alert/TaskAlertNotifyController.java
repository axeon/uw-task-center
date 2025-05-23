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
import uw.dao.DaoManager;
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
@MscPermDeclare(user = UserType.OPS)
public class TaskAlertNotifyController {
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 列表报警信息通知。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @GetMapping("/list")
    @Operation(summary = "列表报警信息通知", description = "列表报警信息通知")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<TaskAlertNotify> list(TaskAlertNotifyQueryParam queryParam) {
        AuthServiceHelper.logRef( TaskAlertNotify.class );
        return dao.list( TaskAlertNotify.class, queryParam ).getData();
    }

    /**
     * 加载报警信息通知。
     *
     * @param id
     * @throws TransactionException
     */
    @GetMapping("/load")
    @Operation(summary = "加载报警信息通知", description = "加载报警信息通知")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public TaskAlertNotify load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
        AuthServiceHelper.logRef( TaskAlertNotify.class, id );
        return dao.load( TaskAlertNotify.class, id ).getData();
    }

}
