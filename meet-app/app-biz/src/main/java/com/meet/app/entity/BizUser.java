package com.meet.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user")
@ApiModel(description = "用户信息属性")
public class BizUser implements Serializable {

    /**
     * 更新用户信息组
     */
    public interface SaveBizUser {

    }

    /**
     * 更新用户信息组
     */
    public interface UpdateBizUser {

    }
    @ApiModelProperty(value = "用户信息ID", example = "1")
    @NotNull(groups = {UpdateBizUser.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "姓名")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "姓名-Name不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=50,message = "姓名-Name长度不能超过50")
    @TableField("NAME")
    private String name;
    @ApiModelProperty(value = "客户密码")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "客户密码-Password不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=200,message = "客户密码-Password长度不能超过200")
    @TableField("PASSWORD")
    private String password;
    @ApiModelProperty(value = "手机号")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "手机号-mobilePhone不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=11,message = "手机号-mobilePhone长度不能超过11")
    @TableField("PHONE")
    private String phone;
    @ApiModelProperty(value = "性别", example = "0")
    @TableField("SEX")
    private Integer sex;
    @ApiModelProperty(value = "性别", example = "0")
    @TableField("TYPE")
    private Integer type;
    @ApiModelProperty(value = "身份证ID")
    @TableField("ID_CARD")
    private String idCard;
    @ApiModelProperty(value = "客户状态", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "客户状态-Status不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 4, fraction = 0, message = "客户状态-Status参数异常")
    @TableField("STATUS")
    private Integer Status;
    @ApiModelProperty(value = "短信开关", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "短信开关-smsStatus不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 4, fraction = 0, message = "短信开关-smsStatus参数异常")
    @TableField("SMS_STATUS")
    private Integer smsStatus;
    @ApiModelProperty(value = "客户类型", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "客户类型-vipType不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 4, fraction = 0, message = "客户类型-vipType参数异常")
    @TableField("VIP_TYPE")
    private Integer vipType;
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
    @ApiModelProperty(value = "创建人ID", example = "0")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 11, fraction = 0, message = "创建人ID-createBy参数异常")
    @TableField("CREATE_BY")
    private Long createBy;
    @ApiModelProperty(value = "创建人姓名")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=50,message = "创建人姓名-createByName长度不能超过50")
    @TableField("CREATE_BY_NAME")
    private String createByName;
    @ApiModelProperty(value = "创建时间", example = "2019-05-05 00:00:00")
    @TableField("CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @ApiModelProperty(value = "更新人ID", example = "0")
    @TableField("UPDATE_BY")
    private Long updateBy;
    @ApiModelProperty(value = "更新人姓名")
    @TableField("UPDATE_BY_NAME")
    private String updateByName;
    @ApiModelProperty(value = "更新时间", example = "2019-05-05 00:00:00")
    @TableField("UPDATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    @ApiModelProperty(value = "数据有效性", example = "1")
    @TableLogic
    @TableField("IS_AVAILABLE")
    private Integer isAvailable = 1;

}