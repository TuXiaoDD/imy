package com.example.imserver.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * group_message
 * @author 
 */
@Data
public class GroupMessageDO implements Serializable {
    private Long id;

    private Date addTime;

    private Date updateTime;

    private Integer isDelete;

    private Long groupId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息唯一标识
     */
    private String msgHash;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 发消息的
     */
    private Long srcUid;

    /**
     * 收消息的
     */
    private Long dstUid;

    /**
     * 0 src发给dst
1 dst发给src
     */
    private Integer direction;

    /**
     * 0 未读；1 已读
     */
    private Integer unread;

    private static final long serialVersionUID = 1L;
}