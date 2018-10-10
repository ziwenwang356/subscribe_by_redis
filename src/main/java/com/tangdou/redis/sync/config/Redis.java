package com.tangdou.redis.sync.config;

import com.tangdou.redis.sync.utils.RedisShuffler;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Redis {

    //    @Value("${spring.redis.cluster.nodes}")
    private static String cluster = "127.0.0.1:6379";


    public static RedisShuffler getRedis() {
        RedisShuffler redisShuffler = new RedisShuffler(Arrays.asList(cluster.split("\\,")));
        return redisShuffler;
    }

}
