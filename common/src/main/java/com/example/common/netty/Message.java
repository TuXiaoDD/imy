package com.example.common.netty;

import com.example.common.constants.Constants;
import com.example.common.enums.ClientType;
import com.example.common.enums.CodeType;
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
    private int headerLength;
    /**
     * appId
     */
    protected Integer appId;
    /**
     * 版本号
     */
    protected Integer version;
    /**
     * 命令
     */
    protected Integer command;
    /**
     * 客户端类型
     *
     * @see ClientType
     */
    protected Integer clientType;
    /**
     * 解析方式
     *
     * @see CodeType
     */
    protected Integer codeType;
    /**
     * 消息类型：0 请求；1 响应
     */
    protected Integer messageType;

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
        this.appId = byteBuf.readInt();
        this.version = byteBuf.readInt();
        this.command = byteBuf.readInt();
        this.clientType = byteBuf.readInt();
        this.codeType = byteBuf.readInt();
        this.messageType = byteBuf.readInt();
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

    public Message(int headerLength, Integer appId, Integer version, Integer command, Integer clientType, Integer messageType, Integer codeType, int bodyLength, byte[] body) {
        this.headerLength = headerLength;
        this.appId = appId;
        this.version = version;
        this.command = command;
        this.clientType = clientType;
        this.codeType = codeType;
        this.messageType = messageType;

        this.bodyLength = bodyLength;
        this.body = body;

        // 写进byteBuf
        this.byteBuf = Unpooled.buffer(Constants.headLengthLength + headerLength + Constants.bodyLengthLength + bodyLength);
        byteBuf.writeInt(headerLength);
        byteBuf.writeInt(appId);
        byteBuf.writeInt(version);
        byteBuf.writeInt(command);
        byteBuf.writeInt(clientType);
        byteBuf.writeInt(codeType);
        byteBuf.writeInt(messageType);
        byteBuf.writeInt(bodyLength);
        byteBuf.writeBytes(body);
    }


}
