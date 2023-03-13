package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;

public interface LimitedProductShopService extends LimitedProductService{

    void cacheQuantityById(Long id);
    Boolean purchase(Long id, Integer purchaseQuantity);
    void rollBack(Long id, Integer purchaseQuantity);
    void updateQuantityInDB(Long id, Integer purchaseQuantity);

    void placeOrder(LimitedProductPurchaseDTO purchaseDTO, Long orderId);

    void pay(Long orderId, Long userId);

    void expire(Long orderId);
}
