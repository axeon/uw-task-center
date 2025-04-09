package uw.task.center.controller.ops.home;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.util.SystemClock;
import uw.dao.DaoFactory;
import uw.dao.TransactionException;
import uw.dao.annotation.ColumnMeta;
import uw.dao.annotation.TableMeta;
import uw.dao.util.ShardingTableUtils;
import uw.task.center.entity.TaskAlertInfo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "仪表盘")
@RequestMapping("/ops/home/dashboard")
@MscPermDeclare(user = UserType.OPS)
public class DashboardController {

    private final DaoFactory dao = DaoFactory.getInstance();

    /**
     * 任务统计。
     *
     * @return
     * @throws TransactionException
     */
    @GetMapping("/taskStats")
    @Operation(summary = "任务统计", description = "")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public TaskStatsVo taskStats() throws TransactionException {
        TaskStatsVo taskStatsVo = new TaskStatsVo();
        // 当前运行主机定义:last_update时间在当前系统时间一分钟内的都视为运行中的主机
        Integer taskHostStatusNum = dao.queryForSingleValue( Integer.class, "select count(1) from task_host_info where state=1 " );
        taskStatsVo.setTaskHostStatusNum( taskHostStatusNum );
        Integer taskCronerConfigNum = dao.queryForSingleValue( Integer.class, "select count(1) from task_croner_info where state = 1" );
        taskStatsVo.setTaskCronerConfigNum( taskCronerConfigNum );
        Integer taskRunnerConfigNum = dao.queryForSingleValue( Integer.class, "select count(1) from task_runner_info where state = 1" );
        taskStatsVo.setTaskRunnerConfigNum( taskRunnerConfigNum );
        return taskStatsVo;
    }

    /**
     * 最新报警日志。
     *
     * @return
     * @throws TransactionException
     */
    @GetMapping("/listNewAlert")
    @Operation(summary = "报警日志NEW", description = "")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public List<TaskAlertInfo> listNewAlert() throws TransactionException {
        // 获取七天之内的最新10条邮件信息记录回显
        return dao.list( TaskAlertInfo.class, "select * from task_alert_info order by id desc", 0, 10, false ).results();
    }

