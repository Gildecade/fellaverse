package com.fellaverse.backend.test;

import com.fellaverse.backend.TokenServer_6001;
import com.fellaverse.backend.dto.UserDTO;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = TokenServer_6001.class)
public class TestAuthService {
    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void testLoginSuccess() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("superadmin@admin.com")
                .setPassword(this.passwordEncryptService.getEncryptedPassword("hello"));
        System.out.println(this.authenticationService.login(userDTO));
    }

    @Test
    public void testLoginFailure() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("")
                .setPassword(this.passwordEncryptService.getEncryptedPassword("hello"));
        System.out.println(this.authenticationService.login(userDTO));
    }
}
