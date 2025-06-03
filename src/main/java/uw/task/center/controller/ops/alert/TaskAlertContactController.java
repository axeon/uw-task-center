package uw.task.center.controller.ops.alert;

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
import uw.common.app.dto.SysCritLogQueryParam;
import uw.common.app.dto.SysDataHistoryQueryParam;
import uw.common.app.entity.SysCritLog;
import uw.common.app.entity.SysDataHistory;
import uw.common.app.helper.SysDataHistoryHelper;
import uw.common.dto.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.dto.TaskAlertContactQueryParam;
import uw.task.center.entity.TaskAlertContact;

/**
 * 报警联系人配置表：增删改查
 */

@RestController
@RequestMapping("/ops/alert/contact")
@Tag(name = "报警联系人管理")
@MscPermDeclare(user = UserType.OPS)
public class TaskAlertContactController {
    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 轻量级列表报警联系信息，一般用于select控件。
     *
     * @return
     */
    @GetMapping("/liteList")
    @Operation(summary = "轻量级列表报警联系信息", description = "轻量级列表报警联系信息，一般用于select控件。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public ResponseData<DataList<TaskAlertContact>> liteList(TaskAlertContactQueryParam queryParam) {
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
    public ResponseData<TaskAlertContact> load(@Parameter(description = "主键ID", required = true) @RequestParam long id) {
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
    public ResponseData<DataList<SysDataHistory>> listDataHistory(SysDataHistoryQueryParam queryParam) {
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
    public ResponseData<DataList<SysCritLog>> listCritLog(SysCritLogQueryParam queryParam) {
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
    public ResponseData<TaskAlertContact> save(@RequestBody TaskAlertContact taskAlertContact) {
        long id = dao.getSequenceId( TaskAlertContact.class );
        AuthServiceHelper.logRef( TaskAlertContact.class, id );
        taskAlertContact.setId( id );
        taskAlertContact.setCreateDate( SystemClock.nowDate() );
        taskAlertContact.setModifyDate( null );
        taskAlertContact.setState( CommonState.ENABLED.getValue() );
        //保存历史记录
        return dao.save( taskAlertContact ).onSuccess(savedEntity -> {
            SysDataHistoryHelper.saveHistory(taskAlertContact);
        });
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
    public ResponseData<TaskAlertContact> update(@RequestBody TaskAlertContact taskAlertContact, @Parameter( description = "备注") @RequestParam String remark) {
        AuthServiceHelper.logInfo(TaskAlertContact.class,taskAlertContact.getId(),remark);
        return  dao.load( TaskAlertContact.class, taskAlertContact.getId() ).onSuccess(taskAlertContactDb-> {
            taskAlertContactDb.setContactType(taskAlertContact.getContactType());
            taskAlertContactDb.setContactName(taskAlertContact.getContactName());
            taskAlertContactDb.setMobile(taskAlertContact.getMobile());
            taskAlertContactDb.setEmail(taskAlertContact.getEmail());
            taskAlertContactDb.setWechat(taskAlertContact.getWechat());
            taskAlertContactDb.setIm(taskAlertContact.getIm());
            taskAlertContactDb.setNotifyUrl(taskAlertContact.getNotifyUrl());
            taskAlertContactDb.setRemark(taskAlertContact.getRemark());
            taskAlertContactDb.setModifyDate(SystemClock.nowDate());
            return dao.update( taskAlertContactDb ).onSuccess(updatedEntity -> {
                SysDataHistoryHelper.saveHistory( taskAlertContactDb,remark );
            } );
        } );
    }

    /**
     * 启用报警联系信息。
     *
     * @param id
     *
     */
    @PutMapping("/enable")
    @Operation(summary = "启用报警联系信息", description = "启用报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData enable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark){
        AuthServiceHelper.logInfo(TaskAlertContact.class,id,remark);
        return dao.update(new TaskAlertContact().modifyDate(SystemClock.nowDate()).state(CommonState.ENABLED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

    /**
     * 禁用报警联系信息。
     *
     * @param id
     *
     */
    @PutMapping("/disable")
    @Operation(summary = "禁用报警联系信息", description = "禁用报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData disable(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark){
        AuthServiceHelper.logInfo(TaskAlertContact.class,id,remark);
        return dao.update(new TaskAlertContact().modifyDate(SystemClock.nowDate()).state(CommonState.DISABLED.getValue()), new IdStateQueryParam(id, CommonState.ENABLED.getValue()));
    }

    /**
     * 删除报警联系信息。
     *
     * @param id
     *
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除报警联系信息", description = "删除报警联系信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.CRIT)
    public ResponseData delete(@Parameter(description = "主键ID") @RequestParam long id, @Parameter(description = "备注") @RequestParam String remark){
        AuthServiceHelper.logInfo(TaskAlertContact.class,id,remark);
        return dao.update(new TaskAlertContact().modifyDate(SystemClock.nowDate()).state(CommonState.DELETED.getValue()), new IdStateQueryParam(id, CommonState.DISABLED.getValue()));
    }

}