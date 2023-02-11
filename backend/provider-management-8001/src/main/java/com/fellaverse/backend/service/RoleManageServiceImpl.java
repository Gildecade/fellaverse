package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManageServiceImpl implements RoleManageService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findRoleByIds(List<Long> roleIds) {
        return roleRepository.findByIdIn(roleIds);
    }
}
