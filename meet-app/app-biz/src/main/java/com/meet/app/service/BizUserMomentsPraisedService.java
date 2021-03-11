package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserMomentsPraised;
import com.meet.app.vo.BizUserMomentsPraisedVo;
import com.youlai.common.result.Result;

/**
 * 用户朋友圈点赞记录管理
 * @author xiaoning
 */

public interface BizUserMomentsPraisedService extends IService<BizUserMomentsPraised> {

        /**
         * @param bizUserMomentsPraisedVo
         * @return
         */
        Result list(BizUserMomentsPraisedVo bizUserMomentsPraisedVo);

        /**
         * @param bizUserMomentsPraised
         * @return
         */
        Result add(BizUserMomentsPraised bizUserMomentsPraised);

        /**
         * @param bizUserMomentsPraised
         * @return
         */
        Result update(BizUserMomentsPraised bizUserMomentsPraised);

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
