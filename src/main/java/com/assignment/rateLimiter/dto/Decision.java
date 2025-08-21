package com.assignment.rateLimiter.dto;

public class Decision {
    private final boolean allowed;
    private final long remaining;
    private final long resetMs;
    private final String reason;

    public Decision(boolean allowed, long remaining, long resetMs, String reason) {
        this.allowed = allowed;
        this.remaining = remaining;
        this.resetMs = resetMs;
        this.reason = reason;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public long getRemaining() {
        return remaining;
    }

    public long getResetMs() {
        return resetMs;
    }

    public String getReason() {
        return reason;
    }
}


