package uw.task.center.conf;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Swagger / OpenAPI 文档配置。
 *
 * <p>仅在 debug、dev 环境启用。为 OPS 管理端接口生成分组文档，统一注入 Bearer Token 鉴权方案
 * 与项目基本信息（名称、版本、联系人）。</p>
 *
 * @author axeon
 */
@Configuration
@Profile({"debug","dev"})
public class SwaggerConfig {

    /**
     * 应用名称（取自 project.name，由 Maven 注入）。
     */
    @Value("${project.name}")
    private String appName;

    /**
     * 应用版本（取自 project.version，由 Maven 注入）。
     */
    @Value("${project.version}")
    private String appVersion;

    /**
     * 构建 OpenAPI 定制器：为所有文档统一添加 Bearer Token 鉴权方案与项目信息。
     *
     * @return OpenApiCustomizer 实例
     */
    @Bean
    public OpenApiCustomizer customOpenAPI() {
        return openApi -> openApi
                .addSecurityItem(new SecurityRequirement().addList("AuthToken"))
                .components(openApi.getComponents().addSecuritySchemes("AuthToken", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").in(SecurityScheme.In.HEADER)))
                .info(new Info().title(appName).version(appVersion)
                        .contact(new Contact().name("axeon").email("23231269@qq.com")));
    }

    /**
     * OPS 管理端接口分组：扫描 {@code uw.task.center.controller.ops} 包，复用统一的鉴权与信息定制。
     *
     * @return opsApi 分组配置
     */
    @Bean
    public GroupedOpenApi opsApi() {
        return GroupedOpenApi.builder()
                .group("opsApi")
                .packagesToScan("uw.task.center.controller.ops")
                .addOpenApiCustomizer(customOpenAPI())
                .build();
    }

}
