package com.ohgiraffers.interceptortest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterCeptor implements WebMvcConfigurer {

    @Autowired

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor()
    }
}
