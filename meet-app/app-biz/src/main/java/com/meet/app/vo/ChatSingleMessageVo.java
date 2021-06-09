package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/26 下午11:32
 * @Description
 */
@Data
@ApiModel(description = "单用户聊天")
public class ChatSingleMessageVo {
    @ApiModelProperty(value = "对方用户id", example = "0", required = true)
    private String sendTo;
    @ApiModelProperty(value = "消息内容", example = "0", required = true)
    private String message;
}
