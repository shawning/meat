package com.meet.app.service.live;

import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/23 下午10:43
 * @Description
 */
public class Live {
    public static final String NAME = "";
    @Value("${ali.live.url}")
    public static String URL ;
    @Value("${ali.sms.AccessKeyId}")
    public static String ACCESSKEY ;
    @Value("${ali.sms.AccessKeySecret}")
    public static String SECRET ;

    private static com.aliyun.live20161101.Client client = null;
    static {
        init();
    }
    public static com.aliyun.live20161101.Client server() {
        try {
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            init();
            return null;
        }
    }
    public static void init(){
        try {
            Config config = new Config()
                    // 您的AccessKey ID
                    .setAccessKeyId(ACCESSKEY)
                    // 您的AccessKey Secret
                    .setAccessKeySecret(SECRET);
            // 访问的域名
            config.endpoint = URL;
            client =  new com.aliyun.live20161101.Client(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
