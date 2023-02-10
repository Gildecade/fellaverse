package com.fellaverse.backend.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "user_function")
public class UserFunction {
    @EmbeddedId
    private UserFunctionId id;

    @MapsId("functionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "function_id", nullable = false)
    private Function function;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserFunctionId getId() {
        return id;
    }

    public void setId(UserFunctionId id) {
        this.id = id;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}