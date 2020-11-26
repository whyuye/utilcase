//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.wuhui.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class JsonUtils {
    private static Gson gson;

    public JsonUtils() {
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJsonGenericObject(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJsonByTypeToken(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> List<T> fromJsonList(String json, TypeToken<List<T>> typeToken) {
        return json == null ? Collections.emptyList() : (List)gson.fromJson(json, typeToken.getType());
    }

    static {
        gson = (new GsonBuilder()).registerTypeAdapter(Boolean.class, new JsonUtils.BooleanTypeAdapter()).registerTypeAdapter(Integer.class, new JsonUtils.IntegerTypeAdapter()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    private static class IntegerTypeAdapter implements JsonDeserializer<Integer> {
        private IntegerTypeAdapter() {
        }

        public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonNull()) {
                return null;
            } else {
                try {
                    return json.getAsInt();
                } catch (NumberFormatException | ClassCastException var6) {
                    boolean b = json.getAsBoolean();
                    return Boolean.TRUE.equals(b) ? 1 : 0;
                }
            }
        }
    }

    private static class BooleanTypeAdapter implements JsonDeserializer<Boolean> {
        private BooleanTypeAdapter() {
        }

        public Boolean deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return jsonElement.getAsBoolean();
            } catch (ClassCastException var6) {
                int code = jsonElement.getAsInt();
                return code == 0 ? false : true;
            }
        }
    }
}

