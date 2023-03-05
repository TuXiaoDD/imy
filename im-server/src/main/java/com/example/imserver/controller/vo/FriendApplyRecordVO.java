package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FriendApplyRecordVO {

    private List<FriendApplyRecord> items;

    @Data
    public static class FriendApplyRecord {
        @JsonProperty("avatar")
        private String avatar;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("friend_id")
        private Integer friendId;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("remark")
        private String remark;
        @JsonProperty("user_id")
        private Integer userId;
    }
}
