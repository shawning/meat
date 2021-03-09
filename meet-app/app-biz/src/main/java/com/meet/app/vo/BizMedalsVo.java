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
@ApiModel(description = "勋章表列表查询入参")
public class BizMedalsVo extends BaseVO {
    @ApiModelProperty(value = "勋章名称")
    private String medalName;
    @ApiModelProperty(value = "勋章图片地址")
    private String medalPic;
    @ApiModelProperty(value = "勋章排序", example = "0")
    private Long dedalOrderBy;
    @ApiModelProperty(value = "备注")
    private String remark;
}
