package com.meet.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserTag;
import com.meet.app.vo.BizUserTagVo;
import com.youlai.admin.pojo.entity.SysUser;
import com.youlai.common.result.Result;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/3/8 下午1:13
 * @Description
 */
public interface BizUserTagService extends IService<BizUserTag> {
    Result add(BizUserTag bizUserTag);
    Result update(Long id,BizUserTag bizUserTag);
    Result delete(String ids);
    Result detail(Long id);
    Result list(BizUserTagVo bizUserTagVo);
    Result userTags(Long userId);
}
