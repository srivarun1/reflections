package com.java.UserManagement;

import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimiterImpl implements  RateLimiter{
    @Autowired
    private RedissonClient redis;

    private static final int RATE_LIMIT = 10;

    public RateLimiterImpl(RedissonClient redis)
    {
        this.redis = redis;
    }

    @Override
    public boolean isWithinRateLimit(String uuid) {
        RMapCache<String,Integer> rateLimiterMap = redis.getMapCache("RateLimiterMap");
        rateLimiterMap.put(uuid,rateLimiterMap.getOrDefault(uuid,0)+1,2, TimeUnit.SECONDS);

        if(rateLimiterMap.get(uuid) > RATE_LIMIT)
            return false;

        return true;
    }


}