    /**
     * 首页折线图
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    @GetMapping("/taskReport")
    @Operation(summary = "任务折线图", description = "任务折线图")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.USER, log = ActionLog.NONE)
    public TaskReportVo taskReport(@Parameter(description = "开始日期") @RequestParam(required = false) Date startDate,
                                   @Parameter(description = "结束日期") @RequestParam(required = false) Date endDate,
                                   @Parameter(description = "聚合类型。0自动1按日2按时3按分") @RequestParam(required = false, defaultValue = "0") int dateType) throws TransactionException {
        //判断自动类型。
        if (startDate == null) {
            startDate = new Date( SystemClock.now() - 10 * 86400_000L );
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
        String runnerTableNameA = ShardingTableUtils.getTableNameByDate( "task_runner_stats", startDate );
        String cronerTableNameA = ShardingTableUtils.getTableNameByDate( "task_croner_stats", startDate );
        String runnerTableNameB = ShardingTableUtils.getTableNameByDate( "task_runner_stats", endDate );
        String cronerTableNameB = ShardingTableUtils.getTableNameByDate( "task_croner_stats", endDate );
        // 1按日 2按時 3按分 4按秒
        String selectSql;
        switch (dateType) {
            case 1:
                selectSql = "SELECT LEFT(create_date,10) AS stats_date,";
                break;
            case 2:
                selectSql = "SELECT LEFT(create_date,13) AS stats_date,";
                break;
            case 3:
                selectSql = "SELECT LEFT(create_date,16) AS stats_date,";
                break;
            default:
                throw new IllegalArgumentException( "dateType is error :" + dateType );
        }
        String cornerCommand =
                selectSql + " sum(num_all) as num_all, sum(num_fail_program + num_fail_config + num_fail_data + num_fail_partner) as num_fail FROM %s WHERE " +
                        " create_date >= ? AND create_date <= ? group by stats_date";
        String runnerCommand =
                selectSql + " sum(num_all) as num_all, sum(num_fail_program + num_fail_config + num_fail_data + num_fail_partner) as num_fail FROM %s WHERE " +
                        " create_date >= ? AND create_date <= ? group by stats_date";
        List<Object> param = new ArrayList<>( 2 );
        param.add( startDate );
        param.add( endDate );
        ArrayList<TaskReportDetail> cronerReportList = new ArrayList<>();
        ArrayList<TaskReportDetail> runnerReportList = new ArrayList<>();
        runnerReportList.addAll( dao.list( TaskReportDetail.class, String.format( runnerCommand, runnerTableNameA ), param.toArray(), 0, 0, false ).results() );
        cronerReportList.addAll( dao.list( TaskReportDetail.class, String.format( cornerCommand, cronerTableNameA ), param.toArray(), 0, 0, false ).results() );
        if (!StringUtils.equals( runnerTableNameA, runnerTableNameB )) {
            runnerReportList.addAll( dao.list( TaskReportDetail.class, String.format( runnerCommand, runnerTableNameB ), param.toArray(), 0, 0, false ).results() );
            cronerReportList.addAll( dao.list( TaskReportDetail.class, String.format( cornerCommand, cronerTableNameB ), param.toArray(), 0, 0, false ).results() );
        }

        TaskReportVo taskReportVo = new TaskReportVo();
        taskReportVo.setCronerReportDetail( cronerReportList );
        taskReportVo.setRunnerReportDetail( runnerReportList );
        return taskReportVo;
    }

    @Schema(title = "任务统计Vo", description = "任务统计Vo")
    public static class TaskStatsVo {

        @Schema(title = "任务主机数量", description = "任务主机数量")
        private int taskHostStatusNum;

        @Schema(title = "定时任务数量", description = "定时任务数量")
        private int taskCronerConfigNum;

        @Schema(title = "队列任务数量", description = "队列任务数量")
        private int taskRunnerConfigNum;

        public int getTaskHostStatusNum() {
            return taskHostStatusNum;
        }

        public void setTaskHostStatusNum(int taskHostStatusNum) {
            this.taskHostStatusNum = taskHostStatusNum;
        }

        public int getTaskCronerConfigNum() {
            return taskCronerConfigNum;
        }

        public void setTaskCronerConfigNum(int taskCronerConfigNum) {
            this.taskCronerConfigNum = taskCronerConfigNum;
        }

        public int getTaskRunnerConfigNum() {
            return taskRunnerConfigNum;
        }

        public void setTaskRunnerConfigNum(int taskRunnerConfigNum) {
            this.taskRunnerConfigNum = taskRunnerConfigNum;
        }
    }


    @Schema(title = "任务报表vo", description = "任务报表vo")
    public static class TaskReportVo {

        @Schema(title = "队列任务报表", description = "队列任务报表")
        private List<TaskReportDetail> runnerReportDetail;

        @Schema(title = "定时任务报表", description = "定时任务报表")
        private List<TaskReportDetail> cronerReportDetail;

        public List<TaskReportDetail> getRunnerReportDetail() {
            return runnerReportDetail;
        }

        public void setRunnerReportDetail(List<TaskReportDetail> runnerReportDetail) {
            this.runnerReportDetail = runnerReportDetail;
        }

        public List<TaskReportDetail> getCronerReportDetail() {
            return cronerReportDetail;
        }

        public void setCronerReportDetail(List<TaskReportDetail> cronerReportDetail) {
            this.cronerReportDetail = cronerReportDetail;
        }
    }

    /**
     * 任务报表明细
     */
    @Schema(title = "任务报表明细", description = "任务报表明细")
    @TableMeta(tableType = "view")
    public static class TaskReportDetail {
        /**
         * 全部数量
         */
        @Schema(title = "全部数量", description = "全部数量")
        @ColumnMeta(columnName = "num_all", dataType = "long", dataSize = 10)
        private long numAll;

        /**
         * 失败数量
         */
        @Schema(title = "失败数量", description = "失败数量")
        @ColumnMeta(columnName = "num_fail", dataType = "long", dataSize = 10)
        private long numFail;

        /**
         * 时间点
         */
        @Schema(title = "时间点", description = "时间点")
        @ColumnMeta(columnName = "stats_date", dataType = "String", dataSize = 19)
        private String statsDate;

        public long getNumAll() {
            return numAll;
        }

        public void setNumAll(long numAll) {
            this.numAll = numAll;
        }

        public long getNumFail() {
            return numFail;
        }

        public void setNumFail(long numFail) {
            this.numFail = numFail;
        }

        public String getStatsDate() {
            return statsDate;
        }

        public void setStatsDate(String statsDate) {
            this.statsDate = statsDate;
        }
    }

}
