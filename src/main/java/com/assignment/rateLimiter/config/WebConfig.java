package com.assignment.rateLimiter.config;

import com.assignment.rateLimiter.core.RateLimitTrafficHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RateLimitTrafficHandler rateLimitTrafficHandler;
    @Autowired
    public WebConfig(RateLimitTrafficHandler rateLimitTrafficHandler) {
        this.rateLimitTrafficHandler = rateLimitTrafficHandler;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitTrafficHandler)
                .addPathPatterns("/**"); // apply to all endpoints
    }
}
