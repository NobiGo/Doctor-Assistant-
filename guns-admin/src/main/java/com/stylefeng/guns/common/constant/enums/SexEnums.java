package com.stylefeng.guns.common.constant.enums;

public enum SexEnums {
    BOY_TYPE(1, "男性"),
    GIRL_TYPE(0, "女性");

    private Integer code;
    private String value;

    SexEnums(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String valueOf(Integer code) {
        if (code == null) {
            return "";
        } else {
            for (SexEnums sexEnums : SexEnums.values()) {
                if (sexEnums.getCode() == code) {
                    return sexEnums.getValue();
                }
            }
            return code + "";
        }
    }
}
