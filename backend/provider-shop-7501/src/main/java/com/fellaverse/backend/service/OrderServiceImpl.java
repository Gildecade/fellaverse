package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) { orderRepository.save(order);}

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }


}
