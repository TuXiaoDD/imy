package com.example.imserver.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UnreadClearDTO {

    @JsonProperty("talk_type")
    private Integer talkType;
    @JsonProperty("receiver_id")
    private Integer receiverId;
}
