package uw.task.center.controller.ops.croner;

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
import uw.dao.DaoManager;
import uw.dao.vo.QueryParamResult;
import uw.log.es.LogClient;
import uw.common.data.PageList;
import uw.common.dto.PageQueryParam;
import uw.task.center.dto.TaskCronerEsLogQueryParam;
import uw.task.center.entity.TaskCronerEsLog;

/**
 * 定时任务运行日志查询接口。
 *
 * <p>日志数据来源于 ES（索引 {@value #INDEX_NAME}），由任务执行主机通过 logback-es 异步写入。
 * 本接口仅提供按条件检索，不支持写入。</p>
 *
 * @author axeon
 */
@RestController
@RequestMapping("/ops/croner/log")
@Tag(name = "定时任务日志")
@MscPermDeclare(user = UserType.OPS)
public class TaskCronerLogController {

    private static final Logger log = LoggerFactory.getLogger( TaskCronerLogController.class );
    /**
     * 定时任务日志在 ES 中的索引名。
     */
    private static final String INDEX_NAME = "uw.task.croner.log";
    private final DaoManager dao = DaoManager.getInstance();
    /**
     * ES 日志客户端。
     */
    private final LogClient logClient;

    /**
     * @param logClient ES 日志客户端
     */
    @Autowired
    public TaskCronerLogController(final LogClient logClient) {
        this.logClient = logClient;
    }

    /**
     * 分页查询定时任务运行日志。
     *
     * <p>强制按 {@code @timestamp} 倒序，将查询参数翻译为 ES DSL 后执行检索。</p>
     *
     * @param queryParam 查询参数（含分页、排序、过滤条件）
     * @return 命中的日志分页列表
     * @throws Exception DSL 翻译或 ES 查询失败时抛出
     */
    @GetMapping("/list")
    @Operation(summary = "列表定时任务日志", description = "列表定时任务日志")
    @MscPermDeclare(user = UserType.OPS, auth = AuthType.PERM, log = ActionLog.REQUEST)
    public PageList<TaskCronerEsLog> list(TaskCronerEsLogQueryParam queryParam) throws Exception {
        AuthServiceHelper.logRef( TaskCronerEsLog.class );
        //钉死关键参数
        queryParam.SORT_NAME( "@timestamp" );
        queryParam.SORT_TYPE( PageQueryParam.SORT_DESC );
        QueryParamResult result = dao.parseQueryParam( TaskCronerEsLog.class, queryParam );
        String dsl = logClient.translateSqlToDsl( result.genFullSql(), queryParam.START_INDEX(), queryParam.RESULT_NUM(), queryParam.CHECK_AUTO_COUNT() );
        return logClient.mapQueryResponseToPageList( logClient.dslQuery( TaskCronerEsLog.class, INDEX_NAME, dsl ), queryParam.START_INDEX(), queryParam.RESULT_NUM() );
    }

}
