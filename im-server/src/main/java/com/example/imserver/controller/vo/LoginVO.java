package com.example.imserver.controller.vo;

import lombok.Data;

@Data
public class LoginVO {
    private String access_token;
    private Long expires_in;

    private String type = "Bearer";
}
