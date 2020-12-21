package com.ibar.demo.repositories;

import com.ibar.demo.model.User;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class RedisRepository {
    private HashOperations hashOperations;

    public RedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void addUser(User user) {
        log.info("adding user to redis...");
        this.hashOperations.put("USER", user.getId(), user);
    }

    public User getUser(long userId) {
        log.info("getting user from redis");
        return (User) this.hashOperations.get("USER", userId);
    }

    public void getAllUser() {
        log.info("getting all users from redis");
        Map<Long, User> map = this.hashOperations.entries("USER");
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            log.info(entry.getKey() + " " + entry.getValue().getName());
        }
    }


}
