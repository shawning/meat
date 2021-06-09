package com.meet.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizRoom;
import com.meet.app.vo.BizRoomVo;
import com.youlai.common.result.Result;

/**
 * 直播房间号管理
 * @author xiaoning
 */

public interface BizRoomService extends IService<BizRoom> {

        /**
         * @param bizRoomVo
         * @return
         */
        Result list(BizRoomVo bizRoomVo);

        /**
         * @param bizRoom
         * @return
         */
        Result add(BizRoom bizRoom);

        /**
         * @param bizRoom
         * @return
         */
        Result update(BizRoom bizRoom);

        /**
         * @param id
         * @return
         */
        Result delete(Long id);

        /**
         * @param ids
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

        /**
         * 开始直播
         * @return
         */
        Result start();
        /**
         * 结束直播
         * @return
         */
        Result end(String roomId);

        /**
         * 进入直播间
         * @param roomId
         * @return
         */
        Result join(String roomId);
        Result leave(String roomId);

}
