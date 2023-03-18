package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
