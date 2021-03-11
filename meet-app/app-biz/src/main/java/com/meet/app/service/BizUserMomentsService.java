package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserMoments;
import com.meet.app.vo.BizUserMomentsVo;
import com.youlai.common.result.Result;

/**
 * 用户朋友圈动态管理
 * @author xiaoning
 */

public interface BizUserMomentsService extends IService<BizUserMoments> {

        /**
         * @param bizUserMomentsVo
         * @return
         */
        Result list(BizUserMomentsVo bizUserMomentsVo);

        /**
         * @param bizUserMoments
         * @return
         */
        Result add(BizUserMoments bizUserMoments);

        /**
         * @param bizUserMoments
         * @return
         */
        Result update(BizUserMoments bizUserMoments);

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
