package com.example.common.netty;

import com.example.common.constants.Constants;
import com.example.common.enums.MessageType;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class Response extends Message {

    public Response(Request request, byte[] body) {
        super(request.messageType,
                request.appId,
                request.version,
                request.command,
                request.clientType,
                MessageType.RESPONSE.getVal(),
                request.codeType,
                body.length,
                body);
    }


    public Response(ByteBuf byteBuf) {
        super(byteBuf);
    }
}
