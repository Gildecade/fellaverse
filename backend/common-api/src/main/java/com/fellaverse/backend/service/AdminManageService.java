package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
public interface AdminManageService {

    /**
     * return true for successful update a certain user's balance or status
     */
    public Boolean updateUserBalanceStatus(UserBalanceStatusDTO userBalanceStatusDTO);

    /**
     * return user object by username
     */
    public User findUserByUsername(String userName);

    /**
     * return user object by user email
     */
    public User findUserByEmail(String userEmail);
}
