package com.meet.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meet.app.dto.BizVersionDto;
import com.meet.app.entity.BizVersion;
import com.meet.app.mapper.BizVersionMapper;
import com.meet.app.service.BizVersionService;
import com.meet.app.vo.BizVersionVo;
import com.youlai.common.result.Result;
import com.youlai.common.result.ResultCode;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 应用版本管理管理
 * @author xiaoning
 */

@Slf4j
@Service
public class BizVersionServiceImpl extends ServiceImpl<BizVersionMapper, BizVersion> implements BizVersionService {

    @Override
    public IPage<BizVersion> selectBizVersionList(BizVersionVo bizVersionVo) {
        if (bizVersionVo == null) {
            return this.page(new Page<>(), Wrappers.emptyWrapper());
        }
        LambdaQueryWrapper<BizVersion> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bizVersionVo.getAppName())){
            queryWrapper.like(BizVersion::getAppName, bizVersionVo.getAppName());
        }
        if(bizVersionVo.getAppType() != null){
            queryWrapper.eq(BizVersion::getAppType, bizVersionVo.getAppType());
        }
        if(bizVersionVo.getAppPlatform() != null){
            queryWrapper.eq(BizVersion::getAppPlatform, bizVersionVo.getAppPlatform());
        }
        if(StringUtils.isNotBlank(bizVersionVo.getAppVersion())){
            queryWrapper.like(BizVersion::getAppVersion, bizVersionVo.getAppVersion());
        }
        if(bizVersionVo.getAppVersionOrder() != null){
            queryWrapper.eq(BizVersion::getAppVersionOrder, bizVersionVo.getAppVersionOrder());
        }
        if(bizVersionVo.getAppVersionStatus() != null){
            queryWrapper.eq(BizVersion::getAppVersionStatus, bizVersionVo.getAppVersionStatus());
        }
        if(bizVersionVo.getIsEnable() != null){
            queryWrapper.eq(BizVersion::getIsEnable, bizVersionVo.getIsEnable());
        }
        if(StringUtils.isNotBlank(bizVersionVo.getAppUpdateDesc())){
            queryWrapper.like(BizVersion::getAppUpdateDesc, bizVersionVo.getAppUpdateDesc());
        }
        if(StringUtils.isNotBlank(bizVersionVo.getAppDownloadUrl())){
            queryWrapper.like(BizVersion::getAppDownloadUrl, bizVersionVo.getAppDownloadUrl());
        }

        Page<BizVersion> page = new Page<>();
//        Sort.bind(bizVersionVo, page, true);
        return this.page(page, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveBizVersion(@NonNull BizVersion bizVersion) {
        bizVersion.setCreateDate(new Date());
        bizVersion.setIsAvailable(1);
        return this.save(bizVersion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateBizVersion(BizVersion bizVersionNew) {
        Long id = bizVersionNew.getId();
        if (id == null) {
            return Boolean.FALSE;
        }
        BizVersion bizVersion = this.getById(id);

        bizVersion.setAppName(bizVersionNew.getAppName());
        bizVersion.setAppType(bizVersionNew.getAppType());
        bizVersion.setAppPlatform(bizVersionNew.getAppPlatform());
        bizVersion.setAppVersion(bizVersionNew.getAppVersion());
        bizVersion.setAppVersionOrder(bizVersionNew.getAppVersionOrder());
        bizVersion.setAppVersionStatus(bizVersionNew.getAppVersionStatus());
        bizVersion.setIsEnable(bizVersionNew.getIsEnable());
        bizVersion.setAppUpdateDesc(bizVersionNew.getAppUpdateDesc());
        bizVersion.setAppDownloadUrl(bizVersionNew.getAppDownloadUrl());
        bizVersion.setUpdateDate(new Date());

        return this.updateById(bizVersion);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteBizVersion(@NonNull Long id) {
        return this.removeById(id);
    }

    @Override
    public BizVersion getBizVersion(@NonNull Long id){
        return this.getById(id);
    }

    @Override
    public Result checkUpdate() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String appVersion = request.getHeader("appVersion");
        String appType = request.getHeader("appType");
        String appPlatform = request.getHeader("appPlatform");
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(appVersion) || StringUtils.isEmpty(appType)){
            return Result.failed(ResultCode.PARAM_ERROR);
        }
        BizVersion appVersionCurrent = baseMapper.findVersion(Integer.parseInt(appType),appVersion,Integer.parseInt(appPlatform));
        if (appVersionCurrent == null) {
            return Result.failed(ResultCode.CLIENT_AUTHENTICATION_NOT_EXIST);
        }
        if(appVersionCurrent.getAppVersionStatus().equals(2) || appVersionCurrent.getAppVersionStatus().equals(3)){
            BizVersion lastAvailableVersion = baseMapper.findLastAvailableVersion(Integer.parseInt(appType),Integer.parseInt(appPlatform));
            return Result.success(
                    BizVersionDto.builder()
                    .appName(lastAvailableVersion.getAppName())
                    .appType(lastAvailableVersion.getAppType())
                    .appPlatform(lastAvailableVersion.getAppPlatform())
                    .appVersion(lastAvailableVersion.getAppVersion())
                    .appVersionStatus(1)
                    .appUpdateDesc(lastAvailableVersion.getAppUpdateDesc())
                    .appDownloadUrl(lastAvailableVersion.getAppDownloadUrl())
                    .build()
            );
        }else{
            return Result.success();
        }
    }
}
