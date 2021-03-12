package com.meet.app.controller;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.meet.app.entity.BizUserInterests;
import com.meet.app.service.BizUserInterestsService;
import com.meet.app.vo.BizUserInterestsVo;
import com.youlai.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * 用户兴趣爱好管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizUserInterests")
@Api(value = "/api.app/v1/bizUserInterests", tags = {"用户兴趣爱好 API"}, description = "用户兴趣爱好 API")
public class BizUserInterestsController {
    @Autowired
    private BizUserInterestsService bizUserInterestsService;

    @PostMapping("/list")
    @ApiOperation(notes = "用户兴趣爱好列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "用户兴趣爱好列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizUserInterestsVo", value = "用户兴趣爱好列表", required = true, paramType = "body", dataType = "BizUserInterestsVo")
    public Result list(@RequestBody BizUserInterestsVo bizUserInterestsVo) {
        return bizUserInterestsService.list(bizUserInterestsVo);
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
        return bizUserInterestsService.detail(id);
    }

    @ApiOperation(notes = "新增用户兴趣爱好",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "用户兴趣爱好标签",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizUserInterests", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserInterests")
    @PostMapping("/add")
    public Result add(@RequestBody BizUserInterests bizUserInterests) {
        return bizUserInterestsService.add(bizUserInterests);
    }

    @ApiOperation(notes = "修改用户兴趣爱好",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "修改用户兴趣爱好",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizUserInterests", value = "实体JSON对象", required = true, paramType = "body", dataType = "BizUserInterests")
    })
    @PutMapping(value = "/update")
    public Result update(
            @RequestBody BizUserInterests bizUserInterests) {
        return bizUserInterestsService.update(bizUserInterests);
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
        return bizUserInterestsService.delete(ids);
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
        return bizUserInterestsService.delete(id);
    }

}
