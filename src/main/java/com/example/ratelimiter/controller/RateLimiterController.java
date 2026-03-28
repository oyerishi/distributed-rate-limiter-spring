package com.example.ratelimiter.controller;

import com.example.ratelimiter.annotation.RateLimited;
import com.example.ratelimiter.entity.RateLimitBucket;
import com.example.ratelimiter.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RateLimiterController {
    private final RateLimiterService service;

//    @GetMapping("/test")
//    public ResponseEntity<String> testRateLimit(HttpServletRequest request){
//        //get the visitors IP
//        String clientIp = request.getRemoteAddr();
//
//        //check with our locked service logic
//        if(service.allowRequest(clientIp)){
//            return ResponseEntity.ok("Request Allowed! You have tokens Available.");
//        }else{
//            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Rate Limit Exceeded. Please wait for the bucket to refill.");
//        }
//    }
    @RateLimited
    @GetMapping("/test")
    public ResponseEntity<String> testRateLimit(){
        return ResponseEntity.ok("Request Allowed! You have reached the business logic");
    }

}
