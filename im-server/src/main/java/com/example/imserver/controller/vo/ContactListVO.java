package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ContactListVO {

     /*
      * 好友头像
      * */
    @JsonProperty("avatar")
    private String avatar;

    /*
     * 好友性别
     * */
    @JsonProperty("gender")
    private Integer gender;

    /*
     * 好友所属群组id
     * */
    @JsonProperty("group_id")
    private Long groupId;

    /*
     * 好友id
     * */
    @JsonProperty("id")
    private Long id;

    /*
     * 好友是否在线
     * */
    @JsonProperty("is_online")
    private Integer isOnline;

    /*
     * 好友个签
     * */
    @JsonProperty("motto")
    private String motto;

    /*
     * 好友昵称
     * */
    @JsonProperty("nickname")
    private String nickname;

    /*
     * 备注
     * */
    @JsonProperty("remark")
    private String remark;
}
