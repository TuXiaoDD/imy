package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchUserVO {

    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("motto")
    private String motto;
    @JsonProperty("nickname")
    private String nickname;
}
