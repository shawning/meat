package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizGifts;
import com.meet.app.service.BizGiftsService;
import com.meet.app.vo.BizGiftsVo;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 礼物管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizGifts")
@Api(value = "/api.app/v1/bizGifts", tags = {"礼物列表 API"}, description = "礼物 API")
public class BizGiftsController {
    @Autowired
    private BizGiftsService bizGiftsService;

    @PostMapping("/list")
    @ApiOperation(notes = "礼物列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "礼物列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizGiftsVo", value = "礼物列表", required = true, paramType = "body", dataType = "BizGiftsVo")
    public Result list(@RequestBody BizGiftsVo bizGiftsVo) {
        return bizGiftsService.list(bizGiftsVo);
    }


    @ApiIgnore
    @ApiOperation(notes = "礼物详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "礼物详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizGiftsService.detail(id);
    }

    @ApiIgnore
    @ApiOperation(notes = "新增礼物",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "新增礼物",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizGifts", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizGifts")
    @PostMapping("/add")
    public Result add(@RequestBody BizGifts bizGifts) {
        return bizGiftsService.add(bizGifts);
    }

    @ApiIgnore
    @ApiOperation(notes = "修改礼物",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改礼物",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGifts", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizGifts")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizGifts bizGifts) {
        return bizGiftsService.update(bizGifts);
    }

    @ApiIgnore
    @ApiOperation(notes = "删除多个礼物",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个礼物",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizGiftsService.delete(ids);
    }

    @ApiIgnore
    @ApiOperation(notes = "删除单个礼物",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个礼物",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
        @DeleteMapping("/delete/{ids}")
        public Result delete(@PathVariable Long id) {
            return bizGiftsService.delete(id);
        }

}
