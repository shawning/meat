package com.meet.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meet.app.dto.AppUserDto;
import com.meet.app.dto.ValidCodeDto;
import com.meet.app.entity.BizUser;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.entity.BizUserFriends;
import com.meet.app.entity.BizVersion;
import com.meet.app.mapper.BizUserBlacklistMapper;
import com.meet.app.mapper.BizUserFriendsMapper;
import com.meet.app.mapper.BizUserMapper;
import com.meet.app.service.BizUserService;
import com.meet.app.service.im.IMUserService;
import com.meet.app.vo.BizUserForgotPasswordVo;
import com.meet.app.vo.BizUserLoginPasswordVo;
import com.meet.app.vo.BizUserLoginValidCodeVo;
import com.meet.app.vo.BizUserSetPasswordVo;
import com.meet.sms.service.SmsService;
import com.meet.sms.utils.SmsUtils;
import com.youlai.common.constant.AuthConstants;
import com.youlai.common.result.Result;
import com.youlai.common.result.ResultCode;
import com.youlai.common.utils.RegexUtils;
import com.youlai.common.web.exception.BizException;
import com.youlai.common.web.util.RequestUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.apache.naming.factory.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.youlai.common.result.ResultCode.PARAM_IS_NULL;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/2/25 下午1:49
 * @Description
 */
@Service
@AllArgsConstructor
public class BizUserServiceImpl extends ServiceImpl<BizUserMapper, BizUser> implements BizUserService {
    private RedisTemplate redisTemplate;
    private BizUserBlacklistMapper bizUserBlacklistMapper;
    private BizUserFriendsMapper bizUserFriendsMapper;
    private BizUserMapper bizUserMapper;
//    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SmsService smsService;
    @Autowired
    private IMUserService imUserService;

