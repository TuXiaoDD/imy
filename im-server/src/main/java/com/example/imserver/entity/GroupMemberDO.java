package com.example.imserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * group_member
 * @author 
 */
@Data
public class GroupMemberDO implements Serializable {
    private Long id;

    private Long groupId;

    private Long uid;

    /**
     * 群备注
     */
    private String groupRemark;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}