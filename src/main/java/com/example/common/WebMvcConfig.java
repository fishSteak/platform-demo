package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login", "/api/user/register",
                        "/api/user/logout","/api/user/session","/api/user/getCode/","/api/user/confirmCode/","/api/user/upload/**")
                .excludePathPatterns("/api/notice/")
                .excludePathPatterns("/api/activity/page");
    }

    @Bean
    public AuthInterceptor  authInterceptor() {
        return new AuthInterceptor();
    }
}
