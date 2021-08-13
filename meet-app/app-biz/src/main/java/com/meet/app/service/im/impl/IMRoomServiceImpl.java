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
    private EMService emService = IM.emService;
    @Override
    public Result addRoomMember(String roomId, String username) {
        return Result.success(IM.emService.room().addRoomMember(roomId, "meet"+username));
    }

    @Override
    public String createRoom(String name, String description, String owner, List<String> members, int maxMembers) {
        return IM.emService.room().createRoom(name, description, "meet"+owner, members, maxMembers).block();
    }

    @Override
    public Result demoteRoomAdmin(String roomId, String username) {
        return Result.success(IM.emService.room().demoteRoomAdmin(roomId, "meet"+username));
    }

    @Override
    public Result demoteRoomSuperAdmin(String username) {
        return Result.success(IM.emService.room().demoteRoomSuperAdmin("meet"+username));
    }

    @Override
    public Result destroyRoom(String roomId) {
        return Result.success(IM.emService.room().destroyRoom(roomId));
    }

    @Override
    public Result getRoom(String id) {
        return Result.success(IM.emService.room().getRoom(id));
    }

    @Override
    public Result listRoomAdminsAll(String roomId) {
        return Result.success(IM.emService.room().listRoomAdminsAll(roomId));
    }

    @Override
    public Result listRoomMembers(String roomId, int limit, String cursor) {
        return Result.success(IM.emService.room().listRoomMembers(roomId, limit, cursor));
    }

    @Override
    public List<String> listRoomMembersAll(String roomId) {
        return IM.emService.room().listRoomAdminsAll(roomId).collectList().block();
    }

    @Override
    public Result listRooms(int limit, String cursor) {
        return Result.success(IM.emService.room().listRooms(limit, cursor));
    }

    @Override
    public Result listRoomsAll() {
        return Result.success(IM.emService.room().listRoomsAll());
    }

    @Override
    public Result listRoomSuperAdminsAll() {
        return Result.success(IM.emService.room().listRoomSuperAdminsAll());
    }

    @Override
    public Result listRoomsUserJoined(String username) {
        return Result.success(IM.emService.room().listRoomsUserJoined("meet"+username));
    }

    @Override
    public Result promoteRoomAdmin(String roomId, String username) {
        return Result.success(IM.emService.room().promoteRoomAdmin(roomId, username));
    }

    @Override
    public Result promoteRoomSuperAdmin(String username) {
        return Result.success(IM.emService.room().promoteRoomSuperAdmin(username));
    }

    @Override
    public Result removeRoomMember(String roomId, String username) {
        return Result.success(IM.emService.room().removeRoomMember(roomId, username));
    }

    @Override
    public Result updateRoom(String id, Consumer<UpdateRoomRequest> customizer) {
        return Result.success(IM.emService.room().updateRoom(id, customizer));
    }
}
