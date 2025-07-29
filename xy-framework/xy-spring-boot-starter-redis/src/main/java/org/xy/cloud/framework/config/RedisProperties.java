package org.xy.cloud.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "xy.redis")
public class RedisProperties {

    private String host;

    private int port;

    private String password;

    private int database;

    private long timeout;

    private long lockWatchdogTimeout;

}
