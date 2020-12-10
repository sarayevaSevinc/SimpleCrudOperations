package com.ibar.demo;

import com.ibar.demo.repositories.PhotoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@EnableMongoRepositories(basePackageClasses = PhotoRepository.class)
//@EnableMongoAuditing
public class CrudOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudOperationsApplication.class, args);

    }

}
