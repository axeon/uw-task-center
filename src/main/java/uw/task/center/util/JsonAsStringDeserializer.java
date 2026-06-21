package uw.task.center.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NullNode;

import java.io.IOException;

/**
 * 这是为了解决jackson强制解析json为String的Deserializer。
 * 对于TaskRunnerLog的taskParam,taskConfig,resultData字段，应加上
 *
 * @author axeon
 * @JsonDeserialize(using = JsonAsStringDeserializer.class)
 */
public class JsonAsStringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        TreeNode tree = jsonParser.getCodec().readTree(jsonParser);
        // JSON null 应返回 null，而非字面字符串 "null"，避免下游字段被污染。
        if (tree == null || tree instanceof NullNode) {
            return null;
        }
        return tree.toString();

    }
}
