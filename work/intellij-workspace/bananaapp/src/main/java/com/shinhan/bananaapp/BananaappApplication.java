package com.shinhan.bananaapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@Slf4j
@SpringBootApplication
//@ComponentScan(basePackages = {"com.shinhan.bananaapp", "net.firstzone.other"})
@EnableAspectJAutoProxy // @Aspect를 찾아서 주업무에 보조 업무를 삽입
public class BananaappApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BananaappApplication.class, args);
//        List<String> filters = List.of(context.getBeanNamesForType(Filter.class));
//        filters.forEach(System.out::println);
        List<String> filters = List.of(context.getBeanNamesForType(FilterRegistrationBean.class));
        filters.forEach(log::info);
    }
}
