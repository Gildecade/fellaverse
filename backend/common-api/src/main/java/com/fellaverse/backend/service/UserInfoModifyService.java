package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;

public interface UserInfoModifyService {
    /**
     * return status of registry
     * true = success, false = failure
     */
    User register(User user);

    void addFunctions(User user);

    /**
     * return status of changing password
     * true = success, false = failure
     */
    String forgetPassword(User user);
}
