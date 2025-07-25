package org.xy.cloud.system.server.controller.user;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xy.cloud.system.server.domain.user.vo.User;
import org.xy.cloud.system.server.service.user.UserService;

/**
 * @author lch
 * @date 2025/7/9
 */

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUserInfoById")
    public User getUserInfoById(@RequestParam("id") Long id){
        int i = 1 / 0;
        return userService.getUserInfoById(id);
    }
}
