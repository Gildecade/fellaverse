package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Order;

public interface OrderService {

    // add new order to order repository
    void addOrder(Order order);
}
