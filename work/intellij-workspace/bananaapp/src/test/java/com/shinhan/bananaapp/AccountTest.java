package com.shinhan.bananaapp;

import com.shinhan.bananaapp.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AccountTest {

    @Test
    public void f1(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setOwnerName("Hong");
        AccountDTO accountDTO1 = new AccountDTO("000-111-222","Hong2",12000L,"");
        accountDTO1.setAccountType("예금");
        AccountDTO accountDTO2 = AccountDTO.builder()
                .accountNo("111-222-333")
                .ownerName("Hong3")
                .balance(12000L)
                .accountType("예금")
                .build();

        assertThat(accountDTO2.getOwnerName()).isEqualTo("Hong");
    }
}
