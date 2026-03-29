package com.example.ratelimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ResourceElementResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    //Template : Java talks to Redis
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //Use Strings for keys (so they are readbale in terminal)
        template.setKeySerializer(new StringRedisSerializer());
        //Use JSON for values(so we can store the Hash fields)
        template.setValueSerializer(new GenericJacksonJsonRedisSerializer());

        return template;
    }
    //The Script Loader - This loads lua file into memory
    @Bean
    public RedisScript<Long> rateLimitScript(){
        Resource scriptSource = new ClassPathResource("scripts/rate-limit.lua");
        return RedisScript.of(scriptSource, Long.class);
    }
}
