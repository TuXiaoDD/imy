package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginVO {
    @JsonProperty("access_token")

    private String accessToken;
    @JsonProperty("expires_in")
    private Long expiresIn;

    private String type = "Bearer";
}
