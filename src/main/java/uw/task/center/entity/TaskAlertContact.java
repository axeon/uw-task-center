package uw.task.center.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.util.JsonUtils;
import uw.dao.DataEntity;
import uw.dao.DataUpdateInfo;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

import java.io.Serializable;


/**
 * TaskAlertContact实体类
 * 报警联系信息
 *
 * @author axeon
 */
@TableMeta(tableName="task_alert_contact",tableType="table")
@Schema(title = "报警联系信息", description = "报警联系信息")
public class TaskAlertContact implements DataEntity,Serializable{


    /**
     * id
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "id", description = "id", maxLength=19, nullable=false )
    private long id;

    /**
     * 联系人类型
     */
    @ColumnMeta(columnName="contact_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "联系人类型", description = "联系人类型", maxLength=10, nullable=true )
    private int contactType;

    /**
     * 联系人
     */
    @ColumnMeta(columnName="contact_name", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系人", description = "联系人", maxLength=100, nullable=true )
    private String contactName;

    /**
     * 联系电话
     */
    @ColumnMeta(columnName="mobile", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系电话", description = "联系电话", maxLength=100, nullable=true )
    private String mobile;

    /**
     * 联系email
     */
    @ColumnMeta(columnName="email", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系email", description = "联系email", maxLength=100, nullable=true )
    private String email;

    /**
     * 联系微信
     */
    @ColumnMeta(columnName="wechat", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系微信", description = "联系微信", maxLength=100, nullable=true )
    private String wechat;

    /**
     * 备用im
     */
    @ColumnMeta(columnName="im", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "备用im", description = "备用im", maxLength=100, nullable=true )
    private String im;

    /**
     * 通知链接，如钉钉，微信等
     */
    @ColumnMeta(columnName="notify_url", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "通知链接，如钉钉，微信等", description = "通知链接，如钉钉，微信等", maxLength=500, nullable=true )
    private String notifyUrl;

    /**
     * 备注
     */
    @ColumnMeta(columnName="remark", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "备注", description = "备注", maxLength=500, nullable=true )
    private String remark;

    /**
     * 创建日期
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建日期", description = "创建日期", maxLength=23, nullable=true )
    private java.util.Date createDate;

    /**
     * 修改日期
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "修改日期", description = "修改日期", maxLength=23, nullable=true )
    private java.util.Date modifyDate;

    /**
     * 状态
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态", description = "状态", maxLength=10, nullable=true )
    private int state;

    /**
     * 数据更新信息.
     */
    private transient DataUpdateInfo _UPDATED_INFO = null;

    /**
     * 是否加载完成.
     */
    private transient boolean _IS_LOADED;

    /**
     * 获得实体的表名。
     */
    @Override
    public String ENTITY_TABLE(){
        return "task_alert_contact";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "报警联系信息";
    }

    /**
     * 获得主键
     */
    @Override
    public Serializable ENTITY_ID(){
        return getId();
    }

    /**
     * 获取更新信息.
     */
    @Override
    public DataUpdateInfo GET_UPDATED_INFO() {
        return this._UPDATED_INFO;
    }

    /**
     * 清除更新信息.
     */
    @Override
    public void CLEAR_UPDATED_INFO() {
        _UPDATED_INFO = null;
    }


    /**
     * 获取id。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获取联系人类型。
     */
    public int getContactType(){
        return this.contactType;
    }

    /**
     * 获取联系人。
     */
    public String getContactName(){
        return this.contactName;
    }

    /**
     * 获取联系电话。
     */
    public String getMobile(){
        return this.mobile;
    }

    /**
     * 获取联系email。
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * 获取联系微信。
     */
    public String getWechat(){
        return this.wechat;
    }

    /**
     * 获取备用im。
     */
    public String getIm(){
        return this.im;
    }

    /**
     * 获取通知链接，如钉钉，微信等。
     */
    public String getNotifyUrl(){
        return this.notifyUrl;
    }

    /**
     * 获取备注。
     */
    public String getRemark(){
        return this.remark;
    }

    /**
     * 获取创建日期。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获取修改日期。
     */
    public java.util.Date getModifyDate(){
        return this.modifyDate;
    }

    /**
     * 获取状态。
     */
    public int getState(){
        return this.state;
    }


    /**
     * 设置id。
     */
    public void setId(long id){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "id", this.id, id, !_IS_LOADED );
        this.id = id;
    }

    /**
     *  设置id链式调用。
     */
    public TaskAlertContact id(long id){
        setId(id);
        return this;
    }

    /**
     * 设置联系人类型。
     */
    public void setContactType(int contactType){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "contactType", this.contactType, contactType, !_IS_LOADED );
        this.contactType = contactType;
    }

    /**
     *  设置联系人类型链式调用。
     */
    public TaskAlertContact contactType(int contactType){
        setContactType(contactType);
        return this;
    }

    /**
     * 设置联系人。
     */
    public void setContactName(String contactName){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "contactName", this.contactName, contactName, !_IS_LOADED );
        this.contactName = contactName;
    }

    /**
     *  设置联系人链式调用。
     */
    public TaskAlertContact contactName(String contactName){
        setContactName(contactName);
        return this;
    }

    /**
     * 设置联系电话。
     */
    public void setMobile(String mobile){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "mobile", this.mobile, mobile, !_IS_LOADED );
        this.mobile = mobile;
    }

    /**
     *  设置联系电话链式调用。
     */
    public TaskAlertContact mobile(String mobile){
        setMobile(mobile);
        return this;
    }

    /**
     * 设置联系email。
     */
    public void setEmail(String email){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "email", this.email, email, !_IS_LOADED );
        this.email = email;
    }

