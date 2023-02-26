package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.mapper.UserBalanceStatusMapper;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserManageServiceImpl implements UserManageService {

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

    @Override
    public Set<User> findAllUser() {
        List<User> userList = userRepository.findAll();
        Set<User> userSet = new HashSet<>(userList);
        return userSet;
    }

    @Override
    public Boolean updateUserFunction(Long userId, Set<Function> functions) {
        return null;
    }
}
