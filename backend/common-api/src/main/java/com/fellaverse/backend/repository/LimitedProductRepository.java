package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.enumerator.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LimitedProductRepository extends JpaRepository<LimitedProduct, Long> {
    List<LimitedProduct> findByProductStatusIn(Collection<ProductStatus> productStatuses);
}