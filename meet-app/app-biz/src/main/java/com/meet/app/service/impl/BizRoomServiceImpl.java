package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easemob.im.server.api.room.update.UpdateRoomRequest;
import com.meet.app.dto.LivePushPullUrlDto;
import com.meet.app.mapper.BizRoomMapper;
import com.meet.app.entity.BizRoom;
import com.meet.app.service.BizRoomService;
import com.meet.app.service.im.IMRoomService;
import com.meet.app.service.im.ImChatService;
import com.meet.app.service.live.LiveService;
import com.meet.app.vo.BizRoomVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.youlai.common.result.ResultCode.DATA_IS_NULL;
import static com.youlai.common.result.ResultCode.PARAM_ERROR;

import java.util.Date;
import java.util.function.Consumer;

/**
 * 直播房间号管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizRoomServiceImpl extends ServiceImpl<BizRoomMapper, BizRoom> implements BizRoomService {

    @Autowired
    private IMRoomService imRoomService;
    @Autowired
    private LiveService liveService;

    @Override
    public Result list(BizRoomVo bizRoomVo) {
        if (bizRoomVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizRoomVo.getCurrentPage(), bizRoomVo.getPageSize());
        LambdaQueryWrapper<BizRoom> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bizRoomVo.getRoomName())){
            queryWrapper.like(BizRoom::getRoomName, bizRoomVo.getRoomName());
        }
        if(bizRoomVo.getMaxMembers() != null){
            queryWrapper.eq(BizRoom::getMaxMembers, bizRoomVo.getMaxMembers());
        }
        if(StringUtils.isNotBlank(bizRoomVo.getRemark())){
            queryWrapper.like(BizRoom::getRemark, bizRoomVo.getRemark());
        }
        if(bizRoomVo.getOwnerId() != null){
            queryWrapper.eq(BizRoom::getOwnerId, bizRoomVo.getOwnerId());
        }
        if(StringUtils.isNotBlank(bizRoomVo.getOwnerName())){
            queryWrapper.like(BizRoom::getOwnerName, bizRoomVo.getOwnerName());
        }
         queryWrapper.like(BizRoom::getOnline, 1);
        IPage<BizRoom> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizRoom bizRoom) {
        if (bizRoom == null){
            return Result.failed(PARAM_ERROR);
        }
        bizRoom.setOwnerId(RequestUtils.getUserId());
        bizRoom.setOwnerName(RequestUtils.getUsername());
        bizRoom.setRoomName(RequestUtils.getUsername()+"的群聊");
        bizRoom.setCreateBy(RequestUtils.getUserId());
        bizRoom.setCreateByName(RequestUtils.getUsername());
        bizRoom.setRoomType(2);//聊天室
        bizRoom.setIsAvailable(1);
        List<String> members = new ArrayList<String>();
        members.add(bizRoom.getOwnerId()+"");
        String roomId = imRoomService.createRoom(bizRoom.getRoomName(), bizRoom.getRoomName(), bizRoom.getOwnerId().toString(),members,1000);
        bizRoom.setRoomId(roomId);
        baseMapper.insert(bizRoom);
        return Result.success(bizRoom);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizRoom bizRoom) {
        Long id = bizRoom.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        if (StrUtil.isEmpty(bizRoom.getRoomName())) {
            return Result.failed("群聊名称不能为空");
        }
        BizRoom bizRoomNew = baseMapper.selectById(bizRoom.getId());
        imRoomService.updateRoom(bizRoom.getRoomId(),request -> request.withName(bizRoom.getRoomName()).withDescription(bizRoom.getRemark()));
        if(bizRoomNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        return Result.judge(baseMapper.updateById(bizRoom));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result delete(@NonNull String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        return Result.judge(baseMapper.deleteBatchIds(idList));
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result delete(@NonNull Long id) {
        return Result.judge(baseMapper.deleteById(id));
    }

    @Override
    public Result detail(@NonNull Long id){
        QueryWrapper<BizRoom> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }

    @Override
    public Result start() {
//        long userId = 105;
//        String username = "xiao";
        if (RequestUtils.getUserId() == null){
            return Result.failed(PARAM_ERROR);
        }
        LambdaQueryWrapper<BizRoom> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizRoom::getOwnerId, RequestUtils.getUserId());
//        queryWrapper.eq(BizRoom::getOwnerId, userId);
        BizRoom bizRoom = this.getOne(queryWrapper);
        /**
         * 开始直播，如果没有直播房间，则创建一个
         */
        if(bizRoom == null){
            bizRoom = new BizRoom();
            LivePushPullUrlDto dto = liveService.createLivePushUrl(RequestUtils.getUserId().toString());
//            LivePushPullUrlDto dto = liveService.createLivePushUrl(userId+"");
            bizRoom.setPushUrl(dto.getPushUrl());
            bizRoom.setPullUrl(dto.getPullUrl());
//            bizRoom.setOwnerId(userId);
//            bizRoom.setOwnerName(username);
//            bizRoom.setRoomName(username+"的直播间");
//            bizRoom.setCreateBy(userId);
//            bizRoom.setCreateByName(username);
            bizRoom.setOwnerId(RequestUtils.getUserId());
            bizRoom.setOwnerName(RequestUtils.getUsername());
            bizRoom.setRoomName(RequestUtils.getUsername()+"的直播间");
            bizRoom.setCreateBy(RequestUtils.getUserId());
            bizRoom.setCreateByName(RequestUtils.getUsername());
            bizRoom.setIsAvailable(1);
            bizRoom.setOnline(1);//直播中
            List<String> members = new ArrayList<String>();
            members.add(bizRoom.getOwnerId()+"");
            /*String roomId = imRoomService.createRoom(bizRoom.getRoomName(), bizRoom.getRoomName(), bizRoom.getOwnerId().toString(),members,1000);
            if(StrUtil.isNotEmpty(roomId)){
                bizRoom.setRoomId(roomId);
            }*/
            baseMapper.insert(bizRoom);
        }else{
            bizRoom.setOnline(1);//直播中
            baseMapper.updateById(bizRoom);
        }
        List<String> members = new ArrayList<String>();
        return Result.success(bizRoom);
    }

    @Override
    public Result end(String roomId) {
        if(StrUtil.isEmpty(roomId)){
            return Result.failed("直播间不存在");
        }
        BizRoom bizRoom = this.getById(roomId);
        List<String> members = imRoomService.listRoomMembersAll(roomId);
        for(String username : members){
            if(! username.contentEquals(bizRoom.getOwnerId().toString())) {//创建人不移除
                imRoomService.removeRoomMember(roomId, username);
            }
        }
        bizRoom.setOnline(0);//下线
        return this.update(bizRoom);
    }

    @Override
    public Result join(String roomId) {
        if(StrUtil.isEmpty(roomId)){
            return Result.failed("直播间不存在");
        }
        BizRoom bizRoom = this.getById(roomId);
        if(bizRoom == null){
            return Result.failed("直播间无效");
        }
        return imRoomService.addRoomMember(roomId,RequestUtils.getUserId().toString());
    }

    @Override
    public Result leave(String roomId) {
        if(StrUtil.isEmpty(roomId)){
            return Result.failed("直播间不存在");
        }
        BizRoom bizRoom = this.getById(roomId);
        if(bizRoom == null){
            return Result.failed("直播间无效");
        }
        return imRoomService.removeRoomMember(roomId,RequestUtils.getUserId().toString());
    }
}
