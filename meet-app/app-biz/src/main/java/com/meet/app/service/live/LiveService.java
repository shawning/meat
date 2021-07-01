package com.meet.app.service.live;

import cn.hutool.json.JSONObject;
import com.meet.app.dto.LivePushPullUrlDto;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/7/1 下午8:38
 * @Description
 */
public interface LiveService {
    /**
     * 创建个人主播流
     * @param userId
     * @return
     */
    LivePushPullUrlDto createLivePushUrl(String userId);
}
