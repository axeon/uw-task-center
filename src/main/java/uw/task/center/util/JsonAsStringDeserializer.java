package uw.task.center.util;

import tools.jackson.core.JsonParser;
import tools.jackson.core.TreeNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.node.NullNode;

/**
 * 这是为了解决jackson强制解析json为String的Deserializer。
 * 对于TaskRunnerLog的taskParam,resultData,errorInfo字段，应加上
 *
 * @author axeon
 * @JsonDeserialize(using = JsonAsStringDeserializer.class)
 */
public class JsonAsStringDeserializer extends ValueDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        // Jackson 3 中 JsonParser.getCodec() 已移除，改用 readValueAsTree() 直接读取树节点。
        TreeNode tree = jsonParser.readValueAsTree();
        // JSON null 应返回 null，而非字面字符串 "null"，避免下游字段被污染。
        if (tree == null || tree instanceof NullNode) {
            return null;
        }
        return tree.toString();

    }
}
