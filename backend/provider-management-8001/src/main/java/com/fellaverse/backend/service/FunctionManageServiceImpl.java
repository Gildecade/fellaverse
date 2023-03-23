package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.repository.FunctionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Service
public class FunctionManageServiceImpl implements FunctionManageService {
    @Autowired
    private FunctionRepository functionRepository;

    @Override
    public Function findFunctionById(Long id) {
        return functionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Function> findAllFunctions() {
        return functionRepository.findAll();
    }

    @Override
    public boolean addFunction(Function function) {
        if (functionRepository.findByFunctionName(function.getFunctionName()) != null)
            return false;
        else {
            functionRepository.save(function);
            return true;
        }
    }

    @Override
    public boolean deleteFunction(Long id) {
        if (functionRepository.existsById(id)) {
            functionRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean updateFunction(Function function) {
        if (functionRepository.findById(function.getId()) == null)
            return false;
        else {
            functionRepository.save(function);
            return true;
        }
    }
    @Override
    public Set<Function> findFunctionByKeyword(String keyword) {
        return functionRepository.findByFunctionNameContains(keyword);
    }
}
