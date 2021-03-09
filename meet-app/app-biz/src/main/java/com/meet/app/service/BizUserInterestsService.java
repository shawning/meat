package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserInterests;
import com.meet.app.vo.BizUserInterestsVo;
import com.youlai.common.result.Result;

/**
 * 用户兴趣爱好管理
 * @author xiaoning
 */

public interface BizUserInterestsService extends IService<BizUserInterests> {

        /**
         * @param bizUserInterestsVo
         * @return
         */
        Result list(BizUserInterestsVo bizUserInterestsVo);

        /**
         * @param bizUserInterests
         * @return
         */
        Result add(BizUserInterests bizUserInterests);

        /**
         * @param bizUserInterests
         * @return
         */
        Result update(BizUserInterests bizUserInterests);

        /**
         * @param id
         * @return
         */
        Result delete(Long id);
        /**
         * @param id
         * @return
         */
        Result delete(String ids);

        /**
         * 根据ID查询
         *
         * @param id
         * @return
         */
        Result detail(Long id);
}
