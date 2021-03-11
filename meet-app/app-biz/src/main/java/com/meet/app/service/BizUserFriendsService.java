package com.meet.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUserFriends;
import com.meet.app.vo.BizUserFriendsVo;
import com.youlai.common.result.Result;

/**
 * 我的好友管理
 * @author xiaoning
 */

public interface BizUserFriendsService extends IService<BizUserFriends> {

        /**
         * @param bizUserFriendsVo
         * @return
         */
        Result list(BizUserFriendsVo bizUserFriendsVo);

        /**
         * @param bizUserFriends
         * @return
         */
        Result add(BizUserFriends bizUserFriends);

        /**
         * @param bizUserFriends
         * @return
         */
        Result update(BizUserFriends bizUserFriends);

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
