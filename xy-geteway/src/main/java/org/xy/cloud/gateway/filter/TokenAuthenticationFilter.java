package org.xy.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import org.xy.cloud.framework.common.utils.JwtUtil;
import reactor.core.publisher.Mono;

public class TokenAuthenticationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求头中的 token
        String token = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String username = JwtUtil.getUsernameFromToken(token);

            // 在请求前执行逻辑（如鉴权、日志）
            exchange.getRequest().mutate()
                    .header("login-username", username);
        }

        // 继续执行过滤器链
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 在响应后执行逻辑
            exchange.getResponse().getHeaders()
                    .add("X-Custom-Response", "processed");
        }));
    }
}
