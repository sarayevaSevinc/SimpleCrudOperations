package com.ibar.demo.repositories;

import com.ibar.demo.config.Serializer;
import com.ibar.demo.model.User;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class RedisRepository {
    private final String REDIS_KEY = "USER";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    public RedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(Serializer.createRedisSerializerForLongClass());

        redisTemplate.setHashValueSerializer(Serializer.createRedisSerializerForUserClass());
    }

    public void addUser(User user) {
        log.info("adding user to redis...");
        this.hashOperations.put(REDIS_KEY, user.getId(), user);
    }

    public User getUser(long userId) {
        log.info("getting user from redis");
        return (User) this.hashOperations.get(REDIS_KEY, userId);
    }

    public void getAllUser() {
        log.info("getting all users from redis");
        Map<Long, User> map = this.hashOperations.entries(REDIS_KEY);
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue().getName());
        }
    }


}
