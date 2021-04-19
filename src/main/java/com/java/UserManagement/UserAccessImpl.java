package com.java.UserManagement;

import com.java.Model.Credential;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
public class UserAccessImpl implements UserAccess{

    @Autowired
    private RedissonClient redis;

    private static final String COOKIE_PATTERN = "";

    public UserAccessImpl(RedissonClient redis)
    {
        this.redis = redis;
    }


    @Override
    public String authenticate(String cookieId) {
        RMapCache<String,String> cookieCache = redis.getMapCache("CookieCache");
        return cookieCache.get(cookieId);
    }

    @Override
    public String generateCookie(String username) {
        RMapCache<String,String> cookieCache = redis.getMapCache("CookieCache");
        String uuid = String.valueOf(UUID.randomUUID());
        cookieCache.put(uuid,username,1, TimeUnit.DAYS);
        return uuid;
    }


}
