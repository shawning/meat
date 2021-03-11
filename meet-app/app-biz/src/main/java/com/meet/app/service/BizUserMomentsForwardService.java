package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserMomentsForward;
import com.meet.app.vo.BizUserMomentsForwardVo;
import com.youlai.common.result.Result;

/**
 * 用户朋友圈转发记录管理
 * @author xiaoning
 */

public interface BizUserMomentsForwardService extends IService<BizUserMomentsForward> {

        /**
         * @param bizUserMomentsForwardVo
         * @return
         */
        Result list(BizUserMomentsForwardVo bizUserMomentsForwardVo);

        /**
         * @param bizUserMomentsForward
         * @return
         */
        Result add(BizUserMomentsForward bizUserMomentsForward);

        /**
         * @param bizUserMomentsForward
         * @return
         */
        Result update(BizUserMomentsForward bizUserMomentsForward);

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
