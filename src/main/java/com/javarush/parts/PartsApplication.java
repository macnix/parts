package com.javarush.parts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.javarush.parts.dao")

public class PartsApplication {

    public static void main(String[] args) {
        SpringApplication.run (PartsApplication.class, args);
    }

}
