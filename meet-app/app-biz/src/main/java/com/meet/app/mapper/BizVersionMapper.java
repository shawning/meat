package com.meet.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meet.app.entity.BizVersion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoning
 */

@Repository
public interface BizVersionMapper extends BaseMapper<BizVersion> {
    public BizVersion findVersion(@Param("appType") Integer appType,@Param("appVersion") String appVsersion,@Param("appPlatform") Integer appPlatform);
    public BizVersion findLastAvailableVersion(@Param("appType") Integer appType,@Param("appPlatform") Integer appPlatform);
}