package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Id(Long id);
    List<Order> findByProduct_Id(Long id);
}