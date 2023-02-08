package com.fellaverse.backend.projection;

import java.util.Set;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Admin} entity
 */
public interface AdminInfo {
    Long getId();

    String getUsername();

    String getPassword();

    String getEmail();

    String getPhoneNumber();

    Set<RoleInfo> getRoles();
}