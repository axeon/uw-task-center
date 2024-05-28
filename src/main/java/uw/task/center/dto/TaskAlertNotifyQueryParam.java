package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
* 报警信息通知列表查询参数。
*/
@Schema(title = "报警信息通知列表查询参数", description = "报警信息通知列表查询参数")
public class TaskAlertNotifyQueryParam extends PageQueryParam{


    /**
    * 
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="", description = "")
    private Long id;

    /**
    * 报警信息ID
    */
    @QueryMeta(expr = "info_id=?")
    @Schema(title="报警信息ID", description = "报警信息ID")
    private Long infoId;

    /**
    * 联系人
    */
    @QueryMeta(expr = "contact_man like ?")
    @Schema(title="联系人", description = "联系人")
    private String contactMan;

    /**
    * 联系人信息类型,mobile,qq,wx,email
    */
    @QueryMeta(expr = "contact_type like ?")
    @Schema(title="联系人信息类型,mobile,qq,wx,email", description = "联系人信息类型,mobile,qq,wx,email")
    private String contactType;

    /**
    * 联系人信息
    */
    @QueryMeta(expr = "contact_info like ?")
    @Schema(title="联系人信息", description = "联系人信息")
    private String contactInfo;
    /**
    * 创建时间范围
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

    /**
    * 发送时间范围
    */
    @QueryMeta(expr = "sent_date between ? and ?")
    @Schema(title="发送时间范围", description = "发送时间范围")
    private Date[] sentDateRange;


    /**
    * 发送次数
    */
    @QueryMeta(expr = "sent_times=?")
    @Schema(title="发送次数", description = "发送次数")
    private Integer sentTimes;

    /**
    * 发送次数范围
    */
    @QueryMeta(expr = "sent_times between ? and ?")
    @Schema(title="发送次数范围", description = "发送次数范围")
    private Integer[] sentTimesRange;
    /**
    * 状态
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态", description = "状态")
    private Integer state;

    /**
    * 正常状态
    */
    @QueryMeta(expr = "state>-1")
    @Schema(title="正常状态", description = "正常状态")
    private Boolean stateOn;

    /**
    * 状态数组
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="状态数组", description = "状态数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
    * 状态运算条件。
    * 可以使用运算符号。
    */
    @QueryMeta(expr = "state ?")
    @Schema(title="状态运算条件", description = "状态运算条件，可使用><=!比较运算符。")
    private String stateOp;



    /**
    * 获得。
    */
    public Long getId(){
        return this.id;
    }

    /**
    * 设置。
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获得报警信息ID。
    */
    public Long getInfoId(){
        return this.infoId;
    }

    /**
    * 设置报警信息ID。
    */
    public void setInfoId(Long infoId){
        this.infoId = infoId;
    }


    /**
    * 获得联系人。
    */
    public String getContactMan(){
        return this.contactMan;
    }

    /**
    * 设置联系人。
    */
    public void setContactMan(String contactMan){
        this.contactMan = contactMan;
    }


    /**
    * 获得联系人信息类型,mobile,qq,wx,email。
    */
    public String getContactType(){
        return this.contactType;
    }

    /**
    * 设置联系人信息类型,mobile,qq,wx,email。
    */
    public void setContactType(String contactType){
        this.contactType = contactType;
    }


    /**
    * 获得联系人信息。
    */
    public String getContactInfo(){
        return this.contactInfo;
    }

    /**
    * 设置联系人信息。
    */
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }


    /**
    * 获得创建时间范围。
    */
    public Date[] getCreateDateRange(){
        return this.createDateRange;
    }

    /**
    * 设置创建时间范围。
    */
    public void setCreateDateRange(Date[] createDateRange){
        this.createDateRange = createDateRange;
    }


    /**
    * 获得发送时间范围。
    */
    public Date[] getSentDateRange(){
        return this.sentDateRange;
    }

    /**
    * 设置发送时间范围。
    */
    public void setSentDateRange(Date[] sentDateRange){
        this.sentDateRange = sentDateRange;
    }


    /**
    * 获得发送次数。
    */
    public Integer getSentTimes(){
        return this.sentTimes;
    }

    /**
    * 设置发送次数。
    */
    public void setSentTimes(Integer sentTimes){
        this.sentTimes = sentTimes;
    }

    /**
    * 获得发送次数范围。
    */
    public Integer[] getSentTimesRange(){
        return this.sentTimesRange;
    }

    /**
    * 设置发送次数范围。
    */
    public void setSentTimesRange(Integer[] sentTimesRange){
        this.sentTimesRange = sentTimesRange;
    }

    /**
    * 获得状态。
    */
    public Integer getState(){
        return this.state;
    }

    /**
    * 设置状态。
    */
    public void setState(Integer state){
        this.state = state;
    }

    /**
    * 获得正常状态。
    */
    public Boolean getStateOn(){
        return this.stateOn;
    }

    /**
    * 设置正常状态。
    */
    public void setStateOn(Boolean stateOn){
        this.stateOn = stateOn;
    }

    /**
    * 获得状态数组。
    */
    public Integer[] getStates(){
        return this.states;
    }

    /**
    * 设置状态数组。
    */
    public void setStates(Integer[] states){
        this.states = states;
    }

    /**
    * 获得状态运算条件。
    */
    public String getStateOp(){
        return this.stateOp;
    }

    /**
    * 设置状态运算条件。
    */
    public void setStateOp(String stateOp){
        this.stateOp = stateOp;
    }

}