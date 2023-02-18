package com.fellaverse.backend.service;

import com.fellaverse.backend.TokenServer_6001;
import com.fellaverse.backend.dto.UserLoginDTO;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = TokenServer_6001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("Login Service Test")
class AuthenticationServiceImplTest {

    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    @DisplayName("Admin login service success")
    public void testAdminLoginSuccess() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("superadmin@admin.com")
                .setPassword(this.passwordEncryptService.getEncryptedPassword("hello"));
        Assertions.assertNotNull(this.authenticationService.login(userLoginDTO));
    }

    @Test
    @DisplayName("User login service success")
    public void testUserLoginSuccess() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("user01@user.com")
                .setPassword(this.passwordEncryptService.getEncryptedPassword("hello"));
        Assertions.assertNotNull(this.authenticationService.login(userLoginDTO));
    }

    @Test
    @DisplayName("Login service failure")
    public void testLoginFailure() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("")
                .setPassword(this.passwordEncryptService.getEncryptedPassword("hello"));
        Map<String, Object> result = new HashMap<>();
        result.put("status", false);
        Assertions.assertEquals(this.authenticationService.login(userLoginDTO), result);
    }
}