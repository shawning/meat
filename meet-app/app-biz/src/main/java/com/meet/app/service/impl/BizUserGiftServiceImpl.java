package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserGiftMapper;
import com.meet.app.entity.BizUserGift;
import com.meet.app.service.BizUserGiftService;
import com.meet.app.vo.BizUserGiftVo;
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
 * 用户收到的礼物管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserGiftServiceImpl extends ServiceImpl<BizUserGiftMapper, BizUserGift> implements BizUserGiftService {



    @Override
    public Result list(BizUserGiftVo bizUserGiftVo) {
        if (bizUserGiftVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserGiftVo.getCurrentPage(), bizUserGiftVo.getPageSize());
        LambdaQueryWrapper<BizUserGift> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserGiftVo.getUserId() != null){
            queryWrapper.eq(BizUserGift::getUserId, bizUserGiftVo.getUserId());
        }
        if(bizUserGiftVo.getGiftId() != null){
            queryWrapper.eq(BizUserGift::getGiftId, bizUserGiftVo.getGiftId());
        }
        if(StringUtils.isNotBlank(bizUserGiftVo.getGiftName())){
            queryWrapper.like(BizUserGift::getGiftName, bizUserGiftVo.getGiftName());
        }
        if(StringUtils.isNotBlank(bizUserGiftVo.getRemark())){
            queryWrapper.like(BizUserGift::getRemark, bizUserGiftVo.getRemark());
        }
        IPage<BizUserGift> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserGift bizUserGift) {
        if (bizUserGift == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserGift.setCreateBy(RequestUtils.getUserId());
        bizUserGift.setCreateByName(RequestUtils.getUsername());
        bizUserGift.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserGift.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserGift));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserGift bizUserGift) {
        Long id = bizUserGift.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserGift bizUserGiftNew = baseMapper.selectById(bizUserGift.getId());
        if(bizUserGiftNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserGiftNew.setUpdateBy(RequestUtils.getUserId());
        bizUserGiftNew.setUpdateByName(RequestUtils.getUsername());
        bizUserGiftNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserGift));
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
        QueryWrapper<BizUserGift> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
