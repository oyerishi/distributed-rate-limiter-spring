package com.example.ratelimiter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rate_limit_buckets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateLimitBucket {
    @Id
    private String identifier;     //this will store the user's IP address

    private double tokens; // Current tokens in the bucket
    private double maxCapacity; //Maximum Tokens (e.g. 10)
    private double refillRate; //Tokens added per second(e.g., 10)

    private LocalDateTime lastRefillTime;   //The timestamp of the last request

}
