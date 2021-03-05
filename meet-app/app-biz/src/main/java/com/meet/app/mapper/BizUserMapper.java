package com.meet.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meet.app.entity.BizUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xiaoning
 */

@Repository
public interface BizUserMapper extends BaseMapper<BizUser> {
    BizUser getBizUserByPhone(@Param("phone") String phone);

}