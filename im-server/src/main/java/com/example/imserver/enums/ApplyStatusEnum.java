package com.example.imserver.enums;

public enum ApplyStatusEnum {

    APPLYING(0,"申请中"),
    AGREE(1,"已同意"),
    REJECT(2,"已拒绝")
    ;

    int code;
    String desc;
    ApplyStatusEnum(int code, String desc){
        this.code = code;
        this.desc= desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
