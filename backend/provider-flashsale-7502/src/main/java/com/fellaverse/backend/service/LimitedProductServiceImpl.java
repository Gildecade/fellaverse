package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.repository.LimitedProductRepository;
import com.fellaverse.backend.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
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
        redisUtils.expire("LimitedProduct", 10, TimeUnit.MINUTES);
        return productList;
    }

    @Override
    public LimitedProduct findById(Long id) {
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id.toString());
        if (product != null) {
            String str = redisUtils.get("Quantity: " + id);
            if (str != null) {
                product.setQuantity(Integer.valueOf(str));
            }
            if (Objects.equals("1", redisUtils.get("Soldout: " + id))) {
                product.setQuantity(0);
            }
            redisUtils.setIfAbsent("Quantity: " + id, product.getQuantity(), 5, TimeUnit.MINUTES);
            return product;
        }
        LimitedProduct limitedProduct = limitedProductRepository.findById(id).orElse(null);
        redisUtils.setIfAbsent("Quantity: " + id, limitedProduct.getQuantity(), 5, TimeUnit.MINUTES);
        return limitedProduct;
    }

//    @Override
//    public Integer cacheQuantityById(Long id) {
//        String str = redisUtils.get("Quantity: " + id);
//        if (str != null) {
//            return Integer.valueOf(str);
//        }
//        if (Objects.equals("1", redisUtils.get("Soldout: " + id))) {
//            return 0;
//        }
//        LimitedProduct product = limitedProductRepository.findById(id).orElse(null);
//        Integer quantity = product.getQuantity();
//        redisUtils.setIfAbsent("Quantity: " + id, quantity, 5, TimeUnit.MINUTES);
//        return quantity;
//    }

    @Override
    public Boolean purchase(Long id, Integer purchaseQuantity) {
        if (Objects.equals("1", redisUtils.get("Soldout: " + id))) {
            return false;
        }
        Long result = redisUtils.decrBy("Quantity: " + id, purchaseQuantity);
        if (result >= 0) {
            return true;
        }
        Long incr = redisUtils.incrBy("Quantity: " + id, purchaseQuantity);
        if (incr <= 0) {
            updateQuantityInDB(id, 0);
        }
        return false;
    }

    @Override
    @Async
    public void rollBack(Long id, Integer purchaseQuantity) {
        redisUtils.incrBy("Quantity: " + id, purchaseQuantity);
        if (Objects.equals("1", redisUtils.get("Soldout: " + id))) {
            redisUtils.delete("Soldout: " + id);
            updateQuantityInDB(id, purchaseQuantity);
        }
    }

    @Override
    @Async
    public void updateQuantityInDB(Long id, Integer quantity) {
        if (Objects.equals("1", redisUtils.get("Soldout: " + id))) {
            return;
        }
        LimitedProduct limitedProduct;
        if (quantity == 0) {
            limitedProduct = limitedProductRepository.findByIdAndQuantityGreaterThan(id, 0);
            if (limitedProduct != null) {
                limitedProductRepository.save(limitedProduct.setProductStatus(ProductStatus.UNAVAILABLE).setQuantity(quantity));
            }
            redisUtils.setEx("Soldout: " + id, "1", 8, TimeUnit.MINUTES);
        } else {
            limitedProduct = limitedProductRepository.findByIdAndQuantity(id, 0);
            if (limitedProduct != null) {
                limitedProductRepository.save(limitedProduct.setProductStatus(ProductStatus.ACTIVE).setQuantity(quantity));
            }
        }
        redisUtils.delete("LimitedProduct");
    }
}
