package com.meet.app.service.im.impl;

import com.easemob.im.server.EMService;
import com.easemob.im.server.api.room.update.UpdateRoomRequest;
import com.meet.app.service.im.IM;
import com.meet.app.service.im.IMRoomService;
import com.youlai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/24 下午10:18
 * @Description
 */
@Slf4j
@Service
public class IMRoomServiceImpl implements IMRoomService {
    private EMService emService = IM.server();
    @Override
    public Result addRoomMember(String roomId, String username) {
        return Result.success(emService.room().addRoomMember(roomId, username));
    }

    @Override
    public String createRoom(String name, String description, String owner, List<String> members, int maxMembers) {
        return emService.room().createRoom(name, description, owner, members, maxMembers).block();
    }

    @Override
    public Result demoteRoomAdmin(String roomId, String username) {
        return Result.success(emService.room().demoteRoomAdmin(roomId, username));
    }

    @Override
    public Result demoteRoomSuperAdmin(String username) {
        return Result.success(emService.room().demoteRoomSuperAdmin(username));
    }

    @Override
    public Result destroyRoom(String roomId) {
        return Result.success(emService.room().destroyRoom(roomId));
    }

    @Override
    public Result getRoom(String id) {
        return Result.success(emService.room().getRoom(id));
    }

    @Override
    public Result listRoomAdminsAll(String roomId) {
        return Result.success(emService.room().listRoomAdminsAll(roomId));
    }

    @Override
    public Result listRoomMembers(String roomId, int limit, String cursor) {
        return Result.success(emService.room().listRoomMembers(roomId, limit, cursor));
    }

    @Override
    public List<String> listRoomMembersAll(String roomId) {
        return emService.room().listRoomAdminsAll(roomId).collectList().block();
    }

    @Override
    public Result listRooms(int limit, String cursor) {
        return Result.success(emService.room().listRooms(limit, cursor));
    }

    @Override
    public Result listRoomsAll() {
        return Result.success(emService.room().listRoomsAll());
    }

    @Override
    public Result listRoomSuperAdminsAll() {
        return Result.success(emService.room().listRoomSuperAdminsAll());
    }

    @Override
    public Result listRoomsUserJoined(String username) {
        return Result.success(emService.room().listRoomsUserJoined(username));
    }

    @Override
    public Result promoteRoomAdmin(String roomId, String username) {
        return Result.success(emService.room().promoteRoomAdmin(roomId, username));
    }

    @Override
    public Result promoteRoomSuperAdmin(String username) {
        return Result.success(emService.room().promoteRoomSuperAdmin(username));
    }

    @Override
    public Result removeRoomMember(String roomId, String username) {
        return Result.success(emService.room().removeRoomMember(roomId, username));
    }

    @Override
    public Result updateRoom(String id, Consumer<UpdateRoomRequest> customizer) {
        return Result.success(emService.room().updateRoom(id, customizer));
    }
}
