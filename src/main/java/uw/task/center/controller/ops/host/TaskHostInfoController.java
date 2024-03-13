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
import uw.auth.service.dto.ResponseData;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskHostInfoQueryParam;
import uw.task.center.entity.TaskHostInfo;


import java.util.Date;
import java.util.Objects;

/**
 * 主机状态管理
 */
@RestController
@Tag(name = "主机状态报表")
@RequestMapping("/ops/host/info")
@MscPermDeclare(type = UserType.OPS)
public class TaskHostInfoController {
    private DaoFactory dao = DaoFactory.getInstance();


    /**
     * 列表主机状态
     */
    @Operation(summary = "列表主机状态", description = "列表主机状态")
    @GetMapping("/list")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public DataList<TaskHostInfo> list(TaskHostInfoQueryParam queryParam) throws TransactionException {
        DataList<TaskHostInfo> list = dao.list( TaskHostInfo.class, queryParam );
        return list;
    }

    /**
     * 加载主机状态
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    @Operation(summary = "加载主机状态", description = "加载主机状态")
    @GetMapping("/load")
    public TaskHostInfo load(@Parameter(description = "主键ID", required = true, example = "1")
                             @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logInfo( TaskHostInfo.class, id, "加载主机状态" );
        return dao.load( TaskHostInfo.class, id );
    }


    /**
     * 开启主机报告。
     */
    @Operation(summary = "开启主机报告", description = "开启主机报告")
    @PostMapping("/enable")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskHostInfo taskHostInfo = dao.load(TaskHostInfo.class, id);
        if (taskHostInfo != null) {
            taskHostInfo.setModifyDate(new Date());
            taskHostInfo.setState(1);
            dao.update(taskHostInfo);
            AuthServiceHelper.logInfo(TaskHostInfo.class,id,"启用task主机信息成功！"+remark);
            return ResponseData.successMsg("启用task主机信息成功！"+remark);
        }else{
            AuthServiceHelper.logInfo(TaskHostInfo.class,id,"启用task主机信息失败！"+remark);
            return ResponseData.errorMsg("启用task主机信息失败！"+remark);
        }
    }

    /**
     * 屏蔽主机报告。
     */
    @Operation(summary = "屏蔽主机报告", description = "屏蔽主机报告")
    @PostMapping("/disable")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskHostInfo taskHostInfo = dao.load(TaskHostInfo.class, id);
        if (taskHostInfo != null) {
            taskHostInfo.setModifyDate(new Date());
            taskHostInfo.setState(0);
            dao.update(taskHostInfo);
            AuthServiceHelper.logInfo(TaskHostInfo.class,id,"禁用task主机信息成功！"+remark);
            return ResponseData.successMsg("禁用task主机信息成功！"+remark);
        }else{
            AuthServiceHelper.logInfo(TaskHostInfo.class,id,"禁用task主机信息失败！"+remark);
            return ResponseData.errorMsg("禁用task主机信息失败！"+remark);
        }
    }

}
