package com.example.ratelimiter.interceptor;

import com.example.ratelimiter.annotation.RateLimited;
import com.example.ratelimiter.service.RateLimiterService;
import com.example.ratelimiter.service.RedisRateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RateLimiterInterceptor implements HandlerInterceptor {
//    private final RateLimiterService service;     //PostgreSQL
    private final RedisRateLimiterService service; //Redis
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //is this request hitting a real java method(Controller)?
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;

            //does that method have our @RateLimited label?
            if(method.hasMethodAnnotation(RateLimited.class)){
                String clientId = request.getRemoteAddr();

                if(!service.isAllowed(clientId, 2, 1)){
                    response.setStatus(429);
                    response.getWriter().write("Rate Limit Exceeded via Interceptor!");
                    return false;
                }
            }
        }
        return true; //No annotation? or tokens available let them pass!
    }
}
