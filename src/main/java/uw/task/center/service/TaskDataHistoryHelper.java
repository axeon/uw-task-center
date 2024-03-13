package uw.task.center.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uw.auth.service.AuthServiceHelper;
import uw.dao.*;
import uw.httpclient.http.ObjectMapper;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.task.center.dto.TaskDataHistoryQueryParam;
import uw.task.center.entity.TaskDataHistory;
import uw.httpclient.exception.DataMapperException;

import java.util.Date;


/**
 * Saas公用的历史记录存储Helper。
 * 注意：此方法中直接使用dao是为了提升效率。
 */
public class TaskDataHistoryHelper {

    private static final Logger log = LoggerFactory.getLogger(TaskDataHistoryHelper.class);

    private static final DaoFactory dao = DaoFactory.getInstance();

    private static final ObjectMapper mapper = JsonInterfaceHelper.JSON_CONVERTER;

    /**
     * 获得历史记录列表。
     *
     * @return
     */
    public static DataList<TaskDataHistory> getHistoryList(TaskDataHistoryQueryParam queryParam) throws TransactionException {
        return dao.list(TaskDataHistory.class, queryParam);
    }

    /**
     * 保存历史记录。
     *
     * @param entityId
     * @param entityData
     */
    public static void saveHistory(long entityId, Object entityData) {
        TaskDataHistory history = new TaskDataHistory();
        history.setId( dao.getSequenceId(TaskDataHistory.class));
        history.setEntityId(entityId);
        history.setEntityClass(entityData.getClass().getSimpleName());
        if (AuthServiceHelper.getContextToken() != null) {
            history.setSaasId( AuthServiceHelper.getSaasId() );
            history.setMchId( AuthServiceHelper.getMchId() );
            history.setUserId( AuthServiceHelper.getUserId() );
            history.setUserType( AuthServiceHelper.getUserType() );
            history.setGroupId( AuthServiceHelper.getGroupId() );
            history.setUserName( AuthServiceHelper.getUserName() );
            history.setNickName( AuthServiceHelper.getNickName() );
            history.setRealName( AuthServiceHelper.getRealName() );
            history.setUserIp( AuthServiceHelper.getRemoteIp() );
        }
        history.setCreateDate(new Date());
        try {
            history.setEntityData(mapper.toString(entityData));
            if (entityData instanceof DataEntity de) {
                history.setEntityUpdateInfo(de.GET_UPDATED_INFO());
            }
        } catch (DataMapperException e) {
            log.error(e.getMessage(), e);
        }
        history.setCreateDate(new Date());
        try {
            dao.save(history);
        } catch (TransactionException e) {
            log.error(e.getMessage(), e);
        }
    }


}
