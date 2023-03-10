package com.example.imserver.controller.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 接受好友申请
 */
@NoArgsConstructor
@Data
public class ApplyAcceptTO {

    @JsonProperty("apply_id")
    @NotNull
    private Long applyId;
    @JsonProperty("remark")
    private String remark;
}
