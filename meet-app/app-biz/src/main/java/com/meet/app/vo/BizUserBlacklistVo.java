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
@ApiModel(description = "我的黑名单列表查询入参")
public class BizUserBlacklistVo extends BaseVO {
    @ApiModelProperty(value = "用户ID", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "朋友ID", example = "0", required = true)
    private Long friendId;
    @ApiModelProperty(value = "朋友手机号")
    private String friendPhone;
    @ApiModelProperty(value = "用户备注")
    private String nameRemark;
}
