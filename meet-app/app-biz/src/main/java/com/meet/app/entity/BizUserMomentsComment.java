package com.meet.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.*;

/**
 * 用户朋友圈评论记录
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user_moments_comment")
@ApiModel(description = "用户朋友圈评论记录属性")
/**
@LogTable(modelName = "用户朋友圈评论记录",
        propertyName = "bizUserMomentsComment",
        tableSqlName = "biz_user_moments_comment")
**/
public class BizUserMomentsComment implements Serializable {

    /**
     * 更新用户朋友圈评论记录组
     */
    public interface SaveBizUserMomentsComment {

    }

    /**
     * 更新用户朋友圈评论记录组
     */
    public interface UpdateBizUserMomentsComment {

    }
    @ApiModelProperty(value = "用户朋友圈评论记录ID", example = "1")
    @NotNull(groups = {UpdateBizUserMomentsComment.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户ID", example = "0")
    @NotNull(groups = {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class}, message = "用户ID-userId不能为空")
    @Digits(groups = {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class}, integer = 11, fraction = 0, message = "用户ID-userId参数异常")
    @TableField("USER_ID")
    private Long userId;
    @ApiModelProperty(value = "用户手机号")
    @NotBlank(groups = {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class}, message = "用户手机号-userPhone不能为空")
    @Size(groups =  {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class},max=50,message = "用户手机号-userPhone长度不能超过50")
    @TableField("USER_PHONE")
    private String userPhone;
    @ApiModelProperty(value = "用户名称")
    @NotBlank(groups = {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class}, message = "用户名称-userName不能为空")
    @Size(groups =  {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class},max=50,message = "用户名称-userName长度不能超过50")
    @TableField("USER_NAME")
    private String userName;
    @ApiModelProperty(value = "朋友圈动态ID", example = "0")
    @NotNull(groups = {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class}, message = "朋友圈动态ID-momentId不能为空")
    @Digits(groups = {SaveBizUserMomentsComment.class, UpdateBizUserMomentsComment.class}, integer = 11, fraction = 0, message = "朋友圈动态ID-momentId参数异常")
    @TableField("MOMENT_ID")
    private Long momentId;
    @ApiModelProperty(value = "点赞数", example = "0")
    @TableField("COMMENT_VALUE")
    private Long commentValue;
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