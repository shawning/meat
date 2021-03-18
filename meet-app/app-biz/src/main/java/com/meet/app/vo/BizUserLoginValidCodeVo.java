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
@ApiModel(description = "验证码登录")
public class BizUserLoginValidCodeVo extends BaseVO {
    @ApiModelProperty(value = "用户手机号", example = "13219873453", required = true)
    private String phone;
    @ApiModelProperty(value = "验证码", example = "1111", required = true)
    private String validCode;
}
