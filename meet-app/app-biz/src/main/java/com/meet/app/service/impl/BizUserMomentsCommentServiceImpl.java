package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.meet.app.mapper.BizUserMomentsCommentMapper;
import com.meet.app.entity.BizUserMomentsComment;
import com.meet.app.service.BizUserMomentsCommentService;
import com.meet.app.vo.BizUserMomentsCommentVo;
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
 * 用户朋友圈评论记录管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizUserMomentsCommentServiceImpl extends ServiceImpl<BizUserMomentsCommentMapper, BizUserMomentsComment> implements BizUserMomentsCommentService {



    @Override
    public Result list(BizUserMomentsCommentVo bizUserMomentsCommentVo) {
        if (bizUserMomentsCommentVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizUserMomentsCommentVo.getCurrentPage(), bizUserMomentsCommentVo.getPageSize());
        LambdaQueryWrapper<BizUserMomentsComment> queryWrapper = new LambdaQueryWrapper<>();
        if(bizUserMomentsCommentVo.getUserId() != null){
            queryWrapper.eq(BizUserMomentsComment::getUserId, bizUserMomentsCommentVo.getUserId());
        }
        if(StringUtils.isNotBlank(bizUserMomentsCommentVo.getUserPhone())){
            queryWrapper.like(BizUserMomentsComment::getUserPhone, bizUserMomentsCommentVo.getUserPhone());
        }
        if(StringUtils.isNotBlank(bizUserMomentsCommentVo.getUserName())){
            queryWrapper.like(BizUserMomentsComment::getUserName, bizUserMomentsCommentVo.getUserName());
        }
        if(bizUserMomentsCommentVo.getMomentId() != null){
            queryWrapper.eq(BizUserMomentsComment::getMomentId, bizUserMomentsCommentVo.getMomentId());
        }
        if(bizUserMomentsCommentVo.getCommentValue() != null){
            queryWrapper.eq(BizUserMomentsComment::getCommentValue, bizUserMomentsCommentVo.getCommentValue());
        }
        if(StringUtils.isNotBlank(bizUserMomentsCommentVo.getRemark())){
            queryWrapper.like(BizUserMomentsComment::getRemark, bizUserMomentsCommentVo.getRemark());
        }
        IPage<BizUserMomentsComment> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizUserMomentsComment bizUserMomentsComment) {
        if (bizUserMomentsComment == null){
            return Result.failed(PARAM_ERROR);
        }
        bizUserMomentsComment.setCreateBy(RequestUtils.getUserId());
        bizUserMomentsComment.setCreateByName(RequestUtils.getUsername());
        bizUserMomentsComment.setCreateDate(new Date(System.currentTimeMillis()));
        bizUserMomentsComment.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizUserMomentsComment));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizUserMomentsComment bizUserMomentsComment) {
        Long id = bizUserMomentsComment.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizUserMomentsComment bizUserMomentsCommentNew = baseMapper.selectById(bizUserMomentsComment.getId());
        if(bizUserMomentsCommentNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserMomentsCommentNew.setUpdateBy(RequestUtils.getUserId());
        bizUserMomentsCommentNew.setUpdateByName(RequestUtils.getUsername());
        bizUserMomentsCommentNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserMomentsComment));
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
        QueryWrapper<BizUserMomentsComment> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
