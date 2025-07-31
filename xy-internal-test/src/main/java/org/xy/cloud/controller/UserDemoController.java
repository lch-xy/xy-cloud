package org.xy.cloud.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xy.cloud.framework.common.pojo.CommonResponse;
import org.xy.cloud.system.api.user.AdminUserApi;
import org.xy.cloud.system.api.user.dto.UserDTO;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserDemoController {

    @Resource
    private AdminUserApi adminUserApi;


    @GetMapping("/getUserInfoById")
    public CommonResponse<UserDTO> getUserInfoById(@RequestParam("id") Long id){
        return adminUserApi.getUserInfoById(id);
    }

}
