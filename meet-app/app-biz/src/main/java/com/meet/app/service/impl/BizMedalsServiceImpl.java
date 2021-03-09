package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizMedalsMapper;
import com.meet.app.entity.BizMedals;
import com.meet.app.service.BizMedalsService;
import com.meet.app.vo.BizMedalsVo;
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
 * 勋章表管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizMedalsServiceImpl extends ServiceImpl<BizMedalsMapper, BizMedals> implements BizMedalsService {



    @Override
    public Result list(BizMedalsVo bizMedalsVo) {
        if (bizMedalsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizMedalsVo.getCurrentPage(), bizMedalsVo.getPageSize());
        LambdaQueryWrapper<BizMedals> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bizMedalsVo.getMedalName())){
            queryWrapper.like(BizMedals::getMedalName, bizMedalsVo.getMedalName());
        }
        if(StringUtils.isNotBlank(bizMedalsVo.getMedalPic())){
            queryWrapper.like(BizMedals::getMedalPic, bizMedalsVo.getMedalPic());
        }
        if(bizMedalsVo.getDedalOrderBy() != null){
            queryWrapper.eq(BizMedals::getDedalOrderBy, bizMedalsVo.getDedalOrderBy());
        }
        if(StringUtils.isNotBlank(bizMedalsVo.getRemark())){
            queryWrapper.like(BizMedals::getRemark, bizMedalsVo.getRemark());
        }
        IPage<BizMedals> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizMedals bizMedals) {
        if (bizMedals == null){
            return Result.failed(PARAM_ERROR);
        }
        bizMedals.setCreateBy(RequestUtils.getUserId());
        bizMedals.setCreateByName(RequestUtils.getUsername());
        bizMedals.setCreateDate(new Date(System.currentTimeMillis()));
        bizMedals.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizMedals));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizMedals bizMedals) {
        Long id = bizMedals.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizMedals bizMedalsNew = baseMapper.selectById(bizMedals.getId());
        if(bizMedalsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizMedalsNew.setUpdateBy(RequestUtils.getUserId());
        bizMedalsNew.setUpdateByName(RequestUtils.getUsername());
        bizMedalsNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizMedals));
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
        QueryWrapper<BizMedals> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
