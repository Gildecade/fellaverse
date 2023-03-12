package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.FlashSaleOrder;
import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.delay.DelayQueueManager;
import com.fellaverse.backend.delay.DelayTask;
import com.fellaverse.backend.delay.OrderTask;
import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;
import com.fellaverse.backend.enumerator.OrderStatus;
import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.repository.FlashSaleOrderRepository;
import com.fellaverse.backend.repository.LimitedProductRepository;
import com.fellaverse.backend.repository.UserRepository;
import com.fellaverse.backend.util.RedisUtils;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private FlashSaleOrderRepository flashSaleOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private DelayQueueManager delayQueueManager;

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
            return product;
        }
        return limitedProductRepository.findById(id).orElse(null);
    }

    @Override
    public void cacheQuantityById(Long id) {
        String str = redisUtils.get("Quantity: " + id);
        if (str != null) {
            return;
        }
        if (redisUtils.get("SoldOut: " + id) != null) {
            redisUtils.setIfAbsent("Quantity: " + id, 0, 5, TimeUnit.MINUTES);
            return;
        }
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id.toString());
        if (product != null) {
            redisUtils.setIfAbsent("Quantity: " + id, product.getQuantity(), 5, TimeUnit.MINUTES);
            return;
        }
        LimitedProduct limitedProduct = limitedProductRepository.findById(id).orElse(null);
        redisUtils.setIfAbsent("Quantity: " + id, limitedProduct.getQuantity(), 5, TimeUnit.MINUTES);
    }

    @Override
    public Boolean purchase(Long id, Integer purchaseQuantity) {
        LimitedProduct product = (LimitedProduct) redisUtils.hGet("LimitedProduct", id.toString());
        Assert.notNull(product, "Your session is expired, please go back to homepage!");
        Assert.notNull(redisUtils.get("Quantity: " + id), "Your session is expired, please refresh!");
        Assert.isTrue(!LocalDateTime.now().isBefore(product.getSaleDateTime()), "Sale has not started!");
        if (redisUtils.get("SoldOut: " + id) != null) {
            return false;
        }
        Long result = redisUtils.decrBy("Quantity: " + id, purchaseQuantity);
        if (result >= 0) {
            return true;
        }
        Long incr = redisUtils.incrBy("Quantity: " + id, purchaseQuantity);
        if (incr <= 0) {
            redisUtils.setEx("SoldOut: " + id, "1", 8, TimeUnit.MINUTES);
        }
        return false;
    }

    @Override
    public void rollBack(Long id, Integer purchaseQuantity) {
        redisUtils.incrBy("Quantity: " + id, purchaseQuantity);
        if (redisUtils.get("SoldOut: " + id) != null) {
            redisUtils.delete("SoldOut: " + id);
            findAll();
        }
    }

    @Override
    @Async
    public void updateQuantityInDB(Long id, Integer quantity) {
        LimitedProduct limitedProduct = limitedProductRepository.findById(id).orElse(null);
        if (limitedProduct == null) {
            return;
        }
        int newQuantity = limitedProduct.getQuantity() + quantity;
        limitedProduct.setQuantity(newQuantity);
        if (newQuantity <= 0) {
            limitedProduct.setProductStatus(ProductStatus.UNAVAILABLE);
        } else {
            limitedProduct.setProductStatus(ProductStatus.ACTIVE);
        }
        limitedProductRepository.save(limitedProduct);
        redisUtils.delete("LimitedProduct");
    }

    @Override
    @Async
    public void placeOrder(LimitedProductPurchaseDTO purchaseDTO, Long orderId) {
        Long userId = purchaseDTO.getUserId();
        Float amount = findById(purchaseDTO.getId()).getPrice() * purchaseDTO.getQuantity();
        FlashSaleOrder order = new FlashSaleOrder();
        order.setId(orderId)
                .setQuantity(purchaseDTO.getQuantity())
                .setPurchaseDateTime(purchaseDTO.getPurchaseDateTime())
                .setAmount(amount)
                .setOrderStatus(OrderStatus.ACTIVE)
                .setUser(userRepository.findById(userId).orElse(null))
                .setLimitedProduct(limitedProductRepository.findById(purchaseDTO.getId()).orElse(null));
        flashSaleOrderRepository.save(order);
        delayQueueManager.put(new DelayTask(new OrderTask("Flash sale order " + orderId + " expired.", orderId), 1000 * 30));
    }

    @Override
    @Transactional
    public void pay(Long orderId) {
        FlashSaleOrder order = flashSaleOrderRepository.findById(orderId).orElse(null);
        Assert.notNull(order, "Error when finding your order, please try again!");
        Assert.isTrue(order.getOrderStatus().equals(OrderStatus.ACTIVE), "Order cannot be paid!");
        Long userId = order.getUser().getId();
        Float wallet = userRepository.lockUserWallet(userId);
        Long productId = order.getLimitedProduct().getId();
        Integer quantity = order.getQuantity();
        Float amount = findById(productId).getPrice() * quantity;
        Assert.isTrue(wallet >= amount, "Insufficient balance!");
        Assert.isTrue(redisUtils.setIfAbsent(orderId.toString(), 1, 1, TimeUnit.MINUTES), "Order has been cancelled!");
        try {
            updateQuantityInDB(productId, -quantity);
            Assert.isTrue(userRepository.updateUserWallet(userId, wallet - amount) == 1, "Update wallet failed!");
            Assert.notNull(flashSaleOrderRepository.save(order.setOrderStatus(OrderStatus.COMPLETED)), "Update order failed!");
        } finally {
            redisUtils.delete(orderId.toString());
        }
    }

    @Override
    @Transactional
    public void expire(Long orderId) {
        FlashSaleOrder order = flashSaleOrderRepository.findById(orderId).orElse(null);
        Assert.notNull(order, "Error when cancelling order: " + orderId + ", check if exits!");
        if (order.getOrderStatus() != OrderStatus.ACTIVE) {
            return;
        }
        String id = orderId.toString();
        if (redisUtils.setIfAbsent(id, 1, 5, TimeUnit.MINUTES)) {
            flashSaleOrderRepository.save(order.setOrderStatus(OrderStatus.CANCELLED));
            rollBack(order.getLimitedProduct().getId(), order.getQuantity());
            redisUtils.delete(id);
        } else {
            delayQueueManager.put(new DelayTask(new OrderTask("Flash sale order " + id + " expired.", orderId), 1000 * 90));
        }
    }
}
