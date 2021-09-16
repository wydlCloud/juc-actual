package com.wy.juclearn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

/**
 * Jackson序列化和反序列化
 */
public class JsonUtils {
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        //timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        //timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        jsonMapper.registerModule(timeModule);
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        String json = null;
        try {
            json = jsonMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }



    public static <T> T deserialize(String json, Class<T> objectType) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return jsonMapper.readValue(json, objectType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T deserialize(String json,TypeReference typeReference) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return jsonMapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




    public static ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

}
