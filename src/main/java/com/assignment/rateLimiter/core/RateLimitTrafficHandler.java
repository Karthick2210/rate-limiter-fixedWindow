package com.assignment.rateLimiter.core;

import com.assignment.rateLimiter.config.RateLimiterRules;
import com.assignment.rateLimiter.dto.Decision;
import com.assignment.rateLimiter.dto.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.List;

@Component
public class RateLimitTrafficHandler implements HandlerInterceptor {

    private final FixedWindowRateLimiter fixedWindowRateLimiter;

    public RateLimitTrafficHandler(FixedWindowRateLimiter fixedWindowRateLimiter) {
        this.fixedWindowRateLimiter = fixedWindowRateLimiter;
    }


    public boolean preReqHandler(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler) throws IOException {
        RequestContext requestContext =
                new RequestContext(
                        request.getRequestURI(),
                        request.getMethod(),
                        request.getHeader("X-User-Id") != null ? request.getHeader("X-User-Id") : "default User",
                        request.getHeader("X-Client-Id") != null ? request.getHeader("X-Client-Id") : "default Client",
                        request.getRemoteAddr()
                );

        RateLimiterRules rules = new RateLimiterRules();
        rules.setApi(request.getRequestURI());
        rules.setBy(List.of(RateLimiterRules.By.USER_ID));
        rules.setLimit(5);
        rules.setWindowMs(60000);

        Decision decision = fixedWindowRateLimiter.isAllowed(requestContext, rules);

        if (!decision.allowed()) {
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Rate limit exceeded. Try again after " + decision.resetMs() + " ms");
            return false; // block request
        }

        return true; // allow request
    }
}
