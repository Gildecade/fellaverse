package com.fellaverse.backend.enumerator;

public enum UserStatus {
    NORMAL("normal"),
    LOCKED("locked"),
    UNKNOWN("unknown");
    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
