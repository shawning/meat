package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizMedals;
import com.meet.app.service.BizMedalsService;
import com.meet.app.vo.BizMedalsVo;
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
 * 勋章管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizMedals")
@Api(value = "/api.app/v1/bizMedals", tags = {"勋章列表 API"}, description = "勋章 API")
public class BizMedalsController {
    @Autowired
    private BizMedalsService bizMedalsService;

    @PostMapping("/list")
    @ApiOperation(notes = "勋章列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "勋章列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizMedalsVo", value = "勋章列表", required = true, paramType = "body", dataType = "BizMedalsVo")
    public Result list(@RequestBody BizMedalsVo bizMedalsVo) {
        return bizMedalsService.list(bizMedalsVo);
    }
    @ApiOperation(notes = "勋章详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "勋章详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizMedalsService.detail(id);
    }

    @ApiOperation(notes = "新增勋章",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "勋章标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "BizMedals", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizMedals")
    @PostMapping("/add")
    public Result add(@RequestBody BizMedals bizMedals) {
        return bizMedalsService.add(bizMedals);
    }

    @ApiOperation(notes = "修改勋章",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改勋章",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizMedals", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizMedals")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizMedals bizMedals) {
        return bizMedalsService.update(bizMedals);
    }

    @ApiOperation(notes = "删除多个勋章",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个勋章",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizMedalsService.delete(ids);
    }

    @ApiOperation(notes = "删除单个勋章",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个勋章",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
        @DeleteMapping("/delete/{ids}")
        public Result delete(@PathVariable Long id) {
            return bizMedalsService.delete(id);
        }

}
