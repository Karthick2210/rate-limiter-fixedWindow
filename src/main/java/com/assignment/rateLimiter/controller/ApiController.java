package com.assignment.rateLimiter.controller;

import com.assignment.rateLimiter.service.RateLimiterFW_Alg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final RateLimiterFW_Alg rateLimiter;
    public ApiController(RateLimiterFW_Alg rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @GetMapping("/test-api")
    public String testApi(@RequestParam String userId) {
        boolean allowed = rateLimiter.requestAllow(userId, "/test-api", 5); // 5 requests per minute

        if (allowed) {
            return "Request allowed for user " + userId;
        } else {
            return "Rate limit exceeded for user " + userId;
        }
    }
}
