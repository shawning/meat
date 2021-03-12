package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizTags;
import com.meet.app.service.BizTagsService;
import com.meet.app.vo.BizTagsVo;
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
 * 用户标签管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizTags")
@Api(value = "/api.app/v1/bizTags", tags = {"用户标签 API"}, description = "用户标签 API")
public class BizTagsController {
    @Autowired
    private BizTagsService bizTagsService;

    @PostMapping("/list")
    @ApiOperation(notes = "用户标签列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "用户标签列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizTagsVo", value = "用户标签列表", required = true, paramType = "body", dataType = "BizTagsVo")
    public Result list(@RequestBody BizTagsVo bizTagsVo) {
        return bizTagsService.list(bizTagsVo);
    }
    @ApiOperation(notes = "用户标签详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "用户标签详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizTagsService.detail(id);
    }

    @ApiOperation(notes = "新增用户标签",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "用户标签标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizTags", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizTags")
    @PostMapping("/add")
    public Result add(@RequestBody BizTags bizTags) {
        return bizTagsService.add(bizTags);
    }

    @ApiOperation(notes = "修改用户标签",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改用户标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizTags", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizTags")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizTags bizTags) {
        return bizTagsService.update(bizTags);
    }

    @ApiOperation(notes = "删除多个用户标签",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个用户标签",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizTagsService.delete(ids);
    }

    @ApiOperation(notes = "删除单个用户标签",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个用户标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
        @DeleteMapping("/delete/{ids}")
        public Result delete(@PathVariable Long id) {
            return bizTagsService.delete(id);
        }

}
