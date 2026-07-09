package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.dto.PageQueryParam;
import uw.dao.annotation.QueryMeta;

import java.util.Date;
import java.util.Map;

/**
 * 延迟任务 ES 日志查询参数（uw.task.delayer.log）。
 *
 * <p>极简版（核心过滤字段），照 TaskRunnerEsLogQueryParam 模式，建议 gencode 重生成完整版。</p>
 */
@Schema(title = "延迟任务日志查询参数", description = "延迟任务ES日志查询参数，用于 uw.task.delayer.log 索引检索。")
public class TaskDelayerEsLogQueryParam extends PageQueryParam {

    /**
     * 延迟任务配置id（对应 TaskDelayerConfig 的 id）。
     */
    @QueryMeta(expr = "task_id=?")
    @Schema(title = "延迟任务配置id", description = "延迟任务配置id（对应 TaskDelayerConfig 的 id）。")
    private Long taskId;

    /**
     * 执行的延迟任务类名（模糊匹配，全限定类名）。
     */
    @QueryMeta(expr = "task_class like ?")
    @Schema(title = "执行的类名", description = "执行的延迟任务类名（模糊匹配，全限定类名）。")
    private String taskClass;

    /**
     * 任务标签（模糊匹配）。
     */
    @QueryMeta(expr = "task_tag like ?")
    @Schema(title = "任务标签", description = "任务标签（模糊匹配）。")
    private String taskTag;

    /**
     * 运行目标（模糊匹配，指定执行主机/实例标识）。
     */
    @QueryMeta(expr = "run_target like ?")
    @Schema(title = "运行目标", description = "运行目标（模糊匹配，指定执行主机/实例标识）。")
    private String runTarget;

    /**
     * 执行状态（精确匹配）。
     */
    @QueryMeta(expr = "state=?")
    @Schema(title = "执行状态", description = "执行状态（精确匹配）。")
    private Integer state;

    /**
     * ES 日志时间戳（@timestamp）范围查询，长度为2的数组：[起始时间, 结束时间]。
     */
    @QueryMeta(expr = "@timestamp between ? and ?")
    @Schema(title = "日志时间戳范围", description = "ES 日志时间戳（@timestamp）范围查询，长度为2的数组：[起始时间, 结束时间]。")
    private Date[] timestampRange;

    private static final Map<String, String> ALLOWED_SORT_PROPERTY = Map.ofEntries(
            Map.entry("id", "id"),
            Map.entry("timestamp", "\"@timestamp\"")
    );

    /**
     * 允许的排序属性。
     *
     * @return 前端排序字段名到 ES 实际字段的映射
     */
    @Override
    public Map<String, String> ALLOWED_SORT_PROPERTY() {
        return ALLOWED_SORT_PROPERTY;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public String getRunTarget() {
        return runTarget;
    }

    public void setRunTarget(String runTarget) {
        this.runTarget = runTarget;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date[] getTimestampRange() {
        return timestampRange;
    }

    public void setTimestampRange(Date[] timestampRange) {
        this.timestampRange = timestampRange;
    }
}
