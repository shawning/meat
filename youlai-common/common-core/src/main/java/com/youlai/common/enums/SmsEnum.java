package com.youlai.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/8 下午5:34
 * @Description
 */
public enum SmsEnum {
    LOGIN_CONFIRM("SMS_213870226", "登录确认验证码"),
    PASSWORD_CHANGE("SMS_213870223", "修改密码验证码");


    @Getter
    @Setter
    private String code;

    SmsEnum(String code, String desc) {
        this.code = code;
    }

    public static SmsEnum getValue(String code){
        for (SmsEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
