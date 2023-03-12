package com.fellaverse.backend.config;

import com.fellaverse.backend.jwt.interceptor.JWTAuthorizeInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile({ "production" })
public class JWTInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getDefaultHandlerInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HandlerInterceptor getDefaultHandlerInterceptor() {
        return new JWTAuthorizeInterceptor();
    }
}
