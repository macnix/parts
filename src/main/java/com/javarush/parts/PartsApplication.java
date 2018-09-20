package com.javarush.parts;

import com.javarush.parts.service.PartService;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.javarush.parts.dao")
@ComponentScan("com.javarush.parts.service")
public class PartsApplication {

    public static void main(String[] args) {
        SpringApplication.run (PartsApplication.class, args);
    }

}
