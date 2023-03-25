package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.bean.ProList;

import java.util.Set;

public interface ProListManageService {

    Set<ProList> findAllProList();
    ProList findProListById(Long id);
    Boolean addEditProList(ProList proList);
    Boolean deleteProList(Long id);
}
