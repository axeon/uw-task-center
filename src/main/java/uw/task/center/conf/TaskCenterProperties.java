package uw.task.center.conf;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 任务配置类。
 *
 * @author axeon
 */
@ConfigurationProperties(prefix = "uw.task.center")
public class TaskCenterProperties{

    /**
     * 系统名称。
     */
    private String centerName = "任务管理中心";

    /**
     * 报警的钉钉通知。
     */
    private DingConfig alertDing = new DingConfig("","TASK");

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public DingConfig getAlertDing() {
        return alertDing;
    }

    public void setAlertDing(DingConfig alertDing) {
        this.alertDing = alertDing;
    }

    /**
     * 钉钉通知配置。
     */
    public static class DingConfig {

        /**
         * 通知地址。
         */
        private String notifyUrl;

        /**
         * 通知key。
         */
        private String notifyKey;

        /**
         * 构造函数。
         * @param notifyUrl
         * @param notifyKey
         */
        public DingConfig(String notifyUrl, String notifyKey) {
            this.notifyUrl = notifyUrl;
            this.notifyKey = notifyKey;
        }

        /**
         * 构造函数。
         */
        public DingConfig() {
        }

        /**
         * 检查配置是否符合。
         *
         * @return
         */
        public boolean isValid() {
            return StringUtils.isNotBlank( notifyKey ) && StringUtils.isNotBlank( notifyUrl );
        }

        public String getNotifyUrl() {
            return notifyUrl;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }

        public String getNotifyKey() {
            return notifyKey;
        }

        public void setNotifyKey(String notifyKey) {
            this.notifyKey = notifyKey;
        }
    }

}
