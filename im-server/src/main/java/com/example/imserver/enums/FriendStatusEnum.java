package com.example.imserver.enums;

public enum FriendStatusEnum {

    NORMAL(0, "正常好友"),
    DELETED(0, "已删除"),
    BLACK(0, "已加入黑名单"),
    ;

    private int status;
    private String desc;

    FriendStatusEnum(int status, String desc) {
        this.desc = desc;
        this.status = status;
    }

}
