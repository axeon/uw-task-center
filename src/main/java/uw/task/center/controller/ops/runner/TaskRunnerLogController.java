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
import uw.dao.DaoManager;
import uw.dao.PageQueryParam;
import uw.dao.vo.QueryParamResult;
import uw.log.es.LogClient;
import uw.log.es.vo.ESDataList;
import uw.log.es.vo.SearchResponse;
import uw.task.center.dto.TaskRunnerEsLogQueryParam;
import uw.task.center.entity.TaskRunnerEsLog;

/**
 * 队列任务日志表：增删改查
 */
@RestController
@RequestMapping("/ops/runner/log")
@Tag(name = "队列任务日志", description = "队列任务日志")
@MscPermDeclare(user = UserType.OPS)
public class TaskRunnerLogController {

    private static final Logger log = LoggerFactory.getLogger( TaskRunnerLogController.class );
    private static final String INDEX_NAME = "uw.task.runner.log";
    private final DaoManager dao = DaoManager.getInstance();
    private final LogClient logClient;

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
    public ESDataList<TaskRunnerEsLog> list(TaskRunnerEsLogQueryParam queryParam) throws Exception {
        AuthServiceHelper.logRef( TaskRunnerEsLog.class );
        //钉死关键参数
        queryParam.SORT_NAME( "@timestamp" );
        queryParam.SORT_TYPE( PageQueryParam.SORT_DESC );

        QueryParamResult result = dao.parseQueryParam( TaskRunnerEsLog.class, queryParam );

        String dsl = logClient.translateSqlToDsl( result.genFullSql(), queryParam.START_INDEX(), queryParam.RESULT_NUM(), queryParam.CHECK_AUTO_COUNT() );
        return logClient.mapQueryResponseToEDataList( logClient.dslQuery( TaskRunnerEsLog.class, INDEX_NAME, dsl ), queryParam.START_INDEX(), queryParam.RESULT_NUM() );
    }

    /**
     * 查询队列任务日志
     */
    @GetMapping("/load")
    @Operation(summary = "查询队列任务日志", description = "查询队列任务日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public TaskRunnerEsLog load(@Parameter(description = "主键") long id) throws Exception {
        AuthServiceHelper.logRef( TaskRunnerEsLog.class, id );
        String dsl = logClient.translateSqlToDsl( "select * from \\\"" + INDEX_NAME + "\\\" where id=" + id, 0, 1, false );
        SearchResponse<TaskRunnerEsLog> response = logClient.dslQuery( TaskRunnerEsLog.class, INDEX_NAME, dsl );
        if (response == null) {
            return null;
        }
        SearchResponse.HitsResponse<TaskRunnerEsLog> hisResponse = response.getHitsResponse();
        return hisResponse.getHits().getFirst().getSource();
    }


}
