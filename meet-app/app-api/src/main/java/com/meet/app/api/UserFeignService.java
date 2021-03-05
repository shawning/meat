package com.meet.app.api;

import com.meet.app.dto.AppUserDto;
import com.youlai.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/2/25 下午5:38
 * @Description
 */
@FeignClient("meet-app")
public interface UserFeignService {
    /**
     * 获取用户信息
     */
    @GetMapping("/api.app/v1/bizUser/get/{phone}")
    Result<AppUserDto> getUserByPhone(@PathVariable String phone);

    /**
     * 新增用户
     * @param bizUser
     * @return
     */
    @PostMapping("/api.app/v1/bizUser/add")
    Result add(@RequestBody AppUserDto bizUser);
}
