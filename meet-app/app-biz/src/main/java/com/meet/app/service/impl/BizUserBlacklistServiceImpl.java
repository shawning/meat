package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserBlacklistMapper;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.service.BizUserBlacklistService;
import com.meet.app.vo.BizUserBlacklistVo;
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
 * 我的黑名单管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserBlacklistServiceImpl extends ServiceImpl<BizUserBlacklistMapper, BizUserBlacklist> implements BizUserBlacklistService {



    @Override
    public Result list(BizUserBlacklistVo bizUserBlacklistVo) {
        if (bizUserBlacklistVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserBlacklistVo.getCurrentPage(), bizUserBlacklistVo.getPageSize());
        LambdaQueryWrapper<BizUserBlacklist> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserBlacklistVo.getUserId() != null){
            queryWrapper.eq(BizUserBlacklist::getUserId, bizUserBlacklistVo.getUserId());
        }
        if(bizUserBlacklistVo.getFriendId() != null){
            queryWrapper.eq(BizUserBlacklist::getFriendId, bizUserBlacklistVo.getFriendId());
        }
        if(StringUtils.isNotBlank(bizUserBlacklistVo.getFriendPhone())){
            queryWrapper.like(BizUserBlacklist::getFriendPhone, bizUserBlacklistVo.getFriendPhone());
        }
        if(StringUtils.isNotBlank(bizUserBlacklistVo.getNameRemark())){
            queryWrapper.like(BizUserBlacklist::getNameRemark, bizUserBlacklistVo.getNameRemark());
        }
        IPage<BizUserBlacklist> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserBlacklist bizUserBlacklist) {
        if (bizUserBlacklist == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserBlacklist.setCreateBy(RequestUtils.getUserId());
        bizUserBlacklist.setCreateByName(RequestUtils.getUsername());
        bizUserBlacklist.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserBlacklist.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserBlacklist));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserBlacklist bizUserBlacklist) {
        Long id = bizUserBlacklist.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserBlacklist bizUserBlacklistNew = baseMapper.selectById(bizUserBlacklist.getId());
        if(bizUserBlacklistNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserBlacklistNew.setUpdateBy(RequestUtils.getUserId());
        bizUserBlacklistNew.setUpdateByName(RequestUtils.getUsername());
        bizUserBlacklistNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserBlacklist));
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
        QueryWrapper<BizUserBlacklist> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
