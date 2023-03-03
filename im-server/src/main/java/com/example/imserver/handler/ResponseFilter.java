package com.example.imserver.handler;

import com.example.common.response.Response;
import com.example.common.utils.DataUtils;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
public class ResponseFilter implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (Objects.equals(returnType.getMethod().getName(),"error")||
                DataUtils.isNotEmpty(returnType.getMethodAnnotation(ExceptionHandler.class))) {
            //处理异常，可以再添加一个异常处理的类，用于处理异常返回格式
            return body;
        }

        return Response.success(body);
    }

    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e) {

        return new Object();
    }
}
