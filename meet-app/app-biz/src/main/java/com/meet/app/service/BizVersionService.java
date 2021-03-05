package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizVersion;
import com.meet.app.vo.BizVersionVo;
import com.youlai.common.result.Result;

/**
 * 应用版本管理管理
 * @author xiaoning
 */

public interface BizVersionService extends IService<BizVersion> {

        /**
         * @param bizVersionVo
         * @return
         */
        IPage<BizVersion> selectBizVersionList(BizVersionVo bizVersionVo);

        /**
         * @param bizVersion
         * @return
         */
        Boolean saveBizVersion(BizVersion bizVersion);

        /**
         * @param bizVersion
         * @return
         */
        Boolean updateBizVersion(BizVersion bizVersion);

        /**
         * @param id
         * @return
         */
        Boolean deleteBizVersion(Long id);

        /**
         * 根据ID查询
         *
         * @param id
         * @return
         */
        BizVersion getBizVersion(Long id);
        Result checkUpdate();

}
