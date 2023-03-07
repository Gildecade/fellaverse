package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.LimitedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitedProductRepository extends JpaRepository<LimitedProduct, Long> {
}