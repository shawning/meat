package com.meet.hbase.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/2 下午10:23
 * @Description
 */
@Data
@Builder
public class SingleMessageDto {
    private String messageId;
    private String sendTo;
    private String sendToName;
    private String sendFrom;
    private String sendFromName;
    private String message;
    private Date date;
}
