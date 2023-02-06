package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.dto.UserDTO;

import java.util.Map;

public interface AuthenticationService {
    /**
     * return data in map after admin login
     * key = loginStatus, userId, name, roles or functions
     */
    public Map<String, Object> login(UserDTO userDTO);
}
