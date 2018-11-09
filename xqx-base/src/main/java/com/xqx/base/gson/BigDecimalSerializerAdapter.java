package com.xqx.base.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Gson货币字段序列化适配器
 */
public class BigDecimalSerializerAdapter implements JsonSerializer<BigDecimal> {
    private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);

    @Override
    public JsonElement serialize(BigDecimal arg, Type type, JsonSerializationContext jsonSerializationContext) {
        if (arg != null) {
            return new JsonPrimitive(numberFormat.format(arg));
        } else {
            return new JsonPrimitive("");
        }

    }
}
