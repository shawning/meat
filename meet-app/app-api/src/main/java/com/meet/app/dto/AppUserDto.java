package com.meet.app.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/2/25 下午5:42
 * @Description
 */
@Data
public class AppUserDto implements Serializable {
    @ApiModelProperty(value = "用户信息ID", example = "1")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "客户密码")
    private String password;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "性别", example = "0")
    private Integer sex;
    @ApiModelProperty(value = "性别", example = "0")
    private Integer type;
    @ApiModelProperty(value = "身份证ID")
    private String idCard;
    @ApiModelProperty(value = "客户状态", example = "0")
    private Integer Status;
    @ApiModelProperty(value = "短信开关", example = "0")
    private Integer smsStatus;
    @ApiModelProperty(value = "客户类型", example = "0")
    private Integer vipType;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建人ID", example = "0")
    private Long createBy;
    @ApiModelProperty(value = "创建人姓名")
    private String createByName;
    @ApiModelProperty(value = "创建时间", example = "2019-05-05 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @ApiModelProperty(value = "更新人ID", example = "0")
    private Long updateBy;
    @ApiModelProperty(value = "更新人姓名")
    private String updateByName;
    @ApiModelProperty(value = "更新时间", example = "2019-05-05 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    @ApiModelProperty(value = "数据有效性", example = "1")
    private Integer isAvailable = 1;
    @ApiModelProperty(value = "oauth clientid")
    private String clientId;
}
