package com.sjy.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 傻傻的远
 * @Date 2021 11 07 14:50
 * @Description: 项目名称 myblog 路径 com.sjy
 * @Function:
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("user")== null)
        {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
