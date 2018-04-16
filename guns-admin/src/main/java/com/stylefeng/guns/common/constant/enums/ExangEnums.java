package com.stylefeng.guns.common.constant.enums;

public enum ExangEnums {
    TRUE_TYPE(1, "是"),
    FALSE_TYPE(0, "否");

    private Integer code;
    private String value;

    ExangEnums(Integer code, String value) {
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
            for (ExangEnums exangEnums : ExangEnums.values()) {
                if (exangEnums.getCode() == code) {
                    return exangEnums.getValue();
                }
            }
            return code + "";
        }
    }
}
