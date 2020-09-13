package cn.codewoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(MyRedisSerializer.KEYSERIALIZER);
        template.setHashKeySerializer(MyRedisSerializer.KEYSERIALIZER);
        template.setValueSerializer(MyRedisSerializer.VALUESERIALIZER);
        template.setHashKeySerializer(MyRedisSerializer.VALUESERIALIZER);
        return template;
    }
}
