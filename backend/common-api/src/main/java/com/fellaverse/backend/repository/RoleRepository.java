package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.projection.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.id in ?1")
    List<Role> findByIdIn(List<Long> ids);
    List<RoleInfo> findByAdmins_Id(Long id);
}
