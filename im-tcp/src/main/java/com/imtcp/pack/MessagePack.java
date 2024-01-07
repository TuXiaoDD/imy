package com.imtcp.pack;

import lombok.Data;

@Data
public class MessagePack {
    /**
     * 请求头
     */
    private MessageHeader header;
    /**
     * 消息内容
     */
    private Object data;
}
