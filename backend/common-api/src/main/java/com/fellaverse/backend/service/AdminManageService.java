package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Admin;

import java.util.List;
public interface AdminManageService {
    List<Admin> findAllAdmin();

    void addAdmin(Admin admin);

    void deleteAdmin(Long id);

    void updateAdmin(Admin admin);

    Admin findAdminById(Long id);

    List<Admin> findAdminByCondition(Admin admin);
}
