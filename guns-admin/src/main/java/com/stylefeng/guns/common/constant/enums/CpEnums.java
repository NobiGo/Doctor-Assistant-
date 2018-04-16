package com.stylefeng.guns.common.constant.enums;

/**
 * 胸部疼痛类型的Value
 */
public enum CpEnums {

    ASYMPTOMATIC_TYPE(1, "典型的心绞痛"),
    NON_ANGINAL_TYPE(2, "非典型的心绞痛"),
    ATYPICAL_PRICE(3, "非心绞痛的疼痛"),
    TYPICAL_PRICE(4, "无临床症状");

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

    CpEnums(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String valueOf(Integer code) {
        if (code == null) {
            return "";
        } else {
            for (CpEnums cpTypeEnum : CpEnums.values()) {
                if (cpTypeEnum.getCode() == code) {
                    return cpTypeEnum.getValue();
                }
            }
            return code + "";
        }
    }

}
