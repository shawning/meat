package com.meet.app.service.live;

import com.aliyun.teaopenapi.models.Config;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/23 下午10:43
 * @Description
 */
public class Live {
    public static final String NAME = "";
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
                    .setAccessKeyId("LTAI5t7xVbtF67fi4Nr2QHXq")
                    // 您的AccessKey Secret
                    .setAccessKeySecret("IPaRHccXM1ccv6K5iWL5P75ow77KiE");
            // 访问的域名
            config.endpoint = "live.aliyuncs.com";
            client =  new com.aliyun.live20161101.Client(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
