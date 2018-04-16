package com.stylefeng.guns.common.constant.enums;

public enum ThalEnums {

    NOMAL_TYPE(3, "正常"),
    FASTEN_TYPE(6, "固有缺陷"),
    REPAIR_TYPE(7, "可修复缺陷");

    private Integer code;
    private String value;

    ThalEnums(Integer code, String value) {
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
            for (ThalEnums thalEnums : ThalEnums.values()) {
                if (thalEnums.getCode() == code) {
                    return thalEnums.getValue();
                }
            }
            return code + "";
        }
    }
}
