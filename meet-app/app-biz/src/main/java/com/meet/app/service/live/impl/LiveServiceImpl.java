package com.meet.app.service.live.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.meet.app.dto.LivePushPullUrlDto;
import com.meet.app.service.live.LiveService;
import com.youlai.common.utils.LiveUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/7/1 下午8:40
 * @Description
 */
@Configuration
@Service
public class LiveServiceImpl implements LiveService {
    @Value("${ali.live.push.domain}")
    private String pushDomain;
    @Value("${ali.live.push.key}")
    private String pushKey;
    @Value("${ali.live.pull.domain}")
    private String pullDomain;
    @Value("${ali.live.pull.key}")
    private String pullKey;
    @Value("${ali.live.appName}")
    private String appName;
    @Override
    public LivePushPullUrlDto createLivePushUrl(String userId) {
        LivePushPullUrlDto dto = new LivePushPullUrlDto();
        if(StrUtil.isEmpty(userId)){
            return null;
        }
//        String appName = "meet";
//        String streamName = "102";

        long expireTime = 3600L;
//        String pullDomain = "live.meet-ys.com";
//        String pullKey = "ugc0HchXjL";
//
//        String pushDomain = "push.meet-ys.com";
//        String pushKey = "T1i5hlHAhI";
        /**
         * 推流
         */
        String pushUrl = LiveUtils.generate_push_url(pushDomain, pushKey, appName, userId, expireTime);
        dto.setPushUrl(pushUrl);
        /**
         * 播流
         */
        JSONObject pullJson = LiveUtils.general_pull_url(pullDomain, pullKey, appName, userId, expireTime);
        dto.setPullUrl(pullJson.toString());
        return dto;
    }
}
