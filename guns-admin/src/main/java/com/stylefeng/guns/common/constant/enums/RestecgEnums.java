package com.stylefeng.guns.common.constant.enums;

public enum RestecgEnums {

    NORMAL_TYPE(0, "正常"),
    NON_TYPE(1, "有ST-T波异常"),
    YES_PRICE(2, "可能左心室肥厚");

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

    RestecgEnums(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String valueOf(Integer code) {
        if (code == null) {
            return "";
        } else {
            for (RestecgEnums restecgEnums : RestecgEnums.values()) {
                if (restecgEnums.getCode() == code) {
                    return restecgEnums.getValue();
                }
            }
            return code + "";
        }
    }
}
