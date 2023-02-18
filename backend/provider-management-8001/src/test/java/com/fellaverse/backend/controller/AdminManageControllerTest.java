package com.fellaverse.backend.controller;

import com.fellaverse.backend.ManageServer_8001;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = ManageServer_8001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("AdminManage Controller Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminManageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JWTTokenService jwtTokenService;

    private Map<String, Object> map;

    @BeforeAll
    void setUp() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        Map<String, Object> resource = new HashMap<>();
        List<String> roleNames = new ArrayList<>();
        roleNames.add("SuperAdmin");
        resource.put("roles", roleNames);
        map.put("resource", resource);
        this.map = map;
    }
    
    @Test
    @Transactional
    void findAllAdmin() throws Exception {
        String token = "eyJtb2R1bGUiOiJwcm92aWRlci10b2tlbiIsImFsZyI6IkhTMjU2In0.eyJzaXRlIjoid3d3LmZlbGxhdmVyc2UuY29tIiwianRpIjoiMSIsImlhdCI6MTY3NjA2NjIwOCwiaXNzIjoiZmVsbGFzIiwic3ViIjoie1wicm9sZXNcIjpbXCJTdXBlckFkbWluXCJdLFwidXNlcm5hbWVcIjpcIlN1cGVyQWRtaW5cIn0iLCJleHAiOjE2NzYwNjY4MDh9.pzuHXQf6x9YClPNbZcVFHOaO7G1_YJq_WfUEMKrHLcM";
//        String token = jwtTokenService.createToken(map.get("id").toString(), (Map<String, Object>) map.get("resource"));
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/management/admin")
//                        .header("fellaverse-token", token))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/management/admin").header("fellaverse-token", token);
        ResultActions perform = mockMvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult result = perform.andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println(response.getContentAsString());
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getContentAsString());
    }

    @Test
    void findAdminByCondition() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/management/admin/conditions")
                        .content("{\n" +
                                "    \"phoneNumber\": \"1234567890\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getContentAsString());
    }

    @Test
    @Transactional
    void addAdmin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/management/admin")
                        .content("{\n" +
                                "    \"username\": \"User08\",\n" +
                                "    \"email\": \"user08@user.com\",\n" +
                                "    \"password\": \"hello\",\n" +
                                "    \"phoneNumber\": \"1234567890\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Add admin success", response.getContentAsString());
    }

    @Test
    @Transactional
    void updateAdmin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/management/admin")
                        .content("{\n" +
                                "    \"id\": \"4\",\n" +
                                "    \"email\": \"user08@user.com\",\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Update admin success", response.getContentAsString());
    }

    @Test
    @Transactional
    void deleteAdmin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/management/admin/4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Delete admin success", response.getContentAsString());
    }

    @Test
    @Transactional
    void updateRoles() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/management/admin/4")
                        .content("{\n" +
                                "    \"roleId\": \"{2, 3}\",\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Update roles success", response.getContentAsString());
    }
}