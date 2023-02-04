package com.fellaverse.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // server boot class, allow others to register with self
public class EurekaServer_8761 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_8761.class, args);
    }
}
