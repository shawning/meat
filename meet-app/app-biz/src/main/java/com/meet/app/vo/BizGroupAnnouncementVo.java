package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/9 下午10:41
 * @Description
 */
@Data
@ApiModel(description = "群公告入参")
public class BizGroupAnnouncementVo {
    @ApiModelProperty(value = "群组ID")
    public String groupId;
    @ApiModelProperty(value = "公告详情")
    public String announcement;
}
