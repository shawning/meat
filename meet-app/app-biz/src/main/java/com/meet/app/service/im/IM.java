package com.meet.app.service.im;

import com.easemob.im.server.EMProperties;
import com.easemob.im.server.EMService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/23 下午10:43
 * @Description
 */
@Component
@Configuration
public class IM {

    public static String APP_KEY ;

    public static String CLIENT_ID;

    public static String SECRET;
    public static EMService emService;
    public static String getAppKey() {
        return APP_KEY;
    }
    @Value("${easemob.chat.appKey}")
    public void setAppKey(String appKey) {
        IM.APP_KEY = appKey;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }
    @Value("${easemob.chat.clientId}")
    public  void setClientId(String clientId) {
        IM.CLIENT_ID = clientId;
    }

    public static String getSECRET() {
        return SECRET;
    }
    @Value("${easemob.chat.secret}")
    public  void setSECRET(String SECRET) {
        IM.SECRET = SECRET;
        EMProperties properties = EMProperties.builder()
                .setAppkey(APP_KEY)
                .setClientId(CLIENT_ID)
                .setClientSecret(SECRET)
                .build();
        emService = new EMService(properties);
    }
//    static {
//        init();
//    }
   /* public static EMService server() {
        try {
            if(emService == null){
                init();
            }
            return emService;
        } catch (Exception e) {
            e.printStackTrace();
//            init();
            return null;
        }
    }
    public static void init(){
        try {
            EMProperties properties = EMProperties.builder()
                    .setAppkey(APP_KEY)
                    .setClientId(CLIENT_ID)
                    .setClientSecret(SECRET)
                    .build();
            emService = new EMService(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

}
