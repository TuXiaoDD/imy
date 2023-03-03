package com.example.imserver.handler;


import com.example.common.exception.BizException;
import com.example.common.response.Response;
import com.example.common.response.ResultCode;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response<?> defaultExceptionHandler(Exception e) {
        return Response.fail(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    /**
     * 所有的业务异常
     */
    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Response<?> bizExceptionHandle(BizException bizException) {
        return Response.fail(bizException.getCode(), bizException.getMessage());
    }

    /**
     * 参数不可用异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Response.fail(ResultCode.PARAM_BIND_ERROR);
    }

    /**
     * 参数类型不匹配异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Response<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        return Response.fail(ResultCode.PARAM_MISS);
    }

    /**
     * 请求方法不匹配异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<?> argumentMissingError(HttpRequestMethodNotSupportedException ex) {
        return Response.fail(ResultCode.METHOD_NOT_SUPPORTED);
    }

}
