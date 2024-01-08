package com.lym;

import com.example.common.enums.ClientType;
import com.example.common.enums.CodeType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    /**
     * 请求头长度
     */
    private Integer headerLength;
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
    private Integer bodyLength;

    private byte[] body;
}
