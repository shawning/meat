package com.meet.app.service.im.impl;

import com.easemob.im.server.EMService;
import com.easemob.im.server.api.group.settings.UpdateGroupRequest;
import com.easemob.im.server.model.EMBlock;
import com.easemob.im.server.model.EMGroup;
import com.meet.app.service.im.IM;
import com.meet.app.service.im.IMGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/8 下午9:51
 * @Description
 */
@Slf4j
@Service
public class IMGroupServiceImpl implements IMGroupService {
    private EMService emService = IM.server();
    @Override
    public String createPrivateGroup(String owner, String groupName, String description, List<String> members, int maxMembers, boolean canMemberInvite) {
        return emService.group().createPrivateGroup(owner,groupName,description,members,maxMembers,canMemberInvite).block();
    }

    @Override
    public String createPublicGroup(String owner, String groupName, String description, List<String> members, int maxMembers, boolean needApproveToJoin) {
        return emService.group().createPublicGroup(owner, groupName, description, members, maxMembers, needApproveToJoin).block();
    }

    @Override
    public void destroyGroup(String groupId) {
        emService.group().destroyGroup(groupId).block();
    }

    @Override
    public EMGroup getGroup(String groupId) {
        return emService.group().getGroup(groupId).block();
    }

    @Override
    public String getGroupAnnouncement(String groupId) {
        return emService.group().getGroupAnnouncement(groupId).block();
    }

    @Override
    public List<String> listAllGroupMembers(String groupId) {
        return emService.group().listAllGroupMembers(groupId).collectList().block();
    }

    @Override
    public List<String> listAllGroups() {
        return emService.group().listAllGroups().collectList().block();
    }

    @Override
    public List<String> listGroupAdmins(String groupId) {
        return emService.group().listGroupAdmins(groupId).collectList().block();
    }

    @Override
    public List<String> listGroupsUserJoined(String username) {
        return emService.group().listGroupsUserJoined(username).collectList().block();
    }

    @Override
    public void removeGroupAdmin(String groupId, String username) {
        emService.group().removeGroupAdmin(groupId, username).block();
    }

    @Override
    public void removeGroupMember(String groupId, String username) {
        emService.group().removeGroupMember(groupId, username).block();
    }

    @Override
    public void updateGroup(String groupId, Consumer<UpdateGroupRequest> customizer) {
        emService.group().updateGroup(groupId, customizer).block();
    }

    @Override
    public void updateGroupAnnouncement(String groupId, String announcement) {
        emService.group().updateGroupAnnouncement(groupId, announcement).block();
    }

    @Override
    public void updateGroupOwner(String groupId, String username) {
        emService.group().updateGroupOwner(groupId, username).block();
    }

    @Override
    public void addGroupMember(String groupId, String username) {
        emService.group().addGroupMember(groupId, username).block();
    }

    @Override
    public void addGroupAdmin(String groupId, String username) {
        emService.group().addGroupAdmin(groupId, username).block();
    }

    @Override
    public void blockUserJoinGroup(String username, String groupId) {
        emService.block().blockUserJoinGroup(username, groupId).block();
    }

    @Override
    public void blockUserSendMsgToGroup(String username, String groupId, Duration duration) {
        emService.block().blockUserSendMsgToGroup(username, groupId, duration).block();
    }

    @Override
    public List<EMBlock> getUsersBlockedSendMsgToGroup(String groupId) {
        return emService.block().getUsersBlockedSendMsgToGroup(groupId).collectList().block();
    }

    @Override
    public void unblockUserJoinGroup(String username, String groupId) {
        emService.block().unblockUserJoinGroup(username, groupId).block();
    }

    @Override
    public void unblockUserSendMsgToGroup(String username, String groupId) {
        emService.block().unblockUserSendMsgToUser(username, groupId).block();
    }
}
