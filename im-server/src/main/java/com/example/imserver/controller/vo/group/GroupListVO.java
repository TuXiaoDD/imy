package com.example.imserver.controller.vo.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 群聊列表返回值
 */
@NoArgsConstructor
@Data
public class GroupListVO {
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("id")
    private Long id;
    /**
     * 消息免打扰
     */
    @JsonProperty("is_disturb")
    private Integer isDisturb;
    @JsonProperty("leader")
    private Integer leader;
    @JsonProperty("profile")
    private String profile;
}
