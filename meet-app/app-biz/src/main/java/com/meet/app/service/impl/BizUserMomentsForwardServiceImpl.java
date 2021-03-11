package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserMomentsForwardMapper;
import com.meet.app.entity.BizUserMomentsForward;
import com.meet.app.service.BizUserMomentsForwardService;
import com.meet.app.vo.BizUserMomentsForwardVo;
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
 * 用户朋友圈转发记录管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserMomentsForwardServiceImpl extends ServiceImpl<BizUserMomentsForwardMapper, BizUserMomentsForward> implements BizUserMomentsForwardService {



    @Override
    public Result list(BizUserMomentsForwardVo bizUserMomentsForwardVo) {
        if (bizUserMomentsForwardVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserMomentsForwardVo.getCurrentPage(), bizUserMomentsForwardVo.getPageSize());
        LambdaQueryWrapper<BizUserMomentsForward> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserMomentsForwardVo.getUserId() != null){
            queryWrapper.eq(BizUserMomentsForward::getUserId, bizUserMomentsForwardVo.getUserId());
        }
        if(StringUtils.isNotBlank(bizUserMomentsForwardVo.getUserPhone())){
            queryWrapper.like(BizUserMomentsForward::getUserPhone, bizUserMomentsForwardVo.getUserPhone());
        }
        if(StringUtils.isNotBlank(bizUserMomentsForwardVo.getUserName())){
            queryWrapper.like(BizUserMomentsForward::getUserName, bizUserMomentsForwardVo.getUserName());
        }
        if(bizUserMomentsForwardVo.getMomentId() != null){
            queryWrapper.eq(BizUserMomentsForward::getMomentId, bizUserMomentsForwardVo.getMomentId());
        }
        if(bizUserMomentsForwardVo.getForwardValue() != null){
            queryWrapper.eq(BizUserMomentsForward::getForwardValue, bizUserMomentsForwardVo.getForwardValue());
        }
        if(StringUtils.isNotBlank(bizUserMomentsForwardVo.getRemark())){
            queryWrapper.like(BizUserMomentsForward::getRemark, bizUserMomentsForwardVo.getRemark());
        }
        IPage<BizUserMomentsForward> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserMomentsForward bizUserMomentsForward) {
        if (bizUserMomentsForward == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserMomentsForward.setCreateBy(RequestUtils.getUserId());
        bizUserMomentsForward.setCreateByName(RequestUtils.getUsername());
        bizUserMomentsForward.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserMomentsForward.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserMomentsForward));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserMomentsForward bizUserMomentsForward) {
        Long id = bizUserMomentsForward.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserMomentsForward bizUserMomentsForwardNew = baseMapper.selectById(bizUserMomentsForward.getId());
        if(bizUserMomentsForwardNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserMomentsForwardNew.setUpdateBy(RequestUtils.getUserId());
        bizUserMomentsForwardNew.setUpdateByName(RequestUtils.getUsername());
        bizUserMomentsForwardNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserMomentsForward));
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
        QueryWrapper<BizUserMomentsForward> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
