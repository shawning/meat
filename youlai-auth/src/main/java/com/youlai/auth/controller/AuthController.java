package com.youlai.auth.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.util.StrUtil;
import com.meet.app.api.UserFeignService;
import com.meet.app.dto.AppUserDto;
import com.youlai.auth.domain.Oauth2Token;
import com.youlai.common.constant.AuthConstants;
import com.youlai.common.constant.GlobalConstants;
import com.youlai.common.result.Result;
import com.youlai.common.result.ResultCode;
import com.youlai.common.utils.RegexUtils;
import com.youlai.common.web.exception.BizException;
//import com.youlai.mall.ums.api.MemberFeignService;
//import com.youlai.mall.ums.pojo.UmsUser;
//import com.youlai.mall.ums.pojo.dto.AuthMemberDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;

@Api(tags = "认证中心")
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private TokenEndpoint tokenEndpoint;

    @ApiOperation("OAuth2认证生成token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", defaultValue = "client", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", defaultValue = "123456", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "登录用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "登录密码"),

            // 微信小程序认证参数（无小程序可忽略）
            @ApiImplicitParam(name = "code", value = "小程序code"),
            @ApiImplicitParam(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据"),
            @ApiImplicitParam(name = "iv", value = "加密算法的初始向量"),
    })
    @PostMapping("/token")
    public Result postAccessToken(
            @ApiIgnore Principal principal,
            @ApiIgnore @RequestParam Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        String clientId = parameters.get(AuthConstants.JWT_CLIENT_ID_KEY);
        switch (clientId) {
//            case AuthConstants.WEAPP_CLIENT_ID:  // 微信认证
//                return this.handleForWxAuth(principal, parameters);
            case AuthConstants.APP_CLIENT_ID:  // APP认证
                return this.handleForAPPAuth(principal, parameters);
            default:
                OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
                Oauth2Token oauth2Token = Oauth2Token.builder()
                        .token(oAuth2AccessToken.getValue())
                        .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                        .expiresIn(oAuth2AccessToken.getExpiresIn())
                        .build();
                return Result.success(oauth2Token);
        }
    }

    /**
     * app登录
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @ApiOperation("OAuth2认证生成token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", defaultValue = "client", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", defaultValue = "123456", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "登录用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "登录密码"),

            // 微信小程序认证参数（无小程序可忽略）
            @ApiImplicitParam(name = "code", value = "小程序code"),
            @ApiImplicitParam(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据"),
            @ApiImplicitParam(name = "iv", value = "加密算法的初始向量"),
    })
    @PostMapping("/login")
    public Result login(
            @ApiIgnore Principal principal,
            @ApiIgnore @RequestParam Map<String, String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        String clientId = parameters.get(AuthConstants.JWT_CLIENT_ID_KEY);
        switch (clientId) {
            case AuthConstants.APP_CLIENT_ID:  // APP认证
                return this.handleForAPPAuth(principal, parameters);
            default:
                OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
                Oauth2Token oauth2Token = Oauth2Token.builder()
                        .token(oAuth2AccessToken.getValue())
                        .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                        .expiresIn(oAuth2AccessToken.getExpiresIn())
                        .build();
                return Result.success(oauth2Token);
        }
    }


    private WxMaService wxService;
//    private MemberFeignService memberFeignService;
    private PasswordEncoder passwordEncoder;

    /*public Result handleForWxAuth(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        String code = parameters.get("code");

        if (StrUtil.isBlank(code)) {
            throw new BizException("code不能为空");
        }

        WxMaJscode2SessionResult session = null;
        try {
            session = wxService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String openid = session.getOpenid();
        String sessionKey = session.getSessionKey();

        Result<AuthMemberDTO> result = memberFeignService.getUserByOpenid(openid);

        if (ResultCode.USER_NOT_EXIST.getCode().equals(result.getCode())) { // 微信授权登录 会员信息不存在时 注册会员
            String encryptedData = parameters.get("encryptedData");
            String iv = parameters.get("iv");

            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
            if (userInfo == null) {
                throw new BizException("获取用户信息失败");
            }
            UmsUser user = new UmsUser()
                    .setNickname(userInfo.getNickName())
                    .setAvatar(userInfo.getAvatarUrl())
                    .setGender(Integer.valueOf(userInfo.getGender()))
                    .setOpenid(openid)
                    .setUsername(openid)
                    .setPassword(passwordEncoder.encode(openid).replace(AuthConstants.BCRYPT, Strings.EMPTY)) // 加密密码移除前缀加密方式 {bcrypt}
                    .setStatus(GlobalConstants.STATUS_NORMAL_VALUE);

            Result res = memberFeignService.add(user);
            if (!ResultCode.SUCCESS.getCode().equals(res.getCode())) {
                throw new BizException("注册会员失败");
            }
        }

        // oauth2认证参数对应授权登录时注册会员的username、password信息，模拟通过oauth2的密码模式认证
        parameters.put("username", openid);
        parameters.put("password", openid);

        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2Token oauth2Token = Oauth2Token.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .build();
        return Result.success(oauth2Token);
    }*/

    private UserFeignService userFeignService;
    private RedisTemplate redisTemplate;
    /**
     * app认证
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    public Result handleForAPPAuth(Principal principal, Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        String password = "";
        String type = parameters.get("type");
        if(StrUtil.isEmpty(type)){
            return Result.failed("登录类型不能为空");
        }
        String username = parameters.get("username");
        if(type.contentEquals("psd")){
            password = parameters.get("password");
            if(StrUtil.isEmpty(username) || StrUtil.isEmpty(password)){
                return Result.failed("用户名密码不能为空");
            }
            Result<AppUserDto> result = userFeignService.getUserByPhone(username);
            if (ResultCode.USER_NOT_EXIST.getCode().equals(result.getCode())) { // 授权登录 会员信息不存在时报错
                return Result.failed("用户不存在");
            }
//            String passwordInput = (passwordEncoder.encode(password).replace(AuthConstants.BCRYPT, Strings.EMPTY));
//            if(!passwordInput.equals(result.getData().getPassword())){
//                return Result.failed("密码不正确");
//            }

        }else if(type.contentEquals("validCode")){
            String code = parameters.get("code");
            if (StrUtil.isBlank(code)) {
                throw new BizException("验证码不能为空");
            }
            boolean hasValidCode = redisTemplate.hasKey(username+"_" +AuthConstants.SMS_VALID_CODE);
            if(!hasValidCode){
                throw new BizException("无效验证码，请重新获取");
            }
            String validCode = redisTemplate.opsForValue().get(username+"_" +AuthConstants.SMS_VALID_CODE).toString();
            if(!validCode.equals(code)){
                throw new BizException("验证码错误");
            }
            Result<AppUserDto> result = userFeignService.getUserByPhone(parameters.get("username"));
            if (ResultCode.USER_NOT_EXIST.getCode().equals(result.getCode())) { // 授权登录 会员信息不存在时 注册会员
                AppUserDto user = new AppUserDto();
                user.setName(username);
                user.setPhone(username);
                user.setPassword((passwordEncoder.encode(username).replace(AuthConstants.BCRYPT, Strings.EMPTY)));
                Result res = userFeignService.add(user);
                if (!ResultCode.SUCCESS.getCode().equals(res.getCode())) {
                    throw new BizException("注册失败");
                }
                password = username;
            }else{//非注册
                password = result.getData().getPassInfo();
            }
        }

//        String password = (passwordEncoder.encode(username).replace(AuthConstants.BCRYPT, Strings.EMPTY));

        parameters.put("password", password);
        // oauth2认证参数对应授权登录时注册会员的username、password信息，模拟通过oauth2的密码模式认证
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2Token oauth2Token = Oauth2Token.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .build();
        redisTemplate.delete(username+"_" +AuthConstants.SMS_VALID_CODE);
        return Result.success(oauth2Token);
    }

}
