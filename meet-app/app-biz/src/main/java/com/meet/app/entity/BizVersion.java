package com.meet.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/**
 * 应用版本管理
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_version")
@ApiModel(description = "应用版本管理属性")
public class BizVersion implements Serializable {

    /**
     * 更新应用版本管理组
     */
    public interface SaveBizVersion {

    }

    /**
     * 更新应用版本管理组
     */
    public interface UpdateBizVersion {

    }
    @ApiModelProperty(value = "应用版本管理ID", example = "1")
    @NotNull(groups = {UpdateBizVersion.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "应用名称")
    @NotBlank(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用名称-appName不能为空")
    @Size(groups =  {SaveBizVersion.class, UpdateBizVersion.class},max=50,message = "应用名称-appName长度不能超过50")
    @TableField("APP_NAME")
    private String appName;
    @ApiModelProperty(value = "应用类型：1.客户APP，2.物流APP", example = "0")
    @NotNull(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用类型：1.客户APP，2.物流APP-appType不能为空")
    @Digits(groups = {SaveBizVersion.class, UpdateBizVersion.class}, integer = 4, fraction = 0, message = "应用类型：1.客户APP，2.物流APP-appType参数异常")
    @TableField("APP_TYPE")
    private Integer appType;
    @ApiModelProperty(value = "应用平台 1 Android 0 IOS", example = "0")
    @NotNull(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用平台 1 Android 0 IOS-appPlatform不能为空")
    @Digits(groups = {SaveBizVersion.class, UpdateBizVersion.class}, integer = 4, fraction = 0, message = "应用平台 1 Android 0 IOS-appPlatform参数异常")
    @TableField("APP_PLATFORM")
    private Integer appPlatform;
    @ApiModelProperty(value = "应用版本")
    @NotBlank(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用版本-appVersion不能为空")
    @Size(groups =  {SaveBizVersion.class, UpdateBizVersion.class},max=20,message = "应用版本-appVersion长度不能超过20")
    @TableField("APP_VERSION")
    private String appVersion;
    @ApiModelProperty(value = "应用版本整数", example = "0")
    @NotNull(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用版本整数-appVersionOrder不能为空")
    @Digits(groups = {SaveBizVersion.class, UpdateBizVersion.class}, integer = 8, fraction = 0, message = "应用版本整数-appVersionOrder参数异常")
    @TableField("APP_VERSION_ORDER")
    private Integer appVersionOrder;
    @ApiModelProperty(value = "应用版本状态 1 正常使用2 提示升级 3 强制升级 4 最新版本", example = "0")
    @NotNull(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用版本状态 1 正常使用2 提示升级 3 强制升级 4 最新版本-appVersionStatus不能为空")
    @Digits(groups = {SaveBizVersion.class, UpdateBizVersion.class}, integer = 4, fraction = 0, message = "应用版本状态 1 正常使用2 提示升级 3 强制升级 4 最新版本-appVersionStatus参数异常")
    @TableField("APP_VERSION_STATUS")
    private Integer appVersionStatus;
    @ApiModelProperty(value = "版本数据状态 1 启用 0 停用", example = "0")
    @NotNull(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "版本数据状态 1 启用 0 停用-isEnable不能为空")
    @Digits(groups = {SaveBizVersion.class, UpdateBizVersion.class}, integer = 4, fraction = 0, message = "版本数据状态 1 启用 0 停用-isEnable参数异常")
    @TableField("IS_ENABLE")
    private Integer isEnable;
    @ApiModelProperty(value = "应用更新描述")
    @TableField("APP_UPDATE_DESC")
    private String appUpdateDesc;
    @ApiModelProperty(value = "应用下载地址")
    @NotBlank(groups = {SaveBizVersion.class, UpdateBizVersion.class}, message = "应用下载地址-appDownloadUrl不能为空")
    @Size(groups =  {SaveBizVersion.class, UpdateBizVersion.class},max=300,message = "应用下载地址-appDownloadUrl长度不能超过300")
    @TableField("APP_DOWNLOAD_URL")
    private String appDownloadUrl;
    @ApiModelProperty(value = "创建人", example = "0")
    @TableField("CREATE_BY")
    private Long createBy;
    @ApiModelProperty(value = "创建时间", example = "2019-05-05 00:00:00")
    @TableField("CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @ApiModelProperty(value = "创建人名称")
    @TableField("CREATE_BY_NAME")
    private String createByName;
    @ApiModelProperty(value = "修改人", example = "0")
    @TableField("UPDATE_BY")
    private Long updateBy;
    @ApiModelProperty(value = "修改时间", example = "2019-05-05 00:00:00")
    @TableField("UPDATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    @ApiModelProperty(value = "修改人名称")
    @TableField("UPDATE_BY_NAME")
    private String updateByName;
    @ApiModelProperty(value = "数据有效性", example = "1")
    @TableLogic
    @TableField("IS_AVAILABLE")
    private Integer isAvailable = 1;

}