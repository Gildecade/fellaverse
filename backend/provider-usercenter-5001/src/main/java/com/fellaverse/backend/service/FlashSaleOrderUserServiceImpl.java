package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.FlashSaleOrder;
import com.fellaverse.backend.repository.FlashSaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashSaleOrderUserServiceImpl implements FlashSaleOrderUserService {
    @Autowired
    private FlashSaleOrderRepository repository;
    @Override
    public List<FlashSaleOrder> findAll(Long id) {
        return repository.findByUser_Id(id);
    }
}
