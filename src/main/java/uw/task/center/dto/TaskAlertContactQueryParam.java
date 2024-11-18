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
    * id。
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * 联系人类型。
    */
    @QueryMeta(expr = "contact_type=?")
    @Schema(title="联系人类型", description = "联系人类型")
    private Integer contactType;

    /**
    * 联系人。
    */
    @QueryMeta(expr = "contact_name like ?")
    @Schema(title="联系人", description = "联系人")
    private String contactName;

    /**
    * 联系电话。
    */
    @QueryMeta(expr = "mobile like ?")
    @Schema(title="联系电话", description = "联系电话")
    private String mobile;

    /**
    * 联系email。
    */
    @QueryMeta(expr = "email like ?")
    @Schema(title="联系email", description = "联系email")
    private String email;

    /**
    * 联系微信。
    */
    @QueryMeta(expr = "wechat like ?")
    @Schema(title="联系微信", description = "联系微信")
    private String wechat;

    /**
    * 备用im。
    */
    @QueryMeta(expr = "im like ?")
    @Schema(title="备用im", description = "备用im")
    private String im;

    /**
    * 通知链接，如钉钉，微信等。
    */
    @QueryMeta(expr = "notify_url like ?")
    @Schema(title="通知链接，如钉钉，微信等", description = "通知链接，如钉钉，微信等")
    private String notifyUrl;

    /**
    * 备注。
    */
    @QueryMeta(expr = "remark like ?")
    @Schema(title="备注", description = "备注")
    private String remark;
    /**
    * 创建日期范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建日期范围", description = "创建日期范围")
    private Date[] createDateRange;

    /**
    * 修改日期范围。
    */
    @QueryMeta(expr = "modify_date between ? and ?")
    @Schema(title="修改日期范围", description = "修改日期范围")
    private Date[] modifyDateRange;

    /**
    * 状态。
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态", description = "状态")
    private Integer state;

    /**
    * 数组状态。
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="数组状态", description = "状态数组，可同时匹配多个状态。")
    private Integer[] states;

    /**
    * 大于等于状态。
    */
    @QueryMeta(expr = "state>=?")
    @Schema(title="大于等于状态", description = "大于等于状态")
    private Integer stateGte;

    /**
    * 小于等于状态。
    */
    @QueryMeta(expr = "state<=?")
    @Schema(title="小于等于状态", description = "小于等于状态")
    private Integer stateLte;


    /**
    * 获取id。
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
    * 获取联系人类型。
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
    * 获取联系人。
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
    * 获取联系电话。
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
    * 获取联系email。
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
    * 获取联系微信。
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
    * 获取备用im。
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
    * 获取通知链接，如钉钉，微信等。
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
    * 获取备注。
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
    * 获取创建日期范围。
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
    * 获取修改日期范围。
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
    * 获取状态。
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
    * 获取数组状态。
    */
    public Integer[] getStates(){
        return this.states;
    }

    /**
    * 设置数组状态。
    */
    public void setStates(Integer[] states){
        this.states = states;
    }
    
    /**
    * 获取大于等于状态。
    */
    public Integer getStateGte(){
        return this.stateGte;
    }

    /**
    * 设置大于等于状态。
    */
    public void setStateGte(Integer stateGte){
        this.stateGte = stateGte;
    }
    
    /**
    * 获取小于等于状态。
    */
    public Integer getStateLte(){
        return this.stateLte;
    }

    /**
    * 获取小于等于状态。
    */
    public void setStateLte(Integer stateOn){
        this.stateLte = stateLte;
    }
    

}