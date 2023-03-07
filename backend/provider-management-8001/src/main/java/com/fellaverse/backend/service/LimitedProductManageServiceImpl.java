package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.repository.LimitedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitedProductManageServiceImpl implements LimitedProductManageService{
    @Autowired
    private LimitedProductRepository limitedProductRepository;

    @Override
    public void addLimitedProduct(LimitedProduct limitedProduct) {
        limitedProductRepository.save(limitedProduct);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = "LimitedProductAll"), @CacheEvict(value = "LimitedProduct", key = "#id")}
    )
    public void deleteLimitedProduct(Long id) {
        limitedProductRepository.deleteById(id);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = "LimitedProductAll")},
            put = {@CachePut(value = "LimitedProduct", key = "#limitedProduct.getId()")}
    )
    public LimitedProduct updateLimitedProduct(LimitedProduct limitedProduct) {
        return limitedProductRepository.save(limitedProduct);
    }

    @Override
    @Cacheable(value = "LimitedProductAll")
    public List<LimitedProduct> findAll() {
        return limitedProductRepository.findAll();
    }

    @Override
    @Cacheable(value = "LimitedProduct", key = "#id")
    public LimitedProduct findById(Long id) {
        return limitedProductRepository.findById(id).orElse(null);
    }

    @Override
    public List<LimitedProduct> findByKeywords(String keyword) {
        return null;
    }

    @Override
    public List<LimitedProduct> findByConditions(LimitedProduct limitedProduct) {
        return null;
    }
}
