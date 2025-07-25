package org.xy.cloud.system.server.security.auth;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xy.cloud.system.server.mapper.user.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        List<org.xy.cloud.system.server.domain.user.vo.User> users = userMapper.selectByMap(map);
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在");
        }
        org.xy.cloud.system.server.domain.user.vo.User user = users.get(0);
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
    }
}
