package uw.task.center.util;

import uw.task.entity.TaskContact;

/**
 * 联系人工具类。
 */
public class ContactUtils {

    private static TaskContact contact;

    static {
        //此处填入联系人信息，程序第一次启动后自动注册。
        contact = new TaskContact("axeon", "13800138000", "", "", "", "", "");
    }

    /**
     * 获得任务联系人信息。
     */
    public static TaskContact getTaskContact() {
        return contact;
    }
}
