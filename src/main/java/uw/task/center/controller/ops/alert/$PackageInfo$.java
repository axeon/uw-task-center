//此处定义菜单项。
package uw.task.center.controller.ops.alert;

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
    @MscPermDeclare(type = UserType.OPS)
    @Operation(summary = "报警管理", description = "报警管理")
    @GetMapping("/ops/alert")
    public void info() {

    }

}
