package uw.task.center;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

@SpringBootApplication
public class UwTaskCenterApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UwTaskCenterApplication.class)
                .beanNameGenerator((beanDefinition, beanDefinitionRegistry) -> {
                    String beanClassName = beanDefinition.getBeanClassName();
                    if (beanClassName.contains("uw.task.center")) {
                        return beanClassName;
                    }

                    if (beanClassName.endsWith("LoadBalancerAutoConfiguration")) {
                        return beanClassName;
                    }
                    return new AnnotationBeanNameGenerator().generateBeanName(beanDefinition, beanDefinitionRegistry);
                })
                .run(args);

    }
}
