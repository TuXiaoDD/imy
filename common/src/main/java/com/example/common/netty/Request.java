package com.example.common.netty;

import com.example.common.constants.Constants;
import com.example.common.enums.ClientType;
import com.example.common.enums.CodeType;
import com.example.common.enums.MessageType;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class Request extends Message {

    public Request(int headerLength, Integer appId, Integer version, Integer command, Integer clientType, Integer codeType, int bodyLength, byte[] body) {
        super(headerLength,
                appId,
                version,
                command,
                clientType,
                MessageType.REQUEST.getVal(),
                codeType,
                bodyLength,
                body);
    }


    public Request(ByteBuf byteBuf) {
        super(byteBuf);
    }


}
