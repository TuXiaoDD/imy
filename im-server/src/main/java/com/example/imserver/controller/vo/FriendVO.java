package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FriendVO {

    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("group_id")
    private Integer groupId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("is_online")
    private Integer isOnline;
    @JsonProperty("motto")
    private String motto;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("remark")
    private String remark;
}
