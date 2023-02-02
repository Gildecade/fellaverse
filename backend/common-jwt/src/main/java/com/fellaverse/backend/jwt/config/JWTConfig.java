package com.fellaverse.backend.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fellaverse.security.config.jwt")  // configuration in application.yaml
public class JWTConfig {
    private  String sign;
    private  String issuer;
    private  String secret;
    private Long expire;
}
