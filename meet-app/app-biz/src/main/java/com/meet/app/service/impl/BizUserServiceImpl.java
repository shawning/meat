package com.meet.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meet.app.dto.AppUserDto;
import com.meet.app.entity.BizUser;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.entity.BizVersion;
import com.meet.app.mapper.BizUserBlacklistMapper;
import com.meet.app.mapper.BizUserMapper;
import com.meet.app.service.BizUserService;
import com.youlai.common.result.Result;
import com.youlai.common.result.ResultCode;
import com.youlai.common.web.util.RequestUtils;
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
public class BizUserServiceImpl extends ServiceImpl<BizUserMapper, BizUser> implements BizUserService {
    @Autowired
    private BizUserBlacklistMapper bizUserBlacklistMapper;

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
    public Result addBlack(BizUserBlacklist bizUserBlacklist) {
        if(bizUserBlacklist == null || bizUserBlacklist.getFriendId() == null){
            return Result.failed("拉黑对象不能为空");
        }
        bizUserBlacklist.setCreateBy(RequestUtils.getUserId());
        bizUserBlacklist.setCreateByName(RequestUtils.getUsername());
        bizUserBlacklist.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserBlacklist.setUserId(RequestUtils.getUserId());
        bizUserBlacklist.setIsAvailable(1);
        return Result.judge(bizUserBlacklistMapper.insert(bizUserBlacklist));
    }

    @Override
    public Result deleteBlack(BizUserBlacklist bizUserBlacklist) {
        if(bizUserBlacklist == null || bizUserBlacklist.getFriendId() == null){
            return Result.failed("取消拉黑对象不能为空");
        }
        LambdaQueryWrapper<BizUserBlacklist> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserBlacklist.getFriendId() != null){
            queryWrapper.eq(BizUserBlacklist::getFriendId, bizUserBlacklist.getFriendId());
        }
        queryWrapper.eq(BizUserBlacklist::getUserId, RequestUtils.getUserId());
        queryWrapper.eq(BizUserBlacklist::getIsAvailable, 1);

        BizUserBlacklist bizUserBlacklistNew = bizUserBlacklistMapper.selectOne(queryWrapper);
        bizUserBlacklistNew.setUpdateBy(RequestUtils.getUserId());
        bizUserBlacklistNew.setUpdateByName(RequestUtils.getUsername());
        bizUserBlacklistNew.setUpdateDate(new Date(System.currentTimeMillis()));
        bizUserBlacklist.setIsAvailable(0);
        return Result.judge(bizUserBlacklistMapper.updateById(bizUserBlacklist));
    }
}
