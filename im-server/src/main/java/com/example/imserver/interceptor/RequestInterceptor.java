package com.example.imserver.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.common.utils.DataUtils;
import com.example.imserver.annotation.NotRequireLogin;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (DataUtils.isNotEmpty(handler) && handler instanceof HandlerMethod) {
            return true;
        }
        //标记了NotRequireLogin注解的方法不需要登录验证
        NotRequireLogin annotation = ((HandlerMethod) handler).getMethodAnnotation(NotRequireLogin.class);
        if(DataUtils.isNotEmpty(annotation)) {
            return true;
        }

        return true;
    }

    // todo 2023-02-26 17:07:20
    private boolean verifyPermissions(HttpServletRequest request) {
//        request.getHeader()
        return true;
    }


}
