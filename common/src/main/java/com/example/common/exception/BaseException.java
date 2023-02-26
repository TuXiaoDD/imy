package com.example.common.exception;


import com.example.common.response.IResultCode;
import com.example.common.response.ResultCode;

/**
 * Base Exception is the parent of all exceptions
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final IResultCode code;

    public BaseException(String errMessage) {
        super(errMessage);
        this.code = ResultCode.INTERNAL_SERVER_ERROR;
    }

    public BaseException(IResultCode code, String errMessage) {
        super(errMessage);
        this.code = code;
    }

    public BaseException(String errMessage, Throwable e) {
        super(errMessage, e);
        this.code = ResultCode.FAILURE;
    }

    public BaseException(IResultCode code, String errMessage, Throwable e) {
        super(errMessage, e);
        this.code = code;
    }

    public IResultCode getCode() {
        return code;
    }

}
