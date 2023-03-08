package com.example.imserver.controller.dto.group;

import lombok.Data;

/**
 * 群聊列表查询
 * 不传参查询全部
 */
@Data
public class GroupListDTO {
    /**
     * 群聊名称
     */
    private String groupName;
    /**
     * 是否是群主
     */
    private Boolean isMaster;

    private Long uid;


}
