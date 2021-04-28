package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.easemob.im.server.model.EMUser;
import com.meet.app.mapper.BizTagsMapper;
import com.meet.app.entity.BizTags;
import com.meet.app.service.BizTagsService;
import com.meet.app.service.im.IM;
import com.meet.app.vo.BizTagsVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static com.youlai.common.result.ResultCode.DATA_IS_NULL;
import static com.youlai.common.result.ResultCode.PARAM_ERROR;

import java.util.Date;

/**
 * 用户标签表管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizTagsServiceImpl extends ServiceImpl<BizTagsMapper, BizTags> implements BizTagsService {



    @Override
    public Result list(BizTagsVo bizTagsVo) {
        if (bizTagsVo == null) {
            return Result.success(baseMapper.selectPage(new Page<>(), Wrappers.emptyWrapper()));
        }
        Page page = new Page<>(bizTagsVo.getCurrentPage(), bizTagsVo.getPageSize());
        LambdaQueryWrapper<BizTags> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bizTagsVo.getTagName())){
            queryWrapper.like(BizTags::getTagName, bizTagsVo.getTagName());
        }
        if(StringUtils.isNotBlank(bizTagsVo.getTagColor())){
            queryWrapper.like(BizTags::getTagColor, bizTagsVo.getTagColor());
        }
        if(bizTagsVo.getTagOrderBy() != null){
            queryWrapper.eq(BizTags::getTagOrderBy, bizTagsVo.getTagOrderBy());
        }
        if(StringUtils.isNotBlank(bizTagsVo.getRemark())){
            queryWrapper.like(BizTags::getRemark, bizTagsVo.getRemark());
        }
        IPage<BizTags> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(@NonNull BizTags bizTags) {
        if (bizTags == null){
            return Result.failed(PARAM_ERROR);
        }
        bizTags.setCreateBy(RequestUtils.getUserId());
        bizTags.setCreateByName(RequestUtils.getUsername());
        bizTags.setCreateDate(new Date(System.currentTimeMillis()));
        bizTags.setIsAvailable(1);
        return Result.judge(baseMapper.insert(bizTags));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result update(BizTags bizTags) {
        Long id = bizTags.getId();
        if (id == null) {
            return Result.failed(PARAM_ERROR);
        }
        BizTags bizTagsNew = baseMapper.selectById(bizTags.getId());
        if(bizTagsNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizTagsNew.setUpdateBy(RequestUtils.getUserId());
        bizTagsNew.setUpdateByName(RequestUtils.getUsername());
        bizTagsNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizTags));
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
//        Void a1 = IM.server().user().create("xiao","12345").block();
        EMUser a = IM.server().user().get("xiao").block();
    String name = a.getUsername();
        QueryWrapper<BizTags> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }
}
