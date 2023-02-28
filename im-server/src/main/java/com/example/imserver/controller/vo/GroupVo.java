package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: leMin
 * @className: GroupVo
 * @date: 2023/2/28 ~ 23:05
 * @description:    通讯录群组信息
 */
@NoArgsConstructor
@Data
public class GroupVo {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sort")
    private Integer sort;

}
