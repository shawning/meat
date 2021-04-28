package com.meet.app.service.im;

import com.youlai.common.result.Result;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/4/23 下午11:19
 * @Description
 */
public interface IMUserService {
    /**
     * 创建一个用户
     */
    Result create(String username,String password);

    /**
     * 删除一个用户
     * @param username
     * @return
     */
    Result delete(String username);

    /**
     * 删除所有用户
     * @return
     */
    Result deleteAll();

    /**
     * 强制该用户所有设备下线
     * @param username
     * @return
     */
    Result forceLogoutAllDevices(String username);

    /**
     * 强制该用户某个设备下线
     * @param username
     * @param resource
     * @return
     */
    Result forceLogoutOneDevice(String username, String resource);

    /**
     * 获得某个用户的信息
     * @param username
     * @return
     */
    Result get(String username);

    /**
     * 获得用户在线状态
     * @param username
     * @return
     */
    Result isUserOnline(String username);

    /**
     * 获取全部用户
     * @return
     */
    Result listAllUsers();

    /**
     * 分页获取用户
     * @param limit
     * @param cursor
     * @return
     */
    Result listUsers(int limit, String cursor);

    /**
     * 修改用户名密码
     * @param username
     * @param password
     * @return
     */
    Result updateUserPassword(String username, String password);

}
