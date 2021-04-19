package com.java.UserManagement;

public interface RateLimiter {

    public boolean isWithinRateLimit(String uuid);
}
