package uw.task.center.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uw.dao.DaoFactory;
import uw.dao.DataList;
import uw.dao.TransactionException;
import uw.httpclient.json.JsonInterfaceHelper;
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

    private static final Logger log = LoggerFactory.getLogger( AlertProcessService.class );
    /**
     * 失败类型映射关系。
     */
    private static final Map<String, String> FailTypeTranslateMap = new HashMap<>();

    static {
        FailTypeTranslateMap.put( "failRate", "总错误率" );//
        FailTypeTranslateMap.put( "failPartnerRate", "接口错误率" );//
        FailTypeTranslateMap.put( "failProgramRate", "程序错误率" );//
        FailTypeTranslateMap.put( "failConfigRate", "配置错误率" );//
        FailTypeTranslateMap.put( "failDataRate", "数据错误率" );//
        FailTypeTranslateMap.put( "queueTimeout", "排队超时" );//
        FailTypeTranslateMap.put( "waitTimeout", "限速超时" );//
        FailTypeTranslateMap.put( "runTimeout", "运行超时" );//
        FailTypeTranslateMap.put( "queueSize", "队列长度超限" );//
        FailTypeTranslateMap.put( "cronerTimeOut", "定时任务超时" );//
    }

    /**
     * runner锁。
     */
    private final StampedLock runnerMapLocker = new StampedLock();
    /**
     * runner锁。
     */
    private final StampedLock cronerMapLocker = new StampedLock();
    private final DaoFactory dao = DaoFactory.getInstance();
    /**
     * 日期格式化。
     */
    private final FastDateFormat dateFormat = FastDateFormat.getInstance( "yyyy-MM-dd HH:mm:ss" );
    /**
     * 百分比格式化。
     */
    private final DecimalFormat percentFormat = new DecimalFormat( "#.##" );
    /**
     * 队列任务缓存。
     */
    private Map<Long, TaskRunnerInfo> runnerMap = new ConcurrentHashMap<>();
    /**
     * 定时任务缓存。
     */
    private Map<Long, TaskCronerInfo> cronerMap = new ConcurrentHashMap<>();
    /**
     * 定时任务检查服务。
     */
    private final ExecutorService cronerProcessService = new ThreadPoolExecutor( 1, 20, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryBuilder().setDaemon( true ).setNameFormat( "CronerProcessService-%d" ).build() );
    /**
     * 队列任务检查服务。
     */
    private final ExecutorService runnerProcessService = new ThreadPoolExecutor( 1, 20, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryBuilder().setDaemon( true ).setNameFormat( "RunnerProcessService-%d" ).build() );

    /**
     * 处理队列任务统计信息。
     *
     * @param statsList 查询的结果
     */
    public void processRunnerStats(List<TaskRunnerStats> statsList) {
        runnerProcessService.submit( new Runnable() {
            @Override
            public void run() {
                if (statsList != null && statsList.size() > 0) {
                    for (TaskRunnerStats stats : statsList) {
                        TaskRunnerInfo config = getFitRunnerConfig( stats.getTaskId() );
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
                                    alerts.add( new AlertData( "failRate", percentFormat.format( config.getAlertFailRate() ) + "%", percentFormat.format( v ) + "%(" + numFail +
                                            ")" ) );
                                }
                            }
                            if (numFailProgram > 0 && config.getAlertFailProgramRate() > 0) {
                                double v = (double) numFailProgram / numAll * 100;
                                if (v > config.getAlertFailProgramRate()) {
                                    alerts.add( new AlertData( "failProgramRate", percentFormat.format( config.getAlertFailProgramRate() ) + "%", percentFormat.format( v ) + "%" +
                                            "(" + numFailProgram + ")" ) );
                                }
                            }
                            if (numFailPartner > 0 && config.getAlertFailPartnerRate() > 0) {
                                double v = (double) numFailPartner / numAll * 100;
                                if (v > config.getAlertFailPartnerRate()) {
                                    alerts.add( new AlertData( "failPartnerRate", percentFormat.format( config.getAlertFailPartnerRate() ) + "%", percentFormat.format( v ) + "%" +
                                            "(" + numFailPartner + ")" ) );
                                }
                            }
                            if (numFailConfig > 0 && config.getAlertFailConfigRate() > 0) {
                                double v = (double) numFailConfig / numAll * 100;
                                if (v > config.getAlertFailConfigRate()) {
                                    alerts.add( new AlertData( "failConfigRate", percentFormat.format( config.getAlertFailConfigRate() ) + "%",
                                            percentFormat.format( v ) + "%(" + numFailConfig + ")" ) );
                                }
                            }
                            if (numFailData > 0 && config.getAlertFailDataRate() > 0) {
                                double v = (double) numFailData / numAll * 100;
                                if (v > config.getAlertFailDataRate()) {
                                    alerts.add( new AlertData( "failDataRate", percentFormat.format( config.getAlertFailDataRate() ) + "%",
                                            percentFormat.format( v ) + "%(" + numFailData + ")" ) );
                                }
                            }
                            if (timeWaitQueue > 0 && config.getAlertQueueTimeout() > 0) {
                                long averageTime = timeWaitQueue / numAll;
                                if (averageTime > config.getAlertQueueTimeout()) {
                                    alerts.add( new AlertData( "queueTimeout", config.getAlertQueueTimeout() + "ms", averageTime + "ms" ) );
                                }
                            }
                            if (timeWaitDelay > 0 && config.getAlertWaitTimeout() > 0) {
                                long averageTime = timeWaitDelay / numAll;
                                if (averageTime > config.getAlertWaitTimeout()) {
                                    alerts.add( new AlertData( "waitTimeout", config.getAlertWaitTimeout() + "ms", averageTime + "ms" ) );
                                }
                            }
                            if (timeRun > 0 && config.getAlertRunTimeout() > 0) {
                                long averageTime = timeRun / numAll;
                                if (averageTime > config.getAlertRunTimeout()) {
                                    alerts.add( new AlertData( "runTimeout", config.getAlertRunTimeout() + "ms", averageTime + "ms" ) );
                                }
                            }
                            if (queueSize > 0 && config.getAlertQueueOversize() > 0) {
                                if (queueSize > config.getAlertRunTimeout()) {
                                    alerts.add( new AlertData( "queueSize", config.getAlertQueueOversize() + "", queueSize + "" ) );
                                }
                            }
                            if (alerts.size() > 0) {
                                processAlertInfo( "runner", config.getId(), config.getTaskName(), numAll, alerts, config.getTaskOwner(), config.getTaskLinkOur(),
                                        config.getTaskLinkMch() );
                            }
                        }
                    }
                }
            }
        } );
    }

    /**
     * 处理定时任务的统计信息。
     *
     * @param statsList 查询的结果
     * @throws Exception
     */
    public void processCronerStats(List<TaskCronerStats> statsList) {
        cronerProcessService.submit( new Runnable() {
            @Override
            public void run() {
                if (statsList != null && statsList.size() > 0) {
                    for (TaskCronerStats stats : statsList) {
                        TaskCronerInfo config = getFitCronerConfig( stats.getTaskId() );
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
                                    alerts.add( new AlertData( "failRate", percentFormat.format( config.getAlertFailRate() ) + "%", percentFormat.format( v ) + "%(" + numFail +
                                            ")" ) );
                                }
                            }
                            if (numFailProgram > 0 && config.getAlertFailProgramRate() > 0) {
                                double v = (double) numFailProgram / numAll * 100;
                                if (v > config.getAlertFailProgramRate()) {
                                    alerts.add( new AlertData( "failProgramRate", percentFormat.format( config.getAlertFailProgramRate() ) + "%", percentFormat.format( v ) + "%" +
                                            "(" + numFailProgram + ")" ) );
                                }
                            }
                            if (numFailPartner > 0 && config.getAlertFailPartnerRate() > 0) {
                                double v = (double) numFailPartner / numAll * 100;
                                if (v > config.getAlertFailPartnerRate()) {
                                    alerts.add( new AlertData( "failPartnerRate", percentFormat.format( config.getAlertFailPartnerRate() ) + "%", percentFormat.format( v ) + "%" +
                                            "(" + numFailPartner + ")" ) );
                                }
                            }
                            if (numFailData > 0 && config.getAlertFailDataRate() > 0) {
                                double v = (double) numFailData / numAll * 100;
                                if (v > config.getAlertFailDataRate()) {
                                    alerts.add( new AlertData( "failDataRate", percentFormat.format( config.getAlertFailDataRate() ) + "%",
                                            percentFormat.format( v ) + "%(" + numFailData + ")" ) );
                                }
                            }
                            if (timeWait > 0 && config.getAlertWaitTimeout() > 0) {
                                long averageTime = timeWait / numAll;
                                if (averageTime > config.getAlertWaitTimeout()) {
                                    alerts.add( new AlertData( "waitTimeout", config.getAlertWaitTimeout() + "ms", averageTime + "ms" ) );
                                }
                            }
                            if (timeRun > 0 && config.getAlertRunTimeout() > 0) {
                                long averageTime = timeRun / numAll;
                                if (averageTime > config.getAlertRunTimeout()) {
                                    alerts.add( new AlertData( "runTimeout", config.getAlertRunTimeout() + "ms", averageTime + "ms" ) );
                                }
                            }

                            if (alerts.size() > 0) {
                                processAlertInfo( "croner", config.getId(), config.getTaskName(), numAll, alerts, config.getTaskOwner(), config.getTaskLinkOur(),
                                        config.getTaskLinkMch() );
                            }
                        }
                    }
                }
            }
        } );
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
            return runnerMap.get( taskId );
        } finally {
            runnerMapLocker.unlockRead( stamp );
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
            return cronerMap.get( taskId );
        } finally {
            cronerMapLocker.unlockRead( stamp );
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
        try {
            runnerList = dao.list( TaskRunnerInfo.class, runnerSql, 0, 0, false );
            for (TaskRunnerInfo runner : runnerList) {
                runnerMap.put( runner.getId(), runner );
            }
            long stamp = runnerMapLocker.writeLock();
            try {
                this.runnerMap = runnerMap;
            } finally {
                runnerMapLocker.unlockWrite( stamp );
            }
        } catch (TransactionException e) {
            log.error( e.getMessage(), e );
        }
        try {
            cronerList = dao.list( TaskCronerInfo.class, connerSql, 0, 0, false );
            for (TaskCronerInfo croner : cronerList) {
                cronerMap.put( croner.getId(), croner );
            }
            long stamp = cronerMapLocker.writeLock();
            try {
                this.cronerMap = cronerMap;
            } finally {
                cronerMapLocker.unlockWrite( stamp );
            }
        } catch (TransactionException e) {
            log.error( e.getMessage(), e );
        }
        if (cronerList != null) {
            // 检测任务超时。
            for (TaskCronerInfo config : cronerList) {
                // 没有执行过的，跳过
                if (config.getStatsRunNum() == 0) {
                    continue;
                }
                // 没有规划下次执行时间的，跳过。
                if (config.getNextRunDate() == null) {
                    continue;
                }
                // 如果超过约定时间还未执行，就要报警了。
                if ((config.getNextRunDate().getTime() + (config.getStatsRunTime() / config.getStatsRunNum()) + 180_000L) < System.currentTimeMillis()) {
                    ArrayList<AlertData> list = new ArrayList<>();
                    AlertData ad = new AlertData( "cronerTimeOut", dateFormat.format( config.getNextRunDate() ), dateFormat.format( new Date() ) );
                    list.add( ad );
                    processAlertInfo( "croner", config.getId(), config.getTaskName(), 0, list, config.getTaskOwner(), config.getTaskLinkOur(), config.getTaskLinkMch() );
                    // 更新下次执行时间为NULL
                    try {
                        dao.executeCommand( "update task_croner_info set next_run_date=NULL where id=?", new Object[]{config.getId()} );
                    } catch (TransactionException e) {
                        log.error( e.getMessage(), e );
                    }
                }
            }
        }
    }

    /**
     * 根据错误类型对所发送的邮件进行处理，保存发送信息，提取联系人，取得发送类型
     */
    private void processAlertInfo(String type, long taskId, String taskName, long runTimes, List<AlertData> alertList, String taskOwner, String taskLinkOur, String taskLinkMch) {
        // 检测报警通知范围。
        HashSet<String> links = new HashSet<>();
        links.add( taskOwner );
        for (AlertData ad : alertList) {
            if (ad.getColumn().contains( "partner" )) {
                // 此时必须通知商户和自己人。
                links.add( taskLinkOur );
                links.add( taskLinkMch );
                break;
            }
            if (ad.getColumn().equals( "failRate" ) || ad.getColumn().equals( "runTimeout" )) {
                // 此时必须通知自己人。
                links.add( taskLinkOur );
            }
        }
        List<TaskAlertContact> taskAlertContactList = getTaskAlertContactList( links.toArray( new String[0] ) );
        // 报警信息
        TaskAlertInfo info = saveAlertInfo( type, taskId, taskName, runTimes, alertList );
        //保存报警通知信息
        saveAlertNotify( taskAlertContactList, info );

    }

    /**
     * 获得联系方式列表。
     *
     * @param taskLink
     * @return
     */
    private List<TaskAlertContact> getTaskAlertContactList(String... taskLink) {
        HashSet<String> set = new HashSet<>();
        for (String link : taskLink) {
            if (link.length() > 2) {
                try {
                    HashMap map = JsonInterfaceHelper.JSON_CONVERTER.parse( link, HashMap.class );
                    set.addAll( map.keySet() );
                } catch (Exception e) {
                    log.error( e.getMessage(), e );
                }
            }
        }
        String ids = StringUtils.join( set, ',' );
        DataList<TaskAlertContact> list = null;
        try {
            list = dao.list( TaskAlertContact.class, "select * from task_alert_contact where id in (?) and state=1", new Object[]{ids} );
        } catch (TransactionException e) {
            log.error( e.getMessage(), e );
        }
        if (list == null) {
            return null;
        } else {
            return list.results();
        }
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
            notify.setInfoId( info.getId() );
            // 联系人
            notify.setContactMan( contact.getContactName() );
            // 设置为email，因为这是邮件发送 获取根据里面的设置
            notify.setCreateDate( new Date() );
            notify.setSentTimes( 0 );
            notify.setState( 0 );
            //写入email通知
            if (StringUtils.isNotBlank( contact.getEmail() )) {
                notify.setId( dao.getSequenceId( TaskAlertNotify.class ) );
                notify.setContactType( "email" );
                notify.setContactInfo( contact.getEmail() );
                try {
                    dao.save( notify );
                } catch (TransactionException e) {
                    log.error( e.getMessage(), e );
                }
            }
            //写入notify通知
            if (StringUtils.isNotBlank( contact.getNotifyUrl() )) {
                notify.setId( dao.getSequenceId( TaskAlertNotify.class ) );
                notify.setContactType( "notifyUrl" );
                notify.setContactInfo( contact.getEmail() );
                try {
                    dao.save( notify );
                } catch (TransactionException e) {
                    log.error( e.getMessage(), e );
                }
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
        TaskAlertInfo info = new TaskAlertInfo();// 邮件信息
        info.setId( dao.getSequenceId( TaskAlertInfo.class ) );
        info.setTaskId( taskId );
        info.setTaskType( type );
        // 拼接邮件信息
        StringBuilder title = new StringBuilder();
        title.append( "#" + info.getId() + "报警" );
        title.append( "[" ).append( taskInfo ).append( "]" );
        title.append( ":" );
        for (AlertData ad : alertList) {
            title.append( FailTypeTranslateMap.get( ad.getColumn() ) ).append( "," );
        }
        if (title.charAt( title.length() - 1 ) == ',') {
            title.deleteCharAt( title.length() - 1 );
        }
        title.append( "运行超限!" );
        StringBuilder content = new StringBuilder();
        if (runTimes > 0) {
            content.append( "[#" + taskId + taskInfo + "]最近1分钟内执行" + runTimes + "次：" );
        } else {
            content.append( "[#" + taskId + taskInfo + "]最近1分钟内执行异常：" );
        }
        for (AlertData ad : alertList) {
            content.append( FailTypeTranslateMap.get( ad.getColumn() ) ).append( "运行值:" ).append( ad.getValue() ).append( "! 报警阀值:" ).append( ad.getConfig() ).append( "; " );
        }
        content.append( "请尽快处理!!!" );

        // 邮件错误类型
        info.setAlertTitle( title.toString() );
        info.setAlertBody( content.toString() );
        info.setCreateDate( new Date() );
        info.setState( 1 );

        try {
            dao.save( info );
        } catch (TransactionException e) {
            log.error( e.getMessage(), e );
        }
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
