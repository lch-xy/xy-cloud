package org.xy.cloud.system.server.api.user;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.xy.cloud.framework.common.pojo.CommonResponse;
import org.xy.cloud.system.api.user.AdminUserApi;
import org.xy.cloud.system.api.user.dto.UserDTO;
import org.xy.cloud.system.server.service.user.UserService;


@RestController
public class AdminUserApiImpl implements AdminUserApi {

    @Resource
    private UserService userService;

    @Override
    public CommonResponse<UserDTO> getUserInfoById(Long id) {
        return CommonResponse.success(BeanUtil.toBean(userService.getUserInfoById(id),UserDTO.class));
    }
}
