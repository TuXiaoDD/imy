package com.example.imserver.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author: leMin
 * @className: EditFriendDTO
 * @date: 2023/3/12 ~ 20:21
 * @description:
 */
@Data
@NoArgsConstructor
public class EditFriendDTO {


    private Long friendUid;

    private String remark;

    private Long uid;

}
