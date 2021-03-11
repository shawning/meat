package com.meet.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.youlai.common.base.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaoning
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户朋友圈动态列表查询入参")
public class BizUserMomentsVo extends BaseVO {
    @ApiModelProperty(value = "用户ID", example = "0", required = true)
    private Long userId;
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "图片1")
    private String picOne;
    @ApiModelProperty(value = "图片2")
    private String picTwo;
    @ApiModelProperty(value = "图片3")
    private String picThree;
    @ApiModelProperty(value = "图片4")
    private String picFour;
    @ApiModelProperty(value = "图片5")
    private String picFive;
    @ApiModelProperty(value = "图片6")
    private String picSix;
    @ApiModelProperty(value = "图片7")
    private String picSeven;
    @ApiModelProperty(value = "图片8")
    private String picEight;
    @ApiModelProperty(value = "图片9")
    private String picNine;
    @ApiModelProperty(value = "点赞数", example = "0")
    private Long praisedValue;
    @ApiModelProperty(value = "评论数", example = "0")
    private Long commentValue;
    @ApiModelProperty(value = "转发数", example = "0")
    private Long forwardValue;
    @ApiModelProperty(value = "地址信息")
    private String address;
    @ApiModelProperty(value = "地址经纬度")
    private String addressGps;
    @ApiModelProperty(value = "备注")
    private String remark;
}
