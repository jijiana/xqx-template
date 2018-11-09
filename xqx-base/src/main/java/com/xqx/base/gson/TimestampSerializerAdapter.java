package com.xqx.base.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Gson时间戳字段序列化适配器
 */
public class TimestampSerializerAdapter implements JsonSerializer<Timestamp> {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public JsonElement serialize(Timestamp timestamp, Type type, JsonSerializationContext jsonSerializationContext) {
        if (timestamp != null) {
            return new JsonPrimitive(format.format(timestamp));
        } else {
            return new JsonPrimitive("");
        }

    }
}
