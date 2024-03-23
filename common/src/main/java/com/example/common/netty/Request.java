package com.example.common.netty;

import com.example.common.enums.RequestType;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Request extends Message {

    public Request(int headerLength,
                   int appSdkVersion,
                   int messageType,
                   int sequence,
                   int bodyLength,
                   byte[] body) {
        super(headerLength,
                appSdkVersion,
                messageType,
                sequence,
                RequestType.REQUEST.getVal(),
                bodyLength,
                body
        );


    }

    public Request(ByteBuf byteBuf) {
        super(byteBuf);
    }
}
