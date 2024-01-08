package com.example.common.netty;

import com.example.common.enums.ClientType;
import com.example.common.enums.CodeType;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Request {
    /**
     * 请求头长度
     */
    private int headerLength;
    /**
     * appId
     */
    private Integer appId;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 命令
     */
    private Integer command;
    /**
     * 客户端类型
     *
     * @see ClientType
     */
    private Integer clientType;
    /**
     * 解析方式
     *
     * @see CodeType
     */
    private Integer codeType;
    /**
     * 消息内容长度 必须填
     */
    private int bodyLength;

    private byte[] body;

    public Request(ByteBuf byteBuf) {
        this.headerLength = byteBuf.readInt();
        this.appId = byteBuf.readInt();
        this.version = byteBuf.readInt();
        this.command = byteBuf.readInt();
        this.clientType = byteBuf.readInt();
        this.codeType = byteBuf.readInt();
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
        byteBuf.markReaderIndex();
    }


}
