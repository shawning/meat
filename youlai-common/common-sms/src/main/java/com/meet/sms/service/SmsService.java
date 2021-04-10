package com.meet.sms.service;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/8 下午6:03
 * @Description
 */
public interface SmsService {
    boolean sendLoginSms(String phone, String validCode);
}
