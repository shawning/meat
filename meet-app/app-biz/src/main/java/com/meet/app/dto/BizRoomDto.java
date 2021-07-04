package com.meet.app.dto;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 直播房间号
 * @author xiaoning
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "直播房间号属性")

public class BizRoomDto {

    @ApiModelProperty(value = "直播房间号ID", example = "1")
    private Long id;
    @ApiModelProperty(value = "房间号")
    private String roomName;
    @ApiModelProperty(value = "最大人数", example = "0")
    private Long maxMembers;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "房主id", example = "0")
    private Long ownerId;
    @ApiModelProperty(value = "房主")
    private String ownerName;
    @ApiModelProperty(value = "创建人ID", example = "0")
    private Long createBy;
    @ApiModelProperty(value = "创建人姓名")
    private String createByName;
    @ApiModelProperty(value = "数据有效性", example = "1")
    private Integer isAvailable = 1;
    @ApiModelProperty(value = "是否直播中 1直播中，0下播", example = "1")
    private Integer online;

    @ApiModelProperty(value = "房间号")
    private String roomId;
    @ApiModelProperty(value = "房间类型 1直播间，2群组", example = "1")
    private Integer roomType;

    @ApiModelProperty(value = "推流/直播流（主播）", example = "rtmp://push.meet-ys.com/meet/102?auth_key=1625138758-0-0-3a087349a85924c14868deec55f2aa4b")
    private String pushUrl;

    @ApiModelProperty(value = "拉流/播放流（粉丝）", example = "rtmp://live.meet-ys.com/meet/102?auth_key=1625138758-0-0-3a4a4cd37805fd334cd548ff3b957340")
    private JSONObject pullUrl;
}