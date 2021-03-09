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
 * 用户兴趣爱好
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user_interests")
@ApiModel(description = "用户兴趣爱好属性")
/**
@LogTable(modelName = "用户兴趣爱好",
        propertyName = "bizUserInterests",
        tableSqlName = "biz_user_interests")
**/
public class BizUserInterests implements Serializable {

    /**
     * 更新用户兴趣爱好组
     */
    public interface SaveBizUserInterests {

    }

    /**
     * 更新用户兴趣爱好组
     */
    public interface UpdateBizUserInterests {

    }
    @ApiModelProperty(value = "用户兴趣爱好ID", example = "1")
    @NotNull(groups = {UpdateBizUserInterests.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户id", example = "0")
    @NotNull(groups = {SaveBizUserInterests.class, UpdateBizUserInterests.class}, message = "用户id-userId不能为空")
    @Digits(groups = {SaveBizUserInterests.class, UpdateBizUserInterests.class}, integer = 11, fraction = 0, message = "用户id-userId参数异常")
    @TableField("USER_ID")
    private Long userId;
    @ApiModelProperty(value = "兴趣爱好ID", example = "0")
    @NotNull(groups = {SaveBizUserInterests.class, UpdateBizUserInterests.class}, message = "兴趣爱好ID-interestId不能为空")
    @Digits(groups = {SaveBizUserInterests.class, UpdateBizUserInterests.class}, integer = 11, fraction = 0, message = "兴趣爱好ID-interestId参数异常")
    @TableField("INTEREST_ID")
    private Long interestId;
    @ApiModelProperty(value = "兴趣爱好名称")
    @NotBlank(groups = {SaveBizUserInterests.class, UpdateBizUserInterests.class}, message = "兴趣爱好名称-interestName不能为空")
    @Size(groups =  {SaveBizUserInterests.class, UpdateBizUserInterests.class},max=50,message = "兴趣爱好名称-interestName长度不能超过50")
    @TableField("INTEREST_NAME")
    private String interestName;
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