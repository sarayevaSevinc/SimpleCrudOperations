package com.ibar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
//@EnableMongoRepositories(basePackageClasses = PhotoRepository.class)
//@EnableMongoAuditing
@EnableSwagger2
@EnableCaching
public class CrudOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudOperationsApplication.class, args);

    }
}
