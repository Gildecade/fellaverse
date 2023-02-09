package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.UserDTO;
import com.fellaverse.backend.dto.UserRegisterDTO;

public interface UserInfoModifyService {
    /**
     * return status of registry
     * true = success, false = failure
     */
    public Boolean register(UserRegisterDTO userRegisterDTO);

    /**
     * return status of changing password
     * true = success, false = failure
     */
    public Boolean forgetPassword(UserDTO userDTO);
}
