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
@ApiModel(description = "用户兴趣爱好列表查询入参")
public class BizUserInterestsVo extends BaseVO {
    @ApiModelProperty(value = "用户id", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "兴趣爱好ID", example = "0", required = true)
    private Long interestId;
    @ApiModelProperty(value = "兴趣爱好名称")
    private String interestName;
    @ApiModelProperty(value = "备注")
    private String remark;
}
