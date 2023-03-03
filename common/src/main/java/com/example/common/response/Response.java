package com.example.common.response;

import com.example.common.constants.HttpServletResponse;
import com.example.common.exception.BizException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;


@NoArgsConstructor
@Getter
@Setter
public class Response<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回消息
     */
    private String msg;


    private Response(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private Response(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Response(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private Response(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Response(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 请求是否成功
     *
     * @param result
     * @return
     */
    public static boolean isSuccess(Response<?> result) {
        return Optional.ofNullable(result)
                .map(x -> Objects.equals(ResultCode.SUCCESS.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(ResultCode.SUCCESS, data);
    }

    /**
     * 获取 data
     *
     * @return
     */
    public T data() {
        // 如果是业务异常
        if (isBizException(this)) {
            throw new BizException(this.msg);
        }
        return this.data;
    }

    private boolean isBizException(Response<T> response) {
        return Optional.ofNullable(response)
                .map(x -> Objects.equals(ResultCode.BIZ_EXCEPTION.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 请求是否失败
     *
     * @param result
     * @return
     */
    public static boolean isNotSuccess(Response<?> result) {
        return !Response.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T extends Serializable> Response<T> data(T data, String msg) {
        return data(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T extends Serializable> Response<T> data(Integer code, T data, String msg) {
        return new Response<>(code, data, data == null ? "no data" : msg);
    }

    public static <T> Response<T> fail(IResultCode resultCode, String msg) {
        return new Response<>(resultCode, msg);
    }

    public static <T> Response<T> fail(IResultCode resultCode) {
        return new Response<>(resultCode, resultCode.getMessage());
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> Response<T> fail(String msg) {
        return new Response<>(ResultCode.FAILURE, msg);
    }


}
