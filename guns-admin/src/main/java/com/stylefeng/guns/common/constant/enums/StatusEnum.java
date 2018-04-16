package com.stylefeng.guns.common.constant.enums;

/**
 * 是否患病
 */
public enum StatusEnum {

    YES_TYPE(0, "正常"),
    NO_TYPE(1, "患病");


    private Integer code;
    private String value;

    StatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


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

    public static String valueOf(Integer code) {
        if (code == null) {
            return "";
        } else {
            for (StatusEnum statusEnum : StatusEnum.values()) {
                if (statusEnum.getCode() == code) {
                    return statusEnum.getValue();
                }
            }
            return code + "";
        }
    }
}
