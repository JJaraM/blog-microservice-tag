package com.jjara.microservice.tag.configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

/**
 * Redis Configuration used to stablish the communication between multiple applications
 */
@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.configuration.host}") private String host;
    @Value("${spring.redis.configuration.port}") private int port;
    @Value("${spring.redis.configuration.password}") private String password;

    /**
     * Create the redis client based on the provided information from the properties files
     *
     * @return {@link RedisClient} with the redis client information
     */
    @Bean RedisClient getRedisClient() {
        var redisURI = new RedisURI();
        redisURI.setHost(host);
        redisURI.setPort(port);
        redisURI.setPassword(password);
        return RedisClient.create(redisURI);
    }

    /**
     * Creates the stateful publish subscribe connection
     *
     * @param client redis client that is toing to be sused to creat the sub connection
     * @return {@link StatefulRedisPubSubConnection} with the sub connection
     */
    @RefreshScope
    @Bean(destroyMethod = "close") StatefulRedisPubSubConnection<String, String> connection(RedisClient client) {
        return client.connectPubSub();
    }

    /**
     * Creates the redis connection factory instance
     *
     * @return {@link JedisConnectionFactory} with the connection details
     */
    @Bean JedisConnectionFactory jedisConnectionFactory() {
        var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));

        var jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));

        return new JedisConnectionFactory(redisStandaloneConfiguration,  jedisClientConfiguration.build());
    }
}
