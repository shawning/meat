package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserMedal;
import com.meet.app.vo.BizUserMedalVo;
import com.youlai.common.result.Result;

/**
 * 用户勋章管理
 * @author xiaoning
 */

public interface BizUserMedalService extends IService<BizUserMedal> {

        /**
         * @param bizUserMedalVo
         * @return
         */
        Result list(BizUserMedalVo bizUserMedalVo);

        /**
         * @param bizUserMedal
         * @return
         */
        Result add(BizUserMedal bizUserMedal);

        /**
         * @param bizUserMedal
         * @return
         */
        Result update(BizUserMedal bizUserMedal);

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
