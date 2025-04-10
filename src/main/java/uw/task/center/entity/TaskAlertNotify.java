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
    @Schema(title = "", description = "")
    private long id;

    /**
     * 报警信息ID
     */
    @ColumnMeta(columnName="info_id", dataType="long", dataSize=19, nullable=true)
    @Schema(title = "报警信息ID", description = "报警信息ID")
    private long infoId;

    /**
     * 联系人
     */
    @ColumnMeta(columnName="contact_man", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系人", description = "联系人")
    private String contactMan;

    /**
     * 联系人信息类型,mobile,qq,wx,email
     */
    @ColumnMeta(columnName="contact_type", dataType="String", dataSize=10, nullable=true)
    @Schema(title = "联系人信息类型,mobile,qq,wx,email", description = "联系人信息类型,mobile,qq,wx,email")
    private String contactType;

    /**
     * 联系人信息
     */
    @ColumnMeta(columnName="contact_info", dataType="String", dataSize=100, nullable=true)
    @Schema(title = "联系人信息", description = "联系人信息")
    private String contactInfo;

    /**
     * 创建时间
     */
    @ColumnMeta(columnName="create_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "创建时间", description = "创建时间")
    private java.util.Date createDate;

    /**
     * 发送时间
     */
    @ColumnMeta(columnName="sent_date", dataType="java.util.Date", dataSize=23, nullable=true)
    @Schema(title = "发送时间", description = "发送时间")
    private java.util.Date sentDate;

    /**
     * 发送次数
     */
    @ColumnMeta(columnName="sent_times", dataType="int", dataSize=10, nullable=true)
    @Schema(title = "发送次数", description = "发送次数")
    private int sentTimes;

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
        this.UPDATED_INFO = new StringBuilder("表task_alert_notify主键\"" + 
        this.id+ "\"更新为:\r\n");
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
        if (!Objects.equals(this.id, id)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("id");
            this.UPDATED_INFO.append("id:\"" + this.id+ "\"=>\"" + id + "\"\r\n");
            this.id = id;
        }
    }

    /**
     * 设置报警信息ID。
     */
    public void setInfoId(long infoId){
        if (!Objects.equals(this.infoId, infoId)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("info_id");
            this.UPDATED_INFO.append("info_id:\"" + this.infoId+ "\"=>\"" + infoId + "\"\r\n");
            this.infoId = infoId;
        }
    }

    /**
     * 设置联系人。
     */
    public void setContactMan(String contactMan){
        if (!Objects.equals(this.contactMan, contactMan)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_man");
            this.UPDATED_INFO.append("contact_man:\"" + this.contactMan+ "\"=>\"" + contactMan + "\"\r\n");
            this.contactMan = contactMan;
        }
    }

    /**
     * 设置联系人信息类型,mobile,qq,wx,email。
     */
    public void setContactType(String contactType){
        if (!Objects.equals(this.contactType, contactType)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_type");
            this.UPDATED_INFO.append("contact_type:\"" + this.contactType+ "\"=>\"" + contactType + "\"\r\n");
            this.contactType = contactType;
        }
    }

    /**
     * 设置联系人信息。
     */
    public void setContactInfo(String contactInfo){
        if (!Objects.equals(this.contactInfo, contactInfo)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("contact_info");
            this.UPDATED_INFO.append("contact_info:\"" + this.contactInfo+ "\"=>\"" + contactInfo + "\"\r\n");
            this.contactInfo = contactInfo;
        }
    }

    /**
     * 设置创建时间。
     */
    public void setCreateDate(java.util.Date createDate){
        if (!Objects.equals(this.createDate, createDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("create_date");
            this.UPDATED_INFO.append("create_date:\"" + this.createDate+ "\"=>\"" + createDate + "\"\r\n");
            this.createDate = createDate;
        }
    }

    /**
     * 设置发送时间。
     */
    public void setSentDate(java.util.Date sentDate){
        if (!Objects.equals(this.sentDate, sentDate)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("sent_date");
            this.UPDATED_INFO.append("sent_date:\"" + this.sentDate+ "\"=>\"" + sentDate + "\"\r\n");
            this.sentDate = sentDate;
        }
    }

    /**
     * 设置发送次数。
     */
    public void setSentTimes(int sentTimes){
        if (!Objects.equals(this.sentTimes, sentTimes)){
            if (this.UPDATED_COLUMN == null) {
                _INIT_UPDATE_INFO();
            }
            this.UPDATED_COLUMN.add("sent_times");
            this.UPDATED_INFO.append("sent_times:\"" + this.sentTimes+ "\"=>\"" + sentTimes + "\"\r\n");
            this.sentTimes = sentTimes;
        }
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
        sb.append("info_id:\"" + this.infoId + "\"\r\n");
        sb.append("contact_man:\"" + this.contactMan + "\"\r\n");
        sb.append("contact_type:\"" + this.contactType + "\"\r\n");
        sb.append("contact_info:\"" + this.contactInfo + "\"\r\n");
        sb.append("create_date:\"" + this.createDate + "\"\r\n");
        sb.append("sent_date:\"" + this.sentDate + "\"\r\n");
        sb.append("sent_times:\"" + this.sentTimes + "\"\r\n");
        sb.append("state:\"" + this.state + "\"\r\n");
        return sb.toString();
    }

}