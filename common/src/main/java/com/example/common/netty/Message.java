package com.example.common.netty;

import com.example.common.constants.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Message {
    /**
     * 请求头长度
     */
    protected int headerLength;

    /**
     * 客户端版本号
     */
    protected int appSdkVersion;
    /**
     * 请求类型 requestType
     */
    protected int requestType;
    /**
     * 请求顺序
     *
     */
    protected int sequence;

    /**
     * 消息内容长度 必须填
     */
    protected int bodyLength;

    protected byte[] body;

    /**
     * 记录完整的消息
     */
    protected ByteBuf byteBuf;

    public Message() {

    }

    public Message(ByteBuf byteBuf) {
        this.headerLength = byteBuf.readInt();
        this.appSdkVersion = byteBuf.readInt();
        this.requestType = byteBuf.readInt();
        this.sequence = byteBuf.readInt();
        this.bodyLength = byteBuf.readInt();
        int readableBytes = byteBuf.readableBytes();

        if (readableBytes < bodyLength) {
            log.error("body长度不足 {} {}", readableBytes, bodyLength);
            byteBuf.resetReaderIndex();
            return;
        }

        if (readableBytes != bodyLength) {
            log.error("body长度有误 {} {}", readableBytes, bodyLength);
            throw new RuntimeException("body长度有误");
        }
        byte[] bytes = new byte[bodyLength];
        byteBuf.readBytes(bytes);
        this.body = bytes;

        byteBuf.resetReaderIndex();
        this.byteBuf=byteBuf;
    }

    public Message(int headerLength,
                   int appSdkVersion,
                   int requestType,
                   int sequence,
                   int bodyLength,
                   byte[] body) {
        this.headerLength = headerLength;
        this.appSdkVersion = appSdkVersion;
        this.requestType = requestType;
        this.sequence = sequence;
        this.bodyLength = bodyLength;
        this.body = body;

        // 写进byteBuf
        this.byteBuf = Unpooled.buffer(Constants.headLengthLength + headerLength + Constants.bodyLengthLength + bodyLength);
        byteBuf.writeInt(headerLength);
        byteBuf.writeInt(appSdkVersion);
        byteBuf.writeInt(requestType);
        byteBuf.writeInt(sequence);
        byteBuf.writeInt(bodyLength);
        byteBuf.writeBytes(body);
    }


}
