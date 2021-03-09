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
@ApiModel(description = "用户收到的礼物列表查询入参")
public class BizUserGiftVo extends BaseVO {
    @ApiModelProperty(value = "用户id", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "礼物ID", example = "0", required = true)
    private Long giftId;
    @ApiModelProperty(value = "礼物名称")
    private String giftName;
    @ApiModelProperty(value = "备注")
    private String remark;
}
