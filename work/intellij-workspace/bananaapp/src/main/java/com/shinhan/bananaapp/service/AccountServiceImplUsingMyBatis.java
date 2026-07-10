package com.shinhan.bananaapp.service;

import com.shinhan.bananaapp.dto.AccountDTO;
import com.shinhan.bananaapp.dto.AccountSearchDTO;
import com.shinhan.bananaapp.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImplUsingMyBatis implements AccountService {
    // 1. @Mapper 사용
    private final AccountMapper accRepo;

    // 2. SQLSession 사용
//    private final AccountRepositorySQLSession accRepo;

    private final Long MAX_TRANSFER_AMOUNT = 100_000L;

    @Transactional(readOnly = true)
    public List<AccountDTO> selectAllAccounts() {
        return accRepo.findAll();
    }

    @Transactional(readOnly = true)
    public AccountDTO selectById(long id) {
        return accRepo.findById(id);
    }

    public Boolean insertAccount(AccountDTO accountDTO) {
        return accRepo.insert(accountDTO) >= 1;
    }

    public Boolean updateAccount(AccountDTO accountDTO) {
        return accRepo.update(accountDTO) >= 1;
    }

    public Boolean deleteAccount(long id) {
        return accRepo.delete(id) >= 1;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public Boolean transaction(Long fromId, Long toId, Long amount) {
        Map<String, Object> req = new HashMap<>();

        validateTransfer(fromId, amount);
        validateTransfer(toId, amount);

        req.put("fromId", fromId);
        req.put("toId", toId);
        req.put("amount", amount);

        log.info("계정 {}번이 계정 {}번에게 {}원 이체", fromId, toId, amount);

        int sendResult = accRepo.withdraw(req);
        if (amount > 500L) {
            throw new IllegalArgumentException("그냥 던진 오류");
        }
        int receiveResult = accRepo.deposit(req);

        if (sendResult != receiveResult) {
            throw new RuntimeException("결과 불일치");
        }

        return true;
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> selectByCondition(AccountSearchDTO accountSearchDTO) {
        return accRepo.findByCondition(accountSearchDTO);
    }

    public AccountDTO validateTransfer(Long id, Long amount) {
        AccountDTO foundAccountDTO = accRepo.findById(id);

        if (foundAccountDTO == null) {
            throw new RuntimeException("Account not found");
        }

        if (foundAccountDTO.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("잔액 부족");
        }

        if (amount > MAX_TRANSFER_AMOUNT) {
            throw new RuntimeException("1회 이체 한도 초과");
        }
        return foundAccountDTO;
    }
}








