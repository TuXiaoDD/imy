package com.example.common.enums;

public enum MessageType {

    REQUEST(0),
    RESPONSE(1);
    private final int val;

    public int getVal() {
        return this.val;
    }

    MessageType(int val) {
        this.val = val;
    }
}
