package com.rummikub.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class WorkInProgressApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkInProgressApplication.class, args);
    }
}