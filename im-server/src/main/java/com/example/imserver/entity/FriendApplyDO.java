package com.example.imserver.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.example.imserver.enums.ApplyStatusEnum;
import lombok.Data;

/**
 * friend_apply
 *
 * @author
 */
@Data
@TableName("friend_apply")
public class FriendApplyDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Long uid;

    /**
     * 需要添加好友的uid
     */
    private Long applyUid;

    /**
     * 好友分组
     */
    private Integer relationType;

    /**
     * 申请理由
     */
    private String remark;

    private Integer isDelete;
    /**
     * 申请状态
     *
     * @see ApplyStatusEnum
     */
    private Integer applyStatus;
}