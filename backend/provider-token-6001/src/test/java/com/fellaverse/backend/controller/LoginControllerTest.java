package com.fellaverse.backend.controller;

import com.fellaverse.backend.TokenServer_6001;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.service.AuthenticationService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = TokenServer_6001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("Login Controller Test")
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Admin login controller success")
    public void testAdminLoginControllerSuccess() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/auth/login")
                .content("{\n" +
                        "    \"email\": \"superadmin@admin.com\",\n" +
                        "    \"password\": \"hello\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getContentAsString());
    }

    @Test
    @DisplayName("User login controller success")
    public void testUserLoginControllerSuccess() throws Exception {
        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                        .content("{\n" +
                                "    \"email\": \"user01@user.com\",\n" +
                                "    \"password\": \"hello\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse userResponse = userResult.getResponse();
        Assertions.assertEquals(200, userResponse.getStatus());
        Assertions.assertNotNull(userResponse.getContentAsString());
    }

    @Test
    @DisplayName("Login controller failure")
    public void testLoginControllerFail() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/auth/login")
                .content("{\n" +
                        "    \"email\": \"superain@admin.com\",\n" +
                        "    \"password\": \"hello\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(response.getContentAsString(), "");
    }

    @Test
    void parseToken() throws Exception {
        String token = "eyJtb2R1bGUiOiJwcm92aWRlci10b2tlbiIsImFsZyI6IkhTMjU2In0.eyJzaXRlIjoid3d3LmZlbGxhdmVyc2UuY29tIiwianRpIjoiMSIsImlhdCI6MTY3NTk1ODY0NiwiaXNzIjoiZmVsbGFzIiwic3ViIjoie1wicm9sZXNcIjpbXCJTaG9wQWRtaW5cIixcIldvcmtvdXRBZG1pblwiLFwiVHdlZXRBZG1pblwiXSxcInVzZXJuYW1lXCI6XCJTdXBlckFkbWluXCJ9IiwiZXhwIjoxNjc1OTU5MjQ2fQ.hqWGKCjn_IaieMeSiXThvdlZd6wFiXJ49zzBUImtDFg";
        MvcResult userResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/parse")
                        .param("token", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse userResponse = userResult.getResponse();
        Assertions.assertEquals(200, userResponse.getStatus());
        Assertions.assertNotNull(userResponse.getContentAsString());
    }
}