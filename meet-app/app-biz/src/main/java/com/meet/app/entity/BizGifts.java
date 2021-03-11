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
 * 礼物表
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_gifts")
@ApiModel(description = "礼物表属性")
/**
@LogTable(modelName = "礼物表",
        propertyName = "bizGifts",
        tableSqlName = "biz_gifts")
**/
public class BizGifts implements Serializable {

    /**
     * 更新礼物表组
     */
    public interface SaveBizGifts {

    }

    /**
     * 更新礼物表组
     */
    public interface UpdateBizGifts {

    }
    @ApiModelProperty(value = "礼物表ID", example = "1")
    @NotNull(groups = {UpdateBizGifts.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "礼物名称",required=true)
    @NotBlank(groups = {SaveBizGifts.class, UpdateBizGifts.class}, message = "礼物名称-giftName不能为空")
    @Size(groups =  {SaveBizGifts.class, UpdateBizGifts.class},max=50,message = "礼物名称-giftName长度不能超过50")
    @TableField("GIFT_NAME")
    private String giftName;
    @ApiModelProperty(value = "礼物图片地址")
    @NotBlank(groups = {SaveBizGifts.class, UpdateBizGifts.class}, message = "礼物图片地址-giftPic不能为空")
    @Size(groups =  {SaveBizGifts.class, UpdateBizGifts.class},max=50,message = "礼物图片地址-giftPic长度不能超过50")
    @TableField("GIFT_PIC")
    private String giftPic;
    @ApiModelProperty(value = "礼物排序", example = "0")
    @TableField("GIFT_ORDER_BY")
    private Long giftOrderBy;
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