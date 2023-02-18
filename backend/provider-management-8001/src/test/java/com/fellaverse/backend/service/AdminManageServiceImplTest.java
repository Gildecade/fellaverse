package com.fellaverse.backend.service;

import com.fellaverse.backend.ManageServer_8001;
import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.repository.AdminRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = ManageServer_8001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("AdminManage Service Test")
class AdminManageServiceImplTest {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminManageService adminManageService;
    @Autowired
    private PasswordEncryptService passwordEncryptService;

    @Test
    void findAllAdmin() {
        Assertions.assertNotNull(adminManageService.findAllAdmin());
    }

    @Test
    @Transactional
    void addAdmin() {
        Admin admin = new Admin();
        admin.setUsername("TestAdmin")
                .setPassword(passwordEncryptService.getEncryptedPassword("hello"))
                .setEmail("testadmin@admin.com")
                .setPhoneNumber("1234567890");
        adminManageService.addAdmin(admin);
        Assertions.assertEquals(admin, adminRepository.findByEmail(admin.getEmail()));
    }

    @Test
    @Transactional
    void deleteAdmin() {
        adminManageService.deleteAdmin(4L);
        Assertions.assertNull(adminManageService.findAdminById(4L));
    }

    @Test
    @Transactional
    void updateAdmin() {
        Admin admin = adminManageService.findAdminById(4L);
        String email = "test@test.com";
        admin.setEmail(email);
        adminManageService.updateAdmin(admin);
        Assertions.assertEquals(email, adminManageService.findAdminById(4L).getEmail());
    }

    @Test
    void findAdminById() {
        Assertions.assertNotNull(adminManageService.findAdminById(1L));
    }

    @Test
    @Transactional
    void findAdminByCondition() {
        Admin admin = new Admin();
        admin.setPhoneNumber("1234567890");
        Assertions.assertEquals(adminManageService.findAllAdmin(), adminManageService.findAdminByCondition(admin));
    }
}