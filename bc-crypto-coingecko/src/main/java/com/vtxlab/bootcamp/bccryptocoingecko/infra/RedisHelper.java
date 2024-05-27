package com.vtxlab.bootcamp.bccryptocoingecko.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisHelper {
  private RedisTemplate<String, String> redisTemplate;

  private ObjectMapper objectMapper; // NPE

  public RedisHelper(RedisConnectionFactory factory, ObjectMapper objectMapper,
    RedisTemplate<String, String> redisTemplate) {
    Objects.requireNonNull(factory);
    Objects.requireNonNull(objectMapper);
    Objects.requireNonNull(redisTemplate);
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  public RedisHelper(RedisConnectionFactory factory, 
    ObjectMapper objectMapper) {
    //Objects.requireNonNull(redisTemplate);
    Objects.requireNonNull(factory);
    Objects.requireNonNull(objectMapper);
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  // new RedisHelper().set("vincent",user)
  public void set(String key, Object value)  throws JsonProcessingException {
    ObjectMapper objectMapper = this.objectMapper; // 
    String serialized = objectMapper.writeValueAsString(value);
    redisTemplate.opsForValue().set(key, serialized);
  }

  // User2 user = new RedisHelper().get("vincent", User2.class)
  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException{
    String serialized = redisTemplate.opsForValue().get(key);
    return objectMapper.readValue(serialized , clazz);

  }

  public List<String> getKeys(String pattern) {
    return new ArrayList<>(redisTemplate.keys(pattern));
  }
}
