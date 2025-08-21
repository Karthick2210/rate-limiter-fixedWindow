package com.assignment.rateLimiter.service;

import com.assignment.rateLimiter.config.RateLimiterRules;
import com.assignment.rateLimiter.core.FixedWindowRateLimiter;
import com.assignment.rateLimiter.dto.Decision;
import com.assignment.rateLimiter.dto.RequestContext;
import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

    private final FixedWindowRateLimiter fixedWindowRateLimiter;
    private final RuleService ruleService;

    public RateLimitService(FixedWindowRateLimiter fixedWindowRateLimiter, RuleService ruleService) {
        this.fixedWindowRateLimiter = fixedWindowRateLimiter;
        this.ruleService = ruleService;
    }

    public Decision checkDecision(RequestContext requestContext)
    {
        RateLimiterRules rules = ruleService.resolveRules(requestContext.api(),requestContext.method());
        return fixedWindowRateLimiter.isAllowed(requestContext,rules);
    }

}
