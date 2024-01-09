package com.example.mdbspringboot.pagebackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@ComponentScan("com.example.mdbspringboot.config")
@ComponentScan("com.example.mdbspringboot.userdata")
@EnableMongoRepositories("com.example.mdbspringboot.userdata")
@SpringBootApplication
public class PageBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageBackendApplication.class, args);
    }

}
