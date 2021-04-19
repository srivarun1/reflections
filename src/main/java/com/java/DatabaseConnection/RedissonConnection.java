package com.java.DatabaseConnection;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConnection {

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson)
    {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean (destroyMethod = "shutdown")
    public RedissonClient redisson(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
}
