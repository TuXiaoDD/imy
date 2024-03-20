package com.example.common.enums;

public enum RequestType {
    AUTH(1, "登录请求"),
    TEXT_MSG(2, "文本消息"),

    ;

    private final String desc;
    private final int value;

    RequestType(int value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    public static RequestType parse(int value) {
        for (RequestType requestType : RequestType.values()) {
            if (requestType.value == value) {
                return requestType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
