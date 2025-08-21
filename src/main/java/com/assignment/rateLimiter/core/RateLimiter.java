package com.assignment.rateLimiter.core;

import com.assignment.rateLimiter.config.RateLimiterRules;
import com.assignment.rateLimiter.dto.Decision;
import com.assignment.rateLimiter.dto.RequestContext;

public interface RateLimiter {
    Decision isAllowed(RequestContext requestContext , RateLimiterRules rules);
}
