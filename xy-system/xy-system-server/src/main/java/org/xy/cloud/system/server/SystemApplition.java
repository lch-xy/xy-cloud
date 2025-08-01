package org.xy.cloud.system.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统启动类
 *
 * @author lch
 * @date 2025/7/8
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"org.xy.cloud"})
public class SystemApplition {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplition.class,args);
    }

}