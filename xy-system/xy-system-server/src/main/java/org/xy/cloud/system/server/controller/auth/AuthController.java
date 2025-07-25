package org.xy.cloud.system.server.controller.auth;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xy.cloud.framework.common.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/system/auth")
public class AuthController {

    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        String token = JwtUtil.generateToken(authentication.getName());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
