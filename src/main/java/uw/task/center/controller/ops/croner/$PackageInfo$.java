//此处定义菜单项。
package uw.task.center.controller.ops.croner;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.UserType;

/**
 * 主要是提供注解支持用。
 */
@RestController
public class $PackageInfo$ {
    @GetMapping("/ops/croner")
    @Operation(summary = "定时任务管理", description = "定时任务管理")
    @MscPermDeclare(user = UserType.OPS)
    public void info() {

    }

}
