package com.redis_study.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RedissonConfig {

    @Bean(destroyMethod="shutdown")
    RedissonClient redisson() throws IOException {
        Config config = new Config();
        //可以用"redis://"来启用SSL连接
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setPassword("redis123");
        return Redisson.create(config);
    }

}