package com.ibar.demo.repositories;

import com.ibar.demo.model.User;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class RedisUserRepository {
    private final String REDIS_USER_KEY = "USER";
    private RedissonClient client;
    private RMap<String, Object> users;

    public RedisUserRepository() {
        client = Redisson.create();
        users = client.getMap(REDIS_USER_KEY);
    }

    public void addUser(User user) {
        log.info("adding user to redis...");
        users.put(String.valueOf(user.getId()), user);
    }

    public User getUser(long userId) {
        log.info("getting user from redis");
        return (User) users.get(String.valueOf(userId));
    }

    public void getAllUser() {
        log.info("getting all users from redis");

        for (Map.Entry<String, Object> entry : users.entrySet()) {
            User user = (User) entry.getValue();
            log.info(entry.getKey() + " " + user.getName());
        }
    }


}
