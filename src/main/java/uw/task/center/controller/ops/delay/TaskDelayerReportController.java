package uw.task.center.controller.ops.delay;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.data.PageList;
import uw.common.response.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;
import uw.dao.util.ShardingTableUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 延迟任务统计报表接口（按日/时/分聚合 task_delayer_stats 分表）。
 *
 * @author axeon
 */
@RestController
@org.springframework.web.bind.annotation.RequestMapping("/ops/delayer/report")
@Tag(name = "延迟任务报表")
@MscPermDeclare(user = UserType.OPS)
public class TaskDelayerReportController {

    private final DaoManager dao = DaoManager.getInstance();

    /**
     * 按日期汇总延迟任务统计（单任务或全局，按日/时/分粒度）。
     *
     * <p>跨天分表查询：通过 {@link ShardingTableUtils#unionAllShards} 遍历 [startDate,endDate] 覆盖的
     * 所有 task_delayer_stats 按天分表，UNION ALL 拼接内层明细，外层再按 stats_date 分组聚合（sum），
     * 避免只按 startDate 推单一表名而漏掉 endDate 侧分表数据。</p>
     *
     * <p>时间分桶由 LEFT(create_date,N) 截取实现：dateType=1 取 LEFT(create_date,10) 按日（yyyy-MM-dd）、
     * dateType=2 取 LEFT(create_date,13) 按时（yyyy-MM-dd HH）、dateType=3 取 LEFT(create_date,16) 按分
     * （yyyy-MM-dd HH:mm）。dateType=0 时按区间跨度自动选择：≤12h 按分、≤24h 按时、否则按日。</p>
     *
     * @param startDate 开始时间（null 默认近 24h）
     * @param endDate   结束时间（null 默认当前）
     * @param dateType  1=按日 2=按时 3=按分（0 自动推断）
     * @param taskId    任务配置 id（>0 时仅汇总该任务）
     * @return 分时段统计列表（DelayStatsVo，按 stats_date 升序）
     */
    @GetMapping("/statsDateSummary")
    @Operation(summary = "按日期汇总延迟任务统计", description = "按日期汇总延迟任务统计")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<PageList<DelayStatsVo>> statsDateSummary(
            @Parameter(description = "开始时间") @RequestParam(required = false) Date startDate,
            @Parameter(description = "结束时间") @RequestParam(required = false) Date endDate,
            @Parameter(description = "日期类型 1日2时3分") @RequestParam(required = false, defaultValue = "0") int dateType,
            @Parameter(description = "任务id") @RequestParam(required = false, defaultValue = "0") long taskId) {
        if (startDate == null) {
            startDate = new Date(SystemClock.now() - 86400_000L);
        }
        if (endDate == null) {
            endDate = SystemClock.nowDate();
        }
        if (dateType == 0) {
            int hourDiff = (int) ((endDate.getTime() - startDate.getTime()) / 3600_000L);
            if (hourDiff <= 12) {
                dateType = 3;
            } else if (hourDiff <= 24) {
                dateType = 2;
            } else {
                dateType = 1;
            }
        }
        // 跨天分表：遍历区间所有分表 UNION ALL，外层聚合（避免单表只按 startDate 推表名而漏 endDate 侧数据）
        String leftExpr = switch (dateType) {
            case 1 -> "LEFT(create_date,10)";
            case 2 -> "LEFT(create_date,13)";
            case 3 -> "LEFT(create_date,16)";
            default -> throw new IllegalArgumentException("dateType is error :" + dateType);
        };
        String innerSelect = leftExpr + " AS stats_date, num_all, num_fail_program, num_fail_config, num_fail_data, num_fail_partner, time_wait_delay, time_run";
        List<Object> param = new ArrayList<>();
        String union = ShardingTableUtils.unionAllShards("task_delayer_stats", startDate, endDate, innerSelect, param,
                taskId > 0 ? "AND task_id=?" : null, taskId > 0 ? new Object[]{taskId} : null);
        String sql = "SELECT stats_date, sum(num_all) as num_all, sum(num_fail_program) as num_fail_program, sum(num_fail_config) as num_fail_config,"
                + " sum(num_fail_data) as num_fail_data, sum(num_fail_partner) as num_fail_partner,"
                + " sum(time_wait_delay) as time_wait_delay, sum(time_run) as time_run FROM (" + union + ") t"
                + " group by stats_date order by stats_date asc";
        return dao.list(DelayStatsVo.class, sql, param.toArray());
    }

