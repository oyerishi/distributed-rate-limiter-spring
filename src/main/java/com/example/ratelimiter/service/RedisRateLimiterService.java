package com.example.ratelimiter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RedisRateLimiterService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisScript<Long> rateLimitScript;

    public boolean isAllowed(String clientIp, int capacity, int refillRate){
        //define the unique "Locker" address
        String key = "rate_limit:" + clientIp;
        //get the current time  (Epoch Seconds)
        long currentTime = System.currentTimeMillis()/1000;

        Long result = redisTemplate.execute(
                rateLimitScript,
                Collections.singletonList(key), //List of Keys
                capacity,                       //ARGV[1]
                refillRate,                     //ARGV[2]
                currentTime                     //ARGV[3]
        );
        //Lua returns 1 for allowed , 0 for blocked
        return result != null && result == 1;
    }
}
