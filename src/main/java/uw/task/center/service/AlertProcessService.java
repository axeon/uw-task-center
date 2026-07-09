package uw.task.center.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jakarta.annotation.PreDestroy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uw.common.app.constant.CommonState;
import uw.common.data.PageList;
import uw.common.response.ResponseData;
import uw.common.util.JsonUtils;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.dao.TransactionException;
import uw.task.center.entity.*;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 任务告警处理服务。
 *
 * <p>接收任务执行主机上报的 runner/croner/delayer 统计数据，按任务配置的各类阈值（失败率、等待/运行超时、队列堆积等）
 * 判定是否触发告警，生成告警记录。处理异步提交到独立线程池（runner/croner/delayer 各一个），互不阻塞。
 * 同时周期性扫描定时任务是否按计划时间运行（cronerTimeOut 告警）。</p>
 *
 * @author axeon
 **/
@Component
@EnableScheduling
public class AlertProcessService {

    /**
     * 日志器。
     */
    private static final Logger log = LoggerFactory.getLogger(AlertProcessService.class);
    /**
     * 失败类型映射关系。
     */
    private static final Map<String, String> FAIL_TYPE_TRANSLATE_MAP = new HashMap<>();

    static {
        FAIL_TYPE_TRANSLATE_MAP.put("failRate", "总错误率");
        FAIL_TYPE_TRANSLATE_MAP.put("failPartnerRate", "接口错误率");
        FAIL_TYPE_TRANSLATE_MAP.put("failProgramRate", "程序错误率");
        FAIL_TYPE_TRANSLATE_MAP.put("failConfigRate", "配置错误率");
        FAIL_TYPE_TRANSLATE_MAP.put("failDataRate", "数据错误率");
        FAIL_TYPE_TRANSLATE_MAP.put("queueTimeout", "排队超时");
        FAIL_TYPE_TRANSLATE_MAP.put("waitTimeout", "限速超时");
        FAIL_TYPE_TRANSLATE_MAP.put("scheduleDelay", "调度延迟超时");
        FAIL_TYPE_TRANSLATE_MAP.put("runTimeout", "运行超时");
        FAIL_TYPE_TRANSLATE_MAP.put("queueSize", "队列长度超限");
        FAIL_TYPE_TRANSLATE_MAP.put("cronerTimeOut", "定时任务未在计划时间运行");
        FAIL_TYPE_TRANSLATE_MAP.put("delayOvertime", "延迟超时");
    }

    /**
     * runner锁。
     */
    private volatile Map<Long, TaskRunnerInfo> runnerMap = new ConcurrentHashMap<>();
    /**
     * croner缓存。
     */
    private volatile Map<Long, TaskCronerInfo> cronerMap = new ConcurrentHashMap<>();
    /**
     * delayer缓存。
     */
    private volatile Map<Long, TaskDelayerInfo> delayerMap = new ConcurrentHashMap<>();

