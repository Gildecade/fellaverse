package com.fellaverse.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication  // auto enable eureka client
@EnableFeignClients
public class TokenConsumer_6002 {
    public static void main(String[] args) {
        SpringApplication.run(TokenConsumer_6002.class, args);
    }
}
