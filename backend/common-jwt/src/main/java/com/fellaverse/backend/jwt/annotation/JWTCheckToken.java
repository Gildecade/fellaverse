package com.fellaverse.backend.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JWTCheckToken {
    // whether to enable?
    boolean required() default true;
    // check role
    String role() default "";
    // check function
    String function() default  "";
}
