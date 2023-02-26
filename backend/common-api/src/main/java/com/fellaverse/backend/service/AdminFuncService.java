package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.bean.UserFunction;

import java.util.List;

public interface AdminFuncService {
    /**
     * return all functions that a certain user has
     */
    public List<Function> findFunctionByUserId(String userId);

    /**
     * return true when successfully added a new function to a certain user
     */
    public Boolean addNewFunc(UserFunction userFunction);

    /**
     * return true when successfully deleted a new function from a certain user
     */
    public Boolean deleteFunc(UserFunction userFunction);
}
