package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizUserMedal;
import com.meet.app.service.BizUserMedalService;
import com.meet.app.vo.BizUserMedalVo;
import com.youlai.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户勋章管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.admin/v1/bizUserMedal")
@Api(value = "/api.admin/v1/bizUserMedal", tags = {"用户勋章 API"}, description = "用户勋章 API")
public class BizUserMedalController {
    @Autowired
    private BizUserMedalService bizUserMedalService;

    @PostMapping("/list")
    @ApiOperation(notes = "用户勋章列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "用户勋章列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizUserMedalVo", value = "用户勋章列表", required = true, paramType = "body", dataType = "BizUserMedalVo")
    public Result list(@RequestBody BizUserMedalVo bizUserMedalVo) {
        return bizUserMedalService.list(bizUserMedalVo);
    }
    @ApiOperation(notes = "用户勋章详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "用户勋章详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizUserMedalService.detail(id);
    }

    @ApiOperation(notes = "新增用户勋章",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "用户勋章标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "BizUserMedal", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserMedal")
    @PostMapping("/add")
    public Result add(@RequestBody BizUserMedal bizUserMedal) {
        return bizUserMedalService.add(bizUserMedal);
    }

    @ApiOperation(notes = "修改用户勋章",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改用户勋章",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizUserMedal", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserMedal")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizUserMedal bizUserMedal) {
        return bizUserMedalService.update(bizUserMedal);
    }

    @ApiOperation(notes = "删除多个用户勋章",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个用户勋章",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizUserMedalService.delete(ids);
    }

    @ApiOperation(notes = "删除单个用户勋章",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个用户勋章",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
        @DeleteMapping("/delete/{ids}")
        public Result delete(@PathVariable Long id) {
            return bizUserMedalService.delete(id);
        }

}
