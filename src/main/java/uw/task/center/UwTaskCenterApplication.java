package uw.task.center;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import uw.common.app.AppBootStrap;

/**
 * uw-task-center 任务管理中心启动入口。
 *
 * <p>作为 uw-task 框架的服务端，承担两类职责：</p>
 * <ul>
 *     <li>接收任务执行主机（uw-task 客户端）的 RPC 上报：主机状态、任务统计、配置初始化与增量拉取；</li>
 *     <li>提供 OPS 管理端接口：任务/主机/告警配置的增删改查、运行报表、ES 日志检索。</li>
 * </ul>
 * <p>依赖 Nacos 做服务注册与配置，依赖 MySQL 存储配置与统计数据，依赖 ES 存储任务运行日志，
 * 依赖钉钉/notifyUrl 推送告警。</p>
 *
 * @author axeon
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UwTaskCenterApplication {

    /**
     * 主入口方法。
     *
     * @param args 启动参数，透传给 {@link AppBootStrap#run}
     */
    public static void main(String[] args) {
        AppBootStrap.run(UwTaskCenterApplication.class, args);
    }
}
