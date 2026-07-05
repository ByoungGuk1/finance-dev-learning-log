package com.shinhan.bananaapp.section4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AppConfig2")
public class AppConfig {
    @Bean
    public Notifier notifier() {
        return new SmsNotifier(); // 여기만 바꾸면 끝
    }
    @Bean
    public AccountService accountService() {
        return new AccountService(notifier());
    }
}
