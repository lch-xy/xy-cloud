package org.xy.cloud;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;
/**
 * 代码生成器
 * @author lch
 * @date 2025/7/11
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("LCH")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")
                            .enableSwagger()
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("org.xy.cloud")
                            .moduleName("")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user").addInclude("sys_role").addInclude("sys_user_role").addInclude("sys_role_menu").addInclude("sys_menu")
                            .addTablePrefix("")
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
