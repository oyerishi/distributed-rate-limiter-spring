package com.example.ratelimiter.repository;

import com.example.ratelimiter.entity.RateLimitBucket;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BucketRepository extends JpaRepository<RateLimitBucket, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RateLimitBucket> findById(String Clientid);
}
