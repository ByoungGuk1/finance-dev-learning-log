package com.shinhan.bananaapp.exception;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountExceptionTestService {
    private final AccountMapper mapper;

    public AccountDTO throwException(Long accId) {
        AccountDTO foundAccount = mapper.findById(accId);
        if (foundAccount == null) {
            throw new AccountNotFoundException(accId);
        }
        return foundAccount;
    }
}
