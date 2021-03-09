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
 * 用户收到的礼物
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user_gift")
@ApiModel(description = "用户收到的礼物属性")
/**
@LogTable(modelName = "用户收到的礼物",
        propertyName = "bizUserGift",
        tableSqlName = "biz_user_gift")
**/
public class BizUserGift implements Serializable {

    /**
     * 更新用户收到的礼物组
     */
    public interface SaveBizUserGift {

    }

    /**
     * 更新用户收到的礼物组
     */
    public interface UpdateBizUserGift {

    }
    @ApiModelProperty(value = "用户收到的礼物ID", example = "1")
    @NotNull(groups = {UpdateBizUserGift.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户id", example = "0")
    @NotNull(groups = {SaveBizUserGift.class, UpdateBizUserGift.class}, message = "用户id-userId不能为空")
    @Digits(groups = {SaveBizUserGift.class, UpdateBizUserGift.class}, integer = 11, fraction = 0, message = "用户id-userId参数异常")
    @TableField("USER_ID")
    private Long userId;
    @ApiModelProperty(value = "礼物ID", example = "0")
    @NotNull(groups = {SaveBizUserGift.class, UpdateBizUserGift.class}, message = "礼物ID-giftId不能为空")
    @Digits(groups = {SaveBizUserGift.class, UpdateBizUserGift.class}, integer = 11, fraction = 0, message = "礼物ID-giftId参数异常")
    @TableField("GIFT_ID")
    private Long giftId;
    @ApiModelProperty(value = "礼物名称")
    @NotBlank(groups = {SaveBizUserGift.class, UpdateBizUserGift.class}, message = "礼物名称-giftName不能为空")
    @Size(groups =  {SaveBizUserGift.class, UpdateBizUserGift.class},max=50,message = "礼物名称-giftName长度不能超过50")
    @TableField("GIFT_NAME")
    private String giftName;
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