package org.xy.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xy.cloud.framework.annotation.RateLimit;
import org.xy.cloud.framework.core.*;

import java.util.Collection;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonDemoController {

    private final RedissonHelper redissonHelper;
    private final RedissonLockManager lockManager;
    private final RedissonDelayQueue delayQueue;
    private final RedissonPubSub pubSub;
    private final RedissonBloomFilterManager bloomFilterManager;
    private final RedissonRateLimiter rateLimiter;

    // ------------------ 基本缓存操作 ------------------
    @GetMapping("/string")
    public String stringOps() {
        redissonHelper.set("user:1001:name", "Alice", 60, TimeUnit.SECONDS);
        return "Name: " + redissonHelper.get("user:1001:name");
    }

    @GetMapping("/hash")
    public String hashOps() {
        redissonHelper.hSet("user:1001:profile", "email", "alice@example.com");
        return "Email: " + redissonHelper.hGet("user:1001:profile", "email");
    }

    @GetMapping("/list")
    public String listOps() {
        redissonHelper.rPush("queue:orders", "order001");
        return "Queue size: " + redissonHelper.lLen("queue:orders");
    }

    @GetMapping("/zset")
    public Collection<Object> zsetOps() {
        redissonHelper.zAdd("ranking:score", "userA", 100);
        redissonHelper.zAdd("ranking:score", "userB", 120);
        return redissonHelper.zRange("ranking:score", 0, 1);
    }

    // ------------------ 分布式锁 ------------------
    @GetMapping("/lock")
    public String tryLock() {
        String lockKey = "lock:order:create";
        boolean locked = lockManager.tryLock(lockKey, 3, 5, TimeUnit.SECONDS);
        if (!locked) {
            return "锁获取失败";
        }
        try {
            Thread.sleep(2000); // 模拟业务
            return "业务执行完成";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "被中断";
        } finally {
            lockManager.unlock(lockKey);
        }
    }

    // ------------------ 延迟队列 ------------------
    @GetMapping("/delay/add")
    public String addDelayedTask() {
        delayQueue.add("delay:orders", "order123", 10, TimeUnit.SECONDS);
        return "已添加延迟任务 (10秒后可消费)";
    }

    @GetMapping("/delay/take")
    public String consumeDelayedTask() throws InterruptedException {
        String task = delayQueue.take("delay:orders");
        return "消费延迟任务: " + task;
    }

    // ------------------ 发布订阅 ------------------
    @GetMapping("/pub")
    public String publish() {
        pubSub.publish("chat:room", "Hello World");
        return "已发布消息";
    }

    @GetMapping("/sub")
    public String subscribe() {
        pubSub.subscribe("chat:room", String.class, msg -> {
            System.out.println("收到消息: " + msg);
        });
        return "已订阅 chat:room";
    }

    // ------------------ 布隆过滤器 ------------------
    @GetMapping("/bloom/init")
    public String initBloom() {
        bloomFilterManager.create("bloom:users", 1000L, 0.01);
        bloomFilterManager.add("bloom:users", "user-1001");
        return "布隆过滤器已初始化";
    }

    @GetMapping("/bloom/check")
    public String checkBloom(@RequestParam String userId) {
        boolean exists = bloomFilterManager.contains("bloom:users", userId);
        return exists ? "可能存在" : "一定不存在";
    }

    // ------------------ 限流 ------------------
    @RateLimit(key = "api:test", rate = 3, permits = 1, message = "限流：每秒最多3次")
    @GetMapping("/rate")
    public String rateLimiterDemo() {
        return "success";
    }
}