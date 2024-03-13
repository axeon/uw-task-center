package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
* 报警联系信息列表查询参数。
*/
@Schema(title = "报警联系信息列表查询参数", description = "报警联系信息列表查询参数")
public class TaskAlertContactQueryParam extends PageQueryParam{


    /**
    * id
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * 联系人类型
    */
    @QueryMeta(expr = "contact_type=?")
    @Schema(title="联系人类型", description = "联系人类型")
    private Integer contactType;

    /**
    * 联系人
    */
    @QueryMeta(expr = "contact_name like ?")
    @Schema(title="联系人", description = "联系人")
    private String contactName;

    /**
    * 联系电话
    */
    @QueryMeta(expr = "mobile like ?")
    @Schema(title="联系电话", description = "联系电话")
    private String mobile;

    /**
    * 联系email
    */
    @QueryMeta(expr = "email like ?")
    @Schema(title="联系email", description = "联系email")
    private String email;

    /**
    * 联系微信
    */
    @QueryMeta(expr = "wechat like ?")
    @Schema(title="联系微信", description = "联系微信")
    private String wechat;

    /**
    * 备用im
    */
    @QueryMeta(expr = "im like ?")
    @Schema(title="备用im", description = "备用im")
    private String im;

    /**
    * 通知链接，如钉钉，微信等
    */
    @QueryMeta(expr = "notify_url like ?")
    @Schema(title="通知链接，如钉钉，微信等", description = "通知链接，如钉钉，微信等")
    private String notifyUrl;

    /**
    * 备注
    */
    @QueryMeta(expr = "remark like ?")
    @Schema(title="备注", description = "备注")
    private String remark;
    /**
    * 创建日期范围
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建日期范围", description = "创建日期范围")
    private Date[] createDateRange;

    /**
    * 修改日期范围
    */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title="修改日期范围", description = "修改日期范围")
    private Date[] modifyDateRange;

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
    * 获得id。
    */
    public Long getId(){
        return this.id;
    }

    /**
    * 设置id。
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获得联系人类型。
    */
    public Integer getContactType(){
        return this.contactType;
    }

    /**
    * 设置联系人类型。
    */
    public void setContactType(Integer contactType){
        this.contactType = contactType;
    }


    /**
    * 获得联系人。
    */
    public String getContactName(){
        return this.contactName;
    }

    /**
    * 设置联系人。
    */
    public void setContactName(String contactName){
        this.contactName = contactName;
    }


    /**
    * 获得联系电话。
    */
    public String getMobile(){
        return this.mobile;
    }

    /**
    * 设置联系电话。
    */
    public void setMobile(String mobile){
        this.mobile = mobile;
    }


    /**
    * 获得联系email。
    */
    public String getEmail(){
        return this.email;
    }

    /**
    * 设置联系email。
    */
    public void setEmail(String email){
        this.email = email;
    }


    /**
    * 获得联系微信。
    */
    public String getWechat(){
        return this.wechat;
    }

    /**
    * 设置联系微信。
    */
    public void setWechat(String wechat){
        this.wechat = wechat;
    }


    /**
    * 获得备用im。
    */
    public String getIm(){
        return this.im;
    }

    /**
    * 设置备用im。
    */
    public void setIm(String im){
        this.im = im;
    }


    /**
    * 获得通知链接，如钉钉，微信等。
    */
    public String getNotifyUrl(){
        return this.notifyUrl;
    }

    /**
    * 设置通知链接，如钉钉，微信等。
    */
    public void setNotifyUrl(String notifyUrl){
        this.notifyUrl = notifyUrl;
    }


    /**
    * 获得备注。
    */
    public String getRemark(){
        return this.remark;
    }

    /**
    * 设置备注。
    */
    public void setRemark(String remark){
        this.remark = remark;
    }


    /**
    * 获得创建日期范围。
    */
    public Date[] getCreateDateRange(){
        return this.createDateRange;
    }

    /**
    * 设置创建日期范围。
    */
    public void setCreateDateRange(Date[] createDateRange){
        this.createDateRange = createDateRange;
    }


    /**
    * 获得修改日期范围。
    */
    public Date[] getModifyDateRange(){
        return this.modifyDateRange;
    }

    /**
    * 设置修改日期范围。
    */
    public void setModifyDateRange(Date[] modifyDateRange){
        this.modifyDateRange = modifyDateRange;
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