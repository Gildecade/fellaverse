package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.projection.FunctionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionRepository extends JpaRepository<Function, Long> {
    @Query("select f from Function f inner join f.users users where users.id = ?1")
    List<FunctionInfo> findByUsers_Id(Long id);
}