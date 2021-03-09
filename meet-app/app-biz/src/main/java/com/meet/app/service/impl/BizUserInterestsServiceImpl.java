package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserInterestsMapper;
import com.meet.app.entity.BizUserInterests;
import com.meet.app.service.BizUserInterestsService;
import com.meet.app.vo.BizUserInterestsVo;
import com.youlai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
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
 * 用户兴趣爱好管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserInterestsServiceImpl extends ServiceImpl<BizUserInterestsMapper, BizUserInterests> implements BizUserInterestsService {


//    @Autowired
//    private RedisService redisService;

    @Override
    public Result list(BizUserInterestsVo bizUserInterestsVo) {
        if (bizUserInterestsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserInterestsVo.getCurrentPage(), bizUserInterestsVo.getPageSize());
        LambdaQueryWrapper<BizUserInterests> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserInterestsVo.getUserId() != null){
            queryWrapper.eq(BizUserInterests::getUserId, bizUserInterestsVo.getUserId());
        }
        if(bizUserInterestsVo.getInterestId() != null){
            queryWrapper.eq(BizUserInterests::getInterestId, bizUserInterestsVo.getInterestId());
        }
        if(StringUtils.isNotBlank(bizUserInterestsVo.getInterestName())){
            queryWrapper.like(BizUserInterests::getInterestName, bizUserInterestsVo.getInterestName());
        }
        if(StringUtils.isNotBlank(bizUserInterestsVo.getRemark())){
            queryWrapper.like(BizUserInterests::getRemark, bizUserInterestsVo.getRemark());
        }
        IPage<BizUserInterests> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserInterests bizUserInterests) {
        if (bizUserInterests == null){
            return Result.failed(PARAM_ERROR);
        }
//        bizUserInterests.setCreateBy(redisService.getUserId());
//        bizUserInterests.setCreateByName(redisService.getUserName());
        bizUserInterests.setCreateDate(new Date());
        bizUserInterests.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserInterests));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserInterests bizUserInterests) {
        Long id = bizUserInterests.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserInterests bizUserInterestsNew = baseMapper.selectById(bizUserInterests.getId());
        if(bizUserInterestsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
//        bizUserInterestsNew.setUpdateBy(redisService.getUserId());
//        bizUserInterestsNew.setUpdateByName(redisService.getUserName());
        bizUserInterestsNew.setUpdateDate(new Date());
        return Result.judge(baseMapper.updateById(bizUserInterests));
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
        QueryWrapper<BizUserInterests> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
