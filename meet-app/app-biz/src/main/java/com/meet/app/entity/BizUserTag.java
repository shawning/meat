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
 * 用户标签
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user_tag")
@ApiModel(description = "用户标签属性")
public class BizUserTag implements Serializable {

    /**
     * 更新用户标签组
     */
    public interface SaveBizUserTag {

    }

    /**
     * 更新用户标签组
     */
    public interface UpdateBizUserTag {

    }
    @ApiModelProperty(value = "用户标签ID", example = "1")
    @NotNull(groups = {UpdateBizUserTag.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户id", example = "0")
    @NotNull(groups = {SaveBizUserTag.class, UpdateBizUserTag.class}, message = "用户id-userId不能为空")
    @Digits(groups = {SaveBizUserTag.class, UpdateBizUserTag.class}, integer = 11, fraction = 0, message = "用户id-userId参数异常")
    @TableField("USER_ID")
    private Long userId;
    @ApiModelProperty(value = "标签ID", example = "0")
    @NotNull(groups = {SaveBizUserTag.class, UpdateBizUserTag.class}, message = "标签ID-tagId不能为空")
    @Digits(groups = {SaveBizUserTag.class, UpdateBizUserTag.class}, integer = 11, fraction = 0, message = "标签ID-tagId参数异常")
    @TableField("TAG_ID")
    private Long tagId;
    @ApiModelProperty(value = "标签名称")
    @NotBlank(groups = {SaveBizUserTag.class, UpdateBizUserTag.class}, message = "标签名称-tagName不能为空")
    @Size(groups =  {SaveBizUserTag.class, UpdateBizUserTag.class},max=50,message = "标签名称-tagName长度不能超过50")
    @TableField("TAG_NAME")
    private String tagName;
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
    @ApiModelProperty(value = "创建人ID", example = "0")
    @TableField("CREATE_BY")
    private Long createBy;
    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_BY_NAME")
    private String createByName;
    @ApiModelProperty(value = "创建时间", example = "2019-05-05 00:00:00")
    @TableField("CREATE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    private Date updateDate;
    @ApiModelProperty(value = "数据有效性", example = "1")
    @TableLogic
    @TableField("IS_AVAILABLE")
    private Integer isAvailable = 1;

}