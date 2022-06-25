package com.wechat.payment.emus;

public enum OperationType {

    DEFAULT_OPERATION ("1","");

    private final String code;
    private final String desc;

    OperationType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
