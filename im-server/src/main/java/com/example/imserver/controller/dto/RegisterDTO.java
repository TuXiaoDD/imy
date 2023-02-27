package com.example.imserver.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegisterDTO {

    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("password")
    private String password;
    @JsonProperty("sms_code")
    private String smsCode;
    @JsonProperty("platform")
    private String platform;
}
