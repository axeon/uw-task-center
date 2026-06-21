package uw.task.center.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uw.httpclient.http.HttpConfig;
import uw.httpclient.http.HttpInterface;
import uw.httpclient.json.JsonInterfaceHelper;
import uw.httpclient.util.SSLContextUtils;

/**
 * 发送钉钉通知的工具类。
 *
 * <p>封装钉钉机器人 webhook 的 markdown 消息发送，供告警通知使用。
 * HTTP 客户端信任所有证书（钉钉 webhook 走 HTTPS）。</p>
 *
 * @author axeon
 */
public class DingUtils {

    /**
     * 日志器。
     */
    private static final Logger log = LoggerFactory.getLogger( DingUtils.class );

    private static final HttpInterface HTTP_INTERFACE = new JsonInterfaceHelper( HttpConfig.builder()
            .retryOnConnectionFailure( true )
            .connectTimeout( 10_000L )
            .readTimeout( 10_000L )
            .writeTimeout( 10_000L )
            .trustManager( SSLContextUtils.getTrustAllManager() ).sslSocketFactory( SSLContextUtils.getTruestAllSocketFactory())
            .hostnameVerifier((hostName, sslSession) -> true)
            .build() );


    /**
     * 向指定钉钉 webhook 发送 markdown 通知。发送失败仅记录日志，不抛异常。
     *
     * @param noticeUrl 钉钉机器人 webhook 地址
     * @param title     消息标题
     * @param body      消息正文（markdown）
     */
    public static void send(String noticeUrl, String title, String body) {
        DingMarkdownMsg ding = new DingMarkdownMsg();
        ding.getMarkdown().setTitle( title );
        ding.getMarkdown().setText( body );
        //发送通知。
        try {
            HTTP_INTERFACE.postBodyForData( noticeUrl, ding );
        } catch (Exception e) {
            log.error( "钉钉通知发送失败！{}", e.getMessage(), e );
        }

    }


    /**
     * 钉钉的 markdown 格式消息体。
     */
    public static class DingMarkdownMsg {

        /**
         * 消息类型，固定为 markdown。
         */
        private String msgtype = "markdown";

        /**
         * markdown 正文。
         */
        private Markdown markdown = new Markdown();

        /**
         * @ 群成员配置。
         */
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

        /**
         * markdown 正文内容。
         */
        private static class Markdown {
            /**
             * 消息标题（通知列表展示用）。
             */
            private String title;

            /**
             * 消息正文。
             */
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

        /**
         * @ 群成员配置。
         */
        private static class At {
            /**
             * 被 @ 的手机号列表。
             */
            private String[] atMobiles;

            /**
             * 被 @ 的用户 id 列表。
             */
            private String[] atUserIds;

            /**
             * 是否 @ 全员，默认 false（避免每次告警 @ 全员造成刷屏）。
             */
            private boolean isAtAll = false;

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
