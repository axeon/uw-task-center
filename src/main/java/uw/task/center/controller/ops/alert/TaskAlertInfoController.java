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
import uw.common.dto.ResponseData;
import uw.dao.DaoManager;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskAlertInfoQueryParam;
import uw.task.center.entity.TaskAlertInfo;

/**
 * 报警发送信息表：增删改查
 */
@RestController
@RequestMapping("/ops/alert/info")
@Tag(name = "报警信息管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskAlertInfoController {
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 列表报警信息。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @GetMapping("/list")
    @Operation(summary = "列表报警信息", description = "列表报警信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<DataList<TaskAlertInfo>> list(TaskAlertInfoQueryParam queryParam) {
        AuthServiceHelper.logRef( TaskAlertInfo.class );
        return dao.list( TaskAlertInfo.class, queryParam );
    }

    /**
     * 加载报警信息。
     *
     * @param id
     * @throws TransactionException
     */
    @GetMapping("/load")
    @Operation(summary = "加载报警信息", description = "加载报警信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<TaskAlertInfo> load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
        AuthServiceHelper.logRef( TaskAlertInfo.class, id );
        return dao.load( TaskAlertInfo.class, id );
    }


}
