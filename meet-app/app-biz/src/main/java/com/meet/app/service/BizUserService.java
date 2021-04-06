package com.meet.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meet.app.entity.BizUser;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.entity.BizUserFriends;
import com.meet.app.vo.BizUserForgotPasswordVo;
import com.meet.app.vo.BizUserLoginPasswordVo;
import com.meet.app.vo.BizUserLoginValidCodeVo;
import com.meet.app.vo.BizUserSetPasswordVo;
import com.youlai.common.result.Result;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/2/25 下午1:48
 * @Description
 */
public interface BizUserService extends IService<BizUser> {
    Result getBizUser(Long id);
    Result getBizUserByPhone(String phone);
    Result add(BizUser bizUser);
    Result addBlack(Long id);
    Result deleteBlack(Long id);
    Result addFriend(Long id);
    Result deleteFriend(Long id);
    Result getValidCode(String phone);
    Result loginByPwd(BizUserLoginPasswordVo bizUserLoginPasswordVo);
    Result loginByValidCode(BizUserLoginValidCodeVo bizUserLoginValidCodeVo);
    Result setPwd(BizUserSetPasswordVo bizUserSetPasswordVo);
    Result forgotPwd(BizUserForgotPasswordVo bizUserForgotPasswordVo);
}
