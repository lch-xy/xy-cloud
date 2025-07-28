//package org.xy.cloud.system.server.security.filter;
//
//import jakarta.servlet.*;
//import org.apache.catalina.core.ApplicationFilterChain;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//
///**
// * 打印 Security filter chain
// */
//@Component
//@Order(Integer.MIN_VALUE)
//public class FilterOrderLogger implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        if (chain instanceof org.apache.catalina.core.ApplicationFilterChain appChain) {
//            try {
//                Field filtersField = ApplicationFilterChain.class.getDeclaredField("filters");
//                filtersField.setAccessible(true);
//                Object[] filters = (Object[]) filtersField.get(appChain);
//                System.out.println("=== 当前请求的 Filter 执行顺序 ===");
//                for (Object filter : filters) {
//                    System.out.println(filter);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}