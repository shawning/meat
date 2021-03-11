package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserMomentsPraisedMapper;
import com.meet.app.entity.BizUserMomentsPraised;
import com.meet.app.service.BizUserMomentsPraisedService;
import com.meet.app.vo.BizUserMomentsPraisedVo;
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
 * 用户朋友圈点赞记录管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserMomentsPraisedServiceImpl extends ServiceImpl<BizUserMomentsPraisedMapper, BizUserMomentsPraised> implements BizUserMomentsPraisedService {



    @Override
    public Result list(BizUserMomentsPraisedVo bizUserMomentsPraisedVo) {
        if (bizUserMomentsPraisedVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserMomentsPraisedVo.getCurrentPage(), bizUserMomentsPraisedVo.getPageSize());
        LambdaQueryWrapper<BizUserMomentsPraised> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserMomentsPraisedVo.getUserId() != null){
            queryWrapper.eq(BizUserMomentsPraised::getUserId, bizUserMomentsPraisedVo.getUserId());
        }
        if(StringUtils.isNotBlank(bizUserMomentsPraisedVo.getUserPhone())){
            queryWrapper.like(BizUserMomentsPraised::getUserPhone, bizUserMomentsPraisedVo.getUserPhone());
        }
        if(StringUtils.isNotBlank(bizUserMomentsPraisedVo.getUserName())){
            queryWrapper.like(BizUserMomentsPraised::getUserName, bizUserMomentsPraisedVo.getUserName());
        }
        if(bizUserMomentsPraisedVo.getMomentId() != null){
            queryWrapper.eq(BizUserMomentsPraised::getMomentId, bizUserMomentsPraisedVo.getMomentId());
        }
        if(bizUserMomentsPraisedVo.getPraisedValue() != null){
            queryWrapper.eq(BizUserMomentsPraised::getPraisedValue, bizUserMomentsPraisedVo.getPraisedValue());
        }
        if(StringUtils.isNotBlank(bizUserMomentsPraisedVo.getRemark())){
            queryWrapper.like(BizUserMomentsPraised::getRemark, bizUserMomentsPraisedVo.getRemark());
        }
        IPage<BizUserMomentsPraised> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserMomentsPraised bizUserMomentsPraised) {
        if (bizUserMomentsPraised == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserMomentsPraised.setCreateBy(RequestUtils.getUserId());
        bizUserMomentsPraised.setCreateByName(RequestUtils.getUsername());
        bizUserMomentsPraised.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserMomentsPraised.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserMomentsPraised));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserMomentsPraised bizUserMomentsPraised) {
        Long id = bizUserMomentsPraised.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserMomentsPraised bizUserMomentsPraisedNew = baseMapper.selectById(bizUserMomentsPraised.getId());
        if(bizUserMomentsPraisedNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserMomentsPraisedNew.setUpdateBy(RequestUtils.getUserId());
        bizUserMomentsPraisedNew.setUpdateByName(RequestUtils.getUsername());
        bizUserMomentsPraisedNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserMomentsPraised));
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
        QueryWrapper<BizUserMomentsPraised> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
