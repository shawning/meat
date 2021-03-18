package com.youlai.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/3/17 下午6:34
 * @Description
 */
public class RegexUtils {
    /**
     *  正则：手机号（简单）, 1字头＋10位数字即可.
     * @param in
     * @return
     */
    public static boolean validateMobilePhone(String in) {
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        return pattern.matcher(in).matches();
    }
    public static String getFourRandom() {
        return StringUtils.leftPad(new Random().nextInt(10000) + "", 4, "0");
    }
}
