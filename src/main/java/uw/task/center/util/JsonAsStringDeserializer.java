package uw.task.center.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

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
        return tree.toString();

    }
}
