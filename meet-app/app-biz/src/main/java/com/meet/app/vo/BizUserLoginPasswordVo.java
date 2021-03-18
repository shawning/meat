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
@ApiModel(description = "密码登录")
public class BizUserLoginPasswordVo extends BaseVO {
    @ApiModelProperty(value = "用户手机号", example = "13219873453", required = true)
    private String phone;
    @ApiModelProperty(value = "用户密码", example = "1111", required = true)
    private String password;
}
