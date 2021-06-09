package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.easemob.im.server.model.EMSentMessageIds;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializerBase;
import com.meet.app.entity.BizUser;
import com.meet.app.mapper.BizUserMapper;
import com.meet.app.service.ChatMessageService;
import com.meet.app.service.im.IMMessageService;
import com.meet.app.vo.ChatRoomMessageVo;
import com.meet.app.vo.ChatSingleMessageVo;
import com.meet.hbase.dto.RoomMessageDto;
import com.meet.hbase.dto.SingleMessageDto;
import com.meet.hbase.service.IHBaseService;
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
import java.util.Date;
import java.util.List;
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
    @Autowired
    private IHBaseService ihBaseService;
    private BizUserMapper bizUserMapper;

    @Override
    public Result chatToSingleUser(ChatSingleMessageVo chatSingleMessageVo) {
        String from,fromName = "",toName;
        if(chatSingleMessageVo == null){
            return Result.failed("单聊数据不能为空");
        }
        if(RequestUtils.getUserId() == null){
            from = "12";
        }else{
            from = RequestUtils.getUserId().toString();
            fromName = RequestUtils.getUsername();
        }
        BizUser bizUser = bizUserMapper.selectById(chatSingleMessageVo.getSendTo());
        toName = bizUser.getName();
        Result<EMSentMessageIds> result = imMessageService.sendToUser(from, chatSingleMessageVo.getSendTo(), chatSingleMessageVo.getMessage());
        String messageId = result.getData().getMessageIdsByEntityId().get(chatSingleMessageVo.getSendTo());
        ihBaseService.saveSingleChatMessage(SingleMessageDto.builder().messageId(messageId)
                .sendFrom(from)
                .sendFromName(fromName)
                .sendTo(chatSingleMessageVo.getSendTo())
                .sendToName(toName)
                .message(chatSingleMessageVo.getMessage())
                .date(new Date(System.currentTimeMillis()))
                .build());
        return result;
    }

    @Override
    public Result chatToRoom(ChatRoomMessageVo chatRoomMessageVo) {
        String from,fromName = "",toName;
        if(chatRoomMessageVo == null){
            return Result.failed("聊天室数据不能为空");
        }
        if(RequestUtils.getUserId() == null){
            from = "12";
        }else{
            from = RequestUtils.getUserId().toString();
            fromName = RequestUtils.getUsername();
        }
        Result<EMSentMessageIds> result = imMessageService.sendToChatRoom(from,chatRoomMessageVo.getRoomId(),chatRoomMessageVo.getMessage());
        String messageId = result.getData().getMessageIdsByEntityId().get(chatRoomMessageVo.getRoomId());
        ihBaseService.saveChatMessage(RoomMessageDto.builder().messageId(messageId)
                .sendFrom(from)
                .roomId(chatRoomMessageVo.getRoomId())
                .message(chatRoomMessageVo.getMessage())
                .date(new Date(System.currentTimeMillis()))
                .build());
        return result;
    }

    @Override
    public Result singleMessageList(String sendTo,String day) {
        if(StrUtil.isEmpty(sendTo)){
            return Result.success();
        }
        String sendFrom;
        if(RequestUtils.getUserId() == null){
            sendFrom = "12";
        }else{
            sendFrom = RequestUtils.getUserId().toString();
        }
        /*Instant instant;  环信聊天记录
        if(StrUtil.isEmpty(day)){
            instant = Instant.now();
        }
        instant = LocalDateTime.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault())
                .withLocale(Locale.CHINA ))
                .toInstant(ZoneOffset.UTC);*/
        List<SingleMessageDto> list = ihBaseService.getSingleMessages(SingleMessageDto.class,sendTo,sendFrom,day);
        return Result.success(list);
//        return imMessageService.getHistoryMessage(instant);
    }

    @Override
    public Result roomMessageList(String roomId,String day) {
        if(StrUtil.isEmpty(roomId)){
            return Result.success();
        }
        String sendFrom;
        if(RequestUtils.getUserId() == null){
            sendFrom = "12";
        }else{
            sendFrom = RequestUtils.getUserId().toString();
        }
        List<RoomMessageDto> list = ihBaseService.getChatRoomMessages(RoomMessageDto.class,roomId,sendFrom,day);
        return Result.success(list);
    }
}
