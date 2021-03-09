package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.youlai.common.base.BaseVO;

/**
 * @author xiaoning
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户标签列表查询入参")
public class BizUserTagVo extends BaseVO {
    @ApiModelProperty(value = "用户id", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "标签ID", example = "0", required = true)
    private Long tagId;
    @ApiModelProperty(value = "标签名称")
    private String tagName;
    @ApiModelProperty(value = "备注")
    private String remark;
}
