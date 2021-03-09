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
 * 用户兴趣爱好表
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_interests")
@ApiModel(description = "用户兴趣爱好表属性")
/**
@LogTable(modelName = "用户兴趣爱好表",
        propertyName = "bizInterests",
        tableSqlName = "biz_interests")
**/
public class BizInterests implements Serializable {

    /**
     * 更新用户兴趣爱好表组
     */
    public interface SaveBizInterests {

    }

    /**
     * 更新用户兴趣爱好表组
     */
    public interface UpdateBizInterests {

    }
    @ApiModelProperty(value = "用户兴趣爱好表ID", example = "1")
    @NotNull(groups = {UpdateBizInterests.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "标签名称")
    @NotBlank(groups = {SaveBizInterests.class, UpdateBizInterests.class}, message = "标签名称-interestName不能为空")
    @Size(groups =  {SaveBizInterests.class, UpdateBizInterests.class},max=50,message = "标签名称-interestName长度不能超过50")
    @TableField("INTEREST_NAME")
    private String interestName;
    @ApiModelProperty(value = "标签颜色")
    @NotBlank(groups = {SaveBizInterests.class, UpdateBizInterests.class}, message = "标签颜色-interestColor不能为空")
    @Size(groups =  {SaveBizInterests.class, UpdateBizInterests.class},max=50,message = "标签颜色-interestColor长度不能超过50")
    @TableField("INTEREST_COLOR")
    private String interestColor;
    @ApiModelProperty(value = "标签排序", example = "0")
    @TableField("INTEREST_ORDER_BY")
    private Long interestOrderBy;
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