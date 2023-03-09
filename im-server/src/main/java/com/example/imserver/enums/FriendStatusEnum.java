package com.example.imserver.enums;

import java.util.List;

public enum FriendStatusEnum {

    NORMAL(0, "正常好友"),
    DELETED(1, "已删除"),
    BLACK(2, "已加入黑名单"),
    ;

    private int status;
    private String desc;

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    FriendStatusEnum(int status, String desc) {
        this.desc = desc;
        this.status = status;
    }

    public static List<Integer> notFriendStatus() {
        return List.of(DELETED.status, BLACK.status);
    }
}
