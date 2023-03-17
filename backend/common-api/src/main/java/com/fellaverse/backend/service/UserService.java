package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;

public interface UserService {

    User findUserById(Long userId);
}
