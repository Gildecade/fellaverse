package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.FlashSaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashSaleOrderRepository extends JpaRepository<FlashSaleOrder, Long> {
}