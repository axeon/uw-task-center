package uw.task.center.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import uw.task.TaskData;
import uw.task.TaskFactory;
import uw.task.exception.TaskRuntimeException;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * uw-task TaskDelayer 端到端集成测试（在 uw-task-center 完整环境跑）。
 * <p>
 * 用 {@link TestContextConfig} 启动，profile=debug 走 bootstrap-debug（nacos 192.168.88.21:8848, namespace dev）。
 * 与 {@link TaskRunnerIntegrationTest}（TaskRunner 队列链路）对称，验证 TaskDelayer 的完整链路：
 * </p>
 * <pre>
 * TaskData.builder(taskClass.getName(), taskParam).taskDelay(ms)   // TaskDelayer 非 TaskRunner 子类，必须用字符串版本 builder
 *   → TaskFactory.delayTask → TaskDelayerContainer.submit           // 校验 configKey 已注册（未注册抛 TaskRuntimeException）
 *   → Redis zset（key=uw-task-delayer:+configKey, score=queueDate+taskDelay, member=Kryo 序列化 TaskData）
 *   → poll 线程 Lua 原子 ZRANGEBYSCORE+ZREM 取出到期任务              // score &lt;= now 才取出，故到点才执行
 *   → 虚拟线程 worker execute → EchoDelayer.run                      // 限速→run→异常分类→统计/日志→重试
 * </pre>
 * <p>
 * 投递依赖 task-center 下发 delayer config（首次 uploadDelayerInfo → setDelayerConfig → configureTaskDelayer 启 poll handle），
 * submit 对 configKey 注册做硬校验，首传窗口内投递会抛 {@link TaskRuntimeException}，故用 {@link #submitWhenReady} 重试覆盖。
 * 每轮用唯一 param（nanoTime）+ {@link EchoDelayer#expectedSet} 精确匹配，隔离 Redis zset 跨运行残留任务。
 * </p>
 *
 * @author axeon
 */
@SpringBootTest(classes = TestContextConfig.class)
@TestPropertySource(properties = "spring.profiles.active=debug")
class TaskDelayerIntegrationTest {

    /**
     * 端到端：delayTask 投递 → EchoDelayer run 消费（taskDelay=2000ms，验证“延迟到点才执行”）。
     */
    @Test
    void delayTask_endToEnd_consumed() throws Exception {
        long taskDelayMs = 2000L;
        String param = "hello-delayer-" + System.nanoTime();
        Set<String> expected = ConcurrentHashMap.newKeySet();
        expected.add(param);
        EchoDelayer.expectedSet = expected;
        EchoDelayer.latch = new CountDownLatch(1);
        EchoDelayer.lastReceivedParam.set(null);
        EchoDelayer.firstExecutedAt = 0L;

        TaskData<String, String> td = TaskData
                .<String, String>builder(EchoDelayer.class.getName(), param)
                .taskDelay(taskDelayMs)
                .build();

        long submitAt = submitWhenReady(td, 60);
        boolean done = EchoDelayer.latch.await(120, TimeUnit.SECONDS);
        assertTrue(done, "延迟任务未被消费：检查 task-center delayer config 下发 + poll 线程启动");

        assertEquals(param, EchoDelayer.lastReceivedParam.get());
        // 延迟语义：ZRANGEBYSCORE 仅取 score(=queueDate+taskDelay) <= now 的成员，故 executedAt >= submitAt + taskDelay（留 500ms 时钟漂移容差）
        long waited = EchoDelayer.firstExecutedAt - submitAt;
        assertTrue(waited >= taskDelayMs - 500,
                "延迟未生效：实际等待 " + waited + "ms < taskDelay(" + taskDelayMs + "ms)-500 容差，任务被提前执行");
    }

    /**
     * 多条延迟任务并发投递 → 全部到点消费（验证批量 poll + 虚拟线程并发执行）。
     */
    @Test
    void delayTask_batch_allConsumed() throws Exception {
        int n = 5;
        long taskDelayMs = 15000L;
        String prefix = "batch-" + System.nanoTime() + "-";
        Set<String> expected = ConcurrentHashMap.newKeySet();
        EchoDelayer.expectedSet = expected;
        EchoDelayer.latch = new CountDownLatch(n);
        EchoDelayer.lastReceivedParam.set(null);
        EchoDelayer.firstExecutedAt = 0L;

        for (int i = 0; i < n; i++) {
            String p = prefix + i;
            expected.add(p);
            TaskData<String, String> td = TaskData
                    .<String, String>builder(EchoDelayer.class.getName(), p)
                    .taskDelay(taskDelayMs)
                    .build();
            submitWhenReady(td, 60);
        }

        boolean allConsumed = EchoDelayer.latch.await(120, TimeUnit.SECONDS);
        assertTrue(allConsumed, n + " 条延迟任务未全部消费：检查 poll 批量取数 + 虚拟线程并发执行");
    }

    /**
     * 重试投递：覆盖首次 delayer config 下发窗口（submit 校验 configKey 未注册时抛 TaskRuntimeException）。
     * <p>
     * 首次 updateTaskDelayerConfig 走 firstRun 上传默认配置 + setDelayerConfig + configureTaskDelayer 启 poll handle，
     * 完成后 configMap 含 configKey，submit 即成功；center 不可用时走降级 startDelayersLocal 同样 setDelayerConfig。
     * </p>
     *
     * @param td             任务数据
     * @param timeoutSeconds 重试总超时（秒）
     * @return 投递成功时刻（毫秒）
     */
    private long submitWhenReady(TaskData<String, String> td, int timeoutSeconds) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutSeconds * 1000L;
        while (System.currentTimeMillis() < deadline) {
            try {
                TaskFactory.getInstance().delayTask(td);
                return System.currentTimeMillis();
            } catch (TaskRuntimeException e) {
                // config 尚未下发（configKey 未注册），等待重试
                Thread.sleep(1000);
            }
        }
        throw new IllegalStateException("投递重试超时（" + timeoutSeconds + "s）：delayer config 始终未就绪");
    }
}
