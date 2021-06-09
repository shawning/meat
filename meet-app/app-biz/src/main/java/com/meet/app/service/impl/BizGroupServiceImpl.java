package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easemob.im.server.api.group.settings.UpdateGroupRequest;
import com.meet.app.entity.BizRoom;
import com.meet.app.mapper.BizRoomMapper;
import com.meet.app.service.BizGroupService;
import com.meet.app.service.im.IMGroupService;
import com.meet.app.vo.BizGroupAdminVo;
import com.meet.app.vo.BizGroupAnnouncementVo;
import com.meet.app.vo.BizGroupMemberVo;
import com.meet.app.vo.BizGroupVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.youlai.common.result.ResultCode.PARAM_ERROR;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/9 下午10:58
 * @Description
 */
@Service
@Slf4j
public class BizGroupServiceImpl implements BizGroupService {
    @Autowired
    private IMGroupService iMGroupService;
    @Autowired
    private BizRoomMapper bizRoomMapper;
    @Override
    public Result createPrivateGroup(BizRoom bizRoom) {
        if (bizRoom == null){
            return Result.failed(PARAM_ERROR);
        }
        bizRoom.setOwnerId(RequestUtils.getUserId());
        bizRoom.setOwnerName(RequestUtils.getUsername());
        if (StrUtil.isEmpty(bizRoom.getRoomName())){
            bizRoom.setRoomName(RequestUtils.getUsername()+"的群");
        }

        bizRoom.setCreateBy(RequestUtils.getUserId());
        bizRoom.setCreateByName(RequestUtils.getUsername());
        bizRoom.setRoomType(2);//群组
        bizRoom.setOnline(0);
        bizRoom.setIsAvailable(1);
        List<String> members = new ArrayList<String>();
        members.add(bizRoom.getOwnerId()+"");
        String roomId = iMGroupService.createPrivateGroup(RequestUtils.getUserId()+"", bizRoom.getRoomName(),"",members,200,true);
        bizRoom.setRoomId(roomId);
        bizRoomMapper.insert(bizRoom);
        return Result.success(bizRoom);
    }

    @Override
    public Result createPublicGroup(BizRoom bizRoom) {
        if (bizRoom == null){
            return Result.failed(PARAM_ERROR);
        }
        bizRoom.setOwnerId(RequestUtils.getUserId());
        bizRoom.setOwnerName(RequestUtils.getUsername());
        if (StrUtil.isEmpty(bizRoom.getRoomName())){
            bizRoom.setRoomName(RequestUtils.getUsername()+"的群");
        }

        bizRoom.setCreateBy(RequestUtils.getUserId());
        bizRoom.setCreateByName(RequestUtils.getUsername());
        bizRoom.setRoomType(2);//群组
        bizRoom.setOnline(0);
        bizRoom.setIsAvailable(1);
        List<String> members = new ArrayList<String>();
        members.add(bizRoom.getOwnerId()+"");
        String roomId = iMGroupService.createPublicGroup(RequestUtils.getUserId()+"", bizRoom.getRoomName(),"",members,200,true);
        bizRoom.setRoomId(roomId);
        bizRoomMapper.insert(bizRoom);
        return Result.success(bizRoom);
    }

    @Override
    public Result destroyGroup(String groupId) {
        iMGroupService.destroyGroup(groupId);
        return Result.success();
    }

    @Override
    public Result getGroup(String groupId) {
        return Result.success(iMGroupService.getGroup(groupId));
    }

    @Override
    public Result getGroupAnnouncement(String groupId) {
        return Result.success(iMGroupService.getGroupAnnouncement(groupId));
    }

    @Override
    public Result listAllGroupMembers(String groupId) {
        return Result.success(iMGroupService.listAllGroupMembers(groupId));
    }

    @Override
    public Result listAllGroups(BizGroupVo bizGroupVo) {
        return Result.success(iMGroupService.listAllGroups());
    }

    @Override
    public Result listGroupAdmins(String groupId) {
        return Result.success(iMGroupService.listGroupAdmins(groupId));
    }

    @Override
    public Result listGroupsUserJoined() {
        return Result.success(iMGroupService.listGroupsUserJoined(RequestUtils.getUserId().toString()));
    }

