package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;

import java.util.Set;

public interface UserManageService {
    /**
     * return true for successful update a certain user's balance or status
     */
    Boolean updateUserBalanceStatus(UserBalanceStatusDTO userBalanceStatusDTO);

    /**
     * return user object by username
     */
    Set<User> findUserByUsername(String userName);

    /**
     * return user object by user email
     */
    Set<User> findUserByEmail(String userEmail);

    Set<User> findAllUser();

    User findUserById(Long userId);

}
