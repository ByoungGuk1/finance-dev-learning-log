package com.shinhan.bananaapp.section5;

import com.shinhan.bananaapp.dto.AccountDTO;
import org.springframework.stereotype.Repository;

@Repository("/empRepo") // @Component + DAO기능 => <bean id="empRepository" class="" />
public class EmpRepository {
    public AccountDTO getData(){
        return AccountDTO.builder()
                .accountNo("123")
                .ownerName("이름")
                .balance(12000L)
                .accountType("예금")
                .build();
    }
}
