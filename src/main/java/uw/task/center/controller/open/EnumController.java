package uw.task.center.controller.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uw.common.util.EnumUtils;

import java.util.Map;

/**
 * 公共枚举查询接口。
 *
 * <p>仅在 debug/dev 环境启用，导出 {@code uw.task.center.constant} 包下的全部枚举，
 * 供前端下拉框等控件使用。</p>
 *
 * @author axeon
 */
@RestController
@RequestMapping("/open/enum")
@Tag(name = "枚举类管理")
@Profile({"debug","dev"})
public class EnumController {

    /**
     * 枚举扫描的基础包名。
     */
    private static final String BASE_PACKAGE = "uw.task.center.constant";

    /**
     * 获取基础包下所有枚举的 value/label 映射。
     *
     * @return 按枚举类名分组的映射（key 为枚举类名，value 为其 value→枚举实例映射）
     */
    @GetMapping("/getAllEnumMap")
    @Operation(summary = "获取所有枚举", description = "获取所有枚举")
    public Map<String, Map<String, Enum<?>>> getAllEnumMap() throws Exception {
        return EnumUtils.getEnumMap(BASE_PACKAGE);
    }

}
