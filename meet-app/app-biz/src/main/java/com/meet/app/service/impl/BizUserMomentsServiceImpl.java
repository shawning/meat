package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserMomentsMapper;
import com.meet.app.entity.BizUserMoments;
import com.meet.app.service.BizUserMomentsService;
import com.meet.app.vo.BizUserMomentsVo;
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
 * 用户朋友圈动态管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserMomentsServiceImpl extends ServiceImpl<BizUserMomentsMapper, BizUserMoments> implements BizUserMomentsService {



    @Override
    public Result list(BizUserMomentsVo bizUserMomentsVo) {
        if (bizUserMomentsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserMomentsVo.getCurrentPage(), bizUserMomentsVo.getPageSize());
        LambdaQueryWrapper<BizUserMoments> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserMomentsVo.getUserId() != null){
            queryWrapper.eq(BizUserMoments::getUserId, bizUserMomentsVo.getUserId());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getUserPhone())){
            queryWrapper.like(BizUserMoments::getUserPhone, bizUserMomentsVo.getUserPhone());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getUserName())){
            queryWrapper.like(BizUserMoments::getUserName, bizUserMomentsVo.getUserName());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicOne())){
            queryWrapper.like(BizUserMoments::getPicOne, bizUserMomentsVo.getPicOne());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicTwo())){
            queryWrapper.like(BizUserMoments::getPicTwo, bizUserMomentsVo.getPicTwo());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicThree())){
            queryWrapper.like(BizUserMoments::getPicThree, bizUserMomentsVo.getPicThree());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicFour())){
            queryWrapper.like(BizUserMoments::getPicFour, bizUserMomentsVo.getPicFour());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicFive())){
            queryWrapper.like(BizUserMoments::getPicFive, bizUserMomentsVo.getPicFive());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicSix())){
            queryWrapper.like(BizUserMoments::getPicSix, bizUserMomentsVo.getPicSix());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicSeven())){
            queryWrapper.like(BizUserMoments::getPicSeven, bizUserMomentsVo.getPicSeven());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicEight())){
            queryWrapper.like(BizUserMoments::getPicEight, bizUserMomentsVo.getPicEight());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getPicNine())){
            queryWrapper.like(BizUserMoments::getPicNine, bizUserMomentsVo.getPicNine());
        }
        if(bizUserMomentsVo.getPraisedValue() != null){
            queryWrapper.eq(BizUserMoments::getPraisedValue, bizUserMomentsVo.getPraisedValue());
        }
        if(bizUserMomentsVo.getCommentValue() != null){
            queryWrapper.eq(BizUserMoments::getCommentValue, bizUserMomentsVo.getCommentValue());
        }
        if(bizUserMomentsVo.getForwardValue() != null){
            queryWrapper.eq(BizUserMoments::getForwardValue, bizUserMomentsVo.getForwardValue());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getAddress())){
            queryWrapper.like(BizUserMoments::getAddress, bizUserMomentsVo.getAddress());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getAddressGps())){
            queryWrapper.like(BizUserMoments::getAddressGps, bizUserMomentsVo.getAddressGps());
        }
        if(StringUtils.isNotBlank(bizUserMomentsVo.getRemark())){
            queryWrapper.like(BizUserMoments::getRemark, bizUserMomentsVo.getRemark());
        }
        IPage<BizUserMoments> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserMoments bizUserMoments) {
        if (bizUserMoments == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserMoments.setCreateBy(RequestUtils.getUserId());
        bizUserMoments.setCreateByName(RequestUtils.getUsername());
        bizUserMoments.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserMoments.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserMoments));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserMoments bizUserMoments) {
        Long id = bizUserMoments.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserMoments bizUserMomentsNew = baseMapper.selectById(bizUserMoments.getId());
        if(bizUserMomentsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserMomentsNew.setUpdateBy(RequestUtils.getUserId());
        bizUserMomentsNew.setUpdateByName(RequestUtils.getUsername());
        bizUserMomentsNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserMoments));
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
        QueryWrapper<BizUserMoments> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
