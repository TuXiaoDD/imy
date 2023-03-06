package com.example.imserver.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.imserver.enums.RelationTypeEnum;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author: leMin
 * @className: ContactDO
 * @date: 2023/3/6 ~ 23:49
 * @description:
 */
@Data
@TableName("friend")
public class ContactDO {

    /**
     * 好友id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * friendUid
     */
    private Long friendUid;

    /**
     * uid
     */
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
