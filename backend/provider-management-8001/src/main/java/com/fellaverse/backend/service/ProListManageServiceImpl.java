package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.ProList;
import com.fellaverse.backend.repository.ProListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProListManageServiceImpl implements ProListManageService
{
    @Autowired
    private ProListRepository proListRepository;
    @Override
    public Boolean addEditProList(ProList proList) {
            if(proListRepository.save(proList) != null)
                return true;
            else
                return false;
    }

    @Override
    public Boolean deleteProList(Long id) {
        if (proListRepository.existsById(id)) {
            proListRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public Set<ProList> findAllProList() {
        List<ProList> sourceList = proListRepository.findAll();
        return new HashSet<>(sourceList);
    }

    @Override
    public ProList findProListById(Long id) {
        return proListRepository.findById(id).orElse(null);
    }

}
