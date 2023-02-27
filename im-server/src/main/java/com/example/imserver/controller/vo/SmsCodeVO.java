package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SmsCodeVO {

    @JsonProperty("is_debug")
    private Boolean isDebug;
    @JsonProperty("sms_code")
    private String smsCode;
}
