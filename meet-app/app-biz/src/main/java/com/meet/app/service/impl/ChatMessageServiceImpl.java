package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializerBase;
import com.meet.app.service.ChatMessageService;
import com.meet.app.service.im.IMMessageService;
import com.meet.app.vo.ChatRoomMessageVo;
import com.meet.app.vo.ChatSingleMessageVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/27 下午2:03
 * @Description
 */
@Slf4j
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private IMMessageService imMessageService;
    @Override
    public Result chatToSingleUser(ChatSingleMessageVo chatSingleMessageVo) {
        if(chatSingleMessageVo == null){
            return Result.failed("单聊数据不能为空");
        }
        if(RequestUtils.getUserId() == null){
            return imMessageService.sendToUser("12",chatSingleMessageVo.getUsername(),chatSingleMessageVo.getMessage());
        }
        return imMessageService.sendToUser(RequestUtils.getUserId().toString(),chatSingleMessageVo.getUsername(),chatSingleMessageVo.getMessage());
    }

    @Override
    public Result chatToRoom(ChatRoomMessageVo chatRoomMessageVo) {
        if(chatRoomMessageVo == null){
            return Result.failed("聊天室数据不能为空");
        }
        if(RequestUtils.getUserId() == null){
            return imMessageService.sendToChatRoom("12",chatRoomMessageVo.getRoomId(),chatRoomMessageVo.getMessage());
        }
        return imMessageService.sendToChatRoom(RequestUtils.getUserId().toString(),chatRoomMessageVo.getRoomId(),chatRoomMessageVo.getMessage());
    }

    @Override
    public Result messageList(String day) {
        Instant instant;
        if(StrUtil.isEmpty(day)){
            instant = Instant.now();
        }
        instant = LocalDateTime.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .withLocale(Locale.CHINA ))
                .toInstant(ZoneOffset.UTC);
        return imMessageService.getHistoryMessage(instant);
    }
}
