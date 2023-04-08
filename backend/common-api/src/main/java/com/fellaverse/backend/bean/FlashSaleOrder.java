package com.fellaverse.backend.bean;

import com.fellaverse.backend.enumerator.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
/**
 * Bean file for flash sale order.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "flash_sale_order")
public class FlashSaleOrder {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "purchase_date_time", nullable = false)
    private LocalDateTime purchaseDateTime;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "status", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "limited_product_id", nullable = false)
    private LimitedProduct limitedProduct;

}