package com.meet.app.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.meet.app.entity.BizUser;
import com.meet.app.service.BizUserService;
import com.youlai.common.result.Result;
import com.youlai.common.web.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
}
