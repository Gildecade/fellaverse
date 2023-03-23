package com.fellaverse.backend;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Function} entity
 */
public interface FunctionInfo {
    Long getId();

    String getFunctionName();

    String getDescription();
}