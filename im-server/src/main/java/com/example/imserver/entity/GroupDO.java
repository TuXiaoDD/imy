package com.example.imserver.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * group
 * @author 
 */
@Data
@TableName("group")
public class GroupDO{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date addTime;

    private Date updateTime;

    private Integer isDelete;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 群公告
     */
    private String announce;

    /**
     * 群主
     */
    private Long masterUid;

    /**
     * 群简介
     */
    private String introduce;

    /**
     * 群头像
     */
    private String avatar;
    /**
     * 消息免打扰
     */
    private Integer isDisturb;
}