package uw.task.center.runner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import uw.httpclient.http.HttpConfig;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.task.TaskData;
import uw.task.TaskRunner;
import uw.task.center.util.ContactUtils;
import uw.task.center.vo.TaskAlertNotifyData;
import uw.task.entity.TaskContact;
import uw.task.entity.TaskRunnerConfig;

@Component
public class AlertNotifySendRunner extends TaskRunner<TaskAlertNotifyData, String> {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final HttpInterface httpInterface = new JsonInterfaceHelper( HttpConfig.builder()
            .retryOnConnectionFailure( true )
            .connectTimeout( 10_000L )
            .readTimeout( 10_000L )
            .writeTimeout( 10_000L )
            .build() );

    @Autowired
    public AlertNotifySendRunner(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    /**
     * 执行任务。
     * 业务层面的异常请根据实际情况手动Throw TaskException:
     * 目前支持的异常:
     * 1. TaskDataException 任务数据异常
     * 2. TaskPartnerException 任务合作方异常
     *
     * @param taskData 数据
     * @return 指定的返回对象
     * @throws Exception 异常
     */
    @Override
    public String runTask(TaskData<TaskAlertNotifyData, String> taskData) throws Exception {
        TaskAlertNotifyData notifyData = taskData.getTaskParam();
        if (notifyData.getContactType().equals( "notifyUrl" )) {
            String contactInfo = notifyData.getContactInfo();
            if (contactInfo.startsWith( "https://oapi.dingtalk.com/robot/send" )) {
                DingMarkdownMsg ding = new DingMarkdownMsg();
                ding.getMarkdown().setTitle( notifyData.getNotifyTitle() );
                ding.getMarkdown().setText( notifyData.getNotifyBody() );
                //发送通知。
                ding.getAt().setAtUserIds( new String[]{notifyData.getContactMan()} );
                httpInterface.postBodyForData( notifyData.getContactInfo(), ding );
            }
        } else if (notifyData.getContactType().equals( "email" )) {
            sendEmail( notifyData.getContactInfo(), notifyData.getNotifyTitle(), notifyData.getNotifyBody() );
        }

        return "发送完成";
    }

    /**
     * 初始化配置信息
     *
     * @return TaskRunnerConfig配置
     */
    @Override
    public TaskRunnerConfig initConfig() {
        return TaskRunnerConfig.builder().taskName( "报警通知发送任务" ).build();
    }

    /**
     * 初始化联系人信息
     *
     * @return TaskContact联系人信息
     */
    @Override
    public TaskContact initContact() {
        return ContactUtils.getTaskContact();
    }

    /**
     * 发送邮件
     *
     * @param toEmail 收件人集合
     * @param title   邮件标题
     * @param content 邮件内容
     */
    public void sendEmail(String toEmail, String title, String content) {
        if (StringUtils.isNotBlank( fromEmail ) && StringUtils.isNotBlank( toEmail )) {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom( fromEmail );// 发件人
            email.setTo( toEmail );// 收件人地址
            email.setSubject( title );// 邮件标题
            email.setText( content );// 邮件信息
            mailSender.send( email );
        }
    }

    /**
     * 钉钉的markdown格式信息。
     */
    public static class DingMarkdownMsg {

        private String msgtype = "markdown";

        private Markdown markdown = new Markdown();

        private At at = new At();


        public String getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(String msgtype) {
            this.msgtype = msgtype;
        }

        public Markdown getMarkdown() {
            return markdown;
        }

        public void setMarkdown(Markdown markdown) {
            this.markdown = markdown;
        }

        public At getAt() {
            return at;
        }

        public void setAt(At at) {
            this.at = at;
        }

        private static class Markdown {
            private String title;

            private String text;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        private static class At {
            private String[] atMobiles;

            private String[] atUserIds;

            private boolean isAtAll = true;

            public String[] getAtMobiles() {
                return atMobiles;
            }

            public void setAtMobiles(String[] atMobiles) {
                this.atMobiles = atMobiles;
            }

            public String[] getAtUserIds() {
                return atUserIds;
            }

            public void setAtUserIds(String[] atUserIds) {
                this.atUserIds = atUserIds;
            }

            public boolean isAtAll() {
                return isAtAll;
            }

            public void setAtAll(boolean atAll) {
                isAtAll = atAll;
            }
        }

    }
}
