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
 * 用户标签表
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_tags")
@ApiModel(description = "用户标签表属性")
/**
@LogTable(modelName = "用户标签表",
        propertyName = "bizTags",
        tableSqlName = "biz_tags")
**/
public class BizTags implements Serializable {

    /**
     * 更新用户标签表组
     */
    public interface SaveBizTags {

    }

    /**
     * 更新用户标签表组
     */
    public interface UpdateBizTags {

    }
    @ApiModelProperty(value = "用户标签表ID", example = "1")
    @NotNull(groups = {UpdateBizTags.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "标签名称")
    @NotBlank(groups = {SaveBizTags.class, UpdateBizTags.class}, message = "标签名称-tagName不能为空")
    @Size(groups =  {SaveBizTags.class, UpdateBizTags.class},max=50,message = "标签名称-tagName长度不能超过50")
    @TableField("TAG_NAME")
    private String tagName;
    @ApiModelProperty(value = "标签颜色")
    @NotBlank(groups = {SaveBizTags.class, UpdateBizTags.class}, message = "标签颜色-tagColor不能为空")
    @Size(groups =  {SaveBizTags.class, UpdateBizTags.class},max=50,message = "标签颜色-tagColor长度不能超过50")
    @TableField("TAG_COLOR")
    private String tagColor;
    @ApiModelProperty(value = "标签排序", example = "0")
    @TableField("TAG_ORDER_BY")
    private Long tagOrderBy;
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