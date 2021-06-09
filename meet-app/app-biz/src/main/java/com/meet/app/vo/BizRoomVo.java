package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.youlai.common.base.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaoning
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "直播房间号列表查询入参")
public class BizRoomVo extends BaseVO {
    @ApiModelProperty(value = "房间号")
    private String roomName;
    @ApiModelProperty(value = "最大人数", example = "0")
    private Long maxMembers;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "房主id", example = "0")
    private Long ownerId;
    @ApiModelProperty(value = "房主")
    private String ownerName;
}
