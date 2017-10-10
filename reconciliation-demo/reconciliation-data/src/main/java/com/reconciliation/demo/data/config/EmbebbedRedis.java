package com.reconciliation.demo.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.util.OS;
import redis.embedded.util.OsArchitecture;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

//@Configuration
public class EmbebbedRedis {


    @Value("${redis.data.host}")
    private String hostname;

    @Value("${redis.data.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {

        RedisExecProvider execProvider=RedisExecProvider.defaultProvider();
        execProvider.override(OS.WINDOWS, "redis-server-2.6.14.exe");
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName(hostname);
        jedisConFactory.setPort(redisPort);
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<String, String> stringTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, String> stringTemplate = new RedisTemplate<>();
        stringTemplate.setConnectionFactory(redisConnectionFactory);
        stringTemplate.setDefaultSerializer(new StringRedisSerializer());
        stringTemplate.afterPropertiesSet();

        return stringTemplate;
    }
}
