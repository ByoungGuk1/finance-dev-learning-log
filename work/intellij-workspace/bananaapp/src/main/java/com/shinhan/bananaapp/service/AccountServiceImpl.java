package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shinhanAccount")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    //    @Qualifier("accRepo")
    private final AccountRepository accountRepository;

    public List<AccountDTO> selectAllAccounts() {
        return accountRepository.getAllAccountList();
    }

    public AccountDTO selectById(long id) {
        return accountRepository.getAccountById(id);
    }

    public Boolean insertAccount(AccountDTO accountDTO) {
        if (accountRepository.getAccountById(accountDTO.getId()) == null) {
            return accountRepository.saveAccount(accountDTO);
        }
        return false;
    }

    public Boolean updateAccount(AccountDTO accountDTO) {
        if (accountRepository.getAccountById(accountDTO.getId()) == null) {
            return false;
        }
        return accountRepository.saveAccount(accountDTO);
    }

    public Boolean deleteAccount(long id) {
        return accountRepository.deleteAccount(id);
    }
}
