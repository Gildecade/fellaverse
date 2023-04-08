package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import lombok.*;

/**
 * Bean file for course product in shop.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "course")
public class Course extends Product {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Product product;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}