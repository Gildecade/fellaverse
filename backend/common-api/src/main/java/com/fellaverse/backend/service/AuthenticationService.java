package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.UserLoginDTO;

import java.util.Map;

public interface AuthenticationService {
    /**
     * return data in map after admin login
     * key = loginStatus, userId, name, roles or functions
     */
    Map<String, Object> login(UserLoginDTO userLoginDTO);
}
