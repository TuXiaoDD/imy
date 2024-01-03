package com.example.imserver.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;

public class ByteBufToMessageUtils {


    public static Message transition(ByteBuf content) {
        int command = content.readInt();
        int version = content.readInt();
        int clientType = content.readInt();
        int appId = content.readInt();
        int messageType = content.readInt();
        int length = content.readInt();
        if (content.readableBytes() < length) {
            content.resetReaderIndex();
            return null;
        }
        byte[] data = new byte[length];
        content.readBytes(data);

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setAppId(appId);
        messageHeader.setClientType(clientType);
        messageHeader.setCommand(command);
        messageHeader.setVersion(version);
        messageHeader.setMessageType(messageType);
        messageHeader.setLength(length);
        Message message = new Message();
        message.setMessageHeader(messageHeader);

        if (messageType == 0x0) {
            String dataStr = new String(data);
            JSONObject parse = (JSONObject) JSONObject.parse(dataStr);
            message.setMessageBody(parse);
        }
        content.markReaderIndex();
        return message;


    }
}
