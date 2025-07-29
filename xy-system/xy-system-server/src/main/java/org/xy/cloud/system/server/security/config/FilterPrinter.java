//package org.xy.cloud.system.server.security.config;
//
//import jakarta.servlet.Filter;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * 打印filter
// */
//@Component
//public class FilterPrinter {
//
//    @Bean
//    CommandLineRunner printFilters(ApplicationContext context) {
//        return args -> {
//            Map<String, Filter> filters = context.getBeansOfType(Filter.class);
//            System.out.println("=== All Servlet Filters ===");
//            filters.forEach((name, filter) -> {
//                System.out.println(name + " -> " + filter.getClass().getName());
//            });
//        };
//    }
//}