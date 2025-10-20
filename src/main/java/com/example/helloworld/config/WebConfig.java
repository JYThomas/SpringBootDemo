package com.example.helloworld.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.helloworld.interceptor.LoginInterceptor;

// Springboot配置类生效
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*图片访问路径虚拟映射*/
    // 从配置文件注入图片存储本地路径（需拼接file:前缀，指定本地文件系统）
    @Value("${file.upload.local-path}")
    private String uploadLocalPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 前端访问虚拟路径：http://ip:port/images/**
        String imageUrlPath = "/images/**";
        
        // 本地路径拼接file:前缀（Spring MVC识别本地文件系统的标识）
        String imageLocalPath = "file:" + uploadLocalPath;

        // 注册映射规则：虚拟路径 → 本地文件路径
        registry.addResourceHandler(imageUrlPath)
                .addResourceLocations(imageLocalPath)
                .setCachePeriod(3600); // 缓存1小时，优化访问性能
    }

    /*拦截器配置*/ 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器,自定义创建的拦截器添加进去，
        //并且是在/user/目录的请求下生效，具体的拦截器逻辑在interceptor包中定义，不加目录则全局生效
        // 在配置类中生效
        // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
        registry.addInterceptor(new LoginInterceptor());
    }
}