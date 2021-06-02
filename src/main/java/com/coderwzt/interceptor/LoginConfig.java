package com.coderwzt.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration=
                registry.addInterceptor(new LoginInterceptor());
        //拦截信息
        registration.addPathPatterns("/**");
        //不拦截信息
        registration.excludePathPatterns(
                "/loginIn",
                "/propertyType/**",
                "/**/login.html",
                "/**/*.js",
                "/**/*.css",
                "/**/images/*.*"
        );

    }
}
