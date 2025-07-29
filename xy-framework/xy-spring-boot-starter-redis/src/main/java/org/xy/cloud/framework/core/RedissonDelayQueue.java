package org.xy.cloud.framework.core;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonDelayQueue {

    private final RedissonClient redissonClient;

    public <T> void add(String queueName, T data, long delay, TimeUnit unit) {
        RBlockingQueue<T> blockingQueue = redissonClient.getBlockingQueue(queueName + ":queue");
        RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        delayedQueue.offer(data, delay, unit);
    }

    public <T> T take(String queueName) throws InterruptedException {
        RBlockingQueue<T> blockingQueue = redissonClient.getBlockingQueue(queueName + ":queue");
        return blockingQueue.take();
    }

    public <T> List<T> pollBatch(String queueName, int count) {
        RBlockingQueue<T> blockingQueue = redissonClient.getBlockingQueue(queueName + ":queue");
        List<T> list = new ArrayList<>();
        blockingQueue.drainTo(list, count);
        return list;
    }

    public int size(String queueName) {
        return redissonClient.getBlockingQueue(queueName + ":queue").size();
    }
}
