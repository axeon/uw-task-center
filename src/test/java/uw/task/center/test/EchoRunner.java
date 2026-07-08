package uw.task.center.test;

import org.springframework.stereotype.Component;
import uw.task.TaskData;
import uw.task.TaskRunner;
import uw.task.entity.TaskContact;
import uw.task.entity.TaskRunnerConfig;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试用业务 Runner。
 * <p>
 * 维护消费计数器（consumed）+ 首末消费时间（consumeStartTime/consumeEndTime），供 JMH @TearDown 计算消费 TPS。
 * latch 供端到端集成测试（UwTaskCenterIntegrationTest）用，JMH 不用。
 * </p>
 *
 * @author axeon
 */
@Component
public class EchoRunner extends TaskRunner<String, String> {

    /** 端到端测试用（等消费 N 条）。JMH 不用（null）。 */
    public static volatile CountDownLatch latch;
    /** 端到端测试用：记录收到的 taskParam。 */
    public static final java.util.concurrent.atomic.AtomicReference<String> receivedParam = new java.util.concurrent.atomic.AtomicReference<>();

    /** 消费计数器（每次 runTask 递增）。 */
    public static final AtomicInteger consumed = new AtomicInteger(0);

    /** 第一条消费的起始时间（CAS 保证只设一次）。 */
    public static volatile long consumeStartTime = 0;

    /** 最后一条消费的结束时间。 */
    public static volatile long consumeEndTime = 0;

    @Override
    public String runTask(TaskData<String, String> taskData) {
        String param = taskData.getTaskParam();
        // 消费计数：CAS 保证首条设 startTime
        if (consumed.compareAndSet(0, 1)) {
            consumeStartTime = System.currentTimeMillis();
        } else {
            consumed.incrementAndGet();
        }
        consumeEndTime = System.currentTimeMillis();
        receivedParam.set(param);
        // latch（端到端测试用，JMH 时为 null）
        CountDownLatch l = latch;
        if (l != null) {
            l.countDown();
        }
        return "echo:" + param;
    }

    @Override
    public TaskRunnerConfig initConfig() {
        TaskRunnerConfig config = new TaskRunnerConfig();
        config.setTaskClass(getClass().getName());
        config.setQueueType(TaskRunnerConfig.TYPE_QUEUE_TASK);
        config.setConsumerNum(5);
        config.setPrefetchNum(50);
        // 开启 TTL+DLX 延时队列，使延时回归用例可覆盖（taskDelay=0 的消息仍走主队列，不影响 benchmark）
        config.setDelayType(TaskRunnerConfig.TYPE_DELAY_ON);
        return config;
    }

    @Override
    public TaskContact initContact() {
        TaskContact contact = new TaskContact();
        contact.setTaskClass(getClass().getName());
        return contact;
    }
}
