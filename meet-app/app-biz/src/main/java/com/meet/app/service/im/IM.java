package com.meet.app.service.im;

import com.easemob.im.server.EMProperties;
import com.easemob.im.server.EMService;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/23 下午10:43
 * @Description
 */
public class IM {
    public static final String NAME = "";
    private static EMService emService = null;
    static {
        init();
    }
    public static EMService server() {
        try {
            return emService;
        } catch (Exception e) {
            e.printStackTrace();
            init();
            return null;
        }
    }
    public static void init(){
        try {
            EMProperties properties = EMProperties.builder()
                    .setAppkey("1120210329085358#meet")
                    .setClientId("YXA6ZRk9u4jqR2e1Gi_Dqrr2Vg")
                    .setClientSecret("YXA6oGWGGHN7HG4uHGz4eKv0vUawvk4")
                    .build();
            emService = new EMService(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
