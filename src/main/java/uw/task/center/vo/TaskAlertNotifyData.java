package uw.task.center.vo;

/**
 * 任务报警通知数据。
 *
 * <p>承载一次告警通知的接收方与内容，用于驱动钉钉/notifyUrl 等通道发送。</p>
 *
 * @author axeon
 */
public class TaskAlertNotifyData {


    /**
     * 联系类型：email / notifyUrl。
     */
    private String contactType;

    /**
     * 联系目标值：邮箱地址或 webhook URL。
     */
    private String contactInfo;

    /**
     * 通知标题。
     */
    private String notifyTitle;

    /**
     * 通知正文（markdown）。
     */
    private String notifyBody;

    /**
     * 全参构造。
     *
     * @param contactType 联系类型
     * @param contactInfo 联系目标值
     * @param notifyTitle 通知标题
     * @param notifyBody  通知正文
     */
    public TaskAlertNotifyData( String contactType, String contactInfo, String notifyTitle, String notifyBody) {
        this.contactType = contactType;
        this.contactInfo = contactInfo;
        this.notifyTitle = notifyTitle;
        this.notifyBody = notifyBody;
    }

    public TaskAlertNotifyData() {
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public String getNotifyBody() {
        return notifyBody;
    }

    public void setNotifyBody(String notifyBody) {
        this.notifyBody = notifyBody;
    }
}
