package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 报警信息通知列表查询参数。
*/
@Schema(title = "报警信息通知列表查询参数", description = "报警信息通知列表查询参数")
public class TaskAlertNotifyQueryParam extends PageQueryParam{

    /**
     * 允许的排序属性。
     * key:排序名 value:排序字段
     *
     * @return
     */
    @Override
    public Map<String, String> ALLOWED_SORT_PROPERTY() {
        return new HashMap<>() {{
            put( "id", "id" );
            put( "infoId", "info_id" );
            put( "contactMan", "contact_man" );
            put( "contactType", "contact_type" );
            put( "contactInfo", "contact_info" );
            put( "createDate", "create_date" );
            put( "sentDate", "sent_date" );
            put( "sentTimes", "sent_times" );
            put( "state", "state" );
        }};
    }

    /**
    * 。
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="", description = "")
    private Long id;

    /**
    * ID数组。
    */
    @QueryMeta(expr = "id in (?)")
    @Schema(title="ID数组", description = "ID数组，可同时匹配多个。")
    private Long[] ids;

    /**
    * 报警信息ID。
    */
    @QueryMeta(expr = "info_id=?")
    @Schema(title="报警信息ID", description = "报警信息ID")
    private Long infoId;
	
    /**
    * 联系人。
    */
    @QueryMeta(expr = "contact_man like ?")
    @Schema(title="联系人", description = "联系人")
    private String contactMan;
	
    /**
    * 联系人信息类型,mobile,qq,wx,email。
    */
    @QueryMeta(expr = "contact_type like ?")
    @Schema(title="联系人信息类型,mobile,qq,wx,email", description = "联系人信息类型,mobile,qq,wx,email")
    private String contactType;
	
    /**
    * 联系人信息。
    */
    @QueryMeta(expr = "contact_info like ?")
    @Schema(title="联系人信息", description = "联系人信息")
    private String contactInfo;
	
    /**
    * 创建时间范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

    /**
    * 发送时间范围。
    */
    @QueryMeta(expr = "sent_date between ? and ?")
    @Schema(title="发送时间范围", description = "发送时间范围")
    private Date[] sentDateRange;

    /**
    * 发送次数。
    */
    @QueryMeta(expr = "sent_times=?")
    @Schema(title="发送次数", description = "发送次数")
    private Integer sentTimes;

    /**
    * 发送次数范围。
    */
    @QueryMeta(expr = "sent_times between ? and ?")
    @Schema(title="发送次数范围", description = "发送次数范围")
    private Integer[] sentTimesRange;
	
    /**
    * 状态。
    */
    @QueryMeta(expr = "state=?")
    @Schema(title="状态", description = "状态")
    private Integer state;

    /**
    * 状态数组。
    */
    @QueryMeta(expr = "state in (?)")
    @Schema(title="状态数组", description = "状态数组，可同时匹配多个状态。")
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
    * 获取。
    */
    public Long getId() {
        return this.id;
    }

    /**
    * 设置。
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * 设置链式调用。
    */
    public TaskAlertNotifyQueryParam id(Long id) {
        setId(id);
        return this;
    }

    /**
    * 获取ID数组。
    */
    public Long[] getIds() {
        return this.ids;
    }

    /**
    * 设置ID数组。
    */
    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    /**
    * 设置ID数组链式调用。
    */
    public TaskAlertNotifyQueryParam ids(Long[] ids) {
        setIds(ids);
        return this;
    }

    /**
    * 获取报警信息ID。
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
    * 设置报警信息ID链式调用。
    */
	public TaskAlertNotifyQueryParam infoId(Long infoId){
        setInfoId(infoId);
        return this;
    }
	
    /**
    * 获取联系人。
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
    * 设置联系人链式调用。
    */
    public TaskAlertNotifyQueryParam contactMan(String contactMan) {
        setContactMan(contactMan);
        return this;
    }
	
    /**
    * 获取联系人信息类型,mobile,qq,wx,email。
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
    * 设置联系人信息类型,mobile,qq,wx,email链式调用。
    */
    public TaskAlertNotifyQueryParam contactType(String contactType) {
        setContactType(contactType);
        return this;
    }
	
    /**
    * 获取联系人信息。
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
    * 设置联系人信息链式调用。
    */
    public TaskAlertNotifyQueryParam contactInfo(String contactInfo) {
        setContactInfo(contactInfo);
        return this;
    }
	
    /**
    * 获取创建时间范围。
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
    * 设置创建时间范围链式调用。
    */
    public TaskAlertNotifyQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange(createDateRange);
        return this;
    }
	
    /**
    * 获取发送时间范围。
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
    * 设置发送时间范围链式调用。
    */
    public TaskAlertNotifyQueryParam sentDateRange(Date[] sentDateRange) {
        setSentDateRange(sentDateRange);
        return this;
    }
	
    /**
    * 获取发送次数。
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
    * 设置发送次数链式调用。
    */
    public TaskAlertNotifyQueryParam sentTimes(Integer sentTimes){
        setSentTimes(sentTimes);
        return this;
    }

    /**
    * 获取发送次数范围。
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
    * 设置发送次数范围链式调用。
    */
    public TaskAlertNotifyQueryParam sentTimesRange(Integer[] sentTimesRange){
        setSentTimesRange(sentTimesRange);
        return this;
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
    * 设置状态链式调用。
    */
    public TaskAlertNotifyQueryParam state(Integer state) {
        setState(state);
        return this;
    }

    /**
    * 获取状态数组。
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
    * 设置状态数组链式调用。
    */
    public TaskAlertNotifyQueryParam states(Integer[] states) {
        setStates(states);
        return this;
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
    * 设置大于等于状态链式调用。
    */
    public TaskAlertNotifyQueryParam stateGte(Integer stateGte) {
        setStateGte(stateGte);
        return this;
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
    public void setStateLte(Integer stateLte){
        this.stateLte = stateLte;
    }
	
    /**
    * 获取小于等于状态链式调用。
    */
    public TaskAlertNotifyQueryParam stateLte(Integer stateLte) {
        setStateLte(stateLte);
        return this;
    }
    

}