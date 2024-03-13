package uw.task.center.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 任务配置类。
 *
 * @author axeon
 */
@Configuration
@ConfigurationProperties(prefix = "uw.task.center")
public class TaskCenterProperties implements WebMvcConfigurer {

    /**
     * 全局报警通知链接。
     */
    private String globalAlertNotifyUrl;


    public String getGlobalAlertNotifyUrl() {
        return globalAlertNotifyUrl;
    }

    public void setGlobalAlertNotifyUrl(String globalAlertNotifyUrl) {
        this.globalAlertNotifyUrl = globalAlertNotifyUrl;
    }


    /**
     * 移除XML消息转换器
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof MappingJackson2XmlHttpMessageConverter);
    }
}