    @Override
    public Result getBizUser(Long id) {
        return Result.success(baseMapper.selectById(id));
    }
    public Result getBizUserByPhone(String phone){
        if(StringUtils.isEmpty(phone)){
            return Result.failed("手机号为空");
        }
        BizUser user = baseMapper.getBizUserByPhone(phone);
        if(user == null){
            return Result.failed(ResultCode.USER_NOT_EXIST);
        }
        AppUserDto dto = new AppUserDto();
        BeanUtil.copyProperties(user,dto);
        System.out.println("--------result----------"+new JSONObject(Result.success(dto)));
        return Result.success(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(BizUser bizUser) {
        if(StringUtils.isEmpty(bizUser.getPhone())){
            return Result.failed("手机号不能为空");
        }
        if(!RegexUtils.validateMobilePhone(bizUser.getPhone())){
            return Result.failed("手机号不合法");
        }
        bizUser.setName(bizUser.getPhone());
        bizUser.setStatus(1);
        bizUser.setSmsStatus(1);
        bizUser.setVipType(1);
        bizUser.setCreateDate(new Date(System.currentTimeMillis()));
        bizUser.setIsAvailable(1);
        bizUser.setPassInfo(bizUser.getPhone());
        if(this.save(bizUser)){
            //IM增加用户
            imUserService.create(bizUser.getId().toString(), bizUser.getPassInfo());
        }
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addBlack(Long userId) {
        if(userId == null){
            return Result.failed("拉黑对象不能为空");
        }
        BizUser bizUser = bizUserMapper.selectById(userId);
        if(bizUser == null){
            return Result.failed("拉黑信息不存在");
        }
        BizUserBlacklist bizUserBlacklist = BizUserBlacklist.builder()
                .userId(RequestUtils.getUserId())
                .friendId(bizUser.getId())
                .friendPhone(bizUser.getPhone())
                .createBy(RequestUtils.getUserId())
                .createByName(RequestUtils.getUsername())
                .createDate(new Date(System.currentTimeMillis()))
                .userId(RequestUtils.getUserId())
                .isAvailable(1).build();
        return Result.judge(bizUserBlacklistMapper.insert(bizUserBlacklist));
    }

    @Override
    public Result deleteBlack(Long userId) {
        if(userId == null){
            return Result.failed("取消拉黑对象不能为空");
        }
        LambdaQueryWrapper<BizUserBlacklist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizUserBlacklist::getFriendId, userId);
        queryWrapper.eq(BizUserBlacklist::getUserId, RequestUtils.getUserId());
        queryWrapper.eq(BizUserBlacklist::getIsAvailable, 1);
        BizUserBlacklist bizUserBlacklistNew = bizUserBlacklistMapper.selectOne(queryWrapper);
        bizUserBlacklistNew.setUpdateBy(RequestUtils.getUserId());
        bizUserBlacklistNew.setUpdateByName(RequestUtils.getUsername());
        bizUserBlacklistNew.setUpdateDate(new Date(System.currentTimeMillis()));
        bizUserBlacklistNew.setIsAvailable(0);
        return Result.judge(bizUserBlacklistMapper.updateById(bizUserBlacklistNew));
    }

    @Override
    public Result addFriend(Long id) {
        if(id == null){
            return Result.failed("好友不能为空");
        }
        BizUser bizUser = bizUserMapper.selectById(id);
        if(bizUser == null){
            return Result.failed("好友信息不存在");
        }
        BizUserFriends bizUserFriends = BizUserFriends.builder().userId(RequestUtils.getUserId())
                .friendId(bizUser.getId())
                .friendPhone(bizUser.getPhone())
                .isAvailable(1)
                .createBy(bizUser.getId())
                .createByName(bizUser.getName())
                .createDate(new Date(System.currentTimeMillis()))
                .intimacy(1L)
                .build();
        return Result.judge(bizUserFriendsMapper.insert(bizUserFriends));
    }

    @Override
    public Result deleteFriend(Long id) {
        if(id == null){
            return Result.failed("好友不能为空");
        }
        LambdaQueryWrapper<BizUserFriends> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizUserFriends::getUserId, RequestUtils.getUserId());
        queryWrapper.eq(BizUserFriends::getFriendId,id);
        BizUserFriends bizUserFriends = bizUserFriendsMapper.selectOne(queryWrapper);
        if(bizUserFriends == null){
            return Result.failed("好友信息不存在");
        }
        bizUserFriends.setUpdateDate(new Date(System.currentTimeMillis()));
        bizUserFriends.setUpdateBy(RequestUtils.getUserId());
        bizUserFriends.setUpdateByName(RequestUtils.getUsername());
        bizUserFriends.setIsAvailable(0);//至于无效
        return Result.judge(bizUserFriendsMapper.updateById(bizUserFriends));
    }

    @Override
    public Result getValidCode(String phone) {
        if(StrUtil.isEmpty(phone)){
            return Result.failed("手机号码不能为空");
        }
        if(!RegexUtils.validateMobilePhone(phone)){
            return Result.failed("手机号不合法");
        }
        String validCode = "";
        boolean hasValidCode = redisTemplate.hasKey(phone+"_" +AuthConstants.SMS_VALID_CODE);
        if(hasValidCode){
            validCode = redisTemplate.opsForValue().get(phone+"_" +AuthConstants.SMS_VALID_CODE).toString();
        }else {
//            validCode = "8888";
            validCode = RegexUtils.getFourRandom();
        }
        smsService.sendLoginSms(phone,validCode);
        redisTemplate.opsForValue().set(phone + "_" + AuthConstants.SMS_VALID_CODE, validCode, 10 * 60, TimeUnit.SECONDS);
        return Result.success(ValidCodeDto.builder().validCode(validCode).build());
    }

    @Override
    public Result loginByPwd(BizUserLoginPasswordVo bizUserLoginPasswordVo) {
        if(bizUserLoginPasswordVo == null){
            return Result.failed(PARAM_IS_NULL);
        }
        LambdaQueryWrapper<BizUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BizUser::getPhone, bizUserLoginPasswordVo.getPhone());
        queryWrapper.eq(BizUser::getIsAvailable, 1);
        BizUser  bizUser = bizUserMapper.getBizUserByPhone(bizUserLoginPasswordVo.getPhone());
        if(bizUser == null){
            return Result.failed("用户不存在，请注册");
        }
        String password = "";
//        String password = passwordEncoder.encode(bizUserLoginPasswordVo.getPassword()).replace(AuthConstants.BCRYPT, Strings.EMPTY);
        if(!password.equals(bizUserLoginPasswordVo.getPassword())){
            return Result.failed("用户密码错误");
        }
        redisTemplate.delete(bizUserLoginPasswordVo.getPhone()+"_" +AuthConstants.SMS_VALID_CODE);
        return Result.success(bizUser);
    }

