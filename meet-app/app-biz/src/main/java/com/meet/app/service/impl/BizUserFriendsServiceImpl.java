package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserFriendsMapper;
import com.meet.app.entity.BizUserFriends;
import com.meet.app.service.BizUserFriendsService;
import com.meet.app.vo.BizUserFriendsVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

import static com.youlai.common.result.ResultCode.DATA_IS_NULL;
import static com.youlai.common.result.ResultCode.PARAM_ERROR;

import java.util.Date;

/**
 * 我的好友管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserFriendsServiceImpl extends ServiceImpl<BizUserFriendsMapper, BizUserFriends> implements BizUserFriendsService {



    @Override
    public Result list(BizUserFriendsVo bizUserFriendsVo) {
        if (bizUserFriendsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserFriendsVo.getCurrentPage(), bizUserFriendsVo.getPageSize());
        LambdaQueryWrapper<BizUserFriends> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserFriendsVo.getUserId() != null){
            queryWrapper.eq(BizUserFriends::getUserId, bizUserFriendsVo.getUserId());
        }
        if(bizUserFriendsVo.getFriendId() != null){
            queryWrapper.eq(BizUserFriends::getFriendId, bizUserFriendsVo.getFriendId());
        }
        if(StringUtils.isNotBlank(bizUserFriendsVo.getFriendPhone())){
            queryWrapper.like(BizUserFriends::getFriendPhone, bizUserFriendsVo.getFriendPhone());
        }
        if(bizUserFriendsVo.getIntimacy() != null){
            queryWrapper.eq(BizUserFriends::getIntimacy, bizUserFriendsVo.getIntimacy());
        }
        if(StringUtils.isNotBlank(bizUserFriendsVo.getNameRemark())){
            queryWrapper.like(BizUserFriends::getNameRemark, bizUserFriendsVo.getNameRemark());
        }
        IPage<BizUserFriends> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserFriends bizUserFriends) {
        if (bizUserFriends == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserFriends.setCreateBy(RequestUtils.getUserId());
        bizUserFriends.setCreateByName(RequestUtils.getUsername());
        bizUserFriends.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserFriends.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserFriends));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserFriends bizUserFriends) {
        Long id = bizUserFriends.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserFriends bizUserFriendsNew = baseMapper.selectById(bizUserFriends.getId());
        if(bizUserFriendsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserFriendsNew.setUpdateBy(RequestUtils.getUserId());
        bizUserFriendsNew.setUpdateByName(RequestUtils.getUsername());
        bizUserFriendsNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserFriends));
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
        QueryWrapper<BizUserFriends> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
