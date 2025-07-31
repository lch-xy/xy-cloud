package org.xy.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 测试服务
 *
 * @author lch
 * @date 2025/7/31
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"org.xy.cloud"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}