package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 报警信息列表查询参数。
*/
@Schema(title = "报警信息列表查询参数", description = "报警信息列表查询参数")
public class TaskAlertInfoQueryParam extends PageQueryParam{

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
            put( "taskType", "task_type" );
            put( "taskId", "task_id" );
            put( "alertTitle", "alert_title" );
            put( "createDate", "create_date" );
            put( "state", "state" );
        }};
    }

    /**
    * id。
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * ID数组。
    */
    @QueryMeta(expr = "id in (?)")
    @Schema(title="ID数组", description = "ID数组，可同时匹配多个。")
    private Long[] ids;

    /**
    * 任务类型。1croner2runner。
    */
    @QueryMeta(expr = "task_type like ?")
    @Schema(title="任务类型。1croner2runner", description = "任务类型。1croner2runner")
    private String taskType;
	
    /**
    * 任务配置ID。
    */
    @QueryMeta(expr = "task_id=?")
    @Schema(title="任务配置ID", description = "任务配置ID")
    private Long taskId;
	
    /**
    * 报警标题。
    */
    @QueryMeta(expr = "alert_title like ?")
    @Schema(title="报警标题", description = "报警标题")
    private String alertTitle;
	
    /**
    * 创建时间范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

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
    * 获取id。
    */
    public Long getId() {
        return this.id;
    }

    /**
    * 设置id。
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    * 设置id链式调用。
    */
    public TaskAlertInfoQueryParam id(Long id) {
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
    public TaskAlertInfoQueryParam ids(Long[] ids) {
        setIds(ids);
        return this;
    }

    /**
    * 获取任务类型。1croner2runner。
    */
    public String getTaskType(){
        return this.taskType;
    }

    /**
    * 设置任务类型。1croner2runner。
    */
    public void setTaskType(String taskType){
        this.taskType = taskType;
    }
	
    /**
    * 设置任务类型。1croner2runner链式调用。
    */
    public TaskAlertInfoQueryParam taskType(String taskType) {
        setTaskType(taskType);
        return this;
    }
	
    /**
    * 获取任务配置ID。
    */
    public Long getTaskId(){
        return this.taskId;
    }

    /**
    * 设置任务配置ID。
    */
    public void setTaskId(Long taskId){
        this.taskId = taskId;
    }
	
    /**
    * 设置任务配置ID链式调用。
    */
	public TaskAlertInfoQueryParam taskId(Long taskId){
        setTaskId(taskId);
        return this;
    }
	
    /**
    * 获取报警标题。
    */
    public String getAlertTitle(){
        return this.alertTitle;
    }

    /**
    * 设置报警标题。
    */
    public void setAlertTitle(String alertTitle){
        this.alertTitle = alertTitle;
    }
	
    /**
    * 设置报警标题链式调用。
    */
    public TaskAlertInfoQueryParam alertTitle(String alertTitle) {
        setAlertTitle(alertTitle);
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
    public TaskAlertInfoQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange(createDateRange);
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
    public TaskAlertInfoQueryParam state(Integer state) {
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
    public TaskAlertInfoQueryParam states(Integer[] states) {
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
    public TaskAlertInfoQueryParam stateGte(Integer stateGte) {
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
    public TaskAlertInfoQueryParam stateLte(Integer stateLte) {
        setStateLte(stateLte);
        return this;
    }
    

}