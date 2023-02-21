package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.repository.AdminRepository;
import com.fellaverse.backend.repository.AdminRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminManageServiceImpl implements AdminManageService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    @Override
    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin findAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> findAdminByCondition(Admin admin) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example<Admin> example = Example.of(admin, matcher);
        return adminRepository.findAll(example);
    }

    @Override
    public List<Long> findRoleIdsByAdminId(Long id) {
        return adminRoleRepository.findById_AdminId(id)
                .stream().map((adminRoleInfo -> adminRoleInfo.getRole().getId())).toList();
    }

    @Override
    public void insertRole(Long userId, Long roleId) {
        adminRoleRepository.insert(userId, roleId);
    }

    @Override
    public void deleteRole(Long userId, Long roleId) {
        adminRoleRepository.delete(userId, roleId);
    }
}
