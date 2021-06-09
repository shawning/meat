package com.meet.app.service.im;

import com.youlai.common.result.Result;

import java.time.Instant;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/25 下午5:49
 * @Description
 */
public interface IMMessageService {
    /**
     * 发送给单个用户
     * @param from
     * @param to
     * @param message
     * @return
     */
    Result sendToUser(String from,String to,String message);

    /**
     * 发送给群聊
     * @param from
     * @param roomId
     * @param message
     * @return
     */
    Result sendToChatRoom(String from,String roomId,String message);

    /**
     * 群组聊天
     * @param from
     * @param groupId
     * @param message
     * @return
     */
    Result sendToGroup(String from,String groupId,String message);

    Result getHistoryMessage(Instant instant);
}
