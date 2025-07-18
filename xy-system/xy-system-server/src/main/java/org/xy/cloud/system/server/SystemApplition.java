package org.xy.cloud.system.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统启动类
 *
 * @author lch
 * @date 2025/7/8
 */
@SpringBootApplication
@MapperScan("org.xy.cloud")
public class SystemApplition {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplition.class,args);
    }
}