package com.meet.app.service.im;

import com.easemob.im.server.api.room.update.UpdateRoomRequest;
import com.youlai.common.result.Result;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/24 下午12:36
 * @Description
 */
public interface IMRoomService {
    /**
     * 向聊天室增加成员
     * @param roomId
     * @param username
     * @return
     */
    Result addRoomMember(String roomId, String username);

    /**
     * 创建聊天室
     * @param name
     * @param description
     * @param owner
     * @param members
     * @param maxMembers
     * @return
     */
    String createRoom(String name, String description, String owner, List<String> members, int maxMembers);

    /**
     * 降级聊天室管理员至成员
     * @param roomId
     * @param username
     * @return
     */
    Result demoteRoomAdmin(String roomId, String username);

    /**
     * 降级超级管理员为普通用户
     * @param username
     * @return
     */
    Result demoteRoomSuperAdmin(String username);

    /**
     * 注销聊天室
     * @param roomId
     * @return
     */
    Result destroyRoom(String roomId);

    /**
     * 获取聊天室详情
     * @param id
     * @return
     */
    Result getRoom(String id);

    /**
     * 获取聊天室管理员
     * @param roomId
     * @return
     */
    Result listRoomAdminsAll(String roomId);

    /**
     * 分页获取聊天室成员列表
     * @param roomId
     * @param limit
     * @param cursor
     * @return
     */
    Result listRoomMembers(String roomId, int limit, String cursor);

    /**
     * 获取聊天室全部成员列表
     * @param roomId
     * @return
     */
    List<String> listRoomMembersAll(String roomId);

    /**
     * 分页获取聊天室列表
     * @param limit
     * @param cursor
     * @return
     */
    Result listRooms(int limit, String cursor);

    /**
     * 获取全部聊天室列表
     * @return
     */
    Result listRoomsAll();

    /**
     * 获取所有超级管理员列表
     * @return
     */
    Result listRoomSuperAdminsAll();

    /**
     * 获取用户加入的聊天室列表
     * @param username
     * @return
     */
    Result  listRoomsUserJoined(String username);

    /**
     * 升级聊天室成员至管理员
     * @param roomId
     * @param username
     * @return
     */
    Result promoteRoomAdmin(String roomId, String username);

    /**
     * 升级用户为超级管理员，只有超级管理员有权限创建聊天室
     * @param username
     * @return
     */
    Result promoteRoomSuperAdmin(String username);

    /**
     * 从聊天室移除成员
     * @param roomId
     * @param username
     * @return
     */
    Result removeRoomMember(String roomId, String username);

    /**
     * 修改聊天室
     * @param id
     * @param customizer
     * @return
     */
    Result updateRoom(String id, Consumer<UpdateRoomRequest> customizer);
}
