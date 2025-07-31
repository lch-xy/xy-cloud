package org.xy.cloud.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

@Component
public class HeaderForwardInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 从当前请求上下文中获取原始请求
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 透传所有指定头部（避免透传敏感头如Cookie）
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                if (shouldForwardHeader(headerName)) {
                    template.header(headerName, request.getHeader(headerName));
                }
            }
        }
    }

    private boolean shouldForwardHeader(String headerName) {
        // 白名单：只透传业务相关头部
        return headerName.startsWith("X-") ||
                headerName.equalsIgnoreCase("Authorization");
    }
}
