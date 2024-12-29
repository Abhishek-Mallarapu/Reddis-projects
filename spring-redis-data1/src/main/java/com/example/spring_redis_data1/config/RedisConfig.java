package com.example.spring_redis_data1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    // Configure Jedis connection to Redis
    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    // Configure RedisTemplate to handle Product objects
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());

        // Set the key serializer for Redis keys (String type)
        template.setKeySerializer(new StringRedisSerializer());

        // Set the serializer for hash keys (String type)
        template.setHashKeySerializer(new StringRedisSerializer());

        // Set the value serializer for objects (Product in your case)
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        // Set the serializer for hash values (Product objects)
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}
