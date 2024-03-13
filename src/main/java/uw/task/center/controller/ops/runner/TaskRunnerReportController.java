package uw.task.center.controller.ops.runner;

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
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;
import uw.dao.util.TableShardingUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 队列任务报表
 */
@RestController
@Tag(name = "队列任务报表")
@RequestMapping("/ops/runner/report")
@MscPermDeclare(type = UserType.OPS)
public class TaskRunnerReportController {

    private static final DaoFactory dao = DaoFactory.getInstance();


    /**
     * 分时段数据汇总报表，如果指定taskId，则显示该任务的报表，否则显示全部报表。
     *
     * @param startDate
     * @param endDate
     * @param dateType
     * @return
     */
    @Operation(summary = "分时段汇总报表", description = "分时段数据汇总报表，如果指定taskId，则显示该任务的报表，否则显示全部报表。")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.REQUEST)
    @GetMapping("/statsDateSummary")
    public DataList<RunnerStatsVo> statsDateSummary(@Parameter(description = "开始日期") @RequestParam(required = false) Date startDate,
                                                    @Parameter(description = "结束日期") @RequestParam(required = false) Date endDate,
                                                    @Parameter(description = "聚合类型。0自动1按日2按时3按分") @RequestParam(required = false, defaultValue = "0") int dateType,
                                                    @Parameter(description = "任务id") @RequestParam(required = false, defaultValue = "0") long taskId) throws TransactionException {
        //判断自动类型。
        if (startDate == null) {
            startDate = new Date( System.currentTimeMillis() - 86400_000L );
        }
        if (endDate == null) {
            endDate = new Date();
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
        String tableName = TableShardingUtils.getTableNameByDate( "task_runner_stats", startDate );
        List<Object> param = new ArrayList<>();
        String sql;
        // 1按日 2按时 3按10分
        switch (dateType) {
            case 1:
                sql = "SELECT LEFT(create_date,10) AS stats_date,";
                break;
            case 2:
                sql = "SELECT LEFT(create_date,13) AS stats_date,";
                break;
            case 3:
                sql = "SELECT LEFT(create_date,15) AS stats_date,";
                break;
            default:
                throw new IllegalArgumentException( "dateType is error :" + dateType );
        }
        sql += " sum(num_all) as num_all ,sum(num_fail_program) as num_fail_program ," + "sum(num_fail_config) as num_fail_config ,sum(num_fail_data) as num_fail_data,sum" +
                "(num_fail_partner) as num_fail_partner" + ",sum(time_wait_queue) as time_wait_queue,sum(time_wait_delay) as time_wait_delay,sum(time_run) as time_run FROM " + tableName;
        sql += " WHERE  create_date >= ? AND create_date <= ? ";
        param.add( startDate );
        param.add( endDate );
        if (taskId > 0) {
            sql += " AND task_id=?";
            param.add( taskId );
        }
        sql += " group by stats_date";
        sql += " ORDER BY stats_date ASC";
        DataList<RunnerStatsVo> runnerStatsList = dao.list( RunnerStatsVo.class, sql, param.toArray() );
        return runnerStatsList;
    }

    /**
     * 分任务的汇总数据，不分时。可以显示出任务名、运行目标、运行类等关键信息。
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Operation(summary = "任务汇总报表", description = "分任务的汇总数据，不分时。可以显示出任务名、运行目标、运行类等关键信息")
    @MscPermDeclare(type = UserType.OPS,auth = AuthType.PERM, log = ActionLog.REQUEST)
    @GetMapping("/taskStatsList")
    public DataList<RunnerStatsDetailVo> taskStatsList(@Parameter(description = "开始日期") @RequestParam(required = false) Date startDate,
                                                       @Parameter(description = "结束日期") @RequestParam(required = false) Date endDate, @Parameter(description = "聚合类型。0自动1按日2按时3" +
            "按分") @RequestParam(required = false, defaultValue = "0") int dateType) throws TransactionException {
        //判断自动类型。
        if (startDate == null) {
            startDate = new Date( System.currentTimeMillis() - 86400_000L );
        }
        if (endDate == null) {
            endDate = new Date();
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
        String tableName = TableShardingUtils.getTableNameByDate( "task_runner_stats", startDate );
        List<Object> param = new ArrayList<>();
        String sql = "select tcs.*,tcc.task_name,tcc.run_target,tcc.task_class, tcc.task_owner, tcc.task_tag, tcc.run_type, tcc.consumer_num, tcc.prefetch_num, tcc.queue_type";
        // 1按日 2按时 3按10分
        switch (dateType) {
            case 1:
                sql = sql + ", LEFT(create_date,10) AS stats_date";
                break;
            case 2:
                sql = sql + ", LEFT(create_date,13) AS stats_date";
                break;
            case 3:
                sql = sql + ", LEFT(create_date,15) AS stats_date";
                break;
            default:
                throw new IllegalArgumentException( "dateType is error :" + dateType );
        }
        sql += " from (SELECT task_id ,sum(num_all) as num_all ,sum(num_fail_program) as num_fail_program ,sum(num_fail_config) as num_fail_config ," + "sum(num_fail_data) as " +
                "num_fail_data,sum(num_fail_partner) as num_fail_partner, sum(time_wait_queue) as time_wait_queue,sum(time_wait_delay) as time_wait_delay,sum(time_run) as " +
                "time_run FROM " + tableName;
        sql += " WHERE create_date >= ? AND create_date <= ? ";
        sql += " group by task_id order by num_all desc) tcs left join task_runner_info tcc on tcs.task_id =tcc.id ";
        param.add( startDate );
        param.add( endDate );
        DataList<RunnerStatsDetailVo> runnerStatsDetailList = dao.list( RunnerStatsDetailVo.class, sql, param.toArray() );
        return runnerStatsDetailList;
    }


    /**
     * 定时任务统计详细vo。
     */
    @Schema(title = "队列任务统计信息")
    @TableMeta(tableName = "RunnerStatsDetailVo", tableType = "view")
    public static class RunnerStatsDetailVo extends RunnerStatsVo {

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
         * 任务所有人
         */
        @ColumnMeta(columnName = "task_owner", dataType = "String", dataSize = 500, nullable = true)
        @Schema(title = "任务所有人", description = "任务所有人")
        private String taskOwner;

        /**
         * 运行标签
         */
        @ColumnMeta(columnName = "task_tag", dataType = "String", dataSize = 100, nullable = true)
        @Schema(title = "运行标签", description = "运行标签")
        private String taskTag;


        /**
         * 消费者的数量
         */
        @ColumnMeta(columnName = "consumer_num", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "消费者的数量", description = "消费者的数量")
        private int consumerNum;

        /**
         * 队列预取数量
         */
        @ColumnMeta(columnName = "prefetch_num", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "队列预取数量", description = "队列预取数量")
        private int prefetchNum;

        /**
         * 消费者的数量
         */
        @ColumnMeta(columnName = "queue_type", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "队列类型", description = "队列类型")
        private int queueType;

        /**
         * 运行类型 : 1本地运行 3全局运行 5全局运行RPC返回结果 -1未知
         */
        @ColumnMeta(columnName = "run_type", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "运行类型 : 1本地运行 3全局运行 5全局运行RPC返回结果 -1未知", description = "运行类型 : 1本地运行 3全局运行 5全局运行RPC返回结果 -1未知")
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

        public String getTaskOwner() {
            return taskOwner;
        }

        public void setTaskOwner(String taskOwner) {
            this.taskOwner = taskOwner;
        }

        public String getTaskTag() {
            return taskTag;
        }

        public void setTaskTag(String taskTag) {
            this.taskTag = taskTag;
        }

        @Override
        public int getConsumerNum() {
            return consumerNum;
        }

        @Override
        public void setConsumerNum(int consumerNum) {
            this.consumerNum = consumerNum;
        }

        public int getPrefetchNum() {
            return prefetchNum;
        }

        public void setPrefetchNum(int prefetchNum) {
            this.prefetchNum = prefetchNum;
        }

        public int getQueueType() {
            return queueType;
        }

        public void setQueueType(int queueType) {
            this.queueType = queueType;
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
     * 定时任务统计vo
     */
    @Schema(title = "队列任务统计vo")
    @TableMeta(tableName = "RunnerStatsVo", tableType = "view")
    public static class RunnerStatsVo {

        /**
         * 全部执行计数
         */
        @ColumnMeta(columnName = "num_all", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "全部执行计数", description = "全部执行计数")
        private int numAll;

        /**
         * 程序失败计数
         */
        @ColumnMeta(columnName = "num_fail_program", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "程序失败计数", description = "程序失败计数")
        private int numFailProgram;

        /**
         * 配置失败计数
         */
        @ColumnMeta(columnName = "num_fail_config", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "配置失败计数", description = "配置失败计数")
        private int numFailConfig;

        /**
         * 数据失败计数
         */
        @ColumnMeta(columnName = "num_fail_data", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "数据失败计数", description = "数据失败计数")
        private int numFailData;

        /**
         * 对方失败计数
         */
        @ColumnMeta(columnName = "num_fail_partner", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "对方失败计数", description = "对方失败计数")
        private int numFailPartner;

        /**
         * 队列等待时间
         */
        @ColumnMeta(columnName = "time_wait_queue", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "队列等待时间", description = "队列等待时间")
        private int timeWaitQueue;

        /**
         * 超时等待时间
         */
        @ColumnMeta(columnName = "time_wait_delay", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "超时等待时间", description = "超时等待时间")
        private int timeWaitDelay;

        /**
         * 运行时间
         */
        @ColumnMeta(columnName = "time_run", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "运行时间", description = "运行时间")
        private int timeRun;

        /**
         * 队列长度
         */
        @ColumnMeta(columnName = "queue_size", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "队列长度", description = "队列长度")
        private int queueSize;

        /**
         * 消费者数量
         */
        @ColumnMeta(columnName = "consumer_num", dataType = "int", dataSize = 10, nullable = true)
        @Schema(title = "消费者数量", description = "消费者数量")
        private int consumerNum;

        /**
         * 统计时间
         */
        @ColumnMeta(columnName = "stats_date", dataType = "String", dataSize = 19, nullable = true)
        @Schema(title = "统计时间", description = "统计时间")
        private String statsDate;

        public int getNumAll() {
            return numAll;
        }

        public void setNumAll(int numAll) {
            this.numAll = numAll;
        }

        public int getNumFailProgram() {
            return numFailProgram;
        }

        public void setNumFailProgram(int numFailProgram) {
            this.numFailProgram = numFailProgram;
        }

        public int getNumFailConfig() {
            return numFailConfig;
        }

        public void setNumFailConfig(int numFailConfig) {
            this.numFailConfig = numFailConfig;
        }

        public int getNumFailData() {
            return numFailData;
        }

        public void setNumFailData(int numFailData) {
            this.numFailData = numFailData;
        }

        public int getNumFailPartner() {
            return numFailPartner;
        }

        public void setNumFailPartner(int numFailPartner) {
            this.numFailPartner = numFailPartner;
        }

        public int getTimeWaitQueue() {
            return timeWaitQueue;
        }

        public void setTimeWaitQueue(int timeWaitQueue) {
            this.timeWaitQueue = timeWaitQueue;
        }

        public int getTimeWaitDelay() {
            return timeWaitDelay;
        }

        public void setTimeWaitDelay(int timeWaitDelay) {
            this.timeWaitDelay = timeWaitDelay;
        }

        public int getTimeRun() {
            return timeRun;
        }

        public void setTimeRun(int timeRun) {
            this.timeRun = timeRun;
        }

        public int getQueueSize() {
            return queueSize;
        }

        public void setQueueSize(int queueSize) {
            this.queueSize = queueSize;
        }

        public int getConsumerNum() {
            return consumerNum;
        }

        public void setConsumerNum(int consumerNum) {
            this.consumerNum = consumerNum;
        }

        public String getStatsDate() {
            return statsDate;
        }

        public void setStatsDate(String statsDate) {
            this.statsDate = statsDate;
        }
    }
}
