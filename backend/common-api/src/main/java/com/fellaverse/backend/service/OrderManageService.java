package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Order;

import java.util.List;

public interface OrderManageService {

    List<Order> findAllOrder();

    void addOrder(Order order);

    void deleteOrder(Long id);

    void updateOrder(Order order);

    Order findOrderById(Long id);

    List<Order> findOrderByProductId(Long productId);

    List<Order> findOrderByUserId(Long userId);
    
}
