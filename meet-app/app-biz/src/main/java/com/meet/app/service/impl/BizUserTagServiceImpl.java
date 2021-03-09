package com.meet.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meet.app.entity.BizUserTag;
import com.meet.app.mapper.BizUserTagMapper;
import com.meet.app.service.BizUserTagService;
import com.meet.app.vo.BizUserTagVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.youlai.common.result.ResultCode.DATA_IS_NULL;
import static com.youlai.common.result.ResultCode.PARAM_ERROR;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/3/8 下午1:14
 * @Description
 */
@Service
public class BizUserTagServiceImpl extends ServiceImpl<BizUserTagMapper, BizUserTag> implements BizUserTagService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result add(BizUserTag bizUserTag) {
        if(bizUserTag == null){
            return Result.failed(PARAM_ERROR);
        }
        return Result.judge(baseMapper.insert(bizUserTag));
    }

    @Override
    public Result update(Long id, BizUserTag bizUserTag) {
        if(bizUserTag == null){
            return Result.failed(PARAM_ERROR);
        }
        BizUserTag bizUserTagNew = baseMapper.selectById(bizUserTag.getId());
        if(bizUserTagNew == null){
            return Result.failed(DATA_IS_NULL);
        }
        bizUserTagNew.setUpdateBy(RequestUtils.getUserId());
        bizUserTagNew.setUpdateByName(RequestUtils.getUsername());
        bizUserTagNew.setUpdateDate(new Date(System.currentTimeMillis()));
        return Result.judge(baseMapper.updateById(bizUserTag));
    }

    @Override
    public Result delete(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        return Result.judge(baseMapper.deleteBatchIds(idList));
    }

    @Override
    public Result detail(Long id) {
        QueryWrapper<BizUserTag> query = new QueryWrapper<>();
        query.eq("ID", id);
        query().eq("IS_AVLIABLE",1);
        return Result.success(baseMapper.selectOne(query));
    }

    @Override
    public Result list(BizUserTagVo bizUserTagVo) {
        Page page = new Page<>(bizUserTagVo.getCurrentPage(), bizUserTagVo.getPageSize());
        LambdaQueryWrapper<BizUserTag> queryWrapper = new LambdaQueryWrapper<BizUserTag>()
                .like(StrUtil.isNotBlank(bizUserTagVo.getTagName()), BizUserTag::getTagName, StrUtil.trimToNull(bizUserTagVo.getTagName()))
                .orderByDesc(BizUserTag::getCreateDate);
        IPage<BizUserTag> result = baseMapper.selectPage(page,queryWrapper);
        return Result.success(result.getRecords(),result.getTotal());
    }

    @Override
    public Result userTags(Long userId) {
        LambdaQueryWrapper<BizUserTag> queryWrapper = new LambdaQueryWrapper<BizUserTag>()
                .eq(BizUserTag::getUserId,userId)
                .eq(BizUserTag::getIsAvailable,1)
                .orderByDesc(BizUserTag::getCreateDate);
        return Result.success(baseMapper.selectList(queryWrapper));
    }
}
