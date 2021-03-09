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
@ApiModel(description = "礼物表列表查询入参")
public class BizGiftsVo extends BaseVO {
    @ApiModelProperty(value = "礼物名称")
    private String giftName;
    @ApiModelProperty(value = "礼物图片地址")
    private String giftPic;
    @ApiModelProperty(value = "礼物排序", example = "0")
    private Long giftOrderBy;
    @ApiModelProperty(value = "备注")
    private String remark;
}
