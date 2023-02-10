package com.fellaverse.backend.controller;

import com.fellaverse.backend.TokenServer_6001;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = TokenServer_6001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("UserInfoModify Controller Test")
class UserInfoModifyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    @Transactional
    void register() throws Exception {
        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .content("{\n" +
                                "    \"username\": \"User08\",\n" +
                                "    \"email\": \"user08@user.com\",\n" +
                                "    \"password\": \"hello\",\n" +
                                "    \"phoneNumber\": \"1234567890\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse userResponse = userResult.getResponse();
        Assertions.assertEquals(200, userResponse.getStatus());
        Assertions.assertEquals("Register success", userResponse.getContentAsString());
    }

    @Test
    @Transactional
    void resetPassword() throws Exception {
        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/resetPassword")
                        .content("{\n" +
                                "    \"username\": \"User06\",\n" +
                                "    \"email\": \"user06@user.com\",\n" +
                                "    \"password\": \"helloooo\",\n" +
                                "    \"phoneNumber\": \"1234567890\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse userResponse = userResult.getResponse();
        Assertions.assertEquals(200, userResponse.getStatus());
        Assertions.assertEquals("Password reset successfully", userResponse.getContentAsString());
    }
}