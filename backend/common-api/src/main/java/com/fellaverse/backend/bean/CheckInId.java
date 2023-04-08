package com.fellaverse.backend.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
/**
 * Bean file for (checkInId, userId) pairs.
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CheckInId implements Serializable {
    private static final long serialVersionUID = -9190963689769025674L;
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CheckInId entity = (CheckInId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

}