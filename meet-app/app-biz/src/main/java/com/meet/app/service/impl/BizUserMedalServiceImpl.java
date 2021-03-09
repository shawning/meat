package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserMedalMapper;
import com.meet.app.entity.BizUserMedal;
import com.meet.app.service.BizUserMedalService;
import com.meet.app.vo.BizUserMedalVo;
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
 * 用户勋章管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserMedalServiceImpl extends ServiceImpl<BizUserMedalMapper, BizUserMedal> implements BizUserMedalService {



    @Override
    public Result list(BizUserMedalVo bizUserMedalVo) {
        if (bizUserMedalVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserMedalVo.getCurrentPage(), bizUserMedalVo.getPageSize());
        LambdaQueryWrapper<BizUserMedal> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserMedalVo.getUserId() != null){
            queryWrapper.eq(BizUserMedal::getUserId, bizUserMedalVo.getUserId());
        }
        if(bizUserMedalVo.getMedalId() != null){
            queryWrapper.eq(BizUserMedal::getMedalId, bizUserMedalVo.getMedalId());
        }
        if(StringUtils.isNotBlank(bizUserMedalVo.getMedalName())){
            queryWrapper.like(BizUserMedal::getMedalName, bizUserMedalVo.getMedalName());
        }
        if(StringUtils.isNotBlank(bizUserMedalVo.getRemark())){
            queryWrapper.like(BizUserMedal::getRemark, bizUserMedalVo.getRemark());
        }
        IPage<BizUserMedal> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserMedal bizUserMedal) {
        if (bizUserMedal == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserMedal.setCreateBy(RequestUtils.getUserId());
        bizUserMedal.setCreateByName(RequestUtils.getUsername());
        bizUserMedal.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserMedal.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserMedal));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserMedal bizUserMedal) {
        Long id = bizUserMedal.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserMedal bizUserMedalNew = baseMapper.selectById(bizUserMedal.getId());
        if(bizUserMedalNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserMedalNew.setUpdateBy(RequestUtils.getUserId());
        bizUserMedalNew.setUpdateByName(RequestUtils.getUsername());
        bizUserMedalNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserMedal));
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
        QueryWrapper<BizUserMedal> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
