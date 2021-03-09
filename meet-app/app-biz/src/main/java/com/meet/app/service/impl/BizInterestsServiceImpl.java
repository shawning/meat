package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizInterestsMapper;
import com.meet.app.entity.BizInterests;
import com.meet.app.service.BizInterestsService;
import com.meet.app.vo.BizInterestsVo;
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
 * 用户兴趣爱好表管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizInterestsServiceImpl extends ServiceImpl<BizInterestsMapper, BizInterests> implements BizInterestsService {



    @Override
    public Result list(BizInterestsVo bizInterestsVo) {
        if (bizInterestsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizInterestsVo.getCurrentPage(), bizInterestsVo.getPageSize());
        LambdaQueryWrapper<BizInterests> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bizInterestsVo.getInterestName())){
            queryWrapper.like(BizInterests::getInterestName, bizInterestsVo.getInterestName());
        }
        if(StringUtils.isNotBlank(bizInterestsVo.getInterestColor())){
            queryWrapper.like(BizInterests::getInterestColor, bizInterestsVo.getInterestColor());
        }
        if(bizInterestsVo.getInterestOrderBy() != null){
            queryWrapper.eq(BizInterests::getInterestOrderBy, bizInterestsVo.getInterestOrderBy());
        }
        if(StringUtils.isNotBlank(bizInterestsVo.getRemark())){
            queryWrapper.like(BizInterests::getRemark, bizInterestsVo.getRemark());
        }
        IPage<BizInterests> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizInterests bizInterests) {
        if (bizInterests == null){
            return Result.failed(PARAM_ERROR);
        }
        bizInterests.setCreateBy(RequestUtils.getUserId());
        bizInterests.setCreateByName(RequestUtils.getUsername());
        bizInterests.setCreateDate(new Date(System.currentTimeMillis()));
        bizInterests.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizInterests));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizInterests bizInterests) {
        Long id = bizInterests.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizInterests bizInterestsNew = baseMapper.selectById(bizInterests.getId());
        if(bizInterestsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizInterestsNew.setUpdateBy(RequestUtils.getUserId());
        bizInterestsNew.setUpdateByName(RequestUtils.getUsername());
        bizInterestsNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizInterests));
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
        QueryWrapper<BizInterests> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
