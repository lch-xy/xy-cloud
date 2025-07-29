package org.xy.cloud.framework.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RedisProperties.class)
public class RedissonAutoConfiguration {


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(RedisProperties properties) {
        Config config = new Config();
        String redisUrl = "redis://" + properties.getHost() + ":" + properties.getPort();
        config.useSingleServer()
                .setAddress(redisUrl)
                .setDatabase(properties.getDatabase())
                .setPassword(properties.getPassword())
                .setTimeout((int) properties.getTimeout())
                .setConnectionMinimumIdleSize(2)
                .setConnectionPoolSize(10);
        config.setLockWatchdogTimeout(properties.getLockWatchdogTimeout());
        config.setCodec(new JsonJacksonCodec());
        return Redisson.create(config);
    }

}
