package uw.task.center.entity;

import java.io.Serializable;
import java.util.HashSet;
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
    @Schema(title = "id", description = "id")
    private long id;

    /**
     * 联系人类型
     */
    @ColumnMeta(columnName="contact_type", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "联系人类型", description = "联系人类型")
    private int contactType;

    /**
     * 联系人
     */
    @ColumnMeta(columnName="contact_name", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系人", description = "联系人")
    private String contactName;

    /**
     * 联系电话
     */
    @ColumnMeta(columnName="mobile", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系电话", description = "联系电话")
    private String mobile;

    /**
     * 联系email
     */
    @ColumnMeta(columnName="email", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系email", description = "联系email")
    private String email;

    /**
     * 联系微信
     */
    @ColumnMeta(columnName="wechat", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系微信", description = "联系微信")
    private String wechat;

    /**
     * 备用im
     */
    @ColumnMeta(columnName="im", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "备用im", description = "备用im")
    private String im;

    /**
     * 通知链接，如钉钉，微信等
     */
    @ColumnMeta(columnName="notify_url", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "通知链接，如钉钉，微信等", description = "通知链接，如钉钉，微信等")
    private String notifyUrl;

    /**
     * 备注
     */
    @ColumnMeta(columnName="remark", dataType="String", dataSize=500, nullable=true)
    @Schema(title = "备注", description = "备注")
    private String remark;

    /**
     * 创建日期
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建日期", description = "创建日期")
    private java.util.Date createDate;

    /**
     * 修改日期
     */
    @ColumnMeta(columnName="modify_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "修改日期", description = "修改日期")
    private java.util.Date modifyDate;

    /**
     * 状态
     */
    @ColumnMeta(columnName="state", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "状态", description = "状态")
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
     * 获得更改的字段列表.
     */
    @Override
    public Set<String> GET_UPDATED_COLUMN() {
        return UPDATED_COLUMN;
    }

    /**
     * 获得文本更新信息.
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
     * 获得id。
     */
    public long getId(){
        return this.id;
    }

    /**
     * 获得联系人类型。
     */
    public int getContactType(){
        return this.contactType;
    }

    /**
     * 获得联系人。
     */
    public String getContactName(){
        return this.contactName;
    }

    /**
     * 获得联系电话。
     */
    public String getMobile(){
        return this.mobile;
    }

    /**
     * 获得联系email。
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * 获得联系微信。
     */
    public String getWechat(){
        return this.wechat;
    }

    /**
     * 获得备用im。
     */
    public String getIm(){
        return this.im;
    }

    /**
     * 获得通知链接，如钉钉，微信等。
     */
    public String getNotifyUrl(){
        return this.notifyUrl;
    }

    /**
     * 获得备注。
     */
    public String getRemark(){
        return this.remark;
    }

    /**
     * 获得创建日期。
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 获得修改日期。
     */
    public java.util.Date getModifyDate(){
        return this.modifyDate;
    }

    /**
     * 获得状态。
     */
    public int getState(){
        return this.state;
    }


    /**
     * 设置id。
     */
    public void setId(long id){
        if ((!String.valueOf(this.id).equals(String.valueOf(id)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("id");
            this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\"" + id + "\"\r\n");
            this.id = id;
        }
    }

    /**
     * 设置联系人类型。
     */
    public void setContactType(int contactType){
        if ((!String.valueOf(this.contactType).equals(String.valueOf(contactType)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_type");
            this.UPDATED_INFO.append("contact_type:\"" + this.contactType+ "\"=>\"" + contactType + "\"\r\n");
            this.contactType = contactType;
        }
    }

    /**
     * 设置联系人。
     */
    public void setContactName(String contactName){
        if ((!String.valueOf(this.contactName).equals(String.valueOf(contactName)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_name");
            this.UPDATED_INFO.append("contact_name:\"" + this.contactName+ "\"=>\"" + contactName + "\"\r\n");
            this.contactName = contactName;
        }
    }

    /**
     * 设置联系电话。
     */
    public void setMobile(String mobile){
        if ((!String.valueOf(this.mobile).equals(String.valueOf(mobile)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("mobile");
            this.UPDATED_INFO.append("mobile:\"" + this.mobile+ "\"=>\"" + mobile + "\"\r\n");
            this.mobile = mobile;
        }
    }

    /**
     * 设置联系email。
     */
    public void setEmail(String email){
        if ((!String.valueOf(this.email).equals(String.valueOf(email)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("email");
            this.UPDATED_INFO.append("email:\"" + this.email+ "\"=>\"" + email + "\"\r\n");
            this.email = email;
        }
    }

    /**
     * 设置联系微信。
     */
    public void setWechat(String wechat){
        if ((!String.valueOf(this.wechat).equals(String.valueOf(wechat)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("wechat");
            this.UPDATED_INFO.append("wechat:\"" + this.wechat+ "\"=>\"" + wechat + "\"\r\n");
            this.wechat = wechat;
        }
    }

    /**
     * 设置备用im。
     */
    public void setIm(String im){
        if ((!String.valueOf(this.im).equals(String.valueOf(im)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("im");
            this.UPDATED_INFO.append("im:\"" + this.im+ "\"=>\"" + im + "\"\r\n");
            this.im = im;
        }
    }

    /**
     * 设置通知链接，如钉钉，微信等。
     */
    public void setNotifyUrl(String notifyUrl){
        if ((!String.valueOf(this.notifyUrl).equals(String.valueOf(notifyUrl)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("notify_url");
            this.UPDATED_INFO.append("notify_url:\"" + this.notifyUrl+ "\"=>\"" + notifyUrl + "\"\r\n");
            this.notifyUrl = notifyUrl;
        }
    }

    /**
     * 设置备注。
     */
    public void setRemark(String remark){
        if ((!String.valueOf(this.remark).equals(String.valueOf(remark)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("remark");
            this.UPDATED_INFO.append("remark:\"" + this.remark+ "\"=>\"" + remark + "\"\r\n");
            this.remark = remark;
        }
    }

    /**
     * 设置创建日期。
     */
    public void setCreateDate(java.util.Date createDate){
        if ((!String.valueOf(this.createDate).equals(String.valueOf(createDate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("create_date");
            this.UPDATED_INFO.append("create_date:\"" + this.createDate+ "\"=>\"" + createDate + "\"\r\n");
            this.createDate = createDate;
        }
    }

    /**
     * 设置修改日期。
     */
    public void setModifyDate(java.util.Date modifyDate){
        if ((!String.valueOf(this.modifyDate).equals(String.valueOf(modifyDate)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("modify_date");
            this.UPDATED_INFO.append("modify_date:\"" + this.modifyDate+ "\"=>\"" + modifyDate + "\"\r\n");
            this.modifyDate = modifyDate;
        }
    }

    /**
     * 设置状态。
     */
    public void setState(int state){
        if ((!String.valueOf(this.state).equals(String.valueOf(state)))) {
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("state");
            this.UPDATED_INFO.append("state:\"" + this.state+ "\"=>\"" + state + "\"\r\n");
            this.state = state;
        }
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