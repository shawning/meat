package com.meet.app.controller;

import com.alibaba.nacos.common.utils.HttpMethod;
import com.meet.app.entity.BizGifts;
import com.meet.app.service.ChatMessageService;
import com.meet.app.vo.ChatRoomMessageVo;
import com.meet.app.vo.ChatSingleMessageVo;
import com.youlai.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.Instant;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/26 下午11:27
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/api.app/v1/chatMessage")
@Api(value = "/api.app/v1/chatMessage", tags = {"聊天室-消息 API"}, description = "聊天室-消息 API")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @ApiOperation(notes = "给单用户发送信息",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "给单用户发送信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chatSingleMessageVo", value = "实体JSON对象", required = true, paramType = "body", dataType = "ChatSingleMessageVo")
    })
    @PostMapping(value = "/singleUser")
    public Result chatToSingleUser(
            @RequestBody ChatSingleMessageVo chatSingleMessageVo) {
        return chatMessageService.chatToSingleUser(chatSingleMessageVo);
    }
    @ApiOperation(notes = "聊天室发送信息",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "聊天室发送信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGifts", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizGifts")
    })
    @PostMapping(value = "/room")
    public Result chatToSingleUser(
            @RequestBody ChatRoomMessageVo chatRoomMessageVo) {
        return chatMessageService.chatToRoom(chatRoomMessageVo);
    }

    @ApiOperation(notes = "获取消息历史",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "获取消息历史",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGifts", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizGifts")
    })
    @GetMapping(value = "/messageList/{day}")
    public Result messageList(
            @PathVariable("day") String day) {
        return chatMessageService.messageList(day);
    }
}
