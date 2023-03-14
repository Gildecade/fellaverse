package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.repository.LimitedProductRepository;
import com.fellaverse.backend.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class LimitedProductManageServiceImpl implements LimitedProductManageService{
    @Autowired
    private LimitedProductRepository limitedProductRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void addLimitedProduct(LimitedProduct limitedProduct) {
        limitedProductRepository.save(limitedProduct);
        redisUtils.delete("LimitedProduct");
    }

    @Override
    public void deleteLimitedProduct(Long id) {
        limitedProductRepository.deleteById(id);
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id.toString());
        if (product != null) {
            redisUtils.hDelete("LimitedProduct", id.toString());
        }
    }

    @Override
    public void updateLimitedProduct(LimitedProduct limitedProduct) {
        limitedProductRepository.save(limitedProduct);
        String id = limitedProduct.getId().toString();
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id);
        if (product != null) {
            redisUtils.hPut("LimitedProduct", id, limitedProduct);
            redisUtils.expire("LimitedProduct", 2, TimeUnit.MINUTES);
        }
    }

    @Override
    public List<LimitedProduct> findAll() {
        return limitedProductRepository.findAll();
    }

    @Override
    public LimitedProduct findById(Long id) {
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id.toString());
        if (product != null) {
            return product;
        }
        return limitedProductRepository.findById(id).orElse(null);
    }
}
