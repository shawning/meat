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
@ApiModel(description = "设置密码")
public class BizUserSetPasswordVo extends BaseVO {
    @ApiModelProperty(value = "用户手机号", example = "13219873453", required = true)
    private String phone;
    @ApiModelProperty(value = "密码One", example = "1111", required = true)
    private String password;
    @ApiModelProperty(value = "密码Two", example = "1111", required = true)
    private String passwordTwo;
}
