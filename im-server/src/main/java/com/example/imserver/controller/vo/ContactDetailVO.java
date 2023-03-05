package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContactDetailVO {

    @JsonProperty("avatar")
    private String avatar;
//    @JsonProperty("friend_apply")
//    private Integer friendApply;
//    @JsonProperty("friend_status")
//    private Integer friendStatus;
    @JsonProperty("gender")
    private Integer gender;
    /**
     * friend_uid
     */
    @JsonProperty("id")
    private Long id;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("motto")
    private String motto;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("nickname_remark")
    private String nicknameRemark;
}
