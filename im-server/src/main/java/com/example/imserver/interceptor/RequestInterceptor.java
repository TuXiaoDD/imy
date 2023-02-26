package com.example.imserver.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //判断是否登录
            if (verifyPermissions(request)) {
                return true;
            }
            //这里设置拦截以后重定向的页面，一般设置为登陆页面地址
//            response.sendRedirect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // todo 2023-02-26 17:07:20
    private boolean verifyPermissions(HttpServletRequest request) {
//        request.getHeader()
        return true;
    }


}
