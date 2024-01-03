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
public class ResponseHanlder implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (DataUtils.isNotEmpty(returnType.getMethodAnnotation(ExceptionHandler.class))
                || Objects.equals(returnType.getMethod().getName(), "error")) {
            return body;
        }
        return Response.success(body);
    }

}
