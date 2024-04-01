package com.lym.entity;

import lombok.Data;

@Data
public class AuthRedisValue {
    private String token;
    private String gatewayChannelId;
    private Long timestamp;
    private Long authTimestamp;
    private Boolean isAuth;
}
