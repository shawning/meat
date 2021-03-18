package com.meet.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/3/18 下午3:31
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "短信验证码")
public class ValidCodeDto {
    @ApiModelProperty(value = "验证码")
    private String validCode;
}
