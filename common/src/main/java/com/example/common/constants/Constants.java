package com.example.common.constants;

public interface Constants {

    /* message*/

    // headerLength是int类型，4个字节
    int headLengthLength = 4;
    int bodyLengthLength = 4;
    /**
     * 消息头的总长度
     */
    int request_header_length = 20;

    /**
     * app sdk版本号
     */
    int app_sdk_version = 1;
    /**
     * 请求类型授权
     */
    int request_type_auth = 1;
    /**
     * 消息顺序
     */
    int request_sequence_default = 1;

}
