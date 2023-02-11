package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Role;

import java.util.List;

public interface RoleManageService {
    List<Role> findRoleByIds(List<Long> roleIds);
}
