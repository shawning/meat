package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizInterests;
import com.meet.app.service.BizInterestsService;
import com.meet.app.vo.BizInterestsVo;
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
 * 用户兴趣爱好管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.admin/v1/bizInterests")
@Api(value = "/api.admin/v1/bizInterests", tags = {"兴趣爱好列表 API"}, description = "用户兴趣爱好 API")
public class BizInterestsController {
    @Autowired
    private BizInterestsService bizInterestsService;

    @PostMapping("/list")
    @ApiOperation(notes = "用户兴趣爱好列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "用户兴趣爱好列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizInterestsVo", value = "用户兴趣爱好列表", required = true, paramType = "body", dataType = "BizInterestsVo")
    public Result list(@RequestBody BizInterestsVo bizInterestsVo) {
        return bizInterestsService.list(bizInterestsVo);
    }
    @ApiOperation(notes = "用户兴趣爱好详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "用户兴趣爱好详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizInterestsService.detail(id);
    }

    @ApiOperation(notes = "新增用户兴趣爱好",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "用户兴趣爱好标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "BizInterests", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizInterests")
    @PostMapping("/add")
    public Result add(@RequestBody BizInterests bizInterests) {
        return bizInterestsService.add(bizInterests);
    }

    @ApiOperation(notes = "修改用户兴趣爱好",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改用户兴趣爱好",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizInterests", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizInterests")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizInterests bizInterests) {
        return bizInterestsService.update(bizInterests);
    }

    @ApiOperation(notes = "删除多个用户兴趣爱好",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个用户兴趣爱好",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizInterestsService.delete(ids);
    }

    @ApiOperation(notes = "删除单个用户兴趣爱好",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个用户兴趣爱好",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
        @DeleteMapping("/delete/{ids}")
        public Result delete(@PathVariable Long id) {
            return bizInterestsService.delete(id);
        }

}
