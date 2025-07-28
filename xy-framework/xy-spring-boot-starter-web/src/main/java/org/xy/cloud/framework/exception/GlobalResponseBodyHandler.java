package org.xy.cloud.framework.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.xy.cloud.framework.common.pojo.CommonResponse;

@ControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    @Value("${spring.profiles.active:dev}")
    private String active;

    /**
     * 要拦截那些接口
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (returnType.getMethod() == null){
            return false;
        }
        // 只拦截controller
        return returnType.getMethod().getReturnType() == CommonResponse.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 要带上<?> 表示可以是任何类型 ，如果不带会有类型擦除警告
        if ("prod".equals(active)){
            if (body instanceof CommonResponse<?> commonResponse && commonResponse.getCode() >= 500) {
                commonResponse.setMessage("服务器开小差了，请稍后再试");
            }
        }
        return body;
    }
}
