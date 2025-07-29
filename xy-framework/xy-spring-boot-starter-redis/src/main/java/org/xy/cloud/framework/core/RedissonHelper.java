package org.xy.cloud.framework.core;


import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RedissonHelper {

    private final RedissonClient redissonClient;

    // ================== Key 操作 ==================
    public boolean delete(String key) {
        return redissonClient.getBucket(key).delete();
    }

    public long deleteKeys(String... keys) {
        return redissonClient.getKeys().delete(keys);
    }

    public boolean exists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    public boolean existsAny(String... keys) {
        return redissonClient.getKeys().countExists(keys) > 0;
    }

    public void expire(String key, long timeout, TimeUnit unit) {
        redissonClient.getBucket(key).expire(timeout, unit);
    }

    public Set<String> scanKeys(String pattern) {
        return redissonClient.getKeys().getKeysStreamByPattern(pattern).collect(Collectors.toSet());
    }

    // ================== String 操作 ==================
    public <T> void set(String key, T value, long timeout, TimeUnit unit) {
        redissonClient.getBucket(key).set(value, timeout, unit);
    }

    public <T> T get(String key) {
        return (T) redissonClient.getBucket(key).get();
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        T val = (T) redissonClient.getBucket(key).get();
        return val == null ? defaultValue : val;
    }

    public boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        return redissonClient.getBucket(key).trySet(value, timeout, unit);
    }

    public long increment(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(delta);
    }

    public long decrement(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(-delta);
    }

    // ================== 批量操作 ==================
    public void multiSet(Map<String, Object> values) {
        values.forEach((k, v) -> redissonClient.getBucket(k).set(v));
    }

    public Map<String, Object> multiGet(Set<String> keys) {
        return keys.stream().collect(Collectors.toMap(k -> k, k -> redissonClient.getBucket(k).get()));
    }

    // ================== Hash 操作 ==================
    public <K, V> void hSet(String key, K hashKey, V value) {
        redissonClient.<K, V>getMap(key).put(hashKey, value);
    }

    public <K, V> V hGet(String key, K hashKey) {
        return redissonClient.<K, V>getMap(key).get(hashKey);
    }

    public <K> void hDelete(String key, K hashKey) {
        redissonClient.getMap(key).remove(hashKey);
    }

    public int hSize(String key) {
        return redissonClient.getMap(key).size();
    }

    public Map<Object, Object> hGetAll(String key) {
        return redissonClient.getMap(key).readAllMap();
    }

    // ================== List 操作 ==================
    public <T> void lPush(String key, T value) {
        redissonClient.<T>getDeque(key).addFirst(value);
    }

    public <T> void rPush(String key, T value) {
        redissonClient.<T>getDeque(key).addLast(value);
    }

    public <T> T lPop(String key) {
        return redissonClient.<T>getDeque(key).pollFirst();
    }

    public <T> T rPop(String key) {
        return redissonClient.<T>getDeque(key).pollLast();
    }

    public int lLen(String key) {
        return redissonClient.getDeque(key).size();
    }

    // ================== Set 操作 ==================
    public <T> boolean sAdd(String key, T value) {
        return redissonClient.<T>getSet(key).add(value);
    }

    public <T> boolean sRemove(String key, T value) {
        return redissonClient.<T>getSet(key).remove(value);
    }

    public int sSize(String key) {
        return redissonClient.getSet(key).size();
    }

    public <T> Set<T> sMembers(String key) {
        return redissonClient.<T>getSet(key).readAll();
    }

    // ================== ZSet 操作 ==================
    public boolean zAdd(String key, Object value, double score) {
        return redissonClient.getScoredSortedSet(key).add(score, value);
    }

    public int zRemove(String key, Object value) {
        return redissonClient.getScoredSortedSet(key).remove(value) ? 1 : 0;
    }

    public Collection<Object> zRange(String key, int start, int end) {
        return redissonClient.getScoredSortedSet(key).valueRange(start, end);
    }
}

