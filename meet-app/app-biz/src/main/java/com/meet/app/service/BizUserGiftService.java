package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserGift;
import com.meet.app.vo.BizUserGiftVo;
import com.youlai.common.result.Result;

/**
 * 用户收到的礼物管理
 * @author xiaoning
 */

public interface BizUserGiftService extends IService<BizUserGift> {

        /**
         * @param bizUserGiftVo
         * @return
         */
        Result list(BizUserGiftVo bizUserGiftVo);

        /**
         * @param bizUserGift
         * @return
         */
        Result add(BizUserGift bizUserGift);

        /**
         * @param bizUserGift
         * @return
         */
        Result update(BizUserGift bizUserGift);

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