    /**
     *  设置联系email链式调用。
     */
    public TaskAlertContact email(String email){
        setEmail(email);
        return this;
    }

    /**
     * 设置联系微信。
     */
    public void setWechat(String wechat){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "wechat", this.wechat, wechat, !_IS_LOADED );
        this.wechat = wechat;
    }

    /**
     *  设置联系微信链式调用。
     */
    public TaskAlertContact wechat(String wechat){
        setWechat(wechat);
        return this;
    }

    /**
     * 设置备用im。
     */
    public void setIm(String im){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "im", this.im, im, !_IS_LOADED );
        this.im = im;
    }

    /**
     *  设置备用im链式调用。
     */
    public TaskAlertContact im(String im){
        setIm(im);
        return this;
    }

    /**
     * 设置通知链接，如钉钉，微信等。
     */
    public void setNotifyUrl(String notifyUrl){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "notifyUrl", this.notifyUrl, notifyUrl, !_IS_LOADED );
        this.notifyUrl = notifyUrl;
    }

    /**
     *  设置通知链接，如钉钉，微信等链式调用。
     */
    public TaskAlertContact notifyUrl(String notifyUrl){
        setNotifyUrl(notifyUrl);
        return this;
    }

    /**
     * 设置备注。
     */
    public void setRemark(String remark){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "remark", this.remark, remark, !_IS_LOADED );
        this.remark = remark;
    }

    /**
     *  设置备注链式调用。
     */
    public TaskAlertContact remark(String remark){
        setRemark(remark);
        return this;
    }

    /**
     * 设置创建日期。
     */
    public void setCreateDate(java.util.Date createDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "createDate", this.createDate, createDate, !_IS_LOADED );
        this.createDate = createDate;
    }

    /**
     *  设置创建日期链式调用。
     */
    public TaskAlertContact createDate(java.util.Date createDate){
        setCreateDate(createDate);
        return this;
    }

    /**
     * 设置修改日期。
     */
    public void setModifyDate(java.util.Date modifyDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "modifyDate", this.modifyDate, modifyDate, !_IS_LOADED );
        this.modifyDate = modifyDate;
    }

    /**
     *  设置修改日期链式调用。
     */
    public TaskAlertContact modifyDate(java.util.Date modifyDate){
        setModifyDate(modifyDate);
        return this;
    }

    /**
     * 设置状态。
     */
    public void setState(int state){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "state", this.state, state, !_IS_LOADED );
        this.state = state;
    }

    /**
     *  设置状态链式调用。
     */
    public TaskAlertContact state(int state){
        setState(state);
        return this;
    }

    /**
     * 重载toString方法.
     */
    @Override
    public String toString() {
        return JsonUtils.toString(this);
    }

}