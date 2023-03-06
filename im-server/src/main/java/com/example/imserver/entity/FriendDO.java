package com.example.imserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.imserver.enums.RelationTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("friend")
public class FriendDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Integer isDelete;

    private Long friendUid;

    private Long uid;
    /**
     * 好友备注
     */
    private String remark;
    /**
     * 家人，朋友
     *
     * @see RelationTypeEnum
     */
    private Integer relationType;
    /**
     * 最近的一条消息
     */
    private String latestMsg;
    /**
     * 0 表示uid给friend_uid发
     * 1 表示friend_uid给uid发
     */
    private Integer direction;
    /**
     * 消息已读未读
     */
    private Integer unread;
}
