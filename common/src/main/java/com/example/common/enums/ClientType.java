package com.example.common.enums;

public enum ClientType {

    APP_AND(0),
    APP_IOS(1),

    WEB(2);
    private final int val;

    public int getVal() {
        return this.val;
    }

    ClientType(int val) {
        this.val = val;
    }

}