    /**
     * 任务维度统计列表（按 num_all 倒序，LEFT JOIN task_delayer_info 带出任务名等）。
     *
     * <p>任务维度汇总：三层嵌套 SQL —— 内层 UNION ALL 跨天分表明细（task_delayer_stats 所有覆盖分表，
     * 由 {@link ShardingTableUtils#unionAllShards} 生成）、中层按 task_id GROUP BY 聚合、
     * 外层 LEFT JOIN task_delayer_info 带出任务名/执行类/运行目标等信息。</p>
     *
     * <p>任务维度按整个区间汇总，<b>不分时段（dateType 参数仅保留签名一致、实际忽略）</b>，
     * 故无需对 create_date 做 LEFT 截取。</p>
     *
     * @param startDate 开始时间（null 默认近 24h）
     * @param endDate   结束时间（null 默认当前）
     * @param dateType  忽略（任务维度不分时段，保留仅为接口签名一致）
     * @return 任务维度统计明细列表（DelayStatsDetailVo，按 num_all 倒序）
     */
    @GetMapping("/taskStatsList")
    @Operation(summary = "任务维度延迟任务统计列表", description = "任务维度延迟任务统计列表")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ResponseData<PageList<DelayStatsDetailVo>> taskStatsList(
            @Parameter(description = "开始时间") @RequestParam(required = false) Date startDate,
            @Parameter(description = "结束时间") @RequestParam(required = false) Date endDate,
            @Parameter(description = "日期类型 1日2时3分") @RequestParam(required = false, defaultValue = "0") int dateType) {
        if (startDate == null) {
            startDate = new Date(SystemClock.now() - 86400_000L);
        }
        if (endDate == null) {
            endDate = SystemClock.nowDate();
        }
        // 跨天分表：内层 UNION ALL 明细 → 中层 group by task_id 聚合 → 外层 LEFT JOIN 任务信息（避免单表漏 endDate 侧数据）
        String innerSelect = "task_id, num_all, num_fail_program, num_fail_config, num_fail_data, num_fail_partner, time_wait_delay, time_run";
        List<Object> param = new ArrayList<>();
        String union = ShardingTableUtils.unionAllShards("task_delayer_stats", startDate, endDate, innerSelect, param, null);
        String sql = "SELECT tcs.task_id, tcs.num_all, tcs.num_fail_program, tcs.num_fail_config, tcs.num_fail_data, tcs.num_fail_partner,"
                + " tcs.time_wait_delay, tcs.time_run, tcc.task_name, tcc.task_class, tcc.task_owner, tcc.task_tag, tcc.run_target, tcc.consumer_num"
                + " from (SELECT task_id, sum(num_all) as num_all, sum(num_fail_program) as num_fail_program, sum(num_fail_config) as num_fail_config,"
                + " sum(num_fail_data) as num_fail_data, sum(num_fail_partner) as num_fail_partner, sum(time_wait_delay) as time_wait_delay, sum(time_run) as time_run"
                + " FROM (" + union + ") raw group by task_id order by num_all desc) tcs"
                + " left join task_delayer_info tcc on tcs.task_id = tcc.id";
        return dao.list(DelayStatsDetailVo.class, sql, param.toArray());
    }

    /**
     * 延迟任务统计聚合 VO（按时间维度汇总）。
     */
    @TableMeta(tableName = "DelayStatsVo", tableType = "view")
    public static class DelayStatsVo implements java.io.Serializable {
        /** 统计时间分桶（LEFT(create_date,N) 截取值：按日/按时/按分）。 */
        @ColumnMeta(columnName = "stats_date", dataType = "String", dataSize = 20, nullable = true)
        private String statsDate;
        /** 全部执行计数。 */
        @ColumnMeta(columnName = "num_all", dataType = "long", dataSize = 19, nullable = true)
        private long numAll;
        /** 程序失败计数。 */
        @ColumnMeta(columnName = "num_fail_program", dataType = "long", dataSize = 19, nullable = true)
        private long numFailProgram;
        /** 配置失败计数。 */
        @ColumnMeta(columnName = "num_fail_config", dataType = "long", dataSize = 19, nullable = true)
        private long numFailConfig;
        /** 数据失败计数。 */
        @ColumnMeta(columnName = "num_fail_data", dataType = "long", dataSize = 19, nullable = true)
        private long numFailData;
        /** 对方失败计数。 */
        @ColumnMeta(columnName = "num_fail_partner", dataType = "long", dataSize = 19, nullable = true)
        private long numFailPartner;
        /** 延迟等待时间。 */
        @ColumnMeta(columnName = "time_wait_delay", dataType = "long", dataSize = 19, nullable = true)
        private long timeWaitDelay;
        /** 运行时间。 */
        @ColumnMeta(columnName = "time_run", dataType = "long", dataSize = 19, nullable = true)
        private long timeRun;

