package com.meet.app.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.meet.app.entity.BizUser;
import com.meet.app.entity.BizUserBlacklist;
import com.meet.app.service.BizUserService;
import com.meet.app.vo.BizUserForgotPasswordVo;
import com.meet.app.vo.BizUserLoginPasswordVo;
import com.meet.app.vo.BizUserLoginValidCodeVo;
import com.meet.app.vo.BizUserSetPasswordVo;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 用户信息管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizUser")
@Api(value = "/api.app/v1/bizUser", tags = {"用户信息 API"}, description = "用户信息 API")
public class BizUserController {
    @Autowired
    private BizUserService bizUserService;

    /**
     * 根据ID获取用户信息
     *
     * @param phone 入参ID
     * @return R
     */
    @GetMapping("get/{phone}")
    @ApiOperation(notes = "根据ID获取用户信息",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "根据PHONE获取用户信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result getBizUser(@PathVariable("phone") String phone){
        Result result = bizUserService.getBizUserByPhone(phone);
        log.info("result"+result.getData());
        RequestUtils.getUserId();
        return bizUserService.getBizUserByPhone(phone);
    }

    /**
     *
     * @param user
     * @return
     *
     */
    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "注册用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/add")
    public Result add(@RequestBody BizUser user){
        log.info("BizUser ===> ", new JSONObject(user));
        return bizUserService.add(user);
    }

    @ApiOperation(
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "根据ID获取用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result get(@PathVariable("id") Long id){
        return bizUserService.getBizUser(id);
    }

    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "拉黑用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/addBlack")
    public Result addBlack(@RequestParam(defaultValue = "1") Long id){
        log.info("BizUser ===> ", id);
        return bizUserService.addBlack(id);
    }

    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "取消拉黑用户",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/deleteBlack")
    public Result deleteBlack(@RequestParam(defaultValue = "1") Long id){
        log.info("BizUser ===> ", id);
        return bizUserService.deleteBlack(id);
    }
    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "添加好友",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/addFriend")
    public Result addFriend(@RequestParam(defaultValue = "1") Long id){
        log.info("id ===> ", id);
        return bizUserService.addFriend(id);
    }
    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "删除好友",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/deleteFriend")
    public Result deleteFriend(@RequestParam(defaultValue = "1") Long id){
        log.info("id ===> ", id);
        return bizUserService.deleteFriend(id);
    }

    @ApiOperation(
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "根据手机号获取验证码",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/getValidCode/{phone}")
    public Result getValidCode(@PathVariable("phone") String phone){
        return bizUserService.getValidCode(phone);
    }

    @ApiIgnore
    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "密码登录",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/loginByPwd")
    public Result loginByPwd(@RequestBody BizUserLoginPasswordVo bizUserLoginPasswordVo){
        log.info("bizUserLoginPasswordVo ===> ", new JSONObject(bizUserLoginPasswordVo));
        return bizUserService.loginByPwd(bizUserLoginPasswordVo);
    }
    @ApiIgnore
    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "验证码登录",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/loginByValidCode")
    public Result loginByValidCode(@RequestBody BizUserLoginValidCodeVo bizUserLoginValidCodeVo){
        log.info("bizUserLoginValidCodeVo ===> ", new JSONObject(bizUserLoginValidCodeVo));
        return bizUserService.loginByValidCode(bizUserLoginValidCodeVo);
    }
    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "设置密码",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/setPwd")
    public Result setPwd(@RequestBody BizUserSetPasswordVo bizUserSetPasswordVo){
        log.info("bizUserSetPasswordVo ===> ", new JSONObject(bizUserSetPasswordVo));
        return bizUserService.setPwd(bizUserSetPasswordVo);
    }

    @ApiOperation(
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "找回密码",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/forgotPwd")
    public Result forgotPwd(@RequestBody BizUserForgotPasswordVo bizUserForgotPasswordVo){
        log.info("bizUserForgotPasswordVo ===> ", new JSONObject(bizUserForgotPasswordVo));
        return bizUserService.forgotPwd(bizUserForgotPasswordVo);
    }


}
