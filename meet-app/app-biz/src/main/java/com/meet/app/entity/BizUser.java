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
 * 用户信息
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_user")
@ApiModel(description = "用户信息属性")
/**
@LogTable(modelName = "用户信息",
        propertyName = "bizUser",
        tableSqlName = "biz_user")
**/
public class BizUser implements Serializable {

    /**
     * 更新用户信息组
     */
    public interface SaveBizUser {

    }

    /**
     * 更新用户信息组
     */
    public interface UpdateBizUser {

    }
    @ApiModelProperty(value = "用户信息ID", example = "1")
    @NotNull(groups = {UpdateBizUser.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "姓名")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "姓名-name不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=50,message = "姓名-name长度不能超过50")
    @TableField("NAME")
    private String name;
    @ApiModelProperty(value = "昵称")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "昵称-nickName不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=50,message = "昵称-nickName长度不能超过50")
    @TableField("NICK_NAME")
    private String nickName;
    @ApiModelProperty(value = "客户密码")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "客户密码-password不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=200,message = "客户密码-password长度不能超过200")
    @TableField("PASSWORD")
    private String password;
    @ApiModelProperty(value = "手机号")
    @NotBlank(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "手机号-phone不能为空")
    @Size(groups =  {SaveBizUser.class, UpdateBizUser.class},max=11,message = "手机号-phone长度不能超过11")
    @TableField("PHONE")
    private String phone;
    @ApiModelProperty(value = "性别", example = "0")
    @TableField("SEX")
    private Integer sex;
    @ApiModelProperty(value = "性别", example = "0")
    @TableField("TYPE")
    private Integer type;
    @ApiModelProperty(value = "身份证ID")
    @TableField("ID_CARD")
    private String idCard;
    @ApiModelProperty(value = "客户状态", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "客户状态-status不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 4, fraction = 0, message = "客户状态-status参数异常")
    @TableField("STATUS")
    private Integer status;
    @ApiModelProperty(value = "短信开关", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "短信开关-smsStatus不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 4, fraction = 0, message = "短信开关-smsStatus参数异常")
    @TableField("SMS_STATUS")
    private Integer smsStatus;
    @ApiModelProperty(value = "客户类型", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "客户类型-vipType不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 4, fraction = 0, message = "客户类型-vipType参数异常")
    @TableField("VIP_TYPE")
    private Integer vipType;
    @ApiModelProperty(value = "实名认证", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "实名认证-realNameAuth不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 1, fraction = 0, message = "实名认证-realNameAuth参数异常")
    @TableField("REAL_NAME_AUTH")
    private Integer realNameAuth;
    @ApiModelProperty(value = "真人认证", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "真人认证-realPersonAuth不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 1, fraction = 0, message = "真人认证-realPersonAuth参数异常")
    @TableField("REAL_PERSON_AUTH")
    private Integer realPersonAuth;
    @ApiModelProperty(value = "年龄", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "年龄-personAge不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 9, fraction = 0, message = "年龄-personAge参数异常")
    @TableField("PERSON_AGE")
    private Integer personAge;
    @ApiModelProperty(value = "身高", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "身高-personHeight不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 10, fraction = 2, message = "身高-personHeight参数异常")
    @TableField("PERSON_HEIGHT")
    private BigDecimal personHeight;
    @ApiModelProperty(value = "体重", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "体重-personWeight不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 10, fraction = 2, message = "体重-personWeight参数异常")
    @TableField("PERSON_WEIGHT")
    private BigDecimal personWeight;
    @ApiModelProperty(value = "体型")
    @TableField("PERSON_SHAPE")
    private String personShape;
    @ApiModelProperty(value = "职业")
    @TableField("PERSON_JOB")
    private String personJob;
    @ApiModelProperty(value = "年收入")
    @TableField("YEAR_INCOME")
    private String yearIncome;
    @ApiModelProperty(value = "所在地ID", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "所在地ID-personAreaId不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 9, fraction = 0, message = "所在地ID-personAreaId参数异常")
    @TableField("PERSON_AREA_ID")
    private Integer personAreaId;
    @ApiModelProperty(value = "所在地名称")
    @TableField("PERSON_AREA_NAME")
    private String personAreaName;
    @ApiModelProperty(value = "居住情况")
    @TableField("LIVE_INFO")
    private String liveInfo;
    @ApiModelProperty(value = "是否约会", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "是否约会-isAppointment不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 1, fraction = 0, message = "是否约会-isAppointment参数异常")
    @TableField("IS_APPOINTMENT")
    private Integer isAppointment;
    @ApiModelProperty(value = "婚前同居", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "婚前同居-isTogetherBeforeMarried不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 1, fraction = 0, message = "婚前同居-isTogetherBeforeMarried参数异常")
    @TableField("IS_TOGETHER_BEFORE_MARRIED")
    private Integer isTogetherBeforeMarried;
    @ApiModelProperty(value = "是否买房", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "是否买房-ownHouse不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 2, fraction = 0, message = "是否买房-ownHouse参数异常")
    @TableField("OWN_HOUSE")
    private Integer ownHouse;
    @ApiModelProperty(value = "是否买车", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "是否买车-ownCar不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 2, fraction = 0, message = "是否买车-ownCar参数异常")
    @TableField("OWN_CAR")
    private Integer ownCar;
    @ApiModelProperty(value = "豪值", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "豪值-highValue不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 10, fraction = 2, message = "豪值-highValue参数异常")
    @TableField("HIGH_VALUE")
    private BigDecimal highValue;
    @ApiModelProperty(value = "魅力值", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "魅力值-charmValue不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 10, fraction = 2, message = "魅力值-charmValue参数异常")
    @TableField("CHARM_VALUE")
    private BigDecimal charmValue;
    @ApiModelProperty(value = "获赞数量", example = "0")
    @NotNull(groups = {SaveBizUser.class, UpdateBizUser.class}, message = "获赞数量-praisedValue不能为空")
    @Digits(groups = {SaveBizUser.class, UpdateBizUser.class}, integer = 10, fraction = 2, message = "获赞数量-praisedValue参数异常")
    @TableField("PRAISED_VALUE")
    private BigDecimal praisedValue;
    @ApiModelProperty(value = "个性签名")
    @TableField("SIGNATURE")
    private String signature;
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
    @ApiModelProperty(value = "")
    @TableField("PASS_INFO")
    private String passInfo;
    @ApiModelProperty(value = "数据有效性", example = "1")
    @TableLogic
    @TableField("IS_AVAILABLE")
    private Integer isAvailable = 1;

}