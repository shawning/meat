package com.meet.app.vo;

import com.youlai.common.base.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaoning
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "群组列表查询入参")
public class BizGroupVo extends BaseVO {
    @ApiModelProperty(value = "群组名称")
    private String groupName;
    @ApiModelProperty(value = "最大人数", example = "0")
    private Long maxMembers;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "房主id", example = "0")
    private Long ownerId;
    @ApiModelProperty(value = "房主")
    private String ownerName;
}
