package uw.task.center;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import uw.dao.TransactionException;

@SpringBootApplication
public class UwTaskCenterApplication {
    public static void main(String[] args) throws TransactionException {
        new SpringApplicationBuilder(UwTaskCenterApplication.class)
                .beanNameGenerator((beanDefinition, beanDefinitionRegistry) -> {
                    String beanClassName = beanDefinition.getBeanClassName();
                    if (beanClassName.contains("uw.task")) {
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
