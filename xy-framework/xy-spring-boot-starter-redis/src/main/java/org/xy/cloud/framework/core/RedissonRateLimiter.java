package org.xy.cloud.framework.core;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonRateLimiter {

    private final RedissonClient redissonClient;

    /**
     * 尝试获取令牌
     */
    public boolean tryAcquire(String key, long rate, long permits, long timeout, TimeUnit timeUnit) {
        RRateLimiter rateLimiter = getOrCreateLimiter(key, rate);
        if (timeout > 0) {
            return rateLimiter.tryAcquire(permits, timeout, timeUnit);
        }
        return rateLimiter.tryAcquire(permits);
    }

    /**
     * 获取或创建限流器
     */
    private RRateLimiter getOrCreateLimiter(String key, long rate) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        if (!rateLimiter.isExists()) {
            rateLimiter.trySetRate(RateType.OVERALL, rate, 1, RateIntervalUnit.MINUTES);
        }
        return rateLimiter;
    }
}
