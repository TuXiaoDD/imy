package com.imtcp.pack;

import io.netty.handler.codec.http.FullHttpRequest;
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
    private FullHttpRequest request;
}
