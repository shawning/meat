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
 * 用户朋友圈动态
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user_moments")
@ApiModel(description = "用户朋友圈动态属性")
/**
@LogTable(modelName = "用户朋友圈动态",
        propertyName = "bizUserMoments",
        tableSqlName = "biz_user_moments")
**/
public class BizUserMoments implements Serializable {

    /**
     * 更新用户朋友圈动态组
     */
    public interface SaveBizUserMoments {

    }

    /**
     * 更新用户朋友圈动态组
     */
    public interface UpdateBizUserMoments {

    }
    @ApiModelProperty(value = "用户朋友圈动态ID", example = "1")
    @NotNull(groups = {UpdateBizUserMoments.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "用户ID", example = "0")
    @NotNull(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "用户ID-userId不能为空")
    @Digits(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, integer = 11, fraction = 0, message = "用户ID-userId参数异常")
    @TableField("USER_ID")
    private Long userId;
    @ApiModelProperty(value = "用户手机号")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "用户手机号-userPhone不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "用户手机号-userPhone长度不能超过50")
    @TableField("USER_PHONE")
    private String userPhone;
    @ApiModelProperty(value = "用户名称")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "用户名称-userName不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "用户名称-userName长度不能超过50")
    @TableField("USER_NAME")
    private String userName;
    @ApiModelProperty(value = "图片1")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片1-picOne不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片1-picOne长度不能超过50")
    @TableField("PIC_ONE")
    private String picOne;
    @ApiModelProperty(value = "图片2")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片2-picTwo不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片2-picTwo长度不能超过50")
    @TableField("PIC_TWO")
    private String picTwo;
    @ApiModelProperty(value = "图片3")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片3-picThree不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片3-picThree长度不能超过50")
    @TableField("PIC_THREE")
    private String picThree;
    @ApiModelProperty(value = "图片4")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片4-picFour不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片4-picFour长度不能超过50")
    @TableField("PIC_FOUR")
    private String picFour;
    @ApiModelProperty(value = "图片5")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片5-picFive不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片5-picFive长度不能超过50")
    @TableField("PIC_FIVE")
    private String picFive;
    @ApiModelProperty(value = "图片6")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片6-picSix不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片6-picSix长度不能超过50")
    @TableField("PIC_SIX")
    private String picSix;
    @ApiModelProperty(value = "图片7")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片7-picSeven不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片7-picSeven长度不能超过50")
    @TableField("PIC_SEVEN")
    private String picSeven;
    @ApiModelProperty(value = "图片8")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片8-picEight不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片8-picEight长度不能超过50")
    @TableField("PIC_EIGHT")
    private String picEight;
    @ApiModelProperty(value = "图片9")
    @NotBlank(groups = {SaveBizUserMoments.class, UpdateBizUserMoments.class}, message = "图片9-picNine不能为空")
    @Size(groups =  {SaveBizUserMoments.class, UpdateBizUserMoments.class},max=50,message = "图片9-picNine长度不能超过50")
    @TableField("PIC_NINE")
    private String picNine;
    @ApiModelProperty(value = "点赞数", example = "0")
    @TableField("PRAISED_VALUE")
    private Long praisedValue;
    @ApiModelProperty(value = "评论数", example = "0")
    @TableField("COMMENT_VALUE")
    private Long commentValue;
    @ApiModelProperty(value = "转发数", example = "0")
    @TableField("FORWARD_VALUE")
    private Long forwardValue;
    @ApiModelProperty(value = "地址信息")
    @TableField("ADDRESS")
    private String address;
    @ApiModelProperty(value = "地址经纬度")
    @TableField("ADDRESS_GPS")
    private String addressGps;
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