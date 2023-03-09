package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.repository.LimitedProductRepository;
import com.fellaverse.backend.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LimitedProductServiceImpl implements LimitedProductShopService {
    @Autowired
    private LimitedProductRepository limitedProductRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<LimitedProduct> findAll() {
        Map<Object, Object> products = redisUtils.hGetAll("LimitedProduct");
        if (!products.isEmpty()) {
            List<LimitedProduct> productListRedis = new ArrayList<>();
            for (Map.Entry<Object, Object> entry : products.entrySet()) {
                productListRedis.add((LimitedProduct) entry.getValue());
            }
            return productListRedis;
        }
        List<ProductStatus> status = new ArrayList<>();
        status.add(ProductStatus.ACTIVE);
        status.add(ProductStatus.UNAVAILABLE);
        List<LimitedProduct> productList = limitedProductRepository.findByProductStatusIn(status);
        Map<String, Object> map = new HashMap<>();
        for (LimitedProduct limitedProduct : productList) {
            map.put(limitedProduct.getId().toString(), limitedProduct);
        }
        redisUtils.hPutAll("LimitedProduct", map);
        redisUtils.expire("LimitedProduct", 5, TimeUnit.MINUTES);
        return productList;
    }

    @Override
    public LimitedProduct findById(Long id) {
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id.toString());
        if (product != null) {
            return product;
        }
        return limitedProductRepository.findById(id).orElse(null);
    }

    @Override
    public Integer cacheQuantityById(Long id) {
        String str = redisUtils.get("Quantity: " + id);
        if (str != null) {
            return Integer.valueOf(str);
        }
        LimitedProduct product = limitedProductRepository.findById(id).orElse(null);
        Integer quantity = product.getQuantity();
        redisUtils.set("Quantity: " + id, quantity);
        return quantity;
    }

    @Override
    public Boolean purchase(Long id, Integer purchaseQuantity) {
        Integer quantity = cacheQuantityById(id);
        if (quantity < purchaseQuantity) {
            return false;
        }
        redisUtils.watch("Quantity: " + id);
        redisUtils.multi();
        redisUtils.incrBy("Quantity: " + id, - purchaseQuantity);
        List<Object> exec = redisUtils.exec();
        if (exec!=null && !exec.isEmpty()) {
            LimitedProduct product = limitedProductRepository.findById(id).orElse(null);
            limitedProductRepository.save(product.setQuantity(quantity - purchaseQuantity));
            redisUtils.delete("LimitedProduct");
        }
        return true;
    }
}
