package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserDTO;
import com.fellaverse.backend.dto.UserLoginDTO;
import com.fellaverse.backend.repository.AdminRepository;
import com.fellaverse.backend.repository.RoleInfo;
import com.fellaverse.backend.repository.RoleRepository;
import com.fellaverse.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Map<String, Object> login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        HashMap<String, Object> result = new HashMap<>();
        // find admin by using AdminId passed by frontend
        Admin admin = this.adminRepository.findByEmail(email);
        // if no such admin or wrong password
        if (admin == null || !admin.getPassword().equals(userLoginDTO.getPassword())) {
            // admin login fail
            User user = this.userRepository.findByEmail(email);
            // user login also fail
            if (user == null || !user.getPassword().equals(userLoginDTO.getPassword()))
            {
                result.put("status", false);
            } else {
                // user login succeed
                result.put("status", true);
                // store userId, username, functions and more useful information in token
                result.put("id", user.getId());
                Map<String, Object> resource = new HashMap<>();
                resource.put("username", user.getUsername());
                // TODO: add functions
                //List<Long> functionIds = this.functionRepository.findByUsers_Id(user.getId()).stream().map(FunctionInfo::getFunctionName).collect(Collectors.toList());;
                //resource.put("functions", functionIds);
                result.put("resource", resource);
            }
        } else {
            // admin login succeed
            result.put("status", true);
            // store adminId, username, roles and more useful information in token
            result.put("id", admin.getId());
            Map<String, Object> resource = new HashMap<>();
            resource.put("username", admin.getUsername());
            List<String> roleNames = this.roleRepository.findByAdmins_Id(admin.getId()).stream().map(RoleInfo::getRoleName).collect(Collectors.toList());
            resource.put("roles", roleNames);
            result.put("resource", resource);
        }
        return result;
    }
}
