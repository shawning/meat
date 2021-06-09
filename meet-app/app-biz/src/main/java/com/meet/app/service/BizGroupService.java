package com.meet.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.easemob.im.server.api.group.settings.UpdateGroupRequest;
import com.meet.app.entity.BizRoom;
import com.meet.app.vo.*;
import com.youlai.common.result.Result;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * 群组管理
 * @author xiaoning
 */

public interface BizGroupService {
        /**
         * 创建公开群
         * @return
         */
        Result createPrivateGroup(BizRoom bizRoom);

        /**
         * 创建公开群
         * @return
         */
        Result createPublicGroup(BizRoom bizRoom);

        /**
         * 注销群
         * @param groupId
         */
        Result destroyGroup(String groupId);

        /**
         * 获取群详情
         * @param groupId
         * @return
         */
        Result getGroup(String groupId);

        /**
         * 获取群公告
         * @param groupId
         * @return
         */
        Result getGroupAnnouncement(String groupId);

        /**
         * 获取群全部成员
         * @param groupId
         * @return
         */
        Result listAllGroupMembers(String groupId);

        /**
         * 获取全部群列表
         * @return
         */
        Result listAllGroups(BizGroupVo bizGroupVo);

        /**
         * 获取群全部管理员
         * @param groupId
         * @return
         */
        Result listGroupAdmins(String groupId);

        /**
         * 获取用户所在的群组
         * @return
         */
        Result listGroupsUserJoined();

        /**
         * 降级群管理员为群成员
         * @param bizGroupAdminVo
         */
        Result removeGroupAdmin(BizGroupAdminVo bizGroupAdminVo);

        /**
         * 移除群成员
         * @param bizGroupMemberVo
         */
        Result removeGroupMember(BizGroupMemberVo bizGroupMemberVo);

        /**
         * 修改群详情
         * @param groupId
         * @param customizer
         */
        Result updateGroup(String groupId, Consumer<UpdateGroupRequest> customizer);

        /**
         * 更新群公告
         * @param bizGroupAnnouncementVo
         */
        Result updateGroupAnnouncement(BizGroupAnnouncementVo bizGroupAnnouncementVo);

        /**
         * 修改群主(移交群主）
         * @param bizGroupAdminVo
         */
        Result updateGroupOwner(BizGroupAdminVo bizGroupAdminVo);

        /**
         * 添加群成员
         * @param bizGroupMemberVo
         */
        Result addGroupMember(BizGroupMemberVo bizGroupMemberVo);

        /**
         * 升级群成员为群管理员
         * @param bizGroupAdminVo
         */
        Result addGroupAdmin(BizGroupAdminVo bizGroupAdminVo);

        /**
         * 阻止某用户进群
         * @param username
         * @param groupId
         */
        Result blockUserJoinGroup(String username, String groupId);

        /**
         * 添加群禁言
         * @param username
         * @param groupId
         * @param duration
         */
        Result blockUserSendMsgToGroup(String username, String groupId, Duration duration);

        /**
         * 获取群禁言列表
         * @param groupId
         * @return
         */
        Result getUsersBlockedSendMsgToGroup(String groupId);

        /**
         * 解除阻止进群
         * @param username
         * @param groupId
         */
        Result unblockUserJoinGroup(String username, String groupId);

        /**
         * 解除群禁言。
         * @param username
         * @param groupId
         */
        Result unblockUserSendMsgToGroup(String username, String groupId);

}
