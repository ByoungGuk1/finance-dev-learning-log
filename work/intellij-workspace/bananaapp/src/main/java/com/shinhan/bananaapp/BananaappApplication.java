package com.shinhan.bananaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

//@Slf4j
@SpringBootApplication
//@EnableJpaAuditing  //@EntityListeners(AuditingEntityListener.class) 사용을 위해 엔티티 사용 시 리스너 =>> 막는 이유: WebMvcTest에서 주입을 하지 못해서 에러 발생 따라서 설정파일로 분리(config.JpaAuditingConfig로 분리)
//@ComponentScan(basePackages = {"com.shinhan.bananaapp", "net.firstzone.other"})
@EnableAspectJAutoProxy // @Aspect를 찾아서 주업무에 보조 업무를 삽입
public class BananaappApplication {
  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(BananaappApplication.class, args);
//        List<String> filters = List.of(context.getBeanNamesForType(Filter.class));
//        filters.forEach(System.out::println);
    List<String> filters = List.of(context.getBeanNamesForType(FilterRegistrationBean.class));
//    filters.forEach(log::info);
  }
}
