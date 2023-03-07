package com.fellaverse.backend.service;

import com.fellaverse.backend.ManageServer_8001;
import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.enumerator.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = ManageServer_8001.class)
@AutoConfigureMockMvc
@Slf4j
@DisplayName("Limited Product Manage Service Test")
class LimitedProductManageServiceImplTest {

    @Autowired
    private LimitedProductManageService limitedProductManageService;

    @Test
    void addLimitedProduct() {
        LimitedProduct product = new LimitedProduct();
        product.setQuantity(100).setProductName("Protein").setPrice(99f)
                .setDescription("Protein powder").setImageUrl("www.aaa.com").setProductStatus(ProductStatus.ACTIVE)
                .setCreatedDateTime(LocalDateTime.now());
        limitedProductManageService.addLimitedProduct(product);
    }

    @Test
    void deleteLimitedProduct() {
        limitedProductManageService.deleteLimitedProduct(2L);
    }

    @Test
    void updateLimitedProduct() {
        LimitedProduct product = limitedProductManageService.findById(2L);
        product.setQuantity(product.getQuantity() - 1);
        limitedProductManageService.updateLimitedProduct(product);
    }

    @Test
    void findAll() {
        System.out.println(limitedProductManageService.findAll());
    }

    @Test
    void findById() {
        System.out.println(limitedProductManageService.findAll());
        System.out.println(limitedProductManageService.findById(2L));
        System.out.println(limitedProductManageService.findById(3L));
        System.out.println(limitedProductManageService.findById(4L));
    }

    @Test
    void findByKeywords() {
    }

    @Test
    void findByConditions() {
    }
}