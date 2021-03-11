package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.vo.BizUserBlacklistVo;
import com.youlai.common.result.Result;

/**
 * 我的黑名单管理
 * @author xiaoning
 */

public interface BizUserBlacklistService extends IService<BizUserBlacklist> {

        /**
         * @param bizUserBlacklistVo
         * @return
         */
        Result list(BizUserBlacklistVo bizUserBlacklistVo);

        /**
         * @param bizUserBlacklist
         * @return
         */
        Result add(BizUserBlacklist bizUserBlacklist);

        /**
         * @param bizUserBlacklist
         * @return
         */
        Result update(BizUserBlacklist bizUserBlacklist);

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
