package com.shinhan.bananaapp.section4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private final AccountService service;
    // 생성자 주입 — Spring이 알아서 넣어줌
    public AppRunner(AccountService service) {
        this.service = service;
    }
    @Override
    public void run(String... args) throws Exception {
        service.transfer(300_000L);
    }
}

