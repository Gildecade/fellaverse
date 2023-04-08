package com.fellaverse.backend.bean;

import jakarta.persistence.*;
/**
 * Bean file for (administrator,role) pairs.
 */
@Entity
@Table(name = "admin_role")
public class AdminRole {
    @EmbeddedId
    private AdminRoleId id;

    @MapsId("adminId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public AdminRoleId getId() {
        return id;
    }

    public void setId(AdminRoleId id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}