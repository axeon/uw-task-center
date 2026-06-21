package uw.task.center.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * uw-task-center 自动配置类。
 *
 * <p>启用 {@link TaskCenterProperties} 配置绑定，并注册供 {@code @Scheduled} 与
 * {@link AlertProcessService} 等定时调度使用的 {@link TaskScheduler}。</p>
 *
 * @author axeon
 */
@Configuration
@EnableConfigurationProperties({TaskCenterProperties.class})
public class TaskCenterAutoConfiguration implements WebMvcConfigurer {


    /**
     * 定时任务调度器（线程池大小 3，供任务中心内部 croner 与 @Scheduled 使用）。
     *
     * @return TaskScheduler 实例
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        return taskScheduler;
    }

}