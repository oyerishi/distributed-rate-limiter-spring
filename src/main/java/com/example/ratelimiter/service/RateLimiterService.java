package com.example.ratelimiter.service;

import com.example.ratelimiter.entity.RateLimitBucket;
import com.example.ratelimiter.repository.BucketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final BucketRepository bucketRepository;

    @Transactional
    public boolean allowRequest(String clientId){
        //get the bucket (or create a new one)
        RateLimitBucket bucket = bucketRepository.findById(clientId).orElse(new RateLimitBucket(clientId, 10.0, 10, 1.0, LocalDateTime.now()));

        //refill logic
        refill(bucket);

        //consumption logic
        if(bucket.getTokens() >= 1.0){
            bucket.setTokens(bucket.getTokens()-1.0);
            bucketRepository.save(bucket);
            return true;    //Request allowed
        }
        return false;    //Rate limit exceeded - BLOCKED
    }
    private void refill(RateLimitBucket bucket){
        LocalDateTime now = LocalDateTime.now();
        long secondsPassed = Duration.between(bucket.getLastRefillTime(), now).getSeconds();
        double newTokens = bucket.getTokens() + (secondsPassed * bucket.getRefillRate());

        bucket.setTokens(Math.min(bucket.getMaxCapacity(), newTokens));
        bucket.setLastRefillTime(now);
    }
}
