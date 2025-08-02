package org.xy.cloud.framework.config.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    /**
     * 配置全局 Jackson 的 ObjectMapper
     * Spring Boot 会自动使用这个 ObjectMapper 处理 JSON
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        /**
         * 1. 统一 java.util.Date 的格式
         *    - 默认是时间戳，改为 yyyy-MM-dd HH:mm:ss
         */
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        /**
         * 2. 设置时区
         *    - 避免服务器默认时区与前端显示不一致
         */
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        /**
         * 3. 禁用时间戳输出
         *    - 让 LocalDateTime 和 Date 都按字符串输出
         */
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        /**
         * 4. 处理 Java 8 时间类型（LocalDateTime）
         *    - 默认是 ISO-8601 格式，这里改为 yyyy-MM-dd HH:mm:ss
         */
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        mapper.registerModule(javaTimeModule);

        /**
         * 5. long 转 String
         *    - 避免前端 JS 精度丢失
         */
        SimpleModule longModule = new SimpleModule();
        longModule.addSerializer(Long.class, ToStringSerializer.instance);
        longModule.addSerializer(Long.TYPE, ToStringSerializer.instance); // 处理 long 基本类型
        mapper.registerModule(longModule);

        /**
         * 6. 控制 null 值
         *    - 不输出 null 字段，让 JSON 更简洁
         */
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }

}
