package com.ibar.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibar.demo.model.User;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class Serializer {
    public static RedisSerializer<Long> createRedisSerializerForLongClass() {
        return new RedisSerializer<Long>() {
            @Override
            public byte[] serialize(Long aLong) throws SerializationException {
                return aLong.toString().getBytes();
            }

            @Override
            public Long deserialize(byte[] bytes) throws SerializationException {
                return Long.parseLong(new String(bytes));
            }
        };

    }

    public static RedisSerializer<User> createRedisSerializerForUserClass() {
        return new RedisSerializer<User>() {
            @Override
            public byte[] serialize(User user) throws SerializationException {
                try {
                    return new ObjectMapper().writeValueAsBytes(user);
                } catch (JsonProcessingException ex) {
                    return null;
                }

            }

            @Override
            public User deserialize(byte[] bytes) throws SerializationException {
                try {
                    return new ObjectMapper().readValue(new String(bytes), User.class);
                } catch (JsonProcessingException ex) {
                    return null;
                }
            }
        };
    }
}
