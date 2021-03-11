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
 * 我的好友
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user_friends")
@ApiModel(description = "我的好友属性")
/**
@LogTable(modelName = "我的好友",
        propertyName = "bizUserFriends",
        tableSqlName = "biz_user_friends")
**/
public class BizUserFriends implements Serializable {

    /**
     * 更新我的好友组
     */
    public interface SaveBizUserFriends {

    }

    /**
     * 更新我的好友组
     */
    public interface UpdateBizUserFriends {

    }
    @ApiModelProperty(value = "我的好友ID", example = "1")
    @NotNull(groups = {UpdateBizUserFriends.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户ID", example = "0")
    @NotNull(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, message = "用户ID-userId不能为空")
    @Digits(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, integer = 11, fraction = 0, message = "用户ID-userId参数异常")
    @TableField("USER_ID")
    private Long userId;
    @ApiModelProperty(value = "朋友ID", example = "0")
    @NotNull(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, message = "朋友ID-friendId不能为空")
    @Digits(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, integer = 11, fraction = 0, message = "朋友ID-friendId参数异常")
    @TableField("FRIEND_ID")
    private Long friendId;
    @ApiModelProperty(value = "朋友手机号")
    @NotBlank(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, message = "朋友手机号-friendPhone不能为空")
    @Size(groups =  {SaveBizUserFriends.class, UpdateBizUserFriends.class},max=50,message = "朋友手机号-friendPhone长度不能超过50")
    @TableField("FRIEND_PHONE")
    private String friendPhone;
    @ApiModelProperty(value = "亲密度", example = "0")
    @NotNull(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, message = "亲密度-intimacy不能为空")
    @Digits(groups = {SaveBizUserFriends.class, UpdateBizUserFriends.class}, integer = 11, fraction = 0, message = "亲密度-intimacy参数异常")
    @TableField("INTIMACY")
    private Long intimacy;
    @ApiModelProperty(value = "用户备注")
    @TableField("NAME_REMARK")
    private String nameRemark;
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