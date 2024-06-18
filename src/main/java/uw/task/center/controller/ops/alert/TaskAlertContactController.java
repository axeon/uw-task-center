package uw.task.center.controller.ops.alert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uw.app.common.helper.SysDataHistoryHelper;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
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
@Tag(name = "报警联系人管理")
@RequestMapping("/ops/alert/contact")
@MscPermDeclare(type = UserType.OPS)
public class TaskAlertContactController {
    private DaoFactory dao = DaoFactory.getInstance();

    /**
     * 列表报警联系人。
     *
     * @param queryParam
     * @return
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    @Operation(summary = "列表报警联系人", description = "列表报警联系人")
    @GetMapping("/list")
    public DataList<TaskAlertContact> list(TaskAlertContactQueryParam queryParam) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, 0, "列表报警联系人" );
        return dao.list( TaskAlertContact.class, queryParam );
    }

    /**
     * 加载报警联系人。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.USER, log = ActionLog.REQUEST)
    @Operation(summary = "加载报警联系人", description = "加载报警联系人")
    @GetMapping("/load")
    public TaskAlertContact load(@Parameter(description = "主键ID", required = true, example = "1")
                                 @RequestParam long id) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, id, "加载报警联系人" );
        return dao.load( TaskAlertContact.class, id );
    }

    /**
     * 新增报警联系人。
     *
     * @param taskAlertContact
     * @return
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "保存报警联系人", description = "新增报警联系人")
    @PostMapping("/save")
    public ResponseData<TaskAlertContact> save(@RequestBody TaskAlertContact taskAlertContact) throws TransactionException {
        long id = dao.getSequenceId( TaskAlertContact.class );
        AuthServiceHelper.logInfo( TaskAlertContact.class, id, "新增报警联系人" );
        taskAlertContact.setId( id );
        taskAlertContact.setCreateDate( new Date() );
        taskAlertContact.setModifyDate( null );
        taskAlertContact.setState( 1 );
        dao.save( taskAlertContact );
        SysDataHistoryHelper.saveHistory( taskAlertContact.getId(), taskAlertContact ,"","");
        return ResponseData.success( taskAlertContact );
    }

    /**
     * 修改报警联系人。
     *
     * @param taskAlertContact
     * @return
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "修改报警联系人", description = "修改报警联系人")
    @PutMapping("/update")
    public ResponseData<TaskAlertContact> update(@RequestBody TaskAlertContact taskAlertContact, @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        AuthServiceHelper.logInfo( TaskAlertContact.class, taskAlertContact.getId(), "修改报警联系信息!" + remark );
        TaskAlertContact taskAlertContactDb = dao.load( TaskAlertContact.class, taskAlertContact.getId() );
        if (taskAlertContactDb == null) {
            return ResponseData.errorMsg( "未找到指定ID的数值！" );
        }
        taskAlertContactDb.setContactType( taskAlertContact.getContactType() );
        taskAlertContactDb.setContactName( taskAlertContact.getContactName() );
        taskAlertContactDb.setMobile( taskAlertContact.getMobile() );
        taskAlertContactDb.setEmail( taskAlertContact.getEmail() );
        taskAlertContactDb.setWechat( taskAlertContact.getWechat() );
        taskAlertContactDb.setIm( taskAlertContact.getIm() );
        taskAlertContactDb.setNotifyUrl( taskAlertContact.getNotifyUrl() );
        taskAlertContactDb.setRemark( taskAlertContact.getRemark() );
        //保存新记录。
        taskAlertContactDb.setModifyDate( new Date() );
        dao.update( taskAlertContactDb );
        SysDataHistoryHelper.saveHistory( taskAlertContactDb.getId(), taskAlertContactDb ,"","");
        return ResponseData.success( taskAlertContactDb );
    }

    /**
     * 删除报警联系人。
     *
     * @param id
     * @throws TransactionException
     */
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    @Operation(summary = "删除报警联系人", description = "删除报警联系人")
    @DeleteMapping("/delete")
    public ResponseData delete(@Parameter(name = "id", description = "主键ID", example = "1") @RequestParam long id,
                               @Parameter(name = "remark", description = "备注") @RequestParam String remark) throws TransactionException {
        TaskAlertContact taskAlertContact = dao.load( TaskAlertContact.class, id );
        if (taskAlertContact != null) {
            taskAlertContact.setModifyDate( new Date() );
            taskAlertContact.setState( -1 );
            dao.update( taskAlertContact );
            AuthServiceHelper.logInfo( TaskAlertContact.class, id, "删除报警联系信息成功！" + remark );
            return ResponseData.successMsg( "删除报警联系信息成功！" + remark );
        } else {
            AuthServiceHelper.logInfo( TaskAlertContact.class, id, "删除报警联系信息失败！" + remark );
            return ResponseData.errorMsg( "删除报警联系信息失败！" + remark );
        }
    }


}
