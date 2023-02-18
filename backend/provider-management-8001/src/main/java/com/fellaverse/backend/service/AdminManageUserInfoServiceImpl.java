package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.mapper.UserBalanceStatusMapper;
import com.fellaverse.backend.repository.UserRepository;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminManageUserInfoServiceImpl implements AdminManageUserInfoService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBalanceStatusMapper userBalanceStatusMapper;
    @Override
    public Boolean updateUserBalanceStatus(UserBalanceStatusDTO userBalanceStatusDTO) {
        User user = userRepository.findById(userBalanceStatusDTO.getId()).orElse(null);
        if (user == null)
            return false;
        User user1 = userBalanceStatusMapper.partialUpdate(userBalanceStatusDTO, user);
        userRepository.save(user1);
        return true;
    }

    @Override
    public Set<User> findUserByUsername(String userName) {
        return userRepository.findByUsernameContains(userName);
    }

    @Override
    public Set<User> findUserByEmail(String userEmail) {
        return userRepository.findByEmailContains(userEmail);
    }
}
