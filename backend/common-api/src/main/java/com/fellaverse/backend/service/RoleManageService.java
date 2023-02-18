package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Role;

import java.util.List;

public interface RoleManageService {
    /**
     * find all roles. send all data to frontend
     * usage:
     * list all roles when role management
     * add roles to admin when update admin
     *
     * @return the list of saved entity.
     */
    List<Role> findAllRoles();

    /**
     * find all role name of a certain admin
     * usage:
     * show roles when list all admin
     *
     * @param adminId must not be {@literal null}.
     * @return the list of role name
     */
    List<String> findRoleNameByAdminId(Long adminId);

    /**
     * find all roles by list of role IDs
     * usage:
     * update admin roles when update admin
     *
     * @param roleIds must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     */
    List<Role> findRoleByIds(List<Long> roleIds);

    Role findRoleById(Long id);

    void addRole(Role role);

    void deleteRole(Long id);

    void updateRole(Role role);
}
