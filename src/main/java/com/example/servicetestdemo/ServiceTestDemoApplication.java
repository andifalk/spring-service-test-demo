package com.example.servicetestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

@SpringBootApplication
public class ServiceTestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTestDemoApplication.class, args);
    }

    @Bean
    public IdGenerator idGenerator() {
        return new JdkIdGenerator();
    }
}
