package com.xqx.base.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * Gson时间戳字段反序列化适配器
 */
public class TimestampDeserializerAdapter implements JsonDeserializer<Timestamp> {

    @Override
    public Timestamp deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return new Timestamp(json.getAsLong());
    }
}
