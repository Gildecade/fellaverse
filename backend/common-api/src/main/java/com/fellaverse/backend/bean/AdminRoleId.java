package com.fellaverse.backend.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdminRoleId implements Serializable {
    private static final long serialVersionUID = 6470750271757164303L;
    @NotNull
    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @NotNull
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AdminRoleId entity = (AdminRoleId) o;
        return Objects.equals(this.roleId, entity.roleId) &&
                Objects.equals(this.adminId, entity.adminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, adminId);
    }

}