package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Order;

import java.util.List;

public interface OrderService {

    // add new order to order repository
    void addOrder(Order order);

    List<Order> findByUserId(Long userId);
}
