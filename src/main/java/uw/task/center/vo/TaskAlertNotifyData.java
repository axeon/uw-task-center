package uw.task.center.vo;

/**
 * 任务报警通知数据。
 */
public class TaskAlertNotifyData {

    /**
     * 联系人
     */
    private String contactMan;

    /**
     * 联系方式，email/notifyUrl
     */
    private String contactType;

    /**
     * 联系方式
     */
    private String contactInfo;

    /**
     * 通知标题
     */
    private String notifyTitle;

    /**
     * 通知内容
     */
    private String notifyBody;

    public TaskAlertNotifyData(String contactMan, String contactType, String contactInfo, String notifyTitle, String notifyBody) {
        this.contactMan = contactMan;
        this.contactType = contactType;
        this.contactInfo = contactInfo;
        this.notifyTitle = notifyTitle;
        this.notifyBody = notifyBody;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
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
