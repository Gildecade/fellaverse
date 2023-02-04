package com.fellaverse.backend.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "fellaverse.security.config.password.encrypt")
public class EncryptConfig {
    private Integer repeat;
    private String salt;
}
