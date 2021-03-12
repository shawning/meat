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
 * 勋章表
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_medals")
@ApiModel(description = "勋章表属性")
/**
@LogTable(modelName = "勋章表",
        propertyName = "bizMedals",
        tableSqlName = "biz_medals")
**/
public class BizMedals implements Serializable {

    /**
     * 更新勋章表组
     */
    public interface SaveBizMedals {

    }

    /**
     * 更新勋章表组
     */
    public interface UpdateBizMedals {

    }
    @ApiModelProperty(value = "勋章表ID", example = "1")
    @NotNull(groups = {UpdateBizMedals.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "勋章名称")
    @NotEmpty(message = "勋章名称不能为空")
    @Size(groups =  {SaveBizMedals.class, UpdateBizMedals.class},max=50,message = "勋章名称-medalName长度不能超过50")
    @TableField("MEDAL_NAME")
    private String medalName;
    @ApiModelProperty(value = "勋章图片地址")
    @NotBlank(groups = {SaveBizMedals.class, UpdateBizMedals.class}, message = "勋章图片地址-medalPic不能为空")
    @Size(groups =  {SaveBizMedals.class, UpdateBizMedals.class},max=50,message = "勋章图片地址-medalPic长度不能超过50")
    @TableField("MEDAL_PIC")
    private String medalPic;
    @ApiModelProperty(value = "勋章排序", example = "0")
    @TableField("DEDAL_ORDER_BY")
    private Long dedalOrderBy;
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