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
 * Bean file for ID of functionalities.
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserFunctionId implements Serializable {
    private static final long serialVersionUID = 6674143811880140754L;
    @NotNull
    @Column(name = "function_id", nullable = false)
    private Long functionId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
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
        UserFunctionId entity = (UserFunctionId) o;
        return Objects.equals(this.functionId, entity.functionId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionId, userId);
    }

}