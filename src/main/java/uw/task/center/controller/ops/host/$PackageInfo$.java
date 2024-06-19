//此处定义菜单项。
package uw.task.center.controller.ops.host;

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
    @GetMapping("/ops/host")
    @Operation(summary = "任务主机管理", description = "任务主机管理")
    @MscPermDeclare(type = UserType.OPS)
    public void info() {

    }

}
