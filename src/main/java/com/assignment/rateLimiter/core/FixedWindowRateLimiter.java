package com.assignment.rateLimiter.core;

import com.assignment.rateLimiter.config.RateLimiterRules;
import com.assignment.rateLimiter.dto.Decision;
import com.assignment.rateLimiter.dto.RequestContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class FixedWindowRateLimiter implements RateLimiter {
    private final StringRedisTemplate stringRedisTemplate;
    public FixedWindowRateLimiter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Decision isAllowed(RequestContext requestContext, RateLimiterRules rules) {
        String  key  = getKey(requestContext,rules);

        ValueOperations<String,String> valueOfOper = stringRedisTemplate.opsForValue();
        Long currentKey = valueOfOper.increment(key);

        if (currentKey==1)
        {
            stringRedisTemplate.expire(key,rules.getWindowMs(), TimeUnit.MILLISECONDS);
        }

        if(currentKey!=null && currentKey<=rules.getLimit()){
            long totalTime = stringRedisTemplate.getExpire(key,TimeUnit.MILLISECONDS);
            return new Decision(true,rules.getLimit()-currentKey,totalTime,"Allowed");
        }
        else {
            long ttl = stringRedisTemplate.getExpire(key,TimeUnit.MILLISECONDS);
            return new Decision(false, 0, ttl, "Rate limit exceeded");
        }
    }
    public String getKey(RequestContext requestContext, RateLimiterRules rateLimiter)
    {
        StringBuilder key = new StringBuilder("rate:");
        key.append(rateLimiter.getApi()).append(":");
        if (rateLimiter.getBy().contains(RateLimiterRules.By.USER_ID)) {
            key.append("user:").append(requestContext.userId()).append(":");
        }
        if (rateLimiter.getBy().contains(RateLimiterRules.By.CLIENT_ID)) {
            key.append("client:").append(requestContext.clientId()).append(":");
        }
        if (rateLimiter.getBy().contains(RateLimiterRules.By.IP)) {
            key.append("ip:").append(requestContext.ip()).append(":");
        }
        long window = Instant.now().toEpochMilli() / rateLimiter.getWindowMs();
        key.append(window);
        return key.toString();
    }
}
