package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Order;
import com.fellaverse.backend.bean.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}