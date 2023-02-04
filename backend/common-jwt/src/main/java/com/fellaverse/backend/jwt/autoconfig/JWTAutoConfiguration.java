package com.fellaverse.backend.jwt.autoconfig;

import com.fellaverse.backend.jwt.config.EncryptConfig;
import com.fellaverse.backend.jwt.config.JWTConfig;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import com.fellaverse.backend.jwt.service.JWTTokenServiceImpl;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.jwt.service.PasswordEncryptServiceImpl;
import com.fellaverse.backend.jwt.util.JWTMemberDataService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JWTConfig.class, EncryptConfig.class})
public class JWTAutoConfiguration {
    @Bean("tokenService")
    public JWTTokenService getTokenServiceBean() {
        return new JWTTokenServiceImpl();
    }

    @Bean("encryptService")
    public PasswordEncryptService getEncryptServiceBean() {
        return new PasswordEncryptServiceImpl();
    }

    @Bean("memberDataService")
    public JWTMemberDataService getMemberDataService () {
        return new JWTMemberDataService();
    }
}