    private final DaoManager dao = DaoManager.getInstance();
    /**
     * 日期格式化。
     */
    private final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    /**
     * 定时任务检查服务。
     */
    private final ExecutorService cronerProcessService = new ThreadPoolExecutor(1, 10, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("CronerProcessService-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 队列任务检查服务。
     */
    private final ExecutorService runnerProcessService = new ThreadPoolExecutor(1, 10, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("RunnerProcessService-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 延迟任务检查服务。
     */
    private final ExecutorService delayerProcessService = new ThreadPoolExecutor(1, 10, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("DelayerProcessService-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 进程关闭时优雅关闭告警处理线程池，尽量保证在途告警落库。
     */
    @PreDestroy
    public void shutdown() {
        shutdownPool(cronerProcessService, "CronerProcessService");
        shutdownPool(runnerProcessService, "RunnerProcessService");
        shutdownPool(delayerProcessService, "DelayerProcessService");
    }

    private void shutdownPool(ExecutorService pool, String name) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                log.warn("{} 仍有任务未完成，强制关闭", name);
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 处理队列任务统计信息。
     *
     * @param statsList 查询的结果
     */
    public void processRunnerStats(List<TaskRunnerStats> statsList) {
        runnerProcessService.submit(() -> {
            if (statsList != null && statsList.size() > 0) {
                for (TaskRunnerStats stats : statsList) {
                    TaskRunnerInfo config = getFitRunnerConfig(stats.getTaskId());
                    if (config != null) {
                        ArrayList<AlertData> alerts = new ArrayList<>();
                        // 注意：累计时间/计数用 long 持有，避免高 QPS 长期累计后 int 溢出。
                        long numAll = stats.getNumAll();
                        long numFailConfig = stats.getNumFailConfig();
                        long numFailProgram = stats.getNumFailProgram();
                        long numFailPartner = stats.getNumFailPartner();
                        long numFailData = stats.getNumFailData();
                        long numFail = (numFailConfig + numFailProgram + numFailPartner + numFailData);
                        long timeWaitDelay = stats.getTimeWaitDelay();
                        long timeWaitQueue = stats.getTimeWaitQueue();
                        long timeRun = stats.getTimeRun();
                        long queueSize = stats.getQueueSize();
                        DecimalFormat percentFormat = new DecimalFormat("#.##");
                        if (numFail > 0 && numAll > 0 && config.getAlertFailRate() > 0) {
                            double v = (double) numFail / numAll * 100;
                            if (v > config.getAlertFailRate()) {
                                alerts.add(new AlertData("failRate", percentFormat.format(config.getAlertFailRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFail + ")"));
                            }
                        }
                        if (numFailProgram > 0 && numAll > 0 && config.getAlertFailProgramRate() > 0) {
                            double v = (double) numFailProgram / numAll * 100;
                            if (v > config.getAlertFailProgramRate()) {
                                alerts.add(new AlertData("failProgramRate", percentFormat.format(config.getAlertFailProgramRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailProgram + ")"));
                            }
                        }
                        if (numFailPartner > 0 && numAll > 0 && config.getAlertFailPartnerRate() > 0) {
                            double v = (double) numFailPartner / numAll * 100;
                            if (v > config.getAlertFailPartnerRate()) {
                                alerts.add(new AlertData("failPartnerRate", percentFormat.format(config.getAlertFailPartnerRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailPartner + ")"));
                            }
                        }
                        if (numFailConfig > 0 && numAll > 0 && config.getAlertFailConfigRate() > 0) {
                            double v = (double) numFailConfig / numAll * 100;
                            if (v > config.getAlertFailConfigRate()) {
                                alerts.add(new AlertData("failConfigRate", percentFormat.format(config.getAlertFailConfigRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailConfig + ")"));
                            }
                        }
                        if (numFailData > 0 && numAll > 0 && config.getAlertFailDataRate() > 0) {
                            double v = (double) numFailData / numAll * 100;
                            if (v > config.getAlertFailDataRate()) {
                                alerts.add(new AlertData("failDataRate", percentFormat.format(config.getAlertFailDataRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailData + ")"));
                            }
                        }
                        if (timeWaitQueue > 0 && numAll > 0 && config.getAlertQueueTimeout() > 0) {
                            long averageTime = timeWaitQueue / numAll;
                            if (averageTime > config.getAlertQueueTimeout()) {
                                alerts.add(new AlertData("queueTimeout", config.getAlertQueueTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeWaitDelay > 0 && numAll > 0 && config.getAlertWaitTimeout() > 0) {
                            long averageTime = timeWaitDelay / numAll;
                            if (averageTime > config.getAlertWaitTimeout()) {
                                alerts.add(new AlertData("waitTimeout", config.getAlertWaitTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeRun > 0 && numAll > 0 && config.getAlertRunTimeout() > 0) {
                            long averageTime = timeRun / numAll;
                            if (averageTime > config.getAlertRunTimeout()) {
                                alerts.add(new AlertData("runTimeout", config.getAlertRunTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (queueSize > 0 && config.getAlertQueueOversize() > 0) {
                            if (queueSize > config.getAlertQueueOversize()) {
                                alerts.add(new AlertData("queueSize", config.getAlertQueueOversize() + "", queueSize + ""));
                            }
                        }
                        if (alerts.size() > 0) {
                            processAlertInfo("runner", config.getId(), config.getTaskName(), numAll, alerts, config.getTaskOwner(), config.getTaskLinkOur(),
                                    config.getTaskLinkMch());
                        }
                    }
                }
            }
        });
    }

    /**
     * 处理延迟任务统计信息。
     *
     * @param statsList 查询的结果
     */
    public void processDelayerStats(List<TaskDelayerStats> statsList) {
        delayerProcessService.submit(() -> {
            if (statsList != null && statsList.size() > 0) {
                for (TaskDelayerStats stats : statsList) {
                    TaskDelayerInfo config = getFitDelayerConfig(stats.getTaskId());
                    if (config != null) {
                        ArrayList<AlertData> alerts = new ArrayList<>();
                        long numAll = stats.getNumAll();
                        long numFailProgram = stats.getNumFailProgram();
                        long numFailPartner = stats.getNumFailPartner();
                        long numFailConfig = stats.getNumFailConfig();
                        long numFailData = stats.getNumFailData();
                        long numFail = numFailProgram + numFailPartner + numFailConfig + numFailData;
                        long timeWaitDelay = stats.getTimeWaitDelay();
                        long timeRun = stats.getTimeRun();
                        DecimalFormat percentFormat = new DecimalFormat("#.##");
                        if (numFail > 0 && numAll > 0 && config.getAlertFailRate() > 0) {
                            double v = (double) numFail / numAll * 100;
                            if (v > config.getAlertFailRate()) {
                                alerts.add(new AlertData("failRate", percentFormat.format(config.getAlertFailRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFail + ")"));
                            }
                        }
                        if (numFailProgram > 0 && numAll > 0 && config.getAlertFailProgramRate() > 0) {
                            double v = (double) numFailProgram / numAll * 100;
                            if (v > config.getAlertFailProgramRate()) {
                                alerts.add(new AlertData("failProgramRate", percentFormat.format(config.getAlertFailProgramRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailProgram + ")"));
                            }
                        }
                        if (numFailPartner > 0 && numAll > 0 && config.getAlertFailPartnerRate() > 0) {
                            double v = (double) numFailPartner / numAll * 100;
                            if (v > config.getAlertFailPartnerRate()) {
                                alerts.add(new AlertData("failPartnerRate", percentFormat.format(config.getAlertFailPartnerRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailPartner + ")"));
                            }
                        }
                        if (numFailConfig > 0 && numAll > 0 && config.getAlertFailConfigRate() > 0) {
                            double v = (double) numFailConfig / numAll * 100;
                            if (v > config.getAlertFailConfigRate()) {
                                alerts.add(new AlertData("failConfigRate", percentFormat.format(config.getAlertFailConfigRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailConfig + ")"));
                            }
                        }
                        if (numFailData > 0 && numAll > 0 && config.getAlertFailDataRate() > 0) {
                            double v = (double) numFailData / numAll * 100;
                            if (v > config.getAlertFailDataRate()) {
                                alerts.add(new AlertData("failDataRate", percentFormat.format(config.getAlertFailDataRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailData + ")"));
                            }
                        }
                        if (timeRun > 0 && numAll > 0 && config.getAlertRunTimeout() > 0) {
                            long averageTime = timeRun / numAll;
                            if (averageTime > config.getAlertRunTimeout()) {
                                alerts.add(new AlertData("runTimeout", config.getAlertRunTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeWaitDelay > 0 && numAll > 0 && config.getAlertWaitTimeout() > 0) {
                            long averageTime = timeWaitDelay / numAll;
                            if (averageTime > config.getAlertWaitTimeout()) {
                                alerts.add(new AlertData("delayOvertime", config.getAlertWaitTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (alerts.size() > 0) {
                            processAlertInfo("delayer", config.getId(), config.getTaskName(), numAll, alerts, config.getTaskOwner(), config.getTaskLinkOur(),
                                    config.getTaskLinkMch());
                        }
                    }
                }
            }
        });
    }

    /**
     * 处理定时任务的统计信息。
     *
     * @param statsList 查询的结果
     * @throws Exception
     */
    public void processCronerStats(List<TaskCronerStats> statsList) {
        cronerProcessService.submit(() -> {
            if (statsList != null && statsList.size() > 0) {
                for (TaskCronerStats stats : statsList) {
                    TaskCronerInfo config = getFitCronerConfig(stats.getTaskId());
                    if (config != null) {
                        ArrayList<AlertData> alerts = new ArrayList<>();
                        long numAll = stats.getNumAll();
                        long numFailConfig = stats.getNumFailConfig();
                        long numFailProgram = stats.getNumFailProgram();
                        long numFailPartner = stats.getNumFailPartner();
                        long numFailData = stats.getNumFailData();
                        long numFail = numFailConfig + numFailProgram + numFailPartner + numFailData;
                        long timeWait = stats.getTimeWait();
                        long timeRun = stats.getTimeRun();
                        DecimalFormat percentFormat = new DecimalFormat("#.##");
                        if (numFail > 0 && numAll > 0 && config.getAlertFailRate() > 0) {
                            double v = (double) numFail / numAll * 100;
                            if (v > config.getAlertFailRate()) {
                                alerts.add(new AlertData("failRate", percentFormat.format(config.getAlertFailRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFail + ")"));
                            }
                        }
                        if (numFailProgram > 0 && numAll > 0 && config.getAlertFailProgramRate() > 0) {
                            double v = (double) numFailProgram / numAll * 100;
                            if (v > config.getAlertFailProgramRate()) {
                                alerts.add(new AlertData("failProgramRate", percentFormat.format(config.getAlertFailProgramRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailProgram + ")"));
                            }
                        }
                        if (numFailPartner > 0 && numAll > 0 && config.getAlertFailPartnerRate() > 0) {
                            double v = (double) numFailPartner / numAll * 100;
                            if (v > config.getAlertFailPartnerRate()) {
                                alerts.add(new AlertData("failPartnerRate", percentFormat.format(config.getAlertFailPartnerRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailPartner + ")"));
                            }
                        }
                        if (numFailConfig > 0 && numAll > 0 && config.getAlertFailConfigRate() > 0) {
                            double v = (double) numFailConfig / numAll * 100;
                            if (v > config.getAlertFailConfigRate()) {
                                alerts.add(new AlertData("failConfigRate", percentFormat.format(config.getAlertFailConfigRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailConfig + ")"));
                            }
                        }
                        if (numFailData > 0 && numAll > 0 && config.getAlertFailDataRate() > 0) {
                            double v = (double) numFailData / numAll * 100;
                            if (v > config.getAlertFailDataRate()) {
                                alerts.add(new AlertData("failDataRate", percentFormat.format(config.getAlertFailDataRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailData + ")"));
                            }
                        }
                        if (timeWait > 0 && numAll > 0 && config.getAlertWaitTimeout() > 0) {
                            long averageTime = timeWait / numAll;
                            if (averageTime > config.getAlertWaitTimeout()) {
                                alerts.add(new AlertData("scheduleDelay", config.getAlertWaitTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeRun > 0 && numAll > 0 && config.getAlertRunTimeout() > 0) {
                            long averageTime = timeRun / numAll;
                            if (averageTime > config.getAlertRunTimeout()) {
                                alerts.add(new AlertData("runTimeout", config.getAlertRunTimeout() + "ms", averageTime + "ms"));
                            }
                        }

                        if (alerts.size() > 0) {
                            processAlertInfo("croner", config.getId(), config.getTaskName(), numAll, alerts, config.getTaskOwner(), config.getTaskLinkOur(),
                                    config.getTaskLinkMch());
                        }
                    }
                }
            }
        });
    }


    /**
     * 根据taskId筛选配置。
     *
     * @param taskId
     * @return
     */
    private TaskRunnerInfo getFitRunnerConfig(long taskId) {
        return runnerMap.get(taskId);
    }

    /**
     * 根据taskId筛选配置。
     *
     * @param taskId
     * @return
     */
    private TaskCronerInfo getFitCronerConfig(long taskId) {
        return cronerMap.get(taskId);
    }

    /**
     * 根据taskId筛选延迟任务配置。
     *
     * @param taskId
     * @return
     */
    private TaskDelayerInfo getFitDelayerConfig(long taskId) {
        return delayerMap.get(taskId);
    }

    /**
     * 初始化任务配置信息。
     * 定时执行此信息。
     */
    @Scheduled(fixedRate = 180000)
    private void initTaskConfig() {
        // 查询语句，查询条件
        String connerSql = "select * from task_croner_info where state=1";
        String runnerSql = "select * from task_runner_info where state=1";
        String delayerSql = "select * from task_delayer_info where state=1";
        Map<Long, TaskCronerInfo> cronerMap = new ConcurrentHashMap<>();
        Map<Long, TaskRunnerInfo> runnerMap = new ConcurrentHashMap<>();
        Map<Long, TaskDelayerInfo> delayerMap = new ConcurrentHashMap<>();
        dao.list(TaskRunnerInfo.class, runnerSql).onSuccess(list -> {
            for (TaskRunnerInfo runner : list) {
                runnerMap.put(runner.getId(), runner);
            }
            this.runnerMap = runnerMap;
        });
        dao.list(TaskDelayerInfo.class, delayerSql).onSuccess(list -> {
            for (TaskDelayerInfo delayer : list) {
                delayerMap.put(delayer.getId(), delayer);
            }
            this.delayerMap = delayerMap;
        });
        dao.list(TaskCronerInfo.class, connerSql).onSuccess(list -> {
            for (TaskCronerInfo croner : list) {
                cronerMap.put(croner.getId(), croner);
                // 没有执行过的，跳过
                if (croner.getStatsRunNum() == 0) {
                    continue;
                }
                // 没有规划下次执行时间的，跳过。
                if (croner.getNextRunDate() == null) {
                    continue;
                }
                // 如果超过约定时间还未执行，就要报警了。
                if ((croner.getNextRunDate().getTime() + (croner.getStatsRunTime() / croner.getStatsRunNum()) + 300_000L) < SystemClock.now()) {
                    // 先以 next_run_date 为条件推进为 NULL，仅当本实例抢到（影响行数>0）时才发告警，
                    // 避免多任务中心实例并发对同一个 croner 重复告警。
                    ResponseData<Integer> claim = dao.execute("update task_croner_info set next_run_date=NULL where id=? and next_run_date=?",
                            new Object[]{croner.getId(), croner.getNextRunDate()});
                    claim.onNotSuccess(d -> {
                        log.error("claim croner timeout update failed, id={}, code={}, msg={}", croner.getId(), claim.getCode(), claim.getMsg());
                    });
                    Integer claimed = claim.getData();
                    if (claimed != null && claimed > 0) {
                        ArrayList<AlertData> alertList = new ArrayList<>();
                        alertList.add(new AlertData("cronerTimeOut", dateFormat.format(croner.getNextRunDate()), dateFormat.format(SystemClock.nowDate())));
                        processAlertInfo("croner", croner.getId(), croner.getTaskName(), 0, alertList, croner.getTaskOwner(), croner.getTaskLinkOur(), croner.getTaskLinkMch());
                    }
                }
            }
            this.cronerMap = cronerMap;
        });
    }

    /**
     * 根据告警类型处理通知发送：保存告警信息，提取联系人，按 notifyUrl 等通道分发
     */
    private void processAlertInfo(String type, long taskId, String taskName, long runTimes, List<AlertData> alertList, String taskOwner, String taskLinkOur, String taskLinkMch) {
        // 检测报警通知范围。
        HashSet<String> links = new HashSet<>();
        links.add(taskOwner);
        for (AlertData ad : alertList) {
            if (ad.getColumn().contains("partner")) {
                // 此时必须通知商户和自己人。
                links.add(taskLinkOur);
                links.add(taskLinkMch);
                break;
            }
            if ("failRate".equals(ad.getColumn()) || "runTimeout".equals(ad.getColumn())) {
                // 此时必须通知自己人。
                links.add(taskLinkOur);
            }
        }
        List<TaskAlertContact> taskAlertContactList = getTaskAlertContactList(links.toArray(new String[0]));
        // 报警信息（落库失败则不生成通知，避免悬空外键）
        TaskAlertInfo info = saveAlertInfo(type, taskId, taskName, runTimes, alertList);
        if (info == null) {
            return;
        }
        //保存报警通知信息
        saveAlertNotify(taskAlertContactList, info);

    }

    /**
     * 获取联系方式列表。
     *
     * <p>link 内容形如 {@code {"<contactId>":"<contactName>"}}，其 key 来自 task_owner/task_link_*
     * 等 OPS 可写的 String 列。为防止二阶 SQL 注入，这里对 key 逐个做 Long 解析，
     * 只保留合法数字 id，并用参数占位符拼接，不把原始字符串直接拼进 SQL。</p>
     *
     * @param taskLink
     * @return
     */
    private List<TaskAlertContact> getTaskAlertContactList(String... taskLink) {
        LinkedHashSet<Long> idSet = new LinkedHashSet<>();
        for (String link : taskLink) {
            if (link == null || link.length() <= 2) {
                continue;
            }
            try {
                HashMap map = JsonUtils.parse(link, HashMap.class);
                for (Object key : map.keySet()) {
                    try {
                        idSet.add(Long.parseLong(key.toString()));
                    } catch (NumberFormatException e) {
                        // 非数字 key 直接丢弃，既防注入也容忍脏数据。
                    }
                }
            } catch (Exception e) {
                log.error("parse taskLink json failed, err={}", e.toString());
            }
        }
        if (idSet.isEmpty()) {
            return null;
        }
        // 用与 id 数量相同的占位符做参数化查询，杜绝 SQL 注入。
        String placeholders = idSet.stream().map(x -> "?").collect(Collectors.joining(","));
        ResponseData<PageList<TaskAlertContact>> result = dao.list(TaskAlertContact.class,
                "select * from task_alert_contact where id in (" + placeholders + ") and state=1", idSet.toArray());
        result.onNotSuccess(d -> {
            log.error("query task_alert_contact failed, ids={}, code={}, msg={}", idSet, result.getCode(), result.getMsg());
        });
        if (result.isNotSuccess()) {
            return null;
        }
        PageList<TaskAlertContact> page = result.getData();
        return page == null ? null : page.list();
    }

    /**
     * 保存报警通知信息。
     *
     * @param info        TaskAlertInfo
     * @param contactList 联系人
     */
    private void saveAlertNotify(List<TaskAlertContact> contactList, TaskAlertInfo info) {
        if (contactList == null) {
            return;
        }
        for (TaskAlertContact contact : contactList) {
            TaskAlertNotify notify = new TaskAlertNotify();
            notify.setInfoId(info.getId());
            // 联系人
            notify.setContactMan(contact.getContactName());
            // 设置通知基础信息（实际通过 notifyUrl 发送，email 通道已停用）
            notify.setCreateDate(SystemClock.nowDate());
            notify.setSentTimes(0);
            notify.setState(0);
//            //写入email通知
//            if (StringUtils.isNotBlank(contact.getEmail())) {
//                notify.setId(dao.getSequenceId(TaskAlertNotify.class));
//                notify.setContactType("email");
//                notify.setContactInfo(contact.getEmail());
//                ResponseData<?> emailResult = dao.save(notify);
//                if (emailResult.isNotSuccess()) {
//                    log.error("saveAlertNotify(email) failed, err={}", emailResult.toString());
//                }
//            }
            //写入notify通知
            if (StringUtils.isNotBlank(contact.getNotifyUrl())) {
                notify.setId(dao.getSequenceId(TaskAlertNotify.class));
                notify.setContactType("notifyUrl");
                notify.setContactInfo(contact.getNotifyUrl());
                ResponseData<TaskAlertNotify> notifyResult = dao.save(notify);
                notifyResult.onNotSuccess(d -> {
                    log.error("saveAlertNotify(notifyUrl) failed, infoId={}, contact={}, code={}, msg={}",
                            info.getId(), contact.getContactName(), notifyResult.getCode(), notifyResult.getMsg());
                });
            }

        }
    }


    /**
     * 保存报警信息。
     *
     * @param taskInfo 类名
     * @return
     * @throws TransactionException
     */
    private TaskAlertInfo saveAlertInfo(String type, long taskId, String taskInfo, long runTimes, List<AlertData> alertList) {
        TaskAlertInfo info = new TaskAlertInfo();
        info.setId(dao.getSequenceId(TaskAlertInfo.class));
        info.setTaskId(taskId);
        info.setTaskType(type);
        // 拼接告警通知信息
        StringBuilder title = new StringBuilder();
        title.append("#").append(info.getId()).append("报警");
        title.append("[").append(taskInfo).append("]");
        title.append(":");
        for (AlertData ad : alertList) {
            title.append(FAIL_TYPE_TRANSLATE_MAP.get(ad.getColumn())).append(",");
        }
        if (title.charAt(title.length() - 1) == ',') {
            title.deleteCharAt(title.length() - 1);
        }
        title.append("运行超限!");
        StringBuilder content = new StringBuilder();
        if (runTimes > 0) {
            content.append("[#").append(taskId).append(taskInfo).append("]最近1分钟内执行").append(runTimes).append("次：");
        } else {
            content.append("[#").append(taskId).append(taskInfo).append("]最近1分钟内执行异常：");
        }
        for (AlertData ad : alertList) {
            if ("cronerTimeOut".equals(ad.getColumn())) {
                content.append(FAIL_TYPE_TRANSLATE_MAP.get(ad.getColumn())).append(", 当前时间:").append(ad.getValue()).append(", 计划运行时间:").append(ad.getConfig()).append("; 已延误超5分钟！\n");
            } else {
                content.append(FAIL_TYPE_TRANSLATE_MAP.get(ad.getColumn())).append(", 运行值:").append(ad.getValue()).append("! 报警阀值:").append(ad.getConfig()).append(
                        "!\n");
            }
        }

        // 告警错误类型
        info.setAlertTitle(title.toString());
        info.setAlertBody(content.toString());
        info.setCreateDate(SystemClock.nowDate());
        info.setState(CommonState.ENABLED.getValue());
        ResponseData<TaskAlertInfo> saveResult = dao.save(info);
        // 落库失败则记录日志并不生成通知，避免悬空外键。
        saveResult.onNotSuccess(d -> {
            log.error("saveAlertInfo failed, taskId={}, taskType={}, code={}, msg={}", taskId, type, saveResult.getCode(), saveResult.getMsg());
        });
        return saveResult.isNotSuccess() ? null : info;
    }


    static class AlertData {

        /**
         * 字段
         */
        private String column;
        /**
         * 配置值
         */
        private String config;
        /**
         * 实际值
         */
        private String value;

        public AlertData(String column, String config, String value) {
            super();
            this.column = column;
            this.config = config;
            this.value = value;
        }

        /**
         * @return the column
         */
        public String getColumn() {
            return column;
        }

        /**
         * @param column the column to set
         */
        public void setColumn(String column) {
            this.column = column;
        }

        /**
         * @return the config
         */
        public String getConfig() {
            return config;
        }

        /**
         * @param config the config to set
         */
        public void setConfig(String config) {
            this.config = config;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

    }
}
