package com.meet.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meet.app.dto.AppUserDto;
import com.meet.app.entity.BizUser;
import com.meet.app.mapper.BizUserMapper;
import com.meet.app.service.BizUserService;
import com.youlai.common.result.Result;
import com.youlai.common.result.ResultCode;
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
}
