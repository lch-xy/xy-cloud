package org.xy.cloud.framework.core;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedissonPubSub {

    private final RedissonClient redissonClient;

    public void publish(String topic, Object message) {
        redissonClient.getTopic(topic).publish(JSON.toJSONString(message));
    }

    public <T> void subscribe(String topic, Class<T> clazz, MessageListener<T> listener) {
        redissonClient.getTopic(topic).addListener(String.class, (channel, msg) -> {
            T data = JSON.parseObject(msg, clazz);
            listener.onMessage(data);
        });
    }

    @FunctionalInterface
    public interface MessageListener<T> {
        void onMessage(T message);
    }
}

