package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.UserDTO;

public interface UserInfoModifyService {
    /**
     * return status of registry
     * true = success, false = failure
     */
    public Boolean register(UserDTO userDTO);

    /**
     * return status of changing password
     * true = success, false = failure
     */
    public Boolean forgetPassword(UserDTO userDTO);
}
