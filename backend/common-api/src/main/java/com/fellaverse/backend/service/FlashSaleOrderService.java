package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.FlashSaleOrder;

import java.util.List;

public interface FlashSaleOrderService {
    List<FlashSaleOrder> findAll();

    void addFlashSaleOrder(FlashSaleOrder flashSaleOrder);
}
