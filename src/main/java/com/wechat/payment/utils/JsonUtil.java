package com.wechat.payment.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

  @SuppressWarnings("all")
  private static ObjectMapper snakeCaseMapper = createObjectMapper();


  static ObjectMapper createObjectMapper() {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }


  public static String writeValueAsString(Object value) {
    try {
      return snakeCaseMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Json read failed", e);
    }
  }

  public static <T> T readValue(String json, Class<T> clazz) {
    try {
      return snakeCaseMapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new IllegalStateException("Json read failed", e);
    }
  }

  public static <T> T readValue(String json, TypeReference<T> typeReference) {
    try {
      return snakeCaseMapper.readValue(json, typeReference);
    } catch (IOException e) {
      throw new IllegalStateException("Json read failed", e);
    }
  }

  public static Map<String, Object> jsonObjectToMap(JSONObject jsonObject) {
    HashMap<String, Object> resp = Maps.newHashMapWithExpectedSize(jsonObject.size());
    jsonObject.forEach(resp::put);
    return resp;
  }

  public static Map<String, Object> jsonArrayToMap(JSONArray jsonArray) {
    HashMap<String, Object> resultMap = Maps.newHashMapWithExpectedSize(jsonArray.size());
    for (Object object : jsonArray) {
      resultMap.putAll(jsonObjectToMap((JSONObject) object));
    }
    return resultMap;
  }

  @SuppressWarnings("all")
  public static List<Map<String, Object>> jsonArrayToList(JSONArray jsonArray) {
    List<Map<String, Object>> result = Lists.newArrayListWithExpectedSize(jsonArray.size());
    for (Object data : jsonArray) {
      if (data instanceof JSONObject) {
        result.add(jsonObjectToMap((JSONObject) data));
      } else if (data instanceof HashMap) {
        result.add((HashMap) data);
      } else if (data instanceof LinkedHashMap) {
        result.add((LinkedHashMap) data);
      } else {
        throw new IllegalStateException("未知java类型无法转换");
      }
    }
    return result;
  }


}
