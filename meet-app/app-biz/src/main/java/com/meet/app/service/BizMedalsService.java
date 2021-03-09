package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizMedals;
import com.meet.app.vo.BizMedalsVo;
import com.youlai.common.result.Result;

/**
 * 勋章表管理
 * @author xiaoning
 */

public interface BizMedalsService extends IService<BizMedals> {

        /**
         * @param bizMedalsVo
         * @return
         */
        Result list(BizMedalsVo bizMedalsVo);

        /**
         * @param bizMedals
         * @return
         */
        Result add(BizMedals bizMedals);

        /**
         * @param bizMedals
         * @return
         */
        Result update(BizMedals bizMedals);

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
