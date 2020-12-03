package com.ibar.demo;

import com.ibar.demo.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@EnableMongoAuditing
public class CrudOperationsApplication {



    public static void main(String[] args) {
        SpringApplication.run(CrudOperationsApplication.class, args);

    }

}
