package uw.task.center.util;

import uw.task.entity.TaskContact;

/**
 * 联系人工具类。
 */
public class ContactUtils {

    private static final TaskContact AXEON = new TaskContact("axeon", "13800138000", "", "", "", "", "");

    /**
     * 获得任务联系人信息。
     */
    public static TaskContact getTaskContact() {
        return AXEON;
    }
}
