package com.fellaverse.backend.test;

import com.fellaverse.backend.jwt.StartJWTConfiguration;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StartJWTConfiguration.class)
public class TestEncryptService {
    @Autowired
    private PasswordEncryptService passwordEncryptService;

    @Test
    public void testCreatePassword() {
        System.out.println(this.passwordEncryptService.getEncryptedPassword("hello"));
    }
}
