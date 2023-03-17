package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.FlashSaleOrder;

import java.util.List;

public interface FlashSaleOrderUserService {
    List<FlashSaleOrder> findAll(Long id);
}
