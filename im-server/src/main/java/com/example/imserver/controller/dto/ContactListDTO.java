package com.example.imserver.controller.dto;

import com.example.imserver.enums.RelationTypeEnum;
import lombok.Data;

@Data
public class ContactListDTO {

    /**
     * @see RelationTypeEnum
     */
    private Integer relationType;

    private String friendName;

    private Long uid;
}
