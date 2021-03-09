package com.meet.app.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizUser;
import com.meet.app.entity.BizUserTag;
import com.meet.app.service.BizUserService;
import com.meet.app.service.BizUserTagService;
import com.meet.app.vo.BizUserTagVo;
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
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/3/8 下午1:11
 * @Description
 */
@Api(tags = "用户标签")
@RestController
@RequestMapping("/api.admin/v1/userTag")
@AllArgsConstructor
@Slf4j
public class BizUserTagController {
    @Autowired
    private BizUserTagService bizUserTagService;

    @GetMapping("/list")
    @ApiOperation(notes = "标签列表",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "标签列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizUserTagVo", value = "标签列表", required = true, paramType = "body", dataType = "BizUserTagVo")
    public Result list(@RequestBody BizUserTagVo bizUserTagVo) {
        return bizUserTagService.list(bizUserTagVo);
    }

    @ApiOperation(notes = "标签详情",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "标签详情",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizUserTagService.detail(id);
    }

    @ApiOperation(notes = "新增标签",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "新增标签",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "BizUserTag", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserTag")
    @PostMapping("/add")
    public Result add(@RequestBody BizUserTag bizUserTag) {
        return bizUserTagService.add(bizUserTag);
    }

    @ApiOperation(notes = "修改标签",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "修改标签",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签id", required = true, paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "bizUserTag", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserTag")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizUserTag bizUserTag) {
        return bizUserTagService.update(null, bizUserTag);
    }

    @ApiOperation(notes = "删除标签",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除标签",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/delete/{ids}")
    public Result delete(@PathVariable String ids) {
        return bizUserTagService.delete(ids);
    }

    @ApiOperation(notes = "查询用户标签",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "查询用户标签",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    })
    @PatchMapping(value = "user/{id}")
    public Result userTags(@PathVariable Long id) {
        return bizUserTagService.userTags(id);
    }
}
