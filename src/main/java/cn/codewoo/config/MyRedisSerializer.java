package cn.codewoo.config;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class MyRedisSerializer {
    public static final StringRedisSerializer KEYSERIALIZER = new StringRedisSerializer();
    public static final GenericJackson2JsonRedisSerializer VALUESERIALIZER = new GenericJackson2JsonRedisSerializer();
}
