package com.fellaverse.backend.bean;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckInId implements Serializable {
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName="id")
    private User user;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return user.getId();
    }
}