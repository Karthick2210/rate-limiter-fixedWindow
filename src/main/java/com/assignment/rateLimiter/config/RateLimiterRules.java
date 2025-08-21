package com.assignment.rateLimiter.config;

import java.util.List;

public class RateLimiterRules {

    public enum By {USER_ID,CLIENT_ID,IP};
    private String api;
    private List<By> by;
    private  int limit;
    private long windowMs;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public List<By> getBy() {
        return by;
    }

    public void setBy(List<By> by) {
        this.by = by;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getWindowMs() {
        return windowMs;
    }

    public void setWindowMs(long windowMs) {
        this.windowMs = windowMs;
    }
}
