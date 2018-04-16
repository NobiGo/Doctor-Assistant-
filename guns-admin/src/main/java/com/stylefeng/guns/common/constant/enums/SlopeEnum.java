package com.stylefeng.guns.common.constant.enums;

public enum SlopeEnum {
    SUPER_TYPE(1, "上斜"),
    LEVEL_PTYPE(2, "水平"),
    DOWN_TYPE(3, "下压");

    private int code;
    private String value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SlopeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String valueOf(Integer code) {
        if (code == null) {
            return "";
        } else {
            for (SlopeEnum slopeEnum : SlopeEnum.values()) {
                if (slopeEnum.getCode() == code) {
                    return slopeEnum.getValue();
                }
            }
            return code + "";
        }
    }
}
