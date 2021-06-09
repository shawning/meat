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
@ApiModel(description = "移除群成员入参")
public class BizGroupMemberVo {
    @ApiModelProperty(value = "群组ID")
    public String groupId;
    @ApiModelProperty(value = "管理员ID")
    public String username;
}
