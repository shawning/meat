package com.meet.app.service.im.impl;

import cn.hutool.core.util.StrUtil;
import com.easemob.im.server.EMService;
import com.easemob.im.server.api.message.MessageApi;
import com.easemob.im.server.model.EMKeyValue;
import com.easemob.im.server.model.EMMessage;
import com.easemob.im.server.model.EMSentMessageIds;
import com.easemob.im.server.model.EMTextMessage;
import com.meet.app.service.im.IM;
import com.meet.app.service.im.IMMessageService;
import com.youlai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/25 下午5:57
 * @Description
 */
@Slf4j
@Service
public class IMMessageServiceImpl implements IMMessageService {
    private EMService emService = IM.server();
    @Override
    public Result sendToUser(String from, String to, String message) {
        /*emService.message().send()
                .fromUser(from).toUser(to)
                .text(msg -> msg.text("message"))
                .extension(exts -> exts.add(EMKeyValue.of("timeout", 1)))
                .send()
                .block(Duration.ofSeconds(1));
*/
        Set<String> tos = new HashSet<>();
        tos.add(to);
        EMTextMessage message1 = new EMTextMessage();
        message1.text(message);
        EMSentMessageIds emSentMessageIds = emService.message().send(from,"users",tos,message1,null).block();
        return Result.success(emSentMessageIds);
    }

    @Override
    public Result sendToChatRoom(String from, String roomId, String message) {
        Set<String> tos = new HashSet<>();
        tos.add(roomId);
        EMTextMessage message1 = new EMTextMessage();
        message1.text(message);
        EMSentMessageIds emSentMessageIds = emService.message().send(from,"chatrooms",tos,message1,null).block();
        return Result.success(emSentMessageIds);
    }

    @Override
    public Result getHistoryMessage(Instant instant) {
        if(instant == null){
            return Result.success();
        }
        String filePath = emService.message().getHistoryAsUri(instant).block();
        if(StrUtil.isEmpty(filePath)){
            return Result.success();
        }
//        String path = emService.message().getHistoryAsLocalFile()
        return Result.success(filePath);
    }
}
