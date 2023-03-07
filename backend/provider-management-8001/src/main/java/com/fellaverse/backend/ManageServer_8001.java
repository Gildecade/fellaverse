package com.fellaverse.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ManageServer_8001 {
    public static void main(String[] args) {
        SpringApplication.run(ManageServer_8001.class, args);
    }
}
