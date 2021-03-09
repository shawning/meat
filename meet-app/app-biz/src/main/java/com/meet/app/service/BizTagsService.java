package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizTags;
import com.meet.app.vo.BizTagsVo;
import com.youlai.common.result.Result;

/**
 * 用户标签表管理
 * @author xiaoning
 */

public interface BizTagsService extends IService<BizTags> {

        /**
         * @param bizTagsVo
         * @return
         */
        Result list(BizTagsVo bizTagsVo);

        /**
         * @param bizTags
         * @return
         */
        Result add(BizTags bizTags);

        /**
         * @param bizTags
         * @return
         */
        Result update(BizTags bizTags);

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
