package com.fellaverse.backend.service;

public interface LimitedProductShopService extends LimitedProductService{

    Integer cacheQuantityById(Long id);
    Boolean purchase(Long id, Integer purchaseQuantity);
}
