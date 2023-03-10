package com.example.imserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * single_message
 * @author 
 */
@Data
public class SingleMessageDO implements Serializable {
    private Long id;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Integer isDelete;

    /**
     * 发送方uid
     */
    private Long uid;

    /**
     * 接受方uid
     */
    private Long toUid;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 0 表示uid发给to_uid
1 表示to_uid发给uid
     */
    private Integer direction;

    /**
     * 0 未读；1 已读
     */
    private Integer unread;

    private String msgHash;

    private static final long serialVersionUID = 1L;
}