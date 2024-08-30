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
import uw.common.constant.StateCommon;
import uw.common.dto.ResponseData;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskHostInfoQueryParam;
import uw.task.center.entity.TaskHostInfo;

import java.util.Date;

/**
 * 主机状态管理
 */
@RestController
@RequestMapping("/ops/host/info")
@Tag(name = "主机状态报表")
@MscPermDeclare(user = UserType.OPS)
public class TaskHostInfoController {
    private final DaoFactory dao = DaoFactory.getInstance();


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
    public DataList<TaskHostInfo> list(TaskHostInfoQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef( TaskHostInfo.class );
        return dao.list( TaskHostInfo.class, queryParam );
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
    public TaskHostInfo load(@Parameter(description = "主键ID", required = true, example = "1") @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logRef( TaskHostInfo.class, id );
        return dao.load( TaskHostInfo.class, id );
    }

    /**
     * 启用task主机信息。
     *
     * @param id
     * @throws TransactionException
     */
    @PatchMapping("/enable")
    @Operation(summary = "启用task主机信息", description = "启用task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                               @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskHostInfo.class, id, "启用task主机信息！操作备注：" + remark );
        TaskHostInfo taskHostInfo = dao.load( TaskHostInfo.class, id );
        if (taskHostInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的task主机信息！" );
        }
        if (taskHostInfo.getState() != StateCommon.DISABLED.getValue()) {
            return ResponseData.warnMsg( "启用task主机信息失败！当前状态不是禁用状态！" );
        }
        taskHostInfo.setModifyDate( new Date() );
        taskHostInfo.setState( StateCommon.ENABLED.getValue() );
        dao.update( taskHostInfo );
        return ResponseData.success();
    }

    /**
     * 禁用task主机信息。
     *
     * @param id
     * @throws TransactionException
     */
    @PatchMapping("/disable")
    @Operation(summary = "禁用task主机信息", description = "禁用task主机信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                                @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskHostInfo.class, id, "禁用task主机信息！操作备注：" + remark );
        TaskHostInfo taskHostInfo = dao.load( TaskHostInfo.class, id );
        if (taskHostInfo == null) {
            return ResponseData.warnMsg( "未找到指定id的task主机信息！" );
        }
        if (taskHostInfo.getState() != StateCommon.ENABLED.getValue()) {
            return ResponseData.warnMsg( "禁用task主机信息失败！当前状态不是启用状态！" );
        }
        taskHostInfo.setModifyDate( new Date() );
        taskHostInfo.setState( StateCommon.DISABLED.getValue() );
        dao.update( taskHostInfo );
        return ResponseData.success();
    }

}
