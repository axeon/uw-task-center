package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.dao.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* 定时任务统计信息列表查询参数。
*/
@Schema(title = "定时任务统计信息列表查询参数", description = "定时任务统计信息列表查询参数")
public class TaskCronerStatsQueryParam extends PageQueryParam{

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
            put( "taskId", "task_id" );
            put( "numAll", "num_all" );
            put( "numFailProgram", "num_fail_program" );
            put( "numFailConfig", "num_fail_config" );
            put( "numFailData", "num_fail_data" );
            put( "numFailPartner", "num_fail_partner" );
            put( "timeWait", "time_wait" );
            put( "timeRun", "time_run" );
            put( "createDate", "create_date" );
        }};
    }

    /**
    * id。
    */
    @QueryMeta(expr = "id=?")
    @Schema(title="id", description = "id")
    private Long id;
	
    /**
    * 任务配置id。
    */
    @QueryMeta(expr = "task_id=?")
    @Schema(title="任务配置id", description = "任务配置id")
    private Long taskId;
	
    /**
    * 全部执行计数。
    */
    @QueryMeta(expr = "num_all=?")
    @Schema(title="全部执行计数", description = "全部执行计数")
    private Integer numAll;

    /**
    * 全部执行计数范围。
    */
    @QueryMeta(expr = "num_all between ? and ?")
    @Schema(title="全部执行计数范围", description = "全部执行计数范围")
    private Integer[] numAllRange;
	
    /**
    * 程序失败计数。
    */
    @QueryMeta(expr = "num_fail_program=?")
    @Schema(title="程序失败计数", description = "程序失败计数")
    private Integer numFailProgram;

    /**
    * 程序失败计数范围。
    */
    @QueryMeta(expr = "num_fail_program between ? and ?")
    @Schema(title="程序失败计数范围", description = "程序失败计数范围")
    private Integer[] numFailProgramRange;
	
    /**
    * 配置失败计数。
    */
    @QueryMeta(expr = "num_fail_config=?")
    @Schema(title="配置失败计数", description = "配置失败计数")
    private Integer numFailConfig;

    /**
    * 配置失败计数范围。
    */
    @QueryMeta(expr = "num_fail_config between ? and ?")
    @Schema(title="配置失败计数范围", description = "配置失败计数范围")
    private Integer[] numFailConfigRange;
	
    /**
    * 数据失败计数。
    */
    @QueryMeta(expr = "num_fail_data=?")
    @Schema(title="数据失败计数", description = "数据失败计数")
    private Integer numFailData;

    /**
    * 数据失败计数范围。
    */
    @QueryMeta(expr = "num_fail_data between ? and ?")
    @Schema(title="数据失败计数范围", description = "数据失败计数范围")
    private Integer[] numFailDataRange;
	
    /**
    * 对方失败计数。
    */
    @QueryMeta(expr = "num_fail_partner=?")
    @Schema(title="对方失败计数", description = "对方失败计数")
    private Integer numFailPartner;

    /**
    * 对方失败计数范围。
    */
    @QueryMeta(expr = "num_fail_partner between ? and ?")
    @Schema(title="对方失败计数范围", description = "对方失败计数范围")
    private Integer[] numFailPartnerRange;
	
    /**
    * 超时等待。
    */
    @QueryMeta(expr = "time_wait=?")
    @Schema(title="超时等待", description = "超时等待")
    private Integer timeWait;

    /**
    * 超时等待范围。
    */
    @QueryMeta(expr = "time_wait between ? and ?")
    @Schema(title="超时等待范围", description = "超时等待范围")
    private Integer[] timeWaitRange;
	
    /**
    * 运行时间。
    */
    @QueryMeta(expr = "time_run=?")
    @Schema(title="运行时间", description = "运行时间")
    private Integer timeRun;

    /**
    * 运行时间范围。
    */
    @QueryMeta(expr = "time_run between ? and ?")
    @Schema(title="运行时间范围", description = "运行时间范围")
    private Integer[] timeRunRange;
	
    /**
    * 创建时间范围。
    */
    @QueryMeta(expr = "create_date between ? and ?")
    @Schema(title="创建时间范围", description = "创建时间范围")
    private Date[] createDateRange;


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
    * 设置id链式调用。
    */
	public TaskCronerStatsQueryParam id(Long id){
        setId(id);
        return this;
    }
	
    /**
    * 获取任务配置id。
    */
    public Long getTaskId(){
        return this.taskId;
    }

    /**
    * 设置任务配置id。
    */
    public void setTaskId(Long taskId){
        this.taskId = taskId;
    }
	
    /**
    * 设置任务配置id链式调用。
    */
	public TaskCronerStatsQueryParam taskId(Long taskId){
        setTaskId(taskId);
        return this;
    }
	
    /**
    * 获取全部执行计数。
    */
    public Integer getNumAll(){
        return this.numAll;
    }

    /**
    * 设置全部执行计数。
    */
    public void setNumAll(Integer numAll){
        this.numAll = numAll;
    }
	
    /**
    * 设置全部执行计数链式调用。
    */
    public TaskCronerStatsQueryParam numAll(Integer numAll){
        setNumAll(numAll);
        return this;
    }

    /**
    * 获取全部执行计数范围。
    */
    public Integer[] getNumAllRange(){
        return this.numAllRange;
    }

    /**
    * 设置全部执行计数范围。
    */
    public void setNumAllRange(Integer[] numAllRange){
        this.numAllRange = numAllRange;
    }
	
    /**
    * 设置全部执行计数范围链式调用。
    */
    public TaskCronerStatsQueryParam numAllRange(Integer[] numAllRange){
        setNumAllRange(numAllRange);
        return this;
    }
	
    /**
    * 获取程序失败计数。
    */
    public Integer getNumFailProgram(){
        return this.numFailProgram;
    }

    /**
    * 设置程序失败计数。
    */
    public void setNumFailProgram(Integer numFailProgram){
        this.numFailProgram = numFailProgram;
    }
	
    /**
    * 设置程序失败计数链式调用。
    */
    public TaskCronerStatsQueryParam numFailProgram(Integer numFailProgram){
        setNumFailProgram(numFailProgram);
        return this;
    }

    /**
    * 获取程序失败计数范围。
    */
    public Integer[] getNumFailProgramRange(){
        return this.numFailProgramRange;
    }

    /**
    * 设置程序失败计数范围。
    */
    public void setNumFailProgramRange(Integer[] numFailProgramRange){
        this.numFailProgramRange = numFailProgramRange;
    }
	
    /**
    * 设置程序失败计数范围链式调用。
    */
    public TaskCronerStatsQueryParam numFailProgramRange(Integer[] numFailProgramRange){
        setNumFailProgramRange(numFailProgramRange);
        return this;
    }
	
    /**
    * 获取配置失败计数。
    */
    public Integer getNumFailConfig(){
        return this.numFailConfig;
    }

    /**
    * 设置配置失败计数。
    */
    public void setNumFailConfig(Integer numFailConfig){
        this.numFailConfig = numFailConfig;
    }
	
    /**
    * 设置配置失败计数链式调用。
    */
    public TaskCronerStatsQueryParam numFailConfig(Integer numFailConfig){
        setNumFailConfig(numFailConfig);
        return this;
    }

    /**
    * 获取配置失败计数范围。
    */
    public Integer[] getNumFailConfigRange(){
        return this.numFailConfigRange;
    }

    /**
    * 设置配置失败计数范围。
    */
    public void setNumFailConfigRange(Integer[] numFailConfigRange){
        this.numFailConfigRange = numFailConfigRange;
    }
	
    /**
    * 设置配置失败计数范围链式调用。
    */
    public TaskCronerStatsQueryParam numFailConfigRange(Integer[] numFailConfigRange){
        setNumFailConfigRange(numFailConfigRange);
        return this;
    }
	
    /**
    * 获取数据失败计数。
    */
    public Integer getNumFailData(){
        return this.numFailData;
    }

    /**
    * 设置数据失败计数。
    */
    public void setNumFailData(Integer numFailData){
        this.numFailData = numFailData;
    }
	
    /**
    * 设置数据失败计数链式调用。
    */
    public TaskCronerStatsQueryParam numFailData(Integer numFailData){
        setNumFailData(numFailData);
        return this;
    }

    /**
    * 获取数据失败计数范围。
    */
    public Integer[] getNumFailDataRange(){
        return this.numFailDataRange;
    }

    /**
    * 设置数据失败计数范围。
    */
    public void setNumFailDataRange(Integer[] numFailDataRange){
        this.numFailDataRange = numFailDataRange;
    }
	
    /**
    * 设置数据失败计数范围链式调用。
    */
    public TaskCronerStatsQueryParam numFailDataRange(Integer[] numFailDataRange){
        setNumFailDataRange(numFailDataRange);
        return this;
    }
	
    /**
    * 获取对方失败计数。
    */
    public Integer getNumFailPartner(){
        return this.numFailPartner;
    }

    /**
    * 设置对方失败计数。
    */
    public void setNumFailPartner(Integer numFailPartner){
        this.numFailPartner = numFailPartner;
    }
	
    /**
    * 设置对方失败计数链式调用。
    */
    public TaskCronerStatsQueryParam numFailPartner(Integer numFailPartner){
        setNumFailPartner(numFailPartner);
        return this;
    }

    /**
    * 获取对方失败计数范围。
    */
    public Integer[] getNumFailPartnerRange(){
        return this.numFailPartnerRange;
    }

    /**
    * 设置对方失败计数范围。
    */
    public void setNumFailPartnerRange(Integer[] numFailPartnerRange){
        this.numFailPartnerRange = numFailPartnerRange;
    }
	
    /**
    * 设置对方失败计数范围链式调用。
    */
    public TaskCronerStatsQueryParam numFailPartnerRange(Integer[] numFailPartnerRange){
        setNumFailPartnerRange(numFailPartnerRange);
        return this;
    }
	
    /**
    * 获取超时等待。
    */
    public Integer getTimeWait(){
        return this.timeWait;
    }

    /**
    * 设置超时等待。
    */
    public void setTimeWait(Integer timeWait){
        this.timeWait = timeWait;
    }
	
    /**
    * 设置超时等待链式调用。
    */
    public TaskCronerStatsQueryParam timeWait(Integer timeWait){
        setTimeWait(timeWait);
        return this;
    }

    /**
    * 获取超时等待范围。
    */
    public Integer[] getTimeWaitRange(){
        return this.timeWaitRange;
    }

    /**
    * 设置超时等待范围。
    */
    public void setTimeWaitRange(Integer[] timeWaitRange){
        this.timeWaitRange = timeWaitRange;
    }
	
    /**
    * 设置超时等待范围链式调用。
    */
    public TaskCronerStatsQueryParam timeWaitRange(Integer[] timeWaitRange){
        setTimeWaitRange(timeWaitRange);
        return this;
    }
	
    /**
    * 获取运行时间。
    */
    public Integer getTimeRun(){
        return this.timeRun;
    }

    /**
    * 设置运行时间。
    */
    public void setTimeRun(Integer timeRun){
        this.timeRun = timeRun;
    }
	
    /**
    * 设置运行时间链式调用。
    */
    public TaskCronerStatsQueryParam timeRun(Integer timeRun){
        setTimeRun(timeRun);
        return this;
    }

    /**
    * 获取运行时间范围。
    */
    public Integer[] getTimeRunRange(){
        return this.timeRunRange;
    }

    /**
    * 设置运行时间范围。
    */
    public void setTimeRunRange(Integer[] timeRunRange){
        this.timeRunRange = timeRunRange;
    }
	
    /**
    * 设置运行时间范围链式调用。
    */
    public TaskCronerStatsQueryParam timeRunRange(Integer[] timeRunRange){
        setTimeRunRange(timeRunRange);
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
    public TaskCronerStatsQueryParam createDateRange(Date[] createDateRange) {
        setCreateDateRange(createDateRange);
        return this;
    }
	

}