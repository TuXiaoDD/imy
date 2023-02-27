package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoVO {

    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("is_qiye")
    private Boolean isQiye;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("motto")
    private String motto;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("uid")
    private Integer uid;
}
