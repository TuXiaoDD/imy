package com.imtcp.pack;

import com.imtcp.pack.enums.ClientType;
import com.imtcp.pack.enums.CodeType;
import lombok.Data;

@Data
public class MessageHeader {
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
     * @see ClientType
     */
    private Integer clientType;
    /**
     * 解析方式
     * @see CodeType
     */
    private Integer codeType;
    /**
     * 消息内容长度 必须填
     */
    private int length;


}
