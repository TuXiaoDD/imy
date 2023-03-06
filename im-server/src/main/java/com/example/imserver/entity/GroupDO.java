package com.example.imserver.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * group
 * @author 
 */
@Data
public class GroupDO implements Serializable {
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

    private static final long serialVersionUID = 1L;
}