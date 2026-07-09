package uw.task.center.controller.ops.delayer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.AuthServiceHelper;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.common.data.PageList;
import uw.common.dto.QueryParam;
import uw.dao.DaoManager;
import uw.dao.vo.QueryParamResult;
import uw.log.es.LogClient;
import uw.task.center.dto.TaskDelayerEsLogQueryParam;
import uw.task.center.entity.TaskDelayerEsLog;

/**
 * 延迟任务执行日志检索接口（查 ES 索引 uw.task.delayer.log）。
 *
 * @author axeon
 */
@RestController
@RequestMapping("/ops/delayer/log")
@Tag(name = "延迟任务日志", description = "延迟任务日志")
@MscPermDeclare(user = UserType.OPS)
public class TaskDelayerLogController {

    private static final Logger log = LoggerFactory.getLogger(TaskDelayerLogController.class);

    /**
     * ES 索引名（与客户端 TaskDelayerLog 写入的索引一致）。
     */
    private final DaoManager dao = DaoManager.getInstance();

    private final LogClient logClient;

    @Autowired
    public TaskDelayerLogController(final LogClient logClient) {
        this.logClient = logClient;
    }

    /**
     * 列表延迟任务日志（QueryParam → DSL → ES 查询 → PageList）。
     */
    @GetMapping("/list")
    @Operation(summary = "列表延迟任务日志", description = "列表延迟任务日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public PageList<TaskDelayerEsLog> list(TaskDelayerEsLogQueryParam queryParam) throws Exception {
        AuthServiceHelper.logRef(TaskDelayerEsLog.class);
        // 强制按时间倒序，最近的日志优先
        queryParam.CLEAR_SORT().ADD_SORT("timestamp", QueryParam.SORT_DESC);
        QueryParamResult result = dao.parseQueryParam(TaskDelayerEsLog.class, queryParam);
        String dsl = logClient.translateSqlToDsl(result.genFullSql(), queryParam.START_INDEX(), queryParam.RESULT_NUM(), queryParam.CHECK_AUTO_COUNT());
        String loginLogIndex = logClient.getQueryIndexName(TaskDelayerEsLog.class);
        return LogClient.mapQueryResponseToPageList(logClient.dslQuery(TaskDelayerEsLog.class, loginLogIndex, dsl), queryParam.START_INDEX(), queryParam.RESULT_NUM());
    }
}
