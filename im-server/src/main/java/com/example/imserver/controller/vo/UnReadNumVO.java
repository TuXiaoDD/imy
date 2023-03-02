package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UnReadNumVO {
    @JsonProperty("unread_num")
    private Long unreadNum;
}
