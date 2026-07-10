package uw.task.center.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import uw.task.TaskData;
import uw.task.TaskFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * uw-task 端到端集成测试（在 uw-task-center 完整环境跑）。
 * <p>
 * 用 {@link TestContextConfig} 启动（参考 uw-app-template，自定义 BeanNameGenerator 避免 package-info 冲突），
 * profile=debug 走 bootstrap-debug（nacos 192.168.88.21:8848, namespace dev）。
 * 验证 sendToQueue 投递 → listener 消费 的完整链路（含 TaskData KryoSerializable 序列化在真实 MQ 端到端）。
 * </p>
 *
 * @author axeon
 */
@SpringBootTest(classes = TestContextConfig.class)
@TestPropertySource(properties = "spring.profiles.active=debug")
class TaskRunnerIntegrationTest {

    @Test
    void contextLoads() {
        assertNotNull(TaskFactory.getInstance(), "TaskFactory 未就绪");
    }

    /**
     * 端到端：sendToQueue 投递 → EchoRunner 消费。
     * <p>listener 启动依赖 task-center 下发 EchoRunner config（首次 uploadRunnerInfo → updateConfig 拉回 → bindMessageListenerContainer），
     * 故 latch 超时给到 90s 覆盖启动 + 首次配置上传 + MQ 往返 + 消费。</p>
     */
    @Test
    void sendToQueue_endToEnd_consumed() throws Exception {
        EchoRunner.latch = new CountDownLatch(1);
        EchoRunner.receivedParam.set(null);
        TaskData<String, String> td = TaskData.<String, String>builder(EchoRunner.class, "hello-center").build();
        TaskFactory.getInstance().sendToQueue(td);
        boolean consumed = EchoRunner.latch.await(90, TimeUnit.SECONDS);
        assertTrue(consumed, "消息未被消费：检查 task-center config 下发 + listener 启动");
        assertEquals("hello-center", EchoRunner.receivedParam.get());
    }
}
