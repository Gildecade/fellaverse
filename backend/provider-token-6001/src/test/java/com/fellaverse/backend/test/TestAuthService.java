package com.fellaverse.backend.test;

import com.fellaverse.backend.TokenServer_6001;
import com.fellaverse.backend.dto.UserLoginDTO;
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

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = TokenServer_6001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("Provider token test: login")
public class TestAuthService {
    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Login service success")
    public void testLoginSuccess() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("superadmin@admin.com")
                .setPassword(this.passwordEncryptService.getEncryptedPassword("hello"));
        Assertions.assertNotNull(this.authenticationService.login(userLoginDTO));

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

    @Test
    @DisplayName("Login controller success")
    public void testLoginControllerSuccess() throws Exception {
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
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/token/login")
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
}
