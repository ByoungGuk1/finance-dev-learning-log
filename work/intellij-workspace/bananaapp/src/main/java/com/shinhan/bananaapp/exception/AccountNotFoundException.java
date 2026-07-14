package com.shinhan.bananaapp.exception;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(Long id) {
        super("Account_Not_Found", "계좌를 찾을 수 없습니다. id = " + id);
    }
}
