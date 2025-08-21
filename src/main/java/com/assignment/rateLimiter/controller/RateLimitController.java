package com.assignment.rateLimiter.controller;

import com.assignment.rateLimiter.config.RateLimiterRules;
import com.assignment.rateLimiter.dto.Decision;
import com.assignment.rateLimiter.dto.RequestContext;
import com.assignment.rateLimiter.service.RateLimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratelimit")
public class RateLimitController {

    private final RateLimitService  rateLimitService;

    public RateLimitController(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }
    @GetMapping("/checkDecision")
    public Decision checkLimit(@RequestParam String api,
                               @RequestParam  String method,
                               @RequestParam(required = false) String userId,
                               @RequestParam(required = false) String clientId,
                               @RequestParam(required = false) String ip)
    {
        RequestContext requestContext =  new RequestContext(
                api,method,userId, clientId != null ? clientId : "default-client",
                ip != null ? ip : "127.0.0.1");
        return rateLimitService.checkDecision(requestContext);
    }
}