    @Override
    public Result loginByValidCode(BizUserLoginValidCodeVo bizUserLoginValidCodeVo) {
        if(bizUserLoginValidCodeVo == null){
            return Result.failed(PARAM_IS_NULL);
        }
        BizUser  bizUser = bizUserMapper.getBizUserByPhone(bizUserLoginValidCodeVo.getPhone());
        if(bizUser == null){
            return Result.failed("用户不存在，请注册");
        }
        boolean hasValidCode = redisTemplate.hasKey(bizUserLoginValidCodeVo.getPhone()+"_" +AuthConstants.SMS_VALID_CODE);
        if(!hasValidCode){
            return Result.failed("验证码无效");
        }
        String validCode = redisTemplate.opsForValue().get(bizUserLoginValidCodeVo.getPhone()+"_" +AuthConstants.SMS_VALID_CODE).toString();
        if(!validCode.equals(bizUserLoginValidCodeVo.getValidCode())){
            return Result.failed("验证码错误");
        }
        redisTemplate.delete(bizUserLoginValidCodeVo.getPhone()+"_" +AuthConstants.SMS_VALID_CODE);
        return Result.success(bizUser);
    }

    @Override
    public Result setPwd(BizUserSetPasswordVo bizUserSetPasswordVo) {
        if(bizUserSetPasswordVo == null){
            return Result.failed(PARAM_IS_NULL);
        }
        if(StrUtil.isBlank(bizUserSetPasswordVo.getPassword())){
            return Result.failed("密码不能为空");
        }
//        String password = "";
        String password = passwordEncoder.encode(bizUserSetPasswordVo.getPassword()).replace(AuthConstants.BCRYPT, Strings.EMPTY);
        BizUser  bizUser = bizUserMapper.getBizUserByPhone(bizUserSetPasswordVo.getPhone());
        bizUser.setPassword(password);
        bizUser.setPassInfo(bizUserSetPasswordVo.getPassword());
        if(bizUserMapper.updateById(bizUser) > 0){
            //IM修改密码
            imUserService.updateUserPassword(bizUser.getId().toString(), bizUser.getPassInfo());
        }
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result forgotPwd(BizUserForgotPasswordVo bizUserForgotPasswordVo) {
        if(bizUserForgotPasswordVo == null){
            return Result.failed(PARAM_IS_NULL);
        }
        if(StrUtil.isBlank(bizUserForgotPasswordVo.getValidCode())){
            return Result.failed("验证码不能为空");
        }
        boolean hasValidCode = redisTemplate.hasKey(bizUserForgotPasswordVo.getPhone()+"_" +AuthConstants.SMS_VALID_CODE);
        if(!hasValidCode){
            throw new BizException("无效验证码，请重新获取");
        }
        String validCode = redisTemplate.opsForValue().get(bizUserForgotPasswordVo.getPhone()+"_" +AuthConstants.SMS_VALID_CODE).toString();
        if(!validCode.equals(bizUserForgotPasswordVo.getValidCode())){
            throw new BizException("验证码错误");
        }
        BizUser  bizUser = bizUserMapper.getBizUserByPhone(bizUserForgotPasswordVo.getPhone());
        if(bizUser == null){
            return Result.failed("用户不存在");
        }
        String password = passwordEncoder.encode(bizUserForgotPasswordVo.getPassword()).replace(AuthConstants.BCRYPT, Strings.EMPTY);
        bizUser.setPassword(password);
        bizUser.setPassInfo(bizUserForgotPasswordVo.getPassword());
        bizUser.setIsAvailable(1);
        int a = bizUserMapper.updateById(bizUser);
        if(bizUserMapper.updateById(bizUser) > 0){
            //IM修改密码
            imUserService.updateUserPassword(bizUser.getId().toString(), bizUser.getPassInfo());
        }
        return Result.success();
    }
}
