package org.xy.cloud.framework.core;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedissonBloomFilterManager {

    private final RedissonClient redissonClient;

    public RBloomFilter<Object> create(String name, long expectedInsertions, double falseProbability) {
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(name);
        bloomFilter.tryInit(expectedInsertions, falseProbability);
        return bloomFilter;
    }

    public boolean add(String name, Object value) {
        return redissonClient.getBloomFilter(name).add(value);
    }

    public boolean contains(String name, Object value) {
        return redissonClient.getBloomFilter(name).contains(value);
    }
}

