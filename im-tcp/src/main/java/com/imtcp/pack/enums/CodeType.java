package com.imtcp.pack.enums;

public enum CodeType {

    DEFAULT(0),
    PROTOBUF(1),

    XML(2),
    JSON(3);
    private final int val;

    public int getVal() {
        return this.val;
    }

    CodeType(int val) {
        this.val = val;
    }

}
