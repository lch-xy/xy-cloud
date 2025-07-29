package org.xy.cloud.web.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动程序
 *
 * @author lch
 * @date 2025/7/4
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"org.xy.cloud"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}