package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.FlashSaleOrder;
import com.fellaverse.backend.repository.FlashSaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashSaleOrderServiceImpl implements FlashSaleOrderService{
    @Autowired
    private FlashSaleOrderRepository flashSaleOrderRepository;
    @Override
    public List<FlashSaleOrder> findAll() {
        return flashSaleOrderRepository.findAll();
    }

    @Override
    public void addFlashSaleOrder(FlashSaleOrder flashSaleOrder) {
        flashSaleOrderRepository.save(flashSaleOrder);
    }
}
