package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course")
public class Course extends Product {

    @Column(name = "video_url", nullable = false)
    private String video_url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}