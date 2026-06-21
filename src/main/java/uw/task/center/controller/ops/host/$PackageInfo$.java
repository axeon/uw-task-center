package uw.task.center.controller.ops.host;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.UserType;

/**
 * 任务主机管理模块的权限/菜单锚点。
 *
 * <p>占位 controller，自身无业务逻辑，仅为 {@code /ops/host} 路径提供 OPS 权限声明与菜单注册入口。</p>
 *
 * @author axeon
 */
@RestController
public class $PackageInfo$ {
    /**
     * 任务主机管理入口（占位，仅用于权限/菜单注册）。
     */
    @GetMapping("/ops/host")
    @Operation(summary = "任务主机管理", description = "任务主机管理")
    @MscPermDeclare(user = UserType.OPS)
    public void info() {

    }

}
