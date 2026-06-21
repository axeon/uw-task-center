package uw.task.center.controller.ops.log;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.UserType;

/**
 * 操作日志模块的权限/菜单锚点。
 *
 * <p>占位 controller，自身无业务逻辑，仅为 {@code /ops/log} 路径提供 OPS 权限声明与菜单注册入口。</p>
 *
 * @author axeon
 */
@RestController
public class $PackageInfo$ {
    /**
     * 操作日志入口（占位，仅用于权限/菜单注册）。
     */
    @GetMapping("/ops/log")
    @Operation(summary = "操作日志", description = "操作日志")
    @MscPermDeclare(user = UserType.OPS)
    public void info() {
    }

}
