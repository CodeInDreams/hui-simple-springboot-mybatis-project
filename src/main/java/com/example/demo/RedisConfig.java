package com.example.demo;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//    @Bean
//    public RedisTemplate<String, User> redisUserTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, User> redisUserTemplate = new RedisTemplate<>();
//        redisUserTemplate.setConnectionFactory(factory);
//        redisUserTemplate.setKeySerializer(new StringRedisSerializer());
//        redisUserTemplate.setHashValueSerializer(new StringRedisSerializer());
//        redisUserTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(User.class));
//        redisUserTemplate.afterPropertiesSet();
//        return redisUserTemplate;
//    }

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, T> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setStringSerializer(new StringRedisSerializer());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setEnableDefaultSerializer(true);
        template.afterPropertiesSet();
        return template;
    }


}