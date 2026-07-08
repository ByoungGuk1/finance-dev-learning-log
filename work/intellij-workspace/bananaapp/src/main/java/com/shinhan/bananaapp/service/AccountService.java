package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> selectAllAccounts();

    AccountDTO selectById(long id);

    Boolean insertAccount(AccountDTO accountDTO);

    Boolean updateAccount(AccountDTO accountDTO);

    Boolean deleteAccount(long id);
}
