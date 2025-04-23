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
    private String centerName;

    /**
     * 报警的钉钉通知。
     */
    private DingConfig alertDing = new DingConfig();

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
