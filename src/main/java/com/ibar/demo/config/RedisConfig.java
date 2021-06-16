package com.ibar.demo.config;

import com.ibar.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class RedisConfig {

//    @Bean
//    public RedisTemplate<Long, User> redisTemplate() {
//        final RedisTemplate<Long, User> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        return template;
//    }
//
//
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConFactory
//                = new JedisConnectionFactory();
//
//        return jedisConFactory;
//    }
//
//    @Configuration
//    class AppConfig {
//
//        @Bean
//        public LettuceConnectionFactory redisConnectionFactory() {
//
//            return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
//        }
//    }
}
