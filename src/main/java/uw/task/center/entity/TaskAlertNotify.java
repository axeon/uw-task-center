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
 * TaskAlertNotify实体类
 * 报警信息通知
 *
 * @author axeon
 */
@TableMeta(tableName="task_alert_notify",tableType="table")
@Schema(title = "报警信息通知", description = "报警信息通知")
public class TaskAlertNotify implements DataEntity,Serializable{


    /**
     * 
     */
    @ColumnMeta(columnName="id", dataType="long", dataSize=19, nullable=false, primaryKey=true)
    @Schema(title = "", description = "", maxLength=19, nullable=false )
    private long id;

    /**
     * 报警信息ID
     */
    @ColumnMeta(columnName="info_id", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "报警信息ID", description = "报警信息ID", maxLength=19, nullable=true )
    private long infoId;

    /**
     * 联系人
     */
    @ColumnMeta(columnName="contact_man", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系人", description = "联系人", maxLength=100, nullable=true )
    private String contactMan;

    /**
     * 联系人信息类型,mobile,qq,wx,email
     */
    @ColumnMeta(columnName="contact_type", dataType="String", dataSize=10, nullable=true)
    @Schema(title = "联系人信息类型,mobile,qq,wx,email", description = "联系人信息类型,mobile,qq,wx,email", maxLength=10, nullable=true )
    private String contactType;

    /**
     * 联系人信息
     */
    @ColumnMeta(columnName="contact_info", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系人信息", description = "联系人信息", maxLength=100, nullable=true )
    private String contactInfo;

    /**
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间", maxLength=23, nullable=true )
    private java.util.Date createDate;

    /**
     * 发送时间
     */
    @ColumnMeta(columnName="sent_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "发送时间", description = "发送时间", maxLength=23, nullable=true )
    private java.util.Date sentDate;

    /**
     * 发送次数
     */
    @ColumnMeta(columnName="sent_times", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "发送次数", description = "发送次数", maxLength=10, nullable=true )
    private int sentTimes;

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
        return "task_alert_notify";
    }

    /**
     * 获得实体的表注释。
     */
    @Override
    public String ENTITY_NAME(){
        return "报警信息通知";
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
     * 获取。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获取报警信息ID。
     */
    public long getInfoId(){
        return this.infoId;
    }

    /**
     * 获取联系人。
     */
    public String getContactMan(){
        return this.contactMan;
    }

    /**
     * 获取联系人信息类型,mobile,qq,wx,email。
     */
    public String getContactType(){
        return this.contactType;
    }

    /**
     * 获取联系人信息。
     */
    public String getContactInfo(){
        return this.contactInfo;
    }

    /**
     * 获取创建时间。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获取发送时间。
     */
    public java.util.Date getSentDate(){
        return this.sentDate;
    }

    /**
     * 获取发送次数。
     */
    public int getSentTimes(){
        return this.sentTimes;
    }

    /**
     * 获取状态。
     */
    public int getState(){
        return this.state;
    }


    /**
     * 设置。
     */
    public void setId(long id){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "id", this.id, id, !_IS_LOADED );
        this.id = id;
    }

    /**
     *  设置链式调用。
     */
    public TaskAlertNotify id(long id){
        setId(id);
        return this;
    }

    /**
     * 设置报警信息ID。
     */
    public void setInfoId(long infoId){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "infoId", this.infoId, infoId, !_IS_LOADED );
        this.infoId = infoId;
    }

    /**
     *  设置报警信息ID链式调用。
     */
    public TaskAlertNotify infoId(long infoId){
        setInfoId(infoId);
        return this;
    }

    /**
     * 设置联系人。
     */
    public void setContactMan(String contactMan){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "contactMan", this.contactMan, contactMan, !_IS_LOADED );
        this.contactMan = contactMan;
    }

    /**
     *  设置联系人链式调用。
     */
    public TaskAlertNotify contactMan(String contactMan){
        setContactMan(contactMan);
        return this;
    }

    /**
     * 设置联系人信息类型,mobile,qq,wx,email。
     */
    public void setContactType(String contactType){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "contactType", this.contactType, contactType, !_IS_LOADED );
        this.contactType = contactType;
    }

    /**
     *  设置联系人信息类型,mobile,qq,wx,email链式调用。
     */
    public TaskAlertNotify contactType(String contactType){
        setContactType(contactType);
        return this;
    }

    /**
     * 设置联系人信息。
     */
    public void setContactInfo(String contactInfo){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "contactInfo", this.contactInfo, contactInfo, !_IS_LOADED );
        this.contactInfo = contactInfo;
    }

    /**
     *  设置联系人信息链式调用。
     */
    public TaskAlertNotify contactInfo(String contactInfo){
        setContactInfo(contactInfo);
        return this;
    }

    /**
     * 设置创建时间。
     */
    public void setCreateDate(java.util.Date createDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "createDate", this.createDate, createDate, !_IS_LOADED );
        this.createDate = createDate;
    }

    /**
     *  设置创建时间链式调用。
     */
    public TaskAlertNotify createDate(java.util.Date createDate){
        setCreateDate(createDate);
        return this;
    }

    /**
     * 设置发送时间。
     */
    public void setSentDate(java.util.Date sentDate){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "sentDate", this.sentDate, sentDate, !_IS_LOADED );
        this.sentDate = sentDate;
    }

    /**
     *  设置发送时间链式调用。
     */
    public TaskAlertNotify sentDate(java.util.Date sentDate){
        setSentDate(sentDate);
        return this;
    }

    /**
     * 设置发送次数。
     */
    public void setSentTimes(int sentTimes){
        _UPDATED_INFO = DataUpdateInfo.addUpdateInfo(_UPDATED_INFO, "sentTimes", this.sentTimes, sentTimes, !_IS_LOADED );
        this.sentTimes = sentTimes;
    }

    /**
     *  设置发送次数链式调用。
     */
    public TaskAlertNotify sentTimes(int sentTimes){
        setSentTimes(sentTimes);
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
    public TaskAlertNotify state(int state){
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