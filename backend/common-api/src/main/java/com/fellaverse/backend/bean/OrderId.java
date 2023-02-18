package com.fellaverse.backend.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderId implements Serializable {
    private static final long serialVersionUID = 6470730271757164703L;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderId orderId)) return false;
        return userId.equals(orderId.userId) && productId.equals(orderId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}