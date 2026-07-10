package uw.task.center.test;

import org.springframework.stereotype.Component;
import uw.task.TaskData;
import uw.task.TaskDelayer;
import uw.task.entity.TaskContact;
import uw.task.entity.TaskDelayerConfig;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试用业务 Delayer（uw-task 第三种任务类型），与 {@link EchoRunner} 对称。
 * <p>
 * 载体为 Redis zset（非 RabbitMQ），由 {@code TaskDelayerContainer} poll 线程 Lua 原子取出到期任务后，
 * 在虚拟线程 worker 内调用 {@link #run(TaskData)}。
 * </p>
 * <p>
 * <b>残留隔离</b>：Redis zset 跨测试运行/跨用例可能残留历史任务（上一次投递后未被消费的到期成员），
 * 新 JVM 启动后会被 poll 立即消费。为避免残留任务污染断言，{@link #expectedSet} 仅放本轮投递的唯一 param，
 * {@link #run} 只对 expectedSet 命中的 param 记录时刻与 countDown——残留任务 param 不同，不干扰 latch/receivedParam。
 * </p>
 *
 * @author axeon
 */
@Component
public class EchoDelayer extends TaskDelayer<String, String> {

    /** 端到端测试用（等本轮 N 条被消费）。 */
    public static volatile CountDownLatch latch;

    /** 本轮期望的 param 集合（测试方法填入本轮投递的唯一 param，run 仅对命中项 countDown）。 */
    public static volatile Set<String> expectedSet;

    /** 端到端测试用：记录本轮最后收到的 taskParam。 */
    public static final AtomicReference<String> lastReceivedParam = new AtomicReference<>();

    /** 端到端测试用：本轮首个 expected 任务实际执行时刻（毫秒），用于断言“延迟到点才执行”。 */
    public static volatile long firstExecutedAt = 0L;

    /** 消费计数器（含残留任务，每次 run 递增；断言用相对增量而非绝对值）。 */
    public static final AtomicInteger consumed = new AtomicInteger(0);

    /** 第一条消费的起始时间（CAS 保证只设一次，benchmark 算消费 TPS 用）。 */
    public static volatile long consumeStartTime = 0;

    /** 最后一条消费的结束时间（benchmark 算消费 TPS 用）。 */
    public static volatile long consumeEndTime = 0;

    @Override
    public void run(TaskData<String, String> task) throws Exception {
        String param = task.getTaskParam();
        // 消费计数（CAS 保证首条设 startTime）—— benchmark 算消费 TPS 用
        if (consumed.compareAndSet(0, 1)) {
            consumeStartTime = System.currentTimeMillis();
        } else {
            consumed.incrementAndGet();
        }
        consumeEndTime = System.currentTimeMillis();
        // run 返回 void，结果经 taskData 回写（对齐 spec）
        task.setResultData("echo:" + param);
        // 仅本轮 expected 命中才记录/countDown，隔离 zset 残留任务
        Set<String> expected = expectedSet;
        if (expected != null && expected.contains(param)) {
            lastReceivedParam.set(param);
            if (firstExecutedAt == 0L) {
                firstExecutedAt = System.currentTimeMillis();
            }
            CountDownLatch l = latch;
            if (l != null) {
                l.countDown();
            }
        }
    }

    @Override
    public TaskDelayerConfig initConfig() {
        TaskDelayerConfig config = new TaskDelayerConfig();
        config.setTaskClass(getClass().getName());
        config.setConsumerNum(50);
        // 测试用 1s poll 间隔，到期即取，缩短用例耗时（默认 3s）
        config.setPollInterval(1);
        config.setPrefetchNum(50);
        // 不发 ES 日志：默认 RECORD 每条任务 HTTP 回写 center，拖累消费且无断言价值；设 NONE 看消费吞吐真实上限
        config.setLogLevel(TaskDelayerConfig.TASK_LOG_TYPE_NONE);
        return config;
    }

    @Override
    public TaskContact initContact() {
        TaskContact contact = new TaskContact();
        contact.setTaskClass(getClass().getName());
        return contact;
    }
}
