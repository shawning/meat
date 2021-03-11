package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserMomentsComment;
import com.meet.app.vo.BizUserMomentsCommentVo;
import com.youlai.common.result.Result;

/**
 * 用户朋友圈评论记录管理
 * @author xiaoning
 */

public interface BizUserMomentsCommentService extends IService<BizUserMomentsComment> {

        /**
         * @param bizUserMomentsCommentVo
         * @return
         */
        Result list(BizUserMomentsCommentVo bizUserMomentsCommentVo);

        /**
         * @param bizUserMomentsComment
         * @return
         */
        Result add(BizUserMomentsComment bizUserMomentsComment);

        /**
         * @param bizUserMomentsComment
         * @return
         */
        Result update(BizUserMomentsComment bizUserMomentsComment);

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
