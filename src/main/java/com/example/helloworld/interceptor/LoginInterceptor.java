package com.example.helloworld.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println("登录拦截器");
        // 在这里进行前端请求发过来后，进行操作之前的拦截，
        // 比如判断用户是否登录，如果登录了，则放行，否则拦截，跳转到登录页面
        
        // 返回false,路由那边的数据返回操作不会执行
        return true;
    }
}
