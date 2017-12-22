package com.thinkme.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    String prefix = "adam:";

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            /** 重写生成key方法 */
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                if (cacheable != null) {
                    String[] cacheNames = cacheable.value();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cachePut != null) {
                    String[] cacheNames = cachePut.value();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cacheEvict != null) {
                    String[] cacheNames = cacheEvict.value();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (cacheConfig != null && sb.toString().equals(prefix)) {
                    String[] cacheNames = cacheConfig.cacheNames();
                    if (ArrayUtils.isNotEmpty(cacheNames)) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (sb.toString().equals(prefix)) {
                    sb.append(o.getClass().getName()).append(".").append(method.getName());
                }
                sb.append(":");
                if (objects != null) {
                    for (Object object : objects) {
                        sb.append(JSON.toJSONString(object));
                    }
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().prefixKeysWith(prefix);
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory).cacheDefaults(redisCacheConfiguration).build();
    }
}
