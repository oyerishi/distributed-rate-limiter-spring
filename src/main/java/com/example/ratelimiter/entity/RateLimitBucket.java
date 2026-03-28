package com.example.ratelimiter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class RateLimitBucket {
    @Id
    private String identifier;     //this will store the user's IP address

    private double tokens; // Current tokens in the bucket
    private int maxCapacity; //Maximum Tokens (e.g. 10)
    private double refillRate; //Tokens added per second(e.g., 10)

    private LocalDateTime lastRefillTime;   //The timestamp of the last request

}
