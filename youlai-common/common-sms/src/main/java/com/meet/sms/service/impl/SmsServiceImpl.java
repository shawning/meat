package com.meet.sms.service.impl;

import com.meet.sms.service.SmsService;
import com.youlai.common.constant.RabbitConstants;
import com.youlai.common.enums.SmsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/8 下午6:04
 * @Description
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  boolean sendLoginSms(String phone, String validCode){
        log.info("==============短信发送至："+phone+" 内容为:"+validCode);
        Map<String ,String > map = new HashMap<>();
        map.put("templateCode", SmsEnum.LOGIN_CONFIRM.getCode());
        map.put("mobile",phone);
        map.put("checkcode",validCode);
        rabbitTemplate.convertAndSend(RabbitConstants.SMS_RELEASE_QUEUE,map);
        return false;
    }
}
