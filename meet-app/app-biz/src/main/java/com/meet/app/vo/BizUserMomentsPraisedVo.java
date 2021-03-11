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
@ApiModel(description = "用户朋友圈点赞记录列表查询入参")
public class BizUserMomentsPraisedVo extends BaseVO {
    @ApiModelProperty(value = "用户ID", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "朋友圈动态ID", example = "0", required = true)
    private Long momentId;
    @ApiModelProperty(value = "点赞数", example = "0")
    private Long praisedValue;
    @ApiModelProperty(value = "备注")
    private String remark;
}
