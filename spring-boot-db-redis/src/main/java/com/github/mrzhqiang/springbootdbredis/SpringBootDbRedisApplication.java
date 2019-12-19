package com.github.mrzhqiang.springbootdbredis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class SpringBootDbRedisApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootDbRedisApplication.class, args);
  }

  @Bean
  @SuppressWarnings({"rawtypes"})
  public RedisTemplate<Object, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL);
    objectJackson2JsonRedisSerializer.setObjectMapper(mapper);

    template.setValueSerializer(objectJackson2JsonRedisSerializer);
    template.setKeySerializer(new StringRedisSerializer());

    template.afterPropertiesSet();
    return template;
  }
}
