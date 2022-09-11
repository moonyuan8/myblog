//package com.sjy.handler;
//
///**
// * @Author 傻傻的远
// * @Date 2021 11 30 20:30
// * @Description: 项目名称 index.html 路径 com.sjy.handler
// * @Function:
// */
//
//import java.lang.reflect.Method;
//
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//
//@Configuration
//@EnableCaching
//public class RedisCacheConfig extends CachingConfigurerSupport {
//    private volatile JedisConnectionFactory jedisConnectionFactory;
//    private volatile RedisTemplate<String, String> redisTemplate;
//    private volatile RedisCacheManager redisCacheManager;
//
//    public RedisCacheConfig() {
//        super();
//    }
//
//    public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,
//                            RedisCacheManager redisCacheManager) {
//        this.jedisConnectionFactory = jedisConnectionFactory;
//        this.redisTemplate = redisTemplate;
//        this.redisCacheManager = redisCacheManager;
//    }
//
//    public JedisConnectionFactory getJedisConnecionFactory() {
//        return jedisConnectionFactory;
//    }
//
//    public RedisTemplate<String, String> getRedisTemplate() {
//        return redisTemplate;
//    }
//
//    public RedisCacheManager getRedisCacheManager() {
//        return redisCacheManager;
//    }
//
//    @Bean
//    public KeyGenerator customKeyGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... objects) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : objects) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }
//}
//
