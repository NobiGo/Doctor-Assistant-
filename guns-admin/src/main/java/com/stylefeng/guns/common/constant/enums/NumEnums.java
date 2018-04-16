package com.stylefeng.guns.common.constant.enums;

public enum NumEnums {
    H_TYPE(0, "H"),
    S1_TYPE(1, "S1"),
    S2_TYPE(2, "S2"),
    S3_TYPE(3, "S3");

    private Integer code;
    private String value;

    NumEnums(Integer code, String value) {
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
            for (NumEnums numEnums : NumEnums.values()) {
                if (numEnums.getCode() == code) {
                    return numEnums.getValue();
                }
            }
            return code + "";
        }
    }
}
