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
@ApiModel(description = "用户标签表列表查询入参")
public class BizTagsVo extends BaseVO {
    @ApiModelProperty(value = "标签名称")
    private String tagName;
    @ApiModelProperty(value = "标签颜色")
    private String tagColor;
    @ApiModelProperty(value = "标签排序", example = "0")
    private Long tagOrderBy;
    @ApiModelProperty(value = "备注")
    private String remark;
}
