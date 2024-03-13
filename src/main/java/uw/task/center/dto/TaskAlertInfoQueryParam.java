package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;

/**
* 报警信息列表查询参数。
*/
@Schema(title = "报警信息列表查询参数", description = "报警信息列表查询参数")
public class TaskAlertInfoQueryParam extends PageQueryParam{


    /**
    * id
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;

    /**
    * 任务类型。1croner2runner
    */
    @QueryMeta(expr = "task_type like ?")
    @Schema(title="任务类型。1croner2runner", description = "任务类型。1croner2runner")
    private String taskType;

    /**
    * 任务配置ID
    */
    @QueryMeta(expr = "task_id=?")
    @Schema(title="任务配置ID", description = "任务配置ID")
    private Long taskId;

    /**
    * 报警标题
    */
    @QueryMeta(expr = "alert_title like ?")
    @Schema(title="报警标题", description = "报警标题")
    private String alertTitle;
    /**
    * 创建时间范围
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;

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
    * 获得任务类型。1croner2runner。
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
    * 获得任务配置ID。
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
    * 获得报警标题。
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