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
 * 直播房间号
 * @author xiaoning
 */

@Data
@NoArgsConstructor
@TableName("biz_room")
@ApiModel(description = "直播房间号属性")
/**
@LogTable(modelName = "直播房间号",
        propertyName = "bizRoom",
        tableSqlName = "biz_room")
**/
public class BizRoom implements Serializable {

    /**
     * 更新直播房间号组
     */
    public interface SaveBizRoom {

    }

    /**
     * 更新直播房间号组
     */
    public interface UpdateBizRoom {

    }
    @ApiModelProperty(value = "直播房间号ID", example = "1")
    @NotNull(groups = {UpdateBizRoom.class}, message = "ID不能为空")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "房间号")
    @NotBlank(groups = {SaveBizRoom.class, UpdateBizRoom.class}, message = "房间号-roomName不能为空")
    @Size(groups =  {SaveBizRoom.class, UpdateBizRoom.class},max=50,message = "房间号-roomName长度不能超过50")
    @TableField("ROOM_NAME")
    private String roomName;
    @ApiModelProperty(value = "最大人数", example = "0")
    @TableField("MAX_MEMBERS")
    private Long maxMembers;
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
    @ApiModelProperty(value = "房主id", example = "0")
    @TableField("OWNER_ID")
    private Long ownerId;
    @ApiModelProperty(value = "房主")
    @TableField("OWNER_NAME")
    private String ownerName;
    @ApiModelProperty(value = "创建人ID", example = "0")
    @TableField("CREATE_BY")
    private Long createBy;
    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_BY_NAME")
    private String createByName;
    @ApiModelProperty(value = "数据有效性", example = "1")
    @TableLogic
    @TableField("IS_AVAILABLE")
    private Integer isAvailable = 1;
    @ApiModelProperty(value = "是否直播中 1直播中，0下播", example = "1")
    @TableField("ONLINE")
    private Integer online;

    @ApiModelProperty(value = "房间号")
    @TableField("ROOM_ID")
    private String roomId;
    @ApiModelProperty(value = "房间类型 1直播间，2群组", example = "1")
    @TableField("ROOM_TYPE")
    private Integer roomType;

    @ApiModelProperty(value = "推流/直播流（主播）", example = "rtmp://push.meet-ys.com/meet/102?auth_key=1625138758-0-0-3a087349a85924c14868deec55f2aa4b")
    @TableField("PUSH_URL")
    private String pushUrl;

    @ApiModelProperty(value = "拉流/播放流（粉丝）", example = "rtmp://live.meet-ys.com/meet/102?auth_key=1625138758-0-0-3a4a4cd37805fd334cd548ff3b957340")
    @TableField("PULL_URL")
    private String pullUrl;
}