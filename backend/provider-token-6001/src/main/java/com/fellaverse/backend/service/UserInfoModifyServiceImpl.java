package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.bean.UserFunction;
import com.fellaverse.backend.bean.UserFunctionId;
import com.fellaverse.backend.dto.UserDTO;
import com.fellaverse.backend.dto.UserRegisterDTO;
import com.fellaverse.backend.mapper.UserRegisterMapper;
import com.fellaverse.backend.repository.FunctionRepository;
import com.fellaverse.backend.repository.UserFunctionRepository;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoModifyServiceImpl implements UserInfoModifyService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private UserFunctionRepository userFunctionRepository;
    private UserRegisterMapper userRegisterMapper;

    @Override
    public Boolean register(UserRegisterDTO userRegisterDTO) {
        User user = userRepository.save(userRegisterMapper.toEntity(userRegisterDTO));
        Long userId = user.getId();
        List<Function> functions = functionRepository.findByFunctionNameNotContains("course");
        for (Function function : functions) {
            UserFunctionId userFunctionId = new UserFunctionId(function.getId(), userId);
            UserFunction userFunction = new UserFunction();
            userFunction.setId(userFunctionId);
            userFunctionRepository.save(userFunction);
        }
        return true;
    }

    @Override
    public Boolean forgetPassword(UserDTO userDTO) {
        return null;
    }
}
