package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;

import java.util.List;
import java.util.Set;

public interface FunctionManageService {

    Function findFunctionById(Long id);
    List<Function> findAllFunctions();

    boolean addFunction(Function function);

    boolean deleteFunction(Long id);

    boolean updateFunction(Function function);

    Set<Function> findFunctionByKeyword(String keyword);
}
