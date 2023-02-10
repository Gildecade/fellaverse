package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;

public interface UserInfoModifyService {
    /**
     * return status of registry
     * true = success, false = failure
     */
    public User register(User user);

    public void addFunctions(User user);

    /**
     * return status of changing password
     * true = success, false = failure
     */
    public String forgetPassword(User user);
}
