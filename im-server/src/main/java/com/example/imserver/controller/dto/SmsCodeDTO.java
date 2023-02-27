package com.example.imserver.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SmsCodeDTO {

    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("channel")
    private String channel;
}
