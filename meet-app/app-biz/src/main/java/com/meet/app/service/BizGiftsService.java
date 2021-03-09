package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizGifts;
import com.meet.app.vo.BizGiftsVo;
import com.youlai.common.result.Result;

/**
 * 礼物表管理
 * @author xiaoning
 */

public interface BizGiftsService extends IService<BizGifts> {

        /**
         * @param bizGiftsVo
         * @return
         */
        Result list(BizGiftsVo bizGiftsVo);

        /**
         * @param bizGifts
         * @return
         */
        Result add(BizGifts bizGifts);

        /**
         * @param bizGifts
         * @return
         */
        Result update(BizGifts bizGifts);

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
