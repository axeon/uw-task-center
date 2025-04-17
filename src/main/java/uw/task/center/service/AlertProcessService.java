package uw.task.center.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uw.common.app.constant.CommonState;
import uw.common.util.JsonUtils;
import uw.common.util.SystemClock;
import uw.dao.DaoManager;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.task.center.entity.*;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.StampedLock;

/**
 * 对比报警信息进行处理。
 **/
@Component
@EnableScheduling
public class AlertProcessService {

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
        FAIL_TYPE_TRANSLATE_MAP.put("runTimeout", "运行超时");
        FAIL_TYPE_TRANSLATE_MAP.put("queueSize", "队列长度超限");
        FAIL_TYPE_TRANSLATE_MAP.put("cronerTimeOut", "定时任务未在计划时间运行");
    }

    /**
     * runner锁。
     */
    private final StampedLock runnerMapLocker = new StampedLock();
    /**
     * runner锁。
     */
    private final StampedLock cronerMapLocker = new StampedLock();
    private final DaoManager dao = DaoManager.getInstance();
    /**
     * 日期格式化。
     */
    private final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    /**
     * 百分比格式化。
     */
    private final DecimalFormat percentFormat = new DecimalFormat("#.##");
    /**
     * 定时任务检查服务。
     */
    private final ExecutorService cronerProcessService = new ThreadPoolExecutor(1, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("CronerProcessService-%d").build());
    /**
     * 队列任务检查服务。
     */
    private final ExecutorService runnerProcessService = new ThreadPoolExecutor(1, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("RunnerProcessService-%d").build());
    /**
     * 队列任务缓存。
     */
    private Map<Long, TaskRunnerInfo> runnerMap = new ConcurrentHashMap<>();
    /**
     * 定时任务缓存。
     */
    private Map<Long, TaskCronerInfo> cronerMap = new ConcurrentHashMap<>();

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
                        int numAll = stats.getNumAll();
                        int numFailConfig = stats.getNumFailConfig();
                        int numFailProgram = stats.getNumFailProgram();
                        int numFailPartner = stats.getNumFailPartner();
                        int numFailData = stats.getNumFailData();
                        int numFail = (numFailConfig + numFailProgram + numFailPartner + numFailData);
                        int timeWaitDelay = stats.getTimeWaitDelay();
                        int timeWaitQueue = stats.getTimeWaitQueue();
                        int timeRun = stats.getTimeRun();
                        int queueSize = stats.getQueueSize();
                        // 没有值，直接返回
                        if (numFail > 0 && config.getAlertFailRate() > 0) {
                            double v = (double) numFail / numAll * 100;
                            if (v > config.getAlertFailRate()) {
                                alerts.add(new AlertData("failRate", percentFormat.format(config.getAlertFailRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFail + ")"));
                            }
                        }
                        if (numFailProgram > 0 && config.getAlertFailProgramRate() > 0) {
                            double v = (double) numFailProgram / numAll * 100;
                            if (v > config.getAlertFailProgramRate()) {
                                alerts.add(new AlertData("failProgramRate", percentFormat.format(config.getAlertFailProgramRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailProgram + ")"));
                            }
                        }
                        if (numFailPartner > 0 && config.getAlertFailPartnerRate() > 0) {
                            double v = (double) numFailPartner / numAll * 100;
                            if (v > config.getAlertFailPartnerRate()) {
                                alerts.add(new AlertData("failPartnerRate", percentFormat.format(config.getAlertFailPartnerRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailPartner + ")"));
                            }
                        }
                        if (numFailConfig > 0 && config.getAlertFailConfigRate() > 0) {
                            double v = (double) numFailConfig / numAll * 100;
                            if (v > config.getAlertFailConfigRate()) {
                                alerts.add(new AlertData("failConfigRate", percentFormat.format(config.getAlertFailConfigRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailConfig + ")"));
                            }
                        }
                        if (numFailData > 0 && config.getAlertFailDataRate() > 0) {
                            double v = (double) numFailData / numAll * 100;
                            if (v > config.getAlertFailDataRate()) {
                                alerts.add(new AlertData("failDataRate", percentFormat.format(config.getAlertFailDataRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailData + ")"));
                            }
                        }
                        if (timeWaitQueue > 0 && config.getAlertQueueTimeout() > 0) {
                            long averageTime = timeWaitQueue / numAll;
                            if (averageTime > config.getAlertQueueTimeout()) {
                                alerts.add(new AlertData("queueTimeout", config.getAlertQueueTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeWaitDelay > 0 && config.getAlertWaitTimeout() > 0) {
                            long averageTime = timeWaitDelay / numAll;
                            if (averageTime > config.getAlertWaitTimeout()) {
                                alerts.add(new AlertData("waitTimeout", config.getAlertWaitTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeRun > 0 && config.getAlertRunTimeout() > 0) {
                            long averageTime = timeRun / numAll;
                            if (averageTime > config.getAlertRunTimeout()) {
                                alerts.add(new AlertData("runTimeout", config.getAlertRunTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (queueSize > 0 && config.getAlertQueueOversize() > 0) {
                            if (queueSize > config.getAlertRunTimeout()) {
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
                        if (numFail > 0 && config.getAlertFailRate() > 0) {
                            double v = (double) numFail / numAll * 100;
                            if (v > config.getAlertFailRate()) {
                                alerts.add(new AlertData("failRate", percentFormat.format(config.getAlertFailRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFail + ")"));
                            }
                        }
                        if (numFailProgram > 0 && config.getAlertFailProgramRate() > 0) {
                            double v = (double) numFailProgram / numAll * 100;
                            if (v > config.getAlertFailProgramRate()) {
                                alerts.add(new AlertData("failProgramRate", percentFormat.format(config.getAlertFailProgramRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailProgram + ")"));
                            }
                        }
                        if (numFailPartner > 0 && config.getAlertFailPartnerRate() > 0) {
                            double v = (double) numFailPartner / numAll * 100;
                            if (v > config.getAlertFailPartnerRate()) {
                                alerts.add(new AlertData("failPartnerRate", percentFormat.format(config.getAlertFailPartnerRate()) + "%", percentFormat.format(v) + "%" +
                                        "(" + numFailPartner + ")"));
                            }
                        }
                        if (numFailData > 0 && config.getAlertFailDataRate() > 0) {
                            double v = (double) numFailData / numAll * 100;
                            if (v > config.getAlertFailDataRate()) {
                                alerts.add(new AlertData("failDataRate", percentFormat.format(config.getAlertFailDataRate()) + "%",
                                        percentFormat.format(v) + "%(" + numFailData + ")"));
                            }
                        }
                        if (timeWait > 0 && config.getAlertWaitTimeout() > 0) {
                            long averageTime = timeWait / numAll;
                            if (averageTime > config.getAlertWaitTimeout()) {
                                alerts.add(new AlertData("waitTimeout", config.getAlertWaitTimeout() + "ms", averageTime + "ms"));
                            }
                        }
                        if (timeRun > 0 && config.getAlertRunTimeout() > 0) {
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
        long stamp = runnerMapLocker.readLock();
        try {
            return runnerMap.get(taskId);
        } finally {
            runnerMapLocker.unlockRead(stamp);
        }
    }

    /**
     * 根据taskId筛选配置。
     *
     * @param taskId
     * @return
     */
    private TaskCronerInfo getFitCronerConfig(long taskId) {
        long stamp = cronerMapLocker.readLock();
        try {
            return cronerMap.get(taskId);
        } finally {
            cronerMapLocker.unlockRead(stamp);
        }
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
        Map<Long, TaskCronerInfo> cronerMap = new ConcurrentHashMap<>();
        Map<Long, TaskRunnerInfo> runnerMap = new ConcurrentHashMap<>();
        DataList<TaskCronerInfo> cronerList = null;
        DataList<TaskRunnerInfo> runnerList = null;
        dao.list(TaskRunnerInfo.class, runnerSql).onSuccess(list -> {
            for (TaskRunnerInfo runner : list) {
                runnerMap.put(runner.getId(), runner);
            }
            long stamp = runnerMapLocker.writeLock();
            try {
                this.runnerMap = runnerMap;
            } finally {
                runnerMapLocker.unlockWrite(stamp);
            }
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
                    ArrayList<AlertData> alertList = new ArrayList<>();
                    alertList.add(new AlertData("cronerTimeOut", dateFormat.format(croner.getNextRunDate()), dateFormat.format(new Date())));
                    processAlertInfo("croner", croner.getId(), croner.getTaskName(), 0, alertList, croner.getTaskOwner(), croner.getTaskLinkOur(), croner.getTaskLinkMch());
                    // 更新下次执行时间为NULL
                    dao.executeCommand("update task_croner_info set next_run_date=NULL where id=?", new Object[]{croner.getId()});
                }
            }
            long stamp = cronerMapLocker.writeLock();
            try {
                this.cronerMap = cronerMap;
            } finally {
                cronerMapLocker.unlockWrite(stamp);
            }
        });
    }

    /**
     * 根据错误类型对所发送的邮件进行处理，保存发送信息，提取联系人，取得发送类型
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
        // 报警信息
        TaskAlertInfo info = saveAlertInfo(type, taskId, taskName, runTimes, alertList);
        //保存报警通知信息
        saveAlertNotify(taskAlertContactList, info);

    }

    /**
     * 获取联系方式列表。
     *
     * @param taskLink
     * @return
     */
    private List<TaskAlertContact> getTaskAlertContactList(String... taskLink) {
        HashSet<String> set = new HashSet<>();
        for (String link : taskLink) {
            if (link.length() > 2) {
                try {
                    HashMap map = JsonUtils.parse(link, HashMap.class);
                    set.addAll(map.keySet());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        if (set.isEmpty()) {
            return null;
        }
        String ids = StringUtils.join(set, ',');
        return dao.list(TaskAlertContact.class, "select * from task_alert_contact where id in (" + ids + ") and state=1").getData().results();
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
            // 设置为email，因为这是邮件发送 获取根据里面的设置
            notify.setCreateDate(new Date());
            notify.setSentTimes(0);
            notify.setState(0);
            //写入email通知
            if (StringUtils.isNotBlank(contact.getEmail())) {
                notify.setId(dao.getSequenceId(TaskAlertNotify.class));
                notify.setContactType("email");
                notify.setContactInfo(contact.getEmail());
                dao.save(notify);
            }
            //写入notify通知
            if (StringUtils.isNotBlank(contact.getNotifyUrl())) {
                notify.setId(dao.getSequenceId(TaskAlertNotify.class));
                notify.setContactType("notifyUrl");
                notify.setContactInfo(contact.getNotifyUrl());
                dao.save(notify);
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
        // 拼接邮件信息
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

        // 邮件错误类型
        info.setAlertTitle(title.toString());
        info.setAlertBody(content.toString());
        info.setCreateDate(new Date());
        info.setState(CommonState.ENABLED.getValue());
        dao.save(info);
        return info;
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
