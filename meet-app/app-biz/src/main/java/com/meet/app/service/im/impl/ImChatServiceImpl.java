package com.meet.app.service.im.impl;

import com.meet.app.service.im.IM;
import com.meet.app.service.im.ImChatService;
import org.springframework.stereotype.Service;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/23 下午10:31
 * @Description
 */
@Service
public class ImChatServiceImpl implements ImChatService{
    public void sss(){
        IM.server();
    }
}
