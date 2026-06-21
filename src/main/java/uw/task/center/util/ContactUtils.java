package uw.task.center.util;

import uw.task.entity.TaskContact;

/**
 * 联系人工具类。
 *
 * <p>提供任务中心内部定时任务默认使用的联系人信息。</p>
 *
 * @author axeon
 */
public class ContactUtils {

    /**
     * 默认联系人（开发者），供任务中心内部 croner 使用。
     */
    private static final TaskContact AXEON = new TaskContact("axeon", "13800138000", "", "", "", "", "");

    /**
     * 获取默认任务联系人信息。
     *
     * @return 默认联系人
     */
    public static TaskContact getTaskContact() {
        return AXEON;
    }
}
