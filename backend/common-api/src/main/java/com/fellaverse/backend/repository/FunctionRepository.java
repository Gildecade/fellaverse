package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.projection.FunctionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface FunctionRepository extends JpaRepository<Function, Long> {
    long countByUsers_Id(Long id);
    long countByFunctionNameNotContains(String functionName);
    @Query("select f from Function f inner join UserFunction uf on f.id = uf.function.id and uf.user.id = ?1")
    List<FunctionInfo> findByUsers_Id(Long id);

    @Query("select f from Function f where f.functionName not like concat('%', ?1, '%')")
    Set<Function> findByFunctionNameNotContains(String functionName);

    @Query("select f from Function f inner join UserFunction uf on f.id = uf.function.id and uf.user.id = ?1")
    List<Function> findByUserId(Long id);

}