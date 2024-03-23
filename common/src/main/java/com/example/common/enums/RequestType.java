package com.example.common.enums;

public enum RequestType {

    REQUEST(0),
    RESPONSE(1);
    private final int val;

    public int getVal() {
        return this.val;
    }

    RequestType(int val) {
        this.val = val;
    }
}
