package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.UserFunction;
import com.fellaverse.backend.bean.UserFunctionId;
import com.fellaverse.backend.projection.UserFunctionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFunctionRepository extends JpaRepository<UserFunction, UserFunctionId> {
    List<UserFunction> findById_UserId(Long userId);

    @Modifying
    @Query(value = "insert into user_function (user_id, function_id) values (?1, ?2)", nativeQuery = true)
    void insert(Long userId, Long functionId);

    @Modifying
    @Query(value = "delete from user_function where user_id = ?1 and function_id = ?2", nativeQuery = true)
    void delete(Long userId, Long functionId);
}