[TOC]

# uw-task-center 任务管理中心

## 项目简介

**uw-task-center** 是 [uw-task](#uw-task-客户端框架使用文档) 分布式任务框架的**服务端管理中心**。

它本身不执行业务任务，而是作为整个任务体系的"大脑"，承担三类职责：

1. **配置中心**：维护定时任务（croner）、队列任务（runner）、延迟任务（delayer）的服务端配置（cron 表达式、并发、限速、重试、延迟 poll 节奏、告警阈值、联系人等），任务执行主机通过 RPC 增量拉取。
2. **运维监控**：接收各执行主机上报的运行统计与主机指标，提供仪表盘、分时段/分任务报表、ES 日志检索。
3. **告警中枢**：按任务配置的阈值（失败率、等待/运行超时、队列堆积、定时任务未按时执行等）判定并生成告警，经钉钉/notifyUrl 推送给责任人。

> 本仓库是**服务端**。uw-task **客户端框架**（在业务应用中继承 `TaskCroner` / `TaskRunner` 编写任务）的使用文档见文末[附录](#uw-task-客户端框架使用文档)。

## 技术栈

- **Spring Boot** + **Spring Cloud Alibaba (Nacos)**：服务注册与配置中心。
- **MySQL**：存储任务配置、主机信息、告警数据；统计明细按日分表（`task_runner_stats` / `task_croner_stats` / `task_delayer_stats`）。
- **Elasticsearch**：存储任务运行日志（`uw.task.runner.log` / `uw.task.croner.log`），由执行主机经 logback-es 异步写入。
- **Redis**：全局限速、缓存。
- **RabbitMQ**：uw-task 客户端的队列任务通道（中心本身不直连 MQ）。
- **钉钉 webhook / notifyUrl**：告警推送通道。

## 模块结构

```
uw.task.center
├── UwTaskCenterApplication        # 启动入口
├── conf/                          # 自动配置、Swagger、Properties
│   ├── TaskCenterAutoConfiguration
│   ├── TaskCenterProperties       # uw.task.center.* 配置（centerName、alertDing 钉钉通知）
│   └── SwaggerConfig              # debug/dev 环境的 OpenAPI 文档
├── controller/
│   ├── rpc/TaskRpcController      # 供 uw-task 客户端调用的 RPC 接口（UserType.RPC）
│   ├── ops/                       # OPS 管理端接口（UserType.OPS）
│   │   ├── home/                  # 仪表盘
│   │   ├── host/                  # 主机管理
│   │   ├── croner/                # 定时任务配置/报表/日志
│   │   ├── runner/                # 队列任务配置/报表/日志
│   │   ├── delayer/               # 延迟任务配置/报表/日志（表名 task_delayer_*）
│   │   ├── alert/                 # 告警信息/通知/联系人
│   │   └── log/                   # 操作日志/数据历史
│   └── open/EnumController        # 枚举导出（debug/dev）
├── service/AlertProcessService    # 告警判定与生成核心服务
├── croner/                        # 中心内部定时任务
│   ├── AlertNotifyScanCroner      # 扫描并发送未送达告警（每 3 分钟）
│   └── TaskHostCleanCroner        # 清理失联主机（每 5 分钟）
├── entity/                        # 数据库实体
├── dto/                           # 查询参数
├── vo/                            # 视图对象
└── util/                          # DingUtils 钉钉通知、ContactUtils 等
```

## 数据表

| 表名 | 说明 |
|---|---|
| `task_croner_info` | 定时任务配置（cron、运行目标、告警阈值、联系人） |
| `task_runner_info` | 队列任务配置（并发、限速、重试、告警阈值、联系人） |
| `task_delayer_info` | 延迟任务配置（执行并发、poll 节奏、限速、重试、告警阈值、联系人） |
| `task_host_info` | 任务执行主机注册信息与累计运行指标（含 croner/runner/delayer 三类统计） |
| `task_croner_stats` | 定时任务运行统计明细（按日分表） |
| `task_runner_stats` | 队列任务运行统计明细（按日分表） |
| `task_delayer_stats` | 延迟任务运行统计明细（按日分表） |
| `task_alert_info` | 告警事件记录（标题、正文、触发时间） |
| `task_alert_notify` | 告警通知记录（待发送/已发送） |
| `task_alert_contact` | 告警联系人（邮箱、钉钉 notifyUrl 等） |
| `sys_crit_log` / `sys_data_history` / `sys_seq` | 操作日志 / 数据历史 / 序列号（公共表） |

建表脚本见 [`database/uw_task.sql`](database/uw_task.sql)。

## 部署

### 环境变量

启动依赖 Nacos，通过环境变量注入连接信息：

| 变量 | 说明 |
|---|---|
| `NACOS_SERVER` | Nacos 地址 |
| `NACOS_USERNAME` / `NACOS_PASSWORD` | Nacos 账号密码 |
| `NACOS_NAMESPACE` | Nacos 命名空间 |
| `APP_HOST` | 注册 IP（可选，默认自动探测） |

### 中心自身配置（Nacos 中的 yaml）

```yaml
uw:
  task:
    center:
      # 中心名称，会拼到告警标题前缀
      center-name: 任务管理中心
      # 全局告警钉钉机器人（留空则不通过全局钉钉发送）
      alert-ding:
        notify-url: https://oapi.dingtalk.com/robot/send?access_token=xxx
        notify-key: TASK
```

其余 MySQL / Redis / ES 连接由 Nacos 统一配置，遵循 uw-base 约定。

### 构建

```bash
mvn clean package
java -jar target/uw-task-center-<version>.jar
```

## RPC 接口（供 uw-task 客户端调用）

基路径 `/rpc/task`，要求 `UserType.RPC` 身份。

| 方法 | 路径 | 说明 |
|---|---|---|
| POST | `/host/report` | 主机状态上报：更新 JVM/线程指标与三类任务（croner/runner/delayer）累计统计、写入分表统计明细、触发告警判定，返回主机 id/状态 |
| GET | `/croner/list` | 拉取定时任务配置（按 runTarget/taskProject 前缀/增量时间过滤） |
| GET | `/runner/list` | 拉取队列任务配置（同上） |
| GET | `/delayer/list` | 拉取延迟任务配置（同上） |
| PUT | `/croner/tick` | 心跳：更新定时任务下次执行时间 |
| POST | `/croner/init` | 首次注册定时任务默认配置（taskClass+taskParam+runTarget 幂等） |
| POST | `/runner/init` | 首次注册队列任务默认配置（taskClass+taskTag+runTarget 幂等） |
| POST | `/delayer/init` | 首次注册延迟任务默认配置（taskClass+taskTag+runTarget 幂等） |
| POST | `/contact/init` | 上传任务默认联系人信息 |

## 告警机制

`AlertProcessService` 接收主机上报的统计后，异步判定以下阈值（任一越限即生成告警）：

| 类型 | 含义 |
|---|---|
| `failRate` / `failProgramRate` / `failPartnerRate` / `failConfigRate` / `failDataRate` | 总/程序/接口/配置/数据 失败率（%） |
| `queueTimeout` / `waitTimeout` / `runTimeout` | 排队/限速等待/运行 平均耗时超限（ms） |
| `queueSize` | 队列堆积超限 |
| `cronerTimeOut` | 定时任务超过计划时间 5 分钟仍未执行 |
| `delayOvertime` | 延迟任务（`task_type=delayer`）实际执行晚于 runAt 的平均超时（ms）；延迟任务另判定 `failRate` / `failProgramRate` / `failPartnerRate` / `runTimeout` |

告警生成 → 落库 `task_alert_info` / `task_alert_notify` → `AlertNotifyScanCroner` 每 3 分钟扫描合并，经钉钉/notifyUrl 推送。

## 主机禁用与客户端降级

**主机禁用**（OPS）：OPS 将主机 state 置 0 → 客户端下次 `/host/report` 收到 state=0 → 停止所有任务（runner listener / croner 调度 / delayer poll），保留底层线程池以便恢复。解除禁用（state=1）→ 客户端重置配置时间戳 + 全量拉取重新启动，自动恢复（无需重启进程）。

**客户端降级**（center 故障）：uw-task 客户端在 center 不可用时，三套任务用 `initConfig` 本地默认配置继续运行；center 恢复后自动同步服务端动态配置（不丢任务/数据，详见客户端 [`README.md`](../../uw-base/uw-task/README.md)「center 不可用降级与恢复」）。

---

# uw-task 客户端框架使用文档

> 以下内容面向**业务应用开发者**：如何在应用中引入 uw-task 客户端、编写定时/队列任务。这部分配置发生在**执行主机**（业务应用）一侧，不是 uw-task-center 服务端的配置。
>
> ⚠️ 本节为历史简版。完整、最新的客户端文档（含延迟任务 TaskDelayer、限速常量全表、TaskFactory 全部入口）见 uw-task 包自带文档：[`backend/uw-base/uw-task/README.md`](../../uw-base/uw-task/README.md)。

## 简介

uw-task是一个分布式任务框架，通过uw-task可以快速构建分布式任务体系，支持定时任务和队列任务，同时支持任务运维监控和报警设置。

## 主要特性

1. 基于spring boot实现，依赖rabbitMQ,redis。
2. 完全分布式，支持混合云，可指定主机或指定集群运行。
3. 支持定时任务，并支持服务端动态配置。
4. 支持队列任务，支持各种流量控制，支持错误重试，并支持服务端动态配置。
5. 支持RPC风格调用，支持错误重试，并支持服务器端动态配置。
6. 支持多种规则的任务报警。

## maven引入

```
<dependency>
	<groupId>com.umtone</groupId>
	<artifactId>uw-task</artifactId>
	<version>${version}</version>
</dependency>
```

## 应用配置

直接使用spring boot的application.yml文件。

```yaml
#任务基础包名
uw:
  task:
    # 如果是任务执行主机，此配置应为true。
    enable-registry: true
    # 任务管理服务器地址
    task-center-host: http://localhost:8080
    # 任务项目，必须是包名前缀，用于扫描任务注册。
    task-project: com.demo.task
    # 运行目标，这是重要参数，用于识别任务执行集群，默认为default。
    run-target: default
    # croner线程数，默认在5个，建议按照实际croner任务数量*70%。
    croner-thread-num: 3
    # RPC最小线程数,用于执行RPC调用，如不使用rpc，建议设置为1，否则按照最大并发量*10%设置。
    task-rpc-min-thread-num: 1
    # RPC最大线程数,用于执行RPC调用，超过此线程数，将会导致阻塞。
    task-rpc-max-thread-num: 60
    # 本地队列任务运行最小线程数，用于运行本地队列任务，减少资源消耗。
    task-local-min-thread-num: 1
    # 本地队列任务运行最大线程数，用于运行本地队列任务，减少资源消耗。
    task-local-max-thread-num: 60
    # 队列任务重试延时毫秒数，默认2秒
    task-queue-retry-delay: 2000
    # rpc任务重试延时毫秒数，默认100毫秒
    task-rpc-retry-delay: 100
    
    # rabbitmq
    rabbitmq:
      host: 127.0.0.1
      port: 5672
      username: guest
      password: guest
      publisher-confirms: true
      virtual-host: /
  
    # redis 缓存
    redis:
      database: 0
      host: 127.0.0.1
      port: 6379
      password: password
      lettuce:
        pool:
          max-active: 20
          max-idle: 8
          max-wait: -1ms
          min-idle: 0
      timeout: 30s
```

## 定时任务配置

定时任务使用cron表达式来定时执行指定任务。

```
/**
 * 这是一个demo任务。
 * 定时任务需要继承TaskCroner。
 **/
@Component
public class DemoCronTask extends TaskCroner{

	/**
	 * 运行任务。
	 * 如果需要在日志中记录执行信息，请返回值中记录。
	 **/
	@Override
	public String runTask(TaskCronerLog taskCronerLog) throws TaskException {
		logger.info("just test for cron task!");
		return "";
	}

	/**
	 * 初始化定时任务配置。
	 * 在没有服务端配置的时候，默认使用此配置。
	 **/
	@Override
	public TaskCronerConfig initConfig() {
        TaskCronerConfig config = new TaskCronerConfig();
        config.setTaskName("测试定时任务");
        config.setTaskDesc("这是一个测试定时任务");
        //指定cron表达式
        config.setTaskCron("*/5 * * * * ?");
        //指定运行主机配置
        config.setRunTarget("");
        //指定运行模式
        config.setRunType(TaskCronerConfig.RUN_TYPE_SINGLETON);
        //报警信息：总失败率百分比数值
        config.setAlertFailRate(10);
        //程序失败率百分比数值
        config.setAlertFailProgramRate(10);
        //接口失败率百分比数值
        config.setAlertFailPartnerRate(10);
        //限速等待超时ms数
        config.setAlertWaitTimeout(10000);
        //运行超时ms数
        config.setAlertRunTimeout(1000);
        return config;
	}

	/**
	 * 初始化联系人信息。
	 * 用于在服务器端设置默认的报警通知信息。
	 */
	@Override
	public TaskContact initContact() {
		return new TaskContact("开发人员姓名", "手机号码", "邮箱地址", "微信", "im", “notifyUrl”，"备注");
	}

}

```

## 队列任务配置

- 实现TaskRunner接口的runTask方法。
- 对于接口异常，请抛出TaskException用于标识。
- 对于执行结果，通过返回值来设置。

```
@Component
public class DemoTask extends TaskRunner<DemoTaskParam, String> {

	private static final Logger log = LoggerFactory.getLogger(DemoTask.class);

	
	@Override
	public String runTask(TaskData<DemoTaskParam, String> taskData) throws TaskException {
		log.info("这是一个DemoTask:{},{},{}", taskData.getTaskParam().getId(), taskData.getTaskParam().getName(),
				taskData.getTaskParam().getDate());
		return "ok";
	}
	
	/**
	 * 初始化队列任务配置。
	 * 在没有服务端配置的时候，默认使用此配置。
	 **/
	@Override
	public TaskRunnerConfig initConfig() {
		TaskRunnerConfig config = new TaskRunnerConfig();
		config.setTaskName("测试队列任务");
		config.setTaskDesc("测试队列任务描述");
		//设定限速类型
		config.setRateLimitType(TaskRunnerConfig.RATE_LIMIT_TYPE_NONE);
		//设定限速秒数
		config.setRateLimitTime(1);
		//设定限速次数
		config.setRateLimitValue(10);
		//设定限速超时等待时间
		config.setRateLimitWait(60);
		//设定因为对方接口错，重试的次数，默认为0
		config.setRetryTimesByPartner(0);
		//设定因为超过限速的错误，重试的次数，默认为0
		config.setRetryTimesByOverrated(0);
		//并发数，会开启几个并发进程处理任务。
		config.setConsumerNum(5);
		//预取数，大批量任务可以设置为5，一般任务建议设置为1
		config.setPrefetchNum(1);
		//总失败率百分比数值
		config.setAlertFailRate(10);
		//程序失败率百分比数值
		config.setAlertFailProgramRate(10);
	    //接口失败率百分比数值
		config.setAlertFailPartnerRate(10);
		//程序失败率百分比数值
		config.setAlertFailConfigRate(10);
		//队列排队超
		config.setAlertQueueOversize(1000);
		//队列排队超时ms数
		config.setAlertQueueTimeout(600000);
		//限速等待超时ms数
		config.setAlertWaitTimeout(10000);
		//运行超时ms数
		config.setAlertRunTimeout(1000);
		
		return config;
	}

	/**
	 * 初始化联系人信息。
	 * 用于在服务器端设置默认的报警通知信息。
	 */
	@Override
	public TaskContact initContact() {
		return new TaskContact("开发人员姓名", "手机号码", "邮箱地址", "微信", "im", “notifyUrl”，"备注");
	}
}

```

## 延迟任务配置（TaskDelayer）

延迟任务（TaskDelayer）是与 TaskCroner/TaskRunner 对等的第三种任务类型，到点后触发执行，基于 **Redis zset 多实例竞争 poll** 实现（不依赖 RabbitMQ、无队头阻塞、长延时不阻塞短延时、重启不丢存量）。适用于订单超时关闭、定时提醒、重试回调等"X 时间后执行"的场景。

```
@Component
public class DemoDelayTask extends TaskDelayer<DemoTaskParam, Void> {

	/**
	 * 运行延迟任务。
	 **/
	@Override
	public void run(TaskData<DemoTaskParam, Void> task) throws TaskException {
		logger.info("demo delay task: {}", task.getTaskParam().getId());
	}

	/**
	 * 初始化延迟任务配置。
	 * 在没有服务端配置的时候，默认使用此配置。
	 **/
	@Override
	public TaskDelayerConfig initConfig() {
		TaskDelayerConfig config = new TaskDelayerConfig();
		config.setTaskName("测试延迟任务");
		config.setTaskDesc("这是一个测试延迟任务");
		//执行并发数（虚拟线程+Semaphore）
		config.setConsumerNum(5);
		//poll间隔秒数，命中连续poll、空才sleep
		config.setPollInterval(3);
		//单次poll最大条数
		config.setPrefetchNum(50);
		//合作方异常重试次数
		config.setRetryTimesByPartner(3);
		//总失败率百分比数值
		config.setAlertFailRate(10);
		//运行超时ms数
		config.setAlertRunTimeout(1000);
		//延迟超时ms数（实际执行晚于runAt，Delayer特有）
		config.setAlertWaitTimeout(5000);
		return config;
	}

	/**
	 * 初始化联系人信息。
	 * 用于在服务器端设置默认的报警通知信息。
	 */
	@Override
	public TaskContact initContact() {
		return new TaskContact("开发人员姓名", "手机号码", "邮箱地址", "微信", "im", "notifyUrl", "备注");
	}
}
```

投递延迟任务使用 `TaskFactory.delayTask(taskData)`，`taskDelay` 为延迟毫秒数（框架据此计算 zset 到期 score = `queueDate + taskDelay`）：

```
TaskData<DemoTaskParam, Void> taskData = TaskData.<DemoTaskParam, Void>builder(DemoDelayTask.class)
		.taskParam(new DemoTaskParam(1))
		.taskDelay(5000)   //5秒后执行
		.build();
taskFactory.delayTask(taskData);
```

## 任务内异常处理

-
为了更好的支持监控，runTask的异常主要分为3类。分别是：TaskDataException数据异常；TaskPartnerException合作方异常；其他程序异常。其中限速超时异常由框架自动生成。程序异常、数据异常、接口方异常需要程序员来维护。
- 一般来说，尽量不要捕获异常，除非这个异常捕获后不影响任务的完整执行。不捕获的异常，框架会自动捕获为程序异常。
- 接口方异常为TaskPartnerException，此异常需要程序员手工抛出。一般http超时，返回码错误（非200）肯定要抛接口方异常的，其他可能为接口方异常的，可自行决断抛出。

## 队列任务发布

1. 定义TaskFactory

```
@Autowired
private TaskFactory taskFactory;
```

2. 发送任务到队列，此操作为完全异步操作。

```
/**
 * 把任务发送到队列中。
 * 
 * @param target
 *            目标主机配置名，如果没有，则为空
 * @param taskData
 */
public void sendToQueue(TaskData<?, ?> taskData);
```

3. 本地运行队列。为了优化实时性高，且频繁的队列任务，可以优先在本地执行，减少**mq**压力，提升运行效率。当本地执行线程池满的时候，会直接转到队列执行。

```
    /**
     * 本地运行队列。
     * 为了优化实时性高，且频繁的队列任务，可以优先在本地执行，减少mq压力，提升运行效率。
     * 当本地执行线程池满的时候，会直接转到队列执行。
     *
     * @param taskData 任务数据
     */
    public void runQueue(final TaskData<?, ?> taskData) {
        taskQueueService.submit(new TaskQueueLocalExecutor(this, taskData));
    }
```

4. 本地执行任务。没有线程池支持，会导致阻塞。

```
    /**
     * 同步执行任务，没有线程池支持，会导致阻塞。
     * 在调用的时候，尤其要注意，taskData对象不可改变！
     *
     * @param taskData 任务数据
     * @return
     */
    @SuppressWarnings("unchecked")
    public <TP, RD> TaskData<TP, RD> runTaskLocal(final TaskData<TP, RD> taskData) {
        taskData.setId(globalSequenceManager.nextId("task_runner_log"));
        taskData.setQueueDate(SystemClock.nowDate());
        // 当自动RPC，并且本地有runner，而且target匹配的时候，运行在本地模式下。
        if (taskData.getRunType() == TaskData.RUN_TYPE_AUTO_RPC && TaskMetaInfoManager.checkRunnerRunLocal(taskData)) {
            // 启动本地运行模式。
            taskData.setRunType(TaskData.RUN_TYPE_LOCAL);
        }
        if (taskData.getRunType() == TaskData.RUN_TYPE_LOCAL) {
            taskRunnerContainer.process(taskData);
            return taskData;
        } else {
            throw new TaskRuntimeException(taskData.getClass().getName() + " is not a local task! ");
        }
    }
```

5. 同步执行任务
   运行期，程序根据runType判断，并结合任务代码是否在本地的判定，决定是运行在本地还是远程。
   一般来说，程序的默认runType=RUN_TYPE_AUTO_RPC，此时是自动判定默认。
   也可以指定RUN_TYPE_GLOBAL_RPC和RUN_TYPE_LOCAL。

```
	/**
	 * 同步执行任务，可能会导致阻塞。
	 *
	 * @param runTarget
	 *            目标主机配置名，如果没有，则为空
	 * @param taskData
	 *            任务数据
	 * @return
	 */
	public <TP, RD> TaskData<TP, RD> runTask(final TaskData<TP, RD> taskData,final TypeReference<TaskData<TP, RD>> typeRef);
```

6. 异步执行任务

```
	/**
	 * 远程运行任务，并返回future<TaskData<?,?>>。 如果需要获取数据，可以使用futrue.get()来获取。
	 * 此方法要谨慎使用，因为task存在限速，大并发下可能会导致线程数超。
	 * @param runTarget
	 *            目标主机配置名，如果没有，则为空
	 * @param taskData
	 *            任务数据
	 * @return
	 */
	public <TP, RD> Future<TaskData<TP, RD>> runTaskAsync(final TaskData<TP, RD> taskData,
			final TypeReference<TaskData<TP, RD>> typeRef);
```

## TaskData说明

TaskData是分发任务传递参数和返回

```
/**
 * TaskData用于任务执行的传值，以为任务完成后返回结构。
 * TaskParam和ResultData可通过泛型参数制定具体类型。
 * TP,TD应和TaskRunner的泛型参数完全一致，否则会导致运行时出错。
 *
 */
public class TaskData<TP,TD> implements Serializable {

    /**
     * 任务状态:未设置
     */
    public static final int STATUS_UNKNOW = 0;
    
    /**
     * 任务状态:成功
     */
    public static final int STATUS_SUCCESS = 1;
    
    /**
     * 任务状态:程序错误
     */
    public static final int STATUS_FAIL_PROGRAM = 2;
    
    /**
     * 任务状态:配置错误，如超过流量限制
     */
    public static final int STATUS_FAIL_CONFIG = 3;
    
    /**
     * 任务状态:第三方接口错误
     */
    public static final int STATUS_FAIL_PARTNER = 4;
    
    /**
     * 运行模式：本地运行
     */
    public static final int RUN_TYPE_LOCAL = 1;
    
    /**
     * 运行模式：全局运行
     */
    public static final int RUN_TYPE_GLOBAL = 3;	
    
    /**
     * 运行模式：全局运行RPC返回结果
     */
    public static final int RUN_TYPE_GLOBAL_RPC = 5;	

    /**
     * id，此序列值由框架自动生成，无需手工设置。
     */
    private long id;
    
    /**
     * 关联TAG，由调用方设定，用于第三方统计信息。
     */
    private String refTag;
    
    /**
     * 任务延迟毫秒数。一般这个时间不宜太长，大多数情况下不要超过60秒。
     */
    private long taskDelay;
    
    /**
     * 关联id，由调用方根据需要设置，用于第三方统计信息。
     */
    private long refId;

    /**
     * 关联子id，由调用方根据需要设置，用于第三方统计信息。
     */
    private long refSubId;

    /**
     * 关联对象，此对象不存入数据库，但可以通过Listener来访问。
     */
    private Object refObject;

    /**
     * 流量限制TAG。
     */
    private String rateLimitTag;
    
    /**
     * 需要执行的类名，此数值必须由调用方设置。
     */
    private String taskClass = "";

    /**
     * 任务标签，用于细分任务队列，支持多实例运行。
     */
    private String taskTag = "";
    
    /**
     * 执行参数，此数值必须有调用方设置。
     */
    private TP taskParam;

    /**
     * 任务运行类型。由框架设置，无需手工设置。
     */
    private int runType;

    /**
     * 指定运行目标。
     */
    private String runTarget = "";

    /**
     * 任务运行时主机IP，此信息由框架自动设置。
     */
    private String hostIp;

    /**
     * 任务运行时主机ID（可能为docker的ContainerID），此信息由框架自动设置。
     */
    private String hostId;

    /**
     * 进入队列时间，此信息由框架自动设置。
     */
    private Date queueDate;

    /**
     * 开始消费时间，此信息由框架自动设置。
     */
    private Date consumeDate;

    /**
     * 开始运行时间，此信息由框架自动设置。
     */
    private Date runDate;

    /**
     * 运行结束日期，此信息由框架自动设置。
     */
    private Date finishDate;

    /**
     * 执行信息，用于存储框架自动设置。
     */
    private RD resultData;

    /**
     * 出错信息
     */
    private String errorInfo;

    /**
     * 已经执行的次数，此信息由框架自动设置。
     */
    private int ranTimes;

    /**
     * 执行状态，此信息由框架自动设置。
     */
    private int status;


}

```

## 流量控制功能

在TaskRunnerConfig中定义了以下几种流量限制方式。对于全局限速器，建议检测时间不要低于5S。

```
	/**
	 * 限速类型：不限速
	 */
	public static final int RATE_LIMIT_TYPE_NONE = 0;
	
	/**
	 * 限速类型：基于当前进程的限速，所有任务共用一个进程限速器。
	 */
	public static final int RATE_LIMIT_TYPE_PROCESS = 1;
	
	/**
	 * 限速类型：基于当前主机IP限速，所有任务共用一个IP的限速器。
	 */
	public static final int RATE_LIMIT_TYPE_IP = 2;

	/**
	 * 限速类型：基于TaskData的RateLimitTag（推荐设定为接口配置ID）限速
	 */
	public static final int RATE_LIMIT_TYPE_TAG = 3;
	
	/**
	 * 限速类型：基于TaskName限速，基于当前任务限速。
	 */
	public static final int RATE_LIMIT_TYPE_TASK = 5;
	
	/**
	 * 限速类型：进程内基于当前任务的限速
	 */
	public static final int RATE_LIMIT_TYPE_TASK_PROCESS = 6;

	/**
	 * 限速类型：基于当前任务的当前主机IP限速
	 */
	public static final int RATE_LIMIT_TYPE_TASK_IP = 7;

	/**
	 * 限速类型：根据当前任务的TaskData的RateLimitTag（推荐设定为接口配置ID）限速
	 */
	public static final int RATE_LIMIT_TYPE_TASK_TAG = 8;
	
	/**
	 * 限速类型：根据当前IP和进程限速
	 */
	public static final int RATE_LIMIT_TYPE_TAG_PROCESS = 9;
	
	/**
	 * 限速类型：根据当前IP和TaskData的RateLimitTag（推荐设定为接口配置ID）限速
	 */
	public static final int RATE_LIMIT_TYPE_TAG_IP = 10;

```

流量限制的参数设定：

```
    /**
     * 详见流量限制类型说明。
     */
    private int rateLimitType = 1;

    /**
     * 流量限定数值，默认为10次
     */
    private int rateLimitValue = 10;

    /**
     * 流量限定时间(S)，默认为1秒
     */
    private int rateLimitTime = 1;

    /**
     * 当发生流量限制时，等待的秒数，默认60秒
     */
    private int rateLimitWait = 60;
    
    /**
     * 超过流量限制重试次数，默认不再重试，放弃任务。
     */
    private int retryTimesByOverrated = 0;

```

## 多实例运行

- 有时候会存在一套定时任务/队列任务，存在多个并发运行任务实例的情况。此功能需要经过服务器端多配置来实现。
- TaskCroner通过配置中的TaskParam参数来指标识多实例。在实际使用中，可以使用空值（默认值）来处理绝大多数请求，特定用户使用特定ID作为TaskParam数值。
- TaskRunner通过TaskData/TaskRunnerConfig中的TaskTag来标识多实例。发送任务的时候，就需要指定TaskTag/RunTarget。
- 如果指定的TaskRunner的TaskTag&RunTarget无法匹配到指定服务器端配置，框架会宽松匹配最合适的配置。

## 运行目标RunTarget

- 运行目标是非常重要的参数，它和taskProject一起决定了任务配置的同步联系。

- 任务可以指定运行目标，此运行目标通过服务器端配置来实现。

## uw-dao 无数据语义与判定规范（重要）

uw-task-center 大量使用 `uw.dao.DaoManager`（`dao.load` / `dao.queryForObject` / `dao.list` / `dao.execute` / `dao.save` / `dao.update`）与数据库交互。**理解这些方法在"无数据"时的返回语义，是正确编写注册/上报/查重逻辑的前提**——本中心曾因误判多次导致任务无法注册（id 恒为 0）、主机累计统计清零等回归。

### 无数据时的返回值（源自 `DaoManager` 源码）

| 方法 | 无数据 / 0 行时 | 说明 |
|---|---|---|
| `dao.load(Class, id)` | **`warn`**（`code=uw.dao.data.not.found.warn`，data=null） | 主键查不到 |
| `dao.queryForObject(...)` | **`warn`**（data=null） | 单条查不到 |
| `dao.list(...)` | **`success`**（空 `PageList`，非 null） | 列表为空仍是成功 |
| `dao.execute(update/delete SQL)` 影响行数 < 1 | **`warn`**（data=effectedNum） | 0 行更新/删除 |
| `dao.save(entity)` / `dao.update(entity)` 影响行数 < 1 | **`warn`** | 写入未生效 |

根因：`DaoManager.responseData(null)` 对 null 统一返回 `warnCode(DATA_NOT_FOUND_WARN)`；所有写操作在 `effectedNum < 1` 时也返回该 warn。

### 判定方法选择（强制）

`ResponseData.isNotSuccess()` 对 **warn 和 error 都为 true**。因此：

| 业务语义 | 判定方法 | 典型场景 |
|---|---|---|
| "查到走 A，查不到走 B（新建/判无重复）" | **`isError()`**（仅 error 才中断） | `initRunnerConfig` / `initCronerConfig` / `initTaskContact` 去重查询、`report` 的 `load` 主机 |
| "查不到=告知调用方数据不存在" | `isNotSuccess()` 或链式 `onSuccess` | OPS 详情查询、改单前 `load`、`checkDuplicate` 的返回值 |
| 写操作"必须成功否则中止" | `isNotSuccess()` | `save` / `update` 落库后需中断的场景 |
| 列表查询 | `isNotSuccess()` | `list` 空结果本就是 success，安全 |

> **关键陷阱**：在"查无数据需要新建"的分支用 `isNotSuccess()`，warn 会被误判为失败直接 return，**新建分支永远走不到**——首次注册的任务/联系人/主机永远建不进库，客户端拿到 id=0。详见 `TaskRpcController` 各 `init*` 方法注释。

### 本中心的正确范例

- RPC 注册去重（`/runner/init`、`/croner/init`、`/contact/init`）：用 `dao.queryForObject(...).isError()` 判中断，warn 时 `getData()==null` 继续走新建。
- 主机上报（`/host/report`）：`dao.load` 用 `isError()` 判中断；update 的 WHERE **仅 `id=?`**（易变字段如 `app_version` 放 SET，否则发版漂移致 0 行→误 insert→同主机多条记录、累计清零）。
- OPS 查重（`checkDuplicate`）：方法返回值用 `warn` 表达"已存在重复"，调用方用 `isNotSuccess()` 拒绝。

---

## 常见问题

**任务不能注册，无法启动任务。**

1.任务上有没有设置@Componet注解？uw-task的任务通过Spring的@Componet注解扫描识别并注入系统。

**尼玛队列堵成狗，队列任务根本就没按照限速执行。**

是不是不看文档？限速类型设定为“进程内限速”了？这样所有的任务会共用一个限速器，不卡死你才怪。认真阅读文档，选择合理的限速类型！
 
 **关于uw-task的延时队列任务**
 uw-task 现提供专用的延迟任务类型 TaskDelayer（基于 Redis zset，到期即取即执行，无队头阻塞、重启不丢存量），推荐优先使用 `TaskFactory.delayTask()` 投递延迟任务（详见 uw-task 客户端 README 的「延迟任务（TaskDelayer）」章节）。
 队列任务（TaskRunner）的 MQ 延时（delayType=ON，基于 RabbitMQ 死信）仍保留兼容，但存在长延时阻塞短延时问题，仅建议短延时（≤60秒）且量小的场景使用。延迟任务请用 TaskDelayer，小负载也可直接轮询数据库，均可有效降低资源消耗。