package com.meet.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/3/5 上午10:11
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "应用版本升级")
public class BizVersionDto {
    @ApiModelProperty(value = "应用名称")
    private String appName;
    @ApiModelProperty(value = "应用类型：1.客户APP，2.物流APP", example = "0", required = true)
    private Integer appType;
    @ApiModelProperty(value = "应用平台 1 Android 0 IOS", example = "0", required = true)
    private Integer appPlatform;
    @ApiModelProperty(value = "应用版本")
    private String appVersion;
    @ApiModelProperty(value = "应用版本整数", example = "0", required = true)
    private Integer appVersionOrder;
    @ApiModelProperty(value = "应用版本状态 1 正常使用2 提示升级 3 强制升级 4 最新版本", example = "0", required = true)
    private Integer appVersionStatus;
    @ApiModelProperty(value = "版本数据状态 1 启用 0 停用", example = "0", required = true)
    private Integer isEnable;
    @ApiModelProperty(value = "应用更新描述")
    private String appUpdateDesc;
    @ApiModelProperty(value = "应用下载地址")
    private String appDownloadUrl;
}
