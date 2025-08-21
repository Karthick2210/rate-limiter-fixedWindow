package com.assignment.rateLimiter.service;

import com.assignment.rateLimiter.config.RateLimiterRules;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    public RateLimiterRules resolveRules(String api, String method)
    {
        RateLimiterRules rules =  new RateLimiterRules();
        rules.setApi(api);
        rules.setBy(List.of(RateLimiterRules.By.USER_ID));
        rules.setLimit(5);
        rules.setWindowMs(60_000L);
        return  rules;
    }
}
