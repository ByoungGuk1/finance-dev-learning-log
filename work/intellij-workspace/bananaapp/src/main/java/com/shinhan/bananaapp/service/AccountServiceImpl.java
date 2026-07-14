package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.dto.AccountSearchDTO;
import com.shinhan.bananaapp.dto.AccountWithAttachmentDTO;
import com.shinhan.bananaapp.repository.mybatis.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Boolean transaction(Long fromId, Long toId, Long amount) {
        return false;
    }

    public List<AccountDTO> selectByCondition(AccountSearchDTO searchDTO) {
        return null;
    }

    public List<AccountWithAttachmentDTO> findAllWithAttachmentFlat(Long id) {
        return null;
    }

    public AccountDTO findByIdWithAttachment(Long id) {
        return null;
    }

    public void uploadAttachment(Long id, MultipartFile file) {
    }
}
