package com.meet.app.service.im;

import com.easemob.im.server.api.group.settings.UpdateGroupRequest;
import com.easemob.im.server.model.EMBlock;
import com.easemob.im.server.model.EMGroup;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/8 下午9:27
 * @Description
 */
public interface IMGroupService {
    /**
     * 创建公开群
     * @param owner
     * @param groupName
     * @param description
     * @param members
     * @param maxMembers
     * @param canMemberInvite
     * @return
     */
    String createPrivateGroup(String owner, String groupName, String description, List<String> members, int maxMembers, boolean canMemberInvite);

    /**
     * 创建公开群
     * @param owner
     * @param groupName
     * @param description
     * @param members
     * @param maxMembers
     * @param needApproveToJoin
     * @return
     */
    String createPublicGroup(String owner, String groupName, String description, List<String> members, int maxMembers, boolean needApproveToJoin);

    /**
     * 注销群
     * @param groupId
     */
    void destroyGroup(String groupId);

    /**
     * 获取群详情
     * @param groupId
     * @return
     */
    EMGroup getGroup(String groupId);

    /**
     * 获取群公告
     * @param groupId
     * @return
     */
    String getGroupAnnouncement(String groupId);

    /**
     * 获取群全部成员
     * @param groupId
     * @return
     */
    List<String> listAllGroupMembers(String groupId);

    /**
     * 获取全部群列表
     * @return
     */
    List<String> listAllGroups();

    /**
     * 获取群全部管理员
     * @param groupId
     * @return
     */
    List<String> listGroupAdmins(String groupId);

    /**
     * 获取用户所在的群组
     * @param username
     * @return
     */
    List<String> listGroupsUserJoined(String username);

    /**
     * 降级群管理员为群成员
     * @param groupId
     * @param username
     */
    void removeGroupAdmin(String groupId, String username);

    /**
     * 移除群成员
     * @param groupId
     * @param username
     */
    void removeGroupMember(String groupId, String username);

    /**
     * 修改群详情
     * @param groupId
     * @param customizer
     */
    void updateGroup(String groupId, Consumer<UpdateGroupRequest> customizer);

    /**
     * 更新群公告
     * @param groupId
     * @param announcement
     */
    void updateGroupAnnouncement(String groupId, String announcement);

    /**
     * 修改群主(移交群主）
     * @param groupId
     * @param username
     */
    void updateGroupOwner(String groupId, String username);

    /**
     * 添加群成员
     * @param groupId
     * @param username
     */
    void addGroupMember(String groupId, String username);

    /**
     * 升级群成员为群管理员
     * @param groupId
     * @param username
     */
    void addGroupAdmin(String groupId, String username);

    /**
     * 阻止某用户进群
     * @param username
     * @param groupId
     */
    void blockUserJoinGroup(String username, String groupId);

    /**
     * 添加群禁言
     * @param username
     * @param groupId
     * @param duration
     */
    void blockUserSendMsgToGroup(String username, String groupId, Duration duration);

    /**
     * 获取群禁言列表
     * @param groupId
     * @return
     */
    List<EMBlock> getUsersBlockedSendMsgToGroup(String groupId);

    /**
     * 解除阻止进群
     * @param username
     * @param groupId
     */
    void unblockUserJoinGroup(String username, String groupId);

    /**
     * 解除群禁言。
     * @param username
     * @param groupId
     */
    void unblockUserSendMsgToGroup(String username, String groupId);
}
