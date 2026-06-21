package uw.task.center.controller.rpc;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.auth.service.annotation.MscPermDeclare;
import uw.auth.service.constant.UserType;

/**
 * 任务RPC模块的权限/菜单锚点。
 *
 * <p>占位 controller，自身无业务逻辑，仅为 {@code /rpc/task} 路径提供 RPC 权限声明与菜单注册入口。</p>
 *
 * @author axeon
 */
@RestController
public class $PackageInfo$ {
    /**
     * 任务RPC入口（占位，仅用于权限/菜单注册）。
     */
    @GetMapping("/rpc/task")
    @Operation(summary = "任务RPC", description = "任务RPC")
    @MscPermDeclare(user = UserType.RPC)
    public void info() {

    }

}
