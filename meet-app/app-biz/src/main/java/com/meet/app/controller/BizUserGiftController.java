package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizUserGift;
import com.meet.app.service.BizUserGiftService;
import com.meet.app.vo.BizUserGiftVo;
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
 * 用户收到的礼物管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizUserGift")
@Api(value = "/api.app/v1/bizUserGift", tags = {"用户收到的礼物 API"}, description = "用户收到的礼物 API")
public class BizUserGiftController {
    @Autowired
    private BizUserGiftService bizUserGiftService;

    @PostMapping("/list")
    @ApiOperation(notes = "用户收到的礼物列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "用户收到的礼物列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizUserGiftVo", value = "用户收到的礼物列表", required = true, paramType = "body", dataType = "BizUserGiftVo")
    public Result list(@RequestBody BizUserGiftVo bizUserGiftVo) {
        return bizUserGiftService.list(bizUserGiftVo);
    }
    @ApiOperation(notes = "用户收到的礼物详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "用户收到的礼物详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Long id) {
        return bizUserGiftService.detail(id);
    }

    @ApiOperation(notes = "新增用户收到的礼物",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "用户收到的礼物标签",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "BizUserGift", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserGift")
    @PostMapping("/add")
    public Result add(@RequestBody BizUserGift bizUserGift) {
        return bizUserGiftService.add(bizUserGift);
    }

    @ApiOperation(notes = "修改用户收到的礼物",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改用户收到的礼物",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizUserGift", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserGift")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizUserGift bizUserGift) {
        return bizUserGiftService.update(bizUserGift);
    }

    @ApiOperation(notes = "删除多个用户收到的礼物",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个用户收到的礼物",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizUserGiftService.delete(ids);
    }

    @ApiOperation(notes = "删除单个用户收到的礼物",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个用户收到的礼物",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
        @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
        @DeleteMapping("/delete/{ids}")
        public Result delete(@PathVariable Long id) {
            return bizUserGiftService.delete(id);
        }

}
