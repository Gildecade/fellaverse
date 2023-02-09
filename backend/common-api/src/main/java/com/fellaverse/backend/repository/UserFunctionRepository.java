package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.UserFunction;
import com.fellaverse.backend.bean.UserFunctionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFunctionRepository extends JpaRepository<UserFunction, UserFunctionId> {
}