package com.ibar.demo.repositories;


import com.ibar.demo.model.OTP;
import lombok.extern.log4j.Log4j2;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class RedisOtpRepository {
    private final String REDIS_OTP_KEY = "OTP";
    private RedissonClient client;
    private RMap<String, OTP> otps;

    public RedisOtpRepository() {
        this.client = Redisson.create();
        this.otps = client.getMap(REDIS_OTP_KEY);


    }

    public void addOtp(long userId, OTP otp) {
        log.info("adding otp to redis...");
        otps.put(String.valueOf(userId), otp);
    }

    public OTP getOtp(long userId) {
        log.info("getting otp from redis...");
        Object test = otps.remove(String.valueOf(userId));
        log.info(test == null);
        return (OTP) test;
    }
}
