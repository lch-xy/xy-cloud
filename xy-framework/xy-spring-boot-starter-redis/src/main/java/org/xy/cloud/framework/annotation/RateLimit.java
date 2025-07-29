package org.xy.cloud.framework.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 接口限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * 限流 Key，用于标识限流器
     */
    String key();

    /**
     * 每秒生成的令牌数（速率）
     */
    long rate() default 5;

    /**
     * 令牌桶容量
     */
    long capacity() default 10;

    /**
     * 请求需要消耗的令牌数
     */
    long permits() default 1;

    /**
     * 获取令牌的最大等待时间
     */
    long timeout() default 0;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 限流失败时的提示信息
     */
    String message() default "请求过于频繁，请稍后再试";
}
