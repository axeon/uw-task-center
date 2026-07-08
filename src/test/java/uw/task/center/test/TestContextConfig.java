package uw.task.center.test;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 测试用配置类（参考 uw-app-template 测试架构）。
 * <p>
 * 不直接用主类 {@code @SpringBootApplication} 启动，而是用本类 + 自定义 {@link CustomBeanNameGenerator}：
 * 对 uw.task.center 包下的 PackageInfo/Controller/Runner/Croner 用<b>全类名</b>作 bean name，
 * 避免不同包的同名类（如多个包的 package-info 默认都是 ".PackageInfo."）bean name 冲突。
 * </p>
 *
 * @author axeon
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        basePackages = "uw.task.center",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = SpringBootApplication.class),
        nameGenerator = TestContextConfig.CustomBeanNameGenerator.class
)
public class TestContextConfig {

    /**
     * 自定义 bean name 生成器：指定后缀的类用全类名作 bean name，避免同名冲突。
     */
    public static class CustomBeanNameGenerator extends AnnotationBeanNameGenerator {
        @Override
        protected String buildDefaultBeanName(BeanDefinition definition) {
            String className = definition.getBeanClassName();
            if (className != null && className.startsWith("uw.task.center")) {
                if (className.endsWith("Controller") || className.endsWith("Runner")
                        || className.endsWith("Croner") || className.contains("PackageInfo")) {
                    return className;
                }
            }
            return super.buildDefaultBeanName(definition);
        }
    }
}
