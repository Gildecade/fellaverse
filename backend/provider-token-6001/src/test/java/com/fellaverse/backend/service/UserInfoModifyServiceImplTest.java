package com.fellaverse.backend.service;

import com.fellaverse.backend.TokenServer_6001;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.enumerator.UserStatus;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.repository.FunctionRepository;
import com.fellaverse.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = TokenServer_6001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("User Info Modify Service Test")
class UserInfoModifyServiceImplTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private UserInfoModifyService userInfoModifyService;
    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private ThreadPoolTaskExecutor asyncPoolTaskExecutor;

    @Test
    @Transactional
    void register() {
        User user = new User();
        user.setUsername("User07").setPassword(passwordEncryptService.getEncryptedPassword("hello"))
                .setEmail("user07@user.com").setPhoneNumber("1234567890").setWallet(1000L)
                .setStatus(UserStatus.valueOf("NORMAL"));
        Assertions.assertEquals(user, userInfoModifyService.register(user));
    }

    @Test
    @Transactional
    void addFunctions() throws InterruptedException {
        User user = new User();
        user.setId(6L).setUsername("User06").setPassword(passwordEncryptService.getEncryptedPassword("hello"))
                .setEmail("user06@user.com").setPhoneNumber("1234567890").setWallet(1000L)
                .setStatus(UserStatus.valueOf("NORMAL"));
        userInfoModifyService.addFunctions(user);
        asyncPoolTaskExecutor.getThreadPoolExecutor().awaitTermination(500, TimeUnit.MILLISECONDS);
        Assertions.assertEquals(functionRepository.countByFunctionNameNotContains("course"), functionRepository.countByUsers_Id(6L));
    }

    @Test
    @Transactional
    void forgetPassword() {
        User user = new User();
        user.setId(6L).setUsername("User06").setPassword(passwordEncryptService.getEncryptedPassword("helloooo"))
                .setEmail("user06@user.com").setPhoneNumber("1234567890").setWallet(1000L)
                .setStatus(UserStatus.valueOf("NORMAL"));
        userInfoModifyService.forgetPassword(user);
        Assertions.assertEquals("ZJFpuUtdozARIm/KfCn0FA==", userRepository.findByUsername("User06").getPassword());
    }
}