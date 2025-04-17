package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.DataEntity;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;

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
     * 轻量级状态下更新列表list.
     */
    private transient Set<String> UPDATED_COLUMN = null;

    /**
     * 更新的信息.
     */
    private transient StringBuilder UPDATED_INFO = null;


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
     * 获取更改的字段列表.
     */
    @Override
    public Set<String> GET_UPDATED_COLUMN() {
        return UPDATED_COLUMN;
    }

    /**
     * 获取文本更新信息.
     */
    @Override
    public String GET_UPDATED_INFO() {
        if (this.UPDATED_INFO == null) {
            return null;
        } else {
            return this.UPDATED_INFO.toString();
        }
    }

    /**
     * 清除更新信息.
     */
    @Override
    public void CLEAR_UPDATED_INFO() {
        UPDATED_COLUMN = null;
        UPDATED_INFO = null;
    }

    /**
     * 初始化set相关的信息.
     */
    private void _INIT_UPDATE_INFO() {
        this.UPDATED_COLUMN = new HashSet<String>();
        this.UPDATED_INFO = new StringBuilder("表task_alert_contact主键\"" + 
        this.id+ "\"更新为:\r\n");
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
        if (!Objects.equals(this.id, id)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("id");
            this.UPDATED_INFO.append("id:\"").append(this.id).append("\"=>\"").append(id).append("\"\n");
            this.id = id;
        }
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
        if (!Objects.equals(this.contactType, contactType)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_type");
            this.UPDATED_INFO.append("contact_type:\"").append(this.contactType).append("\"=>\"").append(contactType).append("\"\n");
            this.contactType = contactType;
        }
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
        if (!Objects.equals(this.contactName, contactName)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_name");
            this.UPDATED_INFO.append("contact_name:\"").append(this.contactName).append("\"=>\"").append(contactName).append("\"\n");
            this.contactName = contactName;
        }
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
        if (!Objects.equals(this.mobile, mobile)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("mobile");
            this.UPDATED_INFO.append("mobile:\"").append(this.mobile).append("\"=>\"").append(mobile).append("\"\n");
            this.mobile = mobile;
        }
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
        if (!Objects.equals(this.email, email)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("email");
            this.UPDATED_INFO.append("email:\"").append(this.email).append("\"=>\"").append(email).append("\"\n");
            this.email = email;
        }
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
        if (!Objects.equals(this.wechat, wechat)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("wechat");
            this.UPDATED_INFO.append("wechat:\"").append(this.wechat).append("\"=>\"").append(wechat).append("\"\n");
            this.wechat = wechat;
        }
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
        if (!Objects.equals(this.im, im)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("im");
            this.UPDATED_INFO.append("im:\"").append(this.im).append("\"=>\"").append(im).append("\"\n");
            this.im = im;
        }
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
        if (!Objects.equals(this.notifyUrl, notifyUrl)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("notify_url");
            this.UPDATED_INFO.append("notify_url:\"").append(this.notifyUrl).append("\"=>\"").append(notifyUrl).append("\"\n");
            this.notifyUrl = notifyUrl;
        }
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
        if (!Objects.equals(this.remark, remark)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("remark");
            this.UPDATED_INFO.append("remark:\"").append(this.remark).append("\"=>\"").append(remark).append("\"\n");
            this.remark = remark;
        }
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
        if (!Objects.equals(this.createDate, createDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("create_date");
            this.UPDATED_INFO.append("create_date:\"").append(this.createDate).append("\"=>\"").append(createDate).append("\"\n");
            this.createDate = createDate;
        }
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
        if (!Objects.equals(this.modifyDate, modifyDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("modify_date");
            this.UPDATED_INFO.append("modify_date:\"").append(this.modifyDate).append("\"=>\"").append(modifyDate).append("\"\n");
            this.modifyDate = modifyDate;
        }
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
        if (!Objects.equals(this.state, state)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("state");
            this.UPDATED_INFO.append("state:\"").append(this.state).append("\"=>\"").append(state).append("\"\n");
            this.state = state;
        }
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
        StringBuilder sb = new StringBuilder();
        sb.append("id:\"" + this.id + "\"\r\n");
        sb.append("contact_type:\"" + this.contactType + "\"\r\n");
        sb.append("contact_name:\"" + this.contactName + "\"\r\n");
        sb.append("mobile:\"" + this.mobile + "\"\r\n");
        sb.append("email:\"" + this.email + "\"\r\n");
        sb.append("wechat:\"" + this.wechat + "\"\r\n");
        sb.append("im:\"" + this.im + "\"\r\n");
        sb.append("notify_url:\"" + this.notifyUrl + "\"\r\n");
        sb.append("remark:\"" + this.remark + "\"\r\n");
        sb.append("create_date:\"" + this.createDate + "\"\r\n");
        sb.append("modify_date:\"" + this.modifyDate + "\"\r\n");
        sb.append("state:\"" + this.state + "\"\r\n");
        return sb.toString();
    }

}