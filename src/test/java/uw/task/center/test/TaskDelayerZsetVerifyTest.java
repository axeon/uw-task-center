package uw.task.center.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import uw.task.TaskData;
import uw.task.TaskFactory;
import uw.task.container.TaskDelayerContainer;
import uw.task.conf.TaskMetaInfoManager;
import uw.task.conf.TaskProperties;
import uw.task.entity.TaskDelayerConfig;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 验证 TaskDelayer 延迟任务确实写入 Redis zset 且 score=到期时间（绕过 center config 下发）。
 * <p>
 * 用户怀疑“压根没按延时提取”。本测试手动注册 EchoDelayer config（绕过 center 的 fetch/upload，
 * 防止 state&lt;1 或 fetch 失败阻塞 submit），投递一个 24h(86400000ms) 延迟任务，再通过反射拿到
 * {@link TaskDelayerContainer} 内部 redisTemplate（与 addTask 同一 Redis 连接）直查 zset：
 * <ol>
 *   <li>投递后 zset size 增加 → addTask 确实 ZADD 写入了；</li>
 *   <li>存在 score &gt; now 的成员 → taskDelay 生效，score=到期时间在 24h 后，poll 不会立即取出。</li>
 * </ol>
 * 两条同时成立即证明“延迟任务写入了 Redis，且按延迟时间排在未来到期”。
 * </p>
 * <p>
 * 注意：TaskMetaInfoManager / TaskDelayerContainer / taskRedisConnectionFactory 都是 TaskAutoConfiguration
 * 的私有字段（在 taskFactory() @Bean 内 new，不暴露为 bean），仅 TaskFactory 是 bean。
 * 故本测试只注入 TaskFactory，metaInfoManager / taskProperties / redisTemplate 全部反射获取。
 * </p>
 *
 * @author axeon
 */
@SpringBootTest(classes = TestContextConfig.class)
@TestPropertySource(properties = "spring.profiles.active=debug")
class TaskDelayerZsetVerifyTest {

    @Autowired
    private TaskFactory taskFactory;

    @SuppressWarnings("unchecked")
    @Test
    void delayTask_24h_writtenToZsetWithFutureScore() throws Exception {
        // 反射拿 TaskFactory.taskDelayerContainer，再拿其 metaInfoManager / taskProperties / redisTemplate
        Field fContainer = TaskFactory.class.getDeclaredField("taskDelayerContainer");
        fContainer.setAccessible(true);
        TaskDelayerContainer container = (TaskDelayerContainer) fContainer.get(taskFactory);

        Field fMeta = TaskDelayerContainer.class.getDeclaredField("taskMetaInfoManager");
        fMeta.setAccessible(true);
        TaskMetaInfoManager metaInfoManager = (TaskMetaInfoManager) fMeta.get(container);

        Field fProps = TaskDelayerContainer.class.getDeclaredField("taskProperties");
        fProps.setAccessible(true);
        TaskProperties taskProperties = (TaskProperties) fProps.get(container);

        Field fRedis = TaskDelayerContainer.class.getDeclaredField("redisTemplate");
        fRedis.setAccessible(true);
        RedisTemplate<String, byte[]> redisTemplate = (RedisTemplate<String, byte[]>) fRedis.get(container);

        // 1. 手动注册 EchoDelayer config（绕过 center，防 fetch 失败 / state<1 阻塞 submit）
        TaskDelayerConfig config = new TaskDelayerConfig();
        config.setTaskClass(EchoDelayer.class.getName());
        config.setTaskTag("");                              // 对齐 TaskData 默认 taskTag
        config.setRunTarget(taskProperties.getRunTarget()); // 对齐 submit 内 setRunTarget
        config.setState(1);
        String configKey = metaInfoManager.getDelayerConfigKey(config);
        metaInfoManager.setDelayerConfig(configKey, config);

        String zsetKey = "uw-task-delayer:" + configKey;
        long sizeBefore = redisTemplate.opsForZSet().size(zsetKey) == null ? 0
                : redisTemplate.opsForZSet().size(zsetKey);

        // 2. 投递 24h 延迟任务：submit 通过 config 校验 → addTask ZADD 写 zset（score=queueDate+86400000）
        String param = "verify-24h-" + System.nanoTime();
        long taskDelayMs = 86400000L;
        TaskData<String, String> td = TaskData.<String, String>builder(EchoDelayer.class.getName(), param)
                .taskDelay(taskDelayMs)
                .build();
        long submitAt = System.currentTimeMillis();
        taskFactory.delayTask(td); // 不抛异常即 submit 已通过、addTask 已写 zset

        // 3. 查 zset：投递后 size 应增加；且应有 score 在未来的成员（taskDelay 生效，24h 后到期）
        long sizeAfter = redisTemplate.opsForZSet().size(zsetKey) == null ? 0
                : redisTemplate.opsForZSet().size(zsetKey);
        Long futureCount = redisTemplate.opsForZSet().count(zsetKey, (double) submitAt + 1, Double.MAX_VALUE);

        System.out.println("\n========== 延迟任务 zset 验证 ==========");
        System.out.println("uw.task.redis = " + taskProperties.getRedis().getHost() + ":" + taskProperties.getRedis().getPort()
                + " db=" + taskProperties.getRedis().getDatabase() + "（uw-task 实际连接，addTask 写到这里）");
        System.out.println("zsetKey      = " + zsetKey);
        System.out.println("taskDelay    = " + taskDelayMs + "ms (24h)");
        System.out.println("size before  = " + sizeBefore);
        System.out.println("size after   = " + sizeAfter);
        System.out.println("未到期(score>now) = " + futureCount);
        System.out.println("可用 redis-cli 复核: ZRANGE \"" + zsetKey + "\" 0 -1 WITHSCORES");
        System.out.println("==========================================\n");

        assertTrue(sizeAfter > sizeBefore,
                "投递后 zset size 应增加：before=" + sizeBefore + " after=" + sizeAfter + "（证明 addTask 写入 zset）");
        assertTrue(futureCount != null && futureCount > 0,
                "应有 score 在未来的成员：futureCount=" + futureCount + "（证明 taskDelay 生效，24h 后才到期，poll 不会立即取）");
    }
}
