package com.meet.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUser;
import com.youlai.common.result.Result;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/2/25 下午1:48
 * @Description
 */
public interface BizUserService extends IService<BizUser> {
    Result getBizUser(Long id);
    Result getBizUserByPhone(String phone);
    Result add(BizUser bizUser);
}
