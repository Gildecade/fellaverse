package com.fellaverse.backend.bean;

import com.fellaverse.backend.enumerator.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 60)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @Enumerated
    @Column(name = "product_status", nullable = false)
    private ProductStatus productStatus;

    @OneToOne(mappedBy = "id")
    private Course course;

    @OneToMany(mappedBy = "product")
    private Set<Order> orders = new LinkedHashSet<>();

}
