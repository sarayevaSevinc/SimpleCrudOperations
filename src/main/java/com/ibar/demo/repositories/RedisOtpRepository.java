package com.ibar.demo.repositories;

import com.ibar.demo.config.Serializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class RedisOtpRepository {
    private final String REDIS_OTP_KEY = "OTP";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    public RedisOtpRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(Serializer.createRedisSerializerForLongClass());

        redisTemplate.setHashValueSerializer((new StringRedisSerializer()));
    }

    public void addOtp(long userId, String otp) {
        log.info("adding otp to redis...");
        hashOperations.put(REDIS_OTP_KEY, userId, otp);
    }

    public String getOtp(long userId) {
        log.info("getting otp from redis...");
        return (String) hashOperations.get(REDIS_OTP_KEY, userId);
    }
}
