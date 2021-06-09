package com.meet.app.service;

import com.meet.app.vo.ChatRoomMessageVo;
import com.meet.app.vo.ChatSingleMessageVo;
import com.youlai.common.result.Result;

import java.time.Instant;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/27 下午2:02
 * @Description
 */
public interface ChatMessageService {
    Result chatToSingleUser(ChatSingleMessageVo chatSingleMessageVo);
    Result chatToRoom(ChatRoomMessageVo chatRoomMessageVo);
    Result singleMessageList(String sendTo,String day);
    Result roomMessageList(String roomId,String day);
}
