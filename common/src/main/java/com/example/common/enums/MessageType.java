package com.example.common.enums;

public enum MessageType {
    AUTH(1, "登录请求"),
    TEXT_MSG(2, "文本消息"),

    ;

    private final String desc;
    private final int value;

    MessageType(int value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    public static MessageType parse(int value) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.value == value) {
                return messageType;
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
