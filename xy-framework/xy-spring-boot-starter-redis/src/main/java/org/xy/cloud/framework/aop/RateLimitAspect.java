package org.xy.cloud.framework.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.xy.cloud.framework.annotation.RateLimit;
import org.xy.cloud.framework.core.RedissonRateLimiter;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {

    private final RedissonRateLimiter redissonRateLimiter;

    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = rateLimit.key();
        long rate = rateLimit.rate();
        long permits = rateLimit.permits();
        long timeout = rateLimit.timeout();

        boolean allowed = redissonRateLimiter.tryAcquire(key, rate, permits, timeout, rateLimit.timeUnit());
        if (!allowed) {
            return rateLimit.message();  // 直接返回提示信息
        }
        return joinPoint.proceed();
    }
}
