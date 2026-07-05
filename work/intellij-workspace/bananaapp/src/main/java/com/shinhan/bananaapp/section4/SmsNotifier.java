package com.shinhan.bananaapp.section4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsNotifier implements  Notifier {
    @Override
    public void send(String message) {
        log.info("Sending email to {}", message);
    }
}
