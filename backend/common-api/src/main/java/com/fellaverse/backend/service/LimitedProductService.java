package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.LimitedProduct;

import java.util.List;

public interface LimitedProductService {
    List<LimitedProduct> findAll();
    LimitedProduct findById(Long id);
    List<LimitedProduct> findByKeywords(String keyword);
    List<LimitedProduct> findByConditions(LimitedProduct LimitedProduct);
}
