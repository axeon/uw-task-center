package uw.task.center.controller.ops.croner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.response.ResponseData;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.common.data.PageList;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;
import uw.dao.util.ShardingTableUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务报表接口。
 *
 * <p>基于分表统计数据（task_croner_stats）提供分时段汇总、分任务汇总两类报表，
 * 聚合粒度按时间跨度自动选择（按日/按时/按分）。</p>
 *
 * @author axeon
 */
@RestController
@RequestMapping("/ops/croner/report")
@Tag(name = "定时任务报表")
@MscPermDeclare(user = UserType.OPS)
public class TaskCronerReportController {

    private final DaoManager dao = DaoManager.getInstance();


    /**
     * 分时段数据汇总报表，如果指定taskId，则显示该任务的报表，否则显示全部报表。
     *
     * <p>跨天分表查询：通过 {@link ShardingTableUtils#unionAllShards} 遍历 [startDate,endDate] 覆盖的
     * 所有 task_croner_stats 按天分表，UNION ALL 拼接内层明细，外层再按 stats_date 分组聚合（sum），
     * 避免只按 startDate 推单一表名而漏掉 endDate 侧分表数据。</p>
     *
     * <p>时间分桶由 LEFT(create_date,N) 截取实现：dateType=1 取 LEFT(create_date,10) 按日（yyyy-MM-dd）、
     * dateType=2 取 LEFT(create_date,13) 按时（yyyy-MM-dd HH）、dateType=3 取 LEFT(create_date,16) 按分
     * （yyyy-MM-dd HH:mm）。dateType=0 时按区间跨度自动选择：≤12h 按分、≤24h 按时、否则按日。</p>
     *
     * @param startDate 开始日期（null 默认近 24h）
     * @param endDate   结束日期（null 默认当前）
     * @param dateType  聚合粒度：0 自动、1 按日、2 按时、3 按分
     * @param taskId    任务 id（>0 时仅汇总该任务，<=0 汇总全部）
     * @return 分时段统计列表（CronerStatsVo，按 stats_date 升序）
     */
    @GetMapping("/statsDateSummary")
    @Operation(summary = "分时段汇总报表", description = "分时段数据汇总报表，如果指定taskId，则显示该任务的报表，否则显示全部报表。")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM,log = ActionLog.REQUEST)
    public ResponseData<PageList<CronerStatsVo>> statsDateSummary(@Parameter(description = "开始日期") @RequestParam(required = false) Date startDate,
                                                                  @Parameter(description = "结束日期") @RequestParam(required = false) Date endDate,
                                                                  @Parameter(description = "聚合类型。0自动1按日2按时3按分") @RequestParam(required = false, defaultValue = "0") int dateType,
                                                                  @Parameter(description = "任务id") @RequestParam(required = false, defaultValue = "0") long taskId) {

        //判断自动类型。
        if (startDate == null) {
            startDate = new Date( SystemClock.now() - 86400_000L );
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
        String leftExpr = switch (dateType) {
            case 1 -> "LEFT(create_date,10)";
            case 2 -> "LEFT(create_date,13)";
            case 3 -> "LEFT(create_date,16)";
            default -> throw new IllegalArgumentException("dateType is error :" + dateType);
        };
        // 跨天分表 UNION ALL，外层聚合（避免单表只按 startDate 推表名而漏 endDate 侧数据）
        String innerSelect = leftExpr + " AS stats_date, num_all, num_fail_program, num_fail_config, num_fail_data, num_fail_partner, time_wait, time_run";
        List<Object> param = new ArrayList<>();
        String union = ShardingTableUtils.unionAllShards("task_croner_stats", startDate, endDate, innerSelect, param,
                taskId > 0 ? "AND task_id=?" : null, taskId > 0 ? new Object[]{taskId} : null);
        String sql = "SELECT stats_date, sum(num_all) as num_all, sum(num_fail_program) as num_fail_program,"
                + " sum(num_fail_config) as num_fail_config, sum(num_fail_data) as num_fail_data, sum(num_fail_partner) as num_fail_partner,"
                + " sum(time_wait) as time_wait, sum(time_run) as time_run"
                + " FROM (" + union + ") t group by stats_date ORDER BY stats_date ASC";
        return dao.list(CronerStatsVo.class, sql, param.toArray());
    }

    /**
     * 分任务的汇总数据，不分时。可以显示出任务名、运行目标、运行类等关键信息。
     *
     * <p>任务维度汇总：三层嵌套 SQL —— 内层 UNION ALL 跨天分表明细（task_croner_stats 所有覆盖分表，
     * 由 {@link ShardingTableUtils#unionAllShards} 生成）、中层按 task_id GROUP BY 聚合、
     * 外层 LEFT JOIN task_croner_info 带出任务名/cron 表达式/运行目标等信息。</p>
     *
     * <p>任务维度按整个区间汇总，<b>不分时段（dateType 参数仅保留签名一致、实际忽略）</b>，
     * 故无需对 create_date 做 LEFT 截取。</p>
     *
     * @param startDate 开始日期（null 默认近 24h）
     * @param endDate   结束日期（null 默认当前）
     * @param dateType  忽略（任务维度不分时段，保留仅为接口签名一致）
     * @return 任务维度统计明细列表（CronerStatsDetailVo，按 num_all 倒序）
     */
    @GetMapping("/taskStatsList")
    @Operation(summary = "任务汇总报表", description = "分任务的汇总数据，不分时。可以显示出任务名、运行目标、运行类等关键信息")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM,log = ActionLog.REQUEST)
    public ResponseData<PageList<CronerStatsDetailVo>> taskStatsList(@Parameter(description = "开始日期") @RequestParam(required = false) Date startDate,
                                                                     @Parameter(description = "结束日期") @RequestParam(required = false) Date endDate,
                                                                     @Parameter(description = "聚合类型。0自动1按日2按时3按分") @RequestParam(required = false, defaultValue = "0") int dateType) {
        //判断自动类型。
        if (startDate == null) {
            startDate = new Date( SystemClock.now() - 86400_000L );
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

        // 跨天分表 UNION ALL（内层明细→中层 group by task_id→外层 JOIN 任务信息）；任务维度汇总不分时段，dateType 忽略
        String innerSelect = "task_id, num_all, num_fail_program, num_fail_config, num_fail_data, num_fail_partner, time_wait, time_run";
        List<Object> param = new ArrayList<>();
        String union = ShardingTableUtils.unionAllShards("task_croner_stats", startDate, endDate, innerSelect, param, null);
        String sql = "SELECT tcs.task_id, tcs.num_all, tcs.num_fail_program, tcs.num_fail_config, tcs.num_fail_data, tcs.num_fail_partner,"
                + " tcs.time_wait, tcs.time_run, tcc.task_name, tcc.run_target, tcc.task_class, tcc.task_param, tcc.task_owner, tcc.task_cron, tcc.run_type"
                + " from (SELECT task_id, sum(num_all) as num_all, sum(num_fail_program) as num_fail_program, sum(num_fail_config) as num_fail_config,"
                + " sum(num_fail_data) as num_fail_data, sum(num_fail_partner) as num_fail_partner, sum(time_wait) as time_wait, sum(time_run) as time_run"
                + " FROM (" + union + ") raw group by task_id order by num_all desc) tcs left join task_croner_info tcc on tcs.task_id = tcc.id";
        return dao.list(CronerStatsDetailVo.class, sql, param.toArray());
    }

    /**
     * 定时任务统计详细 VO（任务维度，含任务名/cron 表达式/运行目标等信息，用于 taskStatsList）。
     */
    @Schema(title = "定时任务统计信息", description = "定时任务统计信息")
    @TableMeta(tableName = "CronerStatsDetailVo", tableType = "view")
    public static class CronerStatsDetailVo extends CronerStatsVo {


        /**
         * 任务配置id
         */
        @ColumnMeta(columnName = "task_id", dataType = "long", dataSize = 19, nullable = true)
        @Schema(title = "任务配置id", description = "任务配置id")
        private long taskId;

        /**
         * 任务名称
         */
        @ColumnMeta(columnName = "task_name", dataType = "String", dataSize = 200, nullable = true)
        @Schema(title = "任务名称", description = "任务名称")
        private String taskName;


        /**
         * 执行类信息
         */
        @ColumnMeta(columnName = "task_class", dataType = "String", dataSize = 200, nullable = true)
        @Schema(title = "执行类信息", description = "执行类信息")
        private String taskClass;

        /**
         * 任务参数
         */
        @ColumnMeta(columnName = "task_param", dataType = "String", dataSize = 100, nullable = true)
        @Schema(title = "任务参数", description = "任务参数")
        private String taskParam;

        /**
         * 任务所有人
         */
        @ColumnMeta(columnName = "task_owner", dataType = "String", dataSize = 500, nullable = true)
        @Schema(title = "任务所有人", description = "任务所有人")
        private String taskOwner;

        /**
         * cron表达式
         */
        @ColumnMeta(columnName = "task_cron", dataType = "String", dataSize = 100, nullable = true)
        @Schema(title = "cron表达式", description = "cron表达式")
        private String taskCron;

        /**
         * 运行类型
         */
        @ColumnMeta(columnName = "run_type", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "运行类型", description = "运行类型")
        private int runType;

        /**
         * 运行目标
         */
        @ColumnMeta(columnName = "run_target", dataType = "String", dataSize = 100, nullable = true)
        @Schema(title = "运行目标", description = "运行目标")
        private String runTarget;

        public long getTaskId() {
            return taskId;
        }

        public void setTaskId(long taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskClass() {
            return taskClass;
        }

        public void setTaskClass(String taskClass) {
            this.taskClass = taskClass;
        }

        public String getTaskParam() {
            return taskParam;
        }

        public void setTaskParam(String taskParam) {
            this.taskParam = taskParam;
        }

        public String getTaskOwner() {
            return taskOwner;
        }

        public void setTaskOwner(String taskOwner) {
            this.taskOwner = taskOwner;
        }

        public String getTaskCron() {
            return taskCron;
        }

        public void setTaskCron(String taskCron) {
            this.taskCron = taskCron;
        }

        public int getRunType() {
            return runType;
        }

        public void setRunType(int runType) {
            this.runType = runType;
        }

        public String getRunTarget() {
            return runTarget;
        }

        public void setRunTarget(String runTarget) {
            this.runTarget = runTarget;
        }
    }

    /**
     * 定时任务统计 VO（时间维度汇总，用于 statsDateSummary）。
     */
    @Schema(title = "定时任务统计vo")
    @TableMeta(tableName = "CronerStatsVo", tableType = "view")
    public static class CronerStatsVo {

        /**
         * 全部执行计数
         */
        @ColumnMeta(columnName = "num_all", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "全部执行计数", description = "全部执行计数")
        private long numAll;

        /**
         * 程序失败计数
         */
        @ColumnMeta(columnName = "num_fail_program", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "程序失败计数", description = "程序失败计数")
        private long numFailProgram;

        /**
         * 配置失败计数
         */
        @ColumnMeta(columnName = "num_fail_config", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "配置失败计数", description = "配置失败计数")
        private long numFailConfig;

        /**
         * 数据失败计数
         */
        @ColumnMeta(columnName = "num_fail_data", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "数据失败计数", description = "数据失败计数")
        private long numFailData;

        /**
         * 对方失败计数
         */
        @ColumnMeta(columnName = "num_fail_partner", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "对方失败计数", description = "对方失败计数")
        private long numFailPartner;

        /**
         * 超时等待
         */
        @ColumnMeta(columnName = "time_wait", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "超时等待", description = "超时等待")
        private long timeWait;

        /**
         * 运行时间
         */
        @ColumnMeta(columnName = "time_run", dataType = "long", dataSize = 10, nullable = true)
        @Schema(title = "运行时间", description = "运行时间")
        private long timeRun;

        /**
         * 统计时间
         */
        @ColumnMeta(columnName = "stats_date", dataType = "String", dataSize = 19, nullable = true)
        @Schema(title = "统计时间", description = "统计时间")
        private String statsDate;

        public long getNumAll() {
            return numAll;
        }

        public void setNumAll(long numAll) {
            this.numAll = numAll;
        }

        public long getNumFailProgram() {
            return numFailProgram;
        }

        public void setNumFailProgram(long numFailProgram) {
            this.numFailProgram = numFailProgram;
        }

        public long getNumFailConfig() {
            return numFailConfig;
        }

        public void setNumFailConfig(long numFailConfig) {
            this.numFailConfig = numFailConfig;
        }

        public long getNumFailData() {
            return numFailData;
        }

        public void setNumFailData(long numFailData) {
            this.numFailData = numFailData;
        }

        public long getNumFailPartner() {
            return numFailPartner;
        }

        public void setNumFailPartner(long numFailPartner) {
            this.numFailPartner = numFailPartner;
        }

        public long getTimeWait() {
            return timeWait;
        }

        public void setTimeWait(long timeWait) {
            this.timeWait = timeWait;
        }

        public long getTimeRun() {
            return timeRun;
        }

        public void setTimeRun(long timeRun) {
            this.timeRun = timeRun;
        }

        public String getStatsDate() {
            return statsDate;
        }

        public void setStatsDate(String statsDate) {
            this.statsDate = statsDate;
        }
    }
}
