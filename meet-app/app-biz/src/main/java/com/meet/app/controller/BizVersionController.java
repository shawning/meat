package com.meet.app.controller;

import cn.hutool.json.JSONObject;

import com.alibaba.nacos.common.utils.HttpMethod;
import com.meet.app.service.BizVersionService;
import com.meet.app.vo.BizVersionVo;
import com.youlai.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 应用版本管理管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizVersion")
@Api(value = "/api.app/v1/bizVersion", tags = {"应用版本升级 API"}, description = "应用版本管理 API")
public class BizVersionController {
    @Autowired
    private BizVersionService bizVersionService;

    /**
     * 查询应用版本管理列表
     *
     * @param bizVersionVo 入参查询
     * @return R
     */
    @PostMapping("checkUpdate")
    @ApiOperation(notes = "查询应用版本管理列表",
            httpMethod = HttpMethod.POST,
            value = "查询应用版本管理列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result checkUpdate(@RequestBody BizVersionVo bizVersionVo){
        log.info("bizVersionVo ===> ", new JSONObject(bizVersionVo));
        return bizVersionService.checkUpdate();
    }
}
