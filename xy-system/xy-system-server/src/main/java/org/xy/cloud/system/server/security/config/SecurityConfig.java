package org.xy.cloud.system.server.security.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.xy.cloud.system.server.security.auth.CustomUserDetailsService;
import org.xy.cloud.system.server.security.filter.JwtAuthenticationFilter;

/**
 * 实现 spring security 在web场景下的配置
 * @author lch
 * @date 2025/7/9
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Resource
    private CustomUserDetailsService userDetailsService;
    @Resource
    private AuthEntryPoint authEntryPoint;
    @Resource
    private JwtAuthenticationFilter jwtAuthFilter;

    // 可以设置多条SecurityFilterChain，根据不同的前缀进行区分
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // 禁用CSRF
                .formLogin(form -> form.disable())  // 禁用表单登录
                .httpBasic(basic -> basic.disable()) // 禁用HTTP
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/system/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authEntryPoint) // 自定义未认证返回
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
