package com.shinhan.bananaapp.repository.mybatis;

import com.shinhan.bananaapp.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccountRepository {
    private final List<AccountDTO> accounts = new ArrayList<>();

    {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setOwnerName("Hong");
        AccountDTO accountDTO1 = new AccountDTO(2L, "000-111-222", "Hong2", 12000L, "", LocalDate.now(), null);
        accountDTO1.setAccountType("예금");
        AccountDTO accountDTO2 = AccountDTO.builder()
                .id(3L)
                .accountNo("111-222-333")
                .ownerName("Hong3")
                .balance(12000L)
                .accountType("예금")
                .createdAt(LocalDate.now())
                .build();
        accounts.add(accountDTO);
        accounts.add(accountDTO1);
        accounts.add(accountDTO2);
    }

    public List<AccountDTO> getAllAccountList() {
        return accounts;
    }

    public AccountDTO getAccountById(long id) {
        return accounts.stream().filter(acc -> acc.getId().equals(id)).findFirst().orElse(null);
    }

    public Boolean saveAccount(AccountDTO accountDTO) {
        AccountDTO foundAcc = getAccountById(accountDTO.getId());
        try {
            if (foundAcc == null) {
                accounts.add(accountDTO);
            } else {
                foundAcc.setAccountNo(accountDTO.getAccountNo().isEmpty() ? foundAcc.getAccountNo() : accountDTO.getAccountNo());
                foundAcc.setOwnerName(accountDTO.getOwnerName().isEmpty() ? foundAcc.getOwnerName() : accountDTO.getOwnerName());
                foundAcc.setBalance(accountDTO.getBalance() == 0L ? foundAcc.getBalance() : accountDTO.getBalance());
                foundAcc.setAccountType(accountDTO.getAccountType().isEmpty() ? foundAcc.getAccountType() : accountDTO.getAccountType());
//                foundAcc.setCreatedAt(accountDTO.getCreatedAt().toString().isEmpty() ? foundAcc.getCreatedAt() : accountDTO.getCreatedAt());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean deleteAccount(Long id) {
        AccountDTO foundAcc = getAccountById(id);
        if (foundAcc != null) {
            return accounts.remove(foundAcc);
        }
        return false;
    }
}
