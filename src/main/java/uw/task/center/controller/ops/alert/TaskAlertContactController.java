package uw.task.center.controller.ops.alert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.common.app.dto.SysCritLogQueryParam;
import uw.common.app.dto.SysDataHistoryQueryParam;
import uw.common.app.entity.SysCritLog;
import uw.common.app.entity.SysDataHistory;
import uw.common.app.helper.SysDataHistoryHelper;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.app.constant.CommonState;
import uw.common.dto.ResponseData;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskAlertContactQueryParam;
import uw.task.center.entity.TaskAlertContact;

import java.util.Date;

/**
 * 报警联系人配置表：增删改查
 */

@RestController
@RequestMapping("/ops/alert/contact")
@Tag(name = "报警联系人管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskAlertContactController {
    private final DaoFactory dao = DaoFactory.getInstance();

    /**
     * 轻量级列表报警联系信息，一般用于select控件。
     *
     * @return
     */
    @GetMapping("/liteList")
    @Operation(summary = "轻量级列表报警联系信息", description = "轻量级列表报警联系信息，一般用于select控件。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public DataList<TaskAlertContact> liteList(TaskAlertContactQueryParam queryParam) throws TransactionException {
        queryParam.SELECT_SQL( "SELECT id,contact_type,contact_name,mobile,email,wechat,im,notify_url,remark,create_date,modify_date,state from task_alert_contact " );
        return dao.list(TaskAlertContact.class, queryParam);
    }

    /**
     * 加载报警联系信息。
     *
     * @param id
     * @throws TransactionException
     */
    @GetMapping("/load")
    @Operation(summary = "加载报警联系信息", description = "加载报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public TaskAlertContact load(@Parameter(description = "主键ID", required = true) @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logRef(TaskAlertContact.class,id);
        return dao.load(TaskAlertContact.class, id);
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
    public DataList<SysDataHistory> listDataHistory(SysDataHistoryQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef(TaskAlertContact.class, queryParam.getEntityId());
        queryParam.setEntityClass(TaskAlertContact.class);
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
    public DataList<SysCritLog> listCritLog(SysCritLogQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logRef(TaskAlertContact.class, queryParam.getBizId());
        queryParam.setBizTypeClass(TaskAlertContact.class);
        return dao.list(SysCritLog.class, queryParam);
    }

    /**
     * 新增报警联系信息。
     *
     * @param taskAlertContact
     * @return
     * @throws TransactionException
     */
    @PostMapping("/save")
    @Operation(summary = "新增报警联系信息", description = "新增报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskAlertContact> save(@RequestBody TaskAlertContact taskAlertContact) throws TransactionException {
        long id = dao.getSequenceId( TaskAlertContact.class );
        AuthServiceHelper.logRef( TaskAlertContact.class, id );
        taskAlertContact.setId( id );
        taskAlertContact.setCreateDate( new Date() );
        taskAlertContact.setModifyDate( null );
        taskAlertContact.setState( CommonState.ENABLED.getValue() );
        dao.save( taskAlertContact );
        //保存历史记录
        SysDataHistoryHelper.saveHistory( taskAlertContact.getId(), taskAlertContact, "报警联系信息", "新增报警联系信息" );
        return ResponseData.success( taskAlertContact );
    }

    /**
     * 修改报警联系信息。
     *
     * @param taskAlertContact
     * @return
     * @throws TransactionException
     */
    @PutMapping("/update")
    @Operation(summary = "修改报警联系信息", description = "修改报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData<TaskAlertContact> update(@RequestBody TaskAlertContact taskAlertContact, @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, taskAlertContact.getId(), "修改报警联系信息！操作备注：" + remark );
        TaskAlertContact taskAlertContactDb = dao.load( TaskAlertContact.class, taskAlertContact.getId() );
        if (taskAlertContactDb == null) {
            return ResponseData.warnMsg( "未找到指定ID的报警联系信息！" );
        }
        taskAlertContactDb.setContactType( taskAlertContact.getContactType() );
        taskAlertContactDb.setContactName( taskAlertContact.getContactName() );
        taskAlertContactDb.setMobile( taskAlertContact.getMobile() );
        taskAlertContactDb.setEmail( taskAlertContact.getEmail() );
        taskAlertContactDb.setWechat( taskAlertContact.getWechat() );
        taskAlertContactDb.setIm( taskAlertContact.getIm() );
        taskAlertContactDb.setNotifyUrl( taskAlertContact.getNotifyUrl() );
        taskAlertContactDb.setRemark( taskAlertContact.getRemark() );
        taskAlertContactDb.setModifyDate( new Date() );
        dao.update( taskAlertContactDb );
        SysDataHistoryHelper.saveHistory( taskAlertContactDb.getId(), taskAlertContactDb, "报警联系信息", "修改报警联系信息！操作备注：" + remark );
        return ResponseData.success( taskAlertContactDb );
    }

    /**
     * 启用报警联系信息。
     *
     * @param id
     * @throws TransactionException
     */
    @PutMapping("/enable")
    @Operation(summary = "启用报警联系信息", description = "启用报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(description = "主键ID") @RequestParam long id,
                               @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, id, "启用报警联系信息！操作备注：" + remark );
        TaskAlertContact taskAlertContact = dao.load( TaskAlertContact.class, id );
        if (taskAlertContact == null) {
            return ResponseData.warnMsg( "未找到指定id的报警联系信息！" );
        }
        if (taskAlertContact.getState() != CommonState.DISABLED.getValue()) {
            return ResponseData.warnMsg( "启用报警联系信息失败！当前状态不是禁用状态！" );
        }
        taskAlertContact.setModifyDate( new Date() );
        taskAlertContact.setState( CommonState.ENABLED.getValue() );
        dao.update( taskAlertContact );
        return ResponseData.success();
    }

    /**
     * 禁用报警联系信息。
     *
     * @param id
     * @throws TransactionException
     */
    @PutMapping("/disable")
    @Operation(summary = "禁用报警联系信息", description = "禁用报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(description = "主键ID") @RequestParam long id,
                                @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, id, "禁用报警联系信息！操作备注：" + remark );
        TaskAlertContact taskAlertContact = dao.load( TaskAlertContact.class, id );
        if (taskAlertContact == null) {
            return ResponseData.warnMsg( "未找到指定id的报警联系信息！" );
        }
        if (taskAlertContact.getState() != CommonState.ENABLED.getValue()) {
            return ResponseData.warnMsg( "禁用报警联系信息失败！当前状态不是启用状态！" );
        }
        taskAlertContact.setModifyDate( new Date() );
        taskAlertContact.setState( CommonState.DISABLED.getValue() );
        dao.update( taskAlertContact );
        return ResponseData.success();
    }

    /**
     * 删除报警联系信息。
     *
     * @param id
     * @throws TransactionException
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除报警联系信息", description = "删除报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(description = "主键ID") @RequestParam long id,
                               @Parameter( description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, id, "删除报警联系信息！操作备注：" + remark );
        TaskAlertContact taskAlertContact = dao.load( TaskAlertContact.class, id );
        if (taskAlertContact == null) {
            return ResponseData.warnMsg( "未找到指定id的报警联系信息！" );
        }
        if (taskAlertContact.getState() != CommonState.DISABLED.getValue()) {
            return ResponseData.warnMsg( "删除报警联系信息失败！当前状态不是禁用状态！" );
        }
        taskAlertContact.setModifyDate( new Date() );
        taskAlertContact.setState( CommonState.DELETED.getValue() );
        dao.update( taskAlertContact );
        return ResponseData.success();
    }

}