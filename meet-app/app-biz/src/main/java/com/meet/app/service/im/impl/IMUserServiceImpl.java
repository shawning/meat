package com.meet.app.service.im.impl;

import com.easemob.im.server.EMService;
import com.meet.app.service.im.IM;
import com.meet.app.service.im.IMUserService;
import com.youlai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/24 下午12:15
 * @Description
 */
@Slf4j
@Service
public class IMUserServiceImpl implements IMUserService {
    private EMService emService = IM.server();
    @Override
    public Result create(String username, String password) {
        return Result.success(emService.user().create(username, password));
    }

    @Override
    public Result delete(String username) {
        return Result.success(emService.user().delete(username));
    }

    @Override
    public Result deleteAll() {
        return Result.success(emService.user().deleteAll());
    }

    @Override
    public Result forceLogoutAllDevices(String username) {
        return Result.success(emService.user().forceLogoutAllDevices(username));
    }

    @Override
    public Result forceLogoutOneDevice(String username, String resource) {
        return Result.success(emService.user().forceLogoutOneDevice(username, resource));
    }

    @Override
    public Result get(String username) {
        return Result.success(emService.user().get(username));
    }

    @Override
    public Result isUserOnline(String username) {
        return Result.success(emService.user().isUserOnline(username));
    }

    @Override
    public Result listAllUsers() {
        return Result.success(emService.user().listAllUsers());
    }

    @Override
    public Result listUsers(int limit, String cursor) {
        return Result.success(emService.user().listUsers(limit, cursor));
    }

    @Override
    public Result updateUserPassword(String username, String password) {
        return Result.success(emService.user().updateUserPassword(username, password));
    }
}
