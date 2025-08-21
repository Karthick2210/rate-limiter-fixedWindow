package com.assignment.rateLimiter.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterFW_Alg {
    private final StringRedisTemplate stringRedisTemplate;
    public RateLimiterFW_Alg(StringRedisTemplate stringRedisTemplate)
    {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    public boolean requestAllow(String userId,String apiName,int limitPerMin)
    {
        long currentWindow = Instant.now().getEpochSecond()/60;
        String key = String.format("rate:%s:%s:%d", userId, apiName, currentWindow);
        Long count = stringRedisTemplate.opsForValue().increment(key);
        if(count==1)
        {
            stringRedisTemplate.expire(key, 60, TimeUnit.SECONDS);
        }
        return count<=limitPerMin;
    }
    }
