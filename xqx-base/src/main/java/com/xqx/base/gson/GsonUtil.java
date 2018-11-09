package com.xqx.base.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Gson工具类
 */
public class GsonUtil {

	private static final Gson gson;

	static {
		gson = new GsonBuilder().enableComplexMapKeySerialization().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS") // 时间转化为特定格式
				.serializeNulls() // 当字段值为空或null时，依然对该字段进行转换
				// .registerTypeAdapter(Timestamp.class, new TimestampDeserializerAdapter())
				// .registerTypeAdapter(Timestamp.class, new TimestampSerializerAdapter())
				// .registerTypeAdapter(BigDecimal.class, new BigDecimalSerializerAdapter())
				.create();

	}

	/**
	 * Json转Bean
	 *
	 * @param jsonStr 字符串格式的Json数据
	 * @param clazz   数据类型
	 * @param         <T> 泛型
	 * @return Bean
	 */
	public static <T> T fromJson(String jsonStr, Class<T> clazz) {
		if (jsonStr == null || jsonStr.isEmpty()) {
			return null;
		}
		return gson.fromJson(jsonStr, clazz);
	}

	public static <T> T fromJson(String jsonStr, Type typeOfT) {
		if (jsonStr == null || jsonStr.isEmpty()) {
			return null;
		}
		return gson.fromJson(jsonStr, typeOfT);
	}

	/**
	 * Json转Bean
	 *
	 * @param jsonStr 字符串格式的Json数据
	 * @param clazz   数据类型
	 * @param         <T> 泛型
	 * @return Bean集合
	 */
	public static <T> List<T> fromJsonToList(String jsonStr, Class<T> clazz) {
		if (jsonStr == null || jsonStr.isEmpty()) {
			return null;
		}
		JsonArray array = new JsonParser().parse(jsonStr).getAsJsonArray();
		if (array.size() == 0) {
			return null;
		}
		List<T> list = new ArrayList<>();
		for (final JsonElement elem : array) {
			list.add(gson.fromJson(((JsonObject) elem).toString(), clazz));
		}
		return list;
	}

	/**
	 * Bean转Json
	 *
	 * @param object Bean
	 * @return Json格式字符串
	 */
	public static String toJson(Object object) {
		return gson.toJson(object);
	}

}
