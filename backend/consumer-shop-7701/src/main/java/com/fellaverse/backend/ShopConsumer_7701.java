package com.fellaverse.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication  // auto enable eureka client
@EnableFeignClients
public class ShopConsumer_7701 {
    public static void main(String[] args) {
        SpringApplication.run(ShopConsumer_7701.class, args);
    }
}