    @Override
    public Result removeGroupAdmin(BizGroupAdminVo bizGroupAdminVo) {
        if(bizGroupAdminVo == null){
            return Result.failed("参数为空");
        }
        if(StrUtil.isEmpty(bizGroupAdminVo.getGroupId())){
            return Result.failed("群组信息为空");
        }
        if(StrUtil.isEmpty(bizGroupAdminVo.getUsername())){
            return Result.failed("管理员信息为空");
        }
        iMGroupService.removeGroupAdmin(bizGroupAdminVo.getGroupId().toString(),bizGroupAdminVo.getUsername());
        return Result.success();
    }

    @Override
    public Result removeGroupMember(BizGroupMemberVo bizGroupMemberVo) {
        if(bizGroupMemberVo == null){
            return Result.failed("参数为空");
        }
        if(StrUtil.isEmpty(bizGroupMemberVo.getGroupId())){
            return Result.failed("群组信息为空");
        }
        if(StrUtil.isEmpty(bizGroupMemberVo.getUsername())){
            return Result.failed("群员信息为空");
        }
        iMGroupService.removeGroupMember(bizGroupMemberVo.getGroupId().toString(),bizGroupMemberVo.getUsername());
        return Result.success();
    }

    @Override
    public Result updateGroup(String groupId, Consumer<UpdateGroupRequest> customizer) {
        return null;
    }

    @Override
    public Result updateGroupAnnouncement(BizGroupAnnouncementVo bizGroupAnnouncementVo) {
        if(bizGroupAnnouncementVo == null){
            return Result.failed("参数为空");
        }
        if(StrUtil.isEmpty(bizGroupAnnouncementVo.getGroupId())){
            return Result.failed("群组信息为空");
        }
        if(StrUtil.isEmpty(bizGroupAnnouncementVo.getAnnouncement())){
            return Result.failed("群公告信息为空");
        }
        iMGroupService.updateGroupAnnouncement(bizGroupAnnouncementVo.getGroupId(),bizGroupAnnouncementVo.getAnnouncement());
        return Result.success();
    }

    @Override
    public Result updateGroupOwner(BizGroupAdminVo bizGroupAdminVo) {
        if(bizGroupAdminVo == null){
            return Result.failed("参数为空");
        }
        if(StrUtil.isEmpty(bizGroupAdminVo.getGroupId())){
            return Result.failed("群组信息为空");
        }
        if(StrUtil.isEmpty(bizGroupAdminVo.getUsername())){
            return Result.failed("管理员信息为空");
        }
        iMGroupService.updateGroupOwner(bizGroupAdminVo.getGroupId(),bizGroupAdminVo.getUsername());
        return Result.success();
    }

    @Override
    public Result addGroupMember(BizGroupMemberVo bizGroupMemberVo) {
        if(bizGroupMemberVo == null){
            return Result.failed("参数为空");
        }
        if(StrUtil.isEmpty(bizGroupMemberVo.getGroupId())){
            return Result.failed("群组信息为空");
        }
        if(StrUtil.isEmpty(bizGroupMemberVo.getUsername())){
            return Result.failed("群员信息为空");
        }
        iMGroupService.addGroupMember(bizGroupMemberVo.getGroupId().toString(),bizGroupMemberVo.getUsername());
        return Result.success();
    }

    @Override
    public Result addGroupAdmin(BizGroupAdminVo bizGroupAdminVo) {
        if(bizGroupAdminVo == null){
            return Result.failed("参数为空");
        }
        if(StrUtil.isEmpty(bizGroupAdminVo.getGroupId())){
            return Result.failed("群组信息为空");
        }
        if(StrUtil.isEmpty(bizGroupAdminVo.getUsername())){
            return Result.failed("管理员信息为空");
        }
        iMGroupService.addGroupAdmin(bizGroupAdminVo.getGroupId(),bizGroupAdminVo.getUsername());
        return Result.success();
    }

    @Override
    public Result blockUserJoinGroup(String username, String groupId) {
        return null;
    }

    @Override
    public Result blockUserSendMsgToGroup(String username, String groupId, Duration duration) {
        return null;
    }

    @Override
    public Result getUsersBlockedSendMsgToGroup(String groupId) {
        return null;
    }

    @Override
    public Result unblockUserJoinGroup(String username, String groupId) {
        return null;
    }

    @Override
    public Result unblockUserSendMsgToGroup(String username, String groupId) {
        return null;
    }
}
