package uw.task.center.controller.ops.runner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import uw.dao.DaoFactory;
import uw.dao.vo.QueryParamResult;
import uw.log.es.LogClient;
import uw.log.es.vo.ESDataList;
import uw.log.es.vo.SearchResponse;
import uw.task.center.dto.TaskRunnerLogQueryParam;
import uw.task.center.entity.TaskRunnerESLog;

/**
 * 队列任务日志表：增删改查
 */
@RestController
@RequestMapping("/ops/runner/log")
@Tag(name = "队列任务日志", description = "队列任务日志")
@MscPermDeclare(user = UserType.OPS)
public class TaskRunnerLogController {

    private static final Logger log = LoggerFactory.getLogger( TaskRunnerLogController.class );
    private DaoFactory dao = DaoFactory.getInstance();
    private LogClient logClient;

    @Autowired
    public TaskRunnerLogController(final LogClient logClient) {
        this.logClient = logClient;
    }


    /**
     * 列表队列任务日志
     */
    @GetMapping("/list")
    @Operation(summary = "列表队列任务日志", description = "列表队列任务日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public ESDataList<TaskRunnerESLog> list(TaskRunnerLogQueryParam queryParam) throws Exception {
        AuthServiceHelper.logRef( TaskRunnerESLog.class );
        QueryParamResult result = dao.parseQueryParam( TaskRunnerESLog.class, queryParam );
        String dsl = logClient.translateSqlToDsl( result.genFullSql(), queryParam.GET_START_INDEX(), queryParam.GET_RESULT_NUM(), queryParam.CHECK_AUTO_COUNT() );
        return logClient.mapQueryResponseToEDataList( logClient.dslQuery( TaskRunnerESLog.class, "uw.task.entity.task_runner_log_*", dsl ), queryParam.GET_START_INDEX(),
                queryParam.GET_RESULT_NUM() );
    }

    /**
     * 查询队列任务日志
     */
    @GetMapping(value = "/load")
    @Operation(summary = "查询队列任务日志", description = "查询队列任务日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public TaskRunnerESLog load(@Parameter(description = "主键", example = "1") long id) throws Exception {
        AuthServiceHelper.logRef( TaskRunnerESLog.class, id );
        String dsl = logClient.translateSqlToDsl( "select * from \\\"uw.task.entity.task_runner_log_*\\\" where id = " + id, 0, 1, false );
        SearchResponse<TaskRunnerESLog> response = logClient.dslQuery( TaskRunnerESLog.class, "uw.task.entity.task_runner_log_*", dsl );
        if (response == null) {
            return null;
        }
        SearchResponse.HitsResponse<TaskRunnerESLog> hisResponse = response.getHitsResponse();
        if (hisResponse.getHits().size() == 0) {
            return null;
        }
        return hisResponse.getHits().get( 0 ).getSource();
    }


}
