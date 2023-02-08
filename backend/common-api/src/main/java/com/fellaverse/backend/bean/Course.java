package com.fellaverse.backend.bean;

import com.fellaverse.backend.enumerator.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 60)
    private String product_name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime created_date_time;

    @Column(name = "video_url", nullable = false)
    private String video_url;

    @Enumerated
    @Column(name = "product_status", nullable = false)
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}