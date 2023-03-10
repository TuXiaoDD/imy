package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 好友申请记录
 */
@NoArgsConstructor
@Data
public class FriendApplyRecordVO {
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("friend_id")
    private Long friendId;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("user_id")
    private Long userId;
}
