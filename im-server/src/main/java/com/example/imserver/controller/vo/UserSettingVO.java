package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserSettingVO {

    @JsonProperty("user_info")
    private UserInfoVO userInfo;

    private SettingVO setting;


}
