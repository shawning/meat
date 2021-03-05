package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaoning
 */

@Data
@ApiModel(description = "应用版本管理列表查询入参")
public class BizVersionVo{
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
