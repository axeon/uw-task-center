package uw.task.center.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.CommandLineOptions;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import uw.common.util.KryoUtils;
import uw.common.util.SystemClock;
import uw.task.TaskData;
import uw.task.TaskFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TaskDelayer 投递吞吐 + 序列化吞吐（对比 {@link TaskRunnerBenchmark}，排查 Delayer 瓶颈在 CPU 还是 Redis）。
 * <p>
 * serialize：纯 CPU（KryoUtils.serialize），不连 Redis。测序列化上限（与 sendToQueue benchmark 同一基准对比）。
 * enqueueBatch：delayTask（含 nextId + serialize + Redis ZADD），写 Redis zset。
 * 对比两者：若 serialize >> enqueueBatch，瓶颈在 Redis（网络/单连接）；若接近，瓶颈在序列化（nextId + Kryo）。
 * </p>
 * <p>
 * 与 sendToQueue benchmark 的差异：①载体是 Redis zset 非 RabbitMQ；②TaskDelayer 非 TaskRunner 子类，
 * builder 必须用字符串版本 {@code builder(taskClass.getName(), ...)}；③TearDown 无 AMQP queueInfo 可查，
 * 改用 submitted/consumed 计数对比等消费追平；④taskDelay=0 立即到期，让 poll 尽快消费。
 * </p>
 *
 * @author axeon
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 3)
@Measurement(iterations = 5, time = 3)
@Fork(1)
public class TaskDelayerBenchmark {

    private static final int BATCH = 1000;

    private ConfigurableApplicationContext context;
    private TaskFactory taskFactory;
    private TaskData<String, String> preBuiltTaskData;

    /** 本次 benchmark 累计投递数（enqueueBatch 递增），TearDown 据此等消费追平。 */
    private static final AtomicLong submitted = new AtomicLong(0);

    @Setup
    public void setup() throws InterruptedException {
        context = SpringApplication.run(TestContextConfig.class, "--spring.profiles.active=debug", "--server.port=0",
                "--uw.task.run-target=default",
                "--spring.cloud.nacos.discovery.register-enabled=false");
        taskFactory = TaskFactory.getInstance();
        // 等 delayer config 下发 + zset 残留（上次失败/测试遗留）被 poll 消化完
        Thread.sleep(10000);
        // reset 计数：benchmark 的 consumed/startTime 从 0 重计，不受残留消费干扰
        EchoDelayer.consumed.set(0);
        EchoDelayer.consumeStartTime = 0;
        EchoDelayer.consumeEndTime = 0;
        EchoDelayer.expectedSet = null;
        EchoDelayer.latch = null;
        submitted.set(0);
        // TaskDelayer 非 TaskRunner 子类，必须用字符串版本 builder
        preBuiltTaskData = TaskData.<String, String>builder(EchoDelayer.class.getName(), "bench").build();
    }

    @TearDown
    public void tearDown() throws InterruptedException {
        // 等消费追平投递（带超时防死循环：个别任务卡 worker 时不无限阻塞）
        long submittedCount = submitted.get();
        long deadline = System.currentTimeMillis() + 300_000L;
        while (EchoDelayer.consumed.get() < submittedCount && System.currentTimeMillis() < deadline) {
            Thread.sleep(500);
        }
//        Thread.sleep(30000);
        long count = EchoDelayer.consumed.get();
        long elapsed = EchoDelayer.consumeEndTime - EchoDelayer.consumeStartTime;
        double consumeTps = elapsed > 0 ? count * 1000.0 / elapsed : 0;
        long backlog = submittedCount - count;
        System.out.println("\n=== Delayer Consume: " + count + "/" + submittedCount + " msgs, backlog=" + backlog
                + ", elapsed=" + elapsed + "ms, TPS=" + consumeTps + " ops/s ===\n");
        SystemClock.shutdown();
        if (context != null) {
            context.close();
        }
    }

//    /** 纯序列化（不连 Redis）：KryoUtils.serialize 预建 TaskData。测 CPU 上限。 */
//    @Benchmark
//    public byte[] serialize() {
//        return KryoUtils.serialize(preBuiltTaskData);
//    }

    /**
     * 批量投递 BATCH 条到 Redis zset（taskDelay=20s）：完整 delayTask 路径（nextId + serialize + ZADD）。
     * <p>20s 延迟下任务在 benchmark 期间不到期（zset 只增不减），enqueueBatch 测的是纯投递吞吐（ZADD 不受 score 影响）；
     * 任务在投递 20s 后陆续到期，TearDown 等到全部消费完，借此看延迟到点后的消费追平速率。</p>
     */
    @Benchmark
    @OperationsPerInvocation(BATCH)
    public void enqueueBatch() {
        for (int i = 0; i < BATCH; i++) {
            taskFactory.delayTask(TaskData.<String, String>builder(EchoDelayer.class.getName(), "c" + i).taskDelay(20000L).build());
            submitted.incrementAndGet();
        }
    }

    public static void main(String[] args) throws Exception {
        // 支持命令行透传 JMH 参数：-t N 控制线程数做多线程对照、-wi/-i/-f 调轮次
        CommandLineOptions cmdLine = new CommandLineOptions(args);
        Options opt = new OptionsBuilder()
                .include(TaskDelayerBenchmark.class.getSimpleName())
                .parent(cmdLine)
                .build();
        new Runner(opt).run();
    }
}
