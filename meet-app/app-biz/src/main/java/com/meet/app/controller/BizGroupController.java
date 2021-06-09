package com.meet.app.controller;
import com.alibaba.nacos.common.utils.HttpMethod;
import com.meet.app.entity.BizRoom;
import com.meet.app.service.BizGroupService;
import com.meet.app.vo.*;
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
 * 直播房间号管理
 * @author xiaoning
 */

@Slf4j
@RestController
@RequestMapping("/api.app/v1/bizGroup")
@Api(value = "/api.app/v1/bizRoom", tags = {"群组 API"}, description = "群组 API")
public class BizGroupController {
    @Autowired
    private BizGroupService bizGroupService;

    @PostMapping("/list")
    @ApiOperation(notes = "获取所有的群组列表",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "获取所有的群组列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "bizGroupVo", value = "群组列表", required = true, paramType = "body", dataType = "BizGroupVo")
    public Result list(@RequestBody BizGroupVo bizGroupVo) {
        return bizGroupService.listAllGroups(bizGroupVo);
    }

    @ApiOperation(notes = "群组详情",
                httpMethod = HttpMethod.GET,
                response = Result.class,
                value = "群组详情",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "groupId", value = "群组ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/detail/{groupId}")
    public Result detail(@PathVariable String groupId) {
        return bizGroupService.getGroup(groupId);
    }

    @ApiOperation(notes = "群组公告",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "群组公告",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "groupId", value = "群组ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/announcement/{groupId}")
    public Result getGroupAnnouncement(@PathVariable String groupId){
        return bizGroupService.getGroupAnnouncement(groupId);
    }
    @ApiOperation(notes = "群组成员",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "群组成员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "groupId", value = "群组ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/listAllGroupMembers/{groupId}")
    public Result listAllGroupMembers(@PathVariable String groupId){
        return bizGroupService.listAllGroupMembers(groupId);
    }

    @ApiOperation(notes = "创建非公开群组",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "创建非公开群组",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizRoom", value = "群组信息", required = true, paramType = "body", dataType = "BizRoom")
    })
    @PostMapping(value = "/createPrivateGroup")
    public Result createPrivateGroup(
            @RequestBody BizRoom bizRoom) {
        return bizGroupService.createPrivateGroup(bizRoom);
    }
    @ApiOperation(notes = "创建公开群组",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "创建公开群组",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizRoom", value = "群组信息", required = true, paramType = "body", dataType = "BizRoom")
    })
    @PostMapping(value = "/createPublicGroup")
    public Result createPublicGroup(
            @RequestBody BizRoom bizRoom) {
        return bizGroupService.createPublicGroup(bizRoom);
    }

    @ApiOperation(notes = "注销群组",
            httpMethod = HttpMethod.DELETE,
            response = Result.class,
            value = "注销群组",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "groupId", value = "群组ID", required = true, paramType = "query", dataType = "String")
    @DeleteMapping("/destroyGroup/{groupId}")
    public Result destroyGroup(@PathVariable String groupId) {
        return bizGroupService.destroyGroup(groupId);
    }

    @ApiOperation(notes = "获取群组管理员",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "获取群组管理员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "groupId", value = "群组ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/listGroupAdmins/{groupId}")
    public Result listGroupAdmins(@PathVariable String groupId){
        return bizGroupService.listGroupAdmins(groupId);
    }
    @ApiOperation(notes = "我的群组列表",
            httpMethod = HttpMethod.GET,
            response = Result.class,
            value = "我的群组列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "groupId", value = "群组ID", required = true, paramType = "path", dataType = "String")
    @GetMapping("/listGroupsUserJoined")
    public Result listGroupsUserJoined(){
        return bizGroupService.listGroupsUserJoined();
    }
    @ApiOperation(notes = "取消管理员",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "取消管理员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGroupRemoveAdminVo", value = "移除群主管理员信息", required = true, paramType = "body", dataType = "BizGroupRemoveAdminVo")
    })
    @PostMapping(value = "/removeGroupAdmin")
    public Result removeGroupAdmin(
            @RequestBody BizGroupAdminVo bizGroupRemoveAdminVo) {
        return bizGroupService.removeGroupAdmin(bizGroupRemoveAdminVo);
    }
    @ApiOperation(notes = "取消管理员",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "取消管理员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGroupRemoveMemberVo", value = "移除群员信息", required = true, paramType = "body", dataType = "BizGroupRemoveMemberVo")
    })
    @PostMapping(value = "/removeGroupMember")
    public Result removeGroupMember(
            @RequestBody BizGroupMemberVo bizGroupRemoveMemberVo) {
        return bizGroupService.removeGroupMember(bizGroupRemoveMemberVo);
    }
    @ApiOperation(notes = "修改群公告",
            httpMethod = HttpMethod.PUT,
            response = Result.class,
            value = "修改群公告",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGroupAnnouncementVo", value = "修改群公告", required = true, paramType = "body", dataType = "BizGroupAnnouncementVo")
    })
    @PutMapping(value = "/updateGroupAnnouncement")
    public Result updateGroupAnnouncement(
            @RequestBody BizGroupAnnouncementVo bizGroupAnnouncementVo) {
        return bizGroupService.updateGroupAnnouncement(bizGroupAnnouncementVo);
    }

    @ApiOperation(notes = "移交群主",
            httpMethod = HttpMethod.PUT,
            response = Result.class,
            value = "移交群主",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGroupRemoveAdminVo", value = "移除群主管理员信息", required = true, paramType = "body", dataType = "BizGroupRemoveAdminVo")
    })
    @PutMapping(value = "/updateGroupOwner")
    public Result updateGroupOwner(
            @RequestBody BizGroupAdminVo bizGroupRemoveAdminVo) {
        return bizGroupService.updateGroupOwner(bizGroupRemoveAdminVo);
    }
    @ApiOperation(notes = "添加群成员",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "添加群成员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGroupMemberVo", value = "添加群成员", required = true, paramType = "body", dataType = "BizGroupMemberVo")
    })
    @PostMapping(value = "/addGroupMember")
    public Result addGroupMember(
            @RequestBody BizGroupMemberVo bizGroupMemberVo) {
        return bizGroupService.addGroupMember(bizGroupMemberVo);
    }

    @ApiOperation(notes = "添加群管理员",
            httpMethod = HttpMethod.POST,
            response = Result.class,
            value = "添加群管理员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizGroupAdminVo", value = "添加群管理员", required = true, paramType = "body", dataType = "BizGroupAdminVo")
    })
    @PostMapping(value = "/addGroupAdmin")
    public Result addGroupAdmin(
            @RequestBody BizGroupAdminVo bizGroupAdminVo) {
        return bizGroupService.addGroupAdmin(bizGroupAdminVo);
    }

}
