package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiaoning
 */

@Data
//@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设置密码")
public class BizUserForgotPasswordVo {
    @ApiModelProperty(value = "用户手机号", example = "13219873453", required = true)
    private String phone;
    @ApiModelProperty(value = "手机验证码", example = "8888", required = true)
    private String validCode;
    @ApiModelProperty(value = "密码", example = "1111", required = true)
    private String password;

}
