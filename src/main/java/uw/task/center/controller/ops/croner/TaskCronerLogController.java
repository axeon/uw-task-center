package uw.task.center.controller.ops.croner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.ActionLog;
import uw.auth.service.constant.AuthType;
import uw.auth.service.constant.UserType;
import uw.dao.DaoFactory;
import uw.dao.vo.QueryParamResult;
import uw.log.es.LogClient;
import uw.log.es.vo.ESDataList;
import uw.log.es.vo.SearchResponse;
import uw.task.center.dto.TaskCronerLogQueryParam;
import uw.task.center.entity.TaskCronerESLog;

/**
 * 定时任务日志表：增删改查
 */
@RestController
@Tag(name = "定时任务日志")
@RequestMapping("/ops/croner/log")
@MscPermDeclare(type = UserType.OPS)
public class TaskCronerLogController {
    private static final Logger log = LoggerFactory.getLogger(TaskCronerLogController.class);

    private DaoFactory dao = DaoFactory.getInstance();

    private LogClient logClient;

    @Autowired
    public TaskCronerLogController(final LogClient logClient) {
        this.logClient = logClient;
    }

    /**
     * 查看定时任务日志
     */
    @Operation(summary = "查看定时任务日志", description = "查看定时任务日志")
    @GetMapping(value = "/load")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.REQUEST)
    public TaskCronerESLog load(@Parameter(description = "主键", example = "1") long id) throws Exception {
        String dsl = logClient.translateSqlToDsl(
                "select * from \\\"uw.task.entity.task_croner_log_*\\\" where id = " + id, 0, 1, false);
        SearchResponse<TaskCronerESLog> response = logClient.dslQuery(TaskCronerESLog.class, "uw.task.entity.task_croner_log_*", dsl);
        if (response == null) {
            return null;
        }
        SearchResponse.HitsResponse<TaskCronerESLog> hisResponse =
                response.getHitsResponse();
        return hisResponse.getHits().get(0).getSource();
    }

    /**
     * 列表定时任务日志
     */
    @Operation(summary = "列表定时任务日志", description = "列表定时任务日志")
    @GetMapping("/list")
    @MscPermDeclare(type = UserType.OPS, auth = AuthType.PERM,log = ActionLog.REQUEST)
    public ESDataList<TaskCronerESLog> list(TaskCronerLogQueryParam queryParam) throws Exception {
        QueryParamResult result = dao.parseQueryParam(TaskCronerESLog.class, queryParam);
        String dsl = logClient.translateSqlToDsl(result.genFullSql(), queryParam.GET_START_INDEX(), queryParam.GET_RESULT_NUM(), queryParam.CHECK_AUTO_COUNT());
        return logClient.mapQueryResponseToEDataList(logClient.dslQuery(TaskCronerESLog.class, "uw.task.entity.task_croner_log_*", dsl), queryParam.GET_START_INDEX(), queryParam.GET_RESULT_NUM());
    }

}
