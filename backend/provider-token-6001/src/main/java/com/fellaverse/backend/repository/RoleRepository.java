package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
    List<RoleInfo> findByAdmins_Id(Long id);
}
