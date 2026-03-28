package com.example.ratelimiter.repository;

import com.example.ratelimiter.entity.RateLimitBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<RateLimitBucket, String> {
}
