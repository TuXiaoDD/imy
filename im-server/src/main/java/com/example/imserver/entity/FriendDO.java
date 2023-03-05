package com.example.imserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String remark;
    /**
     * 家人，朋友
     */
    private Integer relation_type;
    /**
     * 单向好友 双向好友
     */
    private Integer status;
}
