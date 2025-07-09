package org.xy.cloud.system.server.service.user;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.xy.cloud.system.server.domain.user.vo.User;
import org.xy.cloud.system.server.mapper.user.UserMapper;

/**
 * @author lch
 * @date 2025/7/9
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public User getUserInfoById(Long id){
        return userMapper.selectById(id);
    }

}
