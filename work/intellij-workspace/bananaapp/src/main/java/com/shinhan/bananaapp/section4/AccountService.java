package com.shinhan.bananaapp.section4;

public class AccountService {
    private final Notifier notifier;

    AccountService(Notifier notifier) {
        this.notifier = notifier; // 생성과 함께 주입
    }

    public void transfer(Long amount) {
        notifier.send("이체 완료: " + amount + "원");
    }
}
