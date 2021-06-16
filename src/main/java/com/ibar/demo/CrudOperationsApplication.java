package com.ibar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
//@EnableMongoRepositories(basePackageClasses = PhotoRepository.class)
//@EnableMongoAuditing
@EnableSwagger2

public class CrudOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudOperationsApplication.class, args);

    }
}
