package com.example.imserver.enums;

public enum RelationTypeEnum {
    FRIEND("朋友", 1),
    COLLEAGUE("同事", 2),
    FAMILY("家人", 3),
    STRANGER("陌生人", 4),
    SPECIAL("特别关心", 5),
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
