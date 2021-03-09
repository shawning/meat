package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizInterests;
import com.meet.app.vo.BizInterestsVo;
import com.youlai.common.result.Result;

/**
 * 用户兴趣爱好表管理
 * @author xiaoning
 */

public interface BizInterestsService extends IService<BizInterests> {

        /**
         * @param bizInterestsVo
         * @return
         */
        Result list(BizInterestsVo bizInterestsVo);

        /**
         * @param bizInterests
         * @return
         */
        Result add(BizInterests bizInterests);

        /**
         * @param bizInterests
         * @return
         */
        Result update(BizInterests bizInterests);

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
