package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.LimitedProduct;

public interface LimitedProductManageService extends LimitedProductService{
    void addLimitedProduct(LimitedProduct limitedProduct);
    void deleteLimitedProduct(Long id);
    LimitedProduct updateLimitedProduct(LimitedProduct limitedProduct);
}
