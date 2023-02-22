package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.AdminRole;
import com.fellaverse.backend.bean.AdminRoleId;
import com.fellaverse.backend.projection.AdminRoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminRoleRepository extends JpaRepository<AdminRole, AdminRoleId> {
    List<AdminRoleInfo> findById_AdminId(Long adminId);

    @Modifying
    @Transactional
    @Query(value = "insert into admin_role (admin_id, role_id) values (?1, ?2)", nativeQuery = true)
    void insert(Long adminId, Long roleId);

    @Modifying
    @Transactional
    @Query(value = "delete from admin_role where admin_id = ?1 and role_id = ?2", nativeQuery = true)
    void delete(Long adminId, Long roleId);
}