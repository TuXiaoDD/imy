package com.example.imserver.controller.vo;

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

        private Integer is_disturb;

        private Integer is_online;
        private Integer is_robot;
        private Integer is_top;

        private Integer receiver_id;
        /**
         * 未读消息数
         */
        private Long unread_num;
        /**
         * 聊天类型
         */
        private Integer talk_type;
        private String msg_text;
        private String name;
        private String remark_name;
        private LocalDateTime updated_at;


    }
}