        public String getStatsDate() { return statsDate; }
        public void setStatsDate(String statsDate) { this.statsDate = statsDate; }
        public long getNumAll() { return numAll; }
        public void setNumAll(long numAll) { this.numAll = numAll; }
        public long getNumFailProgram() { return numFailProgram; }
        public void setNumFailProgram(long numFailProgram) { this.numFailProgram = numFailProgram; }
        public long getNumFailConfig() { return numFailConfig; }
        public void setNumFailConfig(long numFailConfig) { this.numFailConfig = numFailConfig; }
        public long getNumFailData() { return numFailData; }
        public void setNumFailData(long numFailData) { this.numFailData = numFailData; }
        public long getNumFailPartner() { return numFailPartner; }
        public void setNumFailPartner(long numFailPartner) { this.numFailPartner = numFailPartner; }
        public long getTimeWaitDelay() { return timeWaitDelay; }
        public void setTimeWaitDelay(long timeWaitDelay) { this.timeWaitDelay = timeWaitDelay; }
        public long getTimeRun() { return timeRun; }
        public void setTimeRun(long timeRun) { this.timeRun = timeRun; }
    }

    /**
     * 延迟任务统计明细 VO（带任务信息，用于 taskStatsList）。
     */
    @TableMeta(tableName = "DelayStatsDetailVo", tableType = "view")
    public static class DelayStatsDetailVo extends DelayStatsVo implements java.io.Serializable {
        /** 任务配置 id。 */
        @ColumnMeta(columnName = "task_id", dataType = "long", dataSize = 19, nullable = true)
        private long taskId;
        /** 任务名称。 */
        @ColumnMeta(columnName = "task_name", dataType = "String", dataSize = 200, nullable = true)
        private String taskName;
        /** 执行类信息。 */
        @ColumnMeta(columnName = "task_class", dataType = "String", dataSize = 200, nullable = true)
        private String taskClass;
        /** 任务所有人（JSON 联系人映射）。 */
        @ColumnMeta(columnName = "task_owner", dataType = "String", dataSize = 200, nullable = true)
        private String taskOwner;
        /** 运行标签（多实例区分维度）。 */
        @ColumnMeta(columnName = "task_tag", dataType = "String", dataSize = 100, nullable = true)
        private String taskTag;
        /** 运行目标。 */
        @ColumnMeta(columnName = "run_target", dataType = "String", dataSize = 100, nullable = true)
        private String runTarget;
        /** 消费者数量。 */
        @ColumnMeta(columnName = "consumer_num", dataType = "int", dataSize = 10, nullable = true)
        private int consumerNum;

        public long getTaskId() { return taskId; }
        public void setTaskId(long taskId) { this.taskId = taskId; }
        public String getTaskName() { return taskName; }
        public void setTaskName(String taskName) { this.taskName = taskName; }
        public String getTaskClass() { return taskClass; }
        public void setTaskClass(String taskClass) { this.taskClass = taskClass; }
        public String getTaskOwner() { return taskOwner; }
        public void setTaskOwner(String taskOwner) { this.taskOwner = taskOwner; }
        public String getTaskTag() { return taskTag; }
        public void setTaskTag(String taskTag) { this.taskTag = taskTag; }
        public String getRunTarget() { return runTarget; }
        public void setRunTarget(String runTarget) { this.runTarget = runTarget; }
        public int getConsumerNum() { return consumerNum; }
        public void setConsumerNum(int consumerNum) { this.consumerNum = consumerNum; }
    }
}
