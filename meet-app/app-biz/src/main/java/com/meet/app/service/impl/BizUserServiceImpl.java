package com.meet.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meet.app.dto.AppUserDto;
import com.meet.app.entity.BizUser;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.entity.BizUserFriends;
import com.meet.app.entity.BizVersion;
import com.meet.app.mapper.BizUserBlacklistMapper;
import com.meet.app.mapper.BizUserFriendsMapper;
import com.meet.app.mapper.BizUserMapper;
import com.meet.app.service.BizUserService;
import com.youlai.common.result.Result;
import com.youlai.common.result.ResultCode;
import com.youlai.common.web.util.RequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/2/25 下午1:49
 * @Description
 */
@Service
@AllArgsConstructor
public class BizUserServiceImpl extends ServiceImpl<BizUserMapper, BizUser> implements BizUserService {
    private BizUserBlacklistMapper bizUserBlacklistMapper;
    private BizUserFriendsMapper bizUserFriendsMapper;
    private BizUserMapper bizUserMapper;

    @Override
    public Result getBizUser(Long id) {
        return Result.success(baseMapper.selectById(id));
    }
    public Result getBizUserByPhone(String phone){
        if(StringUtils.isEmpty(phone)){
            return Result.failed("手机号为空");
        }
        BizUser user = baseMapper.getBizUserByPhone(phone);
        if(user == null){
            return Result.failed(ResultCode.USER_NOT_EXIST);
        }
        AppUserDto dto = new AppUserDto();
        BeanUtil.copyProperties(user,dto);
        System.out.println("--------result----------"+new JSONObject(Result.success(dto)));
        return Result.success(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(BizUser bizUser) {
        if(StringUtils.isEmpty(bizUser.getPhone())){
            return Result.failed("手机号不能为空");
        }
        bizUser.setName(bizUser.getPhone());
        bizUser.setStatus(1);
        bizUser.setSmsStatus(1);
        bizUser.setVipType(1);
        bizUser.setCreateDate(new Date(System.currentTimeMillis()));
        bizUser.setIsAvailable(1);
        return Result.success(this.save(bizUser));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBlack(Long userId) {
        if(userId == null){
            return Result.failed("拉黑对象不能为空");
        }
        BizUser bizUser = bizUserMapper.selectById(userId);
        if(bizUser == null){
            return Result.failed("拉黑信息不存在");
        }
        BizUserBlacklist bizUserBlacklist = BizUserBlacklist.builder()
                .userId(RequestUtils.getUserId())
                .friendId(bizUser.getId())
                .friendPhone(bizUser.getPhone())
                .createBy(RequestUtils.getUserId())
                .createByName(RequestUtils.getUsername())
                .createDate(new Date(System.currentTimeMillis()))
                .userId(RequestUtils.getUserId())
                .isAvailable(1).build();
        return Result.judge(bizUserBlacklistMapper.insert(bizUserBlacklist));
    }

    @Override
    public Result deleteBlack(Long userId) {
        if(userId == null){
            return Result.failed("取消拉黑对象不能为空");
        }
        LambdaQueryWrapper<BizUserBlacklist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizUserBlacklist::getFriendId, userId);
        queryWrapper.eq(BizUserBlacklist::getUserId, RequestUtils.getUserId());
        queryWrapper.eq(BizUserBlacklist::getIsAvailable, 1);
        BizUserBlacklist bizUserBlacklistNew = bizUserBlacklistMapper.selectOne(queryWrapper);
        bizUserBlacklistNew.setUpdateBy(RequestUtils.getUserId());
        bizUserBlacklistNew.setUpdateByName(RequestUtils.getUsername());
        bizUserBlacklistNew.setUpdateDate(new Date(System.currentTimeMillis()));
        bizUserBlacklistNew.setIsAvailable(0);
        return Result.judge(bizUserBlacklistMapper.updateById(bizUserBlacklistNew));
    }

    @Override
    public Result addFriend(Long id) {
        if(id == null){
            return Result.failed("好友不能为空");
        }
        BizUser bizUser = bizUserMapper.selectById(id);
        if(bizUser == null){
            return Result.failed("好友信息不存在");
        }
        BizUserFriends bizUserFriends = BizUserFriends.builder().userId(RequestUtils.getUserId())
                .friendId(bizUser.getId())
                .friendPhone(bizUser.getPhone())
                .isAvailable(1)
                .createBy(bizUser.getId())
                .createByName(bizUser.getName())
                .createDate(new Date(System.currentTimeMillis()))
                .intimacy(1L)
                .build();
        return Result.judge(bizUserFriendsMapper.insert(bizUserFriends));
    }

    @Override
    public Result deleteFriend(Long id) {
        if(id == null){
            return Result.failed("好友不能为空");
        }
        LambdaQueryWrapper<BizUserFriends> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizUserFriends::getUserId, RequestUtils.getUserId());
        queryWrapper.eq(BizUserFriends::getFriendId,id);
        BizUserFriends bizUserFriends = bizUserFriendsMapper.selectOne(queryWrapper);
        if(bizUserFriends == null){
            return Result.failed("好友信息不存在");
        }
        bizUserFriends.setUpdateDate(new Date(System.currentTimeMillis()));
        bizUserFriends.setUpdateBy(RequestUtils.getUserId());
        bizUserFriends.setUpdateByName(RequestUtils.getUsername());
        bizUserFriends.setIsAvailable(0);//至于无效
        return Result.judge(bizUserFriendsMapper.updateById(bizUserFriends));
    }
}
