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
@ApiModel(description = "用户勋章列表查询入参")
public class BizUserMedalVo extends BaseVO {
    @ApiModelProperty(value = "用户id", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "勋章ID", example = "0", required = true)
    private Long medalId;
    @ApiModelProperty(value = "勋章名称")
    private String medalName;
    @ApiModelProperty(value = "备注")
    private String remark;
}
