package com.example.imserver.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
  * @author: leMin
  * @date: 2023/3/8  ~  21:24
  * @className: ContactListVO
  * @description :  好友列表
  */
@NoArgsConstructor
@Data
public class ContactListVO {

    private List<ContactVO> items;

    @Data
    public static class ContactVO {

        /*
         * 好友id
         * */
        @JsonProperty("id")
        private Integer id;

        /*
         * 好友昵称
         * */
        @JsonProperty("nickname")
        private String nickname;

        /*
         * 好友头像
         * */
        @JsonProperty("mobile")
        private String mobile;

        /*
         * 密码
         * */
        @JsonProperty("password")
        private String password;

        /*
         * 好友注册时间
         * */
        @JsonProperty("add_time")
        private String addTime;

        /*
         * 好友头像
         * */
        @JsonProperty("avatar")
        private String avatar;

        /*
         * 邮箱
         * */
        @JsonProperty("email")
        private String email;

        /*
         * 好友性别
         * */
        @JsonProperty("gender")
        private Integer gender;

        /*
         * 生日
         * */
        @JsonProperty("birthday")
        private String birthday;

        /*
         * 好友所属群组id
         * */
        //@JsonProperty("group_id")
        //private Integer groupId;


        /*
         * 好友是否在线
         * */
        //@JsonProperty("is_online")
        //private Integer isOnline;

        /*
         * 好友个签
         * */
        @JsonProperty("motto")
        private String motto;



        /*
         * 备注
         * */
        @JsonProperty("remark")
        private String remark;


    }


}
