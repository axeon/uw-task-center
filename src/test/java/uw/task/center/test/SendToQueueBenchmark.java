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

/**
 * 入队吞吐 + 序列化吞吐（对比，排查瓶颈在 CPU 还是 broker）。
 * <p>
 * serialize：纯 CPU（KryoUtils.serialize），不连 broker。测序列化上限。
 * enqueueBatch：sendToQueue（含 nextId + serialize + channel + basicPublish），连远程 broker。
 * 对比两者：若 serialize >> enqueueBatch，瓶颈在 broker（网络/反压）；若接近，瓶颈在序列化。
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
public class SendToQueueBenchmark {

    private static final int BATCH = 1000;
    private static final String ECHO_QUEUE = "uw.task.center.test.EchoRunner#$default";

    private ConfigurableApplicationContext context;
    private TaskFactory taskFactory;
    private TaskData<String, String> preBuiltTaskData;

    @Setup
    public void setup() throws InterruptedException {
        EchoRunner.consumed.set(0);
        EchoRunner.consumeStartTime = 0;
        EchoRunner.consumeEndTime = 0;
        EchoRunner.latch = null;
        context = SpringApplication.run(TestContextConfig.class, "--spring.profiles.active=debug", "--server.port=0",
                "--uw.task.run-target=default",
                "--spring.cloud.nacos.discovery.register-enabled=false");
        taskFactory = TaskFactory.getInstance();
        preBuiltTaskData = TaskData.<String, String>builder(EchoRunner.class, "bench").build();
        Thread.sleep(15000);
    }

    @TearDown
    public void tearDown() throws InterruptedException {
        while (taskFactory.getQueueInfo(ECHO_QUEUE)[0] > 0) {
            Thread.sleep(1000);
        }
        long count = EchoRunner.consumed.get();
        long elapsed = EchoRunner.consumeEndTime - EchoRunner.consumeStartTime;
        double consumeTps = elapsed > 0 ? count * 1000.0 / elapsed : 0;
        System.out.println("\n=== Consume TPS: " + count + " msgs / " + elapsed + " ms = " + consumeTps + " ops/s ===\n");
        SystemClock.shutdown();
        if (context != null) {
            context.close();
        }
    }

    /** 纯序列化（不连 broker）：KryoUtils.serialize 预建 TaskData。测 CPU 上限。 */
    @Benchmark
    public byte[] serialize() {
        return KryoUtils.serialize(preBuiltTaskData);
    }

    /** 批量入队 BATCH 条（连 broker）：完整 sendToQueue 路径。 */
    @Benchmark
    @OperationsPerInvocation(BATCH)
    public void enqueueBatch() {
        for (int i = 0; i < BATCH; i++) {
            taskFactory.sendToQueue(TaskData.<String, String>builder(EchoRunner.class, "c" + i).build());
        }
    }

    public static void main(String[] args) throws Exception {
        // 支持命令行透传 JMH 参数：-t N 控制线程数做多线程对照、-wi/-i/-f 调轮次
        CommandLineOptions cmdLine = new CommandLineOptions(args);
        Options opt = new OptionsBuilder()
                .include(SendToQueueBenchmark.class.getSimpleName())
                .parent(cmdLine)
                .build();
        new Runner(opt).run();
    }
}
