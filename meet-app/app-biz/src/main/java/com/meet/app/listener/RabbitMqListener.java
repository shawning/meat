package com.meet.app.listener;

import cn.hutool.json.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.youlai.common.constant.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/9 下午2:34
 * @Description
 */
@Component
@Slf4j
public class RabbitMqListener {
    @Value("${ali.sms.AccessKeyId}")
    private String accessKeyId;
    @Value("${ali.sms.AccessKeySecret}")
    private String accessKeySecret;

    @RabbitListener(queues = RabbitConstants.SMS_RELEASE_QUEUE)
    @RabbitHandler
    public void eventDirectSmsPush(Message message) throws Exception {
        if (null == message) {
            log.info("eventDirectSmsPush 接收到消息是空数据，不处理！");
            return;
        }
        String msg = new String(message.getBody());
        log.info("eventDirectSmsPush 接收到消息====>:{}", msg);
        processMessage(msg);

    }
    public void processMessage(String message) throws Exception {
        JSONObject messageJSON = new JSONObject(message);
        switch(messageJSON.getStr("templateCode")){
            case "SMS_213870226" :
            {
                JSONObject codeBody =  new JSONObject();
                codeBody.set("code",messageJSON.getStr("checkcode"));
                SendSmsRequest sendSmsRequest = new SendSmsRequest()
                        .setPhoneNumbers(messageJSON.getStr("mobile"))
                        .setSignName("山东耀世传媒有限公司")
                        .setTemplateCode(messageJSON.getStr("templateCode"))
                        .setTemplateParam(codeBody.toString());
                SendSmsResponse response = createClient().sendSms(sendSmsRequest);
                log.info("===========短信发送结果==="+response.getBody().getCode()+"=======回执ID："+response.getBody().getBizId()+response.getBody().getMessage()+"============");
                break;
            }
            default : {
                break;
            }
        }

    }
    public  com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
}
