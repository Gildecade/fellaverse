package com.fellaverse.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "gateway.config.jwt")
public class GatewayJWTConfig {
    private List<String> skipAuthUrls;
    private String headerName;
}
