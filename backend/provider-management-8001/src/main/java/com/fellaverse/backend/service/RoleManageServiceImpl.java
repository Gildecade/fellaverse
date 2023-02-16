package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.projection.RoleInfo;
import com.fellaverse.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleManageServiceImpl implements RoleManageService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<String> findRoleNameByAdminId(Long adminId) {
        return roleRepository.findByAdmins_Id(adminId).stream().map(RoleInfo::getRoleName).collect(Collectors.toList());
    }

    @Override
    public List<Role> findRoleByIds(List<Long> roleIds) {
        return roleRepository.findByIdIn(roleIds);
    }
}
