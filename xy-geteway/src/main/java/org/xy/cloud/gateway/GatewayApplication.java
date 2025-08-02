package org.xy.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务网关
 *
 * @author lch
 * @date 2025/7/4
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"org.xy.cloud"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}