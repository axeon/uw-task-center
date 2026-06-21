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
     * <p>手机号留空：原硬编码 13800138000 在生产未覆盖配置时会向该号码误发告警。
     * 实际通知走钉钉 notifyUrl（由 TaskCenterProperties.alertDing 配置），此处仅占位。</p>
     */
    private static final TaskContact AXEON = new TaskContact("axeon", "", "", "", "", "", "");

    /**
     * 获取默认任务联系人信息。
     *
     * @return 默认联系人
     */
    public static TaskContact getTaskContact() {
        return AXEON;
    }
}
