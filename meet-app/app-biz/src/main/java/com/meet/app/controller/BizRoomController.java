package com.meet.app.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meet.app.entity.BizRoom;
import com.meet.app.service.BizRoomService;
import com.meet.app.vo.BizRoomVo;
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
 * 直播房间号管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizRoom")
@Api(value = "/api.app/v1/bizRoom", tags = {"直播房间 API"}, description = "直播房间 API")
public class BizRoomController {
    @Autowired
    private BizRoomService bizRoomService;

    @PostMapping("/list")
    @ApiOperation(notes = "直播间列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "直播间列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizRoomVo", value = "直播间列表", required = true, paramType = "body", dataType = "BizRoomVo")
    public Result list(@RequestBody BizRoomVo bizRoomVo) {
        return bizRoomService.list(bizRoomVo);
    }
    @ApiOperation(notes = "直播间详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "直播间详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "roomId", value = "标签ID", required = true, paramType = "path", dataType = "Long")
    @GetMapping("/detail/{roomId}")
    public Result detail(@PathVariable Long roomId) {
        return bizRoomService.detail(roomId);
    }


    @ApiOperation(notes = "创建聊天室",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "创建聊天室",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizRoom", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizRoom")
    })
    @PostMapping(value = "/add")
    public Result add(
            @RequestBody BizRoom bizRoom) {
        return bizRoomService.add(bizRoom);
    }


    @ApiOperation(notes = "开始直播",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "开始直播",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/start")
    public Result start() {
        return bizRoomService.start();
    }


    @ApiOperation(notes = "结束直播",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "结束直播",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "roomId", value = "房间号", required = true, paramType = "path", dataType = "String")
    @GetMapping("/end")
    public Result end(@PathVariable String roomId) {
        return bizRoomService.end(roomId);
    }
    @ApiIgnore
    @ApiOperation(notes = "修改直播房间号",
                httpMethod = HttpMethod.POST,
                response = Result.class,
                value = "修改直播房间号",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizRoom", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizRoom")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizRoom bizRoom) {
        return bizRoomService.update(bizRoom);
    }
    @ApiIgnore
    @ApiOperation(notes = "删除多个直播房间号",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "删除多个直播房间号",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "ids", value = "以,分割拼接字符串", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/deletes/{ids}")
    public Result deletes(@PathVariable String ids) {
        return bizRoomService.delete(ids);
    }

    @ApiIgnore
    @ApiOperation(notes = "删除单个直播房间号",
                httpMethod = HttpMethod.DELETE,
                response = Result.class,
                value = "删除单个直播房间号",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "数据ID", required = true, paramType = "query", dataType = "Long")
    @DeleteMapping("/delete/{ids}")
    public Result delete(@PathVariable Long id) {
        return bizRoomService.delete(id);
    }

    @ApiOperation(notes = "进入直播间",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "进入直播间",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "roomId", value = "房间号", required = true, paramType = "query", dataType = "String")
    @GetMapping("/join/{roomId}")
    public Result joinRoom(@PathVariable String roomId){
        return bizRoomService.join(roomId);

    }

    @ApiOperation(notes = "离开直播间",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "离开直播间",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "roomId", value = "房间号", required = true, paramType = "query", dataType = "String")
    @GetMapping("/leave/{roomId}")
    public Result leaveRoom(@PathVariable String roomId){
        return bizRoomService.leave(roomId);

    }

}
