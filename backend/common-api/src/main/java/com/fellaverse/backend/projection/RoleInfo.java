package com.fellaverse.backend.projection;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Role} entity
 */
public interface RoleInfo {
    Long getId();

    String getRoleName();

    String getDescription();
}