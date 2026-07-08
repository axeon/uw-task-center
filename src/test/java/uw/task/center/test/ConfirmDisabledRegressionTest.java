package uw.task.center.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import uw.task.TaskData;
import uw.task.TaskFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 删除 publisher-confirm-type 后的回归测试（confirm=NONE）。
 * <p>
 * publisher confirm 只负责「消息是否被 broker 接收」的异步回调，与下列三套机制<b>完全正交</b>：
 * <ol>
 *   <li>入队投递：basicPublish 的 fire-and-forget，本就不依赖 confirm；</li>
 *   <li>TTL+DLX 延时：消息 expiration + 死信路由，是 broker 端消息属性行为，与 publisher 确认无关；</li>
 *   <li>RPC reply：sendAndReceive 依赖 direct reply-to + correlationId 匹配回复，不依赖 confirm。</li>
 * </ol>
 * 全 backend grep 已确认业务代码<b>零</b>处使用 ConfirmCallback/CorrelationData/waitForConfirms
 * （唯一相关代码是 TaskAutoConfiguration 的 whenNonNull 配置绑定，不配即 NONE）。
 * 本类在 nacos debug 配置已删除 publisher-confirm-type 的真实环境下，端到端验证三大功能不退化。
 * </p>
 *
 * @author axeon
 */
@SpringBootTest(classes = TestContextConfig.class)
@TestPropertySource(properties = {
        "spring.profiles.active=debug",
        "spring.cloud.nacos.discovery.register-enabled=false",
        "server.port=0"
})
class ConfirmDisabledRegressionTest {

    /**
     * 预热：每条用例前投递一条普通消息等待消费，确保 EchoRunner listener 已就绪
     * （task-center config 下发 + 队列/TTL 队列声明 + 消费启动）。
     * <p>RPC 的 sendAndReceive 与延时回流都依赖 listener 已启动。listener 就绪后本方法秒回。</p>
     */
    @BeforeEach
    void awaitListenerReady() throws Exception {
        EchoRunner.latch = new CountDownLatch(1);
        TaskFactory.getInstance().sendToQueue(
                TaskData.<String, String>builder(EchoRunner.class, "warmup").build());
        assertTrue(EchoRunner.latch.await(90, TimeUnit.SECONDS),
                "EchoRunner listener 未就绪：检查 task-center config 下发 + 队列声明");
    }

    /** 入队任务：confirm=NONE 下 sendToQueue 投递 → 消费，结果正确。 */
    @Test
    void sendToQueue_consumedWithoutConfirm() throws Exception {
        EchoRunner.latch = new CountDownLatch(1);
        EchoRunner.receivedParam.set(null);
        TaskData<String, String> td = TaskData.<String, String>builder(EchoRunner.class, "reg-queue").build();
        assertDoesNotThrow(() -> TaskFactory.getInstance().sendToQueue(td));
        assertTrue(EchoRunner.latch.await(30, TimeUnit.SECONDS), "入队消息未被消费");
        assertEquals("reg-queue", EchoRunner.receivedParam.get());
    }

    /** RPC 请求：confirm=NONE 下 runTask 强制远程 GLOBAL_RPC，sendAndReceive 正常收到 reply。 */
    @Test
    void runTask_globalRpc_returnsResultWithoutConfirm() {
        TaskData<String, String> td = TaskData.<String, String>builder(EchoRunner.class, "reg-rpc")
                .runType(TaskData.RUN_TYPE_GLOBAL_RPC)
                .build();
        TaskData<String, String> result = assertDoesNotThrow(() -> TaskFactory.getInstance().runTask(td));
        assertNotNull(result, "RPC 未返回结果：sendAndReceive 在 confirm=NONE 下 reply 失败");
        assertEquals(TaskData.STATE_SUCCESS, result.getState());
        assertEquals("echo:reg-rpc", result.getResultData());
    }

    /** 延时任务：confirm=NONE 下 taskDelay 消息经 TTL 队列过期 → DLX 回流主队列 → 消费，且延迟期内未被提前消费。 */
    @Test
    void sendToQueue_withDelay_consumedAfterDelayWithoutConfirm() throws Exception {
        EchoRunner.latch = new CountDownLatch(1);
        EchoRunner.receivedParam.set(null);
        long delayMs = 3000;
        long start = System.currentTimeMillis();
        TaskData<String, String> td = TaskData.<String, String>builder(EchoRunner.class, "reg-delay")
                .taskDelay(delayMs)
                .build();
        assertDoesNotThrow(() -> TaskFactory.getInstance().sendToQueue(td));
        // 延迟期内不应被消费
        assertFalse(EchoRunner.latch.await(Math.max(1000, delayMs - 1000), TimeUnit.MILLISECONDS),
                "延迟消息在延迟期内被提前消费");
        // 延迟过后应被消费（留足 TTL 过期 + DLX 路由 + 消费余量）
        assertTrue(EchoRunner.latch.await(delayMs + 30_000, TimeUnit.MILLISECONDS),
                "延迟消息过期后未被消费：TTL+DLX 链路异常");
        assertEquals("reg-delay", EchoRunner.receivedParam.get());
        assertTrue(System.currentTimeMillis() - start >= delayMs,
                "消费早于延迟时间：" + (System.currentTimeMillis() - start) + "ms < " + delayMs + "ms");
    }
}
