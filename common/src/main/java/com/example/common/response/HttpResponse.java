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
public class HttpResponse<T> implements Serializable {

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


    private HttpResponse(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private HttpResponse(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private HttpResponse(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private HttpResponse(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private HttpResponse(Integer code, T data, String msg) {
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
    public static boolean isSuccess(HttpResponse<?> result) {
        return Optional.ofNullable(result)
                .map(x -> Objects.equals(ResultCode.SUCCESS.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    public static <T> HttpResponse<T> success(T data) {
        return new HttpResponse<>(ResultCode.SUCCESS, data);
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

    private boolean isBizException(HttpResponse<T> httpResponse) {
        return Optional.ofNullable(httpResponse)
                .map(x -> Objects.equals(ResultCode.BIZ_EXCEPTION.getCode(), x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 请求是否失败
     *
     * @param result
     * @return
     */
    public static boolean isNotSuccess(HttpResponse<?> result) {
        return !HttpResponse.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T extends Serializable> HttpResponse<T> data(T data, String msg) {
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
    public static <T extends Serializable> HttpResponse<T> data(Integer code, T data, String msg) {
        return new HttpResponse<>(code, data, data == null ? "no data" : msg);
    }

    public static <T> HttpResponse<T> fail(IResultCode resultCode, String msg) {
        return new HttpResponse<>(resultCode, msg);
    }

    public static <T> HttpResponse<T> fail(IResultCode resultCode) {
        return new HttpResponse<>(resultCode, resultCode.getMessage());
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> HttpResponse<T> fail(String msg) {
        return new HttpResponse<>(ResultCode.FAILURE, msg);
    }


}
