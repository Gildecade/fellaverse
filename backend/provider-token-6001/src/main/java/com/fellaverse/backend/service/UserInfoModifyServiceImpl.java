package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.repository.FunctionRepository;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserInfoModifyServiceImpl implements UserInfoModifyService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FunctionRepository functionRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    @Async
    public void addFunctions(User user) {
        Set<Function> functions = functionRepository.findByFunctionNameNotContains("course");
        user.getFunctions().addAll(functions);
        userRepository.save(user);
    }

    @Override
    public String forgetPassword(User user) {
        User oldUser = userRepository.findByUsername(user.getUsername());
        if (oldUser != null) {
            if (user.getEmail().equals(oldUser.getEmail()) && user.getPhoneNumber().equals(oldUser.getPhoneNumber())) {
                if (user.getPassword().equals(oldUser.getPassword())) {
                    return "New password cannot be the same as old password!";
                } else {
                    oldUser.setPassword(user.getPassword());
                    userRepository.save(oldUser);
                    return "Password reset successfully!";
                }
            }
        }
        return "No such user!";
    }
}
