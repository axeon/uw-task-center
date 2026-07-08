package uw.task.center.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import uw.common.dto.PageQueryParam;

import java.util.Date;
import java.util.Map;

/**
 * 延迟任务 ES 日志查询参数（uw.task.delay.log）。
 *
 * <p>极简版（核心过滤字段），照 TaskRunnerEsLogQueryParam 模式，建议 gencode 重生成完整版。</p>
 */
@Schema(title = "延迟任务日志查询参数", description = "延迟任务日志查询参数")
public class TaskDelayerEsLogQueryParam extends PageQueryParam {

    private static final Map<String, String> ALLOWED_SORT_PROPERTY = Map.of("@timestamp", "@timestamp");

    @Override
    public Map<String, String> ALLOWED_SORT_PROPERTY() {
        return ALLOWED_SORT_PROPERTY;
    }

    @uw.dao.annotation.QueryMeta(expr = "task_id=?")
    private Long taskId;

    @uw.dao.annotation.QueryMeta(expr = "task_class like ?")
    private String taskClass;

    @uw.dao.annotation.QueryMeta(expr = "task_tag like ?")
    private String taskTag;

    @uw.dao.annotation.QueryMeta(expr = "run_target like ?")
    private String runTarget;

    @uw.dao.annotation.QueryMeta(expr = "state=?")
    private Integer state;

    @uw.dao.annotation.QueryMeta(expr = "@timestamp between ? and ?")
    private Date[] timestampRange;

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
