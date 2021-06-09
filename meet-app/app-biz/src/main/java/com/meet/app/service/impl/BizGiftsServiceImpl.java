package com.meet.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizGiftsMapper;
import com.meet.app.entity.BizGifts;
import com.meet.app.service.BizGiftsService;
import com.meet.app.vo.BizGiftsVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

import static com.youlai.common.result.ResultCode.DATA_IS_NULL;
import static com.youlai.common.result.ResultCode.PARAM_ERROR;

import java.util.Date;

/**
 * 礼物表管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizGiftsServiceImpl extends ServiceImpl<BizGiftsMapper, BizGifts> implements BizGiftsService {


    @Override
    public Result list(BizGiftsVo bizGiftsVo) {
        if (bizGiftsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizGiftsVo.getCurrentPage(), bizGiftsVo.getPageSize());
        LambdaQueryWrapper<BizGifts> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bizGiftsVo.getGiftName())){
            queryWrapper.like(BizGifts::getGiftName, bizGiftsVo.getGiftName());
        }
        if(StringUtils.isNotBlank(bizGiftsVo.getGiftPic())){
            queryWrapper.like(BizGifts::getGiftPic, bizGiftsVo.getGiftPic());
        }
        if(bizGiftsVo.getGiftOrderBy() != null){
            queryWrapper.eq(BizGifts::getGiftOrderBy, bizGiftsVo.getGiftOrderBy());
        }
        if(StringUtils.isNotBlank(bizGiftsVo.getRemark())){
            queryWrapper.like(BizGifts::getRemark, bizGiftsVo.getRemark());
        }
        IPage<BizGifts> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizGifts bizGifts) {
        if (bizGifts == null){
            return Result.failed(PARAM_ERROR);
        }
        bizGifts.setCreateBy(RequestUtils.getUserId());
        bizGifts.setCreateByName(RequestUtils.getUsername());
        bizGifts.setCreateDate(new Date(System.currentTimeMillis()));
        bizGifts.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizGifts));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizGifts bizGifts) {
        Long id = bizGifts.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizGifts bizGiftsNew = baseMapper.selectById(bizGifts.getId());
        if(bizGiftsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizGiftsNew.setUpdateBy(RequestUtils.getUserId());
        bizGiftsNew.setUpdateByName(RequestUtils.getUsername());
        bizGiftsNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizGifts));
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
        QueryWrapper<BizGifts> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
