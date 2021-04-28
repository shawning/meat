package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/26 下午11:32
 * @Description
 */
@Data
@ApiModel(description = "聊天室聊天")
public class ChatRoomMessageVo {
    @ApiModelProperty(value = "聊天室id", example = "0", required = true)
    private String roomId;
    @ApiModelProperty(value = "消息内容", example = "0", required = true)
    private String message;
}
