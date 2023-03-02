package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TalkListVO {
    private List<TalkList> items;

    @Data
    public static class TalkList {

        /**
         * 头像链接
         */
        private String avatar;
        /**
         *
         */
        private Long id;
        @JsonProperty("is_disturb")
        private Integer isDisturb;
        @JsonProperty("is_online")
        private Integer isOnline;
        @JsonProperty("is_robot")
        private Integer isRobot;
        @JsonProperty("isTop")
        private Integer is_top;
        @JsonProperty("receiver_id")
        private Integer receiverId;
        /**
         * 未读消息数
         */
        @JsonProperty("unread_num")
        private Long unreadNum;
        /**
         * 聊天类型
         */
        @JsonProperty("talk_type")
        private Integer talkType;
        @JsonProperty("msg_text")
        private String msgText;
        private String name;
        @JsonProperty("remark_name")
        private String remarkName;
        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;


    }
}
