package com.example.imserver.enums;

public enum RelationTypeEnum {
    FRIEND("朋友", 0),
    COLLEAGUE("同事", 1),
    FAMILY("家人", 2),
    STRANGER("陌生人", 3),
    SPECIAL("特别关心", 4),
    ;

    private final String desc;
    private final Integer val;

    RelationTypeEnum(String desc, Integer val) {
        this.desc = desc;
        this.val = val;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getVal() {
        return val;
    }
}
