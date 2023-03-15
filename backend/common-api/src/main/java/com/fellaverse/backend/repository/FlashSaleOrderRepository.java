package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.FlashSaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashSaleOrderRepository extends JpaRepository<FlashSaleOrder, Long> {
    List<FlashSaleOrder> findByUser_Id(Long id);
}