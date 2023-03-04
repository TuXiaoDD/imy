package com.example.common.exception;


import com.example.common.response.IResultCode;
import com.example.common.response.ResultCode;

/**
 * 业务异常
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;


    public BizException(String errMessage) {
        super(ResultCode.FAILURE, errMessage);
    }

    public BizException(ResultCode errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public BizException(ResultCode errCode) {
        super(errCode, errCode.getMessage());
    }

    public BizException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public BizException(IResultCode errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

}
